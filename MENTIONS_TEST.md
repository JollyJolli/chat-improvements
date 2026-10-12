# Mention Highlights beta — manual QA

Enable with `/chatimprovements mentions on` (off by default). When a new chat line includes the local Minecraft username as an exact whole-word match, an **[@]** gold marker appears to the left of that message. The original message component and its click/hover events are preserved.

- [ ] `Hello JollyJolli!` shows [@] when the local username is JollyJolli
- [ ] Case insensitive match with punctuation and `@JollyJolli`
- [ ] Names embedded in `xJollyJolli` or `JollyJolli_2` do **not** match
- [ ] No mention marker when feature is off; toggle and restart to confirm configuration persistence
- [ ] Verify timestamps remain unchanged and server click events continue to work
- [ ] Test a Paper 26.2 server with formatted chat and long wrapped messages
- [ ] Confirm no network sends, no saved chat history and no crash

Known limitation: this beta matches message *contents* and may include self-echoes or quotations that contain your username. It does not yet attempt sender-aware suppression or sound notifications.
Issue #10 remains open until in-game QA verifies the feature.
