package dao;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;

import domain.TTEvent;

/** Timetable Interface
 * Created by will on 16/05/2017.
 */

interface TimetableInterface {

    boolean loadTimeTable() throws IOException, ClassNotFoundException;

    boolean saveTimeTable() throws IOException;

    Collection<TTEvent> search(Calendar date, String paperCode);

    Collection<TTEvent> search(Calendar date);

    Collection<TTEvent> search(String paperCode);

    Collection<TTEvent> getTimeTable();

    boolean save(TTEvent ttEvent);

    boolean delete(TTEvent ttEvent);

    @Override
    String toString();

}
