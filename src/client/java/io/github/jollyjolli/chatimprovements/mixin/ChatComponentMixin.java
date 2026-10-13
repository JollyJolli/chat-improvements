package io.github.jollyjolli.chatimprovements.mixin;

import io.github.jollyjolli.chatimprovements.CopyMessageDecorator;
import io.github.jollyjolli.chatimprovements.MentionDecorator;
import io.github.jollyjolli.chatimprovements.TimestampDecorator;
import io.github.jollyjolli.chatimprovements.PlayerHeadDecorator;
import io.github.jollyjolli.chatimprovements.HeadChatDiagnostics;
import net.minecraft.client.multiplayer.chat.GuiMessageTag;
import net.minecraft.network.chat.MessageSignature;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * One entry point for three purely local presentation decorators.
 * The original signed message and its metadata are never rewritten on the wire.
 */
@Mixin(ChatComponent.class)
public abstract class ChatComponentMixin {
    @ModifyVariable(
            method = "addMessage(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/MessageSignature;Lnet/minecraft/client/multiplayer/chat/GuiMessageSource;Lnet/minecraft/client/multiplayer/chat/GuiMessageTag;)V",
            at = @At("HEAD"), argsOnly = true, ordinal = 0
    )
    private Component chatimprovements$decorateIncomingMessage(Component message) {
        if (message == null) return null;
        Component display = CopyMessageDecorator.decorate(message);
        display = MentionDecorator.decorate(display);
        return TimestampDecorator.decorate(display);
    }
    /**
     * Unverified fallback for plugin-reformatted system chat (opt-in only).
     * Doesn't change or forge signed player chat packets.
     */
    @Inject(
            method = "addPlayerMessage(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/MessageSignature;Lnet/minecraft/client/multiplayer/chat/GuiMessageTag;)V",
            at = @At("HEAD"))
    private void chatimprovements$countSignedPlayer(Component message, MessageSignature signature,
            GuiMessageTag tag, CallbackInfo ci) {
        HeadChatDiagnostics.playerChatReceived();
    }

    @Inject(method = "addServerSystemMessage(Lnet/minecraft/network/chat/Component;)V",
            at = @At("HEAD"))
    private void chatimprovements$countServerChat(Component message, CallbackInfo ci) {
        HeadChatDiagnostics.serverSystemChatReceived();
    }

    @ModifyVariable(
            method = "addServerSystemMessage(Lnet/minecraft/network/chat/Component;)V",
            at = @At("HEAD"), argsOnly = true, ordinal = 0, require = 0
    )
    private Component chatimprovements$decorateServerSystemMessage(Component message) {
        return PlayerHeadDecorator.decorateUnverifiedSystemMessage(message);
    }
}
