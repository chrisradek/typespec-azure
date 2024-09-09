import { BreezeChannelIdentifier } from "@microsoft/applicationinsights-common";
import { ApplicationInsights } from "@microsoft/applicationinsights-web-basic";
import { getFetchHttpOverride } from "../http-overrides/fetch.js";
import { getCommonProperties } from "../utils/common-properties.js";
import { isTelemetryDisabled } from "../utils/config.js";
import { getMachineId } from "../utils/machine-id.js";
import { getTelemetryReporter as getDisabledTelemetryReporter } from "./disabled-reporter.js";
import { TelemetryReporter } from "./types.js";

export interface GetTelemetryReporterProps {
  instrumentationKey: string;
  endpointUrl?: string;
}

export function getTelemetryReporter(props: GetTelemetryReporterProps): TelemetryReporter {
  if (isTelemetryDisabled()) {
    return getDisabledTelemetryReporter();
  }

  // configure app insights
  const appInsightsClient = new ApplicationInsights({
    instrumentationKey: props.instrumentationKey,
    endpointUrl: props.endpointUrl,
    autoTrackPageVisitTime: false,
    disableAjaxTracking: true,
    disableExceptionTracking: true,
    disableFetchTracking: true,
    disableCorrelationHeaders: true,
    disableCookiesUsage: true,
    disableFlushOnBeforeUnload: true,
    disableFlushOnUnload: true,
    emitLineDelimitedJson: true,
    extensionConfig: {
      [BreezeChannelIdentifier]: {
        alwaysUseXhrOverride: true,
        httpXHROverride: getFetchHttpOverride(),
      },
    },
  });

  const commonProps = getCommonProperties();
  const machineId = getMachineId();

  return {
    logEvent(eventName, data) {
      appInsightsClient.track({
        name: eventName,
        data: data,
        baseType: "EventData",
        ext: {
          user: {
            id: machineId,
          },
        },
        baseData: {
          name: eventName,
          properties: { ...commonProps, ...data.properties },
          measurements: data.measurements,
        },
      });
      appInsightsClient.flush(false);
    },
    async flush() {
      appInsightsClient.flush(false);
    },
  };
}
