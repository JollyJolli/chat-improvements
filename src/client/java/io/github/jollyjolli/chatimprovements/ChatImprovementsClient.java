package io.github.jollyjolli.chatimprovements;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Entry point for client-only chat improvements. No chat is changed yet. */
public final class ChatImprovementsClient implements ClientModInitializer {
    public static final String MOD_ID = "chatimprovements";
    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("Chat Improvements initialized. No chat modifications are enabled yet.");
    }
}
