package com.seng.timetableapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
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
import android.widget.Toast;

import jsinterface.TimeTableScraper;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

    private WebView webview;
    private final String EVISION_URI = "https://evision.otago.ac.nz/sitsvision/wrd/siw_lgn";
    private TimeTableScraper timeTableScraper;
    private Context context = this;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    private int stage = 0;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("CREATED", "view");
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.txt_username);

        webview = (WebView) findViewById(R.id.webScraper);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.setWebViewClient(new MyBrowser());
        timeTableScraper = new TimeTableScraper(webview);
        webview.addJavascriptInterface(timeTableScraper, "TimeTableJsInterface");

        webview.loadUrl(EVISION_URI);

        /*
        webview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
        */

        mPasswordView = (EditText) findViewById(R.id.txt_password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    insertCredentials();
                    //switchToTimetable(); // TODO: Replace with proper login logic.
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
                //switchToTimetable(); // TODO: Replace with proper login logic.
            }
        });

        mLoginFormView = findViewById(R.id.email_login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    public void insertCredentials() {
        timeTableScraper.setUserName(mEmailView.getText().toString());
        timeTableScraper.setPassword(mPasswordView.getText().toString());
        timeTableScraper.login();
        // TODO: Don't show progress till we know the credentials were accepted by evision.
        showProgress(true);
        stage = 1;
    }

    private void switchToTimetable() {
        Intent intent = new Intent(LoginActivity.this, TimetableActivity.class);
        startActivity(intent);
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    private void showProgress(final boolean show) {
        // The ViewPropertyAnimator APIs are not available, so simply show
        // and hide the relevant UI components.
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    private class MyBrowser extends WebViewClient {

        private int delayCounter = 0;
        private int maxDelay = 5;
        private int delayTime = 500;

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
                    Log.d("NAVIGATING", "navigating to week timetable");
//                    timeTableScraper.initJS();
                    selectTimetable();
                    Log.d("STAGE 1", "delaying stage one for JS");
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("STAGE 1", "stage delayed for 3 seconds");
                            scrapeTimetable();
                            Toast.makeText(context, "Made it here!", Toast.LENGTH_SHORT).show();
                            switchToTimetable();
                            //stage++;

                            //Do something after 100ms
                        }
                    }, 5000);
                    break;
//                case 2:
////                    scrapeTimetable();
//                    Toast.makeText(context, "Made it here!", Toast.LENGTH_SHORT).show();
//                    switchToTimetable();
//                    break;
                default:
                    Log.d("STAGE 2", String.valueOf(stage));
                    break;
            }
        }

        private void selectTimetable() {
            timeTableScraper.gotToTimeTable();
        }

        private void scrapeTimetable() {
            timeTableScraper.scrapeWeekTimeTableOptions();
            timeTableScraper.scrapeWeekTimeTable();
        }
    }
}