package com.example.launcher.newsession.WebService;


import com.example.launcher.newsession.Model.Game;
import com.example.launcher.newsession.Model.Player;
import com.example.launcher.newsession.Model.PlayerResponse;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("player")
    Call<PlayerResponse> getPlayers();

    @GET("login/{email}")
    Call<Player> registerPlayer(@Path("email") String email);



    @GET("game/{player_id}")
    Call<String> getGamesState(@Path("player_id")int player_id);

    @GET("game/{id}")
    Call<String> getMoves(@Path("id") int id);

    @PUT("game/{id}")
    Call<String> sendMoves(@Path("id") int id,@Body Game game);

    @GET("player/{id}")
    Call<Player> getPlayer(@Path("id")int id);

    @PUT("player/{id}")
    Call<String> setPlayer(@Path("id")int id,@Body Player player );
}