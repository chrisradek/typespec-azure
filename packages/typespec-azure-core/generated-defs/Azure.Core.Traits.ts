import type { DecoratorContext, EnumMember, Model, ModelProperty, Type } from "@typespec/compiler";

/**
 * `@trait` marks a model type as representing a 'trait' and performs basic validation
 * checks.
 *
 * @param target The model type to mark as a trait.
 * @param traitName An optional name to uniquely identify the trait.  If unspecified,
 * the model type name is used.
 */
export type TraitDecorator = (context: DecoratorContext, target: Model, traitName?: string) => void;

/**
 * `@traitLocation` sets the applicable location for a trait on its envelope property.
 *
 * @param target The trait envelope property where the context will be applied.
 * @param contexts An enum member or union of enum members representing the trait's
 * applicable contexts.
 */
export type TraitLocationDecorator = (
  context: DecoratorContext,
  target: ModelProperty,
  contexts: EnumMember,
) => void;

/**
 * `@traitContext` sets the applicable context for a trait on its envelope property.
 *
 * @param target The trait envelope property where the context will be applied.
 * @param contexts An enum member or union of enum members representing the trait's
 * applicable contexts.
 */
export type TraitContextDecorator = (
  context: DecoratorContext,
  target: ModelProperty,
  contexts: Type,
) => void;

/**
 * Sets the version for when the trait was added to the specification.  Can be applied
 * to either a trait model type or its envelope property.
 *
 * @param addedVersion The enum member representing the service version.
 */
export type TraitAddedDecorator = (
  context: DecoratorContext,
  target: Model | ModelProperty,
  addedVersion: Type,
) => void;

export type AzureCoreTraitsDecorators = {
  trait: TraitDecorator;
  traitLocation: TraitLocationDecorator;
  traitContext: TraitContextDecorator;
  traitAdded: TraitAddedDecorator;
};
