package com.framedroid.framework;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
     *
     * @param obj any type of variable
     */
    public static void print(Object obj) {
        Log.i("FrameDroidLog", obj + "");
    }

    /**
     * Print string, integer, object etc. to logcat<br>
     * Using FD.p("some log");
     *
     * @param obj any type of variable
     */
    public static void p(Object obj) {
        print(obj + "");
    }

    /**
     * Print string, integer, object etc. to logcat<br>
     * Using FD.print("some log", true, 21.4);
     *
     * @param objs any type of variable
     */
    public static void print(Object... objs) {
        String text = "";
        for (Object obj : objs) {
            text += obj + " ";
        }
        print(text);
    }

    /**
     * Print string, integer, object etc. to logcat<br>
     * Using FD.p("some log", true, 21.4);
     *
     * @param objs any type of variable
     */
    public static void p(Object... objs) {
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

    public static void error(Object... objs) {
        String text = "";
        for (Object obj : objs) {
            text += obj + " ";
        }
        error(text);
    }

    public static void e(Object... objs) {
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

    /**
     * Get color from resources
     *
     * @param resId
     * @return
     */
    public static int color(@ColorRes int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getContext().getResources().getColor(resId, context.getTheme());
        } else {
            return getContext().getResources().getColor(resId);
        }
    }

    /**
     * Parse color from hex (i.e. #123ABC) to int (i.e. 0xFF123ABC)
     * @param hex
     * @return
     */
    public static int color(String hex) {
        return Color.parseColor(hex);
    }

    /**
     * Get string from resources
     *
     * @param resId
     * @return
     */
    public static String string(@StringRes int resId) {
        return getContext().getString(resId);
    }

    /**
     * Get drawable from resources
     *
     * @param resId
     * @return
     */
    public static Drawable drawable(@DrawableRes int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getContext().getDrawable(resId);
        } else {
            return getContext().getResources().getDrawable(resId);
        }
    }

    /**
     * Return array of range between start and end numbers
     *
     * @param start first number
     * @param end   last number
     * @return
     */
    public static Integer[] range(int start, int end) {
        return range(start, end, 1);
    }

    /**
     * Return array of range between start and end numbers by spec period
     *
     * @param start  Integer - first number
     * @param end    Integer - last number
     * @param period Integer
     * @return
     */
    public static Integer[] range(int start, int end, int period) {
        Integer[] range = new Integer[(Math.abs(end - start) / period) + 1];
        for (int i = 0; i < range.length; i++) {
            range[i] = end > start ? start + (i * period) : start - (i * period);
        }
        return range;
    }

    /**
     * Return array of range between start and end numbers by spec period
     *
     * @param start  Double - first number
     * @param end    Dobule - last number
     * @param period Double
     * @return
     */
    public static Double[] range(double start, double end, double period) {
        Double[] range = new Double[(int) (Math.abs(end - start) / period) + 1];
        for (int i = 0; i < range.length; i++) {
            range[i] = end > start ? start + (i * period) : start - (i * period);
        }
        return range;
    }

    public static int toggle(List list, Object obj) {
        if (list.contains(obj)) {
            list.remove(obj);
            return -1;
        }
        list.add(obj);

        return 1;
    }


    public static File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        return image;
    }
}
