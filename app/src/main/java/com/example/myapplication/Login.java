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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText user_email=(EditText) findViewById(R.id.email);
        EditText user_password=(EditText) findViewById(R.id.password);
        Button button_login=(Button) findViewById(R.id.button_login);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = user_email.getText().toString();
                password = user_password.getText().toString();

                if (email.isEmpty()){
                    Toast.makeText(Login.this, "Заполните почту", Toast.LENGTH_SHORT).show();
                }

                else if (password.isEmpty()){
                    Toast.makeText(Login.this, "Заполните пароль", Toast.LENGTH_SHORT).show();
                }

                else{
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Login.this, "Вы зашли",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Login.this, ChatsMenu.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(Login.this, "Вы не зашли",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

//        button_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String email, password;
//                email = user_email.getText().toString();
//                password = user_password.getText().toString();
//
//                if (email.isEmpty()){
//                    Toast.makeText(Login.this, "Заполните почту", Toast.LENGTH_SHORT).show();
//                }
//
//                else if (password.isEmpty()){
//                    Toast.makeText(Login.this, "Заполните пароль", Toast.LENGTH_SHORT).show();
//                }
//
//                else{
//                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
//                    mAuth.signInWithEmailAndPassword(email, password)
//                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<AuthResult> task) {
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(Login.this, "Вы зашли",
//                                                Toast.LENGTH_SHORT).show();
//                                        Intent intent = new Intent(Login.this, ChatsMenu.class);
//                                        startActivity(intent);
//                                        finish();
//                                    } else {
//                                        Toast.makeText(Login.this, "Вы не зашли",
//                                                Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//                }
//            }
//        });
    }
}