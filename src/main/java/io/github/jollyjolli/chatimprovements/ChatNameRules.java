package io.github.jollyjolli.chatimprovements;

import java.util.Locale;

/** Pure name matching utilities, independent of Minecraft client state. */
public final class ChatNameRules {
    private ChatNameRules() { }

    /** A vanilla Java username is 3–16 ASCII letters, digits or underscores. */
    public static boolean validPlayerName(String name) {
        if (name == null || name.length() < 3 || name.length() > 16) return false;
        for (int index = 0; index < name.length(); index++) {
            if (!isNameChar(name.charAt(index))) return false;
        }
        return true;
    }

    /** Find a local username at full Java-name boundaries, ignoring letter case. */
    public static boolean containsWholeName(String message, String username) {
        if (message == null || !validPlayerName(username)) return false;
        String haystack = message.toLowerCase(Locale.ROOT);
        String needle = username.toLowerCase(Locale.ROOT);
        for (int pos = haystack.indexOf(needle); pos >= 0;
                pos = haystack.indexOf(needle, pos + 1)) {
            int end = pos + needle.length();
            boolean before = pos == 0 || !isNameChar(haystack.charAt(pos - 1));
            boolean after = end == haystack.length() || !isNameChar(haystack.charAt(end));
            if (before && after) return true;
        }
        return false;
    }

    private static boolean isNameChar(char c) {
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')
                || (c >= 'A' && c <= 'Z') || c == '_';
    }
}
