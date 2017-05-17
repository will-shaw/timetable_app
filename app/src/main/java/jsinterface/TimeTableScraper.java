package jsinterface;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import java.io.FileOutputStream;
import java.util.Collection;

import dao.TimetableDAO;
import domain.TTEvent;
import parser.DataProcessor;
import parser.Test;

/** Timetable Scraper
 * Created by jacques on 10/05/17.
 */
public class TimeTableScraper {

    private WebView webView;
    private String timeTableTwoDay;
    private String timeTableWeek;
    private String timeTableWeekOptions;

    private String jsWeekFunction = "javascript:TimeTableJsInterface" +
            ".setGetTimeTableWeek(scraper.timeTableWeek('script'))";

    private String jsWeekOptionsFunction = "javascript:TimeTableJsInterface" +
            ".setGetTimeTableWeekOptions(scraper.timeTableWeekOptions('script'))";

    private String jsTwoDayFunction = "javascript:TimeTableJsInterface" +
            ".setGetTimeTableTwoDay(scraper.timeTableTwoDay('sv-list-group sv-portal-2-col'))";
    private Collection<TTEvent> events;
    private boolean once = false;

    public TimeTableScraper(WebView webView) {
        this.webView = webView;
    }

    /**
     * called in javascript context store result into Java
     *
     * @param timeTableTwoDay two day timetable data
     */
    @JavascriptInterface
    public void setTimeTableTwoDay(String timeTableTwoDay) {
        this.timeTableTwoDay = timeTableTwoDay;
        Log.d("TWO-DAY-SET", "Two day set " + timeTableTwoDay);
    }

    /**
     * called in javascript context store result into Java
     *
     * @param timeTableWeekOptions weekly timetable data
     */
    @JavascriptInterface
    public void setGetTimeTableWeekOptions(String timeTableWeekOptions) {
        this.timeTableWeekOptions = timeTableWeekOptions;
        Log.d("WEEK-START-SET", "Week Start set " + timeTableWeekOptions);


    }

    @JavascriptInterface
    public void setGetTimeTableWeek(String TimeTableWeek) {
        this.timeTableWeek = TimeTableWeek;
        Log.d("WEEK-SET", "Week set " + TimeTableWeek.length());
        if (timeTableWeekOptions != null && !once) {
            DataProcessor parser = new DataProcessor();

            TimetableDAO.timetable = parser.parseWebData(timeTableWeek, timeTableWeekOptions);

            System.err.println(timeTableWeek);
            System.err.println(timeTableWeekOptions);
            System.out.println("TT WEEK LENGTH = " + timeTableWeek.length());

        }


    }

    @JavascriptInterface
    public void Log() {
        Log.d("DELLAY", "Delaying Navigation with setTimeout");
    }

    public void setUserName(String userName) {
        webView.loadUrl("javascript:scraper.setUserName('" + userName + "')");
        Log.d("SET-USER", "User set " + userName);
    }

    public void setPassword(String password) {
        webView.loadUrl("javascript:scraper.setPassword('" + password + "')");
        Log.d("SET-PASS", "Password set");
    }

    public void gotToTimeTable() {
        webView.loadUrl("javascript:scraper.goToTimeTable()");
        Log.d("GOTO-TIMETABLE", "GOTO Timetable");
    }

    public void login() {
        webView.loadUrl("javascript:scraper.login()");
        Log.d("LOGIN", "LOGIN");
    }

    public void scrapeWeekTimeTableOptions() {
        webView.loadUrl(jsWeekOptionsFunction);
        Log.d("SCRAPE-OPTIONS", "Scraping");
    }

    public void scrapeWeekTimeTable() {
        webView.loadUrl(jsWeekFunction);
        Log.d("SCRAPE-WEEK", "Scraping");
    }

    public void scrapeTwoDayTimeTable() {
        webView.loadUrl(jsTwoDayFunction);
        Log.d("SCRAPE2", "Scraping 2 day");
    }

    public void initJS() {
        webView.loadUrl(jsScraper);
        Log.d("INIT-JS", "Something");
    }

    /**
     * Getter
     *
     * @return string of two day time table data
     */
    public String getTimeTableTwoDay() {
        Log.d("Get-TimetableTwoDay", this.timeTableWeek);
        return timeTableTwoDay;
    }

    /**
     * Getter
     *
     * @return string of weekly time table data
     */
    public String getTimeTableWeek() {
        Log.d("Get-TimetableWeek", this.timeTableWeek);
        return timeTableWeek;
    }

    /**
     * Getter
     *
     * @return string of weekly time table data
     */
    public String getTimeTableWeekOptions() {
        Log.d("Get-TimetableWeek", this.timeTableWeekOptions);
        return timeTableWeekOptions;
    }

    private String jsScraper = "javascript:var scraper = (function () {\n" +
            "    var scraper_module = {};\n" +
            "    var userName = document.getElementById(\"MUA_CODE.DUMMY.MENSYS\");\n" +
            "    var pass = document.getElementById(\"PASSWORD.DUMMY.MENSYS\");\n" +
            "    var submit = document.getElementsByClassName(\"sv-btn sv-btn-block sv-btn-primary\")[0];\n" +
            "\n" +
            "    function _timeTableTwoDay(selector) {\n" +
            "\n" +
            "        return document.getElementsByClassName(selector)[8].getElementsByTagName(\"table\")[0];\n" +
            "    }\n" +
            "\n" +
            "     function _timeTableWeek(selector) {\n" +
            "        var timeTableScript = document.getElementsByTagName(selector);\n" +
            "        var rawText = _scrapeTimeTableWeek(selector);\n" +
            "        var timetable = rawText.substring(\n" +
            "          rawText.indexOf('var eventdata = '),\n" +
            "          rawText.indexOf('sits_timetable_widget(\"#newtimetable\", \"CREATE\", options, eventdata);'));\n" +
            "          console.log(timetable);" +
            "          return timetable;\n" +
            "    }\n" +
            "\n" +
            "    function _timeTableWeekStart(selector) {\n" +
            "      var timeTableScript = document.getElementsByTagName(selector);\n" +
            "      var rawText = _scrapeTimeTableWeek(selector);\n" +
            "      var dates = rawText.substring(rawText.indexOf(\"var options\"), rawText.indexOf('var eventdata = '));\n" +
            "      console.log('Dates: '+dates);" +
            "       return dates;\n" +
            "    }\n" +
            "\n" +
            "    function _scrapeTimeTableWeek(selector) {\n" +
            "      var timeTableScript = document.getElementsByTagName(selector);\n" +
            "      for (var i = 0; i < timeTableScript.length; i++) {\n" +
            "          if (timeTableScript[i].innerText.length > 5000) {\n" +
            "              return timeTableScript[i].innerText;\n" +
            "          }\n" +
            "      }\n" +
            "      return \"\";\n" +
            "    }\n" +
            "\n" +
            "    scraper_module.setUserName = function (username) {\n" +
            "        userName.value = username;\n" +
            "    };\n" +
            "\n" +
            "    scraper_module.setPassword = function (password) {\n" +
            "        pass.value = password;\n" +
            "    };\n" +
            "\n" +
            "    scraper_module.login = function () {\n" +
            "        submit.click();\n" +
            "    };\n" +
            "\n" +
            "    scraper_module.goToTimeTable = function () {\n" +
            "\t\tsetInterval(function(){\n" +
            "\t\ttry {\n" +
            "        \tvar goToTimeTable = document.getElementsByClassName(\"uo_see_more\")[7].children[0];\n" +
            "\t\t\tconsole.log('element found:'+goToTimeTable);\n" +
            "        \tgoToTimeTable.click();\n" +
            "\t\t}\n" +
            "\t\tcatch(err) {\n" +
            "    console.log('element not found');\n" +
            "\t\t}\n" +
            "\t\t}, 2000)\n" +
            "    };\n" +
            "\n" +
            "    scraper_module.timeTableTwoDay = _timeTableTwoDay;\n" +
            "    scraper_module.timeTableWeek = _timeTableWeek;\n" +
            "    scraper_module.timeTableWeekOptions = _timeTableWeekStart;\n" +
            "    return scraper_module;\n" +
            "})();";// "javascript:var scraper = (function () {\n" +
//            "    var scraper_module = {};\n" +
//            "    var userName = document.getElementById(\"MUA_CODE.DUMMY.MENSYS\");\n" +
//            "    var pass = document.getElementById(\"PASSWORD.DUMMY.MENSYS\");\n" +
//            "    var submit = document.getElementsByClassName(\"sv-btn sv-btn-block sv-btn-primary\")[0];\n" +
//            "\n" +
//            "    function _timeTableTwoDay(selector) {\n" +
//            "\n" +
//            "        return document.getElementsByClassName(selector)[8].getElementsByTagName(\"table\")[0];\n" +
//            "    }\n" +
//            "\n" +
//            "     function _timeTableWeek(selector) {\n" +
//            "        var timeTableScript = document.getElementsByTagName(selector);\n" +
//            "        var rawText = _scrapeTimeTableWeek(selector);\n" +
//            "        var timetable = rawText.substring(\n" +
//            "          rawText.indexOf('var eventdata = '),\n" +
//            "          rawText.indexOf('sits_timetable_widget(\"#newtimetable\", \"CREATE\", options, eventdata);'));\n" +
//            "          return timetable;\n" +
//            "    }\n" +
//            "\n" +
//            "    function _timeTableWeekStart(selector) {\n" +
//            "      var timeTableScript = document.getElementsByTagName(selector);\n" +
//            "      var rawText = _scrapeTimeTableWeek(selector);\n" +
//            "      var dates = rawText.substring(sc.indexOf(\"var options\"), rawText.indexOf('var eventdata = '));\n" +
//            "      return dates;\n" +
//            "    }\n" +
//            "\n" +
//            "    function _scrapeTimeTableWeek(selector) {\n" +
//            "      var timeTableScript = document.getElementsByTagName(selector);\n" +
//            "      for (var i = 0; i < timeTableScript.length; i++) {\n" +
//            "          if (timeTableScript[i].innerText.length > 5000) {\n" +
//            "              return timeTableScript[i].innerText;\n" +
//            "          }\n" +
//            "      }\n" +
//            "      return \"\";\n" +
//            "    }\n" +
//            "\n" +
//            "    scraper_module.setUserName = function (username) {\n" +
//            "        userName.value = username;\n" +
//            "    };\n" +
//            "\n" +
//            "    scraper_module.setPassword = function (password) {\n" +
//            "        pass.value = password;\n" +
//            "    };\n" +
//            "\n" +
//            "    scraper_module.login = function () {\n" +
//            "        submit.click();\n" +
//            "    };\n" +
//            "\n" +
//            "    scraper_module.goToTimeTable = function () {\n" +
//            "\t\tTimeTableJsInterface.Log(); " +
//            "    console.log('setting timeout');" +
//            "   setTimeout(function(){\n" +
//            "        console.log('time out finished');" +
//            "        TimeTableJsInterface.Log(); " +
//            "        var goToTimeTable = document.getElementsByClassName(\"uo_see_more\")[7].children[0];\n" +
//            "        goToTimeTable.click();\n" +
//            "\t\t}, 8000)\n" +
//            "    };\n" +
//            "\n" +
//            "    scraper_module.timeTableTwoDay = _timeTableTwoDay;\n" +
//            "    scraper_module.timeTableWeek = _timeTableWeek;\n" +
//            "    scraper_module.timeTableWeekOptions = _timeTableWeekStart;\n" +
//            "    return scraper_module;\n" +
//            "})();";
}