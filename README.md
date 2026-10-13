# Chat Improvements

**A lightweight Fabric 26.2 client-only Minecraft mod to make chat easier to read and use.**

## Current verified baseline
- v0.1 timestamp prefix verified in the game on multiplayer.
- v0.2 player-head sprites verified to render for the local player in a private world.
- v0.3 preview code builds in CI. In-game testing is still required.

## v0.3.0-preview.3 integration test
- Timestamp format options (12/24 h, optional seconds)
- [C] hover/click action to copy the original message text
- Optional [@] local username mention indicator
- Press **C** (rebindable via Controls > Key Binds) or type **/ci** for a native English/Spanish settings menu
- **Player Heads** menu tab: heads on/off (on by default), skin's outer hat layer (on), and experimental server-format fallback (off by default)
- The old command **/chatimprovements** is an alias for /ci

### Large servers and player heads
Authenticated sender profiles work with normal player chat; many big Paper/Proxy servers transform chat into system text or use nicknames, ranks and other patterns. Client-only identity data may be unavailable. The opt-in system-message fallback supports only exact `<Username> text` for players known to the local tab list and can misidentify/spoof a speaker. It does NOT fix all public-server formats. Issue #8 tracks that work; do not treat guessed heads as verified senders.

## Installation
1. Install Minecraft Java 26.2 Fabric + compatible Fabric API.
2. Download `chat-improvements-dev` from a SUCCESSFUL GitHub Actions build for `integration/v0.3-preview` at the **preview.3** commit or later.
3. Extract the non-`sources` jar to `.minecraft/mods`, removing any previous Chat Improvements jar.
4. Press C while playing, then visit the Player Heads tab. Follow `V03_PREVIEW_TEST.md`.
5. If game crashes, share the crash-report file or last 100 lines of `logs/latest.log` with unrelated info redacted.

Configuration is stored locally at `config/chatimprovements.json`. New settings affect future messages; existing lines are not rewritten. No outgoing packets, telemetry, network requests by our code, or chat history storage.

## Technical / contribution
Minecraft 26.2, Fabric Loader 0.19.5, Fabric API 0.161.0+26.2, Java 25, Gradle 9.7.1. Original code; no imported Chat Heads assets. Development substantially AI-assisted; follow marketplace disclosure and original human contribution requirements for any eventual public release. MIT license.
