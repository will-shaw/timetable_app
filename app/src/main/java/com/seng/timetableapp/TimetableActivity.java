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
        TextView lblDateDayThree = (TextView) findViewById(R.id.lbl_date_day_3);
        TextView lblDateDayFour = (TextView) findViewById(R.id.lbl_date_day_4);
        TextView lblDateDayFive = (TextView) findViewById(R.id.lbl_date_day_5);
        TextView lblDateDaySix = (TextView) findViewById(R.id.lbl_date_day_6);
        TextView lblDateDaySeven = (TextView) findViewById(R.id.lbl_date_day_7);

        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshTask = new RefreshTimeTable();
                refreshTask.execute();
            }
        });

        dao = new TimetableDAO(context);
        //dao.saveTimeTable();
        loadTimetable();

        Calendar c = Calendar.getInstance();
        lblTodayDate.setText(c.getTime().toString().substring(0, 10));
        c.add(Calendar.DATE, 1);
        lblTomorrowDate.setText(c.getTime().toString().substring(0, 10));
        c.add(Calendar.DATE, 1);
        lblDateDayThree.setText(c.getTime().toString().substring(0, 10));
        c.add(Calendar.DATE, 1);
        lblDateDayFour.setText(c.getTime().toString().substring(0, 10));
        c.add(Calendar.DATE, 1);
        lblDateDayFive.setText(c.getTime().toString().substring(0, 10));
        c.add(Calendar.DATE, 1);
        lblDateDaySix.setText(c.getTime().toString().substring(0, 10));
        c.add(Calendar.DATE, 1);
        lblDateDaySeven.setText(c.getTime().toString().substring(0, 10));
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
            if (data != null && data.hasExtra("returnEvent")) {
                TTEvent returnedEvent = (TTEvent) data.getSerializableExtra("returnEvent");
                boolean saved = dao.save(returnedEvent) && dao.saveTimeTable();
                if (saved) {
                    showSnack("Saved");
                } else {
                    showSnack("Couldn't save :(");
                }
                refreshTask = new RefreshTimeTable();
                refreshTask.execute();
            }
        } else if (resultCode == RESULT_DELETE) {
            if (data != null && data.hasExtra("ttEvent")) {
                TTEvent ttEvent = (TTEvent) data.getSerializableExtra("ttEvent");
                boolean deleted = dao.delete(ttEvent) && dao.saveTimeTable();
                if (deleted) {
                    showSnack("Deleted");
                } else {
                    showSnack("Couldn't delete :(");
                }
                refreshTask = new RefreshTimeTable();
                refreshTask.execute();
            }
        }
    }

    public void showSnack(String message) {
        Snackbar.make(getWindow()
                .getDecorView()
                .getRootView(), message, Snackbar.LENGTH_SHORT)
                .show();
    }

    /**
     * Attempts to load the timetable from the dao on start up.
     */
    private void loadTimetable() {
        boolean loaded = dao.loadTimeTable();
        if (loaded) {
            showSnack("Loading timetable...");
        } else {
            showSnack("Error loading timetable :(");
        }
        refreshTask = new RefreshTimeTable();
        refreshTask.execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu:
                break;
            case R.id.add:
                Intent intent = new Intent(TimetableActivity.this, EditEventActivity.class);
                intent.putExtra("title", "New event");
                startActivityForResult(intent, 1);
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

        private final Integer PADDING = 10;

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
            //Integer todayItemSize = 1, tomorrowItemSize = 1;

            // I added the dummy timetable to the DAO for now, just use:
            timetable = (ArrayList<TTEvent>) dao.getTimeTable();

            final ListView listToday = (ListView) findViewById(R.id.list_tt_day_1);
            final ListView listTomorrow = (ListView) findViewById(R.id.list_tt_day_2);
            final ListView listDayThree = (ListView) findViewById(R.id.list_tt_day_3);
            final ListView listDayFour = (ListView) findViewById(R.id.list_tt_day_4);
            final ListView listDayFive = (ListView) findViewById(R.id.list_tt_day_5);
            final ListView listDaySix = (ListView) findViewById(R.id.list_tt_day_6);
            final ListView listDaySeven = (ListView) findViewById(R.id.list_tt_day_7);

            final CardView cardToday = (CardView) findViewById(R.id.card_day_1);
            final CardView cardTomorrow= (CardView) findViewById(R.id.card_day_2);
            final CardView cardDayThree = (CardView) findViewById(R.id.card_day_3);
            final CardView cardDayFour = (CardView) findViewById(R.id.card_day_4);
            final CardView cardDayFive = (CardView) findViewById(R.id.card_day_5);
            final CardView cardDaySix = (CardView) findViewById(R.id.card_day_6);
            final CardView cardDaySeven = (CardView) findViewById(R.id.card_day_7);

            ArrayList<TTEvent> empty = new ArrayList<>();
            listToday.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, empty));
            listTomorrow.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, empty));
            listDayThree.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, empty));
            listDayFour.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, empty));
            listDayFive.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, empty));
            listDaySix.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, empty));
            listDaySeven.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, empty));

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

            listDayThree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TTEvent event = (TTEvent) listDayThree.getItemAtPosition(position);
                    Intent intent = new Intent(TimetableActivity.this, ViewEventActivity.class);
                    intent.putExtra("ttEvent", event);
                    startActivityForResult(intent, 1);
                }
            });

            listDayFour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TTEvent event = (TTEvent) listDayFour.getItemAtPosition(position);
                    Intent intent = new Intent(TimetableActivity.this, ViewEventActivity.class);
                    intent.putExtra("ttEvent", event);
                    startActivityForResult(intent, 1);
                }
            });

            listDayFive.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TTEvent event = (TTEvent) listDayFive.getItemAtPosition(position);
                    Intent intent = new Intent(TimetableActivity.this, ViewEventActivity.class);
                    intent.putExtra("ttEvent", event);
                    startActivityForResult(intent, 1);
                }
            });

            listDaySix.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TTEvent event = (TTEvent) listDaySix.getItemAtPosition(position);
                    Intent intent = new Intent(TimetableActivity.this, ViewEventActivity.class);
                    intent.putExtra("ttEvent", event);
                    startActivityForResult(intent, 1);
                }
            });

            listDaySeven.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TTEvent event = (TTEvent) listDaySeven.getItemAtPosition(position);
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
                if (ttEvent != null && ttEvent.getDate() != null) {
                    Integer day;
                    if (ttEvent.getDay() > -1) {
                        day = ttEvent.getDay();
                    } else {
                        day = ttEvent.getDate().get(DAY_OF_YEAR) - new GregorianCalendar().get(DAY_OF_YEAR);
                    }
                    System.out.println("DAY/INDEX = " + day);

                    //if dayTT isn't empty
                    if (!weekTT.get(day).isEmpty()) {
                        //and it doesn't contain the event, add it
                        if (!weekTT.get(day).contains(ttEvent)) {
                            ArrayList<TTEvent> dayTT = weekTT.get(day);
                            dayTT.add(ttEvent);
                            weekTT.set(day, dayTT);
                        }

                        //if the dayTT is empty, make a new dayTT with event, and add it.
                    } else {
                        ArrayList<TTEvent> dayTT = new ArrayList<>();
                        dayTT.add(ttEvent);
                        weekTT.set(day, dayTT);
                    }
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
                            if (!weekEvents.isEmpty() && i < weekEvents.size()) {
                                dayEvents = weekEvents.get(i);
                            }
                        }catch(java.lang.IndexOutOfBoundsException e){
                            e.printStackTrace();
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
                ArrayAdapter<TTEvent> adapterDayOne = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, weekEvents.get(0));
                listToday.setAdapter(adapterDayOne);
            }
            if (weekEvents.size() > 1) {
                ArrayAdapter<TTEvent> adapterDayTwo = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, weekEvents.get(1));
                listTomorrow.setAdapter(adapterDayTwo);
            }
            if (weekEvents.size() > 2) {
                ArrayAdapter<TTEvent> adapterDayThree = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, weekEvents.get(2));
                listDayThree.setAdapter(adapterDayThree);
            }
            if (weekEvents.size() > 3) {
                ArrayAdapter<TTEvent> adapterDayFour = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, weekEvents.get(3));
                listDayFour.setAdapter(adapterDayFour);
            }
            if (weekEvents.size() > 4) {
                ArrayAdapter<TTEvent> adapterDayFive = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, weekEvents.get(4));
                listDayFive.setAdapter(adapterDayFive);
            }
            if (weekEvents.size() > 5) {
                ArrayAdapter<TTEvent> adapterDaySix = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, weekEvents.get(5));
                listDaySix.setAdapter(adapterDaySix);
            }
            if (weekEvents.size() > 6) {
                ArrayAdapter<TTEvent> adapterDaySeven = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, weekEvents.get(6));
                listDaySeven.setAdapter(adapterDaySeven);
            }

            Integer day = 0;
            cardToday.setLayoutParams(new LinearLayout.LayoutParams(cardToday.getWidth(), getPixels(weekTT.get(day).size())));
            LinearLayout linearToday = (LinearLayout)cardToday.getParent();
            linearToday.setPadding(0,PADDING,0,PADDING);

            day++;
            cardTomorrow.setLayoutParams(new LinearLayout.LayoutParams(cardTomorrow.getWidth(), getPixels(weekTT.get(day).size())));
            LinearLayout linearTomorrow = (LinearLayout)cardTomorrow.getParent();
            linearTomorrow.setPadding(0,PADDING,0,PADDING);

            day++;
            cardDayThree.setLayoutParams(new LinearLayout.LayoutParams(cardDayThree.getWidth(), getPixels(weekTT.get(day).size())));
            LinearLayout linearDayThree = (LinearLayout)cardDayThree.getParent();
            linearDayThree.setPadding(0,PADDING,0,PADDING);

            day++;
            cardDayFour.setLayoutParams(new LinearLayout.LayoutParams(cardDayFour.getWidth(), getPixels(weekTT.get(day).size())));
            LinearLayout linearDayFour = (LinearLayout)cardDayFour.getParent();
            linearDayFour.setPadding(0,PADDING,0,PADDING);

            day++;
            cardDayFive.setLayoutParams(new LinearLayout.LayoutParams(cardDayFive.getWidth(), getPixels(weekTT.get(day).size())));
            LinearLayout linearDayFive = (LinearLayout)cardDayFive.getParent();
            linearDayFive.setPadding(0,PADDING,0,PADDING);

            day++;
            cardDaySix.setLayoutParams(new LinearLayout.LayoutParams(cardDaySix.getWidth(), getPixels(weekTT.get(day).size())));
            LinearLayout linearDaySix = (LinearLayout)cardDaySix.getParent();
            linearDaySix.setPadding(0,PADDING,0,PADDING);

            day++;
            cardDaySeven.setLayoutParams(new LinearLayout.LayoutParams(cardDaySeven.getWidth(), getPixels(weekTT.get(day).size())));
            LinearLayout linearDaySeven = (LinearLayout)cardDaySeven.getParent();
            linearDaySeven.setPadding(0,PADDING,0,PADDING);

            //TODO: should probably think up a cleverer way of doing it loop it up somehow idk but erm, yeah. like add more array adaptors
            //TODO: and lists view references and on click listeners to complete days 3-7. It should just work cause everything else is hunky-dory af


            swipeRefresh.setRefreshing(false);
        }
    }

}


