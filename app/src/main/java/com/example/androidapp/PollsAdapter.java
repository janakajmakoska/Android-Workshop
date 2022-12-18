package com.example.androidapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PollsAdapter extends RecyclerView.Adapter<PollsAdapter.MyViewHolder> {

    Context context;
    ArrayList poll_id, poll_name, poll_time, poll_btn;
    String userName;

    PollsAdapter(Context context, ArrayList poll_id, ArrayList poll_name, ArrayList poll_time, ArrayList poll_btn,String userName) {
        this.context = context;
        this.poll_id = poll_id;
        this.poll_name = poll_name;
        this.poll_time = poll_time;
        this.poll_btn = poll_btn;
        this.userName=userName;
    }

    @NonNull
    @Override
    public PollsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PollsAdapter.MyViewHolder holder, int position) {
        holder.poll_name_txt.setText(String.valueOf(poll_name.get(position)));
        holder.poll_btn_txt.setText("Start");

        holder.poll_btn_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer pom = (Integer) holder.getAdapterPosition();
                String pollIdPom = (String) getPoll_id().get(pom);
                Intent intent = new Intent(view.getContext(), QuestionViewActivity.class);
                intent.putExtra("pollId", pollIdPom);
                intent.putExtra("userName",userName);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return poll_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView poll_name_txt, poll_btn_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            poll_name_txt = itemView.findViewById(R.id.poll_name);
            poll_btn_txt = itemView.findViewById(R.id.button_poll);
        }
    }

    public ArrayList getPoll_id() {
        return poll_id;
    }

    public void setPoll_id(ArrayList poll_id) {
        this.poll_id = poll_id;
    }
}
