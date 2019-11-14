package com.navjot.finalprojectapp.viewcontroller;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;


import com.navjot.finalprojectapp.R;

public class DetailedNewsActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_news);

        getSupportActionBar().setTitle(" Sports News");

        webView = findViewById(R.id.webView);

        Intent rcv = getIntent();

        String url = rcv.getStringExtra("keyUrl");

        WebViewClient client = new WebViewClient();
        webView.setWebViewClient(client);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

    }
}
