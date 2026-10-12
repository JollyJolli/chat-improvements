package io.github.jollyjolli.chatimprovements;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

/** Adds a local, dark-gray time prefix without flattening styled chat content. */
public final class TimestampDecorator {
    private static final DateTimeFormatter H24 = DateTimeFormatter.ofPattern("HH:mm", Locale.ROOT);
    private static final DateTimeFormatter H24_SECONDS = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ROOT);
    private static final DateTimeFormatter H12 = DateTimeFormatter.ofPattern("hh:mm a", Locale.ROOT);
    private static final DateTimeFormatter H12_SECONDS = DateTimeFormatter.ofPattern("hh:mm:ss a", Locale.ROOT);

    private TimestampDecorator() { }

    public static Component decorate(Component original) {
        ChatImprovementsConfig config = ChatImprovementsConfig.get();
        if (!config.timestampsEnabled || original == null) {
            return original;
        }
        DateTimeFormatter formatter = config.twentyFourHour
                ? (config.showSeconds ? H24_SECONDS : H24)
                : (config.showSeconds ? H12_SECONDS : H12);
        String time = LocalTime.now().format(formatter);
        MutableComponent prefix = Component.literal("[" + time + "] ")
                .withStyle(ChatFormatting.DARK_GRAY);
        return Component.empty().append(prefix).append(original);
    }
}
