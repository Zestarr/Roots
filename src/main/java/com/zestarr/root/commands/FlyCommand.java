package com.zestarr.root.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static com.zestarr.root.utils.ChatUtils.sendFromServer;

public class FlyCommand extends CommandManager {
    public FlyCommand() {

        super(
                "fly",
                new String[]{"flight"},
                "Enable/Disable flight",
                "roots.fly"
        );
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (sender instanceof Player) {
            if (args.length == 0) {
                Player player = (Player) sender;
                player.setAllowFlight(!player.getAllowFlight());
                if (player.getAllowFlight()) { sendFromServer("&7Fly has been enabled", (Player) sender); }
                if (!player.getAllowFlight()) { sendFromServer("&7Fly has been disabled", (Player) sender); }
            } else if (args.length == 1) {
                Player player = Bukkit.getPlayer(args[0]);
                if (player != null) {
                    player.setAllowFlight(!player.getAllowFlight());
                    if (player.getAllowFlight()) { sendFromServer("&7Fly has been enabled for " + player.getDisplayName(), (Player) sender); }
                    if (!player.getAllowFlight()) { sendFromServer("&7Fly has been disabled for " + player.getDisplayName(), (Player) sender); }
                } else {
                    sendFromServer("&cPlayer not found", (Player) sender);
                }
            } else {
                sendFromServer("&cPlease follow the format: /fly (Player) or /fly", (Player) sender);
            }
        }

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
