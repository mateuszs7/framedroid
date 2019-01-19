package com.framedroid.framework.events;

/**
 * Created by mateu on 12/04/2017.
 */

public interface EventListener<T> {
    void onEvent(T...data);
}
