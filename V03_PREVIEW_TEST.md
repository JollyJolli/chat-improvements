# Chat Improvements v0.3.0-preview.3 — in-game QA

This preview reintegrates player heads from draft PR #7 into the working v0.3 preview. It is NOT merged to main and large-server compatibility bug #8 is still unresolved.

1. **Installation:** remove ALL older Chat Improvements jars; install ONLY the non-sources jar from successful preview.3 CI build and Fabric API.
2. **Startup:** launch Fabric 26.2 (JDK 25) and enter a private world. No mixin crash. Test a line typed locally; the known player head should reappear.
3. **Other player:** join a vanilla/Paper server where another player's real signed messages are visible. Check heads, their skins, and hats.
4. **Menu:** press C in normal gameplay or type /ci. Check both tabs, Chat and Player Heads. The player heads toggle is ON by default; hats ON; experimental compatibility OFF.
5. **Toggles:** turn heads OFF then ON; test **new** messages only. Toggle outer hat layer. Verify tab switch and menu reset are correct.
6. **Large server:** by default, messages re-emitted by plugins as system text may have no heads. Optionally test the experimental compatibility toggle ON; the only supported format is exact `<KnownUsername> message`. **CAUTION: this mode is unverified and can show a spoofed/wrong head; do not infer chat identity or signatures from it.**
7. **Other features:** timestamps, copy [C], mentions [@], menu shortcut, settings persistence, link-click/hover interactions, long lines and chat scale still work.
8. **Stability:** on any crash, send a redacted crash report or log. Don't publish to Modrinth.

# Known limitations
- Server-plugin chat variants are not fully understood (#8); don't guess a username from rank or nickname display without proof.
- This preview requires real in-game QA before merging issues #4, #5, #9, #10, #16, #17.
- Neither signed messages nor outgoing packets are altered; skin sprites are display-only.
