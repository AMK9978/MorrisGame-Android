package com.example.launcher.newsession.Model;

import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.launcher.newsession.GameActivity;
import com.example.launcher.newsession.R;

public class Home {
    
    private int index;
    public Player owner = null;
    public boolean tic=false;
    public boolean isocc=false;
    private String pic=null;
    private ImageButton imageButton = null;
    public String color = null;

    public int getIndex() {
        return index;
    }

    public Home(int n, ImageButton imageButton){
        index=n;
        this.imageButton = imageButton;
    }

    public void setPic(String pic) {
        this.pic = pic;
        if (pic == null){
            imageButton.setImageDrawable(null);
            return;
        }
        if (pic.equals("pic1.png")){
            if (imageButton==null){
                Log.i("payam","imageButton is null");
            }else{
                imageButton.setBackgroundResource(R.drawable.pic1);
            }

        }else if (pic.equals("pic2.png")){
            imageButton.setBackgroundResource(R.drawable.pic2);
        }else if (pic.equals("pic1_waiting.png")){
            imageButton.setBackgroundResource(R.drawable.pic1_waiting);
        }else {
            imageButton.setBackgroundResource(R.drawable.pic2_waiting);
        }

    }
}
