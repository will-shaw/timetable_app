package com.seng.timetableapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import dao.TimetableDAO;
import domain.TTEvent;

public class EditEventActivity extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private TTEvent ttEvent;
    private AutoCompleteTextView pPaper;
    private AutoCompleteTextView pName;
    private AutoCompleteTextView pRoom;
    private AutoCompleteTextView pBuilding;
    private EditText editText;

    private String message = "Cannot save :(";

    private int year = -1, month = -1, day = -1, hour = -1, minute = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        pPaper = (AutoCompleteTextView) findViewById(R.id.edit_text_paper);
        pName = (AutoCompleteTextView) findViewById(R.id.edit_text_paperName);
        pRoom = (AutoCompleteTextView) findViewById(R.id.edit_text_room);
        pBuilding = (AutoCompleteTextView) findViewById(R.id.edit_text_building);
        editText = (EditText) findViewById(R.id.edit_text_date);

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
                getSupportActionBar().setTitle("Edit " + ttEvent.getLectureName());
            }
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void showDatePickerDialog(@SuppressWarnings("UnusedParameters") View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void fillDetails() {
        pPaper.setText(ttEvent.getLectureName());
        pName.setText(ttEvent.getPaperName());
        pRoom.setText(ttEvent.getRoomCode());
        pBuilding.setText(ttEvent.getBuildingName());
        editText.setText(ttEvent.getDate().getTime().toString());
    }

    private void updateDetails() {
        if (ttEvent.getId() == null) {
            ttEvent.setId(TimetableDAO.genUniqueID());
        }
        ttEvent.setLectureName(pPaper.getText().toString());
        ttEvent.setPaperName(pName.getText().toString());
        ttEvent.setRoomCode(pRoom.getText().toString());
        ttEvent.setBuildingName(pBuilding.getText().toString());
        if (year != -1 && month != -1 && day != -1 && hour != -1 && minute != -1) {
            Calendar cal = Calendar.getInstance();
            cal.set(year, month, day, hour, minute);
            ttEvent.setDate(cal);
            Integer day = ttEvent.getDate().get(Calendar.DAY_OF_WEEK) - 1;
            ttEvent.setDay(day);
            ttEvent.setStart(hour);
            ttEvent.setEnd(hour+1);
        }
    }

    private boolean validate() {
        if(ttEvent.getLectureName() == null || ttEvent.getLectureName().length() < 1) {
            message = "Paper code required.";
            return false;
        }
        if (ttEvent.getDate() == null) {
            message = "A date is required.";
            return false;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.save:
                updateDetails();
                View view = this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                if (validate()) {
                    Intent data = new Intent();
                    data.putExtra("returnEvent", ttEvent);
                    setResult(RESULT_OK, data);
                    this.finish();
                } else {
                    Snackbar.make(getWindow()
                            .getDecorView()
                            .getRootView(), message, Snackbar.LENGTH_SHORT)
                            .show();
                }
                break;

            case R.id.delete:
                int RESULT_DELETE = 2;
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        editText.setText(dayOfMonth + "/" + month + "/" + year);
        this.year = year;
        this.month = month;
        this.day = dayOfMonth;
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        this.hour = hourOfDay;
        this.minute = minute;
        editText.append(" " + hourOfDay + ":" + minute);
    }

}
