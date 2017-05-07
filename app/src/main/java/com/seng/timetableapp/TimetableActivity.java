package com.seng.timetableapp;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class TimetableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        TextView lblTodayDate = (TextView) findViewById(R.id.lbl_today_date);
        TextView lblTomorrowDate = (TextView) findViewById(R.id.lbl_tomorrow_date);
        LinearLayout lstToday = (LinearLayout) findViewById(R.id.lst_today);
        LinearLayout lstTomorrow = (LinearLayout) findViewById(R.id.lst_tomorrow);
        Toolbar app_bar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(app_bar);

        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);

        lblTodayDate.setText(dt.toString().substring(0, 10));
        dt = c.getTime();
        lblTomorrowDate.setText(dt.toString().substring(0, 10));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Toolbar app_bar = (Toolbar) findViewById(R.id.app_bar);
        app_bar.inflateMenu(R.menu.menu_main);
        app_bar.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return onOptionsItemSelected(item);
                    }
                });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.more_vert:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.search:
                Toast.makeText(this, "Search selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.refresh:
                Toast.makeText(this, "Refresh selected", Toast.LENGTH_SHORT)
                    .show();
                break;

            default:
                break;
        }
        return true;
    }

}


