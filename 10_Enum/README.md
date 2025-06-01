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
