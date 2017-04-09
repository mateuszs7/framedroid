package com.framedroid.framework.database;

import android.util.ArrayMap;

import com.framedroid.framework.FD;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static android.R.attr.value;

/**
 * Created by mateu on 06/04/2017.
 */

public class Where<T> {
    private List<T> elements;
    private String conditions;
    private Object[] params;
    private Class cls;
    private boolean globalCondition = true;

    public Where(Class cls, List<T> elements, String conditions, Object...params) {
        this.elements = elements;
        this.conditions = conditions;
        this.params = params;
        this.cls = cls;
    }

    public void parseConditions() throws Exception {
        String[] cond = conditions.split("(or)|(and)");

        FD.p(cond);
        Map<String, Condition> condList = new ArrayMap<>();
        for (int i = 0; i < cond.length; i++) {
            Condition c = Condition.fromRaw(cond[i], params[i]);
            condList.put(c.fieldName, c);
        }


        FD.p("XX", condList);
        String[] orConditions = conditions.split("or");
        for (int i = 0; i < orConditions.length; i++) {
            String or = orConditions[i];
            String[] andConditions = or.split("and");
            for (int j = 0; j < andConditions.length; j++) {
                String and = andConditions[j];
                FD.p("AA", and, "|",Condition.nameFromRaw(and));
                Condition tCond = condList.get(Condition.nameFromRaw(and));
                tCond.type = (j + 1 > andConditions.length) ? Condition.Type.AND : Condition.Type.OR;
                FD.p("DD", tCond);
                condList.put(tCond.fieldName, tCond);
//                Field field = cls.getField(getFieldName(and));
//                List<T> result = new ArrayList<>();
//                for (Object obj : elements) {
//                    if (field.get(obj).equals(params[i+j]))
//                        result.add(obj);
//                }
            }
        }

        FD.p(condList);
            
    }

    private String getFieldName(String condition) {
        return condition.replaceAll("[^a-zA-Z0-9 -]", "");
    }


}
