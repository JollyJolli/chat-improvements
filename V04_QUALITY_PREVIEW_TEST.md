# Chat Improvements — Quality Preview 2

**Test build only. NOT stable, NOT Modrinth, NOT the v0.4 release candidate.**

- Branch: `integration/v0.4-quality-preview`
- Version: `0.4.0-quality-preview.2`
- Based on: `release/v0.4.0-rc1`; `main` remains on v0.3.
- Includes PR #35 (diagnostics compact layout), #39 (safe config writes), #40 (PT-BR + German and internationalized tooltips/colors), #42 (JUnit regression tests), and #48 (settings pagination on short screens).
- The foundational native chat and beta 7/7 tests come from v0.4 RC1. **These new combined changes have not been manually tested in Minecraft.**

## Install only this preview

1. Open the branch's successful GitHub Actions **Build** run and download `chat-improvements-dev`.
2. The ZIP contains one installable JAR and `SHA256SUMS.txt`; the JAR is **not** `-sources.jar`.
3. Remove other Chat Improvements JARs from `.minecraft/mods` to avoid duplicate mod-ID crashes.
4. Use Minecraft Java 26.2 + Fabric + compatible Fabric API + Java 25, and back up `config/chatimprovements.json`.
5. Record the exact build SHA, Fabric version, GUI scale and any other chat mods tested.

## QA cases

- [ ] Launch without crash, press C and `/ci`, check all tabs and four color themes.
- [ ] On a short Minecraft window, each settings tab should show a localized MORE page button to reach **all** options without overlapping Reset/Done; verify all pages and tab switching in four languages.
- [ ] Enable Spanish, Brazilian Portuguese, German, then English via Minecraft language settings. Check text, copy hover tooltip, accent color choices, HUD overlays, privacy warnings and truncation on a narrow GUI.
- [ ] Switch to compact window / larger GUI scale. Open HEADS > Chat diagnostics; text and Back/Copy/Reset controls should not overlap. Copy report must retain all six counters even if fewer lines are visible.
- [ ] Change old v0.3 preferences, upgrade with the backed-up config file, restart and confirm the values survive.
- [ ] With Minecraft fully closed, test intentionally invalid `uiAccentTheme` and `compactHudLines` numbers in a **copy of** your config; confirm the game loads and falls back to valid settings. Do not paste configuration or private information in public issues.
- [ ] Confirm new config saves cleanly after changes and no `chatimprovements-*.tmp` files remain after normal saves.
- [ ] Mention indicator highlights exact player names regardless of case but does not highlight substring matches.
- [ ] Quick Mention suggests `@Username ` only with known sender data, never automatically sends.
- [ ] Verify [C] copy action, player heads in private world, timestamps and the native compact/expanded Dark Neon chat still work.
- [ ] Repeat scrolling, URLs, hover events and Minecraft reporting interactions.
- [ ] On a large public server, #8 remains a **known unsolved limitation**; note only redacted, content-free diagnostic counts.

## Exit criteria

- [ ] GitHub Actions succeeded for the **combined** preview commit (Gradle Java/JUnit tests, localization tests, installable artifact + SHA256).
- [ ] All critical in-game tests passed; any failure gets a new issue and a narrow fix PR.
- [ ] Human review of language quality and code logic.
- [ ] Only then consider porting preview changes to a new RC. Never merge a test integration branch directly to stable `main` without owner approval.

This workflow and its content received substantial AI assistance.
