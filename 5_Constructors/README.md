# Constructors in Java

## What is a Constructor?
- A constructor is used to create an instance/initialize the instance variables
- It's similar to a method except for the following differences:
  - **Name**: Constructor name must be same as the class name
  - **Return Type**: Constructors do not have any return type
  - **Modifiers**: Constructor cannot be static, final, abstract, or synchronized

## Key Points
- The `new` keyword tells Java to call the constructor

## Common Questions

### Why must constructor name be same as class name?
Constructor name is always same as class name because it makes it easy to identify. Since there is no return type, Java implicitly adds the class as return type.

### Why do constructors not have return type?
There can be methods with same name and even class as return type, but they cannot be called constructors as they do not obey the rules of constructor (i.e., same name without return type).

### Why can't constructors be final?
Constructors are different from usual methods and cannot be inherited. Therefore, it doesn't make sense to make them final because final is used to prevent overriding. If constructors cannot be inherited, then there's no point in making them final.

### Why can't constructors be abstract?
Since for abstract method, the responsibility of implementation is of child class. But constructors can't even be inherited, so there's no point of making them abstract.

### Why can't constructors be static?
Since static methods can only access static variables & other static methods, it won't be able to initialize the instance variables. We also won't be able to use constructor chaining & call super().

### Can we define constructor in interface?
No, because we cannot create object so there's no point of constructor.

## Types of Constructors

### 1. Default Constructor
- When we do not define a constructor, Java internally provides a constructor which is known as default constructor
- Default constructor also set default values for all the instance variables
- It is added only when we do not define a constructor

### 2. No Argument Constructor
- A constructor that does not take any argument
- It is very similar to default constructor but we are defining it instead of Java

### 3. Parameterized Constructor
- It takes arguments and assigns the instance variables with these parameters
- We can initialize one or multiple instance variables using a parameterized constructor
- For the variables we don't provide any arguments, they'll be initialized with default values

### 4. Constructor Overload
- We can create multiple constructors with different parameters

### 5. Private Constructor
- We can create a private constructor & no one outside the class will be able to call this constructor
- This is used usually in Singleton design pattern
- To create an object of a class having private constructor, we can create another static method to create the object & then call that method using class name

## Constructor Chaining
It means that we can call one constructor in another constructor. This is done using `this()` & `super()`. To chain a constructor within the same class, `this()` is used. For example:

```java
class Calculation {
    Calculation() {
        this("raj", 203);
    }
    
    Calculation(String name, int empID) {
        this.name = name;
        this.empID = empID;
    }
}
```
