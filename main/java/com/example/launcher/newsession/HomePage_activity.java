package com.example.launcher.newsession;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.launcher.newsession.Model.Player;
import com.example.launcher.newsession.WebService.APIClient;
import com.example.launcher.newsession.WebService.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage_activity extends AppCompatActivity {
    public static Player player;
    TextView txt_name,txt_des;
    Button challenge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_activity);
        player = (Player) getIntent().getSerializableExtra("player_object");

        txt_name = (TextView)findViewById(R.id.homepage_name);
        txt_des = (TextView) findViewById(R.id.homepage_description);
        txt_name.setText(player.getName());
        txt_des.setText(player.getDescription());
        challenge = (Button)findViewById(R.id.homepage_start);
        challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage_activity.this,waiting_activity.class);
                player.setState("online");
                intent.putExtra("player",player);
                startActivity(intent);
            }
        });
    }

//    private void setOnline(final String email){
//        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
//        Call<Player> call = apiInterface.setOnline(email);
//        call.enqueue(new Callback<Player>() {
//            @Override
//            public void onResponse(Call<Player> call, Response<Player> response) {
//                if (response.isSuccessful()){
//                    Intent intent = new Intent(HomePage_activity.this,waiting_activity.class);
//                    try {
//                        intent.putExtra("player", response.body());
//                    }catch (Exception e){
//                        Toast.makeText(HomePage_activity.this,"No such player",Toast.LENGTH_SHORT).show();
//                        e.printStackTrace();
//                        return;
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Player> call, Throwable t) {
//
//            }
//        });
//    }

//    private Player Player_Object(String email){
//        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
//        Call<Player> call = apiInterface.showPlayer(email);
//        call.enqueue(new Callback<Player>() {
//            @Override
//            public void onResponse(Call<Player> call, Response<Player> response) {
//                if (response.isSuccessful()){
//                    String name = response.body().getName();
//                    String email = response.body().getEmail();
//                    String password = response.body().getPassword();
//                    int age = response.body().getAge();
//                    String des = response.body().getDescription();
//                    player = new Player(age,name,email,password,des);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Player> call, Throwable t) {
//
//            }
//        });
//        return player;
//    }
}
