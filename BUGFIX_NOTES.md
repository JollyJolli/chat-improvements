# Timestamp beta crash fix — 0.1.0-beta.2

The 0.1.0-beta.1 artifact compiled, but its mixin targeted the Minecraft 1.21-style three-argument ChatComponent.addMessage method.

Minecraft Java 26.2 instead declares `ChatComponent.addMessage(Component, MessageSignature, GuiMessageSource, GuiMessageTag)`, with the last two classes in `net.minecraft.client.multiplayer.chat`.

This revision updates the precise JVM descriptor to the four-argument target.

Authoritative class signature reference:
https://aldak.netlify.app/javadoc/26.2.x/net/minecraft/client/gui/components/chatcomponent

**Important:** This correction was made from API class signatures. A successful GitHub build is NOT a runtime smoke test. If this build also crashes, attach the complete crash report in `.minecraft/crash-reports` or the last 150 lines of `.minecraft/logs/latest.log`, especially the first `Caused by:` or `Mixin apply failed` messages.

Please remove the older beta.1 jar before trying beta.2. Do not run both versions concurrently.
