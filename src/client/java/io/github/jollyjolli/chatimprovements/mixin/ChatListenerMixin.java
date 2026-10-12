package io.github.jollyjolli.chatimprovements.mixin;

import com.mojang.authlib.GameProfile;
import io.github.jollyjolli.chatimprovements.PlayerHeadDecorator;
import java.time.Instant;
import net.minecraft.client.multiplayer.chat.ChatListener;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.PlayerChatMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

/**
 * Intercepts the local HUD call from verified player-chat handling.
 * Uses ChatListener's provided GameProfile instead of reading player names
 * from formatted message strings. Never edits a signed message or packet.
 *
 * 'require = 0' prevents client startup failure if another Minecraft patch
 * removes or changes this invocation; in that case heads won't be injected.
 */
@Mixin(ChatListener.class)
public abstract class ChatListenerMixin {
    @ModifyArgs(
            method = "showMessageToPlayer",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/components/ChatComponent;addPlayerMessage(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/MessageSignature;Lnet/minecraft/client/multiplayer/chat/GuiMessageTag;)V"
            ),
            require = 0
    )
    private void chatimprovements$decoratePlayerMessage(
            Args call,
            ChatType.Bound boundChatType,
            PlayerChatMessage signedMessage,
            Component decoratedMessage,
            GameProfile sender,
            boolean onlyShowSecure,
            Instant received
    ) {
        Component original = call.get(0);
        call.set(0, PlayerHeadDecorator.decorate(original, sender));
    }
}
