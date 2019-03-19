[![](https://jitpack.io/v/mateuszs7/framedroid.svg)](https://jitpack.io/#mateuszs7/framedroid)

# FrameDroid
> FrameDroid is a light framework for android applications. It's provide short methods, resources and class for most android problems. Each method can be call from any place in your app.
- Memory database
- API helper
- printers, toasts, resources, ranges
- json builder
- view, time, prefs, screen helpers
- json parser to model
- simple events

## Installation (gradle)

#### 1. Add repository
Put maven repository in Your's project build.gradle

```
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

#### 2. Add dependency in Your's module build.grandle

```
dependencies {
    compile 'com.github.mateuszs7:framedroid:0.2.13'
}
```

#### 3. Put initialization code to your `Application` class

```java
public class YourAppName extends Application {
@Override
    public void onCreate() {
        super.onCreate();
        FrameDroid.init(this);
    }
}
```



## Usage

> extends your activity

```java
...
import com.framedroid.framework.Activity;

public class YourActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen().width();
    }
}
```

or

> use static FrameDroid

```java
    FrameDroid.screen().width();
```
or

> use static FD

```java
    FD.screen().width();
    FD.print("Some logs");
    FD.p("short log");
```


## List of helper methods
* print(anything), p(anything), print(a1, a2, a3, ...), p(a1, a2, a3, ...)
* error(anything), e(anything), error(a1, a2, a3, ...), e(a1, a2, a3, ...)
* toast(text), toast(stringRes), toast(text, length), toast(stringRes, length)
* color(colorRes), color(hex)
* string(stringRes), string(stringRes, args...)
* drawable(drawableRes)
* range(start, end), range(start, end, perioid)
* toggle(list, object)
* reverse(list)

More informations in [methods](./docs/METHODS.md) section

## Documentation
* [helpers](./docs/HELPERS.md)
* [printers](./docs/PRINTERS.md)
* [methods](./docs/METHODS.md)
* [database](./docs/DATABASE.md)
* [events](./docs/EVENTS.md)

## TODO
- [X] parsing json to model (using annotations)
- [X] events
- [ ] remove chars from string
- [ ] database relations
