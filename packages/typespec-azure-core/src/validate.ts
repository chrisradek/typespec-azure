import { getTelemetryReporter } from "@azure-tools/typespec-azure-telemetry";
import { Program } from "@typespec/compiler";
import { checkEnsureVerb, checkRpcRoutes } from "./decorators.js";

export function $onValidate(program: Program) {
  const telemetryReporter = getTelemetryReporter({
    instrumentationKey: "key",
    endpointUrl: "endpoint",
  });

  program.emitters.forEach((emitter) => {
    telemetryReporter.logEvent(emitter.metadata.name ?? `Unknown`, {
      properties: {
        emitterName: emitter.metadata.name,
        emitterVersion: emitter.metadata.version,
      },
    });
  });

  checkRpcRoutes(program);
  checkEnsureVerb(program);
}
