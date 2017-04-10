package com.framedroid.framework.database;

import android.content.Intent;
import android.util.ArrayMap;

import com.framedroid.framework.FD;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

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
    private List<Condition> condList;
    private Map<String, Integer> listIndex;

    public Where(Class cls, List<T> elements, String conditions, Object... params) {
        this.elements = elements;
        this.conditions = conditions;
        this.params = params;
        this.cls = cls;
    }

    public List<T> parseConditions() throws Exception {
        String[] cond = conditions.split("(or)|(and)");

        FD.p(cond);
        condList = new ArrayList<>();
        listIndex = new ArrayMap<>();
        for (int i = 0; i < cond.length; i++) {
            Condition c = Condition.fromRaw(cond[i], params[i]);
            condList.add(c);
            listIndex.put(c.fieldName, i);
        }

        FD.p("XX", condList);
        String[] orConditions = conditions.split("or");
        for (int i = 0; i < orConditions.length; i++) {
            String or = orConditions[i];
            String[] andConditions = or.split("and");
            for (int j = 0; j < andConditions.length; j++) {
                String and = andConditions[j];
                Condition tCond = findConditionByName(Condition.nameFromRaw(and));
                tCond.type = (j + 1 < andConditions.length) ? Condition.Type.AND : Condition.Type.OR;

            }
        }

        List<T> result = new ArrayList<>();
        for (T obj : elements) {
            Condition.Type last = Condition.Type.OR;
            boolean init = true;
            boolean resultCondition = true;

            for (Condition c : condList) {
                Field field = cls.getField(c.fieldName);
                boolean pass = field.get(obj).equals(c.value);
                if (init) {
                    init = false;
                    resultCondition = pass;
                } else
                    resultCondition = last == Condition.Type.OR ? resultCondition || pass : resultCondition && pass;
                last = c.type;
                FD.p("resultCondition", resultCondition);
            }

            if (resultCondition)
                result.add(obj);
        }

        FD.p(condList);
        FD.p(result);
        return result;
    }

    private Condition findConditionByName(String name) {
        return condList.get(listIndex.get(name));
    }


    private String getFieldName(String condition) {
        return condition.replaceAll("[^a-zA-Z0-9 -]", "");
    }


}
