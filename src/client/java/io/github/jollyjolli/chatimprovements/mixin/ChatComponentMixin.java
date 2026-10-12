package io.github.jollyjolli.chatimprovements.mixin;

import io.github.jollyjolli.chatimprovements.TimestampDecorator;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * Decorates only the text passed to the client HUD.
 * Incoming signed messages and outgoing packets are untouched.
 * The public three-argument addMessage overload is used for both player chat
 * and ordinary system chat; action-bar overlays are not affected.
 */
@Mixin(ChatComponent.class)
public abstract class ChatComponentMixin {
    @ModifyVariable(
            method = "addMessage(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/MessageSignature;Lnet/minecraft/client/GuiMessageTag;)V",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 0
    )
    private Component chatimprovements$decorateIncomingMessage(Component message) {
        return TimestampDecorator.decorate(message);
    }
}
