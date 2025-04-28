package com.example.myapplication;


public class ModelUsers {

    String name;
    String email;
    String uid;

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    //важно назвать переменную(Uid) так же, как и ту, что будешь запихивать(нормального гетера-сетера тоже касается >:( )


    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public ModelUsers(){
    }

    public ModelUsers(String name, String email, String uid) {
        this.name = name;
        this.email = email;
        this.uid = uid;
    }


    @Override
    public String toString() {
        return "ModelUsers{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
