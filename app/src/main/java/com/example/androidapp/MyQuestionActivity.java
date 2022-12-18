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

public class MyQuestionActivity extends AppCompatActivity {

        TextView txt;
        DBUser DBAnswers;
        ArrayList<String> pollId;
        ArrayList<String> userName;
        UserAdapter userAdapter;
        RecyclerView recyclerView;
        String username;
        ArrayList<String> pollName;
        ArrayList<Integer> pollTime;
        DBHelperPolls DBPolls;
        ArrayList<String> pollNamePOM;
        Boolean flag;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_my_question);
            txt = (TextView) findViewById(R.id.textView);
            recyclerView = new RecyclerView(this);
            DBAnswers = new DBUser(this);
            DBPolls = new DBHelperPolls(this);
            pollId = new ArrayList<String>();
            userName = new ArrayList<String>();
            pollTime = new ArrayList<Integer>();
            pollName = new ArrayList<String>();
            pollNamePOM = new ArrayList<String>();
            flag = false;
            recyclerView = findViewById(R.id.recyclerView);

            Intent i = getIntent();
            username = (String) i.getSerializableExtra("userName");
            storeDataInArrays();

            for (int j = 0; j < pollName.size() - 1; j++) {
                flag = false;
                for (int k = j; k < pollName.size(); k++) {
                    if (pollName.get(j) == pollName.get(k)) {
                        pollName.remove(k);
                    }
                }
            }
            for (int j = 0; j < pollId.size() - 1; j++) {
                flag = false;
                for (int k = j; k < pollId.size(); k++) {
                    if (pollId.get(j) == pollId.get(k)) {
                        pollId.remove(k);
                    }
                }
            }



            storeDataInArraysPollName();

            userAdapter = new UserAdapter(MyQuestionActivity.this, pollId, pollName, null, null, username);
            recyclerView.setAdapter(userAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(MyQuestionActivity.this));

            recyclerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), MyAnswerActivity.class);
                    startActivity(intent);
                }
            });
            txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
            for(int m = 0; m < pollName.size(); m++) {
                Log.d("POLLLLLL ", pollName.get(m));
            }
        }

        void storeDataInArrays() {
            Cursor cursor = DBAnswers.readAllDataByUserName(username);

            if(cursor.getCount() == 0) {
                Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
            } else {
                while(cursor.moveToNext()) {
                    pollId.add(cursor.getString(5));
                }
            }
        }

        void storeDataInArraysPollName() {
            for(int i = 0; i < pollId.size(); i++) {
                Cursor cursor = DBPolls.readPollNameFromPollsByIdDistinct(pollId.get(i));

                if(cursor.getCount() == 0) {
                    Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
                } else {
                    while(cursor.moveToNext()) {
                        pollName.add(cursor.getString(0));
                    }
                }
            }
        }

    }