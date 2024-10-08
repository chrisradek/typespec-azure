import {
  Diagnostic,
  EnumMember,
  Type,
  Union,
  UnionVariant,
  createDiagnosticCollector,
  isNullType,
} from "@typespec/compiler";
import { createDiagnostic } from "../lib.js";

export interface UnionEnumBase<K extends string, T> {
  kind: K;
  /** Reference to the type used to construct this enum */
  union: Union;
  /** Member of this enum. */
  members: Map<string | symbol, UnionEnumVariant<T>>;
  /** Any union used as a union variant. */
  include: UnionEnumBase<K, T>;
  /** Flattened member of this enum, this is the {@see members} with the members of the {@see include} union flattened inside. */
  flattenedMembers: Map<string | symbol, UnionEnumVariant<T>>;
  /** If the enum is open. */
  open: boolean;
  /** If the union included null as a variant */
  nullable: boolean;
}

export interface UnionEnumVariant<T> {
  value: T;
  /** Reference to the type used to construct this enum member */
  type: UnionVariant | EnumMember;
}

export type UnionEnum = UnionEnumBase<"string", string> | UnionEnumBase<"number", number>;

/**
 * Tries to convert a union into an enum.
 * If the union only contains the same type of literal options with optionally
 * the base scalar to mark extensibility we can represent this union as an enum of that type.
 *
 * @param union Union to try to convert
 *
 * @example
 *
 * Simple closed string enum
 *
 * ```tsp
 * union PetKind { "cat", "dog" }
 * ```
 *
 * ```ts
 * const [enum, diagnostics] = getUnionAsEnum(union);
 * enum.open === false
 * enum.open.members.get("cat") // { value: "cat", variant: ... }
 * enum.open.members.get("dog") // { value: "dog", variant: ... }
 * ```
 *
 * Simple open string enum
 *
 * ```tsp
 * union PetKind { Cat: "cat", Dog: "dog", string }
 * ```
 *
 * ```ts
 * const [enum, diagnostics] = getUnionAsEnum(union);
 * enum.open === true
 * enum.open.members.get("Cat") // { value: "cat", variant: ... }
 * enum.open.members.get("Dog") // { value: "dog", variant: ... }
 * ```
 *
 * Invalid case
 *
 * ```tsp
 * union PetKind { Cat: "cat", Dog: 123, string }
 * ```
 *
 * ```ts
 * const [enum, diagnostics] = getUnionAsEnum(union);
 * enum === undefined
 * diagnostics.length === 1
 * ```
 */
export function getUnionAsEnum(union: Union): [UnionEnum | undefined, readonly Diagnostic[]] {
  return getUnionAsEnumInternal(union, new Set());
}

function getUnionAsEnumInternal(
  union: Union,
  visited: Set<Union>,
): [UnionEnum | undefined, readonly Diagnostic[]] {
  if (visited.has(union)) {
    return [undefined, [createDiagnostic({ code: "union-enums-circular", target: union })]];
  }
  visited.add(union);
  const diagnostics = createDiagnosticCollector();
  function reportInvalidKind(type: Type) {
    diagnostics.add(
      createDiagnostic({
        code: "union-enums-invalid-kind",
        format: { kind: type.kind },
        target: union,
      }),
    );
  }

  const kinds = new Set<UnionEnum["kind"]>();
  const members = new Map<string | symbol, UnionEnumVariant<any>>();
  const flattenedMembers = new Map<string | symbol, UnionEnumVariant<any>>();
  let open = false;
  let nullable = false;
  for (const variant of union.variants.values()) {
    switch (variant.type.kind) {
      case "Intrinsic":
        if (isNullType(variant.type)) {
          nullable = true;
        } else {
          reportInvalidKind(variant.type);
        }
        break;
      case "String":
        kinds.add("string");
        members.set(variant.name, { value: variant.type.value, type: variant });
        break;
      case "Number":
        kinds.add("number");
        members.set(variant.name, { value: variant.type.value, type: variant });
        break;
      case "Union":
        const parentUnion = diagnostics.pipe(getUnionAsEnumInternal(variant.type, visited));
        if (parentUnion !== undefined) {
          kinds.add(parentUnion.kind);
          if (parentUnion.open) {
            open = true;
          }
          if (parentUnion.nullable) {
            nullable = true;
          }
          for (const [key, value] of parentUnion.flattenedMembers) {
            flattenedMembers.set(key, value);
          }
        }
        break;
      case "Enum":
        for (const member of variant.type.members.values()) {
          kinds.add(typeof member.value === "number" ? "number" : "string");
          flattenedMembers.set(member.name, { value: member.value ?? member.name, type: member });
        }
        break;
      case "Scalar":
        const scalar = variant.type;
        switch (scalar.name) {
          case "string":
            kinds.add("string");
            open = true;
            break;
          case "float":
          case "float32":
          case "float64":
          case "integer":
          case "int8":
          case "int16":
          case "int32":
          case "int64":
          case "uint8":
          case "uint16":
          case "uint32":
          case "uint64":
            kinds.add("number");
            open = true;
            break;
          default:
            reportInvalidKind(variant.type);
        }
        break;
      default:
        reportInvalidKind(variant.type);
    }
  }
  for (const [key, value] of members) {
    flattenedMembers.set(key, value);
  }
  if (kinds.size > 1) {
    diagnostics.add(
      createDiagnostic({
        code: "union-enums-multiple-kind",
        format: { kinds: [...kinds].join(", ") },
        target: union,
      }),
    );
  }
  if (diagnostics.diagnostics.length > 0) {
    return diagnostics.wrap(undefined);
  }

  if (flattenedMembers.size === 0) {
    return [undefined, []];
  }

  return [
    {
      kind: [...kinds][0],
      union,
      members,
      flattenedMembers,
      open,
      nullable,
    } as any,
    [],
  ];
}
