# Interfaces

## What is Interface?

Interface is something which helps 2 system to interact with each other, without one system has to know the details of other.

Or in simple term I can say, it helps to achieve ABSTRACTION.

## How to define the interface?

Interface declaration consist of:
- Modifiers
- "interface" keyword
- Interface Name
- Comma separated list of parent interfaces
- Body

Only Public and Default Modifiers are allowed (Protected and private are not allowed)

### Example 1: Public Interface

```java
public interface Bird {
    public void fly();
}
```

### Example 2: Default Interface

```java
interface Bird {
    public void fly();
}
```

### Comma separated list of parent interfaces (it can extend from Class) Example:

```java
public interface NonFlyingBirds extends Bird, LivingThings{
    public void canRun();
}
```

## Why we need Interface?

### 1. Abstraction:

Using interface, we can achieve full Abstraction means, we can define WHAT class must do, but not HOW it will do.

**Interface Definition:**
```java
public interface Bird {
    public void fly();
}
```

**Class Implementation:**
```java
public class Eagle implements Bird{
    @Override
    public void fly() {
        //the complex process of flying take place here
    }
}
```

### 2. Polymorphism:

- Interface can be used as a Data Type.
- we can not create the object of an interface, but it can hold the reference of all the classes which implements it. And at runtime, it decide which method need to be invoked.

**Interface Definition:**
```java
public interface Bird {
    public void fly();
}
```

**Main Class:**
```java
public class Main {
    public static void main(String args[]){
        Bird birdObject1 = new Eagle();
        Bird birdObject2 = new Hen();

        birdObject1.fly();
        birdObject2.fly();
    }
}
```

**Eagle Class:**
```java
public class Eagle implements Bird {
    @Override
    public void fly() {
        System.out.println("Eagle Fly Implementation");
    }
}
```

**Hen Class:**
```java
public class Hen implements Bird {
    @Override
    public void fly() {
        System.out.println("Hen Fly Implementation");
    }
}
```

### 3. Multiple Inheritance:

In Java Multiple inheritance is possible only through Interface only.

#### Diamond problem:

**Problem with Classes (NOT ALLOWED):**

**Main Class:**
```java
public class Main {
    public static void main(String args[]){
        Crocodile obj = new Crocodile();
        obj.canBreathe();
    }
}
```

**WaterAnimal Class:**
```java
public class WaterAnimal {
    public boolean canBreathe() {
        return true;
    }
}
```

**LandAnimal Class:**
```java
public class LandAnimal {
    public boolean canBreathe(){
        return true;
    }
}
```

**Crocodile Class (THIS DOES NOT WORK):**
```java
public class Crocodile extends LandAnimal, WaterAnimal{
}
```
*Note: Java does not support multiple inheritance of classes. The above code will cause a compilation error.*

**Solution with Interfaces:**

**Main Class:**
```java
public class Main {
    public static void main(String args[]){
        Crocodile obj = new Crocodile();
        obj.canBreathe();
    }
}
```

**LandAnimal Interface:**
```java
public interface LandAnimal {
    public boolean canBreathe();
}
```

**WaterAnimal Interface:**
```java
public interface WaterAnimal {
    public boolean canBreathe();
}
```

**Crocodile Class (IMPLEMENTING MULTIPLE INTERFACES):**
```java
public class Crocodile implements LandAnimal, WaterAnimal {
    @Override
    public boolean canBreathe() {
        return true;
    }
}
```

## Methods in Interface:

- All methods are implicit public only.
- Method can not be declared as final.

**Example:**
```java
public interface Bird {
    void fly();              // implicitly public
    public void hasBeak();   // explicitly public (but redundant)
}
```

## Fields in Interface:

- Fields are public, static and final implicitly (CONSTANTS).
- You can not make field private or protected.

**The following two declarations are equivalent:**

```java
public interface Bird {
    int MAX_HEIGHT_IN_FEET = 2000;
}
```

**Equals to:**

```java
public interface Bird {
    public static final int MAX_HEIGHT_IN_FEET = 2000;
}
```
