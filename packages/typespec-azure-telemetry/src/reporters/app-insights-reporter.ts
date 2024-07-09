import { BreezeChannelIdentifier } from "@microsoft/applicationinsights-common";
import { ApplicationInsights } from "@microsoft/applicationinsights-web-basic";
import { getFetchHttpOverride } from "../http-overrides/fetch.js";
import { TelemetryReporter } from "./type.js";

export interface Props {
  instrumentationKey: string;
  endpointUrl?: string;
}

export function getTelemetryReporter(props: Props): TelemetryReporter {
  // TODO: Add buffering - so we can send events after user has opted in?
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

  return {
    logEvent(eventName, data) {
      appInsightsClient.track({
        name: eventName,
        data: data,
        baseType: "EventData",
        baseData: {
          name: eventName,
          properties: data.properties,
          measurements: data.measurements,
        },
      });
    },
    async flush() {
      appInsightsClient.flush(false);
    },
  };
}
