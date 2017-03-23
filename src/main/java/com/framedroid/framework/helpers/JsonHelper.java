package com.framedroid.framework.helpers;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mateusz on 10.03.2017.
 */

public class JsonHelper {
    public static JSONObject toJson(PreJson...preJsons) {
        JSONObject jsonObject = new JSONObject();
        try {
            for (PreJson preJson : preJsons) {
                jsonObject.put(preJson.key, preJson.value == null ? preJson.jsonValue : preJson.value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static class PreJson {
        String key;
        String value;
        JSONObject jsonValue;

        public PreJson(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public PreJson(String key, JSONObject value) {
            this.key = key;
            this.jsonValue = value;
        }

        public static class Builder {
            List<PreJson> list = new ArrayList<>();
            public Builder() {
                list = new ArrayList<>();
            }

            public Builder add(PreJson preJson) {
                list.add(preJson);
                return this;
            }

            public List<PreJson> build() {
                return list;
            }
            public JSONObject buildToJson() {
                return toJson(list.toArray(new PreJson[list.size()]));
            }
        }
    }
}
