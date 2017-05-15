package com.seng.timetableapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;

import java.io.IOException;
import java.sql.Time;

import dao.TimetableDAO;
import domain.TTEvent;

public class EditEventActivity extends AppCompatActivity {

    private TTEvent ttEvent;
    private AutoCompleteTextView pPaper;
    private AutoCompleteTextView pName;
    private AutoCompleteTextView pRoom;
    private AutoCompleteTextView pBuilding;
    private TimetableDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        ttEvent = (TTEvent) getIntent().getSerializableExtra("ttEvent");

        dao = new TimetableDAO(this);

        pPaper = (AutoCompleteTextView) findViewById(R.id.edit_text_paper);
        pName = (AutoCompleteTextView) findViewById(R.id.edit_text_paperName);
        pRoom = (AutoCompleteTextView) findViewById(R.id.edit_text_room);
        pBuilding = (AutoCompleteTextView) findViewById(R.id.edit_text_building);

        if (ttEvent != null) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("View " + ttEvent.getId());
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            fillDetails();
        }
    }

    private void fillDetails() {
        pPaper.setText(ttEvent.getId());
        pName.setText(ttEvent.getPaperName());
        pRoom.setText(ttEvent.getRoomCode());
        pBuilding.setText(ttEvent.getBuildingName());
    }

    private void updateDetails() {
        ttEvent.setId(pPaper.getText().toString());
        ttEvent.setPaperName(pName.getText().toString());
        ttEvent.setRoomCode(pRoom.getText().toString());
        ttEvent.setBuildingName(pBuilding.getText().toString());
        dao.save(ttEvent);
        try {
            dao.saveTimeTable();
            Snackbar.make(getWindow().getDecorView().getRootView(), "Saved", Snackbar.LENGTH_SHORT)
                    .show();
        } catch (IOException e) {
            Snackbar.make(getWindow().getDecorView().getRootView(), "Cannot save :(", Snackbar.LENGTH_SHORT)
                    .show();
        }
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
                Intent data = new Intent();
                updateDetails();
                data.putExtra("ttEvent", ttEvent);
                setResult(RESULT_OK, data);
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
