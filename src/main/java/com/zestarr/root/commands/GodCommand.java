package com.zestarr.root.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static com.zestarr.root.utils.ChatUtils.sendFromServer;

public class GodCommand extends CommandManager{


    public GodCommand() {
        super(
                "God",
                new String[]{"god"},
                "God (another) player",
                "roots.god"
        );
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (sender instanceof Player) {
            if (args.length == 0) {
                Player player = (Player) sender;
                player.setInvulnerable(!player.isInvulnerable());
                if (player.isInvulnerable()) { sendFromServer("&6God mode enabled", (Player) sender); }
                if (!player.isInvulnerable()) { sendFromServer("&6God mode disabled", (Player) sender); }
            } else if (args.length == 1) {
                Player player = Bukkit.getPlayer(args[0]);
                if (player != null) {
                    player.setInvulnerable(!player.isInvulnerable());
                    if (player.isInvulnerable()) { sendFromServer("&6God mode enabled for " + player, (Player) sender); }
                    if (!player.isInvulnerable()) { sendFromServer("&6God mode disabled for " + player, (Player) sender); }
                } else {
                    sendFromServer("&cPlayer not found", (Player) sender);
                }
            } else {
                sendFromServer("&cPlease follow the format: /heal (Player) or /heal", (Player) sender);
            }
        }

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
