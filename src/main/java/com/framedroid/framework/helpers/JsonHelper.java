package com.framedroid.framework.helpers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mateusz on 10.03.2017.
 */

public class JsonHelper {
    public JSONObject build(SubJson...subJsons) {
        JSONObject jsonObject = new JSONObject();
        try {
            for (SubJson subJson : subJsons) {
                jsonObject.put(subJson.key, subJson.value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Deprecated
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

    public JSONArray toArray(List list) {
        JSONArray jsonArray = new JSONArray();
        for (Object obj :list) {
            jsonArray.put(obj);
        }

        return jsonArray;

    }

    /**
     * Use SubJson instead
     */
    @Deprecated
    public static class PreJson {
        String key;
        Object value;

        public PreJson(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}
