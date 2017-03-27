package com.framedroid.framework.helpers;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mateusz on 10.03.2017.
 */

public class JsonHelper {
    public JSONObject build(PreJson...preJsons) {
        JSONObject jsonObject = new JSONObject();
        try {
            for (PreJson preJson : preJsons) {
                jsonObject.put(preJson.key, preJson.value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static class PreJson {
        String key;
        Object value;

        public PreJson(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}
