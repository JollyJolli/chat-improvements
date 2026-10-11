# First Codex task — bootstrap the repository

Goal: establish a clean and buildable Fabric mod for Minecraft Java 26.2, without implementing chat changes yet.

1. Inspect `build.gradle`, `settings.gradle`, `gradle.properties`, `fabric.mod.json` and `src/client/java`.
2. Compare dependency choices with https://github.com/FabricMC/fabric-example-mod/tree/26.2 and the official Fabric 26.2 docs. If updated versions are required, explain each change in the PR.
3. Add and verify an official Gradle 9.5.1 wrapper (`gradlew`, `gradlew.bat`, `gradle/wrapper/gradle-wrapper.properties`, `gradle/wrapper/gradle-wrapper.jar`) using a trustworthy source/tool.
4. Run `./gradlew build` with JDK 25. Fix build errors only. Verify `fabric.mod.json` has environment `client` and no server entrypoint.
5. Check and fix GitHub Actions CI until green. Preserve the existing product documentation.
6. Create a PR titled `chore: bootstrap Fabric 26.2 development environment` including build output, files changed, and manual testing steps.

Do not copy functionality from Chat Heads, Chat Plus or Chat Patches. Do not add features, remote telemetry, or chat history persistence in this task. If full Minecraft dependencies cannot be downloaded, report the limitation and avoid claiming success.
