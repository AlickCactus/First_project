package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyRow> {

    private static int MSG_TYPE_LEFT = 0;
    private static int MSG_TYPE_RIGHT = 1;
    private ArrayList<MessageModel> arrayList;
    private Context context;


    public ChatAdapter(Context context, ArrayList<MessageModel> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatAdapter.MyRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_LEFT){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.their_message, parent,false);
            return new ChatAdapter.MyRow(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_message, parent,false);
            return new ChatAdapter.MyRow(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyRow holder, int position){
        String message = arrayList.get(position).getMessage();
        holder.message.setText(message);
    }

    class MyRow extends RecyclerView.ViewHolder{
        TextView message;

        public MyRow(@NonNull View itemView) {
            super(itemView);
            this.message = itemView.findViewById(R.id.message_body);
        }

    }

    public int getItemCount() {
        return this.arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (arrayList.get(position).getSender().equals(firebaseUser.getUid())){
            return MSG_TYPE_RIGHT;
        }else{
            return MSG_TYPE_LEFT;
        }
    }

}
