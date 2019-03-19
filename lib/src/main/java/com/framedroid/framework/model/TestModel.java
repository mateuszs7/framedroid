package com.framedroid.framework.model;

import com.framedroid.framework.database.Database;

/**
 * Created by mateusz on 03.04.2017.
 */

public class TestModel extends FDModel<TestModel> {
    @JsonParse(name = "name")
    private String name;

    @JsonParse(name = "something")
    private int value;

    public TestModel() {
    }

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
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
