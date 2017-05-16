package com.seng.timetableapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.util.Calendar.DAY_OF_YEAR;

import dao.TimetableDAO;
import domain.TTEvent;

public class TimetableActivity extends AppCompatActivity {

    private RefreshTimeTable refreshTask;
    private final Context context = this;


    private TimetableDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        TextView lblTodayDate = (TextView) findViewById(R.id.lbl_day_1_date);
        TextView lblTomorrowDate = (TextView) findViewById(R.id.lbl_date_day_2);

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
        private ArrayList<ArrayList<TTEvent>> weekTT = new ArrayList<>();

        private final Integer PADDING = 15;

        //get dummy timetable
        private ArrayList<TTEvent> getDummy(){

            ArrayList<TTEvent> timetable = new ArrayList<>();
            TTEvent tte1 = new TTEvent();
            TTEvent tte2 = new TTEvent();
            TTEvent tte3 = new TTEvent();
            TTEvent tte4 = new TTEvent();
            TTEvent tte5 = new TTEvent();
            TTEvent tte6 = new TTEvent();
            TTEvent tte7 = new TTEvent();


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

            tte2.setId("SENG302");
            tte2.setRoomName("Central CAL");
            tte2.setBuildingName("Richardson");
            tte2.setLectureName("Lecture Name");
            tte2.setDate((GregorianCalendar)date.clone());
            tte2.setPaperName("Software Project Management");
            tte2.setLat(-45.8660731);
            tte2.setLon(170.5135830);
            tte2.setRoomCode("CNCAL");

            date.add(Calendar.HOUR_OF_DAY, -2);

            tte3.setId("SENG303");
            tte3.setRoomName("Central CAL");
            tte3.setBuildingName("Richardson");
            tte3.setLectureName("Lecture Name");
            tte3.setDate((GregorianCalendar)date.clone());
            tte3.setPaperName("Software Project Management");
            tte3.setLat(-45.8660731);
            tte3.setLon(170.5135830);
            tte3.setRoomCode("CNCAL");

            date.add(Calendar.HOUR_OF_DAY, -1);

            tte4.setId("SENG304");
            tte4.setRoomName("Central CAL");
            tte4.setBuildingName("Richardson");
            tte4.setLectureName("Lecture Name");
            tte4.setDate((GregorianCalendar)date.clone());
            tte4.setPaperName("Software Project Management");
            tte4.setLat(-45.8660731);
            tte4.setLon(170.5135830);
            tte4.setRoomCode("CNCAL");

            date.add(Calendar.HOUR_OF_DAY, 0);

            tte5.setId("SENG305");
            tte5.setRoomName("Central CAL");
            tte5.setBuildingName("Richardson");
            tte5.setLectureName("Lecture Name");
            tte5.setDate((GregorianCalendar)date.clone());
            tte5.setPaperName("Software Project Management");
            tte5.setLat(-45.8660731);
            tte5.setLon(170.5135830);
            tte5.setRoomCode("CNCAL");

            date.add(Calendar.HOUR_OF_DAY, 1);

            tte6.setId("SENG306");
            tte6.setRoomName("Central CAL");
            tte6.setBuildingName("Richardson");
            tte6.setLectureName("Lecture Name");
            tte6.setDate((GregorianCalendar)date.clone());
            tte6.setPaperName("Software Project Management");
            tte6.setLat(-45.8660731);
            tte6.setLon(170.5135830);
            tte6.setRoomCode("CNCAL");

            date.add(Calendar.HOUR_OF_DAY, 2);

            tte7.setId("SENG307");
            tte7.setRoomName("Central CAL");
            tte7.setBuildingName("Richardson");
            tte7.setLectureName("Lecture Name");
            tte7.setDate((GregorianCalendar)date.clone());
            tte7.setPaperName("Software Project Management");
            tte7.setLat(-45.8660731);
            tte7.setLon(170.5135830);
            tte7.setRoomCode("CNCAL");

            timetable.add(tte1);
            timetable.add(tte2);
            timetable.add(tte3);
            timetable.add(tte4);
            timetable.add(tte5);
            timetable.add(tte6);
            timetable.add(tte7);

            return timetable;
        }

        //get pixels from row
        private Integer getPixels(Integer rows){
            rows+=1;
            return rows*100;
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
            Integer todayItemSize = 1, tomorowItemSize = 1;

            //get dummy timetable
                timetable = getDummy();


            final ListView listToday = (ListView) findViewById(R.id.list_tt_day_1);
            final ListView listTomorrow = (ListView) findViewById(R.id.list_tt_day_2);
            final CardView cardToday = (CardView) findViewById(R.id.card__day_1);
            final CardView cardTomorow= (CardView) findViewById(R.id.card_day_2);

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

            //initialize weekTT
            if(weekTT.isEmpty()) {
                int i = 0;
                while (i < 7) {
                    weekTT.add(new ArrayList<TTEvent>());
                    i++;
                }
            }

            //adds ttEvents to correct day of weekTT
            for (TTEvent ttEvent : timetable) {
                //sees how far away ttEvent is
                Integer day = ttEvent.getDate().get(DAY_OF_YEAR) - new GregorianCalendar().get(DAY_OF_YEAR);
                //if dayTT isnt empty
                if(!weekTT.get(day).isEmpty()){
                    //and it doesnt contain the event, add it
                    if(!weekTT.get(day).contains(ttEvent)){
                        ArrayList<TTEvent> dayTT = weekTT.get(day);
                        dayTT.add(ttEvent);
                        weekTT.set(day,dayTT);
                    }

                    //if the dayTT is empty, make a new dayTT with event, and add it.
                }else{
                    ArrayList<TTEvent> dayTT = new ArrayList<TTEvent>();
                    dayTT.add(ttEvent);
                    weekTT.set(day, dayTT);
                }
            }

            String ttEventString;
            ArrayList<ArrayList<String>> weekTTStrings = new ArrayList<ArrayList<String>>();

                //for each day of the week
                for(int i = 0; i < weekTT.size(); i++){
                    //for each ttEvent in the day
                    for(int j = 0; j < weekTT.get(i).size(); j++){
                        //get ttEvent string
                        ttEventString = weekTT.get(i).get(j).getId();

                        ArrayList<String> dayTTStrings = new ArrayList<String>();
                        //try get string array for day if it exists
                        try {
                            dayTTStrings = weekTTStrings.get(i);
                        }catch(java.lang.IndexOutOfBoundsException e){

                        }

                        //add event string to dayTTStrings array
                        if (!dayTTStrings.contains(ttEventString)){
                            dayTTStrings.add(ttEventString);
                        }
                        //set dayTTStrings array back into weekTTStrings array
                        try {
                            weekTTStrings.set(i, dayTTStrings);
                        }catch(java.lang.IndexOutOfBoundsException e){
                            weekTTStrings.add(dayTTStrings);
                        }


                    }
                }



            ArrayAdapter<String> adapterToday = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, weekTTStrings.get(0));
            ArrayAdapter<String> adapterTomorrow = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, weekTTStrings.get(1));
            listToday.setAdapter(adapterToday);
            listTomorrow.setAdapter(adapterTomorrow);

            Integer day = 0;
            cardToday.setLayoutParams(new LinearLayout.LayoutParams(cardToday.getWidth(), getPixels(weekTT.get(day).size())));
            LinearLayout linearToday = (LinearLayout)cardToday.getParent();
            linearToday.setPadding(PADDING,PADDING,0,0);

            day++;
            cardTomorow.setLayoutParams(new LinearLayout.LayoutParams(cardTomorow.getWidth(), getPixels(weekTT.get(day).size())));
            LinearLayout linearTomorow = (LinearLayout)cardTomorow.getParent();
            linearTomorow.setPadding(PADDING,PADDING,0,0);

            //TODO: should probably think up a cleverer way of doing it loop it up somehow idk but erm, yeah. like add more array adaptors
            //TODO: and lists view references and on click listeners to complete days 3-7. It should just work cause everything else is hunkydory af




        }
    }

}


