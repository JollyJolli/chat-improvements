package io.github.jollyjolli.chatimprovements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public static ChatImprovementsConfig get() {
        return current;
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
            } else {
                ChatImprovementsClient.LOGGER.warn("Empty config at {}; using defaults.", PATH);
            }
        } catch (IOException | JsonParseException exception) {
            // Preserve the invalid configuration so it can be recovered by the player.
            ChatImprovementsClient.LOGGER.warn("Could not read {}; using current settings.", PATH, exception);
        }
    }

    public static void save() {
        try {
            Files.createDirectories(PATH.getParent());
            Files.writeString(PATH, GSON.toJson(current), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            ChatImprovementsClient.LOGGER.error("Could not save {}", PATH, exception);
        }
    }
}
