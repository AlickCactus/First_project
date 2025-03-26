package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        ModelUsers user = arrayList.get(position);
        holder.pn.setText(this.arrayList.get(position).getName());
        holder.lm.setText(this.arrayList.get(position).getMessage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChatRoom.class);
                intent.putExtra("userid", user.getUid());
                context.startActivity(intent);
            }
        });
    }

    class MyRow extends RecyclerView.ViewHolder{
        TextView pn, lm;

        public MyRow(@NonNull View itemView) {
            super(itemView);
            this.pn = itemView.findViewById(R.id.person_name);
            this.lm = itemView.findViewById(R.id.last_message);
        }

    }

    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }
}