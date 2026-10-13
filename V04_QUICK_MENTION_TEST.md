# v0.4 Quick Mention beta — QA

Scope: this branch only; separate from Compact HUD and large-server diagnosis.

1. Run Fabric 26.2. On a small private server with other identified players, receive one of their ordinary player chat messages.
2. With /ci > CHAT > Quick mention ON, a small **[M]** click target should appear beside identified player chat. Hover for its tooltip.
3. Open chat with T, click [M] on their line. The editor should be filled with `@TheirRealUsername ` (do not press Enter); **no chat packet/command should be sent automatically**.
4. Verify the existing chat text/click events still work, including URLs, and [C] copies without [M] glyph.
5. Turn Quick mention OFF in /ci: new incoming messages must no longer have [M]. Restart game: setting remains.
6. Check plugin/system messages, fake player names and server announcements get NO [M]. Heads, timestamps and mention highlights still work.
7. Check long messages, line wrapping, scrollback and GUI-scale. Some vanilla UI environments may replace the current unsent draft when clicking suggestions; avoid clicking with an important draft.

IMPORTANT: This uses the native `ClickEvent.SuggestCommand` action, not `RunCommand`. Only the client can verify the true sender through `GameProfile` and the tab player record; spoofed server-formatted text never gets the action. No automatic sending.
