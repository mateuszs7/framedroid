package com.framedroid.framework.helpers;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StorageHelper {

    private Context context;

    public StorageHelper(Context context) {
        this.context = context;
    }

    public boolean save(String key, Object object) {
        try {
            FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
            fos.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Object read(String key) {
        try {
            FileInputStream fis = context.openFileInput(key);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object object = ois.readObject();
            return object;
        } catch (Exception e) {
            return null;
        }
    }
}
