# Current bootstrap / validation task

The standard Fabric source layout was merged in PR #1. This file replaces the earlier bootstrap task.

1. Run the Fabric 26.2 build with Java 25, Gradle 9.7.1, Loader 0.19.5 and Loom 1.18.
2. Review whether the chat timestamp mixin actually targets the 26.2 ChatComponent method. CI compilation alone is not a runtime guarantee.
3. Test commands in singleplayer and an unmodified Paper server; verify signed chat, links, and action-bar messages are not broken.
4. Report any crash logs or warnings as GitHub issues. Do not call the beta production-ready until in-game tests pass.
5. Player heads are scheduled for v0.2 and must not guess player identity from nicknames or arbitrary text.
