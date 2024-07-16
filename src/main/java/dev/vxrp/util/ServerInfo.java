package dev.vxrp.util;

public class ServerInfo {
    private boolean status;
    private String players;

    public ServerInfo(boolean status, String players) {
        this.status = status;
        this.players = players;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPlayers() {
        return players;
    }

    public void setPlayers(String players) {
        this.players = players;
    }
}
