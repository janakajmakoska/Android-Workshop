package com.example.androidapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAnswerActivity extends AppCompatActivity {

    TextView txt;
    DBUser DBAnswers;
    AnswerAdapter AnswerAdapter;
    RecyclerView recyclerView;
    String username,poll_id;
    ArrayList<String> question,answer,date;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_answer);
        txt = (TextView) findViewById(R.id.textView);
        recyclerView = new RecyclerView(this);
        DBAnswers = new DBUser(this);
        question = new ArrayList<String>();
        answer = new ArrayList<String>();
        date= new ArrayList<String>();
        recyclerView = findViewById(R.id.recyclerView);

        Intent i = getIntent();
        username = (String) i.getSerializableExtra("userName");
        poll_id=(String) i.getSerializableExtra("pollId");
        storeDataInArrays();

        txt=(TextView) findViewById(R.id.textView);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });




        AnswerAdapter= new AnswerAdapter(MyAnswerActivity.this, question, answer, date, username,null,false);
        recyclerView.setAdapter(AnswerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyAnswerActivity.this));

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyAnswerActivity.class);
                startActivity(intent);
            }
        });


    }

    void storeDataInArrays() {
        Cursor cursor = DBAnswers.readAllDataByPollIDAndUsername(poll_id,username);

        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()) {
                question.add(cursor.getString(2));
                answer.add(cursor.getString(3));
                date.add(cursor.getString(4));
            }
        }
    }



}


