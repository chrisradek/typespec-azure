export function isTelemetryDisabled() {
  return Boolean(process.env.DISABLE_TYPESPEC_AZURE_TELEMETRY);
}
