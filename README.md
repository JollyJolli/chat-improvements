# Chat Improvements

A lightweight, **client-side** Fabric mod to make Minecraft chat cleaner and easier to use while preserving the vanilla experience.

> **Status: initial development scaffold.** No chat features have been implemented yet. This repository is not a playable release.

## Vision

- Looks and feels vanilla during gameplay.
- Shows richer tools when the chat is opened.
- Works in singleplayer and on vanilla/Paper servers with no server installation.
- Never forges, alters, or impersonates signed chat messages.
- Every feature is configurable; compatibility beats cleverness.

## Target

- Minecraft Java **26.2**
- **Fabric**, client only
- Java **25**
- Gradle **9.5.1**, Fabric Loom **1.17-SNAPSHOT**, Fabric Loader **0.19.3**, Fabric API **0.156.0+26.2** (initial values from the Fabric 26.2 example template; verify during the first CI run)

## MVP plan

1. Buildable Fabric client project with CI.
2. Optional chat timestamps, respecting chat message styling and signatures.
3. Player head rendering with conservative sender detection and safe fallbacks.
4. Simple settings screen; compact chat remains the default.
5. Test compatibility (vanilla singleplayer, Paper 26.2 server, Sodium/Iris, popular chat mods).

See [ROADMAP.md](ROADMAP.md) for scope and [AGENTS.md](AGENTS.md) for development rules.

## Build

The starter ZIP deliberately does **not** include a Gradle Wrapper binary: generate an official wrapper with `gradle wrapper --gradle-version 9.5.1` from an installed Gradle or carry over the wrapper files from the official [Fabric 26.2 example mod](https://github.com/FabricMC/fabric-example-mod/tree/26.2). Never download or execute an untrusted wrapper JAR.

With JDK 25 and Gradle 9.5.1 available:

```bash
gradle build
```

After adding the Gradle Wrapper:

```bash
./gradlew build
```

Do not claim the starter has been compiled or tested until CI succeeds. The output from a successful build appears under `build/libs/`.

## Origin and legal

This is a new mod, not a fork of Chat Heads, Chat Patches, or Chat Plus. No code or assets from those projects are included. New dependencies or reused material need license verification, provenance and attribution. Significant AI assistance and original author contributions should be documented for a future Modrinth submission.

License: MIT (see [LICENSE](LICENSE)).
