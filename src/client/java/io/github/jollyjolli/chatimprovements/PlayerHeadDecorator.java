package io.github.jollyjolli.chatimprovements;

import com.mojang.authlib.GameProfile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    // Exact, anchored chat format only. Avoids matching announcers, text elsewhere
    // in the line, or arbitrary player mentions. No fuzzy names or nicknames.
    private static final Pattern PLAYER_CHAT = Pattern.compile(
            "^<([A-Za-z0-9_]{3,16})>\\s+\\S", Pattern.DOTALL);

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

        return withHead(original, info);
    }

    /**
     * Opt-in fallback for servers that deliver custom formatted player messages
     * as system chat. A displayed name is NOT cryptographically verified and can
     * be spoofed, even if it exists in the tab player list.
     */
    public static Component decorateUnverifiedSystemMessage(Component original) {
        var config = ChatImprovementsConfig.get();
        if (original == null || !config.playerHeadsEnabled || !config.systemHeadFallback) {
            return original;
        }
        ClientPacketListener connection = Minecraft.getInstance().getConnection();
        if (connection == null) {
            return original;
        }

        // Plain-text view is used only to detect a familiar chat prefix.
        // The original structured message is never rebuilt or flattened.
        String text = original.getString();
        Matcher match = PLAYER_CHAT.matcher(text);
        if (!match.find()) {
            return original;
        }

        String candidate = match.group(1);
        PlayerInfo info = connection.getPlayerInfo(candidate);
        if (info == null || !candidate.equals(info.getProfile().name())) {
            return original;
        }
        return withHead(original, info);
    }

    private static Component withHead(Component original, PlayerInfo info) {
        ResolvableProfile profile = ResolvableProfile.createResolved(info.getProfile());
        Component head = Component.object(
                new PlayerSprite(profile, ChatImprovementsConfig.get().showHatLayer));
        MutableComponent displayed = Component.empty();
        return displayed.append(head).append(Component.literal(" ")).append(original);
    }
}
