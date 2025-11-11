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

## Interface Implementation:

- Overriding method can not have more restrict access specifiers.
- Concrete class must override all the methods declared in the interface.
- Abstract classes are not forced to override all the methods.
- A class can implement from multiple interfaces.

### Example 1: Concrete Class Implementation

**Interface Definition:**
```java
public interface Bird {
    public void fly();
}
```

**Concrete Class Implementation:**
```java
public class Eagle implements Bird{
    @Override
    public void fly() {
        System.out.println("Eagle Fly Implementation");
    }
}
```

### Example 2: Abstract Class Implementation of Interface

**Interface Definition:**
```java
public interface Bird {
    public void canFly();
    public void noOfLegs();
}
```

**Abstract Class Implementation:**
```java
public abstract class Eagle implements Bird{
    @Override
    public void canFly() {
        //Implementation goes here
    }
    
    public abstract void beakLength();
}
```

**Concrete Class Extending Abstract Class:**
```java
public class WhiteEagle extends Eagle{
    @Override
    public void noOfLegs() {
        //implement interface method
    }
    
    @Override
    public void beakLength() {
        //implementing abstract class method
    }
}
```

## Nested Interface

A Nested Interface can be declared within another Interface or within a Class. Its general purpose is to group logical related interfaces.

### Rules for Nested Interface:

- A nested interface declared within an interface must be public.
- A nested interface declared within a class can have any access modifier.
- When you implement outer interface, inner interface implementation is not required and vice versa.

### Example 1: Nested Interface within Interface

**Interface with Nested Interface:**
```java
public interface Bird {
    public void canFly();

    public interface NonFlyingBird{
        public void canRun();
    }
}
```

**Class Implementing Only Outer Interface:**
```java
public class Eagle implements Bird{
    @Override
    public void canFly() {
        //Implementation goes here
    }
}
```

**Class Implementing Only Nested Interface:**
```java
public class Eagle implements Bird.NonFlyingBird{
    @Override
    public void canRun() {
    }
}
```

**Class Implementing Both Interfaces:**
```java
public class Eagle implements Bird, Bird.NonFlyingBird{
    @Override
    public void canRun() {
    }
    
    @Override
    public void canFly() {
    }
}
```

**Main Class Using Nested Interface:**
```java
public class Main {
    public static void main(String args[]) {
        Bird.NonFlyingBird obj = new Eagle();
        obj.canRun();
    }
}
```

### Example 2: Nested Interface within Class

**Class with Nested Interface:**
```java
public class Bird {
    protected interface NonFlyingBird{
        public void canRun();
    }
}
```

**Class Implementing Nested Interface:**
```java
public class Eagle implements Bird.NonFlyingBird{
    @Override
    public void canRun() {
    }
}
```

## Interface Vs Abstract Class

| S.No. | Abstract Class | Interface |
|-------|----------------|-----------|
| 1. | Keyword used: "abstract" | Keyword used: "interface" |
| 2. | Child classes need to use "extends" | Child classes need to use "implements" |
| 3. | Can have both abstract and non-abstract methods | Can have only abstract methods (from Java 8 onwards, it can also have default, static, and private methods where implementation can be provided) |
| 4. | Can extend from another class and multiple interfaces | Can only extend from other interfaces |
| 5. | Variables can be static, non-static, final, non-final, etc. | Variables are by default CONSTANTS |
| 6. | Variables and methods can be private, protected, public, default | Variables and methods are by default public (in Java 9, private methods are supported) |
| 7. | Multiple Inheritance is not supported | Multiple Inheritance is supported with this in Java |
| 8. | Can provide the implementation of an interface | Cannot provide implementation of any other interface or abstract class |
| 9. | Can have a constructor | Cannot have a constructor |
| 10. | To declare a method abstract, the "abstract" keyword must be used, and it can be protected, public, or default | No need for any keyword to make a method abstract; it is public by default |