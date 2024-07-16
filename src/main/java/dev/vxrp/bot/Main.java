package dev.vxrp.bot;

import dev.vxrp.bot.token.TokenManager;
import dev.vxrp.util.DelayedTask;
import dev.vxrp.util.ServerInfo;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import java.util.Objects;

import static dev.vxrp.util.FetchServerInfo.fetchServerInfo;

public class Main extends ListenerAdapter {
    public static void main(String[] args) {
        if (new TokenManager().getToken() == null) throw new RuntimeException("Token is null");
        //Creating the JDA instance
        JDA api = JDABuilder.createDefault(new TokenManager().getToken(), GatewayIntent.MESSAGE_CONTENT)
                .setActivity(Activity.customStatus("Pending..."))
                .disableCache(CacheFlag.VOICE_STATE, CacheFlag.EMOJI, CacheFlag.STICKER, CacheFlag.SCHEDULED_EVENTS)
                .build();
        //Starting the task
        DelayedTask.repeatTaskDelayed(retrieveServerInfo(api), 12);

    }

    public static void updateActivity(JDA api, boolean status, String players) {
        //Setting the activity
        if (status) {
            //Setting the Status

            if (Objects.equals(players, "0/"+TokenManager.getSlots())) {
                api.getPresence().setStatus(OnlineStatus.IDLE);
            } else {
                api.getPresence().setStatus(OnlineStatus.ONLINE);
            }
            api.getPresence().setActivity(Activity.customStatus("Server Online | Players: " + players));
        } else {
            api.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
            api.getPresence().setActivity(Activity.customStatus("Server Offline"));
        }
    }

    public static Runnable retrieveServerInfo(JDA api) {
        return () -> {
            ServerInfo serverInfo = fetchServerInfo();
            boolean status = serverInfo.isStatus();
            String players = serverInfo.getPlayers();
            //Applying the changes
            if (status) {
                System.out.println("Updated Status: Online | Players: " + players);
                updateActivity(api, true, players);
            } else {
                System.out.println("Updated Status: Offline");
               updateActivity(api, false, null);
            }
        };
    }
}