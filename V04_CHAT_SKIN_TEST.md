# v0.4 Dark Neon Chat HUD — 4 design improvements

This branch is based on the combined v0.4 beta and is intentionally separate from main.

## Visual features
1. Dark gradient "glass" panel behind the chat WHEN T opens ChatScreen.
2. Thin accent stripe and left rail matching cyan/violet/amber/lime menu theme.
3. "CHAT / LIVE" heading (with EXPANDED status on wider displays).
4. Each incoming message gets a small accent-colored vertical marker, with original rich Component appended unchanged.

These are rendering-layer changes, NOT a replacement for vanilla signed chat, reportability, scrolling, click events or text-input logic. No server-side mod.

## Test before merge
- Install only this branch's non-sources JAR with Fabric API on Minecraft 26.2.
- Open chat with T. The panel and LIVE heading appear behind normal interactive chat messages; typed input is not obscured.
- Close chat with Escape. Full panel disappears. The compact HUD remains clean and existing line caps still work.
- Open C > STYLE, turn Custom chat HUD OFF. New messages have no marker and the panel is gone. Re-enable and try all 4 themes.
- Verify timestamps, heads on private servers, Copy [C] (clipboard does NOT contain marker), Quick Mention [M], and mention highlight [@] still function.
- Test click links, report signed messages, scroll wheel, long wrapped lines, chat scale, GUI scale 2-4 and narrow window; check no overlapping panels/header.
- Restart game to check persistent Chat Skin selection. Test singleplayer, small private and large public servers.

Known limitations: background geometry is a fixed responsive panel sized for common scales; it does not yet position one custom background per message and can overlap on atypical chat-scale/height settings. Report a screenshot so it can be tuned. This version does not change missing player heads on large public servers (issue #8).
