package com.example.hotelquickly.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hotelquickly.models.PageUrl;

/**
 * Created by apple on 8/25/2015 AD.
 */
public class PageAdapter extends ArrayAdapter<PageUrl> {

    // The items
    private PageUrl[] items;

    // Constructor
    public PageAdapter(Context context, PageUrl[] items) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, null);
        }

        // Page
        PageUrl p = items[position];
        TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
        tv.setText(p.getName());
        return convertView;
    }
}
