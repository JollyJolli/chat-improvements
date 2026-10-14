# Chat Improvements — roadmap and release status

_Last checked: 2026-10-14. This file describes the current project state, **not** a guarantee of broad compatibility._

## Released-to-main baseline: v0.3.0 Dark Neon Beta

- [x] v0.1: configurable local timestamps; initial Minecraft 26.2 startup crash fixed (#6)
- [x] v0.2: player heads for reliably identified senders in private/local worlds (#4)
- [x] v0.3: copy-message action [C] (#9), local mention indicator [@] (#10)
- [x] v0.3: native settings /ci + rebindable C, four Dark Neon themes (#17, #18)
- [x] v0.3: user-tested beta promoted to main in PR #20
- [ ] Wider formatting/hover/accessibility regression checks (#3)

## Current candidate: v0.4.0-rc.1 (PR #30 — NOT merged/released)

- [x] Unfocused compact HUD (3 / 5 / 8 lines) and full expanded chat (#5, PR #21)
- [x] Quick Mention [M] for reliable player senders; suggestion only, never auto-send (#11, PR #22)
- [x] Private session-only head diagnostics (#8 investigation, PR #23)
- [x] Native Dark Neon mini-HUD line cards and expanded chat window/input (#25, #28, PRs #26/#29)
- [x] Seven primary beta scenarios manually reported passing (#27)
- [x] Release-candidate build passing on exact PR #30 commit (GitHub Actions)
- [ ] Broader GUI scale/chat width, wrapping, scrolling, click/hover/report tests
- [ ] Real in-game test of the **exact RC artifact**, after upgrading from v0.3 and restarting
- [ ] Public-server versus private-server anonymous diagnostic comparison; report #8 accurately even if unresolved
- [ ] Human review, publishing compliance and explicit approval before moving RC to main
- [ ] Close #5, #11, and #27 only after their final acceptance criteria are actually met

See `V04_RELEASE_CANDIDATE.md` for concrete steps. The integration preview PR #24 is intentionally not the release merge path.

## Deferred: large public-server player heads (#8)

Large public servers can deliver player-looking lines as system chat without authenticated sender identities. An opt-in text-pattern fallback can be spoofed and is **not** a verified identity. No general fix is currently claimed. It should not block honest release of unrelated v0.4 improvements if documented, but #8 stays open.

## Future candidates (uncommitted scope)

- Chat search and/or optional privacy-first history features, only after a separate threat-model/privacy decision
- Internationalization, accessibility and screen scaling hardening
- Compatibility research with other chat mods and diverse proxy/plugin server formats
- Ongoing test automation and release/documentation quality

New features require a scoped issue, acceptance criteria, and review before implementation.
