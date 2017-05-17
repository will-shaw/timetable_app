package parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.nio.channels.Pipe;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.text.SimpleDateFormat;

import domain.TTEvent;

/** Data Parser
 * Created by shaun on 5/8/2017.
 */

public class DataProcessor {

    public DataProcessor() {};

    public DataProcessor(boolean test) {
        System.out.printf(eventData());
        System.out.println(options());
        Collection<TTEvent> events = parseWebData(eventData(), options());

        for (TTEvent event: events
                ) {
            System.out.println(event.getLectureName());
        }

    }

    /**
     * Helper function to trim unnecessary characters from the input string.
     * @param c The character to trim to
     * @param s The string to trim
     * @return The substring starting from the first occurrance of c, to the end of the string, or s if the character isn't found.
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
     * Helper function to perform nextChar multiple times
     * @param c The character to trim to
     * @param s The string to trim
     * @param n the number of times to run
     * @return The trimmed string
     */
    private String mulNextChar(char c, String s, int n) {
        for(int i = 0; i < n; i++) {
            s = nextChar(c, s).substring(1);
        }
        return s;
    }

    /**
     * Helper function to return substring up until first occurrance of a character.
     * @param c The character to grab until
     * @param s The string to work on
     * @return The substring starting going to the first c, or s if the character isn't found.
     */
    private String seqUntilChar(char c, String s) {
        boolean f = false;
        int i;
        for(i = 0; i < s.length(); i++) {
            if(s.charAt(i) == (c)) {
                f = true;
                break;
            }
        }
        if(f) {
            return s.substring(0,i);
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
    public Collection<String> getBlocks(char cS, char cE, String s) {
        Collection<String> blocks = new ArrayList<>();
        String t = nextChar(cS, s);
        int nests = 0;
        int start = 0;
        boolean lookForStart = true;
        int i;
        for(i = 0; i < t.length(); i++) {
            //If starting a new block, skip over in-between characters
            if(lookForStart && t.charAt(i) != cS) {
                continue;
            } else if(lookForStart) {
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
                //System.out.println("ADDED -> Start: " + start + ", length: " + i + t.substring(start,i+1));
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
    private TTEvent parseEventString(String s) {
        String info;
        TTEvent t = new TTEvent();
        JsonParser jp = new JsonParser();
        //System.out.println(s);
        JsonObject jo = jp.parse(s).getAsJsonObject();
        t.setId(jo.get("id").getAsString());
        t.setDay(jo.get("day").getAsInt());
        t.setStart(Integer.parseInt(seqUntilChar(':', jo.get("start").getAsString())));
        t.setEnd(Integer.parseInt(seqUntilChar(':', jo.get("end").getAsString())));
        t.setBcol(jo.get("bcol").getAsString());
        t.setFcol(jo.get("fcol").getAsString());
        info = jo.get("info").getAsString();
        //Begin parsing info
        info = nextChar('\n', info).substring(1);
        t.setLectureName(seqUntilChar('<',info));
        info = nextChar('\"', info).substring(1); //Skip to start of url
        t.setGmapsUrl(seqUntilChar('\"', info));
        info = nextChar('>', info).substring(1); //Skip to room code
        t.setRoomCode(seqUntilChar('<', info));
        info = mulNextChar('<', info, 13);
        info = nextChar('>', info).substring(1); //Skip to paper name
        t.setPaperName(seqUntilChar('<', info));
        info = mulNextChar('<', info, 10);
        info = nextChar('>', info).substring(1); //Skip to start of stream
        t.setStream(seqUntilChar('<', info));
        info = mulNextChar('<', info, 10);
        info = nextChar('>', info).substring(1); //Skip to start of room name
        t.setRoomName(seqUntilChar('<', info));
        info = mulNextChar('<', info, 10);
        info = nextChar('>', info).substring(1); //Skip to start of room name
        t.setBuildingName(seqUntilChar('<', info));
        //End parsing
        return t;
    }

    public Collection<TTEvent> parseWebData(String eventData, String options) {
        options = nextChar('{', options).trim();
        options = options.substring(0,options.length()-1);
        //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>" + options);
        options = nextChar('i', options);
        options = mulNextChar(':', options, 3);
        options = nextChar(',', options);
        options = "{" + options.substring(1);
        JsonParser jp = new JsonParser();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>" + options);
        //System.out.printf(">>>>>>>>>>>>>>>>>>>>>>>" + eventData);
        JsonObject jo = jp.parse(options).getAsJsonObject();
        String beginDate = jo.get("title").getAsString();
        beginDate = beginDate.substring(34); //Skip "Timetable for (Now showing dates "
        beginDate = seqUntilChar(' ', beginDate);
        SimpleDateFormat sdf = new SimpleDateFormat("d/m/Y");
        Date date;
        try {
            date = sdf.parse(beginDate);
        } catch(ParseException e) {
            date = new Date(0);
        }
        eventData = nextChar('{', eventData).trim();
        eventData = eventData.substring(0, eventData.length()-1);
        Collection<String> blocks = getBlocks('{', '}', eventData);
        Collection<TTEvent> items = new ArrayList<>();
        for(String j : blocks) {
            TTEvent t = parseEventString(j);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            t.setDate(cal);
            items.add(t);
        }
        return items;
    }

    private String eventData() {
        return "var eventdata = [{\"id\":\"YTTB1\",\"day\":1,\"start\":\"09:00\",\"end\":\"10:00\",\"draggable\":false,\"resizable\":false,\"selected\":false,\"bcol\":\"lightgrey\",\"fcol\":\"#121212\",\"info\":\"Lecture<br>\\nCOSC343<br>\\n\\n<a href=\\\"https:\\/\\/maps.google.com\\/?q=-45.867074072439,170.513475268215&ll=-45.867074072439,170.513475268215&z=19&t=k\\\" target=\\\"_blank\\\">BURN7<\\/a>\\n\\n<div class=\\\"sv-hidden-md sv-hidden-lg sv-hidden-sm\\\">\\n\\t<a onclick=\\\"$('.uo_mobile_more_COSC343S1DNI2017LL1').toggle()\\\" href=\\\"javascript:void(0)\\\">More...<\\/a>\\n\\t<div class=\\\"uo_mobile_more_COSC343S1DNI2017LL1\\\" style=\\\"display:none\\\">\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Artificial Intelligence<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>L1<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Burns 7 <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> Arts 1 (Burns) <\\/span>\\n<\\/div>\\n\\n\\t<\\/div>\\n<\\/div>\",\"ttip\":\"<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Artificial Intelligence<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>L1<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Burns 7 <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> Arts 1 (Burns) <\\/span>\\n<\\/div>\\n\",\"preinfo\":\"\",\"isnote\":false,\"extra\":{\"tts_seqn\":5,\"rcid\":\"\"}},{\"id\":\"YTTB2\",\"day\":1,\"start\":\"12:00\",\"end\":\"14:00\",\"draggable\":false,\"resizable\":false,\"selected\":false,\"bcol\":\"#80FFFF\",\"fcol\":\"#000000\",\"info\":\"Computer Lab<br>\\nINFO323<br>\\n\\n<a href=\\\"https:\\/\\/maps.google.com\\/?q=-45.8639136763326,170.513502564758&ll=-45.8639136763326,170.513502564758&z=19&t=k\\\" target=\\\"_blank\\\">NCAL<\\/a>\\n\\n<div class=\\\"sv-hidden-md sv-hidden-lg sv-hidden-sm\\\">\\n\\t<a onclick=\\\"$('.uo_mobile_more_INFO323S1DNI2017CZ1').toggle()\\\" href=\\\"javascript:void(0)\\\">More...<\\/a>\\n\\t<div class=\\\"uo_mobile_more_INFO323S1DNI2017CZ1\\\" style=\\\"display:none\\\">\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Distributed Information Systems<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Z1<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>North CAL Lab. <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> Science III <\\/span>\\n<\\/div>\\n\\n\\t<\\/div>\\n<\\/div>\",\"ttip\":\"<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Distributed Information Systems<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Z1<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>North CAL Lab. <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> Science III <\\/span>\\n<\\/div>\\n\",\"preinfo\":\"\",\"isnote\":false,\"extra\":{\"tts_seqn\":1,\"rcid\":\"\"}},{\"id\":\"YTTB3\",\"day\":2,\"start\":\"16:00\",\"end\":\"17:00\",\"draggable\":false,\"resizable\":false,\"selected\":false,\"bcol\":\"#FFD700\",\"fcol\":\"#000000\",\"info\":\"Tutorial<br>\\nCOSC345<br>\\n\\n<a href=\\\"https:\\/\\/maps.google.com\\/?q=-45.8636426650169,170.513851671001&ll=-45.8636426650169,170.513851671001&z=19&t=k\\\" target=\\\"_blank\\\">SDAV2<\\/a>\\n\\n<div clas";
    }

    private String options() {
        return "var options = {\"onEventHover\":function(e,ui){sitsjqtt_increase_event_size($(this)); if(typeof ui.extraData.extra.eirh==='string'&&ui.extraData.extra.eirh!=''){return true;}return false;},\"onEventEditClick\":function(e,ui){ttb_editDetails(ui.extraData.extra.eire, ui.extraData.extra.eirr, ui.extraData.extra.eirh);},\"height\":\"90%\",\"in_client\":\"inBrowser\",\"title\":\"Timetable for  (Now showing dates 15\\/05\\/2017 to 21\\/05\\/2017)\",\"start_day\":1,\"no_days\":7,\"start_time\":8,\"no_hours\":14,\"view\":\"DAY_BY_TIME\",\"snap_mins\":30,\"view_toggle\":\"Y\",\"show_time\":\"N\",\"dim_on_select\":false,\"more_info\":\"<B>More<\\/B>\"};    ";
    }

}