package com.framedroid.framework.events;

import android.util.ArrayMap;

import java.util.Map;

/**
 * Created by mateu on 12/04/2017.
 */

public class EventsCore {
    private static Map<String, Event> eventMap = new ArrayMap<>();

    public static void addEvent(Event event) {
        eventMap.put(event.getName(), event);
    }

    public static Event getEvent(String name) {
        Event event = eventMap.get(name);
        if (event == null)
            throw new RuntimeException("Event named '" + name + "' doasn't exist");
        return event;
    }

}
