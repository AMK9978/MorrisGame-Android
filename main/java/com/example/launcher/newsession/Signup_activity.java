package com.example.launcher.newsession;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Signup_activity extends AppCompatActivity {

    Button register,back;
    EditText txt_name,txt_password,txt_email,txt_age,txt_description;
    String name,password,email,description;
    int age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_activity);
        register = (Button)findViewById(R.id.register_btn);
        back = (Button)findViewById(R.id.bac_btn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup_activity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = txt_name.getText().toString();
                email = txt_email.getText().toString();
                password = txt_password.getText().toString();
                description = txt_description.getText().toString();
                age = Integer.parseInt(txt_age.getText().toString());
                if (!validation(name,email,password)){
                    return;
                }
                //post info's to api and if it was unique email , store his info's into players table
                //and go to HomePage

            }
        });

    }

    private boolean validation(String name,String email,String password){


        return false;
    }

}
