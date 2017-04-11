[ ![Download](https://api.bintray.com/packages/mateuszs7/maven/framedroid/images/download.svg) ](https://bintray.com/mateuszs7/maven/framedroid/_latestVersion)

# FrameDroid
> FrameDroid is light framework for android applications. It's provide short methods, resources and class for most android problems. Each method can be call from any place in your app.
- Memory database
- API helper
- printers, toasts, resources, ranges
- json builder
- view, time, prefs, screen helpers

## Installation (gradle)

#### 1. Add repository
Put maven repository in Your's project build.gradle

```
allprojects {
    repositories {
        jcenter()
        maven {
            url  "http://dl.bintray.com/mateuszs7/maven"
        }
    }
}
```

#### 2. Add dependency in Your's module build.grandle

```
dependencies {
    compile 'com.framedroid.framework:framedroid:0.1.1'
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


## Documentation
* [helpers](./docs/HELPERS.md)
* [printers](./docs/PRINTERS.md)
* [api](./docs/API.md)
* [methods](./docs/METHODS.md)
* [database](./docs/DATABASE.md)

## TODO
- [ ] parsing json to model (using annotations)
- [ ] events
- [ ] remove chars from string
- [ ] api interface
- [ ] database relations