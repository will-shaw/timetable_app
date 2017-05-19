package com.seng.timetableapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import dao.TimetableDAO;
import jsinterface.TimeTableScraper;

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

    private boolean debugging = false;
    private boolean timetableLoaded;

    private int stage = 0;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        showProgress();
        stage = 1;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    private void showProgress() {
        mProgressView.setVisibility(View.VISIBLE);
        mLoginFormView.setVisibility(View.GONE);
        findViewById(R.id.login_logo).setVisibility(View.GONE);
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
            timeTableScraper.initJS();

            switch (stage) {
                case 0:
                    break;
                case 1:
                    if (debugging) Log.d("NAVIGATING", "navigating to week timetable");
                    timeTableScraper.gotToTimeTable();
                    if (debugging) Log.d("STAGE 1", "delaying stage one for JS");
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (debugging) Log.d("STAGE 1", "stage delayed for 3 seconds");
                            timeTableScraper.scrapeWeekTimeTableOptions();
                            timeTableScraper.scrapeWeekTimeTable();
                            if (TimetableDAO.timetable != null) {
                                if (!timetableLoaded) {
                                    if (debugging) Log.d("TT-ACTIVITY", "Created");
                                    startActivity(new Intent(LoginActivity.this, TimetableActivity.class));
                                    timetableLoaded = true;
                                    webview.destroy();
                                    webview = null;
                                }
                            }
                        }
                    }, 5000);
                    break;
                default:
                    if (debugging) Log.d("STAGE 2", String.valueOf(stage));
                    break;
            }
        }
    }

}