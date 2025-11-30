## What is ThreadPool:

- It's a collection of threads (aka workers), which are available to perform the submitted tasks.
- Once task completed, worker thread get back to Thread Pool and wait for new task to assigned.
- Means threads can be reused.

![](/diagrams/threadpool.png)

## What's the Advantage of Thread Pool?

1. **Thread Creation time can be saved:**
   - When each thread created, space is allocated to it (stack, heap, program counter etc..) and this takes time.
   - With thread, this can be avoided by reusing the thread.

2. **Overhead of managing the Thread lifecycle can be removed:**
   - Thread has different state like Running, Waiting, terminate etc. And managing thread state includes complexity.
   - Thread pool abstract away this management.

3. **Increased the performance:**
   - More threads means, more **Context Switching** time, using control over thread creation, excess context switching can be avoided.

![](/diagrams/executor.png)