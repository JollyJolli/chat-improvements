package io.github.jollyjolli.chatimprovements;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * Built using native, keyboard-narratable Minecraft widgets.
 * This integration preview additionally exposes the copy and mention modules.
 * Mod settings remain local and never change signed chat payloads.
 */
public final class ChatImprovementsSettingsScreen extends Screen {
    public ChatImprovementsSettingsScreen() {
        super(Component.literal("Chat Improvements Settings"));
    }

    @Override
    protected void init() {
        int buttonWidth = Math.min(240, this.width - 30);
        int x = (this.width - buttonWidth) / 2;
        int y = Math.max(16, this.height / 2 - 92);

        addRenderableWidget(Button.builder(timestampTitle(), button -> {
            var config = ChatImprovementsConfig.get();
            config.timestampsEnabled = !config.timestampsEnabled;
            ChatImprovementsConfig.save();
            button.setMessage(timestampTitle());
        }).bounds(x, y, buttonWidth, 20).build());

        addRenderableWidget(Button.builder(clockTitle(), button -> {
            var config = ChatImprovementsConfig.get();
            config.twentyFourHour = !config.twentyFourHour;
            ChatImprovementsConfig.save();
            button.setMessage(clockTitle());
        }).bounds(x, y + 28, buttonWidth, 20).build());

        addRenderableWidget(Button.builder(secondsTitle(), button -> {
            var config = ChatImprovementsConfig.get();
            config.showSeconds = !config.showSeconds;
            ChatImprovementsConfig.save();
            button.setMessage(secondsTitle());
        }).bounds(x, y + 56, buttonWidth, 20).build());

        addRenderableWidget(Button.builder(copyTitle(), button -> {
            var config = ChatImprovementsConfig.get();
            config.copyButtonsEnabled = !config.copyButtonsEnabled;
            ChatImprovementsConfig.save();
            button.setMessage(copyTitle());
        }).bounds(x, y + 84, buttonWidth, 20).build());

        addRenderableWidget(Button.builder(mentionTitle(), button -> {
            var config = ChatImprovementsConfig.get();
            config.mentionHighlightsEnabled = !config.mentionHighlightsEnabled;
            ChatImprovementsConfig.save();
            button.setMessage(mentionTitle());
        }).bounds(x, y + 112, buttonWidth, 20).build());

        addRenderableWidget(Button.builder(Component.literal("Done"), button -> onClose())
                .bounds(x, y + 146, buttonWidth, 20).build());
    }

    private static Component timestampTitle() {
        return Component.literal("Timestamps: "
                + (ChatImprovementsConfig.get().timestampsEnabled ? "ON" : "OFF"));
    }

    private static Component clockTitle() {
        return Component.literal("Time format: "
                + (ChatImprovementsConfig.get().twentyFourHour ? "24-hour" : "12-hour"));
    }

    private static Component secondsTitle() {
        return Component.literal("Show seconds: "
                + (ChatImprovementsConfig.get().showSeconds ? "ON" : "OFF"));
    }

    private static Component copyTitle() {
        return Component.literal("Copy buttons: "
                + (ChatImprovementsConfig.get().copyButtonsEnabled ? "ON" : "OFF"));
    }

    private static Component mentionTitle() {
        return Component.literal("Mention indicators: "
                + (ChatImprovementsConfig.get().mentionHighlightsEnabled ? "ON" : "OFF"));
    }

    @Override
    public void onClose() {
        if (this.minecraft != null) {
            this.minecraft.setScreenAndShow(null);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
