package com.example.myapplication;

import com.google.firebase.Timestamp;

public class MessageModel {
    private String message;
    private String username;
    private Timestamp timestamp;

    public MessageModel(){

    }

    public MessageModel(String message, String username, Timestamp timestamp){
        this.message = message;
        this.username = username;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
