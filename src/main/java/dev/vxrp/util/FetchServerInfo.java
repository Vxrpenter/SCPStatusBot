package dev.vxrp.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import dev.vxrp.bot.token.TokenManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FetchServerInfo {
    public static ServerInfo fetchServerInfo() {
        //Fetching the server info
        String content;
        try (InputStream is = new URL("https://api.scpslgame.com/serverinfo.php?id="+TokenManager.getID()+"&key="+TokenManager.getAPI()+"&players=false&online=true").openStream()) {
            content = new String(is.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to retrieve server info", e);
        }
        JsonArray array = JsonParser.parseString(content).getAsJsonObject().get("Servers").getAsJsonArray();
        //Try to get the status, if it fails, set it to false
        boolean status = array.get(0).getAsJsonObject().get("Online").getAsBoolean();
        //Try to get the players, if it fails, set it to 0
        String players;
        try {
            players = array.get(0).getAsJsonObject().get("Players").getAsString();
        } catch (NullPointerException e) {
            players = "0";
        }
        return new ServerInfo(status, players);
    }
}
