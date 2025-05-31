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
