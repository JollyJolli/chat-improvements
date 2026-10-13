package io.github.jollyjolli.chatimprovements.mixin;

import io.github.jollyjolli.chatimprovements.ChatSurfacePainter;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * BACKGROUND path = the small chat notification HUD visible during gameplay.
 * The native renderer still computes positions, scrolling and fading.
 */
@Mixin(targets = "net.minecraft.client.gui.components.ChatComponent$DrawingBackgroundGraphicsAccess")
public abstract class ChatBackgroundGraphicsMixin {
    @Shadow @Final private GuiGraphicsExtractor graphics;

    @Inject(method = "fill(IIIII)V", at = @At("HEAD"), cancellable = true)
    private void chatimprovements$paintSmallChat(int x0, int y0, int x1, int y1,
                                                 int color, CallbackInfo ci) {
        if (ChatSurfacePainter.paintLine(graphics, x0, y0, x1, y1, color)) {
            ci.cancel();
        }
    }
}
