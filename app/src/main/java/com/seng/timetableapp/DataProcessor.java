package com.seng.timetableapp;

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shaun on 5/8/2017.
 */

public class DataProcessor {

    public class TTEvent {
        private String id;
        private int day;
        private int start;
        private int end;
        private String bcol;
        private String fcol;
        private String lectureName;
        private String gmapsUrl;
        private String roomCode;
        private String paperName;
        private String roomName;
        private String buildingName;
        private String stream;

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
    }

    /**
     * Helper function to trim unnecessary characters from the input string.
     * @param c The character to trim to
     * @param s The string to trim
     * @return The substring starting from the first occurence of c, to the end of the string, or s if the character isn't found.
     */
    private String nextChar(char c, String s) {
        boolean f = false;
        int i;
        for(i = 0; i < s.length(); i++) {
            if(s.charAt(i) == (c)) {
                f = true;
                break;
            }
        }
        if(f) {
            return s.substring(i);
        }
        return s;
    }

    /**
     * Helper function to return a block defined by cS and cE (Such as a pair of brackets)
     * @param cS Starting character of block
     * @param cE Ending character of block
     * @param s String to work on
     * @return An array of strings, each being a block as defined by cS and cE, null if mismatched.
     */
    private Collection<String> getBlocks(char cS, char cE, String s) {
        Collection<String> blocks = new ArrayList<>();
        String t = nextChar(cS, s);
        int nests = 0;
        int start = 0;
        boolean lookForStart = false;
        int i;
        for(i = 0; i < t.length(); i++) {
            //If starting a new block, skip over in-between characters
            if(lookForStart && t.charAt(i) != cS) {
                continue;
            } else {
                lookForStart = false;
                start = i;
            }
            if(t.charAt(i) == cS) {
                ++nests;
            } else if(t.charAt(i) == cE) {
                --nests;
            }
            //If nests is zero, we've finished the block and can return it.
            if(nests == 0) {
                blocks.add(t.substring(start,i+1));
                lookForStart = true;
            }
            //If nests is negative, brackets aren't set up properly.
            if(nests < 0) {
                return null;
            }
        }
        //Anything not added will be extra characters outside of a block, so ignore them
        //and return the blocks we have found.
        return blocks;
    }

    /**
     * Takes a block string and parses it into an event object.
     * @param s The string block to parse.
     * @return An event object.
     */
    public TTEvent parseEventString(String s) {
        s = s.substring(1,s.length()-1); //Trim block characters.
        String[] items = s.split(",");
        //Go through items, remove unnecessary quotes.
        for(int i = 0; i < items.length; i++) {
            boolean skip = false;
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < items[i].length(); j++) {
                if(skip) {
                    skip = false;
                } else {
                    if(items[i].charAt(j) == '\\') {
                        skip = true;
                    } else if(items[i].charAt(j) != '"') {
                        sb.append(items[i].charAt(j));
                    }
                }
            }
            items[i] = sb.toString();
        }
        //Split by colon and add each key value to a temporary map.
        Map mBlockItems = new HashMap();
        for(String c : items) {
            StringBuilder keySB = new StringBuilder();
            for(int i = 0; i < c.length(); i++) {
                if(c.charAt(i) != ':') {
                    keySB.append(c.charAt(i));
                } else {
                    break;
                }
            }
            c = nextChar(':', c);
            c = c.substring(1,c.length());
            //Check if there is a leading space
            if(c.charAt(0) == ' ') {
                c = c.substring(1,c.length());
            }
            mBlockItems.put(keySB.toString(), c);
        }
    }
}

// var eventdata = [{
//     "id": "YTTB1",
//     "day": 1,
//     "start": "11:00",
//     "end": "12:00",
//     "draggable": false,
//     "resizable": false,
//     "selected": false,
//     "bcol": "lightgrey",
//     "fcol": "#121212",
//     "info": "Lecture<br>\nCOSC301<br>\n\n<a href=\"https:\/\/maps.google.com\/?q=-45.8636426650169,170.513851671001&ll=-45.8636426650169,170.513851671001&z=19&t=k\" target=\"_blank\">SDAV1<\/a>\n\n<div class=\"sv-hidden-md sv-hidden-lg sv-hidden-sm\">\n\t<a onclick=\"$('.uo_mobile_more_COSC301S1DNI2017LL1').toggle()\" href=\"javascript:void(0)\">More...<\/a>\n\t<div class=\"uo_mobile_more_COSC301S1DNI2017LL1\" style=\"display:none\">\n<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Network Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>St David Seminar Rm 1 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> St David Complex <\/span>\n<\/div>\n\n\t<\/div>\n<\/div>",
//     "ttip": "<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Network Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>St David Seminar Rm 1 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> St David Complex <\/span>\n<\/div>\n",
//     "preinfo": "",
//     "isnote": false,
//     "extra": {"tts_seqn": 9, "rcid": ""}
// }, {