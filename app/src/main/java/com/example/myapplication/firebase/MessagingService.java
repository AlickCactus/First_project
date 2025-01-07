package com.example.myapplication.firebase;

import androidx.annotation.NonNull;
import com.google.firebase.messaging.FirebaseMessagingService;
import android.util.Log;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        // Log and toast
        Log.d("FCM Token", "Refreshed token: " + token);
        // Here you can send the token to your server or save it for later use
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage){
        super.onMessageReceived(remoteMessage);
        Log.d("FCM Token", "Message: " + remoteMessage.getNotification().getBody());
    }
}