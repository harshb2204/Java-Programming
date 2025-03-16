# Introduction to Java  

Java is a **platform-independent**, **object-oriented programming (OOP)** language known for its **portability**—Write Once, Run Anywhere (WORA).  

## Key Features of Java  
- **Platform-Independent**: Java programs can run on any system with a Java Virtual Machine (JVM).  
- **Object-Oriented**: Follows OOP principles such as **Encapsulation, Inheritance, Polymorphism, and Abstraction**.  
- **Robust & Secure**: Provides features like garbage collection, exception handling, and memory management.  
- **Multi-threaded**: Supports concurrent execution of multiple tasks.  
- **High Performance**: Uses Just-In-Time (JIT) compilation for optimized execution.  

---

## Java Architecture: 3 Main Components  

![Java Architecture](/diagrams/jdkjrejvm.png)  

###  **JVM (Java Virtual Machine)**  
- An **abstract machine** that provides the runtime environment to execute Java bytecode.  
- **Platform-dependent**, meaning different JVMs exist for different operating systems.  
- Includes the **JIT (Just-In-Time) compiler**, which converts bytecode into native machine code for faster execution.  
- Ensures **portability** by running the same bytecode across different platforms.  

> **Compilation Process:**  
> - Java source code (`.java`) is compiled using `javac` into **bytecode (`.class`)**.  
> - The **JVM** takes this bytecode and converts it into **machine code** at runtime.  


![](/diagrams/javacode.png)
#### 🖥 Compile a Java Program:  
```sh
javac filename.java  # Compiles Java source code into bytecode
java filename        # Executes the compiled Java program
```

---

###  **JRE (Java Runtime Environment)**  
- A package that provides the **runtime environment** for Java applications.  
- **Includes JVM** + essential **class libraries** and runtime dependencies.  
- Used for **running Java applications**, but does **not** include development tools like a compiler or debugger.  
- If a Java program uses external libraries, the JRE ensures they are **resolved and executed correctly**.  

> **Key Limitation:**  
> - The JRE **cannot be used for development**—only for running pre-compiled Java programs.

---

### **JDK (Java Development Kit)**  
- A full-featured **software development kit (SDK)** for Java programming.  
- **Includes JRE + additional tools** required for Java development:  
  - **Java Compiler (`javac`)**: Converts source code into bytecode.  
  - **Debugger**: Helps in troubleshooting and debugging Java programs.  
  - **Development Tools**: Libraries, APIs, and utilities for Java development.  

> **JDK = JRE + Compiler + Debugger + Development Tools**

---

##  Platform Independence in Java  
While **JVM, JRE, and JDK are platform-dependent**, the **Java bytecode is platform-independent**.  
- Java achieves **portability** by using **bytecode**, which can run on any system with a compatible JVM.  

> **Example:**  
> - A `.class` file compiled on Windows can run on Linux or macOS **without modification**, as long as the JVM is installed.  

---

 

| Component | Purpose | Contains | Platform Dependency |
|-----------|---------|----------|---------------------|
| **JVM** | Executes Java bytecode | JIT Compiler, Garbage Collector | Platform-Dependent |
| **JRE** | Provides runtime environment | JVM + Standard Libraries | Platform-Dependent |
| **JDK** | Provides development tools | JRE + Compiler + Debugger | Platform-Dependent |


# Java Editions Overview

## Java Standard Edition (JSE)
- Core Java features and libraries.

## Java Enterprise Edition (JEE)
- Built on JSE with additional APIs for enterprise applications.
- Includes APIs like Servlets, JSP, Transaction API, and Persistence API.
- Suitable for large-scale applications.

## Java Micro/Mobile Edition (JME)
- Specialized API for mobile and embedded applications.



## Why in java a single file can have only 1 public class?
- main is a entry point of our program
- it should be inside a public class
- public class name should be same as file name
- main method is static as it can be called by JVM using the class name
- say Employee.java has many classes, jvm is going to call main method
and it should be in a public class, if there are more than 1 public class
how will the jvm know where the main method is. Thus java put a restriction.
 