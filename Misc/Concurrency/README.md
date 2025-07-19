# Java Concurrency Guide

## What is Concurrency?

Concurrency is the ability of a program to execute multiple tasks simultaneously. It doesn't necessarily mean tasks run at the exact same time, but rather that they can be interleaved and make progress together.

## Concurrency vs Parallelism

### Concurrency
- Multiple tasks can be **in progress** at the same time
- Tasks may not run simultaneously (interleaved execution)
- Focus on **structure** and **design**

### Parallelism
- Multiple tasks **actually run** at the same time
- Requires multiple CPU cores
- Focus on **performance** and **speed**

## Threads in Java

A thread is the smallest unit of execution within a process. Java provides built-in support for multithreading.

### Creating Threads

**Method 1: Extending Thread**
- Create a class that extends the Thread class
- Override the run() method
- Call start() to begin execution

**Method 2: Implementing Runnable**
- Create a class that implements the Runnable interface
- Override the run() method
- Pass the Runnable to a Thread constructor

**Method 3: Lambda Expression (Modern)**
- Use lambda expressions for simple thread creation
- More concise and readable syntax

## Simple Multithreading Example in Java

```java
// Method 1: Extending Thread
class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("From MyThread: " + i);
        }
    }
}

// Method 2: Implementing Runnable
class MyRunnable implements Runnable {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("From MyRunnable: " + i);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        Thread t2 = new Thread(new MyRunnable());

        t1.start();
        t2.start();
    }
}
```

This code creates two threads: one by extending the Thread class and another by implementing the Runnable interface. Both threads print numbers from 0 to 4.

## Thread Scheduling

The JVM and operating system work together to schedule threads for execution.

### Thread States
1. **NEW** - Thread created but not started
2. **RUNNABLE** - Thread is executing or ready to execute
3. **BLOCKED** - Thread waiting for a monitor lock
4. **WAITING** - Thread waiting indefinitely for another thread
5. **TIMED_WAITING** - Thread waiting for a specified time
6. **TERMINATED** - Thread has completed execution

### Scheduling Algorithms
- **Preemptive Scheduling** - OS can interrupt running threads
- **Time Slicing** - Each thread gets a time quantum
- **Priority-based** - Higher priority threads get preference

## Context Switching

Context switching is the process of saving and loading thread/process state when switching between them.

### Thread Context Switch
- **Faster** (microseconds)
- **Less overhead**
- **Shared memory space**
- **Smaller context** (registers, stack pointer, program counter)

### Process Context Switch
- **Slower** (milliseconds)
- **More overhead**
- **Separate memory spaces**
- **Larger context** (memory management, file descriptors, etc.)

**Why Context Switching Happens:**
1. **Time quantum expired**
2. **Higher priority thread becomes ready**
3. **Thread voluntarily yields** (sleep, wait, join)
4. **Thread blocks** (I/O, synchronization)

## Multithreading vs Multiprocessing

### Multithreading
- **Single process** with multiple threads
- **Shared memory** space
- **Faster communication** between threads
- **Less overhead** for creation/context switching
- **Risk of race conditions**

### Multiprocessing
- **Multiple processes**
- **Separate memory** spaces
- **Slower communication** (IPC required)
- **More overhead** for creation/context switching
- **Better isolation** and fault tolerance

---

## Shared Resources

**Concept:** In a multithreaded environment, multiple threads often need to access and modify the same data or objects. These shared data structures or objects are known as shared resources.

**Problem:** Uncontrolled access to shared resources by multiple threads can lead to race conditions, where the final outcome depends on the unpredictable interleaving of thread execution. This can result in corrupted data, incorrect computations, or system crashes.

## Thread Safety

**Concept:** Thread safety is the property of an object or code that ensures it behaves correctly even when accessed by multiple threads concurrently.

**Goal:** To prevent race conditions and ensure data consistency and correctness in concurrent environments.

## Synchronization (Mutual Exclusion)

**Concept:** Synchronization is a mechanism used to control access to shared resources, ensuring that only one thread can execute a critical section of code at a time. This is also known as mutual exclusion.

**Mechanism (in Java):**

- **Intrinsic Locks (Monitor Locks):** Every Java object has an associated intrinsic lock (also called a monitor).
- **synchronized Keyword:**
    - When a thread enters a synchronized block or method on a particular object, it must first acquire that object's intrinsic lock.
    - If another thread already holds the lock, the current thread blocks (waits) until the lock is released.
    - Once a thread exits the synchronized block (normally or via an exception), it releases the lock.
    - This guarantees that only one thread can execute the code protected by that specific lock at any given time, thus making the critical section thread-safe.

**Critical Section:** A block of code that accesses or modifies a shared resource and must be executed by only one thread at a time to maintain data integrity.

## Inter-Thread Communication and Coordination (Wait/Notify)

**Concept:** Beyond just preventing concurrent access, threads often need to communicate and coordinate their activities. One thread might need to wait for another thread to complete a task or for a certain condition to become true before it can proceed.

**Mechanism (in Java):** The `java.lang.Object` class provides methods for this purpose:

- **wait():**
    - **Purpose:** A thread calls `wait()` when it determines that it cannot proceed until a certain condition is met.
    - **Action:** When `wait()` is called, the thread releases the intrinsic lock it holds on the object and transitions into a WAITING state. It will remain in this state until it is "notified" by another thread or interrupted.
    - **Requirement:** Must be called from within a synchronized block on the object whose lock is to be released.
- **notify():**
    - **Purpose:** Wakes up a single arbitrary thread that is waiting on the intrinsic lock of the object on which `notify()` is called.
    - **Action:** The awakened thread then competes with other threads to re-acquire the lock.
- **notifyAll():**
    - **Purpose:** Wakes up all threads that are currently waiting on the intrinsic lock of the object on which `notifyAll()` is called.
    - **Action:** Similar to `notify()`, all awakened threads then contend for the lock. `notifyAll()` is generally preferred over `notify()` in most complex scenarios to avoid potential deadlocks or starvation where the "wrong" thread might be woken up.

---

## Semaphores

Semaphores are a fundamental synchronization primitive in concurrent programming. They are essentially integer variables that are used to control access to shared resources by multiple threads (or processes) in a way that prevents race conditions and ensures proper coordination.

Think of a semaphore as a counter that represents the number of available "permits" or "units" of a particular resource.

### Core Concepts

- **Integer Value (Permits):** A semaphore maintains an internal integer value, which is initialized to the number of resources it can manage. This value represents the current number of available permits.

- **Counting Semaphore:** Can have any non-negative integer value. Used to control access to a resource that has multiple identical instances (e.g., a pool of 10 database connections, 5 printer slots).

- **Binary Semaphore:** A special case where the value can only be 0 or 1. It's essentially a mutex (mutual exclusion lock) and is used to ensure only one thread can access a resource at a time.

### Key Operations

- **acquire():**
    - A thread calls `acquire()` to request a permit from the semaphore.
    - If the semaphore's value is greater than zero, it is decremented and the thread proceeds.
    - If the value is zero, the thread blocks (waits) until a permit becomes available (i.e., until another thread releases a permit).

- **release():**
    - A thread calls `release()` to return a permit to the semaphore.
    - This increments the semaphore's value and may wake up a waiting thread, allowing it to acquire a permit and proceed.

Semaphores are widely used for controlling access to pools of resources, limiting concurrency, and coordinating activities between threads in concurrent applications.


