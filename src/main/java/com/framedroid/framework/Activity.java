package com.framedroid.framework;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.framedroid.framework.helpers.ScreenHelper;
import com.framedroid.framework.helpers.TimeHelper;

/**
 * Created by mateu on 23/03/2017.
 */

public class Activity extends AppCompatActivity {
    private ScreenHelper screenHelper;
    private TimeHelper timeHelper;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        screenHelper = new ScreenHelper(getApplicationContext());
        timeHelper = new TimeHelper();
        super.onCreate(savedInstanceState, persistentState);
    }

    protected ScreenHelper screen() {
        return screenHelper;
    }

    protected TimeHelper time() {
        return timeHelper;
    }

}
