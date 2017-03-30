package com.framedroid.framework;

import android.app.Application;
import android.util.Log;

/**
 * Created by mateu on 30/03/2017.
 */

public class Methods {

    /*
     * Printers
     */

    public static void print(String string) {
        Log.i("FrameDroidLog", string);
    }

    public static void print(Object obj) {
        print(obj + "");
    }

    public static void p(Object obj) {
        print(obj + "");
    }


}
