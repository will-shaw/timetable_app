package com.seng.timetableapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TimetableActivity extends AppCompatActivity {

    private TextView txtUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        txtUser = (TextView) findViewById(R.id.txtUsername);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        txtUser.setText(username);
    }
}
