package com.seng.timetableapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ViewEventActivity extends AppCompatActivity {

    private String paper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        paper = getIntent().getStringExtra("paper");
        if (getSupportActionBar() != null && paper != null) {
            getSupportActionBar().setTitle("View " + paper);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {

            case R.id.edit:
                Intent intent = new Intent(ViewEventActivity.this, EditEventActivity.class);
                // TODO: Pass through either TTEvent, or just the ID for the next activity to pull.
                intent.putExtra("paper", paper);
                startActivity(intent);
                break;

            case android.R.id.home:
                this.finish();
                break;

            default:
                break;
        }
        return true;
    }

}
