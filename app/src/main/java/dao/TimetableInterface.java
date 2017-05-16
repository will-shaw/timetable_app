package dao;

import java.util.Calendar;
import java.util.Collection;
import domain.TTEvent;

/** Timetable Interface
 * Created by will on 16/05/2017.
 */

interface TimetableInterface {

    boolean loadTimeTable();

    boolean saveTimeTable();

    Collection<TTEvent> search(Calendar date, String paperCode);

    Collection<TTEvent> search(Calendar date);

    Collection<TTEvent> search(String paperCode);

    Collection<TTEvent> getTimeTable();

    boolean save(TTEvent ttEvent);

    boolean delete(TTEvent ttEvent);

    @Override
    String toString();

}
