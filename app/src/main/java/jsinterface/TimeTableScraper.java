package jsinterface;

import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import dao.TimetableDAO;
import parser.DataProcessor;

/** Timetable Scraper
 * Created by jacques on 10/05/17.
 */
public class TimeTableScraper {

    private final WebView webView;
    private String timeTableTwoDay;
    private String timeTableWeek;
    private String timeTableWeekOptions;

    private boolean debugging = false;

    private final String jsWeekFunction = "javascript:TimeTableJsInterface" +
            ".setGetTimeTableWeek(scraper.timeTableWeek('script'))";

    private final String jsWeekOptionsFunction = "javascript:TimeTableJsInterface" +
            ".setGetTimeTableWeekOptions(scraper.timeTableWeekOptions('script'))";

    private final String jsTwoDayFunction = "javascript:TimeTableJsInterface" +
            ".setGetTimeTableTwoDay(scraper.timeTableTwoDay('sv-list-group sv-portal-2-col'))";
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
        if (debugging) Log.d("TWO-DAY:", " Set -> " + timeTableTwoDay);
    }

    /**
     * called in javascript context store result into Java
     *
     * @param timeTableWeekOptions weekly timetable data
     */
    @JavascriptInterface
    public void setGetTimeTableWeekOptions(String timeTableWeekOptions) {
        this.timeTableWeekOptions = timeTableWeekOptions;
        if (debugging) Log.d("OPTIONS:", " Set -> " + timeTableWeekOptions);
    }

    @JavascriptInterface
    public void setGetTimeTableWeek(String timeTableWeek) {
        this.timeTableWeek = timeTableWeek;
        if (debugging) {
            Log.d("WEEK-LENGTH:", " " + timeTableWeek.length());
            Log.d("WEEK-LENGTH:", " Set -> " + timeTableWeek);
        }
        if (timeTableWeekOptions != null && !once) {
            DataProcessor parser = new DataProcessor();
            TimetableDAO.timetable = parser.parseWebData(timeTableWeek, timeTableWeekOptions);
            once = true;
        }
    }

    @JavascriptInterface
    public void Log() {
        Log.d("DELAY", "Delaying Navigation with setTimeout");
    }

    public void setUserName(String userName) {
        webView.loadUrl("javascript:scraper.setUserName('" + userName + "')");
        if (debugging) Log.d("SET:", " Username");
    }

    public void setPassword(String password) {
        webView.loadUrl("javascript:scraper.setPassword('" + password + "')");
        if (debugging) Log.d("SET:", " Password");
    }

    public void gotToTimeTable() {
        webView.loadUrl("javascript:scraper.goToTimeTable()");
        if (debugging) Log.d("GOTO:", " Timetable");
    }

    public void login() {
        webView.loadUrl("javascript:scraper.login()");
        if (debugging) Log.d("LOGIN:", " Attempting Login");
    }

    public void scrapeWeekTimeTableOptions() {
        webView.loadUrl(jsWeekOptionsFunction);
        if (debugging) Log.d("SCRAPE:", " Scraping OPTIONS");
    }

    public void scrapeWeekTimeTable() {
        webView.loadUrl(jsWeekFunction);
        if (debugging) Log.d("SCRAPE:", " Scraping WEEK");
    }

    public void initJS() {
        webView.loadUrl(jsScraper);
        if (debugging) Log.d("INIT-JS", "Something");
    }

    private final String jsScraper = "javascript:var scraper = (function () {\n" +
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
            "})();";
}