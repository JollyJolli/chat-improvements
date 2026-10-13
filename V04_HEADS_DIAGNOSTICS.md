# v0.4 large-server Player Heads investigation

**This branch DOES NOT CLAIM TO FIX issue #8.** It adds a privacy-preserving diagnostic GUI on the existing player-heads settings page.

### Why
User confirmed heads work in singleplayer and small private servers, but not in large public servers. Server plugins may reformat or re-emit messages via `addServerSystemMessage` instead of signed `addPlayerMessage`. We do not know the actual cause, and raw display text cannot reliably identify senders.

### Safe comparative test
1. Remove any older Chat Improvements JAR. Install the diagnostic beta ONLY, with Fabric API on Minecraft 26.2.
2. In a small server where heads work, press C -> **HEADS** -> **Chat diagnostics**. Click Reset counters.
3. Ask a friend to send around five normal messages. Click **Copy report**. Paste in your notes.
4. Leave the server, join the large public server. The anonymous counters reset automatically when the connection changes. Repeat and copy the second report.
5. Send us only those **counts**, plus optionally a **redacted** screenshot of the chat format and player TAB list. Never submit access tokens, logs with private messages or unredacted conversations.
6. On a server where signed player chat is used, expect player-chat counter to rise. On a plugin system chat, expect server-system counter to rise. These are hypotheses to be verified, not facts.
7. Defaults: experimental fallback remains OFF. It is neither needed for diagnosis nor safe as proof of identity.
8. Check that clicking Copy report doesn't send any chat message, that screen Back works, heads are unchanged, and settings/timestamps/copy continue working.

### Counter semantics
- Player-chat messages: invocations of ChatComponent.addPlayerMessage
- Server-system messages: invocations of ChatComponent.addServerSystemMessage (includes plugins, alerts and announcements)
- Head sprites added: any native PlayerSprite added to incoming HUD components
- Missing profile: signed player path with no matching local TAB profile
- Fallback matches/rejections: only when optional experimental fallback is ON

All counters are ephemeral for the current connection, stored in memory only; no identities, message contents, server addresses or persistent logs. Counts alone cannot certify the origin of a particular line.

### Next steps after evidence
- Compare which counters increase between working/private and failing/public servers.
- If system messages dominate, explore explicitly **unverified** display decoration variants with controlled opt-in only; otherwise examine profile mapping and ChatListener dispatch.
- Avoid false identities and don't remove signed chat reportability.
- Leave issue #8 open.
