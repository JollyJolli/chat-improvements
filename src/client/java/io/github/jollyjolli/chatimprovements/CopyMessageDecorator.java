package io.github.jollyjolli.chatimprovements;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;

/**
 * A small clickable [C] icon copies the original visible message text.
 * Only the icon has a click event: URLs and custom click events in the
 * original rich component remain unchanged. Never sends anything to a server.
 */
public final class CopyMessageDecorator {
    private CopyMessageDecorator() { }

    public static Component decorate(Component original) {
        if (original == null || !ChatImprovementsConfig.get().copyButtonsEnabled) {
            return original;
        }
        String plainText = original.getString();
        if (plainText.isEmpty()) {
            return original;
        }

        Component button = Component.literal("[C] ").withStyle(style -> style
                .withColor(ChatFormatting.GRAY)
                .withClickEvent(new ClickEvent.CopyToClipboard(plainText))
                .withHoverEvent(new HoverEvent.ShowText(Component.literal("Copy message"))));

        return Component.empty().append(button).append(original);
    }
}
