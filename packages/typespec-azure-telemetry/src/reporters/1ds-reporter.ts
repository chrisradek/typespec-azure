import { AppInsightsCore } from "@microsoft/1ds-core-js";
import { IChannelConfiguration, PostChannel } from "@microsoft/1ds-post-js";
import { getFetchHttpOverride } from "../http-overrides/fetch.js";
import { getNodeHttpOverride } from "../http-overrides/node-http.js";
import { promiseResolver } from "../utils/promise-resolver.js";
import { supportsFetch } from "../utils/supports-fetch.js";
import { GetTelemetryReporter } from "./types.js";

export const getTelemetryReporter: GetTelemetryReporter = (props) => {
  const appInsightsCore = new AppInsightsCore();
  const postChannel = new PostChannel();

  const postChannelConfig: IChannelConfiguration = {
    // 2 = TransportType.Fetch
    // transports: [2],
    alwaysUseXhrOverride: true,
    httpXHROverride: supportsFetch() ? getFetchHttpOverride() : getNodeHttpOverride(),
  };

  appInsightsCore.initialize(
    {
      instrumentationKey: props.instrumentationKey,
      endpointUrl: props.endpointUrl ?? "https://mobile.events.data.microsoft.com/OneCollector/1.0",
      loggingLevelConsole: 0,
      loggingLevelTelemetry: 0,
      disableCookiesUsage: true,
      disableDbgExt: true,
      channels: [[postChannel]],
      extensionConfig: {
        [postChannel.identifier]: postChannelConfig,
      },
    },
    []
  );

  return {
    logEvent(eventName, data) {
      appInsightsCore.track({
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
      const { promise, resolve } = promiseResolver<void>();
      appInsightsCore.flush(true, (flushComplete) => {
        resolve();
      });
      return promise;
    },
  };
};
