package com.example.launcher.newsession;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.launcher.newsession.Model.Game;
import com.example.launcher.newsession.Model.Player;
import com.example.launcher.newsession.WebService.APIClient;
import com.example.launcher.newsession.WebService.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class waiting_activity extends AppCompatActivity {

    public static Player player1;
    private int change = 0;
    private Handler handler;

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            do {
                Log.i("msg",player1.getEmail());
                getRequest(player1.getEmail());

                try {
                    thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while (change ==0);

        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_activity);
        change = 0;
        Bundle bundle = getIntent().getExtras();
        player1 = (Player)bundle.getSerializable("player");
        PutRequest();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Bundle bundle = new Bundle();
                Message message = new Message();
                message.setData(bundle);
            }
        };

        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
            }
        };
        handler.post(runnable);
    }
    //we should define a Thread for receiving that is available any other player to play together?



    public void PutRequest(){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<String> call = apiInterface.setPlayer(player1.getId(),player1);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    Log.i("msg",response.message());
                    if(response.body().equals("Not found another online player")){
                        thread.start();
                        return;
                    }

                    Intent intent = new Intent(waiting_activity.this,GameActivity.class);
                    String[] s = response.body().split(",");
                    GameActivity.Game_Id = Integer.parseInt(s[3]);
                    change =1;
                    GameActivity.turn = true;
                    GameActivity.color = "W";
                    GameActivity.pic = "pic1.png";
                    GameActivity.waiting_pic = "pic1_waiting.png";
                    GameActivity.current_player_id = Integer.valueOf(s[1]);
                    GameActivity.other_player_id = Integer.valueOf(s[2]);

                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }

        });
    }

    public void getRequest(final String email){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<String> call = apiInterface.getGamesState(player1.getId());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    Log.i("msg",response.message());
                    Intent intent = new Intent(waiting_activity.this,GameActivity.class);
                    String[] s = response.body().split(",");
                    GameActivity.Game_Id = Integer.parseInt(s[0]);
                    change =1;
                    GameActivity.turn = false;
                    GameActivity.color = "B";
                    GameActivity.pic = "pic2.png";
                    GameActivity.waiting_pic = "pic2_waiting.png";
                    GameActivity.current_player_id = Integer.valueOf(s[2]);
                    GameActivity.other_player_id = Integer.valueOf(s[1]);
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("msg","^_^"+t.getMessage());
            }
        });
    }
}
