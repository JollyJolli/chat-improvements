# Development instructions — Chat Improvements

Client-only Minecraft Java 26.2 Fabric mod. Current toolchain: Java 25, Fabric Loader 0.19.5+, Fabric API 0.161.0+26.2, Loom 1.18-SNAPSHOT and Gradle 9.7.1.

## Guardrails
- Read `README.md`, `ROADMAP.md`, and the relevant issue before changing code.
- Implement small, reviewable PRs; use an isolated branch. Do not silently merge experiments or RCs into `main`.
- Never modify outgoing packets, signatures, chat trust indicators, reporting, or server behavior.
- Preserve vanilla rich chat Components, links, hover/click actions, focus, scrolling and text input.
- Player heads and Quick Mention must use reliable player identity metadata; system-message guesses are **unverified** and opt-in only. Never spoof trust.
- Keep all preferences local; no telemetry, server/player identifiers, persistent chat history, or nonconsensual logging.
- Do not block the Minecraft render thread with network requests or skin downloads.
- Check third-party licenses and avoid copied code/assets.
- Record substantial AI assistance. Modrinth currently requires honest disclosure, **significant primary human creation**, and bans AI-generated images on project pages.

## Development and tests
- Run `gradle build` and inspect CI on the exact commit.
- A successful CI build is **not** sufficient proof that runtime Mixin injection works.
- For chat renderer changes test Fabric 26.2 actual client: chat closed/open, theme off/on, all four accents, different GUI/chat scales, wrapping, scrolling, URLs/clicks/hover, chat reporting and chat history.
- For sender-attribution changes test authenticated player chat and plugin-reformatted system chat separately. Never claim compatibility where sender metadata is missing.
- For settings changes test persistence after restart and migration from previous config JSON.
- Attach only cropped screenshots and redacted logs; do not expose private chats, tokens or player details.
- Use the issue and PR templates. Capture what was tested **and not tested**.

## Release policy
- `main` stays on the last approved baseline while a candidate remains in review.
- Do not merge `release/v0.4.0-rc1` / PR #30 without explicit owner approval and final runtime QA.
- Do not call GitHub artifacts an official Modrinth release.
- See `V04_RELEASE_CANDIDATE.md` and `MODRINTH_RELEASE_CHECKLIST.md` before any publication.
