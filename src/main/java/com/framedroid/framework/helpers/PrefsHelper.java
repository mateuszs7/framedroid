package com.framedroid.framework.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.json.JSONArray;

/**
 * Created by mateu on 23/03/2017.
 */

public class PrefsHelper {
    private Context mContext;
    private final SharedPreferences sharedPref;

    public PrefsHelper(Context context) {
        this.mContext = context;
        sharedPref = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    public void set(String name, String value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(name, value);
        editor.apply();
    }

    public void set(String name, boolean value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(name, value);
        editor.apply();
    }

    public void set(String name, JSONArray value) {
        set(name, value.toString());
    }

    public String get(String name) {
        return sharedPref.getString(name, null);
    }

    public int get(String name, int def) {
        return sharedPref.getInt(name, def);
    }

    public int getInt(String name) {
        return sharedPref.getInt(name, -1);
    }

    public float get(String name, float def) {
        return sharedPref.getFloat(name, def);
    }

    public int getDouble(String name) {
        return sharedPref.getInt(name, -1);
    }

    public boolean is(String name, boolean def) {
        return sharedPref.getBoolean(name, def);
    }

    public JSONArray get(String name, JSONArray def) {
        String jsonString = get(name);
        try {
            return new JSONArray(jsonString);
        } catch (Exception e) {
            return def;
        }
    }
}
