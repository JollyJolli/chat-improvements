package io.github.jollyjolli.chatimprovements.mixin;

import io.github.jollyjolli.chatimprovements.CopyMessageDecorator;
import io.github.jollyjolli.chatimprovements.TimestampDecorator;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * Local visual decoration only; never rewrites incoming signed message packets.
 * The 4-argument addMessage descriptor is specific to Minecraft Java 26.2.
 */
@Mixin(ChatComponent.class)
public abstract class ChatComponentMixin {
    @ModifyVariable(
            method = "addMessage(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/MessageSignature;Lnet/minecraft/client/multiplayer/chat/GuiMessageSource;Lnet/minecraft/client/multiplayer/chat/GuiMessageTag;)V",
            at = @At("HEAD"), argsOnly = true, ordinal = 0
    )
    private Component chatimprovements$decorateIncomingMessage(Component message) {
        return TimestampDecorator.decorate(CopyMessageDecorator.decorate(message));
    }
}
