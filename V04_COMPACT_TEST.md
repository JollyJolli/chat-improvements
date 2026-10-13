# v0.4 Compact / Expanded Chat beta — QA

Scope: this branch only. No code from Quick Mention or server diagnostics.

1. Confirm Fabric 26.2 starts without a mixin crash, and timestamps + heads continue working.
2. Press C, open **LAYOUT** tab. Toggle Compact HUD OFF/ON, select 3, 5, 8 visible lines.
3. When playing (chat closed), send enough messages to exceed the cap. HUD should show *up to* that many lines, subject to vanilla settings and font spacing.
4. Press **T**. Vanilla expanded chat must show your full history using your current Minecraft focused chat-height setting.
5. Close chat: compact HUD returns. Repeat after scrolling, resize and changing GUI scale; no message loss, clipping or duplicates.
6. Check setting persists after restart; when OFF, vanilla chat-height options should win for both views.
7. Test long wrapped messages, non-default line spacing, Unicode and high GUI scale, link clicks with chat open.
8. Test singleplayer and a small Paper/private server.

Important: "compact" currently caps unfocused **height** only, not width/scale. It does not animate or create an alternate chat editor. No new server capabilities are required. Do not merge before in-game QA.
