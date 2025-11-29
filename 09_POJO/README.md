# POJO Class

## What is a POJO?
- **POJO** stands for "Plain Old Java Object".
- Contains variables and its getter and setter methods.
- Class should be public.
- Public default constructor.
- No annotations should be used like `@Table`, `@Entity`, `@Id` etc.
- It should not extend any class or implement any interface.

---

## Example

```java
public class Student {
    int name;
    private int rollNumber;
    protected String address;

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
```

---

## Key Points
- **Public class with default public constructor**
- **Variables**
- **Getter & Setter Methods**

> Whenever any request comes to our system, i.e., component receives the data, it is advisable that we map the request object to a POJO which all other classes understand and use. If anything changes in future, we only have to make change to this POJO.
