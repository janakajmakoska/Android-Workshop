package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminAnswerActivity extends AppCompatActivity {
    TextView txt;
    DBUser DBAnswers;
    AnswerAdapter AnswerAdapter;
    RecyclerView recyclerView;
    String username,poll_id;
    ArrayList<String> question,answer,date,users;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_answer);
        recyclerView = new RecyclerView(this);
        DBAnswers = new DBUser(this);
        question = new ArrayList<String>();
        answer = new ArrayList<String>();
        users = new ArrayList<String>();
        date= new ArrayList<String>();
        recyclerView = findViewById(R.id.recyclerView5);

        Intent i = getIntent();
        username = (String) i.getSerializableExtra("userName");
        poll_id=(String) i.getSerializableExtra("pollId");
        storeDataInArrays();

        txt=(TextView) findViewById(R.id.textView5);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });




        AnswerAdapter= new AnswerAdapter(AdminAnswerActivity.this, question, answer, date, "",users,true);
        recyclerView.setAdapter(AnswerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminAnswerActivity.this));

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyAnswerActivity.class);
                startActivity(intent);
            }
        });


    }

    void storeDataInArrays() {
        Cursor cursor = DBAnswers.readAllDataFromPollsById(poll_id);

        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()) {
                users.add(cursor.getString(1));
                question.add(cursor.getString(2));
                answer.add(cursor.getString(3));
                date.add(cursor.getString(4));
            }
        }
    }



}