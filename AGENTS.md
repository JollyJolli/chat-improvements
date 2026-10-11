# Codex collaboration instructions

You are implementing Chat Improvements: a lightweight CLIENT-ONLY Fabric mod for Minecraft Java 26.2. Read README.md and ROADMAP.md before editing.

## Rules

1. Prefer small, reviewable pull requests with a clear test plan. Never claim the code was tested unless a build or game test actually ran.
2. Minecraft target is 26.2, Java 25, Gradle 9.5.1, and non-obfuscated Fabric Loom (`net.fabricmc.fabric-loom`). Before updating dependencies check official Fabric sources.
3. The mod must work without installation on the server, including vanilla and Paper. Never require custom server packets or permissions for core functionality.
4. Keep signed chat content intact: no message forgery, deletion or sending chat on behalf of users. UI decoration and local storage are fine when user-controlled.
5. Preserve incoming text styles, click actions, hover events, translation keys and system messages. Test chat variants, team prefixes and nicknames; never infer that all formatted messages come from a player.
6. Do not copy code/assets from another mod without a reviewed compatible license and clear attribution. Link upstream repos when learning from them.
7. Privacy first: no telemetry, network analytics, or uploads. Do not persist chat history by default. If added, get explicit opt-in and provide delete controls.
8. Performance: no expensive per-frame operations, no blocking skin/network fetch on the render thread. Client mixins should have minimal scope and be well documented.
9. Add a setting to disable each feature and a fallback when another chat mod is detected; never promise universal compatibility without tests.
10. Track substantial AI contributions and human creative work transparently for Modrinth.

## First Codex assignment

Read CODEX_FIRST_TASK.md. Make a `chore/bootstrap-fabric-26.2` branch. Validate the Gradle configuration, supply a trusted Gradle wrapper, and run `./gradlew build`. Do not implement heads or timestamps in the bootstrap PR. Report results and remaining blockers.
