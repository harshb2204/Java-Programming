# Enum Class

---

**ENUM Class:**

- It has a collection of CONSTANTS (variables which values can not be changed)
- Its CONSTANTS are static and final implicitly (we do not have to write it).
- It can not extend any class, as it internally extends java.lang.Enum class
- It can implement interfaces.
- It can have variables, constructor, methods.
- It can not be initiated (as its constructor will be private only, even you give default, in bytecode it make it private)
- No other class can extend Enum class
- It can have abstract method, and all the constant should implement that abstract method.

---

## Normal Enum Class

```java
public enum EnumSample{
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;
}
```

*Internally for any constant we define in enum, a value is assigned starting from 0. So MONDAY will have 0, TUESDAY 1 and so on. It will happen only if we don't define custom values.*

---

### Example Usage

```java
public class Main {
    public static void main(String args[]) {
        // Common functions which is used
        // - values()
        // - ordinal()
        // - valueOf()
        // - name()

        // 1. usage of values() and ordinal()
        for (EnumSample sample : EnumSample.values()) {
            System.out.println(sample.ordinal());
        }

        // 2. usage of valueOf() and name()
        EnumSample enumVariable = EnumSample.valueOf("FRIDAY");
        System.out.println(enumVariable.name());
    }
}
```

Output:
```
0
1
2
3
4
5
6
FRIDAY
```

---

## Enum With Custom Values

In Java, enums can have custom values and methods. Each variable defined in the enum class will be applicable for every constant in the class. In a way, each constant is an object of the enum class having defined variables.

We also need to define a parameterized constructor, which will be invoked for every constant. To define a method for the whole class (enum), we need to make it static; otherwise, it'll be applicable for all the constants.

```java
public enum EnumWithCustomValues {
    MONDAY(101, "1st Day of the Week"),
    TUESDAY(102, "2nd Day of the Week"),
    WEDNESDAY(103, "3rd Day of the Week"),
    THURSDAY(104, "4th Day of the Week"),
    FRIDAY(105, "5th Day of the Week"),
    SATURDAY(106, "6th Day of the Week"),
    SUNDAY(107, "7th Day of the Week");

    private int value;
    private String comment;

    EnumWithCustomValues(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public static EnumWithCustomValues getEnumFromValue(int value) {
        for (EnumWithCustomValues enumWithCustomValue : EnumWithCustomValues.values()) {
            if (enumWithCustomValue.value == value)
                return enumWithCustomValue;
        }
        return null;
    }
}
```

**Key Points:**
- Each variable defined in the enum class is applicable to every constant.
- A parameterized constructor is required for custom values.
- Static methods are used for operations on the whole enum class.

---

## Example Usage of EnumWithCustomValues

```java
public class EnumDemo {
    public static void main(String[] args) {
        EnumWithCustomValues enumWithCustomValue = EnumWithCustomValues.getEnumFromValue(107);
        EnumWithCustomValues enumWithCustomValue1 = EnumWithCustomValues.TUESDAY;
        System.out.println(enumWithCustomValue.getComment());
        System.out.println(enumWithCustomValue1.getComment());
        System.out.println(EnumWithCustomValues.MONDAY.getValue());
        System.out.println(EnumWithCustomValues.MONDAY.getComment());
    }
}
```

Output:
```
7th Day Of the Week
2nd Day Of the Week
101
1st Day Of the Week
```

---

## Method Override by Constant

Enums in Java allow you to override methods for specific constants. If a method is not static, it is applicable for all constants, but you can override it for a particular constant.

```java
public enum EnumMethodOverrideByConstant {
    MONDAY {
        @Override
        public void dummyMethod() {
            System.out.println("Monday Dummy Method");
        }
    },
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    public void dummyMethod() {
        System.out.println("Default Dummy Method");
    }
}
```

**Explanation:**
- Since `dummyMethod` is not static, it is applicable for all constants.
- We can override the method for a particular constant (e.g., `MONDAY`).

Example usage:

```java
public class EnumDemo {
    public static void main(String[] args) {
        EnumMethodOverrideByConstant enumMethodOverrideByConstant = EnumMethodOverrideByConstant.MONDAY;
        EnumMethodOverrideByConstant enumMethodOverrideByConstant1 = EnumMethodOverrideByConstant.TUESDAY;
        enumMethodOverrideByConstant.dummyMethod();
        enumMethodOverrideByConstant1.dummyMethod();
    }
}
```

Output:
```
Monday Dummy Method
Default Dummy Method
```

Since we've overridden `dummyMethod` for `MONDAY`, we get different output.

---

## Enum With Abstract Method

Enums can have abstract methods, which must be implemented by all constants.

```java
public enum EnumWithAbstractMethod {
    MONDAY {
        @Override
        public void dummyMethod() {
            System.out.println("In Monday Dummy Method");
        }
    },
    TUESDAY {
        @Override
        public void dummyMethod() {
            System.out.println("In Tuesday Dummy Method");
        }
    },
    WEDNESDAY {
        @Override
        public void dummyMethod() {
            System.out.println("In Wednesday Dummy Method");
        }
    };

    public abstract void dummyMethod();
}
```

Example usage:

```java
public class EnumDemo {
    public static void main(String[] args) {
        EnumWithAbstractMethod enumWithAbstractMethod = EnumWithAbstractMethod.MONDAY;
        EnumWithAbstractMethod enumWithAbstractMethod1 = EnumWithAbstractMethod.TUESDAY;
        enumWithAbstractMethod.dummyMethod();
        enumWithAbstractMethod1.dummyMethod();
    }
}
```

Output:
```
In Monday Dummy Method
In Tuesday Dummy Method
```

So, enums can have an abstract method which in turn will be implemented by all the constants.

---

## Enum Implements Interface

Enums can also implement interfaces in Java. This allows you to define methods in an interface and provide their implementation in the enum, making them available for every constant.

```java
public interface MyInterface {
    public String toLowerCase();
}
```

```java
public enum EnumImplementInterface implements MyInterface {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    @Override
    public String toLowerCase() {
        return this.name().toLowerCase();
    }
}
```

So here, the enum implements an interface and we can call `toLowerCase()` for every constant.

Example usage:

```java
public class EnumDemo {
    public static void main(String[] args) {
        EnumImplementInterface enumImplementInterface = EnumImplementInterface.MONDAY;
        System.out.println(enumImplementInterface.toLowerCase());
        EnumImplementInterface enumImplementInterface1 = EnumImplementInterface.TUESDAY;
        System.out.println(enumImplementInterface1.toLowerCase());
    }
}
```

Output:
```
monday
tuesday
```

---

## Benefit of Enum Class vs Static Final Constants

What is the benefit of Enum class when we can create constants using `static` and `final`? Let's understand this with an example:

**Using static final constants:**
```java
public class WeekConstants {
    public static final int MONDAY = 0;
    public static final int TUESDAY = 1;
    public static final int WEDNESDAY = 2;
    public static final int THURSDAY = 3;
    public static final int FRIDAY = 4;
    public static final int SATURDAY = 5;
    public static final int SUNDAY = 6;
}
```

**Using Enum:**
```java
public enum EnumSample {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;
}
```

**Example usage:**
```java
public class Main {
    public static void main(String[] args) {
        // Using constant class
        isWeekend(2); // WEDNESDAY, so it will return false
        isWeekend(6); // SUNDAY, so it will return true
        isWeekend(100); // This value is not expected, but still we are able to send this in parameter

        // Using enum - better readability and full control on what value we can pass in parameter
        isWeekend(EnumSample.WEDNESDAY); // return false
        isWeekend(EnumSample.SUNDAY); // return true
    }

    public static boolean isWeekend(int day) {
        if (WeekConstants.SATURDAY == day || WeekConstants.SUNDAY == day)
            return true;
        return false;
    }

    public static boolean isWeekend(EnumSample day) {
        if (EnumSample.SATURDAY == day || EnumSample.SUNDAY == day)
            return true;
        return false;
    }
}
```

**Advantages of Enum:**
- It has better readability.
- We have control on what value we can pass in parameter.

---

## Final Class

A final class is a class which cannot be inherited.

**For example:**
```java
public final class TestClass {
}
```

Trying to extend a final class will result in a compilation error:
```java
public class MyOtherClass extends TestClass {
    // Error: Cannot inherit from final 'TestClass'
}
```
