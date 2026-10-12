# Copy Messages beta — manual QA

Only a GitHub build is verified automatically. Minecraft 26.2 runtime and rendering must be tested in game.

- [ ] With the chat open, click the **[C]** prefix on a message. Paste into a text editor. Only the original message text, *not* timestamp or copy glyph, should appear.
- [ ] Verify the original message's URL and /tell clickable components still work when clicking the original text (not its [C]).
- [ ] Verify hovering [C] shows "Copy message".
- [ ] Test timestamps enabled/disabled and /chatimprovements copy off/on. Restart Minecraft; setting must persist.
- [ ] Test server Paper 26.2 and integrated singleplayer chat, message wrapping, scrolling, GUI scaling, very long/unicode chat.
- [ ] Check no crash, no text sent to server, no logs of copied content.

This beta adds a clickable prefix to *new* incoming chat messages (including system messages) and intentionally does not modify any historical lines.
Do not merge or close issue #9 until manual QA is done.