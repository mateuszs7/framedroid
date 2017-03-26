# framedroid

## Installation
...

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

        Log.i("my screen width", String.valueOf(screen().width()));
    }
}
```

or

> use static FrameDroid

```java
    Log.i("my screen width", String.valueOf(FrameDroid.screen().width()));
```


## Documentation
* [helpers](./docs/HELPERS.md)