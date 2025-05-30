# Methods In Java

## What is method?
- Method is used to perform a certain task
- Collection of instructions that performs a specific task
- It can be used to bring the code reusability & re-usability

For example:
```java
public class Calculation {
    public int sum(int val1, int val2) {
        int total = val1 + val2;
        //doing some logging stuff
        System.out.println("val1 and val2 is: " + total);
        return total;    // Instruction 3
    }

    public int getTotalPenPrice() {
        int capPrice = 2;
        int penBodyPrice = 3;
        int totalPenPrice = sum(capPrice, penBodyPrice);
        return totalPenPrice;
    }
}
```

Here `sum` is a method which will take 2 arguments & return the addition of both the numbers. Since we reused the code of method to perform add so it provides reusability.

## How to declare method?

Method declaration consists of several parts:
```java
public int sum (int a, int b) throws Exception {
    // method body
}
```

Where:
- Access Specifier: `public`
- Return Type: `int`
- Method Name: `sum`
- Method Arguments: `(int a, int b)`

### Access Specifiers
- Defines accessibility of a method i.e who can use the method
- There are 4 types of access specifiers in Java:
  1) Public: can be accessed through any class in any package
  2) Private: can be accessed by methods only in the same class
  3) Protected: can be accessed by other classes in same package or other sub classes in different package
  4) Default: It can only be accessed by classes in same package. If we don't mention anything, then Default access specifier is used.

### Return Type
- It tells what type the method will return after computation. If the method don't return anything, void return type is used.
- Use class name or primitive data types as return type of the method.

### Method Name
- It should be verb (or some kind of action)
- Should start with small letter and follow camel case in case of multiple words

### Method Parameters
- It's a list of variables that will be used in the method
- Parameter list can be blank too

### Method Body
- Method body gets finished when you call 'return' in mid
- Gets finished when reached to the end
- We can end any method by return, even for void return type


## Types Of Methods

### System Defined Methods
- Methods which are already defined and ready to use in Java, like `Math.sqrt()`

### User Defined Methods
- Methods which the programmer creates based upon the program necessity

### Overloaded Method
- More than one method with the same name is created in the same class
- Overloaded Method only gets differentiated based on arguments, so name should be same, arguments should be different, and return type is not even considered

```java
public class Invoice {
    void getInvoice() {
        System.out.println("inside invoice method");
        return;
    }

    void getInvoice(String z) {
    }

    int getInvoice(int a) {
        // doing some other stuff
    }

    void getInvoice(int a, int b) {
        // something else
    }

    public void printInvoice() {
        getInvoice();
    }
}
```

### Overridden Method
- Subclass / Child Class has the same method as the parent class.

### Static Methods
- These methods are associated with the class.
- Can be called just with class name.
- Static methods cannot access non-static instance variables and methods.
- Static methods cannot be overridden.

**Method Hiding (Static Method Overriding)**
- When a subclass declares a static method with the same signature as a static method in the parent class, it's called method hiding, not method overriding.
- Unlike instance methods, static methods are resolved at compile-time based on the reference type, not the runtime object type.
- The method to be called is determined by the type of the reference variable, not the actual object type.

Example:
```java
class Parent {
    static void display() {
        System.out.println("Parent's display");
    }
}

class Child extends Parent {
    static void display() {
        System.out.println("Child's display");
    }
}

public class Main {
    public static void main(String[] args) {
        Parent p = new Child();
        p.display(); // Prints "Parent's display" because static methods are resolved at compile-time
    }
}
```

**So when to declare method static:**
- Methods which do not modify the state of the object can be declared static.
- Utility methods which do not use any instance variable and compute only on arguments.  
  Example: Factory design pattern

### Final Methods
- Final Method cannot be overridden in Java. It is so because final method means its implementation cannot be changed. If child class cannot change its implementation, then no use of overridden.

### Abstract Method
- It is defined only in abstract class.
- Only method declaration is done.
- Its implementation is done in child classes.

### Variable Arguments (Varargs)
- Allows a method to accept variable number of inputs in the parameter.
- Only one variable argument can be present in the method.
- It should be the last argument in the parameter list.
- Used when we don't know the exact number of arguments that will be passed to the method.

Example:
```java
public class Calculation {
    static int carPrice = 40;
    
    public int sum(int a, int... variable) {
        int output = 0;
        for(int var : variable) {
            output = output + var;
        }
        return output;
    }
}

public class Main {
    public static void main(String[] args[]) {
        Calculation calculationObj = new Calculation();
        calculationObj.sum(3);                          // passing one argument
        calculationObj.sum(8, 9, 10);                   // passing three arguments
        calculationObj.sum(3, 2, 3, 2, 2, 2, 2);       // passing seven arguments
    }
}
```

In this example:
- The `sum` method can accept any number of integer arguments after the first parameter
- The variable arguments are treated as an array inside the method
- We can iterate through the variable arguments using a for-each loop
- The method can be called with different numbers of arguments as shown in the main method
