# Native Chat Window Redesign — v0.4.0-native-beta.1

User feedback: v0.4 preview.2 only drew a panel behind expanded chat; the small notification HUD remained vanilla. This branch replaces native *per-line backgrounds* for both paths and replaces ChatScreen background instead of using the old behind-chat HUD overlay.

## Four important differences
1. Mini HUD messages during gameplay have individual dark gradient cards, color rail, bottom separator; vanilla fading still applies.
2. Expanded chat rows are also actual cards drawn by the native ChatComponent foreground backend.
3. Pressing T shows a styled ChatScreen window/header, plus the real text-entry bar gets a Dark Neon frame.
4. The old static `ChatHudSkin` overlay registration has been removed, preventing duplicated windows.

## Manual QA gate
- [ ] Launch Fabric Minecraft 26.2 without Mixin startup errors. A green CI build alone does not prove these mixins apply at runtime.
- [ ] On a singleplayer and private server receive multiple messages with chat CLOSED; individual messages now have Dark Neon backgrounds and colored left rails, fading away normally.
- [ ] Open T: the chat editor gets a custom frame and the message backgrounds are themed. There must NOT be a duplicated panel behind the window.
- [ ] Use C > STYLE > Custom chat HUD OFF then ON. OFF should return vanilla backgrounds while timestamps/copy/heads still follow their own switches. Changing theme affects mini and expanded chat; test all 4 colors.
- [ ] Change GUI scale, chat width/height/scale, line spacing, text wrapping, scroll. Verify NO stray rectangles, text obscured by header, clickable URLs preserved and chat reports usable.
- [ ] Confirm Quick Mention [M] clicks only suggest input, Copy [C] copies the original message text, and player heads remain working on small/private servers.
- [ ] Compare a big public server; missing heads there is independently tracked in #8, no fix claimed.

## Limits
This is a first native reskin; chat storage, mouse hit testing, signing and autocomplete deliberately remain controlled by vanilla Minecraft. Input is visually reframed but NOT moved. The width is a bounded responsive preview and may need screenshot-based alignment improvements. Do not merge into main without actual game QA.
