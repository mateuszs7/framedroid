# Helpers
> you have to extend Activity to use this helpers

### Usage
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

## ScreenHelper `screen()`

* `int width()`
* `int height()`
* `int halfWidth()`
* `int halfHeight()`

