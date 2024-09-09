export interface LogEventData {
  properties?: Record<string, string | undefined>;
  measurements?: Record<string, number | undefined>;
}

export interface TelemetryReporter {
  logEvent(eventName: string, data: LogEventData): void;
  flush(): Promise<void>;
}

export interface GetTelemetryReporterProps {
  instrumentationKey: string;
  endpointUrl?: string;
}

export type GetTelemetryReporter = (props: GetTelemetryReporterProps) => TelemetryReporter;
