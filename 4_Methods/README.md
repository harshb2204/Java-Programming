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
