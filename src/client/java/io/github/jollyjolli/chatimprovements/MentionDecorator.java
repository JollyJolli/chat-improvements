package io.github.jollyjolli.chatimprovements;

import java.util.Locale;
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
        if (!containsWholeName(original.getString(), username)) {
            return original;
        }
        Component marker = Component.literal("[@] ")
                .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD);
        return Component.empty().append(marker).append(original);
    }

    /**
     * Case-insensitive matching for Java Minecraft usernames; no fuzzy or
     * substring matches. Preceding/following ASCII letter, digit or underscore
     * disqualifies a mention. Match is done once per incoming message, not per frame.
     */
    static boolean containsWholeName(String message, String username) {
        if (message == null || username == null || username.isEmpty()) {
            return false;
        }
        String haystack = message.toLowerCase(Locale.ROOT);
        String needle = username.toLowerCase(Locale.ROOT);
        for (int pos = haystack.indexOf(needle); pos >= 0;
                pos = haystack.indexOf(needle, pos + 1)) {
            int end = pos + needle.length();
            boolean before = pos == 0 || !isNameChar(haystack.charAt(pos - 1));
            boolean after = end == haystack.length() || !isNameChar(haystack.charAt(end));
            if (before && after) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNameChar(char c) {
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '_';
    }
}
