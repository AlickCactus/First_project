package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatsMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chats_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        RecyclerView recyclerView = findViewById(R.id.scroll);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new UserAdapter(this, generateList()));

        ImageButton search_button = (ImageButton) findViewById(R.id.search_icon);
        ImageButton settings_button = (ImageButton) findViewById(R.id.settings_icon);

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChatsMenu.this, SearchActiviry.class);
                startActivity(intent);
                finish();
            }
        });

        settings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChatsMenu.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private final String name_list[] = {
            "Arias",
    };

    private final String email_list[] = {
            "sm@yandex.ru",
    };

    private final String uid[] = {
            "111",
    };

    public ArrayList<ModelUsers> generateList(){
        ArrayList<ModelUsers> arrayList = new ArrayList<>();
        for(int i=0;i<name_list.length;i++) {
            arrayList.add(new ModelUsers(name_list[i], email_list[i], uid[i]));
        }
        return arrayList;
    }
}