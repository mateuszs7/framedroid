package com.framedroid.framework.helpers;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by mateu on 23/03/2017.
 */

public class ScreenHelper {

    private Context mContext;
    private final WindowManager mWindowManager;

    public ScreenHelper(Context context) {
        mContext = context;
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    public int width() {
        DisplayMetrics metrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public int height() {
        DisplayMetrics metrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    public int halfWidth() {
        return width() / 2;
    }

    public int halfHeight() {
        return height() / 2;
    }






}
