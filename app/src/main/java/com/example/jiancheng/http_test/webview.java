package com.example.jiancheng.http_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webview extends AppCompatActivity {

    WebView webView;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView=(WebView) findViewById(R.id.web);


        url = getIntent().getStringExtra("url");

        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // 設置可以支持縮放
        webSettings.setSupportZoom(true);

        // 設置出現縮放工具
        webSettings.setBuiltInZoomControls(true);


        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl(url);



    }
}
