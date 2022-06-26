package com.zestarr.root.events.ChatManager;

import com.zestarr.root.Roots;
import com.zestarr.root.utils.ColorUtils;
import com.zestarr.root.utils.VaultUtils;
import net.dv8tion.jda.api.entities.Guild;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static com.zestarr.root.utils.ChatUtils.format;

public class ChatEvent implements Listener {

    private String prefix;
    private String suffix;
    private String discordMessage;
    private String message;


    @EventHandler()
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        LuckPerms api = LuckPermsProvider.get();
        User user = api.getPlayerAdapter(Player.class).getUser(player);


        String gPrefix = api.getGroupManager().getGroup(user.getPrimaryGroup()).getCachedData().getMetaData().getPrefix();

        String suffix = user.getCachedData().getMetaData().getSuffix();
        String gSuffix = api.getGroupManager().getGroup(user.getPrimaryGroup()).getCachedData().getMetaData().getSuffix();

        if (user.getCachedData().getMetaData().getPrefix() == null) {
            if (api.getGroupManager().getGroup(user.getPrimaryGroup()).getCachedData().getMetaData().getPrefix() == null) {
                prefix = "";
            } else {
                prefix = api.getGroupManager().getGroup(user.getPrimaryGroup()).getCachedData().getMetaData().getPrefix();
            }

        } else {
            prefix = user.getCachedData().getMetaData().getPrefix();
        }

        if (user.getCachedData().getMetaData().getSuffix() == null) {
            if (api.getGroupManager().getGroup(user.getPrimaryGroup()).getCachedData().getMetaData().getSuffix() == null) {
                suffix = "";
            } else {
                suffix = api.getGroupManager().getGroup(user.getPrimaryGroup()).getCachedData().getMetaData().getSuffix();
            }

        } else {
            suffix = user.getCachedData().getMetaData().getSuffix();
        }








        if (Roots.getMainInstance().getConfigBoolean("Discord.DiscordBridge.Enabled")) {
            if (Roots.getMainInstance().getConfigBoolean("Discord.DiscordBridge.MinecraftToDiscord")) {

                discordMessage = format(player.getDisplayName() + "" + suffix + "» " + e.getMessage());

                String[] MinecraftLanguageFilter = {"§a","§b","§c","§d","§e", "§f", "§k","§l", "§m","§n", "§o","§r", "§0","§1", "§2", "§3", "§4", "§5", "§6", "§7", "§8", "§9", "§x"}; //Weird color thingies & next-back selections
                for (String f : MinecraftLanguageFilter)
                    discordMessage = discordMessage.replaceAll(f, "");
                if (!discordMessage.isEmpty()) {

                    Roots.getJda().getTextChannelById(Roots.getMainInstance().getConfig().getString("Discord.DiscordBridge.DiscordChannelID"))
                            .sendMessage(discordMessage)
                            .queue();

                }
            }
        }

        if (Roots.getMainInstance().getConfigBoolean("CustomChat.Enabled")) {


            if (Roots.getMainInstance().getConfigBoolean("CustomChat.Enabled")) {
                try {
                    if (prefix != null) {
                        if (suffix != null) {
                            e.setFormat(ColorUtils.translateColorCodes(gPrefix + "&7 " + player.getDisplayName() + " " + suffix  + " &8» &f" + "&f" + e.getMessage()));
                        } else if (suffix == null) {
                            e.setFormat(ColorUtils.translateColorCodes(gPrefix + "&7" + player.getDisplayName() + " &8» &f" + e.getMessage()));
                        }
                    } else {
                        e.setFormat(ColorUtils.translateColorCodes("&7" + player.getDisplayName() + " &8» &f" + e.getMessage()));
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        /*
        if (Roots.getMainInstance().getConfigBoolean("CustomChat.Enabled")) {

            e.setFormat(format(Roots.getMainInstance().getConfig().getString("CustomChat.ChatFormat.ChatFormat")
                    .replace("{player}", e.getPlayer().getDisplayName())
                    .replace("{message}", e.getMessage())));
*/


        }

    }
