package com.example.launcher.newsession;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.launcher.newsession.Model.Player;
import com.example.launcher.newsession.Model.PlayerResponse;
import com.example.launcher.newsession.WebService.APIClient;
import com.example.launcher.newsession.WebService.APIInterface;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button login_button,signup_button,exit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_button = (Button)findViewById(R.id.login_btn);
        signup_button = (Button)findViewById(R.id.signup_btn);
        exit_button = (Button)findViewById(R.id.exit_btn);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,login_activity.class);
                startActivity(intent);
            }
        });

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Signup_activity.class);
                startActivity(intent);
            }
        });

        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(1);
            }
        });

    }

    private void LoginRequest(String name,String password){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Player> call = apiInterface.registerPlayer(name);
        call.enqueue(new Callback<Player>() {
             @Override
             public void onResponse(Call<Player> call, Response<Player> response) {
                 if (response.isSuccessful()){
                     Intent intent = new Intent(MainActivity.this,HomePage_activity.class);
                     startActivity(intent);
                 }
             }

             @Override
             public void onFailure(Call<Player> call, Throwable t) {

             }
         });
    }

    private void getRequest(){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<PlayerResponse> call = apiInterface.getPlayers();
        call.enqueue(new Callback<PlayerResponse>() {
            @Override
            public void onResponse(Call<PlayerResponse> call, Response<PlayerResponse> response) {
                if (response.isSuccessful()){
                    List<Player> players = response.body().getPlayers();
                    for (Player player:players){
                        Log.i("retrofit",player.getName());
                        Log.i("retrofit",player.getEmail());
                    }
                }
            }

            @Override
            public void onFailure(Call<PlayerResponse> call, Throwable t) {
                if (t instanceof IOException){
                    Toast.makeText(MainActivity.this,"Connection failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
