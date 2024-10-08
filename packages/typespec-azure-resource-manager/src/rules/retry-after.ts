import { Operation, Program, createRule } from "@typespec/compiler";
import { getResponsesForOperation } from "@typespec/http";

import { getExtensions } from "@typespec/openapi";
import { isTemplatedInterfaceOperation } from "./utils.js";

/**
 * verify if retry-after header appears in response body .
 */
export const retryAfterRule = createRule({
  name: "retry-after",
  severity: "warning",
  description: "Check if retry-after header appears in response body.",
  messages: {
    default: `For long-running operations, the Retry-After header indicates how long the client should wait before polling the operation status, please add this header to the 201 or 202 response for this operation.`,
  },
  create(context) {
    return {
      operation: (op: Operation) => {
        if (isTemplatedInterfaceOperation(op)) {
          return;
        }
        const isLRO = getExtensions(context.program, op).has("x-ms-long-running-operation");
        if (isLRO && !hasRetryAfterHeader(context.program, op)) {
          context.reportDiagnostic({
            target: op,
          });
        }
      },
    };
  },
});

function hasRetryAfterHeader(program: Program, op: Operation) {
  return !!getResponsesForOperation(program, op)[0].find((re) =>
    re.responses.find((res) => res.headers?.["Retry-After"]),
  );
}
