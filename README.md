# Chat Improvements

**A lightweight, client-side Minecraft Fabric mod that improves chat without changing how the server works.**

> **v0.1.0 beta — test build, not a production release.** Only timestamps are implemented so far. Player heads, compact/expanded mode, search and other planned features are not yet included. GitHub CI builds check compilation, but the mod still needs in-game testing.

## Current feature: timestamps

- Timestamp prefix on Minecraft chat messages: `[14:05] Steve: Hello!`
- 24-hour or 12-hour clock; optional seconds
- Simple settings commands, no server installation required
- Preserves incoming message component styling; decoration happens at the client HUD
- No logging of messages, telemetry or external requests

### Client commands

| Command | Action |
| --- | --- |
| `/chatimprovements` | Show active settings |
| `/chatimprovements timestamps on` | Enable timestamps |
| `/chatimprovements timestamps off` | Disable timestamps |
| `/chatimprovements format 12` | Use 12-hour time |
| `/chatimprovements format 24` | Use 24-hour time |
| `/chatimprovements seconds on` | Show seconds |
| `/chatimprovements seconds off` | Hide seconds |
| `/chatimprovements reload` | Reload the configuration file |

Preferences are saved locally at `config/chatimprovements.json`. Changing settings affects **new incoming messages only**, not chat history already displayed. Client commands may conflict with a server command of the same name; this is a beta limitation.

## Target / compatibility

- Minecraft Java **26.2**
- **Fabric client** with Fabric API installed
- Java 25
- No server mod required; designed for singleplayer and vanilla/Paper multiplayer
- Other chat mods may overwrite the same UI methods. Compatibility is not yet verified.

## Install and test

1. Install Fabric Loader and Fabric API for 26.2.
2. Open the successful GitHub Actions build run on the feature PR.
3. Download its `chat-improvements-dev` artifact and extract the **non-sources** `.jar`.
4. Copy it to your Minecraft `mods` directory, then start the Fabric 26.2 profile.
5. Join a **test** world or server, send and receive a message, and try `/chatimprovements`.
6. Open an issue with the Minecraft log and steps to reproduce if something fails.

**Do not upload a test jar to Modrinth yet.** It requires game validation and compliance review.

## Build

The CI workflow installs Java 25 and Gradle 9.7.1 and runs `gradle build`; finished jars are uploaded as workflow artifacts. For local development use JDK 25 and Gradle 9.7.1. Verify dependency updates against [Fabric's official example mod](https://github.com/FabricMC/fabric-example-mod/tree/26.2).

## Code & credits

This is an original work, **not a fork** of Chat Heads, Chat Plus or Chat Patches. No code or assets from these mods were imported. MIT license, [LICENSE](LICENSE). See [AGENTS.md](AGENTS.md) and [ROADMAP.md](ROADMAP.md). Development uses substantial AI assistance with human review and contribution; disclose this if submitting to Modrinth.
