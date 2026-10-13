package io.github.jollyjolli.chatimprovements;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * A dark-neon, three-page settings dashboard. No chat commands required for
 * individual options. All settings take effect on subsequent messages.
 */
public final class ChatImprovementsSettingsScreen extends Screen {
    private final int page;
    private boolean resetArmed;
    private int left;
    private int top;
    private int layoutWidth;
    private boolean narrow;

    public ChatImprovementsSettingsScreen() {
        this(0);
    }

    private ChatImprovementsSettingsScreen(int page) {
        super(Component.translatable("gui.chatimprovements.title"));
        this.page = page;
    }

    @Override
    protected void init() {
        resetArmed = false;
        layoutWidth = Math.min(430, this.width - 24);
        left = (this.width - layoutWidth) / 2;
        narrow = this.width < 310;
        top = Math.max(4, (this.height - (narrow ? 242 : 200)) / 2);

        int gap = 10;
        int half = (layoutWidth - gap) / 2;
        int third = (layoutWidth - 2 * gap) / 3;
        tab("gui.chatimprovements.tab_chat", 0, left, third);
        tab("gui.chatimprovements.tab_heads", 1, left + third + gap, third);
        tab("gui.chatimprovements.tab_style", 2, left + (third + gap) * 2,
                layoutWidth - 2 * (third + gap));

        if (page == 1) initHeads(gap, half);
        else if (page == 2) initStyle(gap, half);
        else initChat(gap, half);
        addFooterButtons(narrow ? top + 200 : top + 157);
    }

    private void initChat(int gap, int half) {
        if (narrow) {
            int y = top + 60;
            addTimestampButton(left, y, layoutWidth);
            addClockButton(left, y + 24, layoutWidth);
            addSecondsButton(left, y + 48, layoutWidth);
            addCopyButton(left, y + 72, layoutWidth);
            addMentionsButton(left, y + 96, layoutWidth);
        } else {
            int right = left + half + gap;
            int y = top + 59;
            addTimestampButton(left, y, half);
            addClockButton(left, y + 27, half);
            addSecondsButton(left, y + 54, half);
            addCopyButton(right, y, half);
            addMentionsButton(right, y + 27, half);
        }
    }

    private void initHeads(int gap, int half) {
        if (narrow) {
            int y = top + 60;
            addHeadsButton(left, y, layoutWidth);
            addHatsButton(left, y + 24, layoutWidth);
            addCompatibilityButton(left, y + 48, layoutWidth);
        } else {
            int right = left + half + gap;
            int y = top + 59;
            addHeadsButton(left, y, half);
            addHatsButton(right, y, half);
            addCompatibilityButton(left, y + 27, layoutWidth);
        }
    }

    private void tab(String key, int destination, int x, int w) {
        int color = destination == page ? UiPalette.accent() : UiPalette.MUTED;
        addRenderableWidget(Button.builder(Component.translatable(key)
                .withStyle(style -> style.withColor(color)),
                button -> switchPage(destination)).bounds(x, top + 31, w, 20).build());
    }

    private void switchPage(int nextPage) {
        if (nextPage != page && this.minecraft != null) {
            this.minecraft.setScreenAndShow(new ChatImprovementsSettingsScreen(nextPage));
        }
    }

    private void initStyle(int gap, int half) {
        String[] names = {"CYAN", "VIOLET", "AMBER", "LIME"};
        for (int i = 0; i < names.length; i++) {
            final int choice = i;
            int width = narrow ? layoutWidth : half;
            int x = narrow ? left : left + (i % 2) * (half + gap);
            int y = top + 60 + (narrow ? i : i / 2) * (narrow ? 24 : 27);
            int color = UiPalette.accent(i);
            String selected = UiPalette.index() == i ? "* " : "";
            addRenderableWidget(Button.builder(Component.literal(selected + names[i])
                    .withStyle(style -> style.withColor(color)), button -> {
                ChatImprovementsConfig.get().uiAccentTheme = choice;
                ChatImprovementsConfig.save();
                if (this.minecraft != null) {
                    this.minecraft.setScreenAndShow(new ChatImprovementsSettingsScreen(page));
                }
            }).bounds(x, y, width, 20).build());
        }
    }

    private void addTimestampButton(int x, int y, int w) {
        addRenderableWidget(Button.builder(toggleLabel("gui.chatimprovements.timestamps",
                ChatImprovementsConfig.get().timestampsEnabled), button -> {
            var c = ChatImprovementsConfig.get();
            c.timestampsEnabled = !c.timestampsEnabled;
            ChatImprovementsConfig.save();
            button.setMessage(toggleLabel("gui.chatimprovements.timestamps", c.timestampsEnabled));
        }).bounds(x, y, w, 20).build());
    }

    private void addClockButton(int x, int y, int w) {
        addRenderableWidget(Button.builder(clockLabel(), button -> {
            var c = ChatImprovementsConfig.get();
            c.twentyFourHour = !c.twentyFourHour;
            ChatImprovementsConfig.save();
            button.setMessage(clockLabel());
        }).bounds(x, y, w, 20).build());
    }

    private void addSecondsButton(int x, int y, int w) {
        addRenderableWidget(Button.builder(toggleLabel("gui.chatimprovements.seconds",
                ChatImprovementsConfig.get().showSeconds), button -> {
            var c = ChatImprovementsConfig.get();
            c.showSeconds = !c.showSeconds;
            ChatImprovementsConfig.save();
            button.setMessage(toggleLabel("gui.chatimprovements.seconds", c.showSeconds));
        }).bounds(x, y, w, 20).build());
    }

    private void addCopyButton(int x, int y, int w) {
        addRenderableWidget(Button.builder(toggleLabel("gui.chatimprovements.copy",
                ChatImprovementsConfig.get().copyButtonsEnabled), button -> {
            var c = ChatImprovementsConfig.get();
            c.copyButtonsEnabled = !c.copyButtonsEnabled;
            ChatImprovementsConfig.save();
            button.setMessage(toggleLabel("gui.chatimprovements.copy", c.copyButtonsEnabled));
        }).bounds(x, y, w, 20).build());
    }

    private void addMentionsButton(int x, int y, int w) {
        addRenderableWidget(Button.builder(toggleLabel("gui.chatimprovements.mentions",
                ChatImprovementsConfig.get().mentionHighlightsEnabled), button -> {
            var c = ChatImprovementsConfig.get();
            c.mentionHighlightsEnabled = !c.mentionHighlightsEnabled;
            ChatImprovementsConfig.save();
            button.setMessage(toggleLabel("gui.chatimprovements.mentions", c.mentionHighlightsEnabled));
        }).bounds(x, y, w, 20).build());
    }

    private void addHeadsButton(int x, int y, int w) {
        addRenderableWidget(Button.builder(toggleLabel("gui.chatimprovements.heads",
                ChatImprovementsConfig.get().playerHeadsEnabled), button -> {
            var c = ChatImprovementsConfig.get();
            c.playerHeadsEnabled = !c.playerHeadsEnabled;
            ChatImprovementsConfig.save();
            button.setMessage(toggleLabel("gui.chatimprovements.heads", c.playerHeadsEnabled));
        }).bounds(x, y, w, 20).build());
    }

    private void addHatsButton(int x, int y, int w) {
        addRenderableWidget(Button.builder(toggleLabel("gui.chatimprovements.hats",
                ChatImprovementsConfig.get().showHatLayer), button -> {
            var c = ChatImprovementsConfig.get();
            c.showHatLayer = !c.showHatLayer;
            ChatImprovementsConfig.save();
            button.setMessage(toggleLabel("gui.chatimprovements.hats", c.showHatLayer));
        }).bounds(x, y, w, 20).build());
    }

    private void addCompatibilityButton(int x, int y, int w) {
        addRenderableWidget(Button.builder(toggleLabel("gui.chatimprovements.compatibility",
                ChatImprovementsConfig.get().systemHeadFallback), button -> {
            var c = ChatImprovementsConfig.get();
            c.systemHeadFallback = !c.systemHeadFallback;
            ChatImprovementsConfig.save();
            button.setMessage(toggleLabel("gui.chatimprovements.compatibility", c.systemHeadFallback));
        }).bounds(x, y, w, 20).build());
    }

    private void addFooterButtons(int y) {
        int gap = 10;
        int w = (layoutWidth - gap) / 2;
        addRenderableWidget(Button.builder(Component.translatable("gui.chatimprovements.reset"), button -> {
            if (!resetArmed) {
                resetArmed = true;
                button.setMessage(Component.translatable("gui.chatimprovements.confirm_reset"));
                return;
            }
            ChatImprovementsConfig.resetDefaults();
            if (this.minecraft != null) {
                this.minecraft.setScreenAndShow(new ChatImprovementsSettingsScreen(page));
            }
        }).bounds(left, y, w, 20).build());

        addRenderableWidget(Button.builder(Component.translatable("gui.chatimprovements.done"),
                button -> onClose()).bounds(left + w + gap, y, w, 20).build());
    }

    private static Component toggleLabel(String key, boolean enabled) {
        return Component.translatable(key,
                Component.translatable(enabled ? "gui.chatimprovements.on" : "gui.chatimprovements.off"))
                .withStyle(style -> style.withColor(enabled ? UiPalette.accent() : UiPalette.MUTED));
    }

    private static Component clockLabel() {
        return Component.translatable("gui.chatimprovements.clock",
                ChatImprovementsConfig.get().twentyFourHour ? "24h" : "12h")
                .withStyle(style -> style.withColor(UiPalette.accent()));
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);
        drawCentered(graphics, Component.translatable("gui.chatimprovements.title"), top + 2, 0xFFFFFFFF);
        drawCentered(graphics, Component.translatable("gui.chatimprovements.subtitle"), top + 16, 0xFFAAAAAA);

        if (headsPage) {
            drawCentered(graphics, Component.translatable("gui.chatimprovements.heads_note"),
                    narrow ? top + 145 : top + 111, 0xFFDDDDDD);
            drawCentered(graphics, Component.translatable("gui.chatimprovements.fallback_warning"),
                    narrow ? top + 158 : top + 128, 0xFFFFB46A);
        } else {
            drawCentered(graphics, Component.translatable("gui.chatimprovements.preview", previewTime()),
                    narrow ? top + 182 : top + 140, 0xFFDDDDDD);
        }
        drawCentered(graphics, Component.translatable("gui.chatimprovements.footer"),
                narrow ? top + 226 : top + 184, 0xFF999999);
    }

    private String previewTime() {
        var c = ChatImprovementsConfig.get();
        if (!c.timestampsEnabled) return "";
        String format = c.twentyFourHour
                ? (c.showSeconds ? "HH:mm:ss" : "HH:mm")
                : (c.showSeconds ? "hh:mm:ss a" : "hh:mm a");
        return "[" + LocalTime.now().format(DateTimeFormatter.ofPattern(format)) + "]";
    }

    private void drawCentered(GuiGraphicsExtractor graphics, Component text, int y, int color) {
        graphics.text(this.font, text, (this.width - this.font.width(text)) / 2, y, color, true);
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
