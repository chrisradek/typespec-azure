import { ModelProperty, Operation, createRule } from "@typespec/compiler";
import { getHttpOperation } from "@typespec/http";

function getCaseInsensitiveHeader(
  headers: Record<string, ModelProperty> | undefined,
  key: string,
): string | undefined {
  if (!headers) {
    return undefined;
  }
  for (const header of Object.keys(headers)) {
    if (header.toLowerCase() === key.toLowerCase()) {
      return header;
    }
  }
  return undefined;
}

/**
 * Ensure that LRO 202 responses have a Location Header.
 */
export const lroLocationHeaderRule = createRule({
  name: "lro-location-header",
  severity: "warning",
  url: "https://azure.github.io/typespec-azure/docs/libraries/azure-resource-manager/rules/lro-location-header",
  description: "A 202 response should include a Location response header.",
  messages: {
    default: `A 202 response should include a Location response header.`,
  },
  create(context) {
    return {
      operation: (op: Operation) => {
        const [httpOperation, _] = getHttpOperation(context.program, op);
        const responses = httpOperation.responses;
        for (const response of responses) {
          if (response.statusCodes !== 202) {
            continue;
          }
          for (const resp of response.responses) {
            if (getCaseInsensitiveHeader(resp.headers, "Location") === undefined) {
              context.reportDiagnostic({
                target: op,
              });
              return;
            }
          }
        }
      },
    };
  },
});
