import type { DecoratorContext, Model, ModelProperty, Operation } from "@typespec/compiler";

/**
 * `@example` - attaches example files to an operation. Multiple examples can be specified.
 *
 * `@example` can be specified on Operations.
 *
 * @param pathOrUri path or Uri to the example file.
 * @param title name or description of the example file.
 */
export type ExampleDecorator = (
  context: DecoratorContext,
  target: Operation,
  pathOrUri: string,
  title: string,
) => void;

/**
 * `@useRef` - is used to replace the TypeSpec model type in emitter output with a pre-existing named OpenAPI schema such as Azure Resource Manager common types.
 *
 * `@useRef` can be specified on Models and ModelProperty.
 *
 * @param jsonRef path or Uri to an OpenAPI schema.
 */
export type UseRefDecorator = (
  context: DecoratorContext,
  entity: Model | ModelProperty,
  jsonRef: string,
) => void;

export type AutorestDecorators = {
  example: ExampleDecorator;
  useRef: UseRefDecorator;
};
