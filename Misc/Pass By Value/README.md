# Pass by Value in Java

Java uses **pass-by-value** exclusively for method argument passing. However, the behavior differs based on whether you're passing a **primitive type** or a **reference type (object)**.

---

##  What is Pass-by-Value?

**Pass-by-value** means that a **copy of the variable** is passed to the method. Any changes made inside the method affect only the **copy**, not the original variable.

---

##  Types of Variables in Java

1. **Primitive Types** – `int`, `char`, `float`, `boolean`, etc.
2. **Reference Types** – Objects, arrays, collections, custom classes, etc.

---

##  Passing Primitive Types

```java
public class Test {
    public static void changeValue(int x) {
        x = 10;
    }

    public static void main(String[] args) {
        int a = 5;
        changeValue(a);
        System.out.println(a);  // Output: 5
    }
}
```

### Explanation:

* A copy of `a` is passed.
* Changing `x` doesn't affect the original `a`.

 **Primitives are immutable inside methods.**

---

##  Passing Reference Types (Objects)

```java
class Person {
    String name;
}

public class Test {
    public static void changeName(Person p) {
        p.name = "Alice";
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.name = "Bob";
        changeName(person);
        System.out.println(person.name);  // Output: Alice
    }
}
```

### Explanation:

* A copy of the **reference** is passed.
* The reference still points to the same object.
* Modifying object content (`name`) affects the original.

 **You can change the object’s internal state.**

---

##  Reassigning the Reference (Not Allowed)

```java
public class Test {
    public static void changePerson(Person p) {
        p = new Person();       // New object assigned to local copy of reference
        p.name = "Charlie";
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.name = "Bob";
        changePerson(person);
        System.out.println(person.name);  // Output: Bob
    }
}
```

### Explanation:

* A copy of the reference is passed.
* Assigning `p` to a new object only changes the **local reference**.
* The original reference in `main()` still points to the original object.

 **You cannot reassign the original reference inside the method.**

---





---



>  Java is strictly **pass-by-value**, even for objects.
