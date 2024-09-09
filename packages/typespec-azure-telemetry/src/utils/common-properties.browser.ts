export function getCommonProperties() {
  return {
    sessionId: getRandomUUID(),
    "telemetry.sdk.name": "typespec-azure-telemetry",
    "telemetry.sdk.language": "javascript",
    "telemetry.sdk.version": "0.45.0",
  };
}

function getRandomUUID() {
  try {
    if (typeof crypto !== "undefined" && typeof crypto.randomUUID === "function") {
      return crypto.randomUUID();
    }
  } catch {
    // ignore error
  }
  return;
}
