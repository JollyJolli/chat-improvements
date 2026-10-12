package io.github.jollyjolli.chatimprovements;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.minecraft.network.chat.Component;

/** Client-side settings that work in singleplayer and without installing on the server. */
public final class ChatImprovementsCommands {
    private ChatImprovementsCommands() { }

    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommands.literal("chatimprovements")
                    .executes(context -> {
                        var config = ChatImprovementsConfig.get();
                        context.getSource().sendFeedback(Component.literal(
                                "Chat Improvements: timestamps " + (config.timestampsEnabled ? "ON" : "OFF")
                                + ", " + (config.twentyFourHour ? "24h" : "12h")
                                + ", seconds " + (config.showSeconds ? "ON" : "OFF")
                                + ", heads " + (config.playerHeadsEnabled ? "ON" : "OFF")
                                + ", hats " + (config.showHatLayer ? "ON" : "OFF")
                                + ". Use timestamps, format, seconds, heads, hats or reload."));
                        return 1;
                    })
                    .then(ClientCommands.literal("timestamps")
                            .then(ClientCommands.literal("on").executes(context -> {
                                ChatImprovementsConfig.get().timestampsEnabled = true;
                                ChatImprovementsConfig.save();
                                context.getSource().sendFeedback(Component.literal("Chat timestamps enabled."));
                                return 1;
                            }))
                            .then(ClientCommands.literal("off").executes(context -> {
                                ChatImprovementsConfig.get().timestampsEnabled = false;
                                ChatImprovementsConfig.save();
                                context.getSource().sendFeedback(Component.literal("Chat timestamps disabled."));
                                return 1;
                            })))
                    .then(ClientCommands.literal("format")
                            .then(ClientCommands.literal("12").executes(context -> {
                                ChatImprovementsConfig.get().twentyFourHour = false;
                                ChatImprovementsConfig.save();
                                context.getSource().sendFeedback(Component.literal("Timestamp format: 12-hour."));
                                return 1;
                            }))
                            .then(ClientCommands.literal("24").executes(context -> {
                                ChatImprovementsConfig.get().twentyFourHour = true;
                                ChatImprovementsConfig.save();
                                context.getSource().sendFeedback(Component.literal("Timestamp format: 24-hour."));
                                return 1;
                            })))
                    .then(ClientCommands.literal("seconds")
                            .then(ClientCommands.literal("on").executes(context -> {
                                ChatImprovementsConfig.get().showSeconds = true;
                                ChatImprovementsConfig.save();
                                context.getSource().sendFeedback(Component.literal("Timestamp seconds enabled."));
                                return 1;
                            }))
                            .then(ClientCommands.literal("off").executes(context -> {
                                ChatImprovementsConfig.get().showSeconds = false;
                                ChatImprovementsConfig.save();
                                context.getSource().sendFeedback(Component.literal("Timestamp seconds disabled."));
                                return 1;
                            })))
                    .then(ClientCommands.literal("heads")
                            .then(ClientCommands.literal("on").executes(context -> {
                                ChatImprovementsConfig.get().playerHeadsEnabled = true;
                                ChatImprovementsConfig.save();
                                context.getSource().sendFeedback(Component.literal("Chat player heads enabled."));
                                return 1;
                            }))
                            .then(ClientCommands.literal("off").executes(context -> {
                                ChatImprovementsConfig.get().playerHeadsEnabled = false;
                                ChatImprovementsConfig.save();
                                context.getSource().sendFeedback(Component.literal("Chat player heads disabled."));
                                return 1;
                            })))
                    .then(ClientCommands.literal("hats")
                            .then(ClientCommands.literal("on").executes(context -> {
                                ChatImprovementsConfig.get().showHatLayer = true;
                                ChatImprovementsConfig.save();
                                context.getSource().sendFeedback(Component.literal("Chat head hat layers enabled."));
                                return 1;
                            }))
                            .then(ClientCommands.literal("off").executes(context -> {
                                ChatImprovementsConfig.get().showHatLayer = false;
                                ChatImprovementsConfig.save();
                                context.getSource().sendFeedback(Component.literal("Chat head hat layers disabled."));
                                return 1;
                            })))
                    .then(ClientCommands.literal("reload").executes(context -> {
                        ChatImprovementsConfig.load();
                        context.getSource().sendFeedback(Component.literal("Chat Improvements configuration reloaded."));
                        return 1;
                    })));
        });
    }
}
