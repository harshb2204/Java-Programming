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
