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
import java.util.Random;

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
    public static Collection<TTEvent> timetable;

    /** Constructor: receives context from Activity. */
    public TimetableDAO(Context context) {
        this.context = context;
    }

    /**
     * Generated a 5 character ID which doesn't exist already in this collection.
     * @return a 5 character unique id.
     */
    public static String genUniqueID() {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        boolean unique = true;
        do {
            for (int i = 0; i < 5; i++) {
                sb.append((char) (rand.nextInt(26) + 65));
            }
            for (TTEvent event : timetable) {
                if (event.getId() != null && event.getId().equals(sb.toString())) {
                    unique = false;
                }
            }
        } while (!unique);
        return sb.toString();
    }

    /**
     * Saves the timetable to file, in serialized object format.
     */
    @Override
    public boolean saveTimeTable() {
        try (FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
             ObjectOutputStream os = new ObjectOutputStream(fos)
        ) {
            os.writeObject(timetable);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Loads from file (if exists) and de-serializes into timetable object.
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean loadTimeTable() {
        try (
                FileInputStream fis = context.openFileInput(FILE_NAME);
                ObjectInputStream is = new ObjectInputStream(fis)
        ) {
            timetable = (Collection<TTEvent>) is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
        return true;
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

        for (int i = 0; i < timetable.size(); i++) {
            TTEvent tte = ((ArrayList<TTEvent>) timetable).get(i);
            if ((tte.getDay() > ttEvent.getDay()) ||
                    (tte.getDay() == ttEvent.getDay() && tte.getStart() > ttEvent.getStart())) {
                ((ArrayList<TTEvent>) timetable).add(i, ttEvent);
                return true;
            }
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

