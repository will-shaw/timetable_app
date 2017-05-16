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
import java.util.GregorianCalendar;

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


    // TODO: Remove this temporary event list when we're done using it.
    public ArrayList<TTEvent> getDummy(){

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

}

