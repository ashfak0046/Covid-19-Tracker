package com.developer.arsltech.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.os.Bundle;

public class Vaccine extends AppCompatActivity {
    WebView mywebview;
    SwipeRefreshLayout swip;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);
        //getSupportActionBar().hide();
        swip = (SwipeRefreshLayout)findViewById(R.id.swipe);
        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                load();
            }
        });
        load();


    }
    public void load() {
        mywebview = findViewById(R.id.WebView);
        mywebview.loadUrl("https://surokkha.gov.bd/");
        WebSettings webSettings = mywebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);
        mywebview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mywebview.setScrollbarFadingEnabled(true);
        mywebview.setWebChromeClient(new WebChromeClient());
        mywebview.setWebViewClient(new WebViewClient() {
                                       @Override
                                       public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                                           mywebview.loadUrl("Internet Error");

                                       }

                                       @Override
                                       public void onPageFinished(WebView view, String url) {
                                           swip.setRefreshing(false);
                                       }
                                   }
        );
    }
}