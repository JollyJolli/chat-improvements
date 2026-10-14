# Modrinth listing draft — Chat Improvements

**DRAFT ONLY. NOT A PUBLICATION APPROVAL.** Human owner must establish that the project is eligible under [Modrinth's August 2026 AI policy](https://modrinth.com/legal/rules), run real game QA, and approve the listing. In particular, projects primarily made with AI output **cannot** be publicly published there. This page text itself was drafted with AI assistance and requires the appropriate disclosure.

## Project fields

- **Name:** Chat Improvements
- **One-line summary:** Customize Minecraft chat with timestamps, copy and mention actions, player heads when identifiable, and Dark Neon chat themes.
- **Project type:** Mod
- **Side:** Client (not required on server)
- **Supported loader:** Fabric
- **Test target:** Minecraft Java 26.2, Java 25, Fabric Loader 0.19.5+ and Fabric API 0.161.0+26.2
- **License:** MIT (verify code/asset provenance before public upload)
- **Release type if approved:** Beta — not stable 1.0
- **Required dependency:** Fabric API (and Fabric Loader to run the mod)
- **Known compatibility limitation:** No guaranteed player heads on plugin-formatted large public servers; issue #8

## Plain English description draft

Chat Improvements is a lightweight, client-only Fabric mod that gives Minecraft chat a cleaner look and a few practical shortcuts. It adds configurable local timestamps, a one-click way to copy a message, optional highlights when your username is mentioned, and a Quick Mention button for players whose identities Minecraft can reliably identify.

You can choose from four Dark Neon accent colors, apply a custom look to the small in-game chat and the expanded chat window, and keep a compact set of lines on screen while playing. Press **C** (rebindable) or enter **/ci** to adjust settings. The old **/chatimprovements** command still works as an alias. English, Spanish, Brazilian Portuguese and German interfaces are included.

The mod is client-side only. It does not require a server plugin, does not send extra chat packets, and does not store a persistent chat log. Existing Minecraft chat input and reporting features are intended to remain controlled by the game.

**Important limitation:** Player head icons are available when reliable player data exists, but are not guaranteed on large servers that reformat messages as system chat. An experimental text-based fallback is off by default and may associate the wrong player with a message. Its result must never be mistaken for verified identity.

### Installation

1. Install Minecraft Java 26.2 using Fabric Loader and a compatible Fabric API on Java 25.
2. Place only the approved release's main Chat Improvements JAR in your Minecraft instance's `mods` folder. Remove older copies first.
3. Start Minecraft and press **C** while playing or type **/ci**.

### Limitations

Only the listed version/loader has been targeted; other chat mods, unusually sized GUI layouts, large-server plugin formats and accessibility modes require explicit testing. Report reproduction details using the GitHub bug template, with redacted log snippets.

### Credits and source

Project development and issue tracking: https://github.com/JollyJolli/chat-improvements

**AI disclosure:** Development, code and draft publishing materials received substantial generative AI assistance. An appropriate "Contains AI-generated content" disclosure is required on Modrinth. Eligibility depends on primary meaningful human authorship; this is not established merely by testing or by publishing this draft.

## Release notes draft: v0.4.0 (Beta)

- Dark Neon native compact/expanded chat appearance and four saved accent choices.
- Optional compact HUD and Quick Mention for identified players.
- Configurable timestamps, copy action, mention highlighting and identified player heads.
- Local anonymous player-head diagnostics and extra settings reliability work.
- English, Spanish, Brazilian Portuguese and German interfaces.
- No server-side installation or persistent chat-history collection.

Known limitation: Player heads remain unreliable on some large public servers that display player-looking messages as system chat (#8). Other chat mods and nonstandard GUI scales are not guaranteed compatible until tested.

**The above release notes are provisional and must be updated after testing the exact final JAR.**

## Evidence to collect BEFORE Modrinth upload

- [ ] Screenshots from the **real Minecraft client** showing original vanilla chat vs Chat Improvements, compact HUD, expanded chat, settings/themes and representative head/copy/mention actions. Do not use AI-created/edited images.
- [ ] Exact JAR checksum and matching GitHub Actions build SHA.
- [ ] Completed signed-off manual QA matrix for the final candidate.
- [ ] License/provenance and primary human contribution assessment with real evidence.
- [ ] Appropriate AI content disclosure selected in Modrinth.
- [ ] Explicit final owner sign-off and correct project fields/dependencies.
