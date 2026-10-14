package io.github.jollyjolli.chatimprovements;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

/**
 * Pure display-only mention indicator. Adds a distinct marker only when the
 * local player's exact username occurs at an alphanumeric/underscore boundary.
 * Never modifies packets, creates alerts, or saves message history.
 */
public final class MentionDecorator {
    private MentionDecorator() { }

    public static Component decorate(Component original) {
        if (original == null || !ChatImprovementsConfig.get().mentionHighlightsEnabled) {
            return original;
        }
        var profile = Minecraft.getInstance().getGameProfile();
        if (profile == null || profile.name() == null || profile.name().isBlank()) {
            return original;
        }
        String username = profile.name();
        if (!ChatNameRules.containsWholeName(original.getString(), username)) {
            return original;
        }
        Component marker = Component.literal("[@] ")
                .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD);
        return Component.empty().append(marker).append(original);
    }

}
