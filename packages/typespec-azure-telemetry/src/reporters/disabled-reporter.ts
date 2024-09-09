import { TelemetryReporter } from "./types.js";

export function getTelemetryReporter(): TelemetryReporter {
  return {
    logEvent() {
      // do nothing
    },
    async flush() {
      // do nothing
    },
  };
}
