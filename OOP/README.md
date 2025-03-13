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
## 1st Pillar of OOPS – Data Abstraction

- It hides the internal implementation and shows only essential functionality to the user.
- It can be achieved through Interface and abstract classes.

### Example:
- **Car** – we are only shown the **BRAKE** pedal, and if we press it, the car's speed will reduce. But **HOW**? That is **ABSTRACTED** to us.
- **Cellphone** – How a call is made is **abstracted** to us.

### Advantages of Abstraction:
- Increases security and confidentiality.
- Simplifies client code.


## 2nd Pillar of OOPS – Data Encapsulation

- Encapsulation bundles the data and the code working on that data in a single unit.
- Also known as **DATA-HIDING**.

### Steps to achieve encapsulation:
- Declare variables of a class as **private**.
- Provide **public getters and setters** to modify and view the value of the variables.

### Advantages of Encapsulation:
- Loosely coupled code.
- Better access control and security.
- Classes get control over the ownership of the data. Other classes have to go through the getters and setter to access them.

## 3rd Pillar of OOPS – Inheritance

### Definition:
- Capability of a class to inherit properties from its parent class.
- It can inherit both functions and variables, so we do not have to write them again in child classes.
- Can be achieved using the `extends` keyword or through an interface.

### Types of Inheritance:
- **Single Inheritance**
- **Multilevel Inheritance**
- **Hierarchical Inheritance**
- **Multiple Inheritance** – Through interface, we can resolve the diamond problem.

### Advantages of Inheritance:
- **Code reusability**
- **We can achieve Polymorphism using Inheritance.**

#### Why multiple inheritance is not allowed?
- If we have a class C extending class A and class B, then we create
an object of class C and call a method which exists in both A and B, it creates confusion 
to call which method. A and B can be made interfaces to resolve this problem(diamond problem).
As interfaces have only method definitions the class has to implement the method accordingly





