package com.example.hotelquickly.views;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hotelquickly.R;
import com.example.hotelquickly.controllers.UrlDownloadListener;
import com.example.hotelquickly.controllers.UrlDownloader;
import com.example.hotelquickly.models.PageUrl;

public class MainActivity extends ListActivity implements UrlDownloadListener {

    public static final String PAGE_URL_EXTRA = "PAGE_URL_EXTRA";
    private PageUrl[] pageUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initial URL.
        String mainUrl = "http://appcontent.hotelquickly.com/en/1/android/index.json";

        // Initial the UrlDownloader.
        UrlDownloader downloader = new UrlDownloader();

        // Start fetching!
        downloader.downloadFile(this, mainUrl);
    }

    @Override // TODO ListView's Listener
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        // Start new activity.
        Intent intent = new Intent(this, PageActivity.class);
        intent.putExtra(PAGE_URL_EXTRA, pageUrls[position]);
        startActivity(intent);
    }

    /** TODO Private Methods
     * This methods will preload the webview.
     */
    private void preload() {
        WebView view = new WebView(this);
        for (PageUrl item : pageUrls) {
            if (item.isCache()) {
                view.loadUrl(item.getUrl());
            }
        }
    }

    // TODO FileDownloadListener
    @Override
    public void onSuccess(String url, PageUrl[] pageUrls) {
        this.pageUrls = pageUrls;
        preload();
        setListAdapter(new PageAdapter(this, pageUrls));
    }

    @Override
    public void onError(Exception e, String url) {
        // Show error message.
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
    }
}
