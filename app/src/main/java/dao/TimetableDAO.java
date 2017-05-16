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

import domain.TTEvent;

/** Timetable DAO class.
 * Created by Bill on 09/05/2017.
 * Updated by Will on 16/05/2017.
 */

public class TimetableDAO implements Serializable, TimetableInterface {

    // Context comes from Activity.
    private final Context context;

    // File to save and load serialized data to and from.
    private final String FILE_NAME = "timetable-data";

    // Collection of events.
    private static Collection<TTEvent> timetable = new ArrayList<>();

    /** Constructor: receives context from Activity. */
    public TimetableDAO(Context context) {
        this.context = context;
    }

    /** Constructor: receives context from Activity. Allows for pre-existing timetable data. */
    public TimetableDAO(Context context, Collection<TTEvent> collect) {
        this.context = context;
        timetable = collect;
    }

    /** Saves the timetable to file, in serialized object format. */
    @Override
    public void saveTimeTable() throws IOException {
        FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(timetable);
        os.close();
        fos.close();
    }

    /** Loads from file (if exists) and de-serializes into timetable object. */
    @Override
    @SuppressWarnings("unchecked")
    public void loadTimeTable() throws IOException, ClassNotFoundException {
        FileInputStream fis = context.openFileInput(FILE_NAME);
        ObjectInputStream is = new ObjectInputStream(fis);
        timetable = (Collection<TTEvent>) is.readObject();
        is.close();
        fis.close();
    }

    /** Search by paper code.
     * @param paperCode the paper code to search for.
     * @return all events with that paper code.
     */
    @Override
    public Collection<TTEvent> search(String paperCode) {
        Collection<TTEvent> result = new ArrayList<>();

        for (TTEvent event : timetable) {
            if (event.getId().equalsIgnoreCase(paperCode)) {
                result.add(event);
            }
        }
        return result;
    }

    /** Search by date.
     * @param date the date to search for.
     * @return all events with that date.
     */
    @Override
    public Collection<TTEvent> search(Calendar date) {
        Collection<TTEvent> result = new ArrayList<>();

        for (TTEvent event : timetable) {
            if (event.getDate().equals(date)) {
                result.add(event);
            }
        }
        return result;
    }

    /** Search by both date and paper code.
     * @param date the date to search for.
     * @param paperCode the paper code to search for.
     * @return all event that match these two requirements.
     */
    @Override
    public Collection<TTEvent> search(Calendar date, String paperCode) {
        Collection<TTEvent> result = new ArrayList<>();

        for (TTEvent event : timetable) {
            if (event.getDate().equals(date) &&
                    event.getId().equalsIgnoreCase(paperCode)) {
                result.add(event);
            }
        }
        return result;
    }

    /** Gets a full list of current timetable events.
     * @return a list of all events.
     */
    @Override
    public Collection<TTEvent> getTimeTable() {
        return timetable;
    }

    /** Saves a new event to the timetable; or updates an event with the same id.
     * @param ttEvent the event to save or update.
     * @return whether the operation was successful.
     */
    @Override
    public boolean save(TTEvent ttEvent) {
        if (timetable.contains(ttEvent)) {
            timetable.remove(ttEvent);
        }
        return timetable.add(ttEvent);
    }

    /** Deletes an event from the timetable.
     * @param ttEvent the event to delete.
     * @return whether the operation was successful.
     */
    @Override
    public boolean delete(TTEvent ttEvent) {
        return timetable.contains(ttEvent) && timetable.remove(ttEvent);
    }

    /** Overrides the toString method with indication of the data type + it's length.
     * @return a string representation of the timetable array.
     */
    @Override
    public String toString() {
        return "timetableDAO { ..." + timetable.size() + "... }";
    }

}

