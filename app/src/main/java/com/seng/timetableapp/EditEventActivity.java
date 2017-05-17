package com.seng.timetableapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;

import domain.TTEvent;

public class EditEventActivity extends AppCompatActivity {

    private TTEvent ttEvent;
    private AutoCompleteTextView pPaper;
    private AutoCompleteTextView pName;
    private AutoCompleteTextView pRoom;
    private AutoCompleteTextView pBuilding;

    private final int RESULT_DELETE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        pPaper = (AutoCompleteTextView) findViewById(R.id.edit_text_paper);
        pName = (AutoCompleteTextView) findViewById(R.id.edit_text_paperName);
        pRoom = (AutoCompleteTextView) findViewById(R.id.edit_text_room);
        pBuilding = (AutoCompleteTextView) findViewById(R.id.edit_text_building);

        if (getIntent().hasExtra("ttEvent")) {
            ttEvent = (TTEvent) getIntent().getSerializableExtra("ttEvent");
            fillDetails();
        } else {
            ttEvent = new TTEvent();
        }

        if (getSupportActionBar() != null) {
            if (getIntent().hasExtra("title")) {
                getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
            } else {
                getSupportActionBar().setTitle("Edit " + ttEvent.getId());
            }
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
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
                data.putExtra("returnEvent", ttEvent);
                setResult(RESULT_OK, data);
                this.finish();
                break;

            case R.id.delete:
                setResult(RESULT_DELETE);
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
