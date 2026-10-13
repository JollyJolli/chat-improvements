package io.github.jollyjolli.chatimprovements;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Client-only entrypoint. The keybind is reassignable under Minecraft Controls. */
public final class ChatImprovementsClient implements ClientModInitializer {
    public static final String MOD_ID = "chatimprovements";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static KeyMapping openSettingsKey;

    @Override
    public void onInitializeClient() {
        ChatImprovementsConfig.load();
        ChatImprovementsCommands.register();
        ChatHudSkin.register();

        openSettingsKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.chatimprovements.open_settings",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                KeyMapping.Category.MISC
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            HeadChatDiagnostics.observeConnection(client.getConnection());
            // Consume presses even if a different screen is open, so a C typed
            // into chat, signs or search boxes cannot open our menu later.
            while (openSettingsKey.consumeClick()) {
                if (client.player != null && client.gui.screen() == null) {
                    SettingsScreenRequest.request();
                }
            }
            SettingsScreenRequest.openWhenRequested(client);
        });
        LOGGER.info("Chat Improvements initialized; press C or type /ci to configure.");
    }
}
