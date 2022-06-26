package com.zestarr.root.commands.Discord;

import com.zestarr.root.Roots;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class BotControl {

    /*
    private static ShardManager shardManager;

    public static void BotManager() throws LoginException {
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(Roots.getMainInstance().getConfig().getString("Discord.DiscordBridge.Token"));

        builder.setActivity(Activity.playing("SoulNetork"));
        builder.addEventListeners(new DiscordListener());

        shardManager = builder.build();

    }

    public static ShardManager getShardManager() { return shardManager; }
*/
}
