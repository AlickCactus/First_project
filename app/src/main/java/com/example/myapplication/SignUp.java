package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;


public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText user_name = (EditText) findViewById(R.id.username);
        EditText user_email = (EditText) findViewById(R.id.email);
        EditText user_password = (EditText) findViewById(R.id.password);
        EditText user_repassword = (EditText) findViewById(R.id.repassword);
        Button register = (Button) findViewById(R.id.button_register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password, repassword, username;
                email = user_email.getText().toString();
                password = user_password.getText().toString();
                repassword = user_repassword.getText().toString();
                username = user_name.getText().toString();

                if (password.isEmpty()){
                    Toast.makeText(SignUp.this, "Заполните пароль", Toast.LENGTH_SHORT).show();
                }

                else if (!password.equals(repassword)){
                    Toast.makeText(SignUp.this, "Не совпадают пароли", Toast.LENGTH_SHORT).show();
                }

                else{
                    addToFireAuth(email, password, username);
                }
            }
        });
    }

    private void addToFireAuth(String email, String password, String name){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "Вы зарегестрировалась",
                                    Toast.LENGTH_SHORT).show();

                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            String uid = task.getResult().getUser().getUid();
                            CollectionReference ref = db.collection("usersCollection");
                            HashMap<String, String> user = new HashMap<>();
                            user.put("name", name);
                            user.put("email", email);
                            user.put("uid", uid);
                            ref.add(user);

//                            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("PersonData");
//                            ModelUsers modelUsers = new ModelUsers();
//                            modelUsers.setName(name);
//                            modelUsers.setEmail(email);
//                            modelUsers.setId(uid);
//                            mDatabase.push().setValue(modelUsers).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void unused) {
//                                    Toast.makeText(SignUp.this, "Data added successfully!", Toast.LENGTH_SHORT).show();
//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Toast.makeText(SignUp.this, "Failed to add data", Toast.LENGTH_SHORT).show();
//                                }
//                            });

                            Intent intent = new Intent(SignUp.this, ChatsMenu.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(SignUp.this, "Вы не зарегестрировались",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}