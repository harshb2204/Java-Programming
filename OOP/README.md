# OOPS 

## OOPS mean Object-Oriented Programming
Here, Object means real-world entities like Car, Bike, ATM, etc.

## Procedural Programming vs OOPS

| S.No | Procedural Programming                          | OOPS                                      |
|------|------------------------------------------------|-------------------------------------------|
| 1    | Program is divided into parts called functions | Program is divided into Objects           |
| 2    | Does not provide proper way to hide data, gives importance to function and data moves freely. | Objects provide data hiding, gives importance to data. |
| 3    | Overloading is not possible                     | Overloading is possible                   |
| 4    | Inheritance is not possible                     | Inheritance is possible                   |
| 5    | Code reusability does not present               | Code reusability is present               |
| 6    | Examples: Pascal, C, FORTRAN etc.              | Examples: Java, C#, Python, C++          |

## Object and Classes

- Object has 2 things:
  - Properties or State
  - Behavior or Function

### Example:
- **Dog** is an Object because it has:
  - Properties like: Age, Breed, Color etc.
  - Behaviors like: Bark, Sleep, Eat etc.

- **Car** is an Object because it has:
  - Properties like: Color, Type, Brand, Weight etc.
  - Behaviors like: Apply Break, Drive, Increase Speed etc.

## Classes

- To create an Object, a Class is required.
- So, Class provides the template or blueprint from which Objects can be created.
- From one Class, we can create multiple Objects.
- To create a class, use the keyword `class`:

```java
class Dog {
// Properties
String name;
int age;
String breed;
// Constructor
Dog(String name, int age, String breed) {
this.name = name;
this.age = age;
this.breed = breed;
}
// Behavior: Bark
String bark() {
return name + " says Woof!";
}
}
```
```java
public class Main {
public static void main(String[] args) {
// Creating objects of the Dog class
Dog dog1 = new Dog("Buddy", 3, "Golden Retriever");
Dog dog2 = new Dog("Max", 5, "Bulldog");
// Accessing properties and behaviors
System.out.println(dog1.bark()); // Output: Buddy says Woof!
System.out.println(dog2.name + " is a " + dog2.breed + " and is " + dog2.age + " years old.");
}
}
```
