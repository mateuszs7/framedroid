# Printers

### Info printers

You can print string to console log in very simple way

```java
FD.print("Log some string");
```

```java
FD.p("Log some string");
```

You can log int, double, float, boolean and every object

```java
FD.p(11.3);
```

```java
FD.p(true);
```

```java
Object obj = getSomeObject();
FD.p(obj);
```

And more than one

```java
Object obj = getSomeObject();
FD.p("First print", 1.33, true, obj);
```


### Error printers

You can log errors like

```java
FD.e("User with id", id, "doasn't exist");
```