package com.zestarr.root.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static com.zestarr.root.utils.ChatUtils.sendFromServer;

public class SudoCommand extends CommandManager {

    public SudoCommand() {
        super("sudo",
                new String[]{"speak"},
                "Make the (another) Player say a message or command",
                "roots.sudo"
        );
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 2) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        target.chat(args[1]);
                    } else {
                        sendFromServer("&cPlayer not found", player);
                    }
                } else {
                    sendFromServer("&cPlease follow the format: /sudo (Player) (Message)", player);
                }

        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
