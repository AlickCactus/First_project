package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        EditText username=(EditText) findViewById(R.id.username);
        EditText email=(EditText) findViewById(R.id.email);
        EditText password=(EditText) findViewById(R.id.password);
        EditText repassword=(EditText) findViewById(R.id.repassword);

        Button button_register=(Button) findViewById(R.id.button_register);

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(repassword.getText().toString() == password.getText().toString() && password.getText().toString().length() != 0){
                    boolean correct_password = true;

                    for (int i = 0; i < password.getText().toString().length(); i++) {
                        char s = password.getText().toString().charAt(i);
                        if (!(Character.isLetter(s) || Character.isDigit(s))) {
                            correct_password = false;
                            Toast.makeText(SignUp.this, "В пароле должны быть только цифры и буквы", Toast.LENGTH_SHORT).show();
                        }
                    }

                    if (correct_password){
                        //переход на др страницу
                        Button button_register = findViewById(R.id.button_register);
                        button_register.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(SignUp.this, ChatRoom.class);
                                startActivity(intent);
                            }
                        });
                    }
                    
                }else if(username.getText().toString().length() == 0 || repassword.getText().toString().length() == 0 || email.getText().toString().length() == 0){
                    Toast.makeText(SignUp.this, "Заполните до конца", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}