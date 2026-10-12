# Chat Improvements

**A lightweight, client-side Minecraft Fabric mod that improves chat without changing how the server works.**

> **v0.2.0-beta.1 test build.** Timestamps are proven to appear in actual multiplayer gameplay. Player heads are new and have not yet passed in-game testing. Build success is not runtime proof.

## Features

### Configurable timestamps (v0.1)
- Local timestamp prefix on Minecraft chat messages: `[21:03] <Steve> Hello!`
- 24-hour or 12-hour clock; optional seconds
- Configurable and saved locally, no server installation required

### Player heads (v0.2, experimental)
- Adds a real native Minecraft skin-head sprite next to an authenticated *player chat* message.
- Can toggle heads and the skin's outer "hat" layer independently.
- Uses the sender's UUID/profile provided by the game; **does not** guess names from formatted messages.
- Does not attach heads to system messages, fake server messages or chat whose sender cannot be reliably identified.
- **Important:** servers that route chat through plugins as system messages may show NO heads for those lines. This is intentional in this initial safety-first beta.
- Keeps vanilla text components, message styling and signed message data intact; no network packets are changed.
- The native Minecraft player-sprite is used rather than manually rendering skull textures.

### Client commands

| Command | Action |
| --- | --- |
| `/chatimprovements` | Show active settings |
| `/chatimprovements timestamps on/off` | Toggle timestamps |
| `/chatimprovements format 12/24` | Choose clock format |
| `/chatimprovements seconds on/off` | Toggle timestamp seconds |
| `/chatimprovements heads on/off` | Toggle player heads |
| `/chatimprovements hats on/off` | Toggle outer hat layer |
| `/chatimprovements reload` | Reload config |

Preferences are saved locally at `config/chatimprovements.json`. Existing visible chat lines are NOT retroactively changed by setting toggles. No chat log, server addresses, or telemetry are persisted.

## Compatibility
- Minecraft Java **26.2**, **Fabric client**, Fabric API, Java 25
- No server-side mod required, designed for vanilla and Paper multiplayer
- Another chat mod may already add player sprites: do not use two mods that both provide heads until we have added duplicate detection.
- Not yet tested with heavily modified chat UIs, custom font engines, GUI scaling or server nicknames.

## Install / test
1. Use Fabric Loader and Fabric API for 26.2.
2. Download `chat-improvements-dev` artifact from a successful GitHub Actions run on the v0.2 PR.
3. Extract **only** the non-`sources` jar and remove the previous beta jar from `mods` (do not keep two versions).
4. Launch Fabric 26.2 in a test world and join a server with genuine player chat. Check both heads and timestamps.
5. Try `/chatimprovements heads off`, `/chatimprovements heads on`, and `/chatimprovements hats off`.
6. If it fails to start, attach `.minecraft/crash-reports` text or the last part of `.minecraft/logs/latest.log` to an issue.

**Do not publish the test JAR on Modrinth until real in-game testing and compliance checks pass.**

## Build and licensing
Java 25 / Gradle 9.7.1 / Fabric 26.2 using the official example-mod dependency versions. Source licensed MIT. Original implementation; no Chat Heads code or art assets have been imported. Substantial AI assistance must be disclosed to Modrinth, alongside human contributions.
