# Development instructions: Chat Improvements

Minecraft Java 26.2, Java 25, Fabric 0.19.5+, non-obfuscated Loom 1.18-SNAPSHOT, Fabric API 0.161.0+26.2.

- Read README.md and ROADMAP.md before editing. Implement features in small reviewable PRs.
- This is a CLIENT-ONLY mod. No server packets, permissions, plugins, gameplay mutations or persistent chat logging.
- Never change outgoing chat, signatures or chat trust indicators. Add local HUD decoration only.
- Preserve incoming chat component formatting (including click/hover interactions).
- Do not assume that every server message is from a player; no avatar for system messages.
- Never block the Minecraft render thread with network or skin downloads.
- Keep configuration local and avoid saving chat histories unless the player explicitly opts in.
- A build success does not prove in-game compatibility: test against an actual Minecraft client.
- Declare substantial AI contributions and respect Modrinth publishing rules.
- No copied code/assets from other mods without license review and attribution.
- Next priority after timestamp beta: heads with strict sender identity handling.
