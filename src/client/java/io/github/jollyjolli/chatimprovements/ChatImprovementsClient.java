package io.github.jollyjolli.chatimprovements;

import net.fabricmc.api.ClientModInitializer;
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
        LOGGER.info("Chat Improvements initialized (timestamps: {}, player heads: {}).",
                ChatImprovementsConfig.get().timestampsEnabled,
                ChatImprovementsConfig.get().playerHeadsEnabled);
    }
}
