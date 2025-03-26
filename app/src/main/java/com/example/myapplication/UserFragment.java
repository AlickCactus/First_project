package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class UserFragment extends Fragment {

    private RecyclerView recyclerView;

    private UserAdapter userAdapter;
    private ArrayList<ModelUsers> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.blank_user, container, false);
//
//        recyclerView = view.findViewById(R.id.scroll);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        arrayList = new ArrayList<>();
//
        return view;
    }

    private void readUsers(){

        FirebaseUser mAuth = FirebaseAuth.getInstance().getCurrentUser();
        

    }
}
