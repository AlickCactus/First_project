package com.example.myapplication;


public class ModelUsers {

    String name;
    String email;
    String id;

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getId() {
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setId(String id){
        this.id = id;
    }

    public ModelUsers(){
    }

    public ModelUsers(String name, String email, String id){
        this.name = name;
        this.email = email;
        this.id = id;
    }
}