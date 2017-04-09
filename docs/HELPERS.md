# Helpers
> you have to extend Activity to use this helpers


## ScreenHelper screen()

* `int width()`
* `int height()`
* `int halfWidth()`
* `int halfHeight()`


## TimeHelper time()

* `long diff()`
* `String diffFormat()`
* `Calendar toCalendar()`
* `void setFormat()`
* `String print()`
```java
Calendar calendar = Calendar.getInstance();

// print data in "yyyy-MM-dd" format i.e. 2017-03-23
Log.i("time format 1", time().print(calendar, "yyyy-MM-dd"));

// print data in default format i.e. 2017-03-23 17:55:31
Log.i("time format 2", time().print(calendar, "yyyy-MM-dd"));
```


## JsonHelper json()

Usage

```java
json().build(
    new JsonHelper.PreJson("id", 1234),
    new JsonHelper.PreJson("name", "Some name"),
    new JsonHelper.PreJson("price", 12.34),
    new JsonHelper.PreJson("anotherJson", json().build(
        new JsonHelper.PreJson("desc", "test")
    ))
);
```

Above code will produce json like:
```json
{
   "id":1234,
   "name":"Some name",
   "price":12.34,
   "anotherJson":{
      "desc":"test"
   }
}
```


## PrefsHelper prefs()

* `set()`
* `get()`
* `is()`

```java
prefs().set("specialKey", "someValue);
```

```java
prefs().get("specialKey")
// above code will return "someValue"
```

```java
prefs().is("specialKey")
// true

prefs().is("otherKey")
// false
```


## ViewHelper view()

* `void multiClick(Activity, View.OnClickListener, int...)`
* `void multiClick(View, View.OnClickListener, int...)`
```java
multiClick(view, myClickListener, R.id.likeIcon, R.id.heartIcon, R.id.okIcon);
```

* `String getValue(Activity, int)`
```java
getValue(this, R.id.username);
```

* ViewWork list(View, int...)
* ViewWork list(Activity, int...)
```java
list(MainActivity.this, R.id.firstText, R.id.secondText).set(new ViewHelper.Format<TextView>() {
    @Override
    public void make(TextView view) {
        view.setAlpha(0.3f);
        view.setTranslationX(200f);
    }
});
```