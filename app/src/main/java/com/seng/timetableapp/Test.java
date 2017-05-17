package com.seng.timetableapp;

import java.util.Collection;

public class Test {
   public static void main(String[] args) {
      String eventData =  " var eventdata = [{\n" +
              " \"id\": \"YTTB1\",\n" +
              " \"day\": 1,\n" +
              " \"start\": \"11:00\",\n" +
              " \"end\": \"12:00\",\n" +
              " \"draggable\": false,\n" +
              " \"resizable\": false,\n" +
              " \"selected\": false,\n" +
              " \"bcol\": \"lightgrey\",\n" +
              " \"fcol\": \"#121212\",\n" +
              " \"info\": \"Lecture<br>\\nCOSC301<br>\\n\\n<a href=\\\"https:\\/\\/maps.google.com\\/?q=-45.8636426650169,170.513851671001&ll=-45.8636426650169,170.513851671001&z=19&t=k\\\" target=\\\"_blank\\\">SDAV1<\\/a>\\n\\n<div class=\\\"sv-hidden-md sv-hidden-lg sv-hidden-sm\\\">\\n\\t<a onclick=\\\"$('.uo_mobile_more_COSC301S1DNI2017LL1').toggle()\\\" href=\\\"javascript:void(0)\\\">More...<\\/a>\\n\\t<div class=\\\"uo_mobile_more_COSC301S1DNI2017LL1\\\" style=\\\"display:none\\\">\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Network Management<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>L1<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>St David Seminar Rm 1 <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> St David Complex <\\/span>\\n<\\/div>\\n\\n\\t<\\/div>\\n<\\/div>\",\n" +
              " \"ttip\": \"<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Network Management<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>L1<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>St David Seminar Rm 1 <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> St David Complex <\\/span>\\n<\\/div>\\n\",\n" +
              " \"preinfo\": \"\",\n" +
              " \"isnote\": false,\n" +
              " \"extra\": {\"tts_seqn\": 9, \"rcid\": \"\"}\n" +
              " }, {\n" +
              " \"id\": \"YTTB2\",\n" +
              " \"day\": 1,\n" +
              " \"start\": \"12:00\",\n" +
              " \"end\": \"14:00\",\n" +
              " \"draggable\": false,\n" +
              " \"resizable\": false,\n" +
              " \"selected\": false,\n" +
              " \"bcol\": \"#80FFFF\",\n" +
              " \"fcol\": \"#000000\",\n" +
              " \"info\": \"Computer Lab<br>\\nINFO323<br>\\n\\n<a href=\\\"https:\\/\\/maps.google.com\\/?q=-45.8639136763326,170.513502564758&ll=-45.8639136763326,170.513502564758&z=19&t=k\\\" target=\\\"_blank\\\">NCAL<\\/a>\\n\\n<div class=\\\"sv-hidden-md sv-hidden-lg sv-hidden-sm\\\">\\n\\t<a onclick=\\\"$('.uo_mobile_more_INFO323S1DNI2017CZ1').toggle()\\\" href=\\\"javascript:void(0)\\\">More...<\\/a>\\n\\t<div class=\\\"uo_mobile_more_INFO323S1DNI2017CZ1\\\" style=\\\"display:none\\\">\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Distributed Information Systems<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Z1<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>North CAL Lab. <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> Science III <\\/span>\\n<\\/div>\\n\\n\\t<\\/div>\\n<\\/div>\",\n" +
              " \"ttip\": \"<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Distributed Information Systems<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Z1<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>North CAL Lab. <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> Science III <\\/span>\\n<\\/div>\\n\",\n" +
              " \"preinfo\": \"\",\n" +
              " \"isnote\": false,\n" +
              " \"extra\": {\"tts_seqn\": 3, \"rcid\": \"\"}\n" +
              " }, {\n" +
              " \"id\": \"YTTB3\",\n" +
              " \"day\": 1,\n" +
              " \"start\": \"14:00\",\n" +
              " \"end\": \"16:00\",\n" +
              " \"draggable\": false,\n" +
              " \"resizable\": false,\n" +
              " \"selected\": false,\n" +
              " \"bcol\": \"#09BBF7\",\n" +
              " \"fcol\": \"#000000\",\n" +
              " \"info\": \"Practical<br>\\nCOSC301<br>\\n\\n<a href=\\\"https:\\/\\/maps.google.com\\/?q=-45.8670533441689,170.518171263001&ll=-45.8670533441689,170.518171263001&z=19&t=k\\\" target=\\\"_blank\\\">OWG38<\\/a>\\n\\n<div class=\\\"sv-hidden-md sv-hidden-lg sv-hidden-sm\\\">\\n\\t<a onclick=\\\"$('.uo_mobile_more_COSC301S1DNI2017PP2').toggle()\\\" href=\\\"javascript:void(0)\\\">More...<\\/a>\\n\\t<div class=\\\"uo_mobile_more_COSC301S1DNI2017PP2\\\" style=\\\"display:none\\\">\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Network Management<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>P2<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Owheo Lab G38 COSC <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> Owheo Building <\\/span>\\n<\\/div>\\n\\n\\t<\\/div>\\n<\\/div>\",\n" +
              " \"ttip\": \"<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Network Management<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>P2<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Owheo Lab G38 COSC <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> Owheo Building <\\/span>\\n<\\/div>\\n\",\n" +
              " \"preinfo\": \"\",\n" +
              " \"isnote\": false,\n" +
              " \"extra\": {\"tts_seqn\": 10, \"rcid\": \"\"}\n" +
              " }, {\n" +
              " \"id\": \"YTTB4\",\n" +
              " \"day\": 2,\n" +
              " \"start\": \"12:00\",\n" +
              " \"end\": \"13:00\",\n" +
              " \"draggable\": false,\n" +
              " \"resizable\": false,\n" +
              " \"selected\": false,\n" +
              " \"bcol\": \"lightgrey\",\n" +
              " \"fcol\": \"#121212\",\n" +
              " \"info\": \"Lecture<br>\\nINFO312<br>\\n\\n<a href=\\\"https:\\/\\/maps.google.com\\/?q=-45.8659830734156,170.513554965408&ll=-45.8659830734156,170.513554965408&z=19&t=k\\\" target=\\\"_blank\\\">RGS2<\\/a>\\n\\n<div class=\\\"sv-hidden-md sv-hidden-lg sv-hidden-sm\\\">\\n\\t<a onclick=\\\"$('.uo_mobile_more_INFO312FYDNI2017LL1').toggle()\\\" href=\\\"javascript:void(0)\\\">More...<\\/a>\\n\\t<div class=\\\"uo_mobile_more_INFO312FYDNI2017LL1\\\" style=\\\"display:none\\\">\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Information Systems Development Project<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>L1<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Te Tumu RGS2 Te Riu <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> Richardson Building <\\/span>\\n<\\/div>\\n\\n\\t<\\/div>\\n<\\/div>\",\n" +
              " \"ttip\": \"<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Information Systems Development Project<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>L1<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Te Tumu RGS2 Te Riu <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> Richardson Building <\\/span>\\n<\\/div>\\n\",\n" +
              " \"preinfo\": \"\",\n" +
              " \"isnote\": false,\n" +
              " \"extra\": {\"tts_seqn\": 2, \"rcid\": \"\"}\n" +
              " }, {\n" +
              " \"id\": \"YTTB5\",\n" +
              " \"day\": 3,\n" +
              " \"start\": \"12:00\",\n" +
              " \"end\": \"13:00\",\n" +
              " \"draggable\": false,\n" +
              " \"resizable\": false,\n" +
              " \"selected\": false,\n" +
              " \"bcol\": \"lightgrey\",\n" +
              " \"fcol\": \"#121212\",\n" +
              " \"info\": \"Lecture<br>\\nINFO323<br>\\n\\n<a href=\\\"https:\\/\\/maps.google.com\\/?q=-45.8636426650169,170.513851671001&ll=-45.8636426650169,170.513851671001&z=19&t=k\\\" target=\\\"_blank\\\">SDAV5<\\/a>\\n\\n<div class=\\\"sv-hidden-md sv-hidden-lg sv-hidden-sm\\\">\\n\\t<a onclick=\\\"$('.uo_mobile_more_INFO323S1DNI2017LL1').toggle()\\\" href=\\\"javascript:void(0)\\\">More...<\\/a>\\n\\t<div class=\\\"uo_mobile_more_INFO323S1DNI2017LL1\\\" style=\\\"display:none\\\">\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Distributed Information Systems<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>L1<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>St David Seminar Rm 5 <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> St David Complex <\\/span>\\n<\\/div>\\n\\n\\t<\\/div>\\n<\\/div>\",\n" +
              " \"ttip\": \"<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Distributed Information Systems<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>L1<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>St David Seminar Rm 5 <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> St David Complex <\\/span>\\n<\\/div>\\n\",\n" +
              " \"preinfo\": \"\",\n" +
              " \"isnote\": false,\n" +
              " \"extra\": {\"tts_seqn\": 5, \"rcid\": \"\"}\n" +
              " }, {\n" +
              " \"id\": \"YTTB6\",\n" +
              " \"day\": 3,\n" +
              " \"start\": \"14:00\",\n" +
              " \"end\": \"16:00\",\n" +
              " \"draggable\": false,\n" +
              " \"resizable\": false,\n" +
              " \"selected\": false,\n" +
              " \"bcol\": \"lightgrey\",\n" +
              " \"fcol\": \"#121212\",\n" +
              " \"info\": \"Lecture<br>\\nSENG301<br>\\n\\n<a href=\\\"https:\\/\\/maps.google.com\\/?q=-45.8636426650169,170.513851671001&ll=-45.8636426650169,170.513851671001&z=19&t=k\\\" target=\\\"_blank\\\">SDAV2<\\/a>\\n\\n<div class=\\\"sv-hidden-md sv-hidden-lg sv-hidden-sm\\\">\\n\\t<a onclick=\\\"$('.uo_mobile_more_SENG301S1DNI2017LL1').toggle()\\\" href=\\\"javascript:void(0)\\\">More...<\\/a>\\n\\t<div class=\\\"uo_mobile_more_SENG301S1DNI2017LL1\\\" style=\\\"display:none\\\">\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Software Project Management<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>L1<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>St David Seminar Rm 2 <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> St David Complex <\\/span>\\n<\\/div>\\n\\n\\t<\\/div>\\n<\\/div>\",\n" +
              " \"ttip\": \"<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Software Project Management<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>L1<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>St David Seminar Rm 2 <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> St David Complex <\\/span>\\n<\\/div>\\n\",\n" +
              " \"preinfo\": \"\",\n" +
              " \"isnote\": false,\n" +
              " \"extra\": {\"tts_seqn\": 7, \"rcid\": \"\"}\n" +
              " }, {\n" +
              " \"id\": \"YTTB7\",\n" +
              " \"day\": 4,\n" +
              " \"start\": \"11:00\",\n" +
              " \"end\": \"12:00\",\n" +
              " \"draggable\": false,\n" +
              " \"resizable\": false,\n" +
              " \"selected\": false,\n" +
              " \"bcol\": \"lightgrey\",\n" +
              " \"fcol\": \"#121212\",\n" +
              " \"info\": \"Lecture<br>\\nCOSC301<br>\\n\\n<a href=\\\"https:\\/\\/maps.google.com\\/?q=-45.867074072439,170.513475268215&ll=-45.867074072439,170.513475268215&z=19&t=k\\\" target=\\\"_blank\\\">BURN7<\\/a>\\n\\n<div class=\\\"sv-hidden-md sv-hidden-lg sv-hidden-sm\\\">\\n\\t<a onclick=\\\"$('.uo_mobile_more_COSC301S1DNI2017LL1').toggle()\\\" href=\\\"javascript:void(0)\\\">More...<\\/a>\\n\\t<div class=\\\"uo_mobile_more_COSC301S1DNI2017LL1\\\" style=\\\"display:none\\\">\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Network Management<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>L1<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Burns 7 <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> Arts 1 (Burns) <\\/span>\\n<\\/div>\\n\\n\\t<\\/div>\\n<\\/div>\",\n" +
              " \"ttip\": \"<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Network Management<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>L1<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Burns 7 <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> Arts 1 (Burns) <\\/span>\\n<\\/div>\\n\",\n" +
              " \"preinfo\": \"\",\n" +
              " \"isnote\": false,\n" +
              " \"extra\": {\"tts_seqn\": 8, \"rcid\": \"\"}\n" +
              " }, {\n" +
              " \"id\": \"YTTB8\",\n" +
              " \"day\": 4,\n" +
              " \"start\": \"13:00\",\n" +
              " \"end\": \"15:00\",\n" +
              " \"draggable\": false,\n" +
              " \"resizable\": false,\n" +
              " \"selected\": false,\n" +
              " \"bcol\": \"#09BBF7\",\n" +
              " \"fcol\": \"#000000\",\n" +
              " \"info\": \"Practical<br>\\nCOSC301<br>\\n\\n<a href=\\\"https:\\/\\/maps.google.com\\/?q=-45.8670533441689,170.518171263001&ll=-45.8670533441689,170.518171263001&z=19&t=k\\\" target=\\\"_blank\\\">OWG38<\\/a>\\n\\n<div class=\\\"sv-hidden-md sv-hidden-lg sv-hidden-sm\\\">\\n\\t<a onclick=\\\"$('.uo_mobile_more_COSC301S1DNI2017PQ2').toggle()\\\" href=\\\"javascript:void(0)\\\">More...<\\/a>\\n\\t<div class=\\\"uo_mobile_more_COSC301S1DNI2017PQ2\\\" style=\\\"display:none\\\">\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Network Management<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Q2<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Owheo Lab G38 COSC <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> Owheo Building <\\/span>\\n<\\/div>\\n\\n\\t<\\/div>\\n<\\/div>\",\n" +
              " \"ttip\": \"<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Network Management<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Q2<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Owheo Lab G38 COSC <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> Owheo Building <\\/span>\\n<\\/div>\\n\",\n" +
              " \"preinfo\": \"\",\n" +
              " \"isnote\": false,\n" +
              " \"extra\": {\"tts_seqn\": 11, \"rcid\": \"\"}\n" +
              " }, {\n" +
              " \"id\": \"YTTB9\",\n" +
              " \"day\": 4,\n" +
              " \"start\": \"11:00\",\n" +
              " \"end\": \"13:00\",\n" +
              " \"draggable\": false,\n" +
              " \"resizable\": false,\n" +
              " \"selected\": false,\n" +
              " \"bcol\": \"#80FFFF\",\n" +
              " \"fcol\": \"#000000\",\n" +
              " \"info\": \"Computer Lab<br>\\nSENG301<br>\\n\\n<a href=\\\"https:\\/\\/maps.google.com\\/?q=-45.8659830734156,170.513554965408&ll=-45.8659830734156,170.513554965408&z=19&t=k\\\" target=\\\"_blank\\\">CNCAL<\\/a>\\n\\n<div class=\\\"sv-hidden-md sv-hidden-lg sv-hidden-sm\\\">\\n\\t<a onclick=\\\"$('.uo_mobile_more_SENG301S1DNI2017CA1').toggle()\\\" href=\\\"javascript:void(0)\\\">More...<\\/a>\\n\\t<div class=\\\"uo_mobile_more_SENG301S1DNI2017CA1\\\" style=\\\"display:none\\\">\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Software Project Management<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>A1<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Central CAL (Richardson) <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> Richardson Building <\\/span>\\n<\\/div>\\n\\n\\t<\\/div>\\n<\\/div>\",\n" +
              " \"ttip\": \"<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Software Project Management<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>A1<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Central CAL (Richardson) <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> Richardson Building <\\/span>\\n<\\/div>\\n\",\n" +
              " \"preinfo\": \"\",\n" +
              " \"isnote\": false,\n" +
              " \"extra\": {\"tts_seqn\": 6, \"rcid\": \"\"}\n" +
              " }, {\n" +
              " \"id\": \"YTTB10\",\n" +
              " \"day\": 5,\n" +
              " \"start\": \"09:00\",\n" +
              " \"end\": \"11:00\",\n" +
              " \"draggable\": false,\n" +
              " \"resizable\": false,\n" +
              " \"selected\": false,\n" +
              " \"bcol\": \"lightgrey\",\n" +
              " \"fcol\": \"#121212\",\n" +
              " \"info\": \"Lecture<br>\\nINFO312<br>\\n\\n<a href=\\\"https:\\/\\/maps.google.com\\/?q=-45.8664204533545,170.515870883468&ll=-45.8664204533545,170.515870883468&z=19&t=k\\\" target=\\\"_blank\\\">CO805<\\/a>\\n\\n<div class=\\\"sv-hidden-md sv-hidden-lg sv-hidden-sm\\\">\\n\\t<a onclick=\\\"$('.uo_mobile_more_INFO312FYDNI2017LA1').toggle()\\\" href=\\\"javascript:void(0)\\\">More...<\\/a>\\n\\t<div class=\\\"uo_mobile_more_INFO312FYDNI2017LA1\\\" style=\\\"display:none\\\">\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Information Systems Development Project<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>A1<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Info Sci  Commerce 8.05 <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> Commerce Building <\\/span>\\n<\\/div>\\n\\n\\t<\\/div>\\n<\\/div>\",\n" +
              " \"ttip\": \"<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Information Systems Development Project<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>A1<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Info Sci  Commerce 8.05 <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> Commerce Building <\\/span>\\n<\\/div>\\n\",\n" +
              " \"preinfo\": \"\",\n" +
              " \"isnote\": false,\n" +
              " \"extra\": {\"tts_seqn\": 1, \"rcid\": \"\"}\n" +
              " }, {\n" +
              " \"id\": \"YTTB11\",\n" +
              " \"day\": 5,\n" +
              " \"start\": \"11:00\",\n" +
              " \"end\": \"12:00\",\n" +
              " \"draggable\": false,\n" +
              " \"resizable\": false,\n" +
              " \"selected\": false,\n" +
              " \"bcol\": \"lightgrey\",\n" +
              " \"fcol\": \"#121212\",\n" +
              " \"info\": \"Lecture<br>\\nINFO323<br>\\n\\n<a href=\\\"https:\\/\\/maps.google.com\\/?q=-45.8636426650169,170.513851671001&ll=-45.8636426650169,170.513851671001&z=19&t=k\\\" target=\\\"_blank\\\">SDAV1<\\/a>\\n\\n<div class=\\\"sv-hidden-md sv-hidden-lg sv-hidden-sm\\\">\\n\\t<a onclick=\\\"$('.uo_mobile_more_INFO323S1DNI2017LL1').toggle()\\\" href=\\\"javascript:void(0)\\\">More...<\\/a>\\n\\t<div class=\\\"uo_mobile_more_INFO323S1DNI2017LL1\\\" style=\\\"display:none\\\">\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Distributed Information Systems<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>L1<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>St David Seminar Rm 1 <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> St David Complex <\\/span>\\n<\\/div>\\n\\n\\t<\\/div>\\n<\\/div>\",\n" +
              " \"ttip\": \"<div class='sv-col-sm-3'>\\n\\t<span><strong>Paper:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>Distributed Information Systems<\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Stream:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>L1<\\/span>\\n<\\/div>\\n\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Room:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span>St David Seminar Rm 1 <\\/span>\\n<\\/div>\\n<div class='sv-col-sm-3'>\\n\\t<span><strong>Building:<\\/strong><\\/span>\\n<\\/div>\\n<div class='sv-col-sm-9'>\\n\\t<span> St David Complex <\\/span>\\n<\\/div>\\n\",\n" +
              " \"preinfo\": \"\",\n" +
              " \"isnote\": false,\n" +
              " \"extra\": {\"tts_seqn\": 4, \"rcid\": \"\"}\n" +
              " }];\n" +
              " sits_timetable_widget(\"#newtimetable\", \"CREATE\", options, eventdata);\n" +
              " }";
      String options =
              " var options = {\n" +
              " \"onEventHover\": \"function (e, ui) {\n" +
              " sitsjqtt_increase_event_size($(this));\n" +
              " if (typeof ui.extraData.extra.eirh === 'string' && ui.extraData.extra.eirh != '') {\n" +
              " return true;\n" +
              " }\n" +
              " return false;\n" +
              " }\",\n" +
              " \"onEventEditClick\": \"function (e, ui) {\n" +
              " ttb_editDetails(ui.extraData.extra.eire, ui.extraData.extra.eirr, ui.extraData.extra.eirh);\n" +
              " }\",\n" +
              " \"height\": \"90%\",\n" +
              " \"in_client\": \"inBrowser\",\n" +
              " \"title\": \"Timetable for  (Now showing dates 01\\/05\\/2017 to 07\\/05\\/2017)\",\n" +
              " \"start_day\": 1,\n" +
              " \"no_days\": 7,\n" +
              " \"start_time\": 8,\n" +
              " \"no_hours\": 14,\n" +
              " \"view\": \"DAY_BY_TIME\",\n" +
              " \"snap_mins\": 30,\n" +
              " \"view_toggle\": \"Y\",\n" +
              " \"show_time\": \"N\",\n" +
              " \"dim_on_select\": false,\n" +
              " \"more_info\": \"<B>More<\\/B>\"\n" +
              " };\n";
      Collection<TTEvent> items;
      DataProcessor dp = new DataProcessor();
      items = dp.parseWebData(eventData, options);
      for(TTEvent t : items) {
         System.out.println(t.getLectureName() + "-> " + t.getStart() + ":00, " + t.getRoomCode() + ", " + t.getDate());
      }
   }
}
