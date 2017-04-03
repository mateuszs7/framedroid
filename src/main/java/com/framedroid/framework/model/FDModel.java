package com.framedroid.framework.model;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by mateusz on 03.04.2017.
 */

public abstract class FDModel<T extends FDModel> {
    protected long id;
    private static Database database = new Database();

    public static Database<? extends FDModel> fd() {
        return database;
    }

    public long getId() {
        return id;
    }
}
