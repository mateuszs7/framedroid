package com.framedroid.framework.events;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mateu on 12/04/2017.
 */

public class Event {
    private String name;
    private List<EventListener> subscribers = new ArrayList<>();

    public static Event create(String name) {
        Event event = new Event();
        event.name = name;
        EventsCore.addEvent(event);
        return event;
    }

    public static void subscribe(String name, EventListener eventListener) {
        Event event;
        try {
            event = EventsCore.getEvent(name);
        } catch (RuntimeException e) {
            event = create(name);
        }
        event.subscribers.add(eventListener);
    }

    public static void unsubscribe(String name, EventListener eventListener) {
        Event event = EventsCore.getEvent(name);
        event.subscribers.remove(eventListener);
    }

    public static <T> void publish(String eventName, T...objects) {
        Event event = EventsCore.getEvent(eventName);
        for (EventListener subscriber : event.subscribers) {
            subscriber.onEvent(objects);
        }
    }

    public static <T> void optPublish(String eventName, T...objects) {
        try {
            publish(eventName, objects);
        } catch (Exception e) {

        }
    }


    public String getName() {
        return name;
    }

}
