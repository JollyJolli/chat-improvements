package io.github.jollyjolli.chatimprovements;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

/**
 * Dark Neon presentation layer BEFORE vanilla CHAT.
 * Minecraft still owns every message, scroll line, clickable text style,
 * verified signature, reporting action, text input and accessibility event.
 *
 * Four visual improvements: glass backdrop, theme color rail, dedicated
 * expanded-chat heading, and (via ChatLineSkinDecorator) per-message markers.
 *
 * The overlay only draws while ChatScreen is visible, never while playing,
 * in inventories or while chat is hidden. OFF removes the overlay completely.
 */
public final class ChatHudSkin {
    private ChatHudSkin() { }

    public static void register() {
        HudElementRegistry.attachElementBefore(
                VanillaHudElements.CHAT,
                Identifier.fromNamespaceAndPath("chatimprovements", "chat_glass_skin"),
                ChatHudSkin::render
        );
    }

    private static void render(GuiGraphicsExtractor graphics, DeltaTracker delta) {
        Minecraft client = Minecraft.getInstance();
        if (!ChatImprovementsConfig.get().chatSkinEnabled
                || client.player == null || !(client.gui.screen() instanceof ChatScreen)) {
            return;
        }

        int screenW = graphics.guiWidth();
        int screenH = graphics.guiHeight();
        if (screenW < 195 || screenH < 155) {
            // At very large GUI scales, avoid covering the typing field.
            return;
        }

        int left = 3;
        int panelW = Math.min(348, screenW - 12);
        int bottom = screenH - 37;
        int visibleH = Math.min(187, Math.max(75, screenH - 100));
        int top = bottom - visibleH;
        int headingTop = Math.max(2, top - 25);
        int accent = UiPalette.accent();

        // (1) Deep glass-style panel below native rich text.
        graphics.fillGradient(left, headingTop, left + panelW, bottom + 3,
                0xCB192B3C, 0xDE08121F);
        graphics.outline(left, headingTop, panelW, bottom + 3 - headingTop, 0xCC526579);

        // (2) Color-matched left rail and top stripe.
        graphics.fill(left, headingTop, left + 3, bottom + 3, accent);
        graphics.fill(left, headingTop, left + panelW, headingTop + 2, accent);

        // (3) Small header, visually separate from the vanilla chat input.
        graphics.text(client.font, Component.translatable("chatimprovements.skin.header"),
                left + 13, headingTop + 7, UiPalette.TEXT, true);
        if (panelW >= 282) {
            Component state = Component.translatable("chatimprovements.skin.expanded");
            graphics.text(client.font, state,
                    left + panelW - client.font.width(state) - 10,
                    headingTop + 7, accent, false);
        }

        // The (4) line accents are added as structured Components by
        // ChatLineSkinDecorator. No manual message text rendering is done here.
    }
}
