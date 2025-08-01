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
## Why we have 2 ways to create a thread?
- A class can implement more than one interface but a class can extend only one class.

# Thread LifeCycle

![](/diagrams/threadlifecycle.png)

## Thread States

| Lifecycle State | Description |
|----------------|-------------|
| **New** | • Thread has been created but not started.<br>• It's just an Object in memory. |
| **Runnable** | • Thread is ready to run.<br>• Waiting for CPU time. |
| **Running** | • When thread starts executing its code. |
| **Blocked** | • Different scenarios where a runnable thread goes into the Blocking state:<br>  - **I/O:** like reading from a file or database.<br>  - **Lock acquired:** if a thread wants to lock on a resource which is locked by another thread, it has to wait.<br>• Releases all the MONITOR LOCKS. |
| **Waiting** | • Thread goes into this state when we call the `wait()` method, makes it non-runnable.<br>• It goes back to runnable, once we call `notify()` or `notifyAll()` method.<br>• Releases all the MONITOR LOCKS. |
| **Timed Waiting** | • Thread waits for a specific period of time and comes back to runnable state, after specific conditions are met.<br>• Examples: `sleep()`, `join()`.<br>• Do not Releases any MONITOR LOCKS. |
| **Terminated** | • Life of thread is completed, it cannot be started back again. |

---

## Monitor Lock

**MONITOR LOCK:**
It helps to make sure that only 1 thread goes inside the particular section of code (a synchronized block or method).

```java
public class MonitorLockExample {

    public synchronized void task1() {
        //do something
        try {
            System.out.println("inside task1");
            Thread.sleep(10000);
        } catch (Exception e) {
            //exception handling here
        }
    }

    public void task2() {
        System.out.println("task2, but before synchronized");
        synchronized (this) {
            System.out.println("task2, inside synchronized");
        }
    }

    public void task3() {
        System.out.println("task3");
    }

    public static void main(String args[]){
        MonitorLockExample obj = new MonitorLockExample();

        Thread t1 = new Thread(() -> {obj.task1();});
        Thread t2= new Thread(() -> { obj.task2();});
        Thread t3 = new Thread(() -> {obj.task3();});

        t1.start();
        t2.start();
        t3.start();
    }
}
```

---

## Producer-Consumer Pattern Example

Now let's see an Example:

### SharedResource Class

```java
public class SharedResource {
    boolean itemAvailable = false;

    public synchronized void addItem() {
        itemAvailable = true;
        System.out.println("Item added by: " + Thread.currentThread().getName() + " and invoking all threads which are waiting");
        notifyAll();
    }

    public synchronized void consumeItem() {
        System.out.println("ConsumeItem method invoked by: " + Thread.currentThread().getName());
        while (itemAvailable) {
            try {
                System.out.println("Thread " + Thread.currentThread().getName() + " is waiting now");
                wait(); // to avoid 'spurious wake-up', sometimes because of system noise.
            } catch (Exception e) {
                //exception handling here
            }
        }
        System.out.println("Item Consumed by: " + Thread.currentThread().getName());
        itemAvailable = false;
    }
}
```

### ProduceTask Class

```java
public class ProduceTask implements Runnable {
    SharedResource sharedResource;

    ProduceTask(SharedResource resource) {
        this.sharedResource = resource;
    }

    @Override
    public void run() {
        System.out.println("Producer thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            //exception handling here
        }
        sharedResource.addItem();
    }
}
```

### ConsumeTask Class

```java
public class ConsumeTask implements Runnable {
    SharedResource sharedResource;

    ConsumeTask(SharedResource resource) {
        this.sharedResource = resource;
    }

    @Override
    public void run() {
        System.out.println("Consumer thread: " + Thread.currentThread().getName());
        sharedResource.consumeItem();
    }
}
```

### Main Class

```java
public class Main {
    public static void main(String args[]){
        System.out.println("Main method start");

        SharedResource sharedResource = new SharedResource();

        // producer thread
        Thread producerThread = new Thread(new ProduceTask(sharedResource));
        // consumer thread
        Thread consumerThread = new Thread(new ConsumeTask(sharedResource));

        //thread is in "RUNNABLE state"
        producerThread.start();
        consumerThread.start();

        System.out.println("Main method end");
    }
}
```

### Alternative: Using Lambda Expression

Or use lambda expression, instead of creating ProduceTask and ConsumeTask class:

```java
Thread consumerThread = new Thread(() -> {
    System.out.println("Consumer Thread: " + Thread.currentThread().getName());
    sharedResource.consumeItem();
});
```
