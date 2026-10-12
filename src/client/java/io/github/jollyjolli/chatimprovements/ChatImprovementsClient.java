package io.github.jollyjolli.chatimprovements;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Client-only entrypoint. Never modifies outgoing or signed network messages. */
public final class ChatImprovementsClient implements ClientModInitializer {
    public static final String MOD_ID = "chatimprovements";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        ChatImprovementsConfig.load();
        ChatImprovementsCommands.register();
        ClientTickEvents.END_CLIENT_TICK.register(client -> SettingsScreenRequest.openWhenRequested(client));
        LOGGER.info("Chat Improvements initialized (timestamps {}).",
                ChatImprovementsConfig.get().timestampsEnabled ? "enabled" : "disabled");
    }
}
