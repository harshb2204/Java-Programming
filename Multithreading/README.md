![](/diagrams/process.png)
![](/diagrams/cpu.png)

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


Flow
- First generate byte code (javac main.java)
- Execute the bytecode
- Process will be created 
- JVM instance is allocated to a process

- JIT will start to compile the bytecode -> machine code 
- Create 3 threads, for each thread assign stack, register, counter
- Save machine into code segment 
- Counter is pointing to address in machine code where the threads have to start working
- Start execution of program
- Main thread will use the register, store the data and assign it to the CPU 
- OS schedules and manages these threads
- CPU will load the MC using the program counter in register
- It will start executing, suppose only 1 second is given to execute, then it has to context switch after 1 second
- All the result generated is stored in register of thread 
- Now different thread runs
- Intermediate result again stored in its thread
- The result from the thread register is again loaded into the CPU register and resumes execution
- When you have 2 CPU you can run parellely.

---

**Definition of Multithreading:**

- Allows a program to perform multiple tasks at the same time.
- Multiple threads share the same resource such as memory space but still can perform tasks independently.

**Benefits and Challenges of Multithreading:**

**Benefits :**
--------------
- Improved performance by task parallelism
- Responsiveness
- Resource sharing

**Challenges:**
----------------
- Concurrency issue like deadlock, data inconsistency etc.
- Synchronized overhead.
- Testing and Debugging is difficult.

---

## Thread Creation Ways

![](/diagrams/threadcreation.png)
![](/diagrams/threadcreation2.png)

---

## Implementing Multithreading with Runnable Interface

### Step 1: Create a Runnable Object

**Instructions:**
- Create a class that implements 'Runnable' interface.
- Implement the 'run()' method to tell the task which thread has to do.

```java
public class MultithreadingLearning implements Runnable {
    @Override
    public void run() {
        System.out.println("code executed by thread: " + Thread.currentThread().getName());
    }
}
```

### Step 2: Start the thread

**Instructions:**
- Create an instance of class that implement 'Runnable'.
- Pass the Runnable object to the Thread Constructor.
- Start the thread.

```java
public class Main {
    public static void main(String args[]) {
        System.out.println("Going inside main method: " + Thread.currentThread().getName());
        
        MultithreadingLearning runnableObj = new MultithreadingLearning();
        Thread thread = new Thread(runnableObj);
        thread.start();
        
        System.out.println("Finish main method: " + Thread.currentThread().getName());
    }
}
```

### Output:

```
Going inside main method: main
Finish main method: main
code executed by thread: Thread-0
```

The output demonstrates that the main method's execution can complete before the newly started thread's `run()` method finishes, illustrating the asynchronous nature of multithreading.




## Extending Thread Class
![](/diagrams/threadcreation3.png)

### Step 1: Create a Thread Subclass

**Instructions:**
- Create a class that extends 'Thread' class.
- Override the 'run()' method to tell the task which thread has to do.

```java
public class MultithreadingLearning extends Thread{
    @Override
    public void run() {
        System.out.println("code executed by thread: " + Thread.currentThread().getName());
    }
}
```

### Step 2: Initiate and Start the thread

**Instructions:**
- Create an instance of the subclass.
- Call the start() method to begin the execution.

```java
public class Main {
    public static void main(String args[]){
        System.out.println("Going inside main method: "+ Thread.currentThread().getName());
        MultithreadingLearning myThread = new MultithreadingLearning();
        myThread.start();
        System.out.println("Finish main method: " + Thread.currentThread().getName());
    }
}
```

### Output:

```
Going inside main method: main
Finish main method: main
code executed by thread: Thread-0
```
