package com.framedroid.framework.database;

import com.framedroid.framework.model.FDModel;
import com.framedroid.framework.model.TestModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.R.attr.value;

/**
 * Created by mateu on 06/04/2017.
 */

public class Select {
    public static FDQuery from(Class cls) {
        return FDQuery.getInstance(FDModel.fd(cls).getAll(), cls);
    }


    public static class FDQuery {
        private static Class cls;
        private List results;

        public static FDQuery getInstance(List results, Class cls) {
            FDQuery.cls = cls;
            FDQuery query = new FDQuery();
            query.results = results;
            return query;
        }

        public <T extends FDModel> FDQuery where(String string, Object...objs) {
            Where<T> where = new Where(cls, results, string, objs);
            try {
                results = where.parseConditions();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return this;
        }

        public <T extends FDModel> T first() {
            if (results.size() == 0)
                return null;
            return (T)results.get(0);
        }

        public <T extends FDModel> T last() {
            if (results.size() == 0)
                return null;
            return (T) results.get(results.size()-1);
        }

        public int count() {
            return results.size();
        }

        public <T extends FDModel> List<T> execute() {
            return results;
        }
    }
}
