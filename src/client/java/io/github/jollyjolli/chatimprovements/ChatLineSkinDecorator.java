package io.github.jollyjolli.chatimprovements;

import net.minecraft.network.chat.Component;

/**
 * A display-only, thin theme-colored marker for each incoming chat line.
 * It is appended AFTER copy actions have captured the original text,
 * leaving clipboard contents, signed payloads, hover and URL clicks intact.
 */
public final class ChatLineSkinDecorator {
    private ChatLineSkinDecorator() { }

    public static Component decorate(Component message) {
        if (message == null || !ChatImprovementsConfig.get().chatSkinEnabled) {
            return message;
        }
        Component marker = Component.literal("▏ ")
                .withStyle(style -> style.withColor(UiPalette.accent()));
        return Component.empty().append(marker).append(message);
    }
}
