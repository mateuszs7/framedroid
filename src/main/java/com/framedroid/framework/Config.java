package com.framedroid.framework;

import android.text.format.DateUtils;

/**
 * Created by mateu on 30/03/2017.
 */

public class Config {
    public static String apiKeyParameterName = "apiKey";
    public static boolean apiLogRequest = true;
    public static int apiClientTimeout = (int) DateUtils.SECOND_IN_MILLIS * 20;
}
