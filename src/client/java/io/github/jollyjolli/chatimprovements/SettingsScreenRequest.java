package io.github.jollyjolli.chatimprovements;

import net.minecraft.client.Minecraft;

/**
 * Opens on the next tick so ChatScreen's command-submission lifecycle cannot
 * immediately close the settings screen. No background polling or networking.
 */
final class SettingsScreenRequest {
    private static boolean requested;

    private SettingsScreenRequest() { }

    static void request() {
        requested = true;
    }

    static void openWhenRequested(Minecraft client) {
        if (!requested) {
            return;
        }
        requested = false;
        if (client.level != null) {
            client.setScreenAndShow(new ChatImprovementsSettingsScreen());
        }
    }
}
