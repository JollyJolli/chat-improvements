# v0.4.0-rc.1 — release readiness

## Implemented & user-tested (7/7 primary scenarios — 2026-10-14)
- [x] Minecraft Fabric 26.2 starts without crashing
- [x] Mini/unfocused chat shows custom message styling
- [x] Expanded chat opened by T and text-input area have Dark Neon styling
- [x] Four saved color choices and Custom Chat HUD toggle work
- [x] Compact/Expanded chat works without message loss
- [x] Quick Mention click suggests text without automatically sending
- [x] Player Heads in supported contexts, [C] Copy, timestamps and mention indicators still function

## Still required before stable v0.4
- [ ] Change vanilla chat width, height, text scale and GUI scale to extremes; test long wrapped messages, Unicode, fading, scrolling, links, click/hover and chat reporting
- [ ] Test interactions with another chat mod if compatibility is promised; otherwise mark unsupported/untested
- [ ] Run the anonymous head diagnostics on both a private server (working) and a public large server (heads missing); compare only content-free counts. The bug may remain unresolved in v0.4 if clearly documented (#8)
- [ ] Verify the prepared RC jar on real Minecraft client and after restart; ensure config migrates from v0.3 and all prior settings persist
- [ ] Confirm metadata/version/README/licenses and review source for public publishing requirements
- [ ] Obtain explicit release approval before merging the RC PR to main
- [ ] After stable merge, close user-validated feature issues as appropriate (#11 Quick Mention; follow-up compact scope #5) and keep #8 open until a genuine fix is proven

## Decision
This is a candidate to validate, **not** a published release. User's primary test results are evidence of a successful beta; they do not cover every Minecraft server, chat mod or GUI configuration. Do not equate GitHub build success with runtime safety.

### Release branch
`release/v0.4.0-rc1` based on user-tested `integration/v0.4-preview` commit `316635534e1983358bd25ddd79cd76e5dfe3d193`. Stable main intentionally untouched.
