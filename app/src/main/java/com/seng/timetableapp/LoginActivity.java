package com.seng.timetableapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import dao.TimetableDAO;
import parser.DataProcessor;

/**
 * A login screen that offers login via username/password.
 */
public class LoginActivity extends Activity {

    private WebView webview;
    private TimeTableScraper timeTableScraper;
    private AutoCompleteTextView mUsername;
    private EditText mPassword;
    private View mProgressView;
    private View mLoginFormView;
    private final TimetableDAO dao = new TimetableDAO(this);

    private boolean debugging = false;
    private boolean timetableLoaded;

    private boolean credentialsInserted = false;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedPref = LoginActivity.this.getPreferences(Context.MODE_PRIVATE);
        Long lastDownloaded = sharedPref.getLong(getString(R.string.last_downloaded_key), -1);

        if (lastDownloaded > 0 && !getIntent().hasExtra("type")) {
            // If the latest data is 5 days old or more, then require the user to log in to re-download.
            if (new Date().getTime() < lastDownloaded + (24 * 60 * 60 * 1000)) {
                if (dao.loadTimeTable()) {
                    switchToTimetable();
                    timetableLoaded = true;
                }
            }
        }
        if (!timetableLoaded) continueLogin();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void continueLogin() {
        webview = (WebView) findViewById(R.id.webScraper);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.setWebViewClient(new MyBrowser());
        timeTableScraper = new TimeTableScraper(webview);
        webview.addJavascriptInterface(timeTableScraper, "TimeTableJsInterface");
        webview.loadUrl(getString(R.string.evision_url));

        mUsername = (AutoCompleteTextView) findViewById(R.id.txt_username);
        mPassword = (EditText) findViewById(R.id.txt_password);
        mPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    insertCredentials();
                    return true;
                }
                return false;
            }
        });

        FloatingActionButton mLoginButton = (FloatingActionButton) findViewById(R.id.btn_login);
        mLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                insertCredentials();
            }
        });

        mLoginFormView = findViewById(R.id.email_login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    /**
     * Runs first stage of the timetable scraper implementation.
     */
    private void insertCredentials() {
        timeTableScraper.setUserName(mUsername.getText().toString());
        timeTableScraper.setPassword(mPassword.getText().toString());
        timeTableScraper.login();
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        showProgress();
        credentialsInserted = true;
    }

    private void switchToTimetable() {
        startActivity(new Intent(LoginActivity.this, TimetableActivity.class));
    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    private void showProgress() {
        mProgressView.setVisibility(View.VISIBLE);
        mLoginFormView.setVisibility(View.GONE);
        findViewById(R.id.login_logo).setVisibility(View.GONE);
    }

    private void switchActivity() {
        if (dao.saveTimeTable()) {
            SharedPreferences sharedPref = LoginActivity.this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putLong(getString(R.string.last_downloaded_key), new Date().getTime());
            editor.apply();
        }

        if (debugging) Log.d("TT-ACTIVITY", "Created");
        timetableLoaded = true;
        timeTableScraper = null;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ConstraintLayout container = (ConstraintLayout) findViewById(R.id.container);
                final WebView webView = (WebView) findViewById(R.id.webScraper);
                container.removeView(webView);
                webView.removeAllViews();
                webView.clearHistory();
                webView.clearCache(true);
                webView.destroy();
                webview = null;
            }
        });
        switchToTimetable();
    }

    /**
     * Custom class to process eVision login.
     * Controls injecting JS into the page, and handles
     * the flow after the login button has been pushed.
     */
    private class MyBrowser extends WebViewClient {

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (timeTableScraper != null) {
                timeTableScraper.initJS();
            }

            if (credentialsInserted) {
                if (debugging) Log.d("NAVIGATING", "navigating to week timetable");
                if (timeTableScraper != null) {
                    timeTableScraper.gotToTimeTable();
                }
                if (debugging) Log.d("STAGE 1", "delaying stage one for JS");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (debugging) Log.d("STAGE 1", "stage delayed for 3 seconds");
                        if (timeTableScraper != null) {
                            timeTableScraper.scrapeWeekTimeTableOptions();
                            timeTableScraper.scrapeWeekTimeTable();
                        }
                    }
                }, 5000);
            }
        }
    }

    private class TimeTableScraper {

        private final WebView webView;
        private String timeTableWeek;
        private String timeTableWeekOptions;

        private boolean debugging = false;

        private final String jsWeekFunction = "javascript:TimeTableJsInterface" +
                ".setGetTimeTableWeek(scraper.timeTableWeek('script'))";

        private final String jsWeekOptionsFunction = "javascript:TimeTableJsInterface" +
                ".setGetTimeTableWeekOptions(scraper.timeTableWeekOptions('script'))";

        private boolean once = false;

        private final DataProcessor parser = new DataProcessor();

        private TimeTableScraper(WebView webView) {
            this.webView = webView;
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
            if (this.timeTableWeekOptions != null && this.timeTableWeekOptions.length() > 0 &&
                    this.timeTableWeek != null && this.timeTableWeek.length() > 0 && !once) {
                TimetableDAO.timetable = parser.parseWebData(this.timeTableWeek, this.timeTableWeekOptions);
                switchActivity();
                once = true;
            }
        }

        @JavascriptInterface
        public void setGetTimeTableWeek(String timeTableWeek) {
            this.timeTableWeek = timeTableWeek;
            if (debugging) {
                Log.d("WEEK-LENGTH:", " " + timeTableWeek.length());
                Log.d("WEEK-LENGTH:", " Set -> " + timeTableWeek);
            }
            if (this.timeTableWeekOptions != null && this.timeTableWeekOptions.length() > 0 &&
                    this.timeTableWeek != null && this.timeTableWeek.length() > 0 && !once) {
                TimetableDAO.timetable = parser.parseWebData(timeTableWeek, timeTableWeekOptions);
                switchActivity();
                once = true;
            }
        }

        @JavascriptInterface
        public void Log() {
            if (debugging) Log.d("DELAY", "Delaying Navigation with setTimeout");
        }

        private void setUserName(final String userName) {
            webView.post(new Runnable() {
                public void run() {
                    webView.loadUrl("javascript:scraper.setUserName('" + userName + "')");
                    if (debugging) Log.d("SET:", " Username");
                }
            });
        }

        private void setPassword(final String password) {
            webView.post(new Runnable() {
                public void run() {
                    webView.loadUrl("javascript:scraper.setPassword('" + password + "')");
                    if (debugging) Log.d("SET:", " Password");
                }
            });
        }

        private void gotToTimeTable() {
            webView.post(new Runnable() {
                public void run() {
                    webView.loadUrl("javascript:scraper.goToTimeTable()");
                    if (debugging) Log.d("GOTO:", " Timetable");
                }
            });
        }

        private void login() {
            webView.post(new Runnable() {
                public void run() {
                    webView.loadUrl("javascript:scraper.login()");
                    if (debugging) Log.d("LOGIN:", " Attempting Login");
                }
            });
        }

        private void scrapeWeekTimeTableOptions() {
            webView.post(new Runnable() {
                public void run() {
                    webView.loadUrl(jsWeekOptionsFunction);
                    if (debugging) Log.d("SCRAPE:", " Scraping OPTIONS");
                }
            });
        }

        private void scrapeWeekTimeTable() {
            webView.post(new Runnable() {
                public void run() {
                    webView.loadUrl(jsWeekFunction);
                    if (debugging) Log.d("SCRAPE:", " Scraping WEEK");
                }
            });
        }

        private void initJS() {
            webView.post(new Runnable() {
                public void run() {
                    webView.loadUrl(jsScraper);
                    if (debugging) Log.d("INIT-JS", "Something");
                }
            });
        }

        private final String jsScraper = "javascript:var scraper = (function () {\n" +
                "    var scraper_module = {};\n" +
                "    var userName = document.getElementById(\"MUA_CODE.DUMMY.MENSYS\");\n" +
                "    var pass = document.getElementById(\"PASSWORD.DUMMY.MENSYS\");\n" +
                "    var submit = document.getElementsByClassName(\"sv-btn sv-btn-block sv-btn-primary\")[0];\n" +
                "\n" +
                "     function _timeTableWeek(selector) {\n" +
                "        var timeTableScript = document.getElementsByTagName(selector);\n" +
                "        var rawText = _scrapeTimeTableWeek(selector);\n" +
                "        var timetable = rawText.substring(\n" +
                "          rawText.indexOf('var eventdata = '),\n" +
                "          rawText.indexOf('sits_timetable_widget(\"#newtimetable\", \"CREATE\", options, eventdata);'));\n" +
                "          return timetable;\n" +
                "    }\n" +
                "\n" +
                "    function _timeTableWeekStart(selector) {\n" +
                "      var timeTableScript = document.getElementsByTagName(selector);\n" +
                "      var rawText = _scrapeTimeTableWeek(selector);\n" +
                "      var dates = rawText.substring(rawText.indexOf(\"var options\"), rawText.indexOf('var eventdata = '));\n" +
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
                "    scraper_module.timeTableWeek = _timeTableWeek;\n" +
                "    scraper_module.timeTableWeekOptions = _timeTableWeekStart;\n" +
                "    return scraper_module;\n" +
                "})();";
    }

}