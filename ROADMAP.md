# Chat Improvements roadmap

## v0.1 — Timestamp beta
- [x] Standard client-only Fabric source layout
- [x] Configurable chat timestamps, 12h/24h formats, seconds optional
- [x] Client commands and locally saved preferences
- [ ] CI green for feature branch
- [ ] Validate singleplayer and Paper 26.2 in game
- [ ] Verify styling, links, hover events, signatures, long messages, and HUD resizing

## v0.2 — Player heads
- [ ] Investigate signed sender identity, skin manager APIs and profile lookups
- [ ] Render sender heads without false attribution to system/translated messages
- [ ] Handle offline skins and nicknames gracefully
- [ ] Avoid synchronous HTTP or skin fetching on render thread

## v0.3 — Chat presentation
- [ ] Compact vanilla-like closed chat
- [ ] Expanded tools when chat opened
- [ ] Optional copy actions
- [ ] Settings GUI with per-feature switches

## Longer-term
- [ ] Mentions and smart focus (opt-in)
- [ ] Local search and saved messages (privacy-first, opt-in)
- [ ] Compatibility checks with popular chat mods
- [ ] In-game validation and original creative contributions before Modrinth review

Keep implementations client-side, lightweight, reviewable and respectful of signed chat.
