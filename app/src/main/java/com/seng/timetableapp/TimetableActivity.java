package com.seng.timetableapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.util.Calendar.DAY_OF_YEAR;

import dao.TimetableDAO;
import domain.TTEvent;

public class TimetableActivity extends AppCompatActivity {

    private RefreshTimeTable refreshTask;
    private ArrayList<TTEvent> todayItems = new ArrayList<>();
    private ArrayList<TTEvent> tomorrowItems = new ArrayList<>();
    private final Context context = this;

    private TimetableDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        TextView lblTodayDate = (TextView) findViewById(R.id.lbl_today_date);
        TextView lblTomorrowDate = (TextView) findViewById(R.id.lbl_tomorrow_date);

        dao = new TimetableDAO(context);
        loadTimetable();

        Calendar c = Calendar.getInstance();
        lblTodayDate.setText(c.getTime().toString().substring(0, 10));
        c.add(Calendar.DATE, 1);
        lblTomorrowDate.setText(c.getTime().toString().substring(0, 10));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Attempts to load the timetable from the dao.
     */
    private void loadTimetable() {
        try {
            dao.loadTimeTable();
            Snackbar.make(getWindow().getDecorView().getRootView(), "Loading timetable...", Snackbar.LENGTH_SHORT)
                    .show();
        } catch (IOException | ClassNotFoundException ex) {
            Snackbar.make(getWindow().getDecorView().getRootView(), "Error loading timetable :(", Snackbar.LENGTH_SHORT)
                    .show();
        }
        refreshTask = new RefreshTimeTable();
        refreshTask.execute();
    }

    /**
     * Attempts to save the timetable to disk using dao.
     */
    public void saveTimetable() {
        try {
            dao.saveTimeTable();
            Snackbar.make(getWindow().getDecorView().getRootView(), "Saving timetable...", Snackbar.LENGTH_SHORT)
                    .show();
        } catch (IOException ex) {
            Snackbar.make(getWindow().getDecorView().getRootView(), "Error saving timetable :(", Snackbar.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu:
                Toast.makeText(context, "Settings selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.search:
                Toast.makeText(context, "Search selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.refresh:
                Toast.makeText(context, "Refresh selected", Toast.LENGTH_SHORT)
                        .show();
                refreshTask = new RefreshTimeTable();
                refreshTask.execute();
                break;

            default:
                break;
        }
        return true;
    }

    /*
    * Gets timetable items then adds them to the list views for today and tomorrow
    */
    private class RefreshTimeTable extends AsyncTask<String, Integer, ArrayList<TTEvent>> {

        //get dummy timetable
        private ArrayList<TTEvent> getDummy(){

            ArrayList<TTEvent> timetable = new ArrayList<>();
            TTEvent tte1 = new TTEvent();
            TTEvent tte2 = new TTEvent();
            TTEvent tte3 = new TTEvent();
            TTEvent tte4 = new TTEvent();

            GregorianCalendar date = new GregorianCalendar();

            tte1.setId("SENG301");
            tte1.setRoomName("Central CAL");
            tte1.setBuildingName("Richardson");
            tte1.setLectureName("Lecture Name");
            tte1.setDate((GregorianCalendar)date.clone());
            tte1.setPaperName("Software Project Management");
            tte1.setLat(-45.8660731);
            tte1.setLon(170.5135830);
            tte1.setRoomCode("CNCAL");

            date.add(Calendar.DAY_OF_MONTH, 1);

            tte2.setId("SENG301");
            tte2.setRoomName("Central CAL");
            tte2.setBuildingName("Richardson");
            tte2.setLectureName("Lecture Name");
            tte2.setDate((GregorianCalendar)date.clone());
            tte2.setPaperName("Software Project Management");
            tte2.setLat(-45.8660731);
            tte2.setLon(170.5135830);
            tte2.setRoomCode("CNCAL");

            date.add(Calendar.HOUR_OF_DAY, 2);

            tte3.setId("SENG301");
            tte3.setRoomName("Central CAL");
            tte3.setBuildingName("Richardson");
            tte3.setLectureName("Lecture Name");
            tte3.setDate((GregorianCalendar)date.clone());
            tte3.setPaperName("Software Project Management");
            tte3.setLat(-45.8660731);
            tte3.setLon(170.5135830);
            tte3.setRoomCode("CNCAL");

            date.add(Calendar.HOUR_OF_DAY, 2);

            tte4.setId("SENG301");
            tte4.setRoomName("Central CAL");
            tte4.setBuildingName("Richardson");
            tte4.setLectureName("Lecture Name");
            tte4.setDate((GregorianCalendar)date.clone());
            tte4.setPaperName("Software Project Management");
            tte4.setLat(-45.8660731);
            tte4.setLon(170.5135830);
            tte4.setRoomCode("CNCAL");

            timetable.add(tte1);
            timetable.add(tte2);
            timetable.add(tte3);
            timetable.add(tte4);

            return timetable;
        }

        @Override
        protected ArrayList<TTEvent> doInBackground(String... urls) {
            return (ArrayList<TTEvent>) dao.getTimeTable();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {

        }

        @Override
        protected void onPostExecute(ArrayList<TTEvent> timetable) {

            //get dummy timetable
                timetable = getDummy();


            final ListView listToday = (ListView) findViewById(R.id.list_tt_today);
            final ListView listTomorrow = (ListView) findViewById(R.id.list_tt_tomorrow);

            listToday.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TTEvent event = (TTEvent) listToday.getItemAtPosition(position);
                    Intent intent = new Intent(TimetableActivity.this, ViewEventActivity.class);
                    intent.putExtra("ttEvent", event);
                    startActivity(intent);
                }
            });

            listTomorrow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TTEvent event = (TTEvent) listTomorrow.getItemAtPosition(position);
                    Intent intent = new Intent(TimetableActivity.this, ViewEventActivity.class);
                    intent.putExtra("ttEvent", event);
                    startActivity(intent);
                }
            });

            //Creates string arrays for today and tomorrow
            for (TTEvent ttEvent : timetable) {
                Integer daysAway = ttEvent.getDate().get(DAY_OF_YEAR) - new GregorianCalendar().get(DAY_OF_YEAR);

                if (daysAway == 0) {
                    todayItems.add(ttEvent);
                } else if (daysAway == 1) {
                    tomorrowItems.add(ttEvent);
                }
            }

            //Writes items to screen
            ArrayAdapter<TTEvent> adapterToday = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, todayItems);
            ArrayAdapter<TTEvent> adapterTomorrow = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, tomorrowItems);
            listToday.setAdapter(adapterToday);
            listTomorrow.setAdapter(adapterTomorrow);
        }
    }

}


