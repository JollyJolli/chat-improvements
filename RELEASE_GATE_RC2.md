# Release gate — v0.4.0 RC2

**Status: PREPARED FOR VERIFICATION; NOT APPROVED OR PUBLISHED.**

## Candidate identity
- Proposed branch: `release/v0.4.0-rc2-prep`
- Version: `0.4.0-rc.2`
- This branch starts from `integration/v0.4-quality-preview`, so it includes the improved GUI, PT-BR/DE language support, configuration hardening and regression tests.
- Older `release/v0.4.0-rc1` / PR #30 remains a separate candidate with its own test history.
- No automatic promotion from this branch to stable `main`.

## Automated build gate
- [ ] GitHub Actions **on the final RC2 commit** all green (Java compilation, JUnit tests, Python localization validator/tests, JAR metadata verification).
- [ ] Artifact includes exactly one installable JAR and `SHA256SUMS.txt`.
- [ ] Confirm JAR has `fabric.mod.json`, project version and client-only metadata, compatible Minecraft declaration and four locale JSON files.
- [ ] Retain Actions URL, commit SHA, artifact ID and SHA-256 checksum in the QA report.

## Actual client QA gate
- [ ] Launch exact RC2 JAR on Minecraft Java 26.2 / Fabric Loader / Fabric API / Java 25 (not just CI).
- [ ] Confirm chat compact HUD & T expanded window, timestamps, all theme choices and OFF switch.
- [ ] Test copy with and without player heads; test Quick Mention *without auto sending*; mentions correctly match whole usernames.
- [ ] Test wrapping, long formatted/Unicode messages, fade, scrolling, native URL/hover/click, signed-chat/report functionality.
- [ ] Test all `/ci` tabs on normal, small and resized windows; GUI scale/chat scale/width extremes, compact pagination and Reset/Done.
- [ ] Switch Minecraft language to English, Spanish, Brazilian Portuguese and German and check labels fit.
- [ ] Verify upgrading a backed-up v0.3 config, saved settings after restart, and safe recovery from invalid local UI integers.
- [ ] Private/local multiplayer player heads succeed for reliably identified senders.
- [ ] Check anonymized diagnostics for big public-server chat behavior if permitted; keep #8 open and make limitation explicit.
- [ ] Record whether other chat mods are tested; do not imply unsupported compatibility.

## Publishing / authorship gate
- [ ] Human owner reviews code, licenses, test evidence and truthful claims.
- [ ] Independently establish **significant primary human creative contribution**. Modrinth does not permit projects that are primarily AI-generated; prompting and testing alone cannot be treated as proof.
- [ ] Correct "Contains AI-generated content" disclosure; no AI-generated or AI-edited page images.
- [ ] Gather genuine, redacted Minecraft screenshots using `SCREENSHOT_SHOT_LIST.md`.
- [ ] Review draft listing at `MODRINTH_PAGE_DRAFT.md`, all metadata and dependencies.
- [ ] Obtain explicit human authorization for moving approved code to main and submitting a public release.

**No file in this repository is a statement that these unchecked items have been completed.** Link screenshots, manual QA and review evidence when available; do not silently tick boxes.
