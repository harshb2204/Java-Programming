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

## ⇒ Lazy Initialization
- It is created to solve the problem of eager initialization where we're creating & initializing the object as soon as program starts.

```java
public class DBConnection {
    
    private static DBConnection conObject;
    
    private DBConnection() {
    }
    
    public static DBConnection getInstance() {
        if(conObject == null) {
            conObject = new DBConnection();
        }
        return conObject;
    }
}
```

So, in this the object will be created only when someone calls the provided method for the first time. After that the same object will be returned if someone needs the object & calls getInstance method.

* Its disadvantage is that if two threads tries to get the object at the same time, the object is null & hence two objects will be created.

## Synchronized Method:
- It is used to overcome the problem of Lazy Initialization where two objects might created if two threads tries to get the object for the first time.

```java
public class DBConnection {
    
    private static DBConnection conObject;
    
    private DBConnection() {
    }
    
    synchronized public static DBConnection getInstance() {
        if(conObject == null) {
            conObject = new DBConnection();
        }
        return conObject;
    }
}
```

So, this synchronized keyword does two things:
- Put a lock on the method.
- Unlock the method.

So, only one thread is allowed to enter the method at a time. Hence the possibility of 2 objects being created will be zero.

* The only disadvantage of using synchronized is that it is very very slow and generally not used.

## ⇒ Double Checked Locking (This is used majorly)
- It overcomes the problem in synchronized method i.e slowness.

```java
public class DatabaseConnection {
    
    private static volatile DatabaseConnection conObject;
    
    private DatabaseConnection() {
    }
    
    public static DatabaseConnection getInstance() {
        if(conObject == null) {
            synchronized (DatabaseConnection.class) {
                if(conObject == null) {
                    conObject = new DatabaseConnection();
                }
            }
        }
        return conObject;
    }
}
```

So, here the synchronized is not on method, but on the block. Because of synchronized on the block, the lock unlock once only.

* Its disadvantage is that there is a memory issue in this. Let's understand it.

![](/diagrams/doublecheckedlocking.png)

Each core has its dedicated L1 Cache which is used to cache objects, and it syncs with main memory from time to time.

**Scenario:**
1. Let's say Thread T1's computation is happening at Core-1. It enters getInstance() method to get the object.
2. Since it's the first time, conObject is null. T1 proceeds to create an object, which is then temporarily stored in Core-1's L1 cache.
3. At this point, the object has been created but not yet synced with main memory.
4. Simultaneously, Thread T2, whose computation is happening at Core-2, tries to get the object.
5. Since the object is not yet visible in main memory (or T2's cache), T2 also finds conObject as null.
6. T2 then proceeds to create a second object.
7. Therefore, two objects are created despite using Double Checked Locking.

**Resolution using `volatile` keyword:**
- This issue is solved using the `volatile` keyword.
- **`volatile` keyword means:** The object will be created directly in main memory instead of the CPU cache.
- If an object is declared `volatile`, any read or write operation happening to this object will always happen directly in main memory.
- **Note:** Since we're using main memory and synchronization, this approach is also a bit slow.

## ⇒ Bill Pugh Solution:
```java
public class DatabaseConnection {
    
    private DatabaseConnection() {
    }
    
    private static class DBConnectionHelper {
        private static final DatabaseConnection INSTANCE_OBJECT = new DatabaseConnection();
    }
    
    public static DatabaseConnection getInstance() {
        return DBConnectionHelper.INSTANCE_OBJECT;
    }
}
```

So, Bill Pugh Solution rectified the issue of eager initialization by putting the object inside private static nested class because this nested class do not get loaded on program startup. Instead it is loaded when it is referred.

## ⇒ ENUM SINGLETON
- As we know, in Enum, all constructors are private & only one object of enum is created per JVM. So by default enum are singleton only.

```java
enum DBConnection {
    INSTANCE;
}
```
