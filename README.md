# Chat Improvements

A lightweight **client-side Minecraft Java 26.2 Fabric mod** that makes the in-game chat easier to read and use. No server mod or plugin is required.

## Current status (October 2026)

**`main` is the v0.3 Dark Neon beta baseline.** It has not been published as an official Modrinth release. A separate **v0.4 native chat release candidate** is under manual review in [PR #30](https://github.com/JollyJolli/chat-improvements/pull/30). A distinct experimental [v0.4 quality preview](https://github.com/JollyJolli/chat-improvements/pull/43) tests new languages and reliability improvements, but **is not a stable release**.

### Features available in `main` (v0.3 beta)

- Configurable local timestamps (12h / 24h, optional seconds).
- `[C]` click action to copy individual message text.
- Optional `[@]` marker for whole-name mentions.
- Native player-head skin sprites where reliable sender metadata exists (tested in private/local contexts).
- Dark Neon settings menu with Cyan, Violet, Amber and Lime accent themes.
- Press **C** while playing or type **`/ci`** to open configuration. Rebind C via Minecraft Controls. The older `/chatimprovements` command remains an alias.
- Local settings stored at `config/chatimprovements.json` with no persistent chat history and no telemetry sent by this mod.

### Known limitation

**Player heads are not reliable on many large/public servers.** Some server plugins/proxies deliver formatted player-looking messages as system chat, which lacks authenticated sender identity. The experimental fallback is **off by default**, recognizes only a narrow pattern, and may display the wrong avatar. It must never be treated as cryptographically verified identity. Track this in [issue #8](https://github.com/JollyJolli/chat-improvements/issues/8).

## Install the beta from GitHub Actions

1. Install **Minecraft Java 26.2**, **Fabric Loader 0.19.5+**, and **Fabric API 0.161.0+26.2** (Java 25).
2. Open [GitHub Actions — Build](https://github.com/JollyJolli/chat-improvements/actions/workflows/build.yml) and select a **successful `main` run** for the v0.3 baseline. A PR, RC or preview artifact is not the same build.
3. Download its `chat-improvements-dev` artifact, extract the **installable JAR** (not the `-sources.jar`) and place it in `.minecraft/mods`.
4. Remove all older Chat Improvements JARs before launching, otherwise Fabric may complain about duplicate mod IDs.
5. In-game, press **C** or type `/ci`. For bugs, follow [SUPPORT.md](SUPPORT.md).

**Experimental v0.4:** If you specifically intend to test it, use the appropriate PR's exact commit and its own test checklist: [RC1](V03_PREVIEW_TEST.md) is **not** the testing checklist for the newer quality branch; use [v0.4 quality preview checklist](https://github.com/JollyJolli/chat-improvements/blob/integration/v0.4-quality-preview/V04_QUALITY_PREVIEW_TEST.md) instead. Do not install two versions simultaneously.

## Developing and reporting issues

- [Contribution guide](CONTRIBUTING.md) and [project roadmap](ROADMAP.md)
- [Bug reports / feature ideas](https://github.com/JollyJolli/chat-improvements/issues/new/choose)
- [Security and support notes](SUPPORT.md)

The source code is MIT-licensed. This project has received **substantial AI assistance**. Any public Modrinth publication must first meet its rules on meaningful human creative contribution, accurate AI disclosures and real (non-AI-generated) imagery. No eligibility or publication is claimed here.
