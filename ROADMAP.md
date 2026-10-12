# Chat Improvements roadmap

## v0.1 — Timestamp beta
- [x] Standard client-only Fabric source layout
- [x] Configurable chat timestamps, 12h/24h formats, seconds optional
- [x] CI green
- [x] Verified visible timestamps in a real multiplayer screenshot
- [ ] More testing: links, hover events, chat signatures, text wrapping, server switching

## v0.2 — Player heads
- [x] Native Minecraft player sprite for known signed-player profiles
- [x] Add commands to toggle heads and outer layer
- [x] Keep anonymous system chat separate from authenticated signed messages
- [x] Optional unverified system-chat fallback for exact `<Username> text` messages whose names exist in player list
- [ ] CI build validation for beta.2
- [ ] Test actual multiplayer signed chat and the optional system fallback
- [ ] Test username spoofing risk, Paper custom chat, nickname differences
- [ ] Test other chat mods, long messages, GUI scaling and duplicate heads

## v0.3 — Chat presentation
- [ ] Compact vanilla-like closed chat; expanded tools when opened
- [ ] Copy messages, controls GUI, accessible customization

Never present inferred system-chat identities as authenticated. No server-side installation or telemetry.
