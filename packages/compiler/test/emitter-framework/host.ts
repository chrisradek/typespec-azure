import { fileURLToPath } from "url";
import { resolvePath } from "../../core/index.js";
import { createAssetEmitter, TypeEmitter } from "../../emitter-framework/index.js";
import { createTestHost, TypeSpecTestLibrary } from "../../testing/index.js";

import assert from "assert";
import { SinonSpy, spy } from "sinon";

export const lib: TypeSpecTestLibrary = {
  name: "typespec-ts-interface-emitter",
  packageRoot: resolvePath(fileURLToPath(import.meta.url), "../../../"),
  files: [
    {
      realDir: "",
      pattern: "package.json",
      virtualPath: "./node_modules/typespec-ts-interface-emitter",
    },
    {
      realDir: "dist/src",
      pattern: "*.js",
      virtualPath: "./node_modules/typespec-ts-interface-emitter/dist/src",
    },
  ],
};

export async function getHostForTypeSpecFile(contents: string, decorators?: Record<string, any>) {
  const host = await createTestHost();
  if (decorators) {
    await host.addJsFile("dec.js", decorators);
    contents = `import "./dec.js";\n` + contents;
  }
  await host.addTypeSpecFile("main.tsp", contents);
  await host.compile("main.tsp", {
    outputDir: "typespec-output",
  });
  return host;
}

export async function emitTypeSpec(
  Emitter: typeof TypeEmitter<any>,
  code: string,
  callCounts: Partial<Record<keyof TypeEmitter<any>, number>> = {},
  validateCallCounts = true
) {
  const host = await getHostForTypeSpecFile(code);
  const emitter = createAssetEmitter(host.program, Emitter, {
    emitterOutputDir: "typespec-output",
    options: {},
  } as any);
  const spies = emitterSpies(Emitter);
  emitter.emitProgram();
  await emitter.writeOutput();
  if (validateCallCounts) {
    assertSpiesCalled(spies, callCounts);
  }
  return emitter;
}

type EmitterSpies = Record<string, SinonSpy>;
function emitterSpies(emitter: typeof TypeEmitter<any, any>) {
  const spies: EmitterSpies = {};
  const methods = Object.getOwnPropertyNames(emitter.prototype);
  for (const key of methods) {
    if (key === "constructor") continue;
    if ((emitter.prototype as any)[key].restore) {
      // assume this whole thing is already spied.
      return spies;
    }
    if (typeof (emitter.prototype as any)[key] !== "function") continue;
    spies[key] = spy(emitter.prototype, key as any);
  }

  return spies;
}

function assertSpiesCalled(
  spies: EmitterSpies,
  callCounts: Partial<Record<keyof TypeEmitter<any>, number>>
) {
  for (const [key, spy] of Object.entries(spies)) {
    const expectedCount = (callCounts as any)[key] ?? 1;
    assert.equal(
      spy.callCount,
      expectedCount,
      `Emitter method ${key} should called ${expectedCount} time(s), was called ${spy.callCount} time(s)`
    );
  }
}