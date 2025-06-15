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

### Background System Threads

When a Java program starts and calls the main method, several background threads run alongside the main thread. These can be viewed using `Thread.getAllThreads()`.

#### Key System Threads

1. **VM Thread**
   - Runs on a separate thread from the main thread
   - Handles critical operations like garbage collection and thread dumps
   - Needs to "stop the world" during these operations to prevent faulty thread dumps

2. **Periodic Task Thread**
   - Manages timer events and interrupts
   - Schedules execution of periodic operations

3. **GC Threads**
   - Support various garbage collection activities
   - Handle memory cleanup operations

4. **Compiler Threads**
   - Responsible for runtime compilation of bytecode to native code
   - Optimize program execution

5. **Signal Dispatcher Thread**
   - Handles signals sent to the JVM process
   - Routes signals to appropriate JVM methods

### Thread Components

Each thread in the JVM consists of several key components:

1. **Program Counter (PC)**
   - Buffer storing the address of the next instruction to execute
   - Contains opcode when thread is not native

2. **Stack**
   - LIFO (Last In, First Out) data structure
   - Stores currently running and pending methods
   - Each method call creates a new frame
   - Frames are removed upon method completion
   - Each thread maintains its own stack

### Native Stack

Not all JVMs implementations supports native stacks. So while they are calling some native methods like thread.start() to start an OS thread, sockets communication etc they do create a native stack which invokes native C methods without any restriction of JVM through JNI (java native invocation). If JVM has been implemented using C-linkage model then it creates a native C stack and invocates accordingly. Native stack may call Java stack back and in that case thread will use Java stack only and will create frames if necessary in the Java stack only.

### Stack Restrictions

A stack can be a dynamic or fixed size, but when thread requires a stack more than available then StackOverFlow is thrown. And if in runtime, thread wants to create a new frame and there isn't any enough memory left, then it throws OutOfMemoryError.

### Frame

On every method invocation a new frame is created and added to the stack. The frame is removed once method terminates or throws an uncaught exception.

Each frame consists:
- Local variable array
- Return value
- Operand stack
- Reference to runtime constant pool for class of the current method

![](/diagrams/nativemethodstack.png)


