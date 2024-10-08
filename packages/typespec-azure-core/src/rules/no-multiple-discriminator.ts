import {
  Discriminator,
  Model,
  createRule,
  getDiscriminator,
  paramMessage,
} from "@typespec/compiler";

export const noMultipleDiscriminatorRule = createRule({
  name: "no-multiple-discriminator",
  description: "Classes should have at most one discriminator.",
  severity: "warning",
  messages: {
    default: paramMessage`Class hierarchy for '${"name"}' should only have, at most, one discriminator, but found: ${"values"}.`,
  },
  create(context) {
    return {
      model: (model: Model) => {
        const discriminators = new Array<Discriminator>();
        let current: Model | undefined = model;
        while (current) {
          const discriminator = getDiscriminator(context.program, current);
          if (discriminator) {
            discriminators.push(discriminator);
          }
          current = current.baseModel;
        }
        if (discriminators.length > 1) {
          context.reportDiagnostic({
            target: model,
            format: {
              name: model.name,
              values: discriminators.flatMap((x) => x.propertyName).join(", "),
            },
          });
        }
      },
    };
  },
});
