package com.example.launcher.newsession;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.launcher.newsession.Model.Game;
import com.example.launcher.newsession.Model.Home;
import com.example.launcher.newsession.Model.Player;
import com.example.launcher.newsession.WebService.APIClient;
import com.example.launcher.newsession.WebService.APIInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameActivity extends AppCompatActivity {

    public static int current_player_id,other_player_id;//have been valued
    private int changes = 0;
    public static boolean turn ;//valued
    public static String color;//valued
    public static String pic;//valued
    public static String waiting_pic;//valued
    private Player current_player, other_player;
    private Home origin;
    private int status;
    private Game game = new Game();
    private String my_moves = "";
    ArrayList<String>arrayList = new ArrayList<>();
    ArrayList<Integer> homes_number = new ArrayList<>();
    HashMap<Integer,String> hashMap = new HashMap<>();
    public static int Game_Id;//valued
    public ImageButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,
            btn15,btn16,btn17,btn18,btn19,btn20,btn21,btn22,btn23,btn24;

    public  ImageButton[] imageButtons = new ImageButton[]{btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12
            ,btn13,btn14,btn15,btn16,btn17,btn18,btn19,btn20,btn21,btn22,btn23,btn24
    };

    public Home homes[]=new Home[]{new Home(0,null),new Home(1,imageButtons[0]),new Home(2,imageButtons[1])
            ,new Home(3,imageButtons[2]),new Home(4,imageButtons[3]),new Home(5,imageButtons[4]),new Home(6,imageButtons[5]),
            new Home(7,imageButtons[6]),new Home(8,imageButtons[7]),new Home(9,imageButtons[8]),new Home(10,imageButtons[9])
            ,new Home(11,imageButtons[10]),new Home(12,imageButtons[11]),new Home(13,imageButtons[12]),new Home(14,imageButtons[13]),
            new Home(15,imageButtons[14]),new Home(16,imageButtons[15]),new Home(17,imageButtons[16]),new Home(18,imageButtons[17])
            ,new Home(19,imageButtons[18]),new Home(20,imageButtons[19]),new Home(21,imageButtons[20]),new Home(22,imageButtons[21]),
            new Home(23,imageButtons[22]),new Home(24,imageButtons[23])};

    Home b1[][]=new Home[][]{{homes[1],homes[2],homes[3]},{homes[4],homes[5],homes[6]},{homes[7],homes[8],homes[9]}};
    Home b2[][]=new Home[][]{{homes[1],homes[4],homes[7]},{homes[10],homes[11],homes[12]},{homes[22],homes[19],homes[16]}};
    Home b3[][]=new Home[][]{{homes[9],homes[6],homes[3]},{homes[13],homes[14],homes[15]},{homes[18],homes[21],homes[24]}};
    Home b4[][]=new Home[][]{{homes[16],homes[17],homes[18]},{homes[19],homes[20],homes[21]},{homes[22],homes[23],homes[24]}};
    Home barr[][][]=new Home[][][]{b1,b2,b3,b4};
    Home barr2[][]=new Home[3][3];
    TextView txt_notify ;
    ArrayList<Home> current_player_homes = new ArrayList<>();
    ArrayList<Home> other_player_homes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        connect_view_to_controller();
        imageButtons[0] = (ImageButton)findViewById(R.id.btn1);
        imageButtons[1]= (ImageButton)findViewById(R.id.btn2);
        getPlayers();
//        Bundle bundle = getIntent().getExtras();
//        current_player = (Player) bundle.getSerializable("current_player");
//        Bundle bundle2 = getIntent().getExtras();
//        other_player = (Player) bundle2.getSerializable("other_player");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (current_player == null){
            Toast.makeText(this,"Players are Null",Toast.LENGTH_SHORT).show();
            Log.i("payam", Integer.toString(current_player_id));
        }else {
            if (color.equals("W")){
                current_player.setColor("W");
                other_player.setColor("B");
            }else{
                current_player.setColor("B");
                other_player.setColor("W");
            }
        }
        if (imageButtons[0]==null){
            Toast.makeText(this,"Null",Toast.LENGTH_SHORT).show();
        }
        if (!turn){
            thread.start();
        }

        txt_notify = findViewById(R.id.txt_notify);
        txt_notify.setText(String.format("%s ,Put!","Run!"));

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[1]);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[2]);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[3]);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[4]);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[5]);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[6]);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[7]);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[8]);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[9]);
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[10]);
            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[11]);
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[12]);
            }
        });
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[13]);
            }
        });
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[14]);
            }
        });
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[15]);
            }
        });
        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[16]);
            }
        });
        btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[17]);
            }
        });
        btn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[18]);
            }
        });
        btn19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[19]);
            }
        });
        btn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[20]);
            }
        });
        btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[21]);
            }
        });
        btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[22]);
            }
        });
        btn23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[23]);
            }
        });
        btn24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(homes[24]);
            }
        });
    }

    public void connect_view_to_controller(){
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn10 = findViewById(R.id.btn10);
        btn11 = findViewById(R.id.btn11);
        btn12 = findViewById(R.id.btn12);
        btn13 = findViewById(R.id.btn13);
        btn14 = findViewById(R.id.btn14);
        btn15 = findViewById(R.id.btn15);
        btn16 = findViewById(R.id.btn16);
        btn17 = findViewById(R.id.btn17);
        btn18 = findViewById(R.id.btn18);
        btn19 = findViewById(R.id.btn19);
        btn20 = findViewById(R.id.btn20);
        btn21 = findViewById(R.id.btn21);
        btn22 = findViewById(R.id.btn22);
        btn23 = findViewById(R.id.btn23);
        btn24 = findViewById(R.id.btn24);
//        imageButtons[0] = btn1;
//        imageButtons[1] = btn2;
//        imageButtons[2] = btn3;
//        imageButtons[3] = btn4;
//        imageButtons[4] = btn5;
//        imageButtons[5] = btn6;
//        imageButtons[6] = btn7;
//        imageButtons[7] = btn8;
//        imageButtons[8] = btn9;
//        imageButtons[9] = btn10;
//        imageButtons[10] = btn11;
//        imageButtons[11] = btn12;
//        imageButtons[12] = btn13;
//        imageButtons[13] = btn14;
//        imageButtons[14] = btn15;
//        imageButtons[15] = btn16;
//        imageButtons[16] = btn17;
//        imageButtons[17] = btn18;
//        imageButtons[18] = btn19;
//        imageButtons[19] = btn20;
//        imageButtons[20] = btn21;
//        imageButtons[21] = btn22;
//        imageButtons[22] = btn23;
//        imageButtons[23] = btn24;

    }

    Thread send_Moves_thread = new Thread(new Runnable() {
        @Override
        public void run() {
            sendMoves(Game_Id);
        }
    });

    Thread send_Finish_Game = new Thread(new Runnable() {
        @Override
        public void run() {
            sendMoves(status,Game_Id);
        }
    });

    Thread set_Players_thread = new Thread(new Runnable() {
        @Override
        public void run() {
            setPlayers();
        }
    });

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            do {
                getMoves();
                try {
                    Log.i("MSG","Thread has slept");
                    thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while (changes == 0);
        }
    });

    Thread get_players_thread = new Thread(new Runnable() {
        @Override
        public void run() {
            getPlayers();
        }
    });

    public void process(Home home){
        if (!turn){
            return;
        }
        if (current_player.wanttodelete){
            delete(home);
        }else{
            if (current_player.getPfree()>0){
                //he wants to put a piece right here
                put(home);
            }else{
                //he wants to does moving action, satisfy here is origin or destination
                if (origin == null){
                    //means that here is origin
                    if (home.owner == current_player){
                        setOrigin(home);
                    }
                }else {
                    //means that here is destination
                    move(origin,home);
                }
            }
        }//end of two situation

    }

    public void deleteMovement(int btn_id){
        String color_Symbol = other_player.getColor();
        String s2 = color_Symbol+btn_id+",";
        arrayList.remove(s2);
    }

    public void inputMovement(int btn_id){
        String color_Symbol = current_player.getColor();
        String s2 = color_Symbol+btn_id+",";
        arrayList.add(s2);
    }

    public void getPlayers(){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Player> call = apiInterface.getPlayer(current_player_id);
        Log.i("MSG","Dar sendMoves(int id)");
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                if (response.isSuccessful()){
                    current_player = response.body();
                    Log.i("payam",response.body().getName());
                    Log.i("payam",current_player.getName());
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Log.i("MSG",t.getMessage());
            }
        });
        Call<Player> call2 = apiInterface.getPlayer(other_player_id);
        Log.i("MSG","Dar sendMoves(int id)");
        call2.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                if (response.isSuccessful()){
                    other_player = response.body();
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Log.i("MSG",t.getMessage());
            }
        });


    }

    public void setPlayers(){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<String> call = apiInterface.setPlayer(current_player.getId(),current_player);
        Log.i("MSG","Dar setPlayers(int id)");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("MSG",t.getMessage());
            }
        });
        Call<String> call2 = apiInterface.setPlayer(other_player.getId(),other_player);
        Log.i("MSG","Dar setPlayers(int id)");
        call2.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("MSG",t.getMessage());
            }
        });
    }

    public void getMoves(){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<String> call = apiInterface.getMoves(Game_Id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    String moves = response.body();
                    Log.i("MSG","Dar getPlayer,isSuccessful");
                    if (moves.equals(my_moves)){
                        return;
                    }
                    get_players_thread.start();
                    my_moves = moves;
                    String [] strings = my_moves.split(",");
                    arrayList.clear();
                    Collections.addAll(arrayList, strings);
                    homes_number.clear();
                    for (String string : strings) {
                        homes_number.add(Integer.valueOf(string.substring(1, string.length() - 1)));
                    }
                    for (int i = 0; i < homes_number.size(); i++) {
                        hashMap.put(homes_number.get(i),arrayList.get(i));
                    }
                    changes = 1;
                    turn = true;
                    updateBoard();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("MSG",t.getMessage());
            }
        });
    }

    public void sendMoves(int game_Id){
        set_Players_thread.start();
        turn = false;
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        my_moves = "";
        String s = "";
        for (int i = 0; i < arrayList.size(); i++) {
            s += arrayList.get(i);

        }
        my_moves = s;
        Log.i("MSG",my_moves);
        game.setState("running");
        game.setMoves(my_moves);
        Call<String> call = apiInterface.sendMoves(game_Id,game);
        Log.i("MSG","Dar sendMoves(int id)");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    txt_notify.setText(String.format("%s, wants to move...",other_player.getName()));
                    thread.start();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("MSG",t.getMessage());
            }
        });
    }

    public  void sendMoves(final int status , int game_Id){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        if (status == -1){
            game.setState("win");
        }else {
            game.setState("draw");
        }
        Call<String> call = apiInterface.sendMoves(game_Id,game);
        Log.i("MSG","Dar sendMoves(int id)");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    Intent intent = new Intent(GameActivity.this,AfterGame.class);
                    intent.putExtra("msg",response.message());
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("MSG",t.getMessage());
            }
        });
    }

    public void updateBoard(){//**we must use handler and message objects to change the ui...**
        for (int i = 0; i < homes_number.size(); i++) {
            int btn_id = homes_number.get(i);
            String color = hashMap.get(btn_id).substring(0,1);
            current_player_homes.clear();
            other_player_homes.clear();
            if(current_player.getColor().equals("W")){
                current_player_homes.add(homes[btn_id]);
            }else {
                other_player_homes.add(homes[btn_id]);
            }
            if (color.equals("W")){
                homes[btn_id].color = "W";
                homes[btn_id].isocc = true;
                homes[btn_id].owner = current_player.getColor().equals("W")?current_player:other_player;
                homes[btn_id].setPic(pic);

            }else if (color.equals("B")){
                homes[btn_id].color = "B";
                homes[btn_id].isocc = true;
                homes[btn_id].owner = current_player.getColor().equals("W")?current_player:other_player;
                homes[btn_id].setPic(pic);
            }
            checker();
            checkDraw();
        }
    }

    public void checkWin(){
        if(other_player.getPnum() < 3){
            status = -1;
            send_Finish_Game.start();
        }else{
            send_Moves_thread.start();
        }
    }

    public boolean AccessMove(){
        int turns;
        if (current_player_homes.isEmpty()) {
            return true;
        }
        //We can be sure that current player has house
        //We want to check all of his houses
        for (int m = 0; m < current_player_homes.size(); m++) {
            turns=0;
            for (int i = 0; i < 4; i++) {
                //Houses was divided into 4 arrays and we check all of them
                barr2=barr[i];
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        if (current_player_homes.get(m)==barr2[k][j]) {//processing on the a house starts
                            ArrayList<Home>b=new ArrayList<>();//to contain it's neighbours
                            try{
                                b.add(barr2[k-1][j]);
                            }catch(Exception ignored){
                            }
                            try{
                                b.add(barr2[k+1][j]);
                            }catch(Exception ignored){}
                            try{
                                b.add(barr2[k][j-1]);
                            }catch(Exception ignored){}
                            try{
                                b.add(barr2[k][j+1]);
                            }catch(Exception ignored){}
                            //Maximum of a house neighbours is 4 houses
                            //All of it's neighbours are saved to checked
                            for (int l = 0; l < 4; l++) {
                                try {
                                    if (!b.get(l).isocc) {
                                        //means that one house exists that's not occupied
                                        return true;
                                    }
                                } catch (Exception ignored) {}
                            }
                            //Whene compiler becomes here means that the house finded but It had no neighbours
                            //So it's essential to check another array of 4 array to check has any neighbour?
                            if (turns!=1) {
                                turns=1;
                            }
                        }
                    }
                }
            }
        }

        //means that current player has no neighbours that are non occupied
        status = -2;
        send_Finish_Game.start();
        return false;
    }

    public void checkDraw(){
        //must checks all housrs for put or delete possibility
        if (current_player.wanttodelete){
            //can he delete any house of other_player?
            int j = 0;
            for (int i = 0; i < other_player_homes.size(); i++) {
                if (other_player_homes.get(i).tic){
                    j++;
                }
            }
            if (j == other_player_homes.size()){
                status = -2;
                send_Finish_Game.start();
            }

        }else if (current_player.getPfree()==0){
            //can he move any house?
            //if he cannot, sendMoves(-2,Game_Id);
            AccessMove();

        }

    }//under construction

    public void checker(){
        for (int i = 0; i < 25; i++) {
            homes[i].tic=false;
        }
        for (int i = 0; i < 4; i++) {
            barr2= barr[i];
            for (int j = 0; j < 3; j++) {
                if(barr2[j][0].owner!=null){
                    if (barr2[j][0].owner==barr2[j][1].owner) {
                        if (barr2[j][1].owner==barr2[j][2].owner) {
                            barr2[j][0].tic=true;
                            barr2[j][1].tic=true;
                            barr2[j][2].tic=true;
                        }
                    }
                }
            }
            for (int j = 0; j < 3; j++) {
                if (barr2[0][j].owner!=null) {
                    if (barr2[0][j].owner==barr2[1][j].owner) {
                        if (barr2[1][j].owner==barr2[2][j].owner) {
                            barr2[0][j].tic=true;
                            barr2[1][j].tic=true;
                            barr2[2][j].tic=true;
                        }
                    }
                }
            }
        }
    }

    public boolean checkDooz(Home home){
        checker();
        for (int i = 0; i < 4; i++) {
            barr2= barr[i];
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (barr2[j][k] == home) {
                        //process
                        int n=0;
                        for (int l = 0; l < 3; l++) {
                            if (home.owner==barr2[j][l].owner) {
                                n++;
                            }
                        }
                        if (n==3) {
                            for (int l = 0; l < 3; l++) {
                                barr2[j][l].tic=true;
                            }
                            //music plays
                            //Time Increase
                            checkDraw();
                            current_player.wanttodelete = true;
                            txt_notify.setText(String.format("%s, Delete!",current_player));
                            return true;
                        }
                    }
                }
            }
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (barr2[k][j]== home) {
                        //proccess
                        int n=0;
                        for (int l = 0; l < 3; l++) {
                            if (barr2[l][j].owner == home.owner) {
                                n++;
                            }
                        }
                        if (n==3) {
                            for (int l = 0; l < 3; l++) {
                                barr2[l][j].tic=true;
                            }
                            //music plays
                            //Time Increase
                            txt_notify.setText(String.format("%s, Delete!",current_player));
                            current_player.wanttodelete = true;
                            return true;
                        }
                    }
                }
            }
        }
        send_Moves_thread.start();
        return false;
    }

    public boolean checkDelete(Home home){
        return home.owner == other_player && !home.tic;
    }

    public boolean checkMove(Home first, Home target){
        return !target.isocc;
    }

    public void delete(Home home){
        if (!checkDelete(home)){
            return;
        }
        current_player.wanttodelete = false;
        home.setPic(null);
        home.isocc = false;
        home.owner = null;
        other_player_homes.remove(home);
        other_player.setPnum(other_player.getPnum()-1);
        deleteMovement(home.getIndex());
        checkWin();
    }

    public void put(Home home){
        if (home.isocc){
            return;
        }
        home.isocc = true;
        home.owner = current_player;
        Toast.makeText(this,"home:"+home.getIndex()+","+home.owner.getName(),Toast.LENGTH_SHORT).show();
        home.setPic(pic);
        current_player_homes.add(home);
        current_player.setPfree(current_player.getPfree()-1);
        inputMovement(home.getIndex());
        checkDooz(home);
    }

    public void setOrigin(Home home){
        if (!home.isocc){
            return;
        }
        if (home.owner!=current_player){
            return;
        }
        origin = home;
        home.setPic(pic);

    }

    public void move(Home first, Home target){
        if (!checkMove(first,target)){
            return;
        }
        deleteMovement(first.getIndex());
        origin = null;
        inputMovement(target.getIndex());
        checkDooz(target);

    }

}