package com.seng.timetableapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class EditEventActivity extends AppCompatActivity {

    private String paper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        paper = getIntent().getStringExtra("paper");
        if (getSupportActionBar() != null && paper != null) {
            getSupportActionBar().setTitle("View " + paper);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {

            case R.id.save:
                // TODO: Add event saving code.
                this.finish();
                break;

            case R.id.discard:
            case android.R.id.home:
                this.finish();
                break;

            default:
                break;
        }
        return true;
    }

}
