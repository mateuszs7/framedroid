# DATABASE

> Framedroid has memory database, thats contains data only for session, it's mean if you restart your application database will be empty.

**Why only for the session?**

Lot's of mobile applications doesn't need to have full database. It's slow down application and create lot of problems.

![SQL database vs FD database](https://github.com/mateuszs7/framedroid/raw/master/docs/media/fd_memory_db.jpg "SQL database vs FD database")

## Modeling
To create model you have to extends your class by FDModel.

```java
class User extends FDModel {

}
```

Next, your model have to contain special method.
```java
public static Database<User>.Table fd() {
    return FDModel.fd(User.class);
}
```

```java
public class User extends FDModel<User> {
    public String name;

    public TestModel(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Database<User>.Table fd() {
        return FDModel.fd(User.class);
    }
}
```

## Managing objects

### Adding object
```java
User user = new User(123, "Matthew");
User.fd().add(user);
```

### Getting object

```java
User user = User.fd().get(123);
```

### Updating object
```java
User user = User.fd().get(123);
user.setName("New name");
User.fd().save(user);
```

### Deleting object
```java
User.fd().remove(123);
```
or
```java
User user = User.fd().get(123);
User.fd().remove(user);
```

**deleting all objects**

```java
User.fd().removeAll();
```

## Queries
Available methods:
* .execute();
* .first();
* .last();
* .count()

```java
Select.from(User.class).where("name = ? or name = ?", "Matthew", "New name").execute();
```

```java
Select.from(User.class).count();
```

```java
Select.from(User.class).first();
```
