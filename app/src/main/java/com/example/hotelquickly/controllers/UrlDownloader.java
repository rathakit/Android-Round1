package com.example.hotelquickly.controllers;

import android.os.AsyncTask;

import com.example.hotelquickly.models.PageUrl;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by apple on 8/25/2015 AD.
 */
public class UrlDownloader {

    private String url;
    private UrlDownloadListener listener;

    // TODO Public Methods
    /**
     * Download from another thread.
     */
    public void downloadFile(UrlDownloadListener listener, String url) {
        this.listener = listener;
        this.url = url;
        new SimpleHttpTask().execute();
    }

    // TODO Private Class
    private class SimpleHttpTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            StringBuffer buffer = new StringBuffer("");
            try {
                URL u = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) u.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    buffer.append(line);
                }
            } catch (Exception e) {
                if (listener != null) listener.onError(e, url);
            }
            return buffer.toString();
        }

        @Override
        protected void onPostExecute(String result)  {
            try {
                JSONObject json = new JSONObject(result);
                ArrayList<PageUrl> pageUrls = new ArrayList<>();
                for (Iterator it = json.keys(); it.hasNext();) {
                    String name = (String) it.next();
                    JSONObject obj = json.getJSONObject(name);
                    pageUrls.add(new PageUrl(name, obj));
                }
                if (listener != null) listener.onSuccess(url, pageUrls.toArray(new PageUrl[pageUrls.size()]));
            } catch (Exception e) {
                if (listener != null) listener.onError(e, url);
            }
        }
    }
}
