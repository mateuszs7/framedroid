# framedroid

## Installation (gradle)

#### 1. Add repository
Put maven repository in Your's project build.gradle

```json
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

```json
dependencies {
    compile 'com.framedroid.framework:framedroid:0.1.0'
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