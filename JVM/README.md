# Java Virtual Machine (JVM)

JVM stands for Java Virtual Machine. It is a subset of the resources from the host machine and has its own environment. The JVM loads, verifies, and executes Java's bytecode, manages memory, and supports dynamic class loading.

- **JVM** is responsible for running Java programs by converting bytecode into machine-specific code.
- It provides a runtime environment with its own memory management, including stack, heap, and non-heap memory areas.
- The JVM manages the execution of Java programs, including loading classes, verifying code, and executing instructions.
- It supports dynamic class loading and memory management, such as garbage collection.

## Threads in JVM

A thread is a small worker that carries out a set of executions in a process. Each thread has its own:
- Stack
- Program counter
- Buffer space
- Synchronization objects

In JVM, there is a direct mapping between a Java thread and a native Operating System Thread.

### JVM System Threads

The thread creation process involves:
1. Creating all components of Java thread (Synchronization objects, Program counter, Stack)
2. Creating a native thread
3. The OS schedules the threads on CPU
4. Once the native thread is ready to run, it calls the `run()` method of Java's thread
5. On completion:
   - Any uncaught exception gets caught
   - Native thread terminates
   - All resources from both native thread and Java's thread are released


