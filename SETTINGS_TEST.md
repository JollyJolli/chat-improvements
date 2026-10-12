# Settings GUI beta — QA

Run `/chatimprovements settings` in a Fabric 26.2 singleplayer game or on a Paper server.

- [ ] Settings screen opens after the chat input is dismissed.
- [ ] Timestamp toggle updates the label and affects *new* messages only.
- [ ] Clock format 12h/24h and seconds toggles affect new chat lines.
- [ ] Preferences survive restarting Minecraft.
- [ ] Done / Escape close the GUI without sending extra network chat.
- [ ] Test window resize, GUI scale (small and large), keyboard Tab navigation and narration.
- [ ] No crash in Fabric 26.2 with only Fabric API and Chat Improvements installed.

This PR covers the GUI foundation and existing timestamp preferences, **not** the full expanded chat UI requested by issue #5. Keep issue #5 open.
