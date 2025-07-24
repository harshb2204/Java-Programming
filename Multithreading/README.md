![](/diagrams/process.png)

---

**Process:**

Process is an instance of a program that is getting executed.
It has its own resource like memory, thread etc. OS allocate these resources to process when its created.

- Compilation (*javac Test.java*): generates bytecode that can be executed by JVM.
- Execution (*java Test*): at this point, JVM starts the new Process, here *Test* is the class which has `public static void main(String args[])` method.

---

**Thread:**

- Thread is known as lightweight process
  OR
  Smallest sequence of instructions that are executed by CPU independently.
- And 1 process can have multiple threads.
- When a Process is created, it starts with 1 thread and that initial thread is known as 'main thread' and from that we can create multiple threads to perform tasks concurrently.

```java
public class MultithreadingLearning {
    public static void main(String args[]){
        System.out.println("Thread Name: " + Thread.currentThread().getName());
    }
}
```

Output: **Thread Name: main**

---

**How much memory does process gets?**

While creating the process `java MainClassName` command, a new JVM instance will get created and we can tell how much heap memory needs to be allocated.

    java -Xms256m -Xmx2g MainClassName

- **-Xms<size>**:
  This will set the initial heap size, above, I allocated 256MB.

- **-Xmx<size>**:
  Max heap size the process can get, above, I allocated 2GB. If it tries to allocate more memory, "OutOfMemoryError" will occur.


![](/diagrams/jvminstances.png)

---

**Code Segment:**
----------------------
- Contains the compiled **BYTECODE (_i.e machine code_)** of the Java Program.
- Its read only.
- All threads within the same process, share the same code segment.

**Data Segment:**
----------------------
- Contains the GLOBAL and STATIC variables.
- All threads within the same process, share the same data segment.
- Threads can read and modify the same data.
- Synchronization is required between multiple threads.

**Heap :**
---------
- Objects created at runtime using "new" keyword are allocated in the heap.
- Heap is shared among all the threads of the same process. (but NOT WITHIN PROCESS)
  _(let say in Process1, X8000 heap memory pointing to some location in physical memory, same X8000 heap memory point to different location for Process2)_
- Threads can read and modify the heap data.
- Synchronization is required between multiple threads.

**Stack:**
---------
- **Each thread has its own STACK.**
- **It manages, method calls, local variables.**

**Register:**
----------------------
- When JIT (Just-in time) compiles converts the Bytecode into native machine code, its uses register to optimize the generated machine code.
- Also helps in context switching.
- **Each thread has its own Register.**

**Counter:**
------------
- Also known as Program Counter, it points to the instruction which is getting executed.
- Increments its counter after successful execution of the instruction.

All these are managed by JVM.
