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
        Person personObj = new Person();  // reference of object
        String stringLiteral1 = "abc";  // string literal
        MemoryManagement memObj = new MemoryManagement();
        memObj.memoryManagementTest(personObj);
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

### Garbage Collection Process
After the stack is cleared and all references are deleted, the objects still remain in the heap memory. This is where the Garbage Collector's role becomes crucial. The Garbage Collector will detect and delete all the unreferenced objects from the heap.

The Garbage Collector runs periodically, and the JVM controls when to run the garbage collector. While you can request garbage collection using `System.gc()`, this doesn't guarantee that GC will run immediately. This is why Java is called automatic memory management.

The frequency of Garbage Collection running is directly proportional to how much of the heap memory is currently full. As the heap fills up, garbage collection runs more frequently to free up memory.

## Type of References

### Strong Reference
- It is when a variable in stack is referencing an object in heap memory.
- Till the time the reference exists, GC (Garbage Collector) won't be able to delete the object from the heap memory.

**For example:**
```java
Person pobj = new Person();
```
So here, `pobj` has a strong reference to a `Person` object in the heap memory. Till the time this reference exists, GC won't be able to delete the `Person` object from the heap.

### Weak Reference
- In weak reference, also the reference exists to an object in the heap, but as soon as GC runs, the object is deleted from heap memory even if some variable is referencing this object from the stack. The variable in the stack will get null if it tries to access the object post GC run.

**For example:**
```java
WeakReference<Person> weakRef = new WeakReference<Person>(new Person());
```

### Soft Reference
- It is a type of weak reference, but the difference is that in this case the object will be deleted only when there is a shortage of space in the heap.
- So GC is allowed to delete a soft reference, but it will keep the object if sufficient space is there in the heap.

### Reference can be changed by referencing a current object to a new variable

**Example:**
```java
Person obj1 = new Person(); // created new object
Person obj2 = new Person(); // created another object
obj1 = obj2; // now obj1 refers to obj2
```
Now `obj1` will have a reference to the object of `obj2` in heap. When GC runs, the earlier object which `obj1` was referring to will be deleted.


