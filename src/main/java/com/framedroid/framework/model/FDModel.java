package com.framedroid.framework.model;

import com.framedroid.framework.FD;
import com.framedroid.framework.database.Database;

import org.json.JSONObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by mateusz on 03.04.2017.
 */

public abstract class FDModel<T extends FDModel> {
    @JsonParse(name = "id")
    protected long id;

    public FDModel() {
    }

    private static Database database = new Database();

    public static Database.Table fd(Class cls) {
        return database.byClass(cls);
    }

    public long getId() {
        return id;
    }

    public void fromJson(JSONObject jsonObject) {
        fromJson(this.getClass().getSuperclass(), jsonObject);
        fromJson(this.getClass(), jsonObject);
    }

    private void fromJson(Class<?> aClass, JSONObject jsonObject) {
        for (Field field : aClass.getFields()) {
            JsonParse jsonParse = field.getAnnotation(JsonParse.class);
            if (jsonParse == null)
                continue;

            try {
                field.set(this, jsonObject.opt(jsonParse.name()));
            } catch (Exception e) {
                FD.e("Filed to parse json to model");
            }

        }
    }

}
