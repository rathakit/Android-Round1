package com.example.hotelquickly.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 8/25/2015 AD.
 */
public class PageUrl implements Parcelable {

    public static final String USER_ID = "276";
    public static final String APP_SECRET_KEY = "gvx32RFZLNGhmzYrfDCkb9jypTPa8Q";
    public static final String CURRENCY_CODE = "USD";
    public static final String OFFER_ID = "10736598";
    public static final String SELECTED_VOUCHERS = "[]";
    private String name;
    private String url;
    private String title;
    private String namespace;
    private boolean cache;

    // This field is needed for Android to be able to create new objects, individually or as arrays.
    public static final Parcelable.Creator<PageUrl> CREATOR = new Creator<PageUrl>() {

        @Override
        public PageUrl createFromParcel(Parcel in) {
            return new PageUrl(in);
        }

        @Override
        public PageUrl[] newArray(int size) {
            return new PageUrl[size];
        }
    };

    // TODO Constructor
    public PageUrl(String name, JSONObject jsonObject) throws JSONException {
        // Name
        this.name = name;

        // Namespace
        namespace = jsonObject.getString("namespace");

        // Page Title
        if (jsonObject.has("pageTitle"))
            title = jsonObject.getString("pageTitle");

        // Cache
        cache = jsonObject.getBoolean("cache");

        // URL
        url = jsonObject.getString("url");

        // Params
        JSONArray jsonParams = jsonObject.getJSONArray("params");
        for (int i = 0; i < jsonParams.length(); i++) {
            String param = (String) jsonParams.get(i);
            url = replaceUrl(param, url);
        }
    }

    public PageUrl(Parcel in) {
        name = in.readString();
        url = in.readString();
        title = in.readString();
        namespace = in.readString();
        cache = in.readInt() == 1;
    }

    /** TODO Public Methods
     * Get the namespace.
     * @return The namespace.
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * Get the name.
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the url.
     * @return The url.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Get the page title.
     * @return the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Check whether cache is enabled.
     * @return cache status
     */
    public boolean isCache() {
        return cache;
    }

    /** TODO Private Methods
     * Find and replace value.
     * @param param
     * @param url
     * @return
     */
    private String replaceUrl(String param, String url) {
        String key = String.format("{%s}", param);
        String value = "";
        if (key.equalsIgnoreCase("{userId}")) {
            value = USER_ID;
        } else if (key.equalsIgnoreCase("{appSecretKey}")) {
            value = APP_SECRET_KEY;
        } else if (key.equalsIgnoreCase("{currencyCode}")) {
            value = CURRENCY_CODE;
        } else if (key.equalsIgnoreCase("{offerId}")) {
            value = OFFER_ID;
        } else if (key.equalsIgnoreCase("{selectedVouchers}")) {
            value = SELECTED_VOUCHERS;
        }
        return url.replace(key, value);
    }

    // TODO Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
        dest.writeString(title);
        dest.writeString(namespace);
        dest.writeInt(cache ? 1 : 0);
    }
}
