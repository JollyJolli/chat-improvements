package io.github.jollyjolli.chatimprovements;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * Built using native, keyboard-narratable Minecraft widgets.
 * This version exposes existing timestamp preferences; more modules will be
 * added to the same screen once their independent PRs are tested and merged.
 */
public final class ChatImprovementsSettingsScreen extends Screen {
    public ChatImprovementsSettingsScreen() {
        super(Component.literal("Chat Improvements Settings"));
    }

    @Override
    protected void init() {
        int buttonWidth = Math.min(240, this.width - 30);
        int x = (this.width - buttonWidth) / 2;
        int y = Math.max(24, this.height / 2 - 65);

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

        addRenderableWidget(Button.builder(Component.literal("Done"), button -> onClose())
                .bounds(x, y + 98, buttonWidth, 20).build());
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

    @Override
    public void onClose() {
        if (this.minecraft != null) {
            this.minecraft.setScreen(null);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
