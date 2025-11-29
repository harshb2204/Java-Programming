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
        String stringLiteral1 = "24";  // string literal
        MemoryManagement memObj = new MemoryManagement();
        memObj.memoryManagementTest(personObj);
    }

    private void memoryManagementTest(Person personObj) {
        Person personObj2 = personObj;  // reference of object
        String stringLiteral2 = "24";  // string literal
        String stringObject = new String("24");  // string object
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


## ➔ Heap Memory
Heap memory is divided into two parts i.e. Young Generation and Old Generation. There is also one more part which is generally known as Non Heap (Metaspace). Before Java 7 it is called permgen but now it is not used & it is used as metaspace.

Now young generation is further divided into 3 parts named Eden, S0 (S zero) and S1. S zero & S1 are known as survivor space.
![](/diagrams/gc1.png)

## Object Creation and Eden Space
Now let's see when we create an object, what happens to it.

* Whenever a new object is created, it goes to Eden first. Let's say we've created 5 objects (o1, o2, o3, o4 & o5). They'll be created in Eden first.
* So now heap memory looks like this.
![](/diagrams/gc2.png)

So 5 objects are created inside Eden.

Now let's say Garbage Collector runs & there is no reference to o2, o5 in the heap space. So now GC will use Mark & Sweep Algorithm i.e. in Mark, GC will mark the objects which no more have reference & then Sweep in which it'll do 2 things:

- First remove dereferenced objects (o2, o5) from the memory
- Move the rest of survivor objects into one of the survivor spaces i.e. S0 or S1, and adds age to the objects. So after GC runs, heap now looks like:
![](/diagrams/gc3.png)

Now GC has run once. This whole process is called minor GC as it happens very periodically & very fast.

Let's now create 2 more objects o6, o7. So heap now looks like:

![](/diagrams/gc4.png)

o6, o7 are now created in Eden. Now let's say the GC runs again & this time no reference is there for o4, o7. So now GC will:
- Mark o4, o7
- Delete o4, o7
- Move o1, o3, o6 (survivors) to S1 with corresponding ages.

Therefore, post this minor GC, the heap looks like:

![](/diagrams/gc5.png)

So at one time Eden would be completely free after the GC & one of the survivor spaces (S0 or S1) would be free and we put data alternatively in S0 & S1, along with the respective age.

Now let's create two more objects o8 & o9 & heap looks like:

![](/diagrams/gc6.png)

Now let's say I've set the threshold age to 3.

Therefore, now objects with age 3 need to be promoted. Promotion means that now the objects with age 3 will be moved to Old Generation.

Let's now run GC assuming that there are no more references to o3, o8, hence will be deleted. So GC will now:
- Mark o3, o8
- Delete o3, o8
- Move the survivor objects (o6, o9) to S0, and since o1 still has reference with age 3, it'll be promoted i.e. moved to old generation

How the heap looks like:
![](/diagrams/gc7.png)

In the old generation, the only difference is that here it is called major GC because the GC in Old Generation won't run too periodically. So in Old Generation, the GC runs less periodically as compared to young gen, and the objects in old gen are kind of big objects that are used too frequently & these might have a lot of references pointing to them.

Now let's come to metaspace. What will it store?
The metaspace contains:
- Class variables
- Class metadata (basically stores info about class from which objects can be created)
- Constants

* Prior to Java 8, there is a fixed space called permagen which used to store the data that is stored in metaspace. Since it was non-expandable, once it filled, we'd get out of memory error.
  But now from Java 8, metaspace is there which is different from heap & is expandable.

Garbage Collector Algorithms
* Mark & Sweep Algorithm (Already Discussed)
* Mark & Sweep with compact memory
  In this, once the GC runs, the remaining objects are put in a sequential memory block leaving another sequential block free to put objects.

Let's take this example:
![](/diagrams/gc8.png)

## Versions of GC

1) Serial GC
- In this, only one GC thread is working for both minor and major GC.
- Its disadvantage is that due to the single thread, GC will be slower, but since GC is expensive (because when GC work starts, all application threads pause), this is one of the disadvantages of serial.

2) Parallel GC
- In this, there are parallel threads running based on the CPU configuration. So GC works faster. So the application threads will be in paused state for lesser time.

3) Concurrent Mark & Sweep
- In this, the GC will try its best to run concurrently with application threads but there is no guarantee. In this, there is no memory compaction happens.

4) G1 Garbage Collection
- This is a better version of concurrent mark & sweep in which GC will try not to stop/pause the application thread and supports memory compaction as well.

* Currently Java 8 is using parallel GC
* In latest Java versions using CMS & G1, there is a high chance that pause time is minimal thus increasing throughput & decreasing latency.



