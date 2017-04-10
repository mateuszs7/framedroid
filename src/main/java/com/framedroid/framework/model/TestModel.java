package com.framedroid.framework.model;

import com.framedroid.framework.database.Database;

/**
 * Created by mateusz on 03.04.2017.
 */

public class TestModel extends FDModel<TestModel> {
    public String name;
    public int value;

    public TestModel(long id, String name, int value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public static Database<TestModel>.Table fd() {
        return FDModel.fd(TestModel.class);
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
