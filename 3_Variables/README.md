## What is a variable?
- Variable is a container which holds a value.
- DataType variablename  = value;
- java is a static typed language --> you have to define datatype of variable
- java is a strong typed language --> each datatype has a range


## Variable naming Comvention
- Variable naming is case sensitive
- Variable name can be any legal identifier means can contain Unicode letters and Digits.
- Variable name can start with $, _, and letter.
- Variable name cannot be a JAVA reserved Keyword like "new", "class", "while", "for", "interface", "int", "float", etc.
- Variable should be all small if it contains only 1 word; else camel case should be followed.


## Types Of Variables

### Primitive Type
- **char**
  - 2 bytes i.e. 16 bits
  - Character representation of ASCII values
  - Range: 0 to 65535 i.e. '\u0000' to '\uffff'
  - Default value is '\u0000' i.e. NULL

- **byte**
  - 1 byte i.e. 8 bits
  - Signed 2's complement
  - Range: -128 to 127
  - signed means negative numbers are also allowed, if the msb is 0 it is positive if msb is 1 it is negative 
  say 3 -> 0011, for -3 we need to take 2s complement therefore -> 1101, if u add them ans is 0 (only 4 bits are considered)
  - default value is 0
  - default value only assigned to class member variables, not inside a method(there u have to specify the value)

- **short**
  - 2 bytes i.e. 16 bits
  - Signed 2's complement
  - Range: -32768 to 32767
  - Default value is 0

- **int**
  - 4 bytes i.e. 32 bits
  - Range: -2^31 to 2^31 - 1
  - Default value is 0
  - Signed 2's complement

- **long**
  - 8 bytes i.e. 64 bits
  - Signed 2's complement
  - Range: -2^63 to 2^63 - 1
  - Default value is 0
  - e.g., long var = 100L; // This L signifies that it is long type.

- **boolean**
  - 1 bit
  - Value: True or False
  - Default value is False


## Types of Conversion

1) **Widening / Automatic Conversion**
   - Automatic conversion when we go from lower data type to higher data type.

   ```
   byte (1 byte) 
   short (2 bytes) 
   int (4 bytes) 
   long (8 bytes)
   ```

   e.g., 
   ```java
   int var = 10;
   long varLong = var; // Automatically converted int to long
   ```

2) **Narrowing / Downcasting / Explicit Conversion**
   - It is the opposite of widening, i.e., going from higher data type to lower data type.
   - In this case, downcasting doesn't happen automatically, so we have to manually do it.

   e.g., 
   ```java
   int integerVariable = 10;
   byte byteVariable = (byte) integerVariable;
   ```

   - If we're downcasting beyond range, then it'll again reset to -128 and it goes on.
   - So if `integerVariable`'s value is 128, then `byteVariable`'s value will be -128 (next after 127 is -128 for byte).
   - If it's 148, then `byteVariable`'s value will be -108.

3) **Promotion During Expression**
   - This happens internally during expression evaluation.
   - As soon as a value of an expression crosses the range of the datatype, then promotion happens internally to a higher datatype.
   - `byte` and `short` promote to `int`.

   e.g., 
   ```java
   byte a = 1;
   byte b = 127;
   byte c = (byte) (a + b); // Won't work since range is crossing
   // We'll have to declare it as int as the result will be int.
   ```

   - Although we can explicitly downcast it, the value will change as per explicit downcasting terms.
   - This is also explicit casting during expression evaluation.
   - In an expression, if one datatype is of higher datatype, then all others will also be automatically converted to higher datatype.

   e.g., 
   ```java
   int a = 34;
   double doubleVar = 2.0;
   int sum = a + doubleVar; // Will give error
   double sum = a + doubleVar; // Correct
   ```

## Kind of Variables

### Member / Instance Variable
- It is a variable of the class i.e., it is created when an object of the class containing it is created. So, each object of the class has its individual copy of the member variable.

### Local Variable
- These variables are the variables that are defined inside a method.
- If the method finishes, it gets destroyed.

### Static / Class Variable
- Only one copy of static/class variable exists. All objects can access it using the class name.

### Method Parameters
- These are the variables that are passed to a method.

### Constructor Parameters
- These are the variables that are passed to a constructor.

## Fractional Types
![](/diagrams/fractional1.png)
![](/diagrams/fractional2.png)
![](/diagrams/fractional3.png)
![](/diagrams/fractional4.png)

Therefore always use BigDecimal




## Reference Data Types / Non-Primitive Data Types

There are mainly 4 types of reference data types:
- Class
- String
- Interface
- Array

### What is a Reference?
Let's understand with an example of class:

```java
public class Employee {
    int empId;
    
    public int getEmpId() {
        return empId;
    }
    
    public void setEmpId(int empId) {
        this.empId = empId;
    }
}
```

To create an object of class Employee:
```java
Employee empObject = new Employee();
```
new keyword allocates a memory block object and the variables
name holds a reference to the actual memory.
![](/diagrams/reference1.png)
- In Java ,everything is pass by value.So with the help of renference variables we're achieving the functionality of pointers in CPP

### String
- Strings are immutable in Java
- It contains String literal
- Inside heap, there is a fixed memory space called String Constant Pool
- Each String literal in String Constant Pool has a corresponding String object
![](/diagrams/stringconstantpool.png)

Since strings are immutable, 2 same strings holds reference to same string literal in String Constant Pool. As shown in s1, s2, holds reference to same string literal "hello". This applies for normal strings. If we create using new keyword, it'll be stored as normal object in heap.
e.g., String s3 = new String("hello");
s1.equal(s2) checks the contents of the string
s1==s2 checks if they are pointing to the same memory or not

### Interface
To understand better, let's create an interface & implement it:

```java
public interface Person {
    public String profession();
}
```

This interface can be implemented by different classes:

```java
public class Teacher implements Person {
    @Override
    public String profession() {
        return "teacher";
    }
}

public class Engineer implements Person {
    @Override
    public String profession() {
        return "software engineer";
    }
}
```

Now let's create a few objects in a class:
```java
public class Student {
    public static void main(String args[]) {
        Person softwareEngineer = new Engineer();
        Person teacher = new Teacher();
        Teacher teacher1 = new Teacher();
        Engineer softwareEngineer1 = new Engineer();
    }
}
```
![](/diagrams/interface.png)

### Array
- Sequence of memory storing same datatype
For example:
```java
int[] arr = new int[5];    // capacity of array

```
![](/diagrams/arrays.png)
- Arrays can be assigned in multiple ways. It can be of multiple types like 1D , 2D exc.

## Wrapper Class

For each of the primitive data types, we have corresponding reference types that are known as Wrapper Classes:

| Primitive Type | Wrapper Class |
|---------------|---------------|
| int | Integer |
| char | Character |
| short | Short |
| byte | Byte |
| long | Long |
| float | Float |
| double | Double |
| boolean | Boolean |

There are two processes involved:
- Autoboxing
- Unboxing

### Why the need for wrapper classes?
- We get the advantage of passing by reference. For example, if we've declared a wrapper type of int i.e Integer type, we can change it later on & it'll change in memory as well because in wrapper we're storing reference.
- In primitive this won't be possible as they're stored in stack & not heap.

- The collections works on objects only i.e on reference data types, so we need wrapper class to use collections.

### Autoboxing
- To convert a primitive data type to its wrapper.
For example:
```java
int a = 10;
Integer a1 = a;    // primitive to its wrapper
```

### Unboxing
- To convert a wrapper class to primitive
For example:
```java
Integer n = 20;
int n1 = n;    // wrapper to primitive
```

## Constant Variable
- We cannot change the value of a constant variable. This is usually created using final keyword.
For example:
```java
static final VAR = 10;
```
- static means only one copy exists
- final means the value of VAR can't be changed





