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
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

import dao.TimetableDAO;
import domain.TTEvent;

public class TimetableActivity extends AppCompatActivity {

    private RefreshTimeTable refreshTask;
    private final Context context = this;
    private SwipeRefreshLayout swipeRefresh;
    private long backLastPressed = 0;

    private final int RESULT_DELETE = 2;
    private TimetableDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshTask = new RefreshTimeTable();
                refreshTask.execute();
            }
        });

        dao = new TimetableDAO(context);
        loadTimetable();
        updateTitles();
    }

    /**
     * Used to update the Day and Date labels on each card.
     * Can be called from refresh to update these when timezone changes or
     * then the user has left the app running in background.
     */
    private void updateTitles() {
        TextView[] dateLabels = {
                (TextView) findViewById(R.id.lbl_day_1_date),
                (TextView) findViewById(R.id.lbl_date_day_2),
                (TextView) findViewById(R.id.lbl_date_day_3),
                (TextView) findViewById(R.id.lbl_date_day_4),
                (TextView) findViewById(R.id.lbl_date_day_5),
                (TextView) findViewById(R.id.lbl_date_day_6),
                (TextView) findViewById(R.id.lbl_date_day_7)
        };

        TextView[] dayLabels = {
                (TextView) findViewById(R.id.lbl_day_3),
                (TextView) findViewById(R.id.lbl_day_4),
                (TextView) findViewById(R.id.lbl_day_5),
                (TextView) findViewById(R.id.lbl_day_6),
                (TextView) findViewById(R.id.lbl_day_7)
        };

        Calendar c = Calendar.getInstance();

        for (int i = 0;  i < dateLabels.length; i++) {
            dateLabels[i].setText(c.getTime().toString().substring(0, 10));
            if (i > 1) {
                dayLabels[i-2].setText(
                        c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US));
            }
            c.add(Calendar.DATE, 1);
        }
    }

    /**
     * When the options menu is instantiated, fill using appropriate menu layout.
     * @param menu the menu being created.
     * @return a boolean. Always true here.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Deals with activities created here which have finished.
     * Here we save any updated or new events which have been passed back.
     * @param request the request.
     * @param resultCode the state in which the activity ended. e.g. Success/Error.
     * @param data the returned intent, which contains events or other data.
     */
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

    /**
     * Shows the snack-bar on the current view.
     * @param message the message to display.
     */
    private void showSnack(String message) {
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

    /**
     * Responds to actions related to clicking app bar options.
     * @param item the option what was clicked.
     * @return a boolean. Always true here.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.force_refresh:
                TimetableDAO.timetable = null;
                Intent data = new Intent(TimetableActivity.this, LoginActivity.class);
                data.putExtra("type", true);
                startActivity(data);
                this.finish();
                break;
            case R.id.add:
                Intent intent = new Intent(TimetableActivity.this, EditEventActivity.class);
                intent.putExtra("title", "New event");
                startActivityForResult(intent, 1);
                break;
            case R.id.refresh:
                swipeRefresh.setRefreshing(false);
                refreshTask = new RefreshTimeTable();
                refreshTask.execute();
                break;

            default:
                break;
        }
        return true;
    }

    /**
     * On back button pressed, will show snack to inform the user to press again to quit.
     * If the user presses back twice within 2 seconds, the app will exit.
     */
    @Override
    public void onBackPressed() {
        if (backLastPressed == 0 || backLastPressed < (new Date().getTime() - 2000)) {
            backLastPressed = new Date().getTime();
            showSnack("Press back again to exit.");
        } else {
            this.finishAffinity();
        }
    }

    /**
    * Gets timetable items then adds them to the list views for today and tomorrow
    */
    private class RefreshTimeTable extends AsyncTask<String, Integer, ArrayList<TTEvent>> {
        private final ArrayList<ArrayList<TTEvent>> weekTT = new ArrayList<>();

        private final Integer PADDING = 12;

        /**
         * Gets the number of pixels in a row, times the number of items in the day.
         * @param rows the number of items in the day.
         * @return the number of pixels to set the card height.
         */
        private Integer getPixels(Integer rows) {
            rows += 1;
            return rows * 148;
        }

        /**
         * Gets the timetable from the DAO.
         * @param urls inputs.
         * @return An array of events.
         */
        @Override
        protected ArrayList<TTEvent> doInBackground(String... urls) {
            return (ArrayList<TTEvent>) dao.getTimeTable();
        }

        /**
         * Called when setting up the timetable view.
         * @param timetable the list of events.
         */
        @Override
        protected void onPostExecute(ArrayList<TTEvent> timetable) {

            timetable = (ArrayList<TTEvent>) dao.getTimeTable();

            Calendar cal = Calendar.getInstance();
            int offset = cal.get(Calendar.DAY_OF_WEEK) - 1;

            final ListView listToday = (ListView) findViewById(R.id.list_tt_day_1);
            final ListView listTomorrow = (ListView) findViewById(R.id.list_tt_day_2);
            final ListView listDayThree = (ListView) findViewById(R.id.list_tt_day_3);
            final ListView listDayFour = (ListView) findViewById(R.id.list_tt_day_4);
            final ListView listDayFive = (ListView) findViewById(R.id.list_tt_day_5);
            final ListView listDaySix = (ListView) findViewById(R.id.list_tt_day_6);
            final ListView listDaySeven = (ListView) findViewById(R.id.list_tt_day_7);

            ListView[] listViews = {listToday, listTomorrow, listDayThree, listDayFour, listDayFive, listDaySix, listDaySeven};

            final CardView cardDayOne = (CardView) findViewById(R.id.card_day_1);
            final CardView cardDayTwo = (CardView) findViewById(R.id.card_day_2);
            final CardView cardDayThree = (CardView) findViewById(R.id.card_day_3);
            final CardView cardDayFour = (CardView) findViewById(R.id.card_day_4);
            final CardView cardDayFive = (CardView) findViewById(R.id.card_day_5);
            final CardView cardDaySix = (CardView) findViewById(R.id.card_day_6);
            final CardView cardDaySeven = (CardView) findViewById(R.id.card_day_7);

            CardView[] cardViews = {cardDayOne, cardDayTwo, cardDayThree, cardDayFour, cardDayFive, cardDaySix, cardDaySeven};

            ArrayAdapter empty = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, new ArrayList<TTEvent>());
            ItemClickHandler clickHandler = new ItemClickHandler();

            for (ListView list : listViews) {
                list.setAdapter(empty);
                list.setOnItemClickListener(clickHandler);
            }

            if (weekTT.isEmpty()) {
                for (int i = 0; i < 7; i++) {
                    weekTT.add(new ArrayList<TTEvent>());
                }
            }

            for (TTEvent ttEvent : timetable) {
                if (ttEvent != null && ttEvent.getDate() != null) {
                    Integer day;
                    Calendar c = Calendar.getInstance();
                    c.set(Calendar.HOUR_OF_DAY, ttEvent.getStart());
                    c.set(Calendar.MINUTE, 0);
                    c.set(Calendar.SECOND, 0);
                    c.set(Calendar.DAY_OF_WEEK, ttEvent.getDay());
                    c.add(Calendar.DATE, -(7 - Calendar.DAY_OF_WEEK));
                    c.add(Calendar.DATE, 1);
                    ttEvent.setDate(c);
                    if (ttEvent.getDay() > -1) {
                        day = Math.abs(ttEvent.getDay());
                    } else {
                        day = Math.abs(ttEvent.getDate().get(Calendar.DAY_OF_WEEK));
                    }
                    if (day - offset < 0) {
                        weekTT.get(day + 7 - offset).add(ttEvent);
                    } else {
                        weekTT.get(day - offset).add(ttEvent);
                    }
                }
            }

            for (int i = 0; i < listViews.length; i++) {
                if (weekTT.size() > i) {
                    listViews[i].setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, weekTT.get(i)));
                }
            }

            for (int i = 0; i < cardViews.length; i++) {
                cardViews[i].setLayoutParams(new LinearLayout.LayoutParams(cardDayOne.getWidth(), getPixels(weekTT.get(i).size())));
                LinearLayout layout = (LinearLayout) cardViews[i].getParent();
                layout.setPadding(PADDING, PADDING * 3, PADDING, 0);
            }

            updateTitles();
            swipeRefresh.setRefreshing(false);
        }
    }

    /**
     * Handles what happens when you click an event in the list.
     * Created and switched to a new intent, passing through the event data.
     */
    private class ItemClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TTEvent event = (TTEvent) parent.getItemAtPosition(position);
            Intent intent = new Intent(TimetableActivity.this, ViewEventActivity.class);
            intent.putExtra("ttEvent", event);
            startActivityForResult(intent, 1);
        }

    }

}