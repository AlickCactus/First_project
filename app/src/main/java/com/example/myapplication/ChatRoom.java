package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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

    private ChatAdapter chatAdapter;
    private ArrayList<MessageModel> messageList;
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
        TextView chat_room_name = findViewById(R.id.chat_room_name);

        Intent intent = getIntent();
        String receiverid = intent.getStringExtra("uid");
        String username = intent.getStringExtra("name");
        chat_room_name.setText(username);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        recyclerView = findViewById(R.id.messages_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

        readMessage(receiverid, firebaseUser.getUid());
        
    }

    private void sendMessage(String message, String receiver, String sender){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("message", message);

        reference.child("Chats").push().setValue(hashMap);
        
    }

    private void readMessage(String receiver, String sender){
        messageList = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageList.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    MessageModel messageModel = snapshot1.getValue(MessageModel.class);
                    if (messageModel.getSender().equals(sender) && messageModel.getReceiver().equals(receiver) ||
                            messageModel.getSender().equals(receiver) && messageModel.getReceiver().equals(sender)){
                        messageList.add(messageModel);
                    }

                    chatAdapter = new ChatAdapter(ChatRoom.this, messageList);
                    chatAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(chatAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}