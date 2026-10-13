package io.github.jollyjolli.chatimprovements;

import net.minecraft.client.gui.GuiGraphicsExtractor;

/**
 * The vanilla ChatComponent backends own the message coordinates and opacity.
 * These methods only replace the rendered message-line BACKGROUND, not chat
 * text, signatures, click/hover regions or the actual chat history.
 */
public final class ChatSurfacePainter {
    private ChatSurfacePainter() { }

    /**
     * Draw a small Dark Neon card at the same vanilla message-line bounds.
     * Return false for non-message regions (scroll bar, other decorations).
     *
     * Crucially, the incoming vanilla alpha controls the opacity so closed-HUD
     * notifications fade away naturally and don't leave permanent rectangles.
     */
    public static boolean paintLine(GuiGraphicsExtractor graphics,
                                    int x0, int y0, int x1, int y1, int originalColor) {
        if (!ChatImprovementsConfig.get().chatSkinEnabled) return false;
        int width = x1 - x0, height = y1 - y0;
        if (width < 75 || height < 5 || height > 26) return false;
        int vanillaAlpha = (originalColor >>> 24) & 255;
        if (vanillaAlpha <= 0) return false;

        int alpha = Math.min(206, vanillaAlpha);
        int left = (alpha << 24) | 0x182B3F;
        int right = (alpha << 24) | 0x101C2B;
        int accentAlpha = Math.min(240, Math.max(0, vanillaAlpha));
        int accent = (accentAlpha << 24) | (UiPalette.accent() & 0xFFFFFF);

        // 1. Individual chat bubbles, including the mini chat while playing.
        graphics.fillGradient(x0, y0, x1, y1, left, right);
        // 2. Real colored line rail, not an overlay floating behind the HUD.
        graphics.fill(x0, y0, Math.min(x0 + 2, x1), y1, accent);
        // 3. Thin separator; no content reflow, preserving line hitboxes.
        graphics.fill(x0 + 2, y1 - 1, x1, y1,
                (Math.min(100, vanillaAlpha) << 24) | 0x34495D);
        return true;
    }
}
