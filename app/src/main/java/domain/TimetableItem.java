package domain;

import java.util.Calendar;

/**
 * For creating timetable objects
 */

public class TimetableItem {
        public Calendar date;
        public String LessonType;//lecture,lab etc.
        public String SubjectCode;
        public String Room;
        public String Building;

        public TimetableItem() {
        }

        public TimetableItem(Calendar date, String lessonType, String subjectCode, String room, String building) {
            this.date = date;
            LessonType = lessonType;
            SubjectCode = subjectCode;
            Room = room;
            Building = building;
        }

        public Calendar getDate() {
            return date;
        }

        public String getLessonType() {
            return LessonType;
        }

        public String getSubjectCode() {
            return SubjectCode;
        }

        public String getRoom() {
            return Room;
        }

    public String getBuilding() {
        return Building;
    }

    public void setDate(Calendar date) {
            this.date = date;
        }

        public void setLessonType(String lessonType) {
            LessonType = lessonType;
        }

        public void setSubjectCode(String subjectCode) {
            SubjectCode = subjectCode;
        }

        public void setRoom(String room) {
            Room = room;
        }

    public void setBuilding(String building) {
        Building = building;
    }

    @Override
    public String toString() {
        return "TimetableItem{" +
                "date=" + date +
                ", LessonType='" + LessonType + '\'' +
                ", SubjectCode='" + SubjectCode + '\'' +
                ", Room='" + Room + '\'' +
                ", Building='" + Building + '\'' +
                '}';
    }
}
