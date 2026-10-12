package io.github.jollyjolli.chatimprovements;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.objects.PlayerSprite;
import net.minecraft.world.item.component.ResolvableProfile;

/**
 * Builds a vanilla 26.2 player-sprite component for an authenticated
 * in-game sender. Never attempts to parse arbitrary text or nicknames.
 */
public final class PlayerHeadDecorator {
    private PlayerHeadDecorator() { }

    public static Component decorate(Component original, GameProfile sender) {
        if (original == null || sender == null || sender.id() == null
                || !ChatImprovementsConfig.get().playerHeadsEnabled) {
            return original;
        }

        ClientPacketListener connection = Minecraft.getInstance().getConnection();
        if (connection == null) {
            return original;
        }
        PlayerInfo info = connection.getPlayerInfo(sender.id());
        if (info == null) {
            // Don't guess the identity or show a random/default head.
            return original;
        }

        ResolvableProfile profile = ResolvableProfile.createResolved(info.getProfile());
        Component head = Component.object(
                new PlayerSprite(profile, ChatImprovementsConfig.get().showHatLayer));

        // Keep the original component tree intact; no flattening or rewriting.
        MutableComponent displayed = Component.empty();
        return displayed.append(head).append(Component.literal(" ")).append(original);
    }
}
