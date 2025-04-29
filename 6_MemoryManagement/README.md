# Java Memory Management

Java manages memory in two main areas that the JVM manages:

## 1. Stack Memory
- Stores temporary variables and method-specific memory blocks
- Contains primitive data types
- Stores references to heap objects
- Contains different types of references:
  - Strong references
  - Weak references
  - Soft references
- Each thread has its own stack memory
- Variables within a scope are only visible within that scope
- Memory is released in LIFO (Last In, First Out) order
- When stack memory gets full, it throws `java.lang.StackOverflowError`

## 2. Heap Memory
- Stores objects and there is no specific order of memory allocation
- Garbage Collection is used to deallocate unreferenced objects from the heap
- Types of garbage collectors include:
  - Serial GC
  - Parallel GC (default)
  - CMS (Concurrent Mark Sweep)
- Heap memory is shared across all threads
- Garbage collection algorithms manage memory cleanup

## Example Code

```java
public class MemoryManagement {
    public static void main(String[] args[]) {
        int primitiveVariable1 = 10;  // primitive data type
        Person personObj1 = new Person();  // reference of object
        String stringLiteral1 = "abc";  // string literal
        MemoryManagement memObj = new MemoryManagement();
        memObj.memoryManagementTest(personObj1);
    }

    private void memoryManagementTest(Person personObj) {
        Person personObj2 = personObj;  // reference of object
        String stringLiteral2 = "xyz";  // string literal
        String stringObject = new String("xyz");  // string object
    }
}
```


![](/diagrams/memory.png)
### Memory Deallocation Process
1. When the `memoryManagementTest` method encounters its closing bracket, its scope ends. All variables within this method (personObj2, stringLiteral2, stringObject) are deleted from the stack in LIFO order.

2. Control then returns to the `main()` method. Once `main()` method completes execution (reaches its closing bracket), its portion of the stack (containing primitiveVariable1, personObj1, stringLiteral1, and memObj references) is also deallocated in LIFO order.

This demonstrates how Java efficiently manages memory by automatically deallocating stack memory as soon as the scope of methods ends.


