# Java Serialization

Serialization is basically converting a Java object into a static byte stream (sequence of bytes) & deserialization does the opposite. For ex: You can serialize an object and send that over to a database or network.

Serialization and deserialization are independent of the platforms, basically one can serialize on one platform and can deserialize on other. For a serialization and deserialization to occur a class has to implement a marker interface: Serializable

Generic Streams available are:- InputStream, OutputStream
Every other stream has to implement either one of them.

important method of ObjectOutputStream:-
Point to notice:-
public final void writeObject(Object o) throws IOException;
It accepts a serializable object and can write to a byte stream

similarly, for ObjectInputStream:-
public final Object readObject() throws IOException, ClassNotFoundException;



SerialVersionUID:- JVM associates a long number with each serializable object to identify if on deserialization class is same which was serialized means it has not been changed. JVM auto assign if we do not give it one based on class's attributes, access modifiers, class name etc

## Transient Fields

The `transient` keyword is used to mark fields that should not be serialized. When an object is serialized, transient fields are ignored and not included in the serialized data.

```java
public class Employee implements Serializable {
    private String name;
    private int id;
    private transient String password; // This field will not be serialized
    private transient int tempData;    // This field will not be serialized
}
```

When the object is deserialized, transient fields will have their default values (null for objects, 0 for primitives, false for boolean).

## Simple Example

```java
import java.io.*;

// Serializable class
class Student implements Serializable {
    private String name;
    private int rollNo;
    
    public Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }
    
    @Override
    public String toString() {
        return "Student{name='" + name + "', rollNo=" + rollNo + "}";
    }
}

// Main class to demonstrate serialization and deserialization
public class SerializationDemo {
    public static void main(String[] args) {
        Student student = new Student("John", 101);
        
        // Serialization
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.ser"))) {
            oos.writeObject(student);
            System.out.println("Object serialized successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Deserialization
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.ser"))) {
            Student deserializedStudent = (Student) ois.readObject();
            System.out.println("Object deserialized: " + deserializedStudent);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}d
```


