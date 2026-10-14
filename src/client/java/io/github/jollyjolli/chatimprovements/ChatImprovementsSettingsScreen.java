package io.github.jollyjolli.chatimprovements;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/** Dark Neon dashboard with responsive layout and four locally saved accent themes. */
public final class ChatImprovementsSettingsScreen extends Screen {
    private final int page;
    private int x, y, w, h, rowStep;
    private boolean compact, resetArmed;

    public ChatImprovementsSettingsScreen() { this(0); }

    private ChatImprovementsSettingsScreen(int page) {
        super(Component.translatable("gui.chatimprovements.title"));
        this.page = page;
    }

    @Override
    protected void init() {
        w = Math.min(480, width - 16);
        h = Math.min(252, height - 12);
        x = (width - w) / 2;
        y = (height - h) / 2;
        compact = w < 360 || h < 228;
        rowStep = compact ? Math.max(16, Math.min(22, (h - 102) / 5)) : 28;
        resetArmed = false;

        int inner = x + 11, gap = 6;
        int tabW = (w - 22 - 3 * gap) / 4;
        tab("gui.chatimprovements.tab_chat", 0, inner, tabW);
        tab("gui.chatimprovements.tab_heads", 1, inner + tabW + gap, tabW);
        tab("gui.chatimprovements.tab_style", 2, inner + 2 * (tabW + gap), tabW);
        tab("gui.chatimprovements.tab_layout", 3, inner + 3 * (tabW + gap), w - 22 - 3 * (tabW + gap));

        if (page == 3) buildLayout();
        else if (page == 2) buildColors();
        else {
            int[] ids = page == 1 ? new int[] {5, 6, 7} : new int[] {0, 1, 2, 3, 4, 8};
            for (int i = 0; i < ids.length; i++) {
                int col = compact ? 0 : i % 2;
                int row = compact ? i : i / 2;
                int controlW = compact ? w - 38 : (w - 48) / 2;
                int bx = x + 19 + col * (controlW + 10);
                int by = y + (compact ? 65 : 81) + row * rowStep;
                int id = ids[i];
                addRenderableWidget(Button.builder(label(id), button -> {
                    flip(id);
                    refresh();
                }).bounds(bx, by, controlW, compact ? 18 : 23).build());
            }
        }

        if (page == 1) {
            addRenderableWidget(Button.builder(Component.translatable(
                    "gui.chatimprovements.diagnostics"), button -> {
                if (this.minecraft != null)
                    this.minecraft.setScreenAndShow(new HeadDiagnosticsScreen());
            }).bounds(x + 19, y + (compact ? 144 : 159), w - 38, 20).build());
        }
        int half = (w - 30) / 2;
        addRenderableWidget(Button.builder(
                Component.translatable("gui.chatimprovements.reset").withStyle(s -> s.withColor(UiPalette.WARNING)),
                button -> {
                    if (!resetArmed) {
                        resetArmed = true;
                        button.setMessage(Component.translatable("gui.chatimprovements.confirm_reset"));
                    } else {
                        ChatImprovementsConfig.resetDefaults();
                        refresh();
                    }
                }).bounds(x + 11, y + h - 29, half, 20).build());
        addRenderableWidget(Button.builder(
                Component.translatable("gui.chatimprovements.done")
                        .withStyle(s -> s.withColor(0xFF91E788)),
                button -> onClose()).bounds(x + 19 + half, y + h - 29, half, 20).build());
    }

    private void tab(String key, int destination, int tx, int tw) {
        int color = destination == page ? UiPalette.accent() : UiPalette.MUTED;
        addRenderableWidget(Button.builder(Component.translatable(key)
                .withStyle(s -> s.withColor(color)), btn -> {
                    if (destination != page && minecraft != null)
                        minecraft.setScreenAndShow(new ChatImprovementsSettingsScreen(destination));
                }).bounds(tx, y + 36, tw, 20).build());
    }

    private void buildColors() {
        String[] names = {"gui.chatimprovements.color.cyan",
                "gui.chatimprovements.color.violet", "gui.chatimprovements.color.amber",
                "gui.chatimprovements.color.lime"};
        int gap = 10, half = (w - 48) / 2;
        for (int i = 0; i < 4; i++) {
            final int selected = i;
            int bx = x + 19 + (i % 2) * (half + gap);
            int by = y + (compact ? 65 : 82) + (i / 2) * (compact ? 25 : 32);
            Component name = Component.translatable(names[i]);
            if (UiPalette.index() == i) name = Component.literal("* ").append(name);
            addRenderableWidget(Button.builder(name
                    .withStyle(s -> s.withColor(UiPalette.accent(selected))), button -> {
                        ChatImprovementsConfig.get().uiAccentTheme = selected;
                        ChatImprovementsConfig.save();
                        refresh();
                    }).bounds(bx, by, half, compact ? 20 : 24).build());
        }
        var cfg = ChatImprovementsConfig.get();
        addRenderableWidget(Button.builder(Component.translatable(
                "gui.chatimprovements.chat_skin",
                Component.translatable(cfg.chatSkinEnabled
                        ? "gui.chatimprovements.on" : "gui.chatimprovements.off"))
                .withStyle(s -> s.withColor(cfg.chatSkinEnabled ? UiPalette.accent()
                        : UiPalette.MUTED)), button -> {
            cfg.chatSkinEnabled = !cfg.chatSkinEnabled;
            ChatImprovementsConfig.save();
            refresh();
        }).bounds(x + 19, y + (compact ? 113 : 154), w - 38,
                compact ? 18 : 23).build());
    }

    private void buildLayout() {
        int gap = 10;
        int half = (w - 48) / 2;
        var cfg = ChatImprovementsConfig.get();
        addRenderableWidget(Button.builder(Component.translatable("gui.chatimprovements.compact",
                Component.translatable(cfg.compactHudEnabled
                        ? "gui.chatimprovements.on" : "gui.chatimprovements.off"))
                .withStyle(s -> s.withColor(cfg.compactHudEnabled
                        ? UiPalette.accent() : UiPalette.MUTED)), button -> {
            cfg.compactHudEnabled = !cfg.compactHudEnabled;
            ChatImprovementsConfig.save();
            refresh();
        }).bounds(x + 19, y + (compact ? 65 : 82), compact ? w - 38 : half,
                compact ? 20 : 24).build());

        addRenderableWidget(Button.builder(Component.translatable("gui.chatimprovements.compact_lines",
                cfg.compactHudLines).withStyle(s -> s.withColor(UiPalette.accent())), button -> {
            cfg.compactHudLines = cfg.compactHudLines == 3 ? 5
                    : cfg.compactHudLines == 5 ? 8 : 3;
            ChatImprovementsConfig.save();
            refresh();
        }).bounds(compact ? x + 19 : x + 19 + half + gap,
                y + (compact ? 91 : 82), compact ? w - 38 : half,
                compact ? 20 : 24).build());
    }

    private Component label(int id) {
        var c = ChatImprovementsConfig.get();
        if (id == 1) return Component.translatable("gui.chatimprovements.clock",
                c.twentyFourHour ? "24h" : "12h").withStyle(s -> s.withColor(UiPalette.accent()));
        boolean on = switch (id) {
            case 0 -> c.timestampsEnabled;
            case 2 -> c.showSeconds;
            case 3 -> c.copyButtonsEnabled;
            case 4 -> c.mentionHighlightsEnabled;
            case 8 -> c.quickMentionEnabled;
            case 5 -> c.playerHeadsEnabled;
            case 6 -> c.showHatLayer;
            default -> c.systemHeadFallback;
        };
        String key = switch (id) {
            case 0 -> "gui.chatimprovements.timestamps";
            case 2 -> "gui.chatimprovements.seconds";
            case 3 -> "gui.chatimprovements.copy";
            case 4 -> "gui.chatimprovements.mentions";
            case 8 -> "gui.chatimprovements.quickmention";
            case 5 -> "gui.chatimprovements.heads";
            case 6 -> "gui.chatimprovements.hats";
            default -> "gui.chatimprovements.compatibility";
        };
        int color = on ? UiPalette.accent() : UiPalette.MUTED;
        return Component.translatable(key, Component.translatable(
                on ? "gui.chatimprovements.on" : "gui.chatimprovements.off"))
                .withStyle(s -> s.withColor(color));
    }

    private void flip(int id) {
        var c = ChatImprovementsConfig.get();
        switch (id) {
            case 0 -> c.timestampsEnabled = !c.timestampsEnabled;
            case 1 -> c.twentyFourHour = !c.twentyFourHour;
            case 2 -> c.showSeconds = !c.showSeconds;
            case 3 -> c.copyButtonsEnabled = !c.copyButtonsEnabled;
            case 4 -> c.mentionHighlightsEnabled = !c.mentionHighlightsEnabled;
            case 5 -> c.playerHeadsEnabled = !c.playerHeadsEnabled;
            case 6 -> c.showHatLayer = !c.showHatLayer;
            case 7 -> c.systemHeadFallback = !c.systemHeadFallback;
            case 8 -> c.quickMentionEnabled = !c.quickMentionEnabled;
            default -> { }
        }
        ChatImprovementsConfig.save();
    }

    private void refresh() {
        if (minecraft != null) minecraft.setScreenAndShow(new ChatImprovementsSettingsScreen(page));
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        // Render backgrounds before vanilla buttons for keyboard and narration support.
        graphics.fillGradient(0, 0, width, height, 0xCA091321, 0xE9060A11);
        graphics.fillGradient(x, y, x + w, y + h, 0xFA26354B, 0xFC0D1724);
        graphics.outline(x, y, w, h, 0xFF496078);
        graphics.fill(x, y, x + w, y + 3, UiPalette.accent());

        graphics.fill(x + 9, y + 10, x + 29, y + 29, 0xFF152335);
        graphics.outline(x + 9, y + 10, 20, 19, UiPalette.accent());
        graphics.text(font, "C", x + 16, y + 15, UiPalette.accent(), true);

        int navX = x + 11, navW = (w - 40) / 4;
        graphics.fill(x + 8, y + 33, x + w - 8, y + 60, 0xBA122232);
        graphics.fill(navX + page * (navW + 6), y + 57,
                navX + page * (navW + 6) + navW, y + 60, UiPalette.accent());

        int boxTop = y + 63, boxBottom = y + h - 56;
        graphics.fill(x + 10, boxTop, x + w - 10, boxBottom, 0xCF1B2B3B);
        graphics.outline(x + 10, boxTop, w - 20, boxBottom - boxTop, 0xFF3B5268);
        graphics.fill(x + 10, boxTop, x + 13, boxBottom, UiPalette.accent());

        super.extractRenderState(graphics, mouseX, mouseY, delta);
        graphics.text(font, Component.translatable("gui.chatimprovements.title"),
                x + 37, y + 10, UiPalette.TEXT, true);
        graphics.text(font, Component.translatable("gui.chatimprovements.subtitle"),
                x + 37, y + 22, UiPalette.MUTED, true);

        int noteY = y + h - 49;
        Component note;
        if (page == 0) {
            var c = ChatImprovementsConfig.get();
            String fmt = c.twentyFourHour ? (c.showSeconds ? "HH:mm:ss" : "HH:mm")
                    : (c.showSeconds ? "hh:mm:ss a" : "hh:mm a");
            String preview = c.timestampsEnabled
                    ? "[" + LocalTime.now().format(DateTimeFormatter.ofPattern(fmt)) + "] " : "";
            note = Component.translatable("gui.chatimprovements.preview", preview);
        } else if (page == 1) {
            note = Component.translatable(ChatImprovementsConfig.get().systemHeadFallback
                    ? "gui.chatimprovements.fallback_warning" : "gui.chatimprovements.heads_note");
        } else if (page == 2) {
            note = Component.translatable("gui.chatimprovements.style_note");
        } else {
            note = Component.translatable("gui.chatimprovements.layout_note");
        }
        graphics.text(font, note, x + 14, noteY, UiPalette.MUTED, true);
        graphics.text(font, Component.translatable("gui.chatimprovements.footer"),
                x + 14, y + h - 41, UiPalette.MUTED, true);
    }

    @Override
    public void onClose() {
        if (minecraft != null) minecraft.setScreenAndShow(null);
    }

    @Override
    public boolean isPauseScreen() { return false; }
}
