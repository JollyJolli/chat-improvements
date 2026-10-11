# Product plan — Chat Improvements

## Product principles

- The game view stays almost vanilla; expanded interactions appear only when the chat screen is open.
- Client-side only: no server-side components and no unfair interaction automation.
- Clear defaults, readable settings, accessibility and performance first.

## Milestone 0 — Build and integration

- [ ] Java 25 / Fabric 26.2 development environment
- [ ] Trusted Gradle wrapper committed
- [ ] CI build green
- [ ] Confirm mod initializes with only a log message
- [ ] Test local singleplayer and vanilla/Paper server connection

## Milestone 1 — Timestamps

- [ ] Optional local timestamp for received messages (off-by-default during early testing)
- [ ] Configurable 12/24h time format
- [ ] Distinguish system messages where practical
- [ ] Preserve rich text formatting and signed chat authenticity
- [ ] Verify messages with hover/click actions and line wrapping

## Milestone 2 — Player heads

- [ ] Resolve verified sender identities where available
- [ ] Fallback for console/system/custom server chat
- [ ] Avoid skin loading on the render thread
- [ ] Graceful layout for wrapped lines and chat scaling

## Milestone 3 — Expanded chat UI

- [ ] Search recent in-memory messages (configurable limit)
- [ ] Copy text without altering messages
- [ ] Local message bookmarks (opt-in persistence)
- [ ] Keyboard navigation and accessibility
- [ ] Per-server settings

## Out of scope for v0.1

- Social network features, server-side chat channels, player impersonation, read receipts, cloud backups, native translation services, invasive HUD replacement.

## Definition of done for a feature

Compiles, game-tested, has an off switch, documented, no client crash on vanilla/Paper, no silent changes to signed messages, and no known severe compatibility regressions.
