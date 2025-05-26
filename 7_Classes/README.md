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
