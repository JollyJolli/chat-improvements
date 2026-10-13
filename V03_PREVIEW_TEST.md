# Chat Improvements v0.3.0-preview.2 — in-game QA

## New UX (issue #17)
- [ ] In a singleplayer world / Paper server, press **C** while playing: GUI opens.
- [ ] Open chat and type C normally. C must not open settings while the chat editor is active.
- [ ] In creative inventory or a sign editing screen, pressing C must not open settings.
- [ ] Use `/ci` from chat: GUI opens after chat closes.
- [ ] Legacy `/chatimprovements` opens the same menu (alias).
- [ ] Options > Controls > Key Binds > Miscellaneous > Open Chat Improvements can be reassigned.
- [ ] Try small GUI scales and change to 12/24 hour format, toggle timestamps, seconds, copy, mentions.
- [ ] Settings survive a game restart. Both Done and Escape exit cleanly.
- [ ] Reset Defaults requires **two clicks**. It must reset only the five mod settings, not the keybind or world.
- [ ] Menus show English or Spanish text depending on Minecraft language.

## Previous modules
- [ ] Timestamp prefix still displays correctly (v0.1 baseline).
- [ ] Clicking the `[C]` copy glyph in open chat copies the original message only, without timestamp/UI marker.
- [ ] With Mentions ON, a message including the local exact username shows [@] once; other messages don't.
- [ ] No broken links, text styling, scrolling, chat reporting or client crashes.

**Important:** This preview deliberately has no Player Heads. They remain in draft PR #7 and issue #8.
Only test a jar from a SUCCESSFUL build of the latest integration branch commit.
