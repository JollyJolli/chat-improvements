# Contributing to Chat Improvements

Thanks for making Minecraft chat better. All features must work **client-side** on unmodified Minecraft servers.

## Before coding

1. Search [existing issues](https://github.com/JollyJolli/chat-improvements/issues). Open a bug or feature issue if needed, and include reproduction/acceptance criteria.
2. Work in a small feature branch. Keep experiments and integration previews separate from stable `main`.
3. Read `AGENTS.md`, `README.md`, and `ROADMAP.md`. Target Minecraft 26.2, Fabric Loader 0.19.5+, Fabric API 0.161.0+26.2 and Java 25 unless the issue explicitly changes the support matrix.

## Development and validation

- Do not modify outgoing packets, chat signatures, signing/trust indicators, messages stored by Mojang, or server behavior.
- Keep formatting, links, clicks, hover components, scrolling, reporting, keyboard navigation and chat screen input intact.
- Never guess a verified sender from a server-formatted system message. Any experimental fallback must be opt-in and clearly marked unverified.
- Keep user settings local. Do not persist chat histories, usernames, server IPs, diagnostics or telemetry without deliberate opt-in and a privacy review.
- Do not run network/skin downloads on Minecraft's render thread.
- Run `gradle build` with Java 25 / Gradle 9.7.1, then **test the exact built JAR in Minecraft**. CI success only proves compilation; Minecraft/Fabric Mixins still require runtime testing.
- At minimum smoke-test singleplayer and a private server. For rendering changes, also test the closed mini HUD, expanded chat, GUI scaling, wrapped messages, links, scrolling, and chat reports. Clearly state anything not tested.
- Redact logs/screenshots; avoid posting private messages, public-server chat content, access tokens or unrelated players' details.
- Do not submit other mods' code, images or icons without compatible licenses and attribution. Disclose substantial AI-assisted contributions in PR descriptions.

## Pull requests

Describe what changed, link the issue, give acceptance results, list known limitations, and reference the exact passing Actions run and commit. Use the pull request checklist. Human approval is required before promoting a release candidate to stable `main`.

## Labels and milestones

GitHub issues are organized using `type: *`, `area: *`, `status: *` and `priority: *` labels, plus a release milestone. The repository's maintenance workflow initializes the existing issue catalog; newly reported issues should be triaged into the appropriate milestone. Do not close unresolved manual-QA or cross-server compatibility work just because a build passes.
