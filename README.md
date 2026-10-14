# Chat Improvements

**A lightweight, client-only Minecraft Fabric mod for a beautiful, practical and safer-to-use chat.**

## v0.4.0 Release Candidate 1 — preview for final review

**Status:** v0.3 is on `main`; this v0.4 candidate is on an isolated release branch and is **NOT released to Modrinth or merged into main**. The owner has tested the native beta in Minecraft and confirmed the seven primary scenarios below work. Broader compatibility review remains.

### What is new in v0.4
- **Dark Neon native chat skin** in both the small in-game notification HUD and expanded ChatScreen opened using `T`: individual dark message rows, accent rails, window/input chrome. Supports Cyan, Violet, Amber and Lime; independently toggleable in `/ci` -> STYLE.
- **Compact/Expanded chat** — cap the unfocused HUD to 3, 5 or 8 lines, leave the vanilla focused history/scrolling available when typing. Configure in `/ci` -> LAYOUT.
- **Quick Mention `[M]`** — available for identified player-chat profiles; click to *suggest* `@Username ` in the editor. It NEVER sends automatically; as with vanilla suggestion actions, an unsent draft may be overwritten. Does not guess system-chat identities.
- **Anonymous Player Heads Diagnostics** — open in `/ci` -> HEADS -> Chat diagnostics to compare player-chat and system-chat counts between servers. Counters are session-only, no message text, player names or server identifiers stored/transmitted. **It does not fix heads on large public servers.**
- Retains v0.3 timestamps, `[C]` copy, @mention indicators, authenticated heads in singleplayer/private servers, reskinnable menu and configurable C shortcut.

### Compatibility / privacy
- Minecraft Java **26.2**, Fabric Loader 0.19.5+, Fabric API 0.161.0+26.2, Java 25.
- Client-side only. Do not install server plugin/mod. No persistent chat history recording, server traffic modification or signature forgery.
- Large public servers can reroute formatted messages as system chat. **Player heads are confirmed working in singleplayer and small private servers but remain missing in tested big public servers** — see issue #8. Optional system-chat fallback is off by default and may falsely identify a player; do not treat it as verified identity.
- Native chat-skin Mixins may conflict with other chat mods or nondefault scaling settings; reporting/click/hover, large scales and mod interoperability require further spot checks.

### In-game acceptance so far
The owner reported all seven primary checks passing: launch without crash; new mini chat; redesigned expanded chat and input; four themes and Custom Chat HUD ON/OFF; Compact/Expanded without lost messages; Quick Mention without auto-send; original heads (supported cases), copy, timestamps and mentions still function.

### Install and test this candidate
1. Install Minecraft 26.2 with Fabric Loader and matching Fabric API.
2. Download the `chat-improvements-dev` artifact from a successful GitHub Actions run of this **release candidate commit**, extract the non-`sources` JAR.
3. Remove all older Chat Improvements JARs from `.minecraft/mods` before copying the new JAR in.
4. Press `C` in-game or run `/ci` to configure the mod; press `T` for expanded chat.
5. Follow `V04_RELEASE_CANDIDATE.md` before any stable merge. Never share unredacted server logs, private chats or session tokens.

### Development
Source and issues: https://github.com/JollyJolli/chat-improvements

This project is original and MIT-licensed; no copied Chat Heads assets. AI assistance has been used substantially, and public release requires human review and compliance with the chosen hosting platform's requirements.
