/**
 * Created by jacques on 4/05/17.
 */
var scraper = (function () {
    var _scraper_module = {};

    _scraper_module.timeTableTwoDay = function (selector) {
        // Our timetable is 8th item of a set of divs with class name sv-list-group sv-portal-2-col
        var timeTable = document.getElementsByClassName(selector)[8].getElementsByTagName("table")[0];
        return timeTable;
    };

    //TODO properly make use of private and public module system
    _scraper_module.timeTableWeek = function (selector) {
        var timeTableScript = document.getElementsByTagName(selector);
        for (var i = 0; i < timeTableScript.length; i++) {
            if (timeTableScript[i].innerText.length > 5000){
                return timeTableScript[i];
            }
        }
        return null;
    };

    return _scraper_module;
})();

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
//     "id": "YTTB2",
//     "day": 1,
//     "start": "12:00",
//     "end": "14:00",
//     "draggable": false,
//     "resizable": false,
//     "selected": false,
//     "bcol": "#80FFFF",
//     "fcol": "#000000",
//     "info": "Computer Lab<br>\nINFO323<br>\n\n<a href=\"https:\/\/maps.google.com\/?q=-45.8639136763326,170.513502564758&ll=-45.8639136763326,170.513502564758&z=19&t=k\" target=\"_blank\">NCAL<\/a>\n\n<div class=\"sv-hidden-md sv-hidden-lg sv-hidden-sm\">\n\t<a onclick=\"$('.uo_mobile_more_INFO323S1DNI2017CZ1').toggle()\" href=\"javascript:void(0)\">More...<\/a>\n\t<div class=\"uo_mobile_more_INFO323S1DNI2017CZ1\" style=\"display:none\">\n<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Distributed Information Systems<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Z1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>North CAL Lab. <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Science III <\/span>\n<\/div>\n\n\t<\/div>\n<\/div>",
//     "ttip": "<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Distributed Information Systems<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Z1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>North CAL Lab. <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Science III <\/span>\n<\/div>\n",
//     "preinfo": "",
//     "isnote": false,
//     "extra": {"tts_seqn": 3, "rcid": ""}
// }, {
//     "id": "YTTB3",
//     "day": 1,
//     "start": "14:00",
//     "end": "16:00",
//     "draggable": false,
//     "resizable": false,
//     "selected": false,
//     "bcol": "#09BBF7",
//     "fcol": "#000000",
//     "info": "Practical<br>\nCOSC301<br>\n\n<a href=\"https:\/\/maps.google.com\/?q=-45.8670533441689,170.518171263001&ll=-45.8670533441689,170.518171263001&z=19&t=k\" target=\"_blank\">OWG38<\/a>\n\n<div class=\"sv-hidden-md sv-hidden-lg sv-hidden-sm\">\n\t<a onclick=\"$('.uo_mobile_more_COSC301S1DNI2017PP2').toggle()\" href=\"javascript:void(0)\">More...<\/a>\n\t<div class=\"uo_mobile_more_COSC301S1DNI2017PP2\" style=\"display:none\">\n<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Network Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>P2<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Owheo Lab G38 COSC <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Owheo Building <\/span>\n<\/div>\n\n\t<\/div>\n<\/div>",
//     "ttip": "<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Network Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>P2<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Owheo Lab G38 COSC <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Owheo Building <\/span>\n<\/div>\n",
//     "preinfo": "",
//     "isnote": false,
//     "extra": {"tts_seqn": 10, "rcid": ""}
// }, {
//     "id": "YTTB4",
//     "day": 2,
//     "start": "12:00",
//     "end": "13:00",
//     "draggable": false,
//     "resizable": false,
//     "selected": false,
//     "bcol": "lightgrey",
//     "fcol": "#121212",
//     "info": "Lecture<br>\nINFO312<br>\n\n<a href=\"https:\/\/maps.google.com\/?q=-45.8659830734156,170.513554965408&ll=-45.8659830734156,170.513554965408&z=19&t=k\" target=\"_blank\">RGS2<\/a>\n\n<div class=\"sv-hidden-md sv-hidden-lg sv-hidden-sm\">\n\t<a onclick=\"$('.uo_mobile_more_INFO312FYDNI2017LL1').toggle()\" href=\"javascript:void(0)\">More...<\/a>\n\t<div class=\"uo_mobile_more_INFO312FYDNI2017LL1\" style=\"display:none\">\n<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Information Systems Development Project<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Te Tumu RGS2 Te Riu <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Richardson Building <\/span>\n<\/div>\n\n\t<\/div>\n<\/div>",
//     "ttip": "<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Information Systems Development Project<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Te Tumu RGS2 Te Riu <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Richardson Building <\/span>\n<\/div>\n",
//     "preinfo": "",
//     "isnote": false,
//     "extra": {"tts_seqn": 2, "rcid": ""}
// }, {
//     "id": "YTTB5",
//     "day": 3,
//     "start": "12:00",
//     "end": "13:00",
//     "draggable": false,
//     "resizable": false,
//     "selected": false,
//     "bcol": "lightgrey",
//     "fcol": "#121212",
//     "info": "Lecture<br>\nINFO323<br>\n\n<a href=\"https:\/\/maps.google.com\/?q=-45.8636426650169,170.513851671001&ll=-45.8636426650169,170.513851671001&z=19&t=k\" target=\"_blank\">SDAV5<\/a>\n\n<div class=\"sv-hidden-md sv-hidden-lg sv-hidden-sm\">\n\t<a onclick=\"$('.uo_mobile_more_INFO323S1DNI2017LL1').toggle()\" href=\"javascript:void(0)\">More...<\/a>\n\t<div class=\"uo_mobile_more_INFO323S1DNI2017LL1\" style=\"display:none\">\n<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Distributed Information Systems<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>St David Seminar Rm 5 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> St David Complex <\/span>\n<\/div>\n\n\t<\/div>\n<\/div>",
//     "ttip": "<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Distributed Information Systems<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>St David Seminar Rm 5 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> St David Complex <\/span>\n<\/div>\n",
//     "preinfo": "",
//     "isnote": false,
//     "extra": {"tts_seqn": 5, "rcid": ""}
// }, {
//     "id": "YTTB6",
//     "day": 3,
//     "start": "14:00",
//     "end": "16:00",
//     "draggable": false,
//     "resizable": false,
//     "selected": false,
//     "bcol": "lightgrey",
//     "fcol": "#121212",
//     "info": "Lecture<br>\nSENG301<br>\n\n<a href=\"https:\/\/maps.google.com\/?q=-45.8636426650169,170.513851671001&ll=-45.8636426650169,170.513851671001&z=19&t=k\" target=\"_blank\">SDAV2<\/a>\n\n<div class=\"sv-hidden-md sv-hidden-lg sv-hidden-sm\">\n\t<a onclick=\"$('.uo_mobile_more_SENG301S1DNI2017LL1').toggle()\" href=\"javascript:void(0)\">More...<\/a>\n\t<div class=\"uo_mobile_more_SENG301S1DNI2017LL1\" style=\"display:none\">\n<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Software Project Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>St David Seminar Rm 2 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> St David Complex <\/span>\n<\/div>\n\n\t<\/div>\n<\/div>",
//     "ttip": "<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Software Project Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>St David Seminar Rm 2 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> St David Complex <\/span>\n<\/div>\n",
//     "preinfo": "",
//     "isnote": false,
//     "extra": {"tts_seqn": 7, "rcid": ""}
// }, {
//     "id": "YTTB7",
//     "day": 4,
//     "start": "11:00",
//     "end": "12:00",
//     "draggable": false,
//     "resizable": false,
//     "selected": false,
//     "bcol": "lightgrey",
//     "fcol": "#121212",
//     "info": "Lecture<br>\nCOSC301<br>\n\n<a href=\"https:\/\/maps.google.com\/?q=-45.867074072439,170.513475268215&ll=-45.867074072439,170.513475268215&z=19&t=k\" target=\"_blank\">BURN7<\/a>\n\n<div class=\"sv-hidden-md sv-hidden-lg sv-hidden-sm\">\n\t<a onclick=\"$('.uo_mobile_more_COSC301S1DNI2017LL1').toggle()\" href=\"javascript:void(0)\">More...<\/a>\n\t<div class=\"uo_mobile_more_COSC301S1DNI2017LL1\" style=\"display:none\">\n<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Network Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Burns 7 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Arts 1 (Burns) <\/span>\n<\/div>\n\n\t<\/div>\n<\/div>",
//     "ttip": "<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Network Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Burns 7 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Arts 1 (Burns) <\/span>\n<\/div>\n",
//     "preinfo": "",
//     "isnote": false,
//     "extra": {"tts_seqn": 8, "rcid": ""}
// }, {
//     "id": "YTTB8",
//     "day": 4,
//     "start": "13:00",
//     "end": "15:00",
//     "draggable": false,
//     "resizable": false,
//     "selected": false,
//     "bcol": "#09BBF7",
//     "fcol": "#000000",
//     "info": "Practical<br>\nCOSC301<br>\n\n<a href=\"https:\/\/maps.google.com\/?q=-45.8670533441689,170.518171263001&ll=-45.8670533441689,170.518171263001&z=19&t=k\" target=\"_blank\">OWG38<\/a>\n\n<div class=\"sv-hidden-md sv-hidden-lg sv-hidden-sm\">\n\t<a onclick=\"$('.uo_mobile_more_COSC301S1DNI2017PQ2').toggle()\" href=\"javascript:void(0)\">More...<\/a>\n\t<div class=\"uo_mobile_more_COSC301S1DNI2017PQ2\" style=\"display:none\">\n<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Network Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Q2<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Owheo Lab G38 COSC <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Owheo Building <\/span>\n<\/div>\n\n\t<\/div>\n<\/div>",
//     "ttip": "<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Network Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Q2<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Owheo Lab G38 COSC <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Owheo Building <\/span>\n<\/div>\n",
//     "preinfo": "",
//     "isnote": false,
//     "extra": {"tts_seqn": 11, "rcid": ""}
// }, {
//     "id": "YTTB9",
//     "day": 4,
//     "start": "11:00",
//     "end": "13:00",
//     "draggable": false,
//     "resizable": false,
//     "selected": false,
//     "bcol": "#80FFFF",
//     "fcol": "#000000",
//     "info": "Computer Lab<br>\nSENG301<br>\n\n<a href=\"https:\/\/maps.google.com\/?q=-45.8659830734156,170.513554965408&ll=-45.8659830734156,170.513554965408&z=19&t=k\" target=\"_blank\">CNCAL<\/a>\n\n<div class=\"sv-hidden-md sv-hidden-lg sv-hidden-sm\">\n\t<a onclick=\"$('.uo_mobile_more_SENG301S1DNI2017CA1').toggle()\" href=\"javascript:void(0)\">More...<\/a>\n\t<div class=\"uo_mobile_more_SENG301S1DNI2017CA1\" style=\"display:none\">\n<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Software Project Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>A1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Central CAL (Richardson) <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Richardson Building <\/span>\n<\/div>\n\n\t<\/div>\n<\/div>",
//     "ttip": "<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Software Project Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>A1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Central CAL (Richardson) <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Richardson Building <\/span>\n<\/div>\n",
//     "preinfo": "",
//     "isnote": false,
//     "extra": {"tts_seqn": 6, "rcid": ""}
// }, {
//     "id": "YTTB10",
//     "day": 5,
//     "start": "09:00",
//     "end": "11:00",
//     "draggable": false,
//     "resizable": false,
//     "selected": false,
//     "bcol": "lightgrey",
//     "fcol": "#121212",
//     "info": "Lecture<br>\nINFO312<br>\n\n<a href=\"https:\/\/maps.google.com\/?q=-45.8664204533545,170.515870883468&ll=-45.8664204533545,170.515870883468&z=19&t=k\" target=\"_blank\">CO805<\/a>\n\n<div class=\"sv-hidden-md sv-hidden-lg sv-hidden-sm\">\n\t<a onclick=\"$('.uo_mobile_more_INFO312FYDNI2017LA1').toggle()\" href=\"javascript:void(0)\">More...<\/a>\n\t<div class=\"uo_mobile_more_INFO312FYDNI2017LA1\" style=\"display:none\">\n<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Information Systems Development Project<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>A1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Info Sci  Commerce 8.05 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Commerce Building <\/span>\n<\/div>\n\n\t<\/div>\n<\/div>",
//     "ttip": "<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Information Systems Development Project<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>A1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Info Sci  Commerce 8.05 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Commerce Building <\/span>\n<\/div>\n",
//     "preinfo": "",
//     "isnote": false,
//     "extra": {"tts_seqn": 1, "rcid": ""}
// }, {
//     "id": "YTTB11",
//     "day": 5,
//     "start": "11:00",
//     "end": "12:00",
//     "draggable": false,
//     "resizable": false,
//     "selected": false,
//     "bcol": "lightgrey",
//     "fcol": "#121212",
//     "info": "Lecture<br>\nINFO323<br>\n\n<a href=\"https:\/\/maps.google.com\/?q=-45.8636426650169,170.513851671001&ll=-45.8636426650169,170.513851671001&z=19&t=k\" target=\"_blank\">SDAV1<\/a>\n\n<div class=\"sv-hidden-md sv-hidden-lg sv-hidden-sm\">\n\t<a onclick=\"$('.uo_mobile_more_INFO323S1DNI2017LL1').toggle()\" href=\"javascript:void(0)\">More...<\/a>\n\t<div class=\"uo_mobile_more_INFO323S1DNI2017LL1\" style=\"display:none\">\n<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Distributed Information Systems<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>St David Seminar Rm 1 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> St David Complex <\/span>\n<\/div>\n\n\t<\/div>\n<\/div>",
//     "ttip": "<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Distributed Information Systems<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>St David Seminar Rm 1 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> St David Complex <\/span>\n<\/div>\n",
//     "preinfo": "",
//     "isnote": false,
//     "extra": {"tts_seqn": 4, "rcid": ""}
// }];
// sits_timetable_widget("#newtimetable", "CREATE", options, eventdata);

/*
if (typeof($) == "function") {
    newtimetable();
} else {
    sits_attach_event(window, "load", newtimetable);
}
function newtimetable() {
    var options = {
        "onEventHover": function (e, ui) {
            sitsjqtt_increase_event_size($(this));
            if (typeof ui.extraData.extra.eirh === 'string' && ui.extraData.extra.eirh != '') {
                return true;
            }
            return false;
        },
        "onEventEditClick": function (e, ui) {
            ttb_editDetails(ui.extraData.extra.eire, ui.extraData.extra.eirr, ui.extraData.extra.eirh);
        },
        "height": "90%",
        "in_client": "inBrowser",
        "title": "Timetable for  (Now showing dates 01\/05\/2017 to 07\/05\/2017)",
        "start_day": 1,
        "no_days": 7,
        "start_time": 8,
        "no_hours": 14,
        "view": "DAY_BY_TIME",
        "snap_mins": 30,
        "view_toggle": "Y",
        "show_time": "N",
        "dim_on_select": false,
        "more_info": "<B>More<\/B>"
    };
    var eventdata = [{
        "id": "YTTB1",
        "day": 1,
        "start": "11:00",
        "end": "12:00",
        "draggable": false,
        "resizable": false,
        "selected": false,
        "bcol": "lightgrey",
        "fcol": "#121212",
        "info": "Lecture<br>\nCOSC301<br>\n\n<a href=\"https:\/\/maps.google.com\/?q=-45.8636426650169,170.513851671001&ll=-45.8636426650169,170.513851671001&z=19&t=k\" target=\"_blank\">SDAV1<\/a>\n\n<div class=\"sv-hidden-md sv-hidden-lg sv-hidden-sm\">\n\t<a onclick=\"$('.uo_mobile_more_COSC301S1DNI2017LL1').toggle()\" href=\"javascript:void(0)\">More...<\/a>\n\t<div class=\"uo_mobile_more_COSC301S1DNI2017LL1\" style=\"display:none\">\n<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Network Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>St David Seminar Rm 1 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> St David Complex <\/span>\n<\/div>\n\n\t<\/div>\n<\/div>",
        "ttip": "<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Network Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>St David Seminar Rm 1 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> St David Complex <\/span>\n<\/div>\n",
        "preinfo": "",
        "isnote": false,
        "extra": {"tts_seqn": 9, "rcid": ""}
    }, {
        "id": "YTTB2",
        "day": 1,
        "start": "12:00",
        "end": "14:00",
        "draggable": false,
        "resizable": false,
        "selected": false,
        "bcol": "#80FFFF",
        "fcol": "#000000",
        "info": "Computer Lab<br>\nINFO323<br>\n\n<a href=\"https:\/\/maps.google.com\/?q=-45.8639136763326,170.513502564758&ll=-45.8639136763326,170.513502564758&z=19&t=k\" target=\"_blank\">NCAL<\/a>\n\n<div class=\"sv-hidden-md sv-hidden-lg sv-hidden-sm\">\n\t<a onclick=\"$('.uo_mobile_more_INFO323S1DNI2017CZ1').toggle()\" href=\"javascript:void(0)\">More...<\/a>\n\t<div class=\"uo_mobile_more_INFO323S1DNI2017CZ1\" style=\"display:none\">\n<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Distributed Information Systems<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Z1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>North CAL Lab. <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Science III <\/span>\n<\/div>\n\n\t<\/div>\n<\/div>",
        "ttip": "<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Distributed Information Systems<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Z1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>North CAL Lab. <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Science III <\/span>\n<\/div>\n",
        "preinfo": "",
        "isnote": false,
        "extra": {"tts_seqn": 3, "rcid": ""}
    }, {
        "id": "YTTB3",
        "day": 1,
        "start": "14:00",
        "end": "16:00",
        "draggable": false,
        "resizable": false,
        "selected": false,
        "bcol": "#09BBF7",
        "fcol": "#000000",
        "info": "Practical<br>\nCOSC301<br>\n\n<a href=\"https:\/\/maps.google.com\/?q=-45.8670533441689,170.518171263001&ll=-45.8670533441689,170.518171263001&z=19&t=k\" target=\"_blank\">OWG38<\/a>\n\n<div class=\"sv-hidden-md sv-hidden-lg sv-hidden-sm\">\n\t<a onclick=\"$('.uo_mobile_more_COSC301S1DNI2017PP2').toggle()\" href=\"javascript:void(0)\">More...<\/a>\n\t<div class=\"uo_mobile_more_COSC301S1DNI2017PP2\" style=\"display:none\">\n<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Network Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>P2<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Owheo Lab G38 COSC <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Owheo Building <\/span>\n<\/div>\n\n\t<\/div>\n<\/div>",
        "ttip": "<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Network Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>P2<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Owheo Lab G38 COSC <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Owheo Building <\/span>\n<\/div>\n",
        "preinfo": "",
        "isnote": false,
        "extra": {"tts_seqn": 10, "rcid": ""}
    }, {
        "id": "YTTB4",
        "day": 2,
        "start": "12:00",
        "end": "13:00",
        "draggable": false,
        "resizable": false,
        "selected": false,
        "bcol": "lightgrey",
        "fcol": "#121212",
        "info": "Lecture<br>\nINFO312<br>\n\n<a href=\"https:\/\/maps.google.com\/?q=-45.8659830734156,170.513554965408&ll=-45.8659830734156,170.513554965408&z=19&t=k\" target=\"_blank\">RGS2<\/a>\n\n<div class=\"sv-hidden-md sv-hidden-lg sv-hidden-sm\">\n\t<a onclick=\"$('.uo_mobile_more_INFO312FYDNI2017LL1').toggle()\" href=\"javascript:void(0)\">More...<\/a>\n\t<div class=\"uo_mobile_more_INFO312FYDNI2017LL1\" style=\"display:none\">\n<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Information Systems Development Project<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Te Tumu RGS2 Te Riu <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Richardson Building <\/span>\n<\/div>\n\n\t<\/div>\n<\/div>",
        "ttip": "<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Information Systems Development Project<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Te Tumu RGS2 Te Riu <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Richardson Building <\/span>\n<\/div>\n",
        "preinfo": "",
        "isnote": false,
        "extra": {"tts_seqn": 2, "rcid": ""}
    }, {
        "id": "YTTB5",
        "day": 3,
        "start": "12:00",
        "end": "13:00",
        "draggable": false,
        "resizable": false,
        "selected": false,
        "bcol": "lightgrey",
        "fcol": "#121212",
        "info": "Lecture<br>\nINFO323<br>\n\n<a href=\"https:\/\/maps.google.com\/?q=-45.8636426650169,170.513851671001&ll=-45.8636426650169,170.513851671001&z=19&t=k\" target=\"_blank\">SDAV5<\/a>\n\n<div class=\"sv-hidden-md sv-hidden-lg sv-hidden-sm\">\n\t<a onclick=\"$('.uo_mobile_more_INFO323S1DNI2017LL1').toggle()\" href=\"javascript:void(0)\">More...<\/a>\n\t<div class=\"uo_mobile_more_INFO323S1DNI2017LL1\" style=\"display:none\">\n<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Distributed Information Systems<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>St David Seminar Rm 5 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> St David Complex <\/span>\n<\/div>\n\n\t<\/div>\n<\/div>",
        "ttip": "<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Distributed Information Systems<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>St David Seminar Rm 5 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> St David Complex <\/span>\n<\/div>\n",
        "preinfo": "",
        "isnote": false,
        "extra": {"tts_seqn": 5, "rcid": ""}
    }, {
        "id": "YTTB6",
        "day": 3,
        "start": "14:00",
        "end": "16:00",
        "draggable": false,
        "resizable": false,
        "selected": false,
        "bcol": "lightgrey",
        "fcol": "#121212",
        "info": "Lecture<br>\nSENG301<br>\n\n<a href=\"https:\/\/maps.google.com\/?q=-45.8636426650169,170.513851671001&ll=-45.8636426650169,170.513851671001&z=19&t=k\" target=\"_blank\">SDAV2<\/a>\n\n<div class=\"sv-hidden-md sv-hidden-lg sv-hidden-sm\">\n\t<a onclick=\"$('.uo_mobile_more_SENG301S1DNI2017LL1').toggle()\" href=\"javascript:void(0)\">More...<\/a>\n\t<div class=\"uo_mobile_more_SENG301S1DNI2017LL1\" style=\"display:none\">\n<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Software Project Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>St David Seminar Rm 2 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> St David Complex <\/span>\n<\/div>\n\n\t<\/div>\n<\/div>",
        "ttip": "<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Software Project Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>St David Seminar Rm 2 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> St David Complex <\/span>\n<\/div>\n",
        "preinfo": "",
        "isnote": false,
        "extra": {"tts_seqn": 7, "rcid": ""}
    }, {
        "id": "YTTB7",
        "day": 4,
        "start": "11:00",
        "end": "12:00",
        "draggable": false,
        "resizable": false,
        "selected": false,
        "bcol": "lightgrey",
        "fcol": "#121212",
        "info": "Lecture<br>\nCOSC301<br>\n\n<a href=\"https:\/\/maps.google.com\/?q=-45.867074072439,170.513475268215&ll=-45.867074072439,170.513475268215&z=19&t=k\" target=\"_blank\">BURN7<\/a>\n\n<div class=\"sv-hidden-md sv-hidden-lg sv-hidden-sm\">\n\t<a onclick=\"$('.uo_mobile_more_COSC301S1DNI2017LL1').toggle()\" href=\"javascript:void(0)\">More...<\/a>\n\t<div class=\"uo_mobile_more_COSC301S1DNI2017LL1\" style=\"display:none\">\n<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Network Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Burns 7 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Arts 1 (Burns) <\/span>\n<\/div>\n\n\t<\/div>\n<\/div>",
        "ttip": "<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Network Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Burns 7 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Arts 1 (Burns) <\/span>\n<\/div>\n",
        "preinfo": "",
        "isnote": false,
        "extra": {"tts_seqn": 8, "rcid": ""}
    }, {
        "id": "YTTB8",
        "day": 4,
        "start": "13:00",
        "end": "15:00",
        "draggable": false,
        "resizable": false,
        "selected": false,
        "bcol": "#09BBF7",
        "fcol": "#000000",
        "info": "Practical<br>\nCOSC301<br>\n\n<a href=\"https:\/\/maps.google.com\/?q=-45.8670533441689,170.518171263001&ll=-45.8670533441689,170.518171263001&z=19&t=k\" target=\"_blank\">OWG38<\/a>\n\n<div class=\"sv-hidden-md sv-hidden-lg sv-hidden-sm\">\n\t<a onclick=\"$('.uo_mobile_more_COSC301S1DNI2017PQ2').toggle()\" href=\"javascript:void(0)\">More...<\/a>\n\t<div class=\"uo_mobile_more_COSC301S1DNI2017PQ2\" style=\"display:none\">\n<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Network Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Q2<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Owheo Lab G38 COSC <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Owheo Building <\/span>\n<\/div>\n\n\t<\/div>\n<\/div>",
        "ttip": "<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Network Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Q2<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Owheo Lab G38 COSC <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Owheo Building <\/span>\n<\/div>\n",
        "preinfo": "",
        "isnote": false,
        "extra": {"tts_seqn": 11, "rcid": ""}
    }, {
        "id": "YTTB9",
        "day": 4,
        "start": "11:00",
        "end": "13:00",
        "draggable": false,
        "resizable": false,
        "selected": false,
        "bcol": "#80FFFF",
        "fcol": "#000000",
        "info": "Computer Lab<br>\nSENG301<br>\n\n<a href=\"https:\/\/maps.google.com\/?q=-45.8659830734156,170.513554965408&ll=-45.8659830734156,170.513554965408&z=19&t=k\" target=\"_blank\">CNCAL<\/a>\n\n<div class=\"sv-hidden-md sv-hidden-lg sv-hidden-sm\">\n\t<a onclick=\"$('.uo_mobile_more_SENG301S1DNI2017CA1').toggle()\" href=\"javascript:void(0)\">More...<\/a>\n\t<div class=\"uo_mobile_more_SENG301S1DNI2017CA1\" style=\"display:none\">\n<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Software Project Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>A1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Central CAL (Richardson) <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Richardson Building <\/span>\n<\/div>\n\n\t<\/div>\n<\/div>",
        "ttip": "<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Software Project Management<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>A1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Central CAL (Richardson) <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Richardson Building <\/span>\n<\/div>\n",
        "preinfo": "",
        "isnote": false,
        "extra": {"tts_seqn": 6, "rcid": ""}
    }, {
        "id": "YTTB10",
        "day": 5,
        "start": "09:00",
        "end": "11:00",
        "draggable": false,
        "resizable": false,
        "selected": false,
        "bcol": "lightgrey",
        "fcol": "#121212",
        "info": "Lecture<br>\nINFO312<br>\n\n<a href=\"https:\/\/maps.google.com\/?q=-45.8664204533545,170.515870883468&ll=-45.8664204533545,170.515870883468&z=19&t=k\" target=\"_blank\">CO805<\/a>\n\n<div class=\"sv-hidden-md sv-hidden-lg sv-hidden-sm\">\n\t<a onclick=\"$('.uo_mobile_more_INFO312FYDNI2017LA1').toggle()\" href=\"javascript:void(0)\">More...<\/a>\n\t<div class=\"uo_mobile_more_INFO312FYDNI2017LA1\" style=\"display:none\">\n<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Information Systems Development Project<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>A1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Info Sci  Commerce 8.05 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Commerce Building <\/span>\n<\/div>\n\n\t<\/div>\n<\/div>",
        "ttip": "<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Information Systems Development Project<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>A1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Info Sci  Commerce 8.05 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> Commerce Building <\/span>\n<\/div>\n",
        "preinfo": "",
        "isnote": false,
        "extra": {"tts_seqn": 1, "rcid": ""}
    }, {
        "id": "YTTB11",
        "day": 5,
        "start": "11:00",
        "end": "12:00",
        "draggable": false,
        "resizable": false,
        "selected": false,
        "bcol": "lightgrey",
        "fcol": "#121212",
        "info": "Lecture<br>\nINFO323<br>\n\n<a href=\"https:\/\/maps.google.com\/?q=-45.8636426650169,170.513851671001&ll=-45.8636426650169,170.513851671001&z=19&t=k\" target=\"_blank\">SDAV1<\/a>\n\n<div class=\"sv-hidden-md sv-hidden-lg sv-hidden-sm\">\n\t<a onclick=\"$('.uo_mobile_more_INFO323S1DNI2017LL1').toggle()\" href=\"javascript:void(0)\">More...<\/a>\n\t<div class=\"uo_mobile_more_INFO323S1DNI2017LL1\" style=\"display:none\">\n<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Distributed Information Systems<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>St David Seminar Rm 1 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> St David Complex <\/span>\n<\/div>\n\n\t<\/div>\n<\/div>",
        "ttip": "<div class='sv-col-sm-3'>\n\t<span><strong>Paper:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>Distributed Information Systems<\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Stream:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>L1<\/span>\n<\/div>\n\n<div class='sv-col-sm-3'>\n\t<span><strong>Room:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span>St David Seminar Rm 1 <\/span>\n<\/div>\n<div class='sv-col-sm-3'>\n\t<span><strong>Building:<\/strong><\/span>\n<\/div>\n<div class='sv-col-sm-9'>\n\t<span> St David Complex <\/span>\n<\/div>\n",
        "preinfo": "",
        "isnote": false,
        "extra": {"tts_seqn": 4, "rcid": ""}
    }];
    sits_timetable_widget("#newtimetable", "CREATE", options, eventdata);
}*/
