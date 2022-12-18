package com.example.androidapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.MyViewHolder> {

    Context context;
    ArrayList questionid, answer,poll_time,users;
    String userName;
    DBUser DBAnswers;
    Boolean hasAdminClicked=false;



    AnswerAdapter(Context context, ArrayList questionid, ArrayList answer, ArrayList poll_time, String userName,ArrayList users, Boolean hasAdminClicked) {
        this.context = context;
        this.questionid = questionid;
        this.answer = answer;
        this.poll_time = poll_time;
        this.userName=userName;
        this.users=users;
        this.hasAdminClicked=hasAdminClicked;
    }

    @NonNull
    @Override
    public AnswerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.myrow1, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerAdapter.MyViewHolder holder, int position) {
        holder.questionid_txt.setText(String.valueOf(questionid.get(position)));
        holder.answer_txt.setText(String.valueOf(answer.get(position)));
        holder.poll_time_txt.setText(String.valueOf(poll_time.get(position)));
        if(!hasAdminClicked) {
            holder.userName_txt.setText(userName);
        }
        else
        {
            holder.userName_txt.setText(String.valueOf(users.get(position)));
        }

    }

    @Override
    public int getItemCount() {
        return questionid.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView questionid_txt, answer_txt,poll_time_txt,userName_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            questionid_txt = itemView.findViewById(R.id.question_name);
            answer_txt = itemView.findViewById(R.id.answer);
            poll_time_txt=itemView.findViewById(R.id.poll_time);
            userName_txt=itemView.findViewById(R.id.username);

        }
    }





}

