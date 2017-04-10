package com.framedroid.framework.database;

import android.support.annotation.Nullable;

import com.framedroid.framework.FD;

/**
 * Created by mateu on 07/04/2017.
 */

public class Condition {

    enum Type {
        OR,
        AND
    }

    Type type;
    String fieldName;
    Object value;

    public Condition(String fieldName, Object value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    public Condition(Type type, String fieldName, Object value) {
        this.type = type;
        this.fieldName = fieldName;
        this.value = value;
    }

    public static Condition fromRaw(String s, Object param) {
        s = nameFromRaw(s);
        return new Condition(s, param);
    }

    public static String nameFromRaw(String s) {
        s = s.replace("?", "");
        s = s.replace("=", "");
        s = s.trim();
        return s;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "type=" + type +
                ", fieldName='" + fieldName + '\'' +
                ", value=" + value +
                '}';
    }
}
