import { IXHROverride } from "@microsoft/1ds-core-js";

export function getFetchHttpOverride(): IXHROverride {
  return {
    sendPOST: (payload, onComplete) => {
      fetch(payload.urlString, {
        method: "POST",
        headers: {
          ...payload.headers,
          "Content-Type": "application/json",
        },
        body: payload.data,
      })
        .then(async (response) => {
          const body = await response.text();
          onComplete(response.status, convertHeadersToRecord(response.headers), body);
        })
        .catch(() => {
          // set to 0 so we can retry events
          onComplete(0, {});
        });
    },
  };
}

function convertHeadersToRecord(headers: Headers): Record<string, string> {
  const result: Record<string, string> = {};
  headers.forEach((value, key) => {
    result[key] = value;
  });
  return result;
}
