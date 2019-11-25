package com.example.projectendcourse.view.Newsfilm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.projectendcourse.R;

public class Detailnews extends AppCompatActivity {
    WebView webView;
    ProgressBar loader;
    RelativeLayout a;
    String url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailnews);
        Intent intent = getIntent();
        url = intent.getStringExtra("link");
        loader = findViewById(R.id.loader);
        webView = findViewById(R.id.webView);
        a=findViewById(R.id.chemat);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                loader.setVisibility(View.VISIBLE);
                view.loadUrl(url);
                a.setBackgroundColor(Color.BLACK);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
                loader.setVisibility(View.GONE);
                a.setBackgroundColor(Color.WHITE);
            }
        });

        webView.loadUrl(url);
    }
}
