# Changelog

## v0.4.0-rc.2 — candidate for testing (unreleased)

Built from the v0.4 native chat release candidate plus a quality-preview integration branch.

### Added or refined
- Localized settings, tooltips and theme labels in Brazilian Portuguese and German, alongside English and Spanish.
- Paginated settings controls on short GUI layouts and a compact diagnostics presentation.
- Safer local settings saves and validation of persisted theme/HUD line choices.
- Pure-Java name matching with JUnit regression tests, plus localization and JAR metadata validation in CI.
- Installable JAR checksum as part of GitHub Actions artifact.

### Included native chat features
- Dark Neon message cards in the small chat HUD and expanded chat, with four accent colors and visual toggle.
- Compact 3/5/8-line HUD while playing; expanded ChatScreen preserves normal history and text entry.
- Authenticated-only Quick Mention suggestion, local diagnostics and all earlier copy/timestamp/mention/head features.

### Not verified / limitations
- The **exact RC2 binary has not been manually validated in Minecraft**; automated passing build is not a substitute for runtime QA.
- Some large public servers do not provide trustworthy player identity to this client mod; heads may be missing (#8). Unverified opt-in fallback is off by default.
- Other chat mods, GUI-scale extremes, report/click interoperability and multilingual layout require targeted tests.
- Not approved for Modrinth publication or stable main merge. Review authorship and AI disclosures before any release.

## v0.4.0-rc.1 — previous release candidate (unreleased)

- Original user-tested seven primary native-v0.4 scenarios.
- Dark Neon native compact/expanded chat, Quick Mention, anonymous head diagnostics.
- Release readiness docs, initial metadata and artifact checksum.

## v0.3.0 Dark Neon Beta — main branch baseline

- Click-to-copy chat messages, username mention marker and settings GUI with four accent themes.
- Configurable timestamp prefixes and available signed-player head sprites.
- Default C settings shortcut and /ci /chatimprovements commands.

## v0.1–0.2 — early beta

- Fabric project setup, timestamp functionality, corrected startup Mixin selector.
- Initial known-sender player sprites and opt-in experimental system-chat fallback.
