package com.example.launcher.newsession.Model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayerResponse {

    @SerializedName("players")
    private List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
