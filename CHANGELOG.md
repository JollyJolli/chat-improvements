# Changelog

This file records verified branch milestones. **A release candidate is not a published version.**

## [Unreleased] — v0.4.0-rc.1 (PR #30; approval required)

### Added
- Native Dark Neon theme for both compact chat notification backgrounds and the expanded chat window/input frame.
- Compact unfocused HUD, configurable to show 3, 5 or 8 lines; full vanilla history when chat is focused.
- Quick Mention `[M]` for reliably identified senders, suggesting `@Username ` without automatically sending.
- Optional, session-only, content-free diagnostics for incoming player/system chat and head attribution.

### Retained
- Existing timestamps, copy `[C]`, mention `[@]`, known-profile heads, four color themes and `/ci` settings.

### Known limitations
- Heads may be unavailable on large/public servers that rewrite chat as server-system messages (#8). The optional text-format fallback is **unverified** and may be wrong.
- Mixed chat mods, custom window scaling, cross-server plugin chat and full compatibility require further runtime QA (#27).
- All seven primary beta scenarios passed in user testing, but the exact RC artifact still requires separate manual validation.

## v0.3.0 Dark Neon Beta — merged into main (2026-10-13; PR #20)

- Copy individual messages with `[C]`.
- Highlight local username mentions with `[@]`.
- Client-only `/ci` settings GUI, configurable C shortcut and four color accents.
- Native known-profile player-head sprites in supported worlds/servers.
- Configurable 12/24-hour timestamps and optional seconds.

## v0.1–v0.2 — early beta development

- Fabric 26.2 project layout and timestamp functionality.
- Fixed startup mixin selector error affecting 0.1.0-beta.1 (#6).
- Initial player-sprite and optional experimental system-chat fallback work (#4, #8).
