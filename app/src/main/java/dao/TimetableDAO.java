package dao;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import domain.TTEvent;

/** Timetable DAO class.
 * Created by Bill on 9/05/2017.
 */

public class TimetableDAO implements Serializable {
    //If we want to use a hashmap later depends on when Shaun implements the file.
    //private static Map<String, TTEvent> timetable = null;

    // Context comes from Activity.
    private Context context;

    private static Collection<TTEvent> timetable = new ArrayList<>();

    public void saveTimeTable() throws IOException {
        String FILENAME = "TimeTableEntry";
        FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(timetable);
        os.close();
        fos.close();
    }

    @SuppressWarnings("unchecked")
    public void loadTimeTable() throws IOException, ClassNotFoundException {
        String FILENAME = "TimeTableEntry";
        FileInputStream fis = context.openFileInput(FILENAME);
        ObjectInputStream is = new ObjectInputStream(fis);
        timetable = (Collection<TTEvent>) is.readObject();
        is.close();
        fis.close();
    }

    public void create(Collection<TTEvent> blocks) {
        timetable = blocks;
    }

    public TimetableDAO(Context context) {
        this.context = context;
    }

    public TimetableDAO(Context context, Collection<TTEvent> collect) {
        this.context = context;
        timetable = collect;
    }

    @Override
    public String toString() {
        return "timetableDAO{}";
    }

    public Collection getTimeTable() {
        return timetable;
    }

    //THE GOOD SEARCH: USE THIS WHEN YOU WANT A SPECIFIC CLASS ON A DAY.
    public Collection<TTEvent> genericSearch(Calendar date, String code) {
        Collection<TTEvent> searchResult = new ArrayList<>();
        for (TTEvent item : timetable) {
            Calendar cal = item.getDate();
            String itemCode = item.getId();
            if (cal.equals(date) && itemCode.equals(code)) {
                searchResult.add(item);
            }
        }
        return searchResult;
    }

    //Poorly Implemented. ONLY USE IF YOU WANT ALLLL ON A SPECIFIC DATE I GUESS.
    public Collection<TTEvent> searchByDate(Calendar search) {
        Collection<TTEvent> searchResult = new ArrayList<>();
        for (TTEvent item : timetable) {
            Calendar cal = item.getDate();
            if (cal.equals(search)) {
                searchResult.add(item);
            }
        }
        return searchResult;
    }

    public boolean save(TTEvent ttEvent) {
        return timetable.add(ttEvent);
    }

    public boolean delete(TTEvent ttEvent) {
        return timetable.remove(ttEvent);
    }

}

