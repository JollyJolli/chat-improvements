# Chat Improvements roadmap

## v0.1 — Timestamp beta
- [x] Standard client-only Fabric source layout
- [x] Configurable chat timestamps, 12h/24h formats, seconds optional
- [x] CI green
- [x] Verified visible timestamps in a real multiplayer screenshot
- [ ] More testing: links, hover events, chat signatures, text wrapping, server switching

## v0.2 — Player heads
- [x] Native Minecraft player sprite for known player profiles
- [x] Add commands to toggle heads and outer layer
- [x] Avoid pretending anonymous system messages have authenticated players
- [ ] CI build validation
- [ ] Test genuine player-signed chat in Fabric 26.2
- [ ] Test on Paper with and without plugin-modified chat
- [ ] Check duplicates with other head mods, wrapping, skin render, GUI scale

## v0.3 — Chat presentation
- [ ] Compact vanilla-like closed chat, expanded tools when opened
- [ ] Copy messages
- [ ] Accessible settings GUI

## Longer term
- [ ] Optional safe sender detection for server-plugin chat with explicit opt-in
- [ ] Search / saved messages with privacy controls
- [ ] Chat compatibility and release readiness

No network telemetry or automatic message history storage. Client only.
