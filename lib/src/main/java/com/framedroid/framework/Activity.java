package com.framedroid.framework;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.framedroid.framework.helpers.JsonHelper;
import com.framedroid.framework.helpers.PrefsHelper;
import com.framedroid.framework.helpers.ScreenHelper;
import com.framedroid.framework.helpers.TimeHelper;
import com.framedroid.framework.helpers.ViewHelper;

/**
 * Created by mateu on 23/03/2017.
 */

public class Activity extends AppCompatActivity {
    private ScreenHelper screenHelper;
    private TimeHelper timeHelper;
    private PrefsHelper prefsHelper;
    private static ViewHelper viewHelper;

    private JsonHelper jsonHelper = new JsonHelper();

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        screenHelper = new ScreenHelper(getApplicationContext());
        timeHelper = new TimeHelper(getApplicationContext());
        prefsHelper = new PrefsHelper(getApplicationContext());
        viewHelper = new ViewHelper();
        super.onCreate(savedInstanceState, persistentState);
    }

    protected ScreenHelper screen() {
        return screenHelper;
    }

    protected TimeHelper time() {
        return timeHelper;
    }

    protected JsonHelper json() {
        return jsonHelper;
    }

    protected PrefsHelper prefs() {
        return prefsHelper;
    }

    public static ViewHelper view() {
        return viewHelper;
    }

}
