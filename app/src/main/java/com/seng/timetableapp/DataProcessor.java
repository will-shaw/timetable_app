package com.seng.timetableapp;

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
        for(i = 0; (i < s.length() && !f); i++) {
            if(s.charAt(i) == (c)) {
                f = true;
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
     * @return The substring starting with cS and ending with cE, or null if no block exists.
     */
    private String getBlock(char cS, char cE, String s) {
        String t = nextChar(cS, s);
        //Handle if input string was already entire block, or if it did not contain cS.
        if(t.charAt(0) == (cS) && t.charAt(t.length()-1) == (cE)) {
            return t;
        } else if(t.equals(s)) {
            return null;
        }
        //Actual function
        int nests = 0;
        int i;
        for(i = 0; i < t.length(); i++) {
            if(t.charAt(i) == cS) {
                ++nests;
            } else if(t.charAt(i) == cE) {
                --nests;
            }
            //If nests is zero, we've finished the block and can return it.
            if(nests == 0) {
                return t.substring(0,i+1);
            }
            //If nests is negative, brackets arent set up properly.
            if(nests < 0) {
                return null;
            }
        }
        //If it reaches the end of the loop something is mismatched.
        return null;
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