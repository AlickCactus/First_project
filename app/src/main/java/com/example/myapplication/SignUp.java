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

        EditText username = (EditText) findViewById(R.id.username);
        EditText user_email = (EditText) findViewById(R.id.email);
        EditText user_password = (EditText) findViewById(R.id.password);
        EditText user_repassword = (EditText) findViewById(R.id.repassword);
        Button register = (Button) findViewById(R.id.button_register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password, repassword;
                email = user_email.getText().toString();
                password = user_password.getText().toString();
                repassword = user_repassword.getText().toString();

                if (password.isEmpty()){
                    Toast.makeText(SignUp.this, "Заполните пароль", Toast.LENGTH_SHORT).show();
                }

                else if (!password.equals(repassword)){
                    Toast.makeText(SignUp.this, "Не совпадают пароли", Toast.LENGTH_SHORT).show();
                }

                else{
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignUp.this, "Вы зарегестрировалась",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SignUp.this, ChatRoom.class);
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
        });
    }
}