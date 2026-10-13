package io.github.jollyjolli.chatimprovements;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * Explicit privacy-first diagnostics. Only anonymous current-session counts
 * appear; no traffic is sent, saved or logged.
 */
public final class HeadDiagnosticsScreen extends Screen {
    private int left, top, panelW, panelH;

    public HeadDiagnosticsScreen() {
        super(Component.translatable("gui.chatimprovements.diagnostics"));
    }

    @Override
    protected void init() {
        panelW = Math.min(390, width - 16);
        panelH = Math.min(235, height - 12);
        left = (width - panelW) / 2;
        top = (height - panelH) / 2;
        int gap = 8, w = (panelW - gap - 24) / 2;
        addRenderableWidget(Button.builder(Component.translatable("gui.chatimprovements.copy_report"),
                button -> {
                    if (minecraft != null) {
                        minecraft.keyboardHandler.setClipboard(HeadChatDiagnostics.report());
                    }
                }).bounds(left + 12, top + panelH - 30, w, 20).build());
        addRenderableWidget(Button.builder(Component.translatable("gui.chatimprovements.clear_counts"),
                button -> HeadChatDiagnostics.reset())
                .bounds(left + 12 + w + gap, top + panelH - 30, w, 20).build());
        addRenderableWidget(Button.builder(Component.translatable("gui.chatimprovements.back"),
                button -> onClose())
                .bounds(left + 12, top + panelH - 54, panelW - 24, 19).build());
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        graphics.fillGradient(0, 0, width, height, 0xCD0B1421, 0xED060A10);
        graphics.fillGradient(left, top, left + panelW, top + panelH, 0xF9253449, 0xFB0D1623);
        graphics.outline(left, top, panelW, panelH, 0xFF465E72);
        graphics.fill(left, top, left + panelW, top + 3, UiPalette.accent());
        super.extractRenderState(graphics, mouseX, mouseY, delta);
        graphics.text(font, title, left + 13, top + 12, UiPalette.accent(), true);
        String[] lines = HeadChatDiagnostics.reportLines();
        for (int i = 0; i < lines.length; i++) {
            graphics.text(font, lines[i], left + 14, top + 36 + i * 17,
                    UiPalette.TEXT, true);
        }
        graphics.text(font, Component.translatable("gui.chatimprovements.diagnostic_privacy"),
                left + 14, top + 149, UiPalette.MUTED, true);
    }

    @Override
    public void onClose() {
        if (minecraft != null) minecraft.setScreenAndShow(new ChatImprovementsSettingsScreen());
    }

    @Override
    public boolean isPauseScreen() { return false; }
}
