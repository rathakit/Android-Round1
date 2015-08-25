package com.example.hotelquickly.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.hotelquickly.R;
import com.example.hotelquickly.models.PageUrl;

public class PageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        // Get the object from previous page.
        Intent intent = getIntent();
        PageUrl page = intent.getParcelableExtra(MainActivity.PAGE_URL_EXTRA);

        // Retains UIs
        WebView webView = (WebView) findViewById(R.id.page_webview);
        final ProgressBar progress = (ProgressBar) findViewById(R.id.progress_bar);

        // Setup WebView.
        WebSettings webSettings = webView.getSettings();
        if (page.isCache()) {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
        } else {
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

            // Show loading bar.
            progress.setVisibility(View.VISIBLE);
        }

        // Load it!
        webView.loadUrl(page.getUrl());

        // Listen!
        webView.setWebViewClient(new WebViewClient() {

            @Override // WebViewClient
            public void onPageFinished(WebView view, String url) {
                progress.setVisibility(View.GONE);
            }
        });
    }
}
