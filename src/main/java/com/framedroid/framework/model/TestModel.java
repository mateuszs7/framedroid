package com.framedroid.framework.model;

import com.framedroid.framework.database.Database;

/**
 * Created by mateusz on 03.04.2017.
 */

public class TestModel extends FDModel<TestModel> {
    String name;
    public static Database<TestModel>.Table fd() {
        return FDModel.fd(TestModel.class);
    }
}
