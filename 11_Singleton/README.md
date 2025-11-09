# Singleton Class

## The objective of this class is to create only single object.
For example: If we want to create a DB Connection, we want it to be singleton.

## Different ways of creating Singleton Class:
- Eager Initialization
- Lazy Initialization
- Synchronization Block
- Double Check Lock (there is a memory issue, resolved through Volatile instance variable)
- Bill Pugh Solution
- Enum Singleton

## ⇒ Eager Initialization

```java
public class DBConnection {
    
    private static DBConnection connObject = new DBConnection();
    
    private DBConnection() {
    }
    
    public static DBConnection getInstance() {
        return connObject;
    }
}
```

So we have:
1) Created & initialized the object itself as private static so that it is associated with class and cannot be accessible outside the class.

2) Created private constructor so that nobody is allowed to create the object of the class using "new" keyword.

3) Created a public static method so that if other classes needed the object of this class, they can access it via this method which return this same object only.

```java
public class Main {
    
    public static void main(String args[]) {
        
        DBConnection connObject = DBConnection.getInstance();
    }
}
```

So, this is eager initialization as we're creating & initializing the object as soon as the program starts even if it isn't being currently used by others.
