package com.example.hotelquickly;

import android.app.Application;
import android.content.Context;

public class App extends Application {
	
	private static App instance;

	// Constructor
	public App() {
        instance = this;
    }

	@Override
    public void onCreate() {
    	super.onCreate();
    }

	// TODO Public Methods
    public static Context getContext() {
        return instance;
    }
}
