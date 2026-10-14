package io.github.jollyjolli.chatimprovements.mixin;

import io.github.jollyjolli.chatimprovements.ChatImprovementsConfig;
import io.github.jollyjolli.chatimprovements.UiPalette;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Changes the real ChatScreen backdrop and edit-field frame, instead of
 * drawing a duplicate HUD overlay. ChatScreen remains vanilla: it owns input,
 * history, autocomplete, links, scroll events and signed-chat interactions.
 */
@Mixin(ChatScreen.class)
public abstract class ChatExpandedScreenMixin {
    @Inject(method = "extractBackground(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V",
            at = @At("HEAD"), cancellable = true)
    private void chatimprovements$nativeExpandedFrame(GuiGraphicsExtractor graphics,
            int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (!ChatImprovementsConfig.get().chatSkinEnabled) return;

        int sw = graphics.guiWidth(), sh = graphics.guiHeight();
        if (sw < 150 || sh < 125) return; // defer to vanilla on tiny viewports

        // Align to the vanilla lower-left chat area without moving its input
        // component or widening/narrowing its trusted hitbox calculations.
        int left = 2;
        int right = Math.min(sw - 3, 365);
        int bottom = sh - 40;
        int top = Math.max(10, bottom - Math.min(194, sh - 104));
        int accent = UiPalette.accent();

        // Native glass window: the message rows themselves are restyled by
        // the two chat graphics-backend mixins, not by a HUD overlay.
        graphics.fillGradient(left, top, right, bottom,
                0xB61F3044, 0xC9101B2B);
        graphics.outline(left, top, right - left, bottom - top, 0xC0526981);
        graphics.fill(left, top, left + 3, bottom, accent);
        graphics.fill(left, top, right, top + 2, accent);

        // Real expanded-window header bar.
        int headerBottom = Math.min(bottom, top + 23);
        graphics.fill(left + 3, top + 3, right, headerBottom, 0xD31B2B3C);
        graphics.text(net.minecraft.client.Minecraft.getInstance().font,
                Component.translatable("chatimprovements.native.title"),
                left + 12, top + 8, UiPalette.TEXT, true);
        if (right - left >= 275) {
            Component state = Component.translatable("chatimprovements.native.subtitle");
            var font = net.minecraft.client.Minecraft.getInstance().font;
            graphics.text(font, state, right - font.width(state) - 10,
                    top + 8, accent, false);
        }

        // Restyle the actual vanilla input region's backdrop without moving
        // EditBox: its autocomplete, typing coordinates and tab focus persist.
        graphics.fillGradient(2, sh - 26, sw - 3, sh - 4,
                0xE01B2B3B, 0xE20F1B2B);
        graphics.outline(2, sh - 26, sw - 5, 22, 0xD0475D70);
        graphics.fill(2, sh - 26, 5, sh - 4, accent);

        ci.cancel();
    }
}
