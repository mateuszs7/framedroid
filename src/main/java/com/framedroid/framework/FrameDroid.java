package com.framedroid.framework;

import android.app.Application;
import android.content.Context;

import com.framedroid.framework.helpers.JsonHelper;
import com.framedroid.framework.helpers.PrefsHelper;
import com.framedroid.framework.helpers.ScreenHelper;
import com.framedroid.framework.helpers.TimeHelper;
import com.framedroid.framework.helpers.ViewHelper;

/**
 * Created by mateu on 23/03/2017.
 */

public class FrameDroid {
    private static ScreenHelper screenHelper;
    private static TimeHelper timeHelper;
    private static PrefsHelper prefsHelper;
    private static ViewHelper viewHelper;

    private static JsonHelper jsonHelper = new JsonHelper();

    public static void init(Context context) {
        screenHelper = new ScreenHelper(context);
        timeHelper = new TimeHelper(context);
        prefsHelper = new PrefsHelper(context);
        viewHelper = new ViewHelper();
    }

    public static ScreenHelper screen() {
        return screenHelper;
    }

    public static TimeHelper time() {
        return timeHelper;
    }

    public static JsonHelper json() {
        return jsonHelper;
    }

    public static PrefsHelper prefs() {
        return prefsHelper;
    }

    public static ViewHelper view() {
        return viewHelper;
    }
}
