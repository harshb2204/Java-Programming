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
