package domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

/**
 * Universal Event Class
 *    So that all parts of code have a consistent standard for data io.
 */
public class TTEvent implements Serializable {
    private String id;
    private Calendar date;
    private int day;
    private int start;
    private int end;
    private String bcol;
    private String fcol;
    private String lectureName;
    private String gmapsUrl;
    private double lat;
    private double lon;
    private String roomCode;
    private String paperName;
    private String roomName;
    private String buildingName;
    private String stream;

    public TTEvent() {}

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getBcol() {
        return bcol;
    }

    public void setBcol(String bcol) {
        this.bcol = bcol;
    }

    public String getFcol() {
        return fcol;
    }

    public void setFcol(String fcol) {
        this.fcol = fcol;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getGmapsUrl() {
        return gmapsUrl;
    }

    public void setGmapsUrl(String gmapsUrl) {
        this.gmapsUrl = gmapsUrl;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TTEvent ttEvent = (TTEvent) o;
        return Objects.equals(id, ttEvent.id) &&
                Objects.equals(date, ttEvent.date);
    }

    private String toDate(Integer val) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, val);
        date.set(Calendar.MINUTE, 0);
        return date.getTime().toString().substring(11, 16);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date);
    }

    @Override
    public String toString() {
        return toDate(this.getStart()) + " - "
                + toDate(this.getEnd()) + "   |   "
                + this.lectureName + " in "
                + this.getRoomCode();
    }

}
