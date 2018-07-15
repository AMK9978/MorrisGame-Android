package com.example.launcher.newsession;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.launcher.newsession.Model.Player;
import com.example.launcher.newsession.WebService.APIClient;
import com.example.launcher.newsession.WebService.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login_activity extends AppCompatActivity {

    Button register,back;
    EditText txt_password,txt_email;
    String password,email;
    Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        register = (Button) findViewById(R.id.login_login);
        back = (Button) findViewById(R.id.login_back);
        txt_email = (EditText)findViewById(R.id.login_email);
        txt_password = (EditText) findViewById(R.id.login_password);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = txt_email.getText().toString();
                password = txt_password.getText().toString();
                login_thread.start();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    Thread login_thread = new Thread(new Runnable() {
        @Override
        public void run() {
            LoginRequest(email,password);
        }
    });

    private void LoginRequest(final String email, String password){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Player> call = apiInterface.registerPlayer(email);
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                if (response.isSuccessful()){
                    Intent intent = new Intent(login_activity.this,HomePage_activity.class);
                    try {
                        intent.putExtra("player_object",response.body());

                    }catch (Exception e){
                        Toast.makeText(login_activity.this,"No such player",Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                        return;
                    }
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Toast.makeText(login_activity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
