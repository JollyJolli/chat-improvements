package io.github.jollyjolli.chatimprovements.mixin;

import io.github.jollyjolli.chatimprovements.TimestampDecorator;
import io.github.jollyjolli.chatimprovements.PlayerHeadDecorator;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * Adds a local HUD-only timestamp to incoming chat, without touching any network
 * payload or changing the signed message. Minecraft 26.2 uses a private
 * four-argument addMessage(Component, MessageSignature, GuiMessageSource,
 * GuiMessageTag); the older three-argument target no longer exists.
 */
@Mixin(ChatComponent.class)
public abstract class ChatComponentMixin {
    @ModifyVariable(
            method = "addMessage(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/MessageSignature;Lnet/minecraft/client/multiplayer/chat/GuiMessageSource;Lnet/minecraft/client/multiplayer/chat/GuiMessageTag;)V",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 0
    )
    private Component chatimprovements$decorateIncomingMessage(Component message) {
        return TimestampDecorator.decorate(message);
    }

    /**
     * Optional fallback for server-side chat plugins that re-emit player text
     * as system messages. The fallback is explicitly opt-in and unverified;
     * only the server-system path is touched, not player signed-chat handling.
     */
    @ModifyVariable(
            method = "addServerSystemMessage(Lnet/minecraft/network/chat/Component;)V",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 0
    )
    private Component chatimprovements$decorateServerSystemMessage(Component message) {
        return PlayerHeadDecorator.decorateUnverifiedSystemMessage(message);
    }
}
