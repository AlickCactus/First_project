package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatRoom extends AppCompatActivity {

    private ArrayList message_list;
    private RecyclerView recyclerView;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat_room);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText message_text = findViewById(R.id.msgText);
        ImageButton btn_send = findViewById(R.id.btn_send_message);

        recyclerView = findViewById(R.id.messages_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String receiverid = intent.getStringExtra("uid");

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = message_text.getText().toString();
                if (!msg.isEmpty()){
                    sendMessage(msg, receiverid, firebaseUser.getUid());
                }
                message_text.setText("");
            }
        });
    }

    private void sendMessage(String message, String receiver, String sender){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("message", message);

        reference.child("Chats").push().setValue(hashMap);
    }




//    private void readMessage(){
//        message_list = new ArrayList();
//        DatabaseReference mdata = FirebaseDatabase.getInstance().getReference().child("Chats");
//        mdata.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                message_list.clear();
//                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
//                    MessageModel messageModel = dataSnapshot.getValue(MessageModel.class);
//
//                    firebaseAuth = FirebaseAuth.getInstance();
//                    Intent intent = getIntent();
//
//                    String receiveruid = intent.getStringExtra("userid");
//                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//                    String useruid = firebaseUser.getUid();
//
//                    if (messageModel.getUsername().equals(useruid) && messageModel.getReceiver().equals(receiveruid) ||
//                            messageModel.getUsername().equals(receiveruid) && messageModel.getReceiver().equals(useruid)){
//                        message_list.add(messageModel);
//                    }
//
//                    ChatAdapter chatAdapter = new ChatAdapter(ChatRoom.this, message_list);
//                    chatAdapter.notifyDataSetChanged();
//                    recyclerView.setAdapter(chatAdapter);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {}
//        });
//    }
//
//    private void sendMessage(String message){
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//
//        Intent intent = getIntent();
//        String receiveruid = intent.getStringExtra("uid");
//        firebaseAuth = FirebaseAuth.getInstance();
//        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//        String useruid = firebaseUser.getUid();
//
//        HashMap<String, Object> hashMap = new HashMap<>();
//        hashMap.put("username", useruid);
//        hashMap.put("receiver", receiveruid);
//        hashMap.put("message", message);
//        databaseReference.child("Chats").push().setValue(hashMap);
//
//        DatabaseReference receivechats = FirebaseDatabase.getInstance().getReference("ChatList").child(receiveruid).child(useruid);
//        receivechats.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (!snapshot.exists()){
//                    receivechats.child("id").setValue(useruid);
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {}
//        });
//
//        DatabaseReference userchats = FirebaseDatabase.getInstance().getReference("ChatList").child(useruid).child(receiveruid);
//        userchats.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (!snapshot.exists()){
//                    userchats.child("id").setValue(receiveruid);
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {}
//        });
//    }
}