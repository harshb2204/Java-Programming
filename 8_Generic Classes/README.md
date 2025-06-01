# Generic Classes

*Generic class helps us to write a class in a generic manner that helps to avoid the typecasting that we'll have to use with Object class.*

Let's see via an example:

```java
public class Print {
    Object value;

    public Object getPrintValue(){
        return value;
    }

    public void setPrintValue(Object value){
        this.value = value;
    }
}
```

Since Object is parent of every class, we used it here. Now here value can be of any type i.e. String, Integer etc. The only issue is that we'll have to typecast it as per our use.

```java
public class Main {
    public static void main(String args[]) {
        Print printObj1 = new Print();
        printObj1.setPrintValue(1);
        Object printValue = printObj1.getPrintValue();
        // We can not use printValue directly, we have to typecast it, else it will be compile time error
        if((int)printValue == 1){
            // do something
        }
    }
}
```

So we had to typecast printValue to int to compare it with 1.

## How to define Generic Classes?

* We can do it using `<T>`, this T can be any alphabet like A, B, C etc.
  So let's make our previous Print class generic:

```java
public class Print<T> {
    T value;

    public T getPrintValue(){
        return value;
    }

    public void setPrintValue(T value){
        this.value = value;
    }
}
```

Generic Type (in above example `<T>`) can be any non-primitive object.

```java
public class Main {
    public static void main(String args[]) {
        Print<Integer> printObj1 = new Print<Integer>();
        printObj1.setPrintValue(1);
        Integer printValue = printObj1.getPrintValue();
        if(printValue == 1){
            // do something
        }
    }
}
```

Therefore, while creating printObject I have replaced T by Integer, making it of an Integer type.

## Inheritance in Generic Classes

### 1) Non-Generic Subclass

```java
public class Print<T> {
    T value;

    public T getPrintValue(){
        return value;
    }

    public void setPrintValue(T value){
        this.value = value;
    }
}
```

```java
public class ColorPrint extends Print<String> {
}
```

So, here above we have extended/inherited a Generic Class to a non-generic subclass.

While extending/inheriting a generic class to a non-generic subclass, we have to define the type of T at the time of extending itself.

```java
public class Main {
    public static void main(String args[]) {
        ColorPrint colorPrintObj = new ColorPrint();
        colorPrintObj.setPrintValue("2");
    }
}
```

### 2) Generic Subclass

```java
public class Print<T> {
    T value;

    public T getPrintValue(){
        return value;
    }

    public void setPrintValue(T value){
        this.value = value;
    }
}
```

```java
public class ColorPrint<T> extends Print<T> {
}
```

```java
public class Main {
    public static void main(String args[]) {
        ColorPrint<String> colorPrintObj = new ColorPrint<>();
        colorPrintObj.setPrintValue("2");
    }
}
```

So, for a generic subclass, we can specify the type of T at the time of object creation.

## More than one Generic Type Example

- We can create as many number of generic parameters as we want, i.e. 1, 2, 3, 4 ... N | Classname <T1, T2, T3, T4 ... TN>

For example:

```java
public class Pair<K, V> {
    private K key;
    private V value;

    public void put(K key, V value){
        this.key = key;
        this.value = value;
    }
}
```

```java
public class Main {
    public static void main(String args[]) {
        Pair<String, Integer> pairObj = new Pair<>();
        pairObj.put("hello", 1243);
    }
}
```

So, here we've used two generic parameters.

*Also both the syntaxes are right:*

```java
Pair<String, Integer> object = new Pair<String, Integer>();
Pair<String, Integer> object2 = new Pair<>();
```

## Generic Method

What if we only want to make a method generic, not the complete class? We can write generic methods too.
- Type parameter should be before the return type of the method declaration.
- Type parameter scope is limited to the method only.

To make a method generic, put the generic type that we want it to accept before the return type. For example:

```java
public class GenericMethod {
    public <K, V> void printValue(Pair<K, V> pair1, Pair<K, V> pair2) {
        if (pair2.getKey().equals(pair2.getKey())) {
            // do something
        }
    }
}
```

### Generic Method with Single Generic Parameter

```java
public class Print {
    public <T> void setValue(T busObject) {
        // do something
    }
}
```

```java
public class Main {
    public static void main(String args[]) {
        Print printObj = new Print();
        printObj.setValue(new Bus());
    }
}
```

## Raw Type Object

It's a name of the generic class or interface without any type argument.

For example:

```java
public class Print<T> {
    T value;

    public T getPrintValue(){
        return value;
    }

    public void setPrintValue(T value){
        this.value = value;
    }
}
```

```java
public class Main {
    public static void main(String args[]) {
        Print<String> parametrizedTypePrintObject = new Print<>();
        // Internally it passes Object as parameterized type.
        Print rawTypePrintObject = new Print();
        rawTypePrintObject.setPrintValue(1);
        rawTypePrintObject.setPrintValue("hello");
    }
}
```

So while creating the object, we didn't pass any type as parameterized type. Therefore, internally it passes Object as parameterized type.

So rawTypePrintObject is a raw type object.

## Bounded Generics

Bounded generics can be used at generic class and method level.

- **Upper Bound** (`<T extends Number>`) means T can be of type Number or its subclass only. Here, superclass (in this example Number) can also be an interface.
- Multi Bound is also possible.

It is used to restrict what all objects we can pass at this type parameter.

### For Example: Upper Bound

```java
public class Print<T extends Number> {
    T value;

    public T getPrintValue(){
        return value;
    }

    public void setPrintValue(T value){
        this.value = value;
    }
}
```

// Can also be interface

### Usage Examples

```java
public class Main {
    public static void main(String args[]) {
        Print<Integer> parameterizedTypePrintObject = new Print<>();
    }
}
// Correct since Integer is a child class of Number
```

```java
public class Main {
    public static void main(String args[]) {
        Print<String> parameterizedTypePrintObject = new Print<>();
    }
}
// Incorrect as String is not a child class of Number
```


