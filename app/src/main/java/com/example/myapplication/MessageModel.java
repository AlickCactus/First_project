package com.example.myapplication;


public class MessageModel {
    private String message;
    private String sender;
    private String receiver;

    public MessageModel(){

    }

    public MessageModel(String message, String receiver, String sender){
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
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

    public String getSender() {
        return sender;
    }

    public void setSender(String username) {
        this.sender = username;
    }


}
