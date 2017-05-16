package com.seng.timetableapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
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
    private SwipeRefreshLayout swipeRefresh;

    private final int RESULT_DELETE = 2;
    private TimetableDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        TextView lblTodayDate = (TextView) findViewById(R.id.lbl_day_1_date);
        TextView lblTomorrowDate = (TextView) findViewById(R.id.lbl_date_day_2);

        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshTask = new RefreshTimeTable();
                refreshTask.execute();
            }
        });

        dao = new TimetableDAO(context);

        TTEvent ttEvent1 = new TTEvent();
        ttEvent1.setId("SENG301");
        ttEvent1.setRoomName("Central CAL");
        ttEvent1.setBuildingName("Richardson");
        ttEvent1.setLectureName("Lecture Name");
        Calendar eventDate = Calendar.getInstance();
        ttEvent1.setDate(eventDate);
        ttEvent1.setPaperName("Software Project Management");
        ttEvent1.setLat(-45.8660731);
        ttEvent1.setLon(170.5135830);
        ttEvent1.setRoomCode("CNCAL");

        dao.save(ttEvent1);

        TTEvent ttEvent2 = new TTEvent();
        ttEvent2.setId("COSC345");
        ttEvent2.setRoomName("St Dav. 2");
        ttEvent2.setBuildingName("St Davids");
        ttEvent2.setLectureName("Lecture Name");
        eventDate = Calendar.getInstance();
        eventDate.add(Calendar.HOUR_OF_DAY, 2);
        ttEvent2.setDate(eventDate);
        ttEvent2.setPaperName("Software Engineering");
        ttEvent2.setLat(-45.8636919);
        ttEvent2.setLon(170.5135476);
        ttEvent2.setRoomCode("STDAV2");

        dao.save(ttEvent2);

        saveTimetable();

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

    @Override
    protected void onActivityResult(int request, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            refreshTask = new RefreshTimeTable();
            refreshTask.execute();
        } else if (resultCode == RESULT_DELETE) {
            TTEvent ttEvent = (TTEvent) data.getSerializableExtra("ttEvent");
            try {
                System.out.println("Deleting: " + dao.delete(ttEvent));
                dao.saveTimeTable();
                Snackbar.make(getWindow()
                        .getDecorView()
                        .getRootView(), "Deleted", Snackbar.LENGTH_SHORT)
                        .show();
            } catch (IOException e) {
                Snackbar.make(getWindow()
                        .getDecorView()
                        .getRootView(), "Cannot delete :(", Snackbar.LENGTH_SHORT)
                        .show();
            }
            refreshTask = new RefreshTimeTable();
            refreshTask.execute();
        }
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
                break;
            case R.id.search:
                break;
            case R.id.refresh:
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

            // I added the dummy timetable to the DAO for now, just use:
            timetable = dao.getDummy();

            //timetable = (ArrayList) dao.getTimeTable();

            final ListView listToday = (ListView) findViewById(R.id.list_tt_day_1);
            final ListView listTomorrow = (ListView) findViewById(R.id.list_tt_day_2);
            final CardView cardToday = (CardView) findViewById(R.id.card__day_1);
            final CardView cardTomorow= (CardView) findViewById(R.id.card_day_2);

            ArrayList<TTEvent> empty = new ArrayList<>();
            listToday.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, empty));
            listTomorrow.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, empty));

            listToday.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TTEvent event = (TTEvent) listToday.getItemAtPosition(position);
                    Intent intent = new Intent(TimetableActivity.this, ViewEventActivity.class);
                    intent.putExtra("ttEvent", event);
                    startActivityForResult(intent, 1);
                }
            });

            listTomorrow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TTEvent event = (TTEvent) listTomorrow.getItemAtPosition(position);
                    Intent intent = new Intent(TimetableActivity.this, ViewEventActivity.class);
                    intent.putExtra("ttEvent", event);
                    startActivityForResult(intent, 1);
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
                    ArrayList<TTEvent> dayTT = new ArrayList<>();
                    dayTT.add(ttEvent);
                    weekTT.set(day, dayTT);
                }
            }

            TTEvent event;
            ArrayList<ArrayList<TTEvent>> weekEvents = new ArrayList<>();

                //for each day of the week
                for(int i = 0; i < weekTT.size(); i++){
                    //for each ttEvent in the day
                    for(int j = 0; j < weekTT.get(i).size(); j++){
                        //get ttEvent string
                        event = weekTT.get(i).get(j);

                        ArrayList<TTEvent> dayEvents = new ArrayList<>();
                        //try get string array for day if it exists
                        try {
                            dayEvents = weekEvents.get(i);
                        }catch(java.lang.IndexOutOfBoundsException e){

                        }

                        //add event string to dayTTStrings array
                        if (!dayEvents.contains(event)){
                            dayEvents.add(event);
                        }
                        //set dayTTStrings array back into weekTTStrings array
                        try {
                            weekEvents.set(i, dayEvents);
                        }catch(java.lang.IndexOutOfBoundsException e){
                            weekEvents.add(dayEvents);
                        }

                    }
                }

            if (weekEvents.size() > 0) {
                ArrayAdapter<TTEvent> adapterToday = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, weekEvents.get(0));
                listToday.setAdapter(adapterToday);
            }
            if (weekEvents.size() > 1) {
                ArrayAdapter<TTEvent> adapterTomorrow = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, weekEvents.get(1));
                listTomorrow.setAdapter(adapterTomorrow);
            }

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


            swipeRefresh.setRefreshing(false);
        }
    }

}


