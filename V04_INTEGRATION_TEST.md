# Chat Improvements v0.4-preview.2 — integrated manual testing

This preview combines three independently developed branches and is intentionally **not merged into main**.

## Install
Remove old Chat Improvements jars; install the NON-sources jar from the latest **successful** Actions build on integration/v0.4-preview. Use Fabric and Fabric API for Java Minecraft 26.2.

## Compact / expanded chat (PR #21)
- Press C, open LAYOUT. Compact mode ON by default, choose 3 / 5 / 8 lines; send enough chat lines to exceed the cap.
- HUD should show the limited number of lines while playing; pressing T restores vanilla focused chat height and scrolling/history.
- Disable compact; check vanilla unfocused height works as configured in Minecraft. Resize game/change chat scale, GUI scale and line spacing; check no crash or wrapping regression.

## Quick mention (PR #22)
- In a private server with known player identities, ordinary signed player chat displays a new [M] action; with chat open, click [M] to **suggest** @TheirUsername in input, not send it.
- Confirm no guessed [M] on server-system messages; no clicking player name automatically sends commands; [C] still copies without [M].
- /ci CHAT tab toggles Quick mention; default ON. Vanilla may overwrite the draft, so test with an empty editor.
- Check signed chat skins, timestamps and other interactions remain intact.

## Public-server head diagnosis (PR #23)
- /ci -> HEADS -> Chat diagnostics (button), reset session counters, send around 5 messages on a working private server, copy anonymized report.
- Join the large server with missing heads; reset should happen automatically on connection change, send 5 messages and copy the second report.
- Report counts only (not screenshots of private messages or server names unless redacted). Counters include player vs server-system chat; no stored messages, usernames or addresses.
- Heads still expected to work on small private servers; NO claim of big-public-server fix.

## New custom chat HUD redesign (PR #26, issue #25)
- Open `/ci` -> STYLE -> Custom chat HUD ON/OFF. ON shows the Dark Neon HUD; OFF restores default chat display layout (apart from independent features).
- Press T: dark glass panel behind expanded chat, matching accent-colored border/rail, and CHAT/LIVE heading appear. Esc closes it.
- New incoming messages get one colored vertical marker each; copied clipboard text via [C] must **exclude** it.
- Try all four color themes, change GUI scale (2/3/4), Minecraft chat-scale/height/width, scrolling, wrapped messages and chat reports.
- Check that the overlay never obscures the text input or breaks link clicks. Send screenshots of any overlap; this is a presentation beta, not a complete vanilla chat-engine replacement.

## Regression checklist
C keybind, /ci, four Dark Neon colors, reset defaults, timestamps, copy button, mention highlights, heads, second skin layer, and original click/hover formatting. Test at least small private server and large public server.

**Do not merge feature PRs or close #5, #8, #11 until user has tested this combined preview.**
