import { arch, platform, release, type, version } from "node:os";

export function getCommonProperties() {
  return {
    release: release(),
    platform: platform(),
    architecture: arch(),
    type: type(),
    version: version(),
  };
}
