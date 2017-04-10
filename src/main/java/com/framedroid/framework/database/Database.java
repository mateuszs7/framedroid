package com.framedroid.framework.database;

import android.util.ArrayMap;

import com.framedroid.framework.model.FDModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by mateusz on 03.04.2017.
 */

public class Database<T extends FDModel> {
    private Map<Class, Table> tables = new ArrayMap<>();

    public Table byClass(Class cls) {
        Table table = tables.get(cls);
        if (table == null) {
            table = new Table(cls);
            tables.put(cls, table);
        }
        return table;
    }

    public class Table {
        private Map<Long, T> objects = new ArrayMap<>();
        private Class cls;

        public Table(Class cls) {
            this.cls = cls;
        }

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

        public List<T> getBy(String fieldName, Object value) {
            try {
                Field field = cls.getField(fieldName);
                List<T> result = new ArrayList<>();
                for (T obj : objects.values()) {
                    if (field.get(obj).equals(value))
                        result.add(obj);
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Collections.emptyList();
        }

        public T save(T obj) {
            return objects.put(obj.getId(), obj);
        }
    }
}
