# EVENTS
> Provide methods and class that help you to subscribe event and publish data. You can call them on any place in your project

At first you have to subscribe event:
```java
Event.subscribe("someEventsName", new EventListener() {
    @Override
    public void onEvent(Object...data) {
        FD.p("Event response", data);
    }
});
 ```

Then you can send data to the event by calling `publish` method
```java
Event.publish("someEventsName", "Hello", "world");
```

It will display `Event response Hello world` in android logcat

Receiving data in specified type is possible by setting generic type

```java
Event.subscribe("clientUpdated", new EventListener<Client>() {
    @Override
    public void onEvent(Client...data) {
        FD.p("Event response", data[0].getName());
    }
});
```

```java
Client client = Client.get(123);
client.setName("New name");
Event.publish("clientUpdated", client);
```

It will display `Event response New name` in android logcat