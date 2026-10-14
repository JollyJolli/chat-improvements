# v0.4.0 RC — reproducible in-game QA matrix

Target **the exact RC build artifact**, Minecraft Java 26.2, Fabric Loader 0.19.5+, compatible Fabric API and Java 25. These are manual checks, **not automated or already verified** unless explicitly marked in `V04_RELEASE_CANDIDATE.md`.

## Baseline and setup

1. Record candidate commit SHA, GitHub Actions run URL, mod version, other installed mods and tested Minecraft/Fabric versions.
2. Remove other Chat Improvements JARs from `.minecraft/mods`. Back up `config/chatimprovements.json` before testing migration.
3. Repeat relevant checks in singleplayer, on one private/small server and (when allowed) on a large public server. Never share private chat content or account/server identifiers.

## Visual chat and interaction

| Test | Steps | Expected | Result |
| --- | --- | --- | --- |
| Launch | Launch with only Fabric API and this mod | No crash/mixin error | [ ] |
| Closed HUD | Receive multiple chat lines without opening T | Individual Dark Neon line backgrounds and natural fade | [ ] |
| Expanded chat | Press T while there is history | Native panel, header and input border render without duplicate overlay | [ ] |
| Themes | Use /ci > STYLE to select all four colors | Mini and expanded surfaces change to selected theme | [ ] |
| HUD switch | Toggle Custom chat HUD OFF then ON | Vanilla presentation restored when OFF; other features unchanged | [ ] |
| Compact layout | Try 3, 5, 8; press T then Escape | Limit only affects unfocused HUD; full history and scroll retained | [ ] |
| Chat width / scale | Try minimum and maximum settings and GUI scale 2, 3, 4 | No unreadable overlap, clipped header, or misaligned edit area | [ ] |
| Wrapping / Unicode | Receive long formatted text, emoji and non-ASCII names | No lost or misplaced lines; markup/styles intact | [ ] |
| Interaction | Open URL/hover item/chat report; scroll history | Native behavior still works and is clickable in expected locations | [ ] |
| Copy | Click [C] on a normal and player-head chat message | Clipboard contains expected *message text*, not UI button labels or sprite artifacts | [ ] |
| Quick Mention | Click [M] for a known actual player; inspect input | Suggested @Username; no message sent; no [M] on system messages | [ ] |
| Heads | Receive signed known-profile player chat | Correct known sender head renders where metadata exists | [ ] |
| Mention indicator | Enable mentions; test local name, substring, punctuation | Marker only on whole-username match | [ ] |
| Privacy diagnostics | Reset counts, compare server chat types | Only session-only counts copied; no chat/user/server text stored | [ ] |

## Upgrade, accessibility and compatibility

| Test | Steps | Expected | Result |
| --- | --- | --- | --- |
| Settings migration | Install v0.3 config then launch RC | All previous settings retained | [ ] |
| Persistence | Change 3 options, close/reopen client | Same options remain | [ ] |
| Keybind | Type C in chat / sign; bind settings to another key | Does not unexpectedly open settings; remapped shortcut works | [ ] |
| Compact screen | Use small Minecraft window / large GUI scale | Buttons, labels and diagnostics remain legible/usable | [ ] |
| Other chat mod | Test with a named supported mod **if claimed** | No crash or incompatible mixin behavior | [ ] |
| Public server heads | Compare to private server and note diagnostics | Record limitation honestly; #8 remains open until proven fixed | [ ] |

## Reporting and exit criteria

- Record **pass / fail / not tested**, game and mod versions, and exact RC SHA. Do not fill checkboxes speculatively.
- Bug: file a separate issue with reproduction steps, pertinent redacted log excerpt and cropped screenshots. Never expose tokens, private chat or unrelated player information.
- Block stable merge on startup crashes, broken signed-chat/reporting/click behavior or missing core features.
- Any deferred lower-priority issue must be documented and approved explicitly.
- Do not merge PR #30 or publish without final owner review and explicit approval.
