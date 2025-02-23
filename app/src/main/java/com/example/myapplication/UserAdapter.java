package com.example.myapplication;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyRow> {
    private View.OnClickListener onClickListener;

    // надувает разметку для записи в RecyclerView
    @NonNull
    @Override
    public MyRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // какое количество раз будет вызван этот метод - ?
        Log.d("RRR","onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blank_user, parent,false);
        return new MyRow(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRow holder, int position) {
        Log.d("RRR","onBindViewHolder");
        holder.pn.setText(this.arrayList.get(position).getName());
        holder.lm.setText(this.arrayList.get(position).getMessage());
//        holder.itemView.setOnClickListener(view -> {
//            if (onClickListener != null){
//                onClickListener.onClick(position, );
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }

    class MyRow extends RecyclerView.ViewHolder {
        TextView pn, lm;
        public MyRow(@NonNull View itemView) {
            super(itemView);
            this.pn = itemView.findViewById(R.id.person_name);
            this.lm = itemView.findViewById(R.id.last_message);
        }
    }

    public ArrayList<ModelUsers> arrayList;

    public UserAdapter(ArrayList<ModelUsers> arrayList) {
        this.arrayList = arrayList;
    }
}