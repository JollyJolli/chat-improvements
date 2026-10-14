package io.github.jollyjolli.chatimprovements;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;

/**
 * Adds one native chat-click [M] action only for a known GameProfile supplied
 * by ChatListener's authenticated player-chat path (never unverified system
 * text, plugins' nicknames, tab display text or regex guessed senders).
 *
 * SuggestCommand opens/fills the vanilla chat editor; it does not run a server
 * command or send any message. It may replace an unsent chat draft, as is
 * standard for vanilla "suggest command" click actions.
 */
public final class QuickMentionDecorator {
    private static final String LABEL = "[M] ";
    private QuickMentionDecorator() { }

    public static Component decorate(Component original, GameProfile sender) {
        if (original == null || sender == null || sender.id() == null
                || !ChatImprovementsConfig.get().quickMentionEnabled) {
            return original;
        }
        ClientPacketListener connection = Minecraft.getInstance().getConnection();
        if (connection == null) return original;

        PlayerInfo info = connection.getPlayerInfo(sender.id());
        if (info == null || !info.getProfile().id().equals(sender.id())) return original;

        String name = info.getProfile().name();
        if (name == null || !ChatNameRules.validPlayerName(name)) return original;

        String mention = "@" + name + " ";
        Component button = Component.literal(LABEL).withStyle(style -> style
                .withColor(UiPalette.accent())
                .withClickEvent(new ClickEvent.SuggestCommand(mention))
                .withHoverEvent(new HoverEvent.ShowText(
                        Component.translatable("chatimprovements.quickmention.tooltip", name))));

        return Component.empty().append(button).append(original);
    }

    /**
     * Strip only OUR first-prefix action from clipboard text. Ordinary chat
     * that happens to begin with "[M]" remains unchanged.
     */
    public static String copyableText(Component message) {
        if (message == null) return "";
        var parts = message.getSiblings();
        if (!parts.isEmpty()) {
            Component first = parts.get(0);
            if (LABEL.equals(first.getString())
                    && first.getStyle().getClickEvent() instanceof ClickEvent.SuggestCommand) {
                return message.getString().substring(LABEL.length());
            }
        }
        return message.getString();
    }
}
