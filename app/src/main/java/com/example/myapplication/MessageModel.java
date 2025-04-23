package com.example.myapplication;

import com.google.firebase.Timestamp;

public class MessageModel {
    private String message;
    private String username;
    private String type;
    private String receiver;
//    private Timestamp timestamp;

    public MessageModel(){

    }

    public MessageModel(String message, String receiver, String username, String type){
        this.message = message;
        this.username = username;
        this.receiver = receiver;
        this.type = type;

//        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiver(){
        return receiver;
    }

    public void setReceiver(){
        this.receiver = receiver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public Timestamp getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(Timestamp timestamp) {
//        this.timestamp = timestamp;
//    }
}
