package com.example.launcher.newsession.Model;


import com.google.gson.annotations.SerializedName;

public class Game {

    @SerializedName("id")
    private int id;
    @SerializedName("player1_id")
    private int player1_id;
    @SerializedName("player2_id")
    private int player2_id;
    @SerializedName("state")
    private String state;
    @SerializedName("moves")
    private String moves;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayer1_id() {
        return player1_id;
    }

    public void setPlayer1_id(int player1_id) {
        this.player1_id = player1_id;
    }

    public int getPlayer2_id() {
        return player2_id;
    }

    public void setPlayer2_id(int player2_id) {
        this.player2_id = player2_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMoves() {
        return moves;
    }

    public void setMoves(String moves) {
        this.moves = moves;
    }
}
