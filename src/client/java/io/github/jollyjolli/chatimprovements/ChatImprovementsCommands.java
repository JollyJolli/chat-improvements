package io.github.jollyjolli.chatimprovements;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;

/**
 * Both commands open the full GUI. No per-option chat commands are needed;
 * the long form remains an alias for users of the early betas.
 */
public final class ChatImprovementsCommands {
    private ChatImprovementsCommands() { }

    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommands.literal("ci").executes(context -> {
                SettingsScreenRequest.request();
                return 1;
            }));
            dispatcher.register(ClientCommands.literal("chatimprovements").executes(context -> {
                SettingsScreenRequest.request();
                return 1;
            }));
        });
    }
}
