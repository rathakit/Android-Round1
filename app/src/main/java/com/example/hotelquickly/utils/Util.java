package com.example.hotelquickly.utils;

import android.app.Activity;
import android.net.ConnectivityManager;

import com.example.hotelquickly.App;

/**
 * Created by apple on 8/25/2015 AD.
 */
public class Util {

    /**
     * Whether the network is available.
     * @return
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) App.getContext().getSystemService(Activity.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo().isConnected();
    }
}
