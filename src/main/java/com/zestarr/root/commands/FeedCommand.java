package com.zestarr.root.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static com.zestarr.root.utils.ChatUtils.format;
import static com.zestarr.root.utils.ChatUtils.sendFromServer;

public class FeedCommand extends CommandManager {

    public FeedCommand() {
        super(
                "feed",
                new String[]{"eat","ImHungry"},
                "A command to feed the player",
                "roots.feed"
        );
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (sender instanceof Player) {
            if (args.length == 0) {
                Player player = (Player) sender;
                player.setFoodLevel(20);
            } else if (args.length == 1) {
                Player player = Bukkit.getPlayer(args[0]);
                if (player != null) {
                    player.setFoodLevel(20);
                } else {
                    sender.sendMessage(format("&cPlayer not found"));
                }
            } else {
                sendFromServer("&cPlease follow the format: /feed (Player) or /feed", (Player) sender);
            }
        }

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }

}
