package com.framedroid.framework;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by mateu on 30/03/2017.
 */

public class Methods {
    protected static Context context;

    protected static Context getContext() {
        if (context == null)
            throw new RuntimeException("You have to initialize FrameDroid module by calling FD.init(getApplicationContext())");
        return context;
    }

    /*
     * Info printers
     */

    /**
     * Print string, integer, object etc. to logcat<br>
     * Using FD.print("some log");
     * @param obj any type of variable
     */
    public static void print(Object obj) {
        Log.i("FrameDroidLog", obj + "");
    }

    /**
     * Print string, integer, object etc. to logcat<br>
     * Using FD.p("some log");
     * @param obj any type of variable
     */
    public static void p(Object obj) {
        print(obj + "");
    }

    /**
     * Print string, integer, object etc. to logcat<br>
     * Using FD.print("some log", true, 21.4);
     * @param objs any type of variable
     */
    public static void print(Object...objs) {
        String text = "";
        for (Object obj : objs) {
            text += obj + " ";
        }
        print(text);
    }

    /**
     * Print string, integer, object etc. to logcat<br>
     * Using FD.p("some log", true, 21.4);
     * @param objs any type of variable
     */
    public static void p(Object...objs) {
        print(objs);
    }


    /*
     * Error printers
     */

    public static void error(Object obj) {
        Log.e("FrameDroidError", obj + "");
    }

    public static void e(Object obj) {
        error(obj + "");
    }

    public static void error(Object...objs) {
        String text = "";
        for (Object obj : objs) {
            text += obj + " ";
        }
        error(text);
    }

    public static void e(Object...objs) {
        error(objs);
    }

    /*
     * Toasts
     */
    public static void toast(String string, int length) {
        Toast.makeText(getContext(), string, length).show();
    }

    public static void toast(String string) {
        Toast.makeText(getContext(), string, Toast.LENGTH_SHORT).show();
    }

    public static void toast(int resId, int length) {
        toast(getContext().getString(resId), length);
    }

    public static void toast(int resId) {
        toast(resId, Toast.LENGTH_SHORT);
    }

    /*
     * Resources
     */

    public static int color(@ColorRes int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getContext().getResources().getColor(resId, context.getTheme());
        } else {
            return getContext().getResources().getColor(resId);
        }
    }

    public static String string(@StringRes int resId) {
        return getContext().getString(resId);
    }

    public static Drawable drawable(@DrawableRes int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getContext().getDrawable(resId);
        } else {
            return getContext().getResources().getDrawable(resId);
        }
    }

    public static Integer[] range(int start, int end) {
        Integer[] range = new Integer[Math.abs(end - start) + 1];
        for (int i = 0; i < range.length; i++) {
            range[i] = end > start ? start + i : start - i;
        }

        return range;
    }

}
