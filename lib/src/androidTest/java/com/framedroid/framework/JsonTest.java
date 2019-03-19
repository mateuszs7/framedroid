package com.framedroid.framework;

import android.support.test.runner.AndroidJUnit4;

import com.framedroid.framework.helpers.SubJson;
import com.framedroid.framework.model.TestModel;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class JsonTest {
    @Test
    public void buildTest() throws Exception {
        JSONObject json = FD.json().build(
                new SubJson("int", 1),
                new SubJson("float", 2.3),
                new SubJson("string", "test"),
                new SubJson("json", FD.json().build(
                        new SubJson("subjson", "test")
                ))
        );

        String jsonString = "{\"int\":1,\"float\":2.3,\"string\":\"test\",\"json\":{\"subjson\":\"test\"}}";
        assertEquals(json.toString(), jsonString);
    }
}
