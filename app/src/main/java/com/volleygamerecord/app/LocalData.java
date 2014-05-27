package com.volleygamerecord.app;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Jay on 2014/5/23.
 */
public class LocalData extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        Parse.initialize(this, "OOyy4I805eCgkyEGCiZtAH2RybkVl2tWi4qulbkw", "AOXZIHWss8wAiupkyTQuhEelITKfQ3LUeXAdHVTL");
    }
}
