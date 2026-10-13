package io.github.jollyjolli.chatimprovements;

import net.minecraft.client.multiplayer.ClientPacketListener;

/**
 * Ephemeral, content-free counters for diagnosing server chat delivery.
 * No names, messages, IPs, profiles or server identifiers are captured.
 * Counts reset whenever the client connection object changes.
 *
 * They help distinguish player-chat vs server-system paths; counts alone do
 * not prove the origin of a specific message or identify a speaker.
 */
public final class HeadChatDiagnostics {
    private static ClientPacketListener connection;
    private static int playerMessages, serverSystemMessages, displayedHeads,
            missingKnownProfiles, matchedFallbacks, rejectedFallbacks;

    private HeadChatDiagnostics() { }

    public static void observeConnection(ClientPacketListener current) {
        if (connection != current) {
            connection = current;
            reset();
        }
    }

    public static void reset() {
        playerMessages = 0;
        serverSystemMessages = 0;
        displayedHeads = 0;
        missingKnownProfiles = 0;
        matchedFallbacks = 0;
        rejectedFallbacks = 0;
    }

    public static void playerChatReceived() { ++playerMessages; }
    public static void serverSystemChatReceived() { ++serverSystemMessages; }
    public static void headDisplayed() { ++displayedHeads; }
    public static void missingKnownProfile() { ++missingKnownProfiles; }
    public static void fallbackMatched() { ++matchedFallbacks; }
    public static void fallbackNotMatched() { ++rejectedFallbacks; }

    public static String[] reportLines() {
        return new String[]{
                "Player-chat messages: " + playerMessages,
                "Server-system messages: " + serverSystemMessages,
                "Head sprites added: " + displayedHeads,
                "Known sender missing in tab: " + missingKnownProfiles,
                "Experimental fallback matches: " + matchedFallbacks,
                "Experimental fallback rejections: " + rejectedFallbacks
        };
    }

    public static String report() {
        return "Chat Improvements - local session diagnostics (no chat contents)\n"
                + String.join("\n", reportLines())
                + "\nNote: counts are not proof of chat identity.";
    }
}
