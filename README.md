# Chat Improvements

**A client-only Fabric 26.2 mod to make Minecraft chat easier to read and use.**

## Project status — October 2026

- **Stable development baseline:** `main`, v0.3 Dark Neon Beta.
- **Earlier native chat release candidate:** `release/v0.4.0-rc1`, PR #30. Seven initial in-game beta scenarios were reported passing, but wider QA was outstanding.
- **Current candidate under review:** `release/v0.4.0-rc2-prep`, version `0.4.0-rc.2`. This candidate adds improvements from the v0.4 Quality Preview 2 and stricter JAR validation.
- **Publishing status:** Not published on Modrinth and **not approved to merge into main**. Runtime QA of this exact RC2 JAR, license/AI-policy review and human sign-off are pending.

## What is included in RC2

- Local timestamps, configurable for 12/24 hours and optional seconds.
- `[C]` copy-message button, optional `[@]` whole-username mention indicator, and `[M]` Quick Mention on reliably identified sender chat (*suggests* `@Username` in the editor; never auto-sends).
- Authentic player heads when Minecraft provides a reliable player identity (tested historically on local/private servers).
- Native Dark Neon mini-HUD message cards and expanded chat window/input frame, four accent colors and a toggle to restore vanilla visuals.
- Optional compact in-game chat cap: 3, 5 or 8 lines; full expanded chat history remains available.
- `/ci` configuration menu, rebindable C shortcut, and a separate anonymous diagnostics panel. Small-screen settings pagination and smaller diagnostics panels are included for testing.
- English, Spanish, Brazilian Portuguese and German interfaces. Local config validation and more reliable writing.
- Automated JUnit tests, translation consistency checks, metadata-verified JAR packaging and checksum generation.

### Compatibility & privacy

- Target: **Minecraft Java 26.2, Fabric Loader 0.19.5+, Fabric API 0.161.0+26.2 and Java 25**. Client-side only.
- The mod does not require a server plugin, alter outgoing signed chat, send its own telemetry, or store a persistent chat history.
- **Known limitation:** Some large public servers rewrite player messages as system chat, preventing trustworthy head attribution. The experimental text-based fallback is OFF by default and may infer the wrong player; never treat it as verified. See [#8](https://github.com/JollyJolli/chat-improvements/issues/8).
- Compatibility with other chat mods, unusual GUI/chat settings and public-server formats **has not been comprehensively verified**.

## Download for testing (not public release)

1. Open [GitHub Actions — Build](https://github.com/JollyJolli/chat-improvements/actions/workflows/build.yml), select a **successful run of `release/v0.4.0-rc2-prep` at the final commit**, and download `chat-improvements-dev`.
2. Extract the archive, verify the checksum if desired, and install the primary `.jar` (not `-sources.jar`) in your Minecraft instance's `mods` folder.
3. Remove all other Chat Improvements JARs before launching.
4. Install matching Fabric Loader and Fabric API on Minecraft 26.2 / Java 25, then press **C** or type `/ci` in-game.
5. Test against [RELEASE_GATE_RC2.md](RELEASE_GATE_RC2.md) and send feedback using the [issue form](https://github.com/JollyJolli/chat-improvements/issues/new/choose). Share only cropped screenshots and redacted logs.

## Release preparation and source

- [Exact RC2 quality gates](RELEASE_GATE_RC2.md)
- [Draft Modrinth description, dependencies and beta notes](MODRINTH_PAGE_DRAFT.md)
- [Human-captured screenshot checklist](SCREENSHOT_SHOT_LIST.md)
- [Changelog](CHANGELOG.md)
- [Contributing](CONTRIBUTING.md) · [Issue tracker](https://github.com/JollyJolli/chat-improvements/issues)

Source is MIT-licensed; inspect license provenance and third-party requirements before publication. **Development and publishing drafts have received substantial generative-AI assistance.** Modrinth's current rules prohibit projects that are primarily AI-generated, require relevant disclosure and prohibit AI-generated/edited project-page images. This repository does **not** claim publication eligibility from CI or in-game testing alone.
