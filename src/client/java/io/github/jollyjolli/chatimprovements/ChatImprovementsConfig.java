package io.github.jollyjolli.chatimprovements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.AtomicMoveNotSupportedException;
import net.fabricmc.loader.api.FabricLoader;

/** Local preferences only. Never stores messages, player names, or server addresses. */
public final class ChatImprovementsConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = FabricLoader.getInstance()
            .getConfigDir().resolve("chatimprovements.json");

    private static ChatImprovementsConfig current = new ChatImprovementsConfig();

    public boolean timestampsEnabled = true;
    public boolean twentyFourHour = true;
    public boolean showSeconds = false;
    public boolean copyButtonsEnabled = true;
    public boolean mentionHighlightsEnabled = false;
    /** Quick Mention is offered for known signed player senders only. */
    public boolean quickMentionEnabled = true;
    /** Uses known sender identity of signed player chat; no guesses required. */
    public boolean playerHeadsEnabled = true;
    public boolean showHatLayer = true;
    /** Unverified: may attach the wrong head to server-generated chat. */
    public boolean systemHeadFallback = false;
    /** GUI-only theme: 0 cyan, 1 violet, 2 amber, 3 lime. */
    public int uiAccentTheme = 0;
    /** Dark Neon overlay and message rails; OFF restores vanilla visual presentation. */
    public boolean chatSkinEnabled = true;
    /** Limit the unfocused HUD, never the expanded chat editor or vanilla options. */
    public boolean compactHudEnabled = true;
    /** Only 3, 5, and 8 are exposed by the UI. */
    public int compactHudLines = 5;

    public static ChatImprovementsConfig get() {
        return current;
    }

    public static void resetDefaults() {
        current = new ChatImprovementsConfig();
        save();
    }

    public static void load() {
        if (!Files.exists(PATH)) {
            save();
            return;
        }
        try {
            String json = Files.readString(PATH, StandardCharsets.UTF_8);
            ChatImprovementsConfig parsed = GSON.fromJson(json, ChatImprovementsConfig.class);
            if (parsed != null) {
                current = parsed;
                current.normalize();
            } else {
                ChatImprovementsClient.LOGGER.warn("Empty config at {}; using defaults.", PATH);
            }
        } catch (IOException | JsonParseException exception) {
            ChatImprovementsClient.LOGGER.warn("Could not read {}; using current settings.", PATH, exception);
        }
    }

    /** Ensure manually edited or future-version configs cannot select unsupported UI values. */
    private void normalize() {
        if (uiAccentTheme < 0 || uiAccentTheme > 3) {
            uiAccentTheme = 0;
        }
        if (compactHudLines != 3 && compactHudLines != 5 && compactHudLines != 8) {
            compactHudLines = 5;
        }
    }

    public static void save() {
        current.normalize();
        Path temporary = null;
        try {
            Files.createDirectories(PATH.getParent());
            // Never truncate the user's last valid config before the replacement is ready.
            temporary = Files.createTempFile(PATH.getParent(), "chatimprovements-", ".tmp");
            Files.writeString(temporary, GSON.toJson(current), StandardCharsets.UTF_8);
            try {
                Files.move(temporary, PATH, StandardCopyOption.ATOMIC_MOVE,
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (AtomicMoveNotSupportedException unsupported) {
                // Older filesystems may not support atomic moves.
                Files.move(temporary, PATH, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException | SecurityException exception) {
            ChatImprovementsClient.LOGGER.error("Could not save {}", PATH, exception);
        } finally {
            if (temporary != null) {
                try {
                    Files.deleteIfExists(temporary);
                } catch (IOException exception) {
                    ChatImprovementsClient.LOGGER.warn("Could not clean temporary config {}", temporary);
                }
            }
        }
    }
}
