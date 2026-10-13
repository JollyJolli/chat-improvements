package io.github.jollyjolli.chatimprovements.mixin;

import io.github.jollyjolli.chatimprovements.ChatImprovementsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.client.gui.screens.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Limit ONLY the unfocused HUD's height. Opening vanilla ChatScreen restores
 * the full user-configured focused height automatically, with no changes to
 * the Options object and no forced history reflow or global chat-scale changes.
 * This also preserves click/hover hitboxes and original rich components.
 */
@Mixin(ChatComponent.class)
public abstract class CompactChatMixin {
    @Inject(method = "getHeight()I", at = @At("RETURN"), cancellable = true)
    private void chatimprovements$compactHudHeight(CallbackInfoReturnable<Integer> cir) {
        var config = ChatImprovementsConfig.get();
        if (!config.compactHudEnabled ||
                Minecraft.getInstance().gui.screen() instanceof ChatScreen) {
            return;
        }
        // Vanilla message line height is approximately 10 px (depending on
        // chat line-spacing). This is an upper cap, not a forced minimum;
        // Minecraft's smaller default height or accessibility choice wins.
        int lines = Math.max(2, Math.min(10, config.compactHudLines));
        cir.setReturnValue(Math.min(cir.getReturnValue(), lines * 10));
    }
}
