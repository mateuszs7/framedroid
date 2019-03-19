package com.framedroid.framework.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by mateu on 11/04/2017.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface JsonParse {
    String name();
}
