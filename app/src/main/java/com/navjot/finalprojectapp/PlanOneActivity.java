package com.navjot.finalprojectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class PlanOneActivity extends AppCompatActivity {

    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_one);

        Intent rcv= getIntent();
        getSupportActionBar().hide();


        //webView = view.findViewById(R.id.webView);

        webView=(WebView)findViewById(R.id.plan1WebView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);

        String url = "https://www.runnersworld.com/training/a20853140/the-8-week-beginners-program/";

        webView.loadUrl(url);
    }



}
