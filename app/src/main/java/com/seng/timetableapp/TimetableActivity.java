package com.seng.timetableapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.util.Calendar.DAY_OF_YEAR;

import domain.TimetableItem;

public class TimetableActivity extends AppCompatActivity {

    private RefreshTimeTable refreshTask;
    ArrayList<String> todayItems = new ArrayList<>();
    ArrayList<String> tomorrowItems = new ArrayList<>();
    Context context = this;
    ArrayAdapter<String> adapterToday;
    ArrayAdapter<String> adapterTomorrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        TextView lblTodayDate = (TextView) findViewById(R.id.lbl_today_date);
        TextView lblTomorrowDate = (TextView) findViewById(R.id.lbl_tomorrow_date);

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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
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
                refreshTask = new RefreshTimeTable();
                refreshTask.execute();
                break;

            default:
                break;
        }
        return true;
    }

    /*
    * Makes a dummy timetable
    *
    */
    public ArrayList<TimetableItem> makeTimetable(){
        Calendar cal = new GregorianCalendar();
        ArrayList<TimetableItem> timetable = new ArrayList<>();

        timetable.add(new TimetableItem((Calendar) cal.clone(),"Lecture","SENG301","Room14","MyHouse"));

        cal.add(Calendar.DAY_OF_MONTH, 1);
        timetable.add(new TimetableItem((Calendar) cal.clone(),"Lecture","SENG301","Room14","MyHouse"));

        cal.add(Calendar.HOUR_OF_DAY, 2);
        timetable.add(new TimetableItem((Calendar) cal.clone(),"Lecture","ANDR101","Room14","MyHouse"));

        cal.add(Calendar.HOUR_OF_DAY, 2);
        timetable.add(new TimetableItem((Calendar) cal.clone(),"Lecture","SENG301","Room14","MyHouse"));
        return timetable;
    }

    /*
    * Gets timetable items then adds them to the list views for today and tomorrow
    *
    */
    private class RefreshTimeTable extends AsyncTask<String, Integer, ArrayList<TimetableItem>> {

        @Override
        protected ArrayList<TimetableItem> doInBackground(String... urls) {
            return makeTimetable();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {

        }

        @Override
        protected void onPostExecute(ArrayList<TimetableItem> timetable) {

            final ListView listToday = (ListView) findViewById(R.id.list_tt_today);
            final ListView listTomorrow = (ListView) findViewById(R.id.list_tt_tomorrow);

            listToday.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String label = (String) listToday.getItemAtPosition(position);
                    Intent intent = new Intent(TimetableActivity.this, ViewEventActivity.class);
                    intent.putExtra("paper", label);
                    startActivity(intent);
                }
            });

            listTomorrow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String label = (String) listTomorrow.getItemAtPosition(position);
                    Intent intent = new Intent(TimetableActivity.this, ViewEventActivity.class);
                    intent.putExtra("paper", label);
                    startActivity(intent);
                }
            });

            //Creates string arrays for today and tomorrow
            for (TimetableItem ttItem: timetable) {
                Integer daysAway = ttItem.getDate().get(DAY_OF_YEAR) - new GregorianCalendar().get(DAY_OF_YEAR);

                if (daysAway == 0) {
                    todayItems.add(ttItem.getSubjectCode());
                } else if (daysAway == 1) {
                    tomorrowItems.add(ttItem.getSubjectCode());
                }
            }

            //Writes items to screen
            adapterToday = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, todayItems);
            adapterTomorrow = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, tomorrowItems);
            listToday.setAdapter(adapterToday);
            listTomorrow.setAdapter(adapterTomorrow);
        }
    }

}


