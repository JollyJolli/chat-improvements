package io.github.jollyjolli.chatimprovements;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Lightweight native Minecraft settings UI. All changes save instantly,
 * and affect new incoming messages only. No server interaction or chat logging.
 */
public final class ChatImprovementsSettingsScreen extends Screen {
    private boolean resetArmed;
    private int left;
    private int top;
    private int layoutWidth;
    private boolean narrow;

    public ChatImprovementsSettingsScreen() {
        super(Component.translatable("gui.chatimprovements.title"));
    }

    @Override
    protected void init() {
        resetArmed = false;
        layoutWidth = Math.min(430, this.width - 24);
        left = (this.width - layoutWidth) / 2;
        narrow = this.width < 320;
        top = Math.max(12, (this.height - (narrow ? 210 : 178)) / 2);

        if (narrow) {
            int y = top + 39;
            addTimestampButton(left, y, layoutWidth);
            addClockButton(left, y + 24, layoutWidth);
            addSecondsButton(left, y + 48, layoutWidth);
            addCopyButton(left, y + 72, layoutWidth);
            addMentionsButton(left, y + 96, layoutWidth);
            addFooterButtons(top + 167);
        } else {
            int gap = 12;
            int colWidth = (layoutWidth - gap) / 2;
            int right = left + colWidth + gap;
            int y = top + 47;
            addTimestampButton(left, y, colWidth);
            addClockButton(left, y + 27, colWidth);
            addSecondsButton(left, y + 54, colWidth);
            addCopyButton(right, y, colWidth);
            addMentionsButton(right, y + 27, colWidth);
            addFooterButtons(top + 144);
        }
    }

    private void addTimestampButton(int x, int y, int width) {
        addRenderableWidget(Button.builder(toggleLabel("gui.chatimprovements.timestamps",
                ChatImprovementsConfig.get().timestampsEnabled), button -> {
            var config = ChatImprovementsConfig.get();
            config.timestampsEnabled = !config.timestampsEnabled;
            ChatImprovementsConfig.save();
            button.setMessage(toggleLabel("gui.chatimprovements.timestamps", config.timestampsEnabled));
        }).bounds(x, y, width, 20).build());
    }

    private void addClockButton(int x, int y, int width) {
        addRenderableWidget(Button.builder(clockLabel(), button -> {
            var config = ChatImprovementsConfig.get();
            config.twentyFourHour = !config.twentyFourHour;
            ChatImprovementsConfig.save();
            button.setMessage(clockLabel());
        }).bounds(x, y, width, 20).build());
    }

    private void addSecondsButton(int x, int y, int width) {
        addRenderableWidget(Button.builder(toggleLabel("gui.chatimprovements.seconds",
                ChatImprovementsConfig.get().showSeconds), button -> {
            var config = ChatImprovementsConfig.get();
            config.showSeconds = !config.showSeconds;
            ChatImprovementsConfig.save();
            button.setMessage(toggleLabel("gui.chatimprovements.seconds", config.showSeconds));
        }).bounds(x, y, width, 20).build());
    }

    private void addCopyButton(int x, int y, int width) {
        addRenderableWidget(Button.builder(toggleLabel("gui.chatimprovements.copy",
                ChatImprovementsConfig.get().copyButtonsEnabled), button -> {
            var config = ChatImprovementsConfig.get();
            config.copyButtonsEnabled = !config.copyButtonsEnabled;
            ChatImprovementsConfig.save();
            button.setMessage(toggleLabel("gui.chatimprovements.copy", config.copyButtonsEnabled));
        }).bounds(x, y, width, 20).build());
    }

    private void addMentionsButton(int x, int y, int width) {
        addRenderableWidget(Button.builder(toggleLabel("gui.chatimprovements.mentions",
                ChatImprovementsConfig.get().mentionHighlightsEnabled), button -> {
            var config = ChatImprovementsConfig.get();
            config.mentionHighlightsEnabled = !config.mentionHighlightsEnabled;
            ChatImprovementsConfig.save();
            button.setMessage(toggleLabel("gui.chatimprovements.mentions", config.mentionHighlightsEnabled));
        }).bounds(x, y, width, 20).build());
    }

    private void addFooterButtons(int y) {
        int gap = 12;
        int w = (layoutWidth - gap) / 2;
        addRenderableWidget(Button.builder(Component.translatable("gui.chatimprovements.reset"), button -> {
            if (!resetArmed) {
                resetArmed = true;
                button.setMessage(Component.translatable("gui.chatimprovements.confirm_reset"));
                return;
            }
            ChatImprovementsConfig.resetDefaults();
            // Rebuild to show the new settings on every toggle.
            if (this.minecraft != null) {
                this.minecraft.gui.setScreen(new ChatImprovementsSettingsScreen());
            }
        }).bounds(left, y, w, 20).build());

        addRenderableWidget(Button.builder(Component.translatable("gui.chatimprovements.done"),
                button -> onClose()).bounds(left + w + gap, y, w, 20).build());
    }

    private static Component clockLabel() {
        return Component.translatable("gui.chatimprovements.clock",
                ChatImprovementsConfig.get().twentyFourHour ? "24h" : "12h");
    }

    private static Component toggleLabel(String key, boolean enabled) {
        return Component.translatable(key,
                Component.translatable(enabled ? "gui.chatimprovements.on" : "gui.chatimprovements.off"));
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);
        drawCentered(graphics, Component.translatable("gui.chatimprovements.title"), top + 2, 0xFFFFFFFF);
        drawCentered(graphics, Component.translatable("gui.chatimprovements.subtitle"), top + 16, 0xFFAAAAAA);

        if (!narrow) {
            int gap = 12;
            int w = (layoutWidth - gap) / 2;
            drawAt(graphics, Component.translatable("gui.chatimprovements.display"), left + 3, top + 33, 0xFFE0D2A8);
            drawAt(graphics, Component.translatable("gui.chatimprovements.messages"), left + w + gap + 3, top + 33, 0xFFE0D2A8);
            drawCentered(graphics, Component.translatable("gui.chatimprovements.preview", previewTime()),
                    top + 117, 0xFFDDDDDD);
        } else {
            drawCentered(graphics, Component.translatable("gui.chatimprovements.preview", previewTime()),
                    top + 158, 0xFFDDDDDD);
        }
        // Keybindings are stored in vanilla Controls settings, not our JSON file.
        drawCentered(graphics, Component.translatable("gui.chatimprovements.footer"),
                narrow ? top + 194 : top + 172, 0xFF999999);
    }

    private String previewTime() {
        var config = ChatImprovementsConfig.get();
        if (!config.timestampsEnabled) return "";
        String format = config.twentyFourHour
                ? (config.showSeconds ? "HH:mm:ss" : "HH:mm")
                : (config.showSeconds ? "hh:mm:ss a" : "hh:mm a");
        return "[" + LocalTime.now().format(DateTimeFormatter.ofPattern(format)) + "]";
    }

    private void drawCentered(GuiGraphicsExtractor graphics, Component text, int y, int color) {
        graphics.text(this.font, text, (this.width - this.font.width(text)) / 2, y, color, true);
    }

    private void drawAt(GuiGraphicsExtractor graphics, Component text, int x, int y, int color) {
        graphics.text(this.font, text, x, y, color, true);
    }

    @Override
    public void onClose() {
        if (this.minecraft != null) {
            this.minecraft.gui.setScreen(null);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
