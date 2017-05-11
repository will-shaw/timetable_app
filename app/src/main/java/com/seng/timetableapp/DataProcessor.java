package com.seng.timetableapp;

import android.util.ArrayMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/**
 * Created by shaun on 5/8/2017.
 */

public class DataProcessor {

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
    * Helper function to perform nextChar multiple times
    * @param c The character to trim to
    * @param s The string to trim
    * @param n the number of times to run
    * @return The trimmed string
    */
   private String mulNextChar(char c, String s, int n) {
      for(int i = 0; i < n; i++) {
         s = nextChar(c, s);
      }
      return s;
   }
   
   /**
    * Helper function to return substring up until first occurance of a character.
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
      JSONObject jo = new JSONObject(s);
      TTEvent t = new TTEvent();
      t.setId(jo.getString("id"));
      t.setDay(jo.getString("day"));
      t.setStart(Integer.parseInt(seqUntilChar(':',jo.getString("start"))));
      t.setEnd(Integer.parseInt(seqUntilChar(':',jo.getString("end"))));
      t.setBcol(jo.getString("bcol"));
      t.setFcol(jo.getString("fcol"));
      String info = jo.getString("info");
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
      JSONObject jo = new JSONObject(options);
      String beginDate = jo.getString("title");
      beginDate = beginDate.substring(32); //Skip "Timetable for (Now showing dates "
      beginDate = seqUntilChar(' ', beginDate);
      SimpleDateFormat sdf = new SimpleDateFormat("d/m/Y");
      Date date = sdf.parse(beginDate);
      Collection<String> blocks = getBlocks('{', '}', eventData);
      Collection<TTEvent> items = new ArrayList<>();
      for(String s : blocks) {
         TTEvent t = parseEventString(s);
         t.setDate(date);
         items.add(t);
      }
      return items;
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
