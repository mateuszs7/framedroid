package com.framedroid.framework;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.framedroid.framework.database.Select;
import com.framedroid.framework.helpers.SubJson;
import com.framedroid.framework.model.TestModel;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by mateu on 11/05/2017.
 */

@RunWith(AndroidJUnit4.class)
public class SimpleDatabaseTest {
    @Test
    public void initTest() throws Exception {
        TestModel testModel = new TestModel(1, "name", 4);
        assertEquals(1, testModel.getId());
        assertEquals("name", testModel.getName());
        assertEquals(4, testModel.getValue());
    }

    @Test
    public void addTest() throws Exception {
        TestModel testModel = new TestModel(1, "name", 4);
        TestModel.fd().add(testModel);
        TestModel testModel1 = TestModel.fd().get(1);

        assertNotEquals(null, testModel1);
        assertEquals(1, testModel1.getId());
        assertEquals("name", testModel1.getName());
        assertEquals(4, testModel1.getValue());
        TestModel.fd().removeAll();
    }

    @Test
    public void updateTest() throws Exception {
        TestModel testModel0 = new TestModel(1, "name", 4);
        TestModel.fd().add(testModel0);

        TestModel testModel = TestModel.fd().get(1);
        assertNotEquals(null, testModel);

        testModel.setName("new name");
        testModel.setValue(9);
        TestModel.fd().save(testModel);

        TestModel testModel1 = TestModel.fd().get(1);
        assertEquals(1, testModel1.getId());
        assertEquals("new name", testModel1.getName());
        assertEquals(9, testModel1.getValue());
        TestModel.fd().removeAll();
    }

    @Test
    public void countTest() throws Exception {
        TestModel testModel0 = new TestModel(1, "name", 4);
        TestModel.fd().add(testModel0);

        int count = Select.from(TestModel.class).count();
        assertEquals(1, count);
    }

    @Test
    public void removeTest() throws Exception {
        TestModel testModel0 = new TestModel(1, "name", 4);
        TestModel.fd().add(testModel0);

        TestModel testModel = TestModel.fd().get(1);
        assertNotEquals(null, testModel);
        TestModel.fd().remove(1);

        TestModel testModel1 = TestModel.fd().get(1);
        assertNull(testModel1);

        int count = Select.from(TestModel.class).count();
        assertEquals(0, count);
    }


    @Test
    public void parseTest() throws Exception {
        JSONObject json = FD.json().build(
                new SubJson("id", 1),
                new SubJson("name", "TestName"),
                new SubJson("something", 3)
        );

        Log.i("json", json.toString());

        TestModel testModel = new TestModel();
        testModel.fromJson(json);

        assertEquals(1, testModel.getId());
        assertEquals("TestName", testModel.getName());
        assertEquals(3, testModel.getValue());
    }

}
