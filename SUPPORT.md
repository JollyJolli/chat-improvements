# Installation, support and troubleshooting

Chat Improvements is a **Fabric client-side mod** for Minecraft Java 26.2. The server does not need to install anything.

## Quick installation

1. Verify **Minecraft 26.2**, compatible Fabric Loader + Fabric API and Java 25.
2. Download `chat-improvements-dev` from a successful GitHub Actions **Build** run on the desired branch/commit. Unzip it first and select the mod JAR (not the `-sources.jar`).
3. Remove earlier versions of Chat Improvements from the mods folder. **Never have two Chat Improvements JARs installed at once.**
4. Install the new JAR in the corresponding Minecraft instance's `mods` directory.
   - Windows Launcher default: press **Win + R** and enter `%appdata%\.minecraft\mods`.
   - macOS Launcher default: `~/Library/Application Support/minecraft/mods`.
   - Linux Launcher default: `~/.minecraft/mods`.
   - Other launchers may use a separate instance folder. Always inspect that instance's path.
5. Launch the game and press **C** while playing or use `/ci`. If C conflicts with another mod, rebind the shortcut in Minecraft Controls.

## Quick fixes

**Minecraft crashes at startup**
- Confirm you have the correct Minecraft/Fabric/API/Java versions.
- Check that there is only one Chat Improvements JAR, and that it matches the instance. Do not install the source JAR.
- Try the exact successful Actions artifact with only this mod and Fabric API. If it still crashes, file a bug using a *redacted* crash trace and the exact build commit SHA.
- A green CI build confirms compilation, not necessarily Minecraft runtime compatibility.

**Heads show in singleplayer but not on a large server**
- This is a known, unfixed limitation: issue [#8](https://github.com/JollyJolli/chat-improvements/issues/8). Many public servers reformat player messages into server-system chat, which has no verified sender identity.
- Turning on the experimental fallback is **not a universal fix**. It may display another player's head for spoofed or inferred chat. It is off by default.
- v0.4 preview testers can open the privacy-safe diagnostic counter screen via `/ci` → HEADS → Chat diagnostics. The copied report has only anonymous session counts, no usernames, chat text, IPs or server identifiers.

**The settings menu does not open**
- Use `/ci` (the command is handled locally) or the configurable shortcut, by default C **while not typing**. Check Minecraft key bindings for conflicts.
- `/chatimprovements` is still an alias.

**Custom chat visuals overlap or a button is hidden**
- Record window size, Minecraft GUI scale, chat width/height/scale, and the exact branch/version.
- Try the default settings and remove other chat UI mods temporarily to isolate compatibility. Do not overwrite your config without backing it up.
- The v0.4 quality preview explicitly tests small-window diagnostics, wrapping, input frames and scrolling.

**Preferences do not persist**
- Look for `config/chatimprovements.json` in the active Minecraft instance. Ensure the game is allowed to write to this directory.
- Back up the JSON before manual changes. Never post private system paths, session IDs or unrelated files.

## Reporting a problem safely

Use the [bug report form](https://github.com/JollyJolli/chat-improvements/issues/new/choose) and include:
- What version/branch/commit is running.
- Minecraft, Loader, Fabric API and Java versions.
- Steps to reproduce; whether it happens in singleplayer, private or public servers.
- What you expected and what actually happened.
- Relevant **cropped screenshots** and the minimal **redacted** portion of `logs/latest.log` or the crash report.

**Do not paste private chat messages, full multiplayer logs, account/session tokens, unrelated usernames or private server addresses.** When reporting a possible security-sensitive bug, avoid posting exploit or private data in a public issue; seek a private contact with the repository maintainer instead.

## What is supported?

Only the Minecraft/Fabric combination listed in the README is currently targeted. Forge/NeoForge or other Minecraft versions are **not promised**. Other chat mods, large server proxy/plugins, internationalization and accessibility have limited testing. Check open issues and release QA notes before claiming compatibility.

No telemetry or chat-history storage is added by Chat Improvements. Its config stores only local preferences.
