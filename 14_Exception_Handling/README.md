# What is Exception?

*   It's an event, that occurs during the execution of the program.
*   It will disrupt your program normal flow.
*   It Creates the Exception Object, which contain information about the Error like
    *   Its Type of Exception and Message
    *   Stack trace etc.
*   Runtime system use this Exception Object and find the class which can handle it.
![](/diagrams/exceptionhandling.png)

```java
public class Main {
    public static void main(String[] args) {
        Main sampleObj = new Main();
        sampleObj.method1();
    }

    private void method1(){
        method2();
    }

    private void method2(){
        method3();
    }

    private void method3(){
        int b = 5/0;
    }
}
```

```
Output:
Exception in thread "main" java.lang.ArithmeticException Create breakpoint: / by zero
    at Main.method3(Main.java:18)
    at Main.method2(Main.java:14)
    at Main.method1(Main.java:10)
    at Main.main(Main.java:6)
```
## Exception Hierarchy
![](/diagrams/exceptionhierarchy.png)

## Un-Checked / Runtime Exception:

These are the exceptions which occurs during runtime and compiler not forcing us to handle them.

**ArithmeticException:**

```java
public class Main {
    public static void main(String[] args){
        method1();
    }
    public static void method1() {
        throw new ArithmeticException();
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        int val = 5 / 0;
    }
}
```

```
Exception in thread "main" java.lang.ArithmeticException Create breakpoint: / by zero
at Main.main(Main.java:5)
```

**ClassCastException:**

```java
public class Main {
    public static void main(String[] args) {
        Object val = 0;
        System.out.println((String)val);
    }
}
```

```
Exception in thread "main" java.lang.ClassCastException Create breakpoint : java.lang.Integer cannot be cast to java.lang.String
at Main.main(Main.java:8)
```

**IndexOutOfBoundException:**

```java
public class Main {
    public static void main(String[] args) {
        int[] val = new int[2];
        System.out.println(val[3]);
    }
}
```

```
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException Create breakpoint: 3
at Main.main(Main.java:6)
```

**StringIndexOutOfBoundsException:**

```java
public class Main {
    public static void main(String[] args) {
        String val = "hello";
        System.out.println(val.charAt(5));
    }
}
```

```
Exception in thread "main" java.lang.StringIndexOutOfBoundsException Create breakpoint : String index out of range: 5
at java.lang.String.charAt(String.java:658)
at Main.main(Main.java:6)
```

**NullPointerException:**

```java
public class Main {
    public static void main(String[] args) {
        String val = null;
        System.out.println(val.charAt(0));
    }
}
```

```
Exception in thread "main" java.lang.NullPointerException Create breakpoint
at Main.main(Main.java:6)
```

**IllegalArgumentException:**

```java
public class Main {
    public static void main(String[] args) {
        int val = Integer.parseInt("abc");
    }
}
```

```
Exception in thread "main" java.lang.NumberFormatException Create breakpoint: For input string: "abc"
at java.lang.NumberFormatException.forInputString (NumberFormatException.java:65)
at java.lang.Integer.parseInt(Integer.java:580)
at java.lang.Integer.parseInt(Integer.java:615)
at Main.main(Main.java:5)
```

## Checked / Compile time Exception:

Compiler verifies them during the compile time of the code and if not handled properly, code compilation will fail.

```java
public class Main {
    public static void main(String[] args) {
        method1();
    }
    public static void method1(){
        throw new ClassNotFoundException();
    }
}
```

```
Main.java:9: error: unreported exception ClassNotFoundException; must be caught or declared to be thrown
throw new ClassNotFoundException();
1 error
```

**Lets try to Handle the Exception using "throws":**

"throws" tells that, this method MIGHT throw this exception (or might not), so pls caller you handle it appropriately.

```java
public class Main {
    public static void main(String[] args) {
        method1();
    }
    public static void method1() throws ClassNotFoundException{
        throw new ClassNotFoundException();
    }
}
```

**Caller class need to then take care:**

```java
public class Main {
    public static void main(String[] args) throws ClassNotFoundException{
        method1();
    }
    public static void method1() throws ClassNotFoundException{
        throw new ClassNotFoundException();
    }
}
```
