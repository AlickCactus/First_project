package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyRow>{

    private ArrayList<ModelUsers> arrayList;
    private Context context;


    public UserAdapter(Context context, ArrayList<ModelUsers> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    // надувает разметку для записи в RecyclerView
    @NonNull
    @Override
    public MyRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blank_user, parent,false);
        return new UserAdapter.MyRow(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRow holder, int position) {
        String name = this.arrayList.get(position).getName();
        holder.pn.setText(name);
        holder.lm.setText(this.arrayList.get(position).getEmail());
        String uid = this.arrayList.get(position).getUid();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChatRoom.class);
                intent.putExtra("uid", uid);
                intent.putExtra("name", name);
                context.startActivity(intent);
            }
        });
    }

    class MyRow extends RecyclerView.ViewHolder{
        TextView pn, lm;

        public MyRow(@NonNull View itemView) {
            super(itemView);
            this.pn = itemView.findViewById(R.id.personname);
            this.lm = itemView.findViewById(R.id.user_email);
        }

    }

    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }
}