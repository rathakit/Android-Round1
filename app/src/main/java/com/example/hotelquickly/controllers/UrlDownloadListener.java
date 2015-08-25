package com.example.hotelquickly.controllers;

import com.example.hotelquickly.models.PageUrl;

/**
 * Created by apple on 8/25/2015 AD.
 */
public interface UrlDownloadListener {

    public void onSuccess(String url, PageUrl[] pageUrls);
    public void onError(Exception e, String url);
}
