package com.example.myapplication;

public class ModelUsers {

    String name;
//    String uid;
    String message;

    public String getName(){
        return name;
    }

    public String getMessage(){
        return message;
    }

    public ModelUsers(String name, String message){
        this.name = name;
        this.message = message;
//        this.uid = uid;
    }

//    public String getUid(){
//        return uid;
//    }

}