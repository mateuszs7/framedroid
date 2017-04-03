package com.framedroid.framework.model;

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mateusz on 03.04.2017.
 */

public class Database<T extends FDModel> {
    private Map<Long, T> objects = new ArrayMap<>();

    public T get(long id) {
        return objects.get(id);
    }

    public List<T> getAll() {
        return new ArrayList<>(objects.values());
    }

    public T add(T object) {
        return objects.put(object.getId(), object);
    }

    public List<T> addAll(List<T> objs) {
        for (T obj : objs) {
            add(obj);
        }
        return objs;
    }

    public T remove(T object) {
        return remove(object.getId());
    }

    public T remove(long id) {
        return objects.remove(id);
    }

    public void removeAll() {
        objects.clear();
    }

}
