## Concrete Class
-------------------
- These are those classes that we can create an instance using the NEW keyword.
- All the methods in this class have implementation.
- It can also be your child class from interface or extend abstract class.
- A class access modifier can be "public" or "package private" (no explicit modifier defined).

### For example:

```java
public class Person {
    int empID;

    Person(int empID){
        this.empID = empID;
    }

    public int getEmpID(){
        return empID;
    }
}

public interface Shape {
    public void computeArea();
}

public class Rectangle implements Shape{
    @Override
    public void computeArea(){
        System.out.println("Compute Rectangle Area");
    }
}
```

## Abstract Class (0 to 100% Abstraction):
------------------------------------------
Show only important features to users and hide its internal implementation.

2 ways to achieve abstraction:

- Class is declared as abstracted through keyword "abstract".
- It can have both abstract (method without body) and non-abstract methods.
- We cannot create an instance of this class.
- If parent has some features which all child classes have in common, then this can be used.
- Constructors can be created inside them. And with super keyword from child classes we can access them.

### For example:

```java
public abstract class Car {
    int mileage;

    Car(int mileage){
        this.mileage = mileage;
    }

    public abstract void pressBreak(); // Abstract Method
    public abstract void pressClutch(); // Abstract Method
    public int getNumberOfWheels(){ // Non-Abstract Method
        return 4;
    }
}
```



```java
public abstract class LuxuryCar extends Car {
    LuxuryCar(int mileage){
        super(mileage);
    }

    public abstract void pressDualBreakSystem(); // Additional abstract method

    @Override
    public void pressBreak(){
        // implementation of it goes here
    }
}

public class Audi extends LuxuryCar {
    Audi(int mileage){
        super(mileage);
    }

    @Override
    public void pressClutch() {
        // its implementation goes here
    }

    @Override
    public void pressDualBreakSystem() {
        // its implementation goes here
    }
}
```

- `LuxuryCar` is another abstract class inheriting from the abstract class `Car` and adds an additional abstract method.
- `Audi` is a concrete class inheriting from the abstract class `LuxuryCar` and implements all abstract methods.

## Super and Sub Class:
-----------------------
- A class that is derived from another class is called a Subclass.
- And from class through which Subclass is derived it's called Superclass.
- In Java, in the absence of any other explicit superclass, every class is implicitly a subclass of Object class.
- Object is the topmost class in Java.
- It has some common methods like clone(), toString(), equals(), notify(), wait() etc.

### For example:

```java
public class ObjectTest {
    public static void main(String[] args) {
        ObjectTest obj = new ObjectTest();

        Object obj1 = new Person(1);
        Object obj2 = new Audi(10);

        System.out.println(obj1.getClass());
        System.out.println(obj2.getClass());
    }
}
```

Output:
```
class Person
class Audi
```

*Since Object is the parent class of every class in Java & we know that reference of child class can be kept in parent class, hence Audi & Person objects can be kept in reference of Object.*

## Nested Class:
----------------
Class within another class is called Nested Class.

**When to use?**
If you know that, a class(A) will be used by only one another class(B), then instead of creating a new file (A.java) for it, we can create nested class inside B class itself.
And it also helps to group logically related classes in one file.

**Scope:**
Its scope is same as of its Outer class.

It is of 2 types:
- Static Nested Class
- Non Static Nested Class
  - Member Inner Class
  - Local Inner Class
  - Anonymous Inner Class

---

Now let's go through types of nested classes one by one:

## Static Nested Class:
-----------------------
- It does not have access to the non-static instance variable and method of outer class.
- Its object can be initiated without initiating the object of outer class.
- It can be private, public, protected or package-private (default, no explicit declaration).

### For example:

```java
class OuterClass {
    int instanceVariable = 10;
    static int classVariable = 20;

    static class NestedClass {
        public void print() {
            System.out.println(classVariable + instanceVariable); // Error: static inner class can only access static variables/methods
        }
    }
}
```

### Accessing Static Nested Classes

Since static classes can be accessed directly with the name of the class, we only need the name of the outer class. Hence, an object of the outer class is not needed.

```java
public class ObjectTest {
    public static void main(String args[]) {
        OuterClass.NestedClass nestedObj = new OuterClass.NestedClass();
        nestedObj.print();
    }
}
```

---

- Nested Classes can be created with any type of access modifier, i.e. public, private, protected, default (package-private).

Let's take the example of a nested class with private access modifier:
- Nested class object can be created within the same class itself.

```java
class OuterClass {
    int instanceVariable = 10;
    static int classVariable = 20;

    private static class NestedClass {
        public void print() {
            System.out.println(classVariable);
        }
    }

    public void display() {
        NestedClass nestedObj = new NestedClass();
        nestedObj.print();
    }
}

public class ObjectTest {
    public static void main(String args[]) {
        OuterClass outerClassObj = new OuterClass();
        outerClassObj.display();
    }
}
```

So, to access the private nested class, we had to create an object within the class itself and then expose it.

## Inner Class or Non Static Nested Class:
-----------------------------------------
- It has access to all the instance variables and methods of the outer class.
- Its object can be initiated only after initiating the object of the outer class.

1. **Member Inner Class:**
   - It can be private, public, protected, or default.

### For Example:

```java
class OuterClass {
    int instanceVariable = 10;
    static int classVariable = 20;

    class InnerClass {
        public void print() {
            System.out.println(classVariable + instanceVariable);
        }
    }
}
```

*To invoke it, we'll need an object of the outer class:*

```java
public class ObjectTest {
    public static void main(String args[]) {
        OuterClass outerClassObj = new OuterClass();
        OuterClass.InnerClass innerClassObj = outerClassObj.new InnerClass();
        innerClassObj.print();
    }
}
```

---

## Local Inner Class:
---------------------
- These are those classes which are defined in any block like for loop, while loop block, if condition block, method etc.
- It cannot be declared as private, protected, or public. Only default (not defined explicit) access modifier is used.
- It cannot be initiated outside of this block.

### For Example:

```java
class OuterClass {
    int instanceVariable = 1;
    static int classVariable = 2;

    public void display() {
        int methodLocalVariable = 3;
        class LocalInnerClass {
            int localInnerVariable = 4;
            public void print() {
                System.out.println(instanceVariable + classVariable + methodLocalVariable + localInnerVariable);
            }
        }
        LocalInnerClass localObj = new LocalInnerClass();
        localObj.print();
    }
}
```

*So, it can be invoked inside the block only. As soon as the scope of block ends, its scope also ends.*

## Inheritance in Nested Classes
--------------------------------

### Example 1: One inner class can inherit another inner class in the same outer class

```java
class OuterClass {
    int instanceVariable = 1;
    static int classVariable = 2;

    class InnerClass1 {
        int innerClass1 = 3;
    }

    class InnerClass2 extends InnerClass1 {
        int innerClass2 = 4;
        void display() {
            System.out.println(innerClass1 + innerClass2 + instanceVariable + classVariable);
        }
    }
}

public class ObjectTest {
    public static void main(String args[]) {
        OuterClass outerClassObj = new OuterClass();
        OuterClass.InnerClass2 innerClass2Obj = outerClassObj.new InnerClass2();
        innerClass2Obj.display();
    }
}
```

---

### Example 2: Static inner class inherited by different class

```java
class OuterClass {
    static class NestedClass {
        public void display() {
            System.out.println("inside static nested class");
        }
    }
}

public class SomeOtherClass extends OuterClass.NestedClass {
    public void display1() {
        display();
    }
}
```

### Example 3: Non Static Inner Class inherited by different class

```java
class OuterClass {
    class InnerClass {
        public void display() {
            System.out.println("inside InnerClass");
        }
    }
}

public class SomeOtherClass extends OuterClass.InnerClass {
    SomeOtherClass() {
        new OuterClass().super();
        // As you know, when child class constructor invoked, it first invoked the constructor of parent.
        // But here the parent is InnerClass, so it can only be accessed by the object of OuterClass only.
    }

    public void display1() {
        display();
    }
}
```

## Anonymous Inner Class
------------------------

Anonymous Inner Class:
An inner class without a name is called an Anonymous class.

**Why is it used?**
- When we want to override the behaviour of the method without even creating any subclass.

### For Example:

```java
public abstract class Car {
    public abstract void pressBreak();
}

public class Test {
    public static void main(String args[]) {
        Car audiCarObj = new Car() {
            @Override
            public void pressBreak() {
                // my audi specific implementation here
                System.out.println("Audi specific break changes");
            }
        };
        audiCarObj.pressBreak();
    }
}
```

Now, we know that we cannot create an object of an abstract class, but here we had done it. Let's understand what happened:

So, 2 things happened behind the scene:
- A subclass is created, name decided by the compiler.
- Creates an object of the subclass and assigns its reference to the object.


