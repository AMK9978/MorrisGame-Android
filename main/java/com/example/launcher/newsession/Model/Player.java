package com.example.launcher.newsession.Model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable{

    public Player(int age, String name, String email, String password, String description) {
        this.age = age;
        this.name = name;
        this.email = email;
        this.password = password;
        this.description = description;
    }

    @SerializedName("age")
    private int age;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("color")
    private String color;
    @SerializedName("state")
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @SerializedName("description")

    private String description;
    @SerializedName("id")
    private int id;
    @SerializedName("pfree")
    private int pfree = 12;
    @SerializedName("pnum")
    private int pnum = 12;
    @SerializedName("score")
    public int score;



    public int getPfree() {
        return pfree;
    }

    public void setPfree(int pfree) {
        this.pfree = pfree;
    }

    public int getPnum() {
        return pnum;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public boolean wanttodelete=false;
//    int ps[]=new int[]{0,0};
//    public int ad=0;
//    Home b1[][]=new Home[][]{{Home.homes[1],Home.homes[2],Home.homes[3]},{Home.homes[4],Home.homes[5],Home.homes[6]},{Home.homes[7],Home.homes[8],Home.homes[9]}};
//    Home b2[][]=new Home[][]{{Home.homes[1],Home.homes[4],Home.homes[7]},{Home.homes[10],Home.homes[11],Home.homes[12]},{Home.homes[22],Home.homes[19],Home.homes[16]}};
//    Home b3[][]=new Home[][]{{Home.homes[9],Home.homes[6],Home.homes[3]},{Home.homes[13],Home.homes[14],Home.homes[15]},{Home.homes[18],Home.homes[21],Home.homes[24]}};
//    Home b4[][]=new Home[][]{{Home.homes[16],Home.homes[17],Home.homes[18]},{Home.homes[19],Home.homes[20],Home.homes[21]},{Home.homes[22],Home.homes[23],Home.homes[24]}};
//    Home barr[][][]=new Home[][][]{b1,b2,b3,b4};
//    Home barr2[][]=new Home[3][3];

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
//
//    public ArrayList<Home> houses=new ArrayList();
//    public ArrayList<Home> houses2=new ArrayList();
//
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    

}
