package com.zestarr.root.commands.gamemodes;

import com.zestarr.root.commands.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static com.zestarr.root.utils.ChatUtils.sendFromServer;

public class SurvivalCommand extends CommandManager {

    public SurvivalCommand() {
        super(
                "gms",
                new String[]{"gm0"},
                "Set the (another) players gamemode to survival",
                "roots.gamemode.creative")
        ;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (sender instanceof Player) {
            if (args.length == 0) {
                Player player = (Player) sender;
                player.setGameMode(GameMode.SURVIVAL);
            } else if (args.length == 1) {
                Player player = Bukkit.getPlayer(args[0]);
                if (player != null) {
                    player.setGameMode(GameMode.SURVIVAL);
                } else {
                    sendFromServer("&cPlayer not found", (Player) sender);
                }
            } else {
                sendFromServer("&cPlease follow the format: /gms (Player) or /gms", (Player) sender);
            }
        }

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
