# Methods
## toasts
* void toast(String, int)
```java
toast("Hey", Toast.LENGTH_SHORT);
```
* void toast(String)
```java
toast("Hey"); // Default duration - Toast.LENGTH_SHORT
```
* void toast(int, int)
```java
toast("R.string.toast_text, Toast.LENGTH_LONG);
```
* void toast(int)
```java
toast("R.string.toast_text); // Default duration - Toast.LENGTH_SHORT
```


## color
* int color(int);
```java
color(R.color.white);
```

* int color(String);
```java
color("#000000");
```

## string
* String string(int);
```java
string(R.string.someString);
```

## drawable
* Drawable drawable(int);
```java
drawable(R.drawable.some_background);
```

## range
* Integer[] range(int, int);

```java
range(1, 10);
// return {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
```

* Integer[] range(int, int, int);

```java
range(0, 10, 2);
// return {0, 2, 4, 6, 8, 10}
```

* Double[] range(double, double);

```java
range(0.8, 1.3);
// return {0.8, 0.9, 1.0, 1.1, 1.2, 1.3}
```

* Double[] range(double, double, double);

```java
range(0.8, 1.4, 0.2);
// return {0.8, 1.0, 1.2, 1.4}
```

## toggle
* toggle(List, Object);

```java
List<String> colors = new ArrayList();

toggle(colors, "ABC");
toggle(colors, "QWE");
FD.p(colors);
// ["ABC", "QWE"]

toggle(colors, "ABC");
FD.p(colors);
// ["QWE"]
```
