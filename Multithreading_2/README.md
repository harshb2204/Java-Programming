Concurrency can be achieved using:

Lock based Mechanism
- Synchronized
- Reentrant
- Stamped
- ReadWrite
- Semaphores

Lock Free Mechanism
- CAS Operation (Compare-and-Swap)
  - AtomicInteger
  - AtomicBoolean
  - AtomicLong
  - AtomicReference





![](/diagrams/optimisticconcurrencycontrol.png)
 **T1** 
- Reads rv = 1
- Updates → success
- Version becomes 2

T2
- Uses old rv = 1
- Attempts update
- Finds rv ≠ 1
- UPDATE affects 0 rows → OptimisticLockException


## Lock Free Mechanism

It uses CAS (compare and Swap) technique:
- It's a Low level operation. (CPU operation)
- Its Atomic.
- And all modern Processor supports it.

It involves 3 main parameters:
- Memory location: location where variable is stored. From here the variable data is loaded
- Expected Value: value which should be present at the memory. Compare the data stored in memory and the expected value. If not matching something has been changed
  - ABA problem is solved using version or timestamp.
- New Value: value to be written to memory, if the current value matches the expected value.
- Similar to optimistic locking (row version is checked)

### ABA problem
- Memory m1 has x value as 10. 
```
CAS(m1, 10, 13) // mem, expected value, new value
{
    read m1
    compare m1 value with expected 
    update if matched
     
}
```
t1 wanted to run the above, but some operation changed it to 12 and after it some operation again changed it back to 10, CAS will change it to 13 but the expected 10 is the og one which was changed.
resolved with version number in memory or timestamp.

## Atomic Variables

What ATOMIC means:
- It means Single or "all or nothing"

```java
public class Main {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        for(int i=0; i<400; i++) {
            resource.increment();
        }
        System.out.println(resource.get());
    }
}
```

```java
public class SharedResource {
    int counter;

    public void increment() {
        counter++;
    }

    public int get() {
        return counter;
    }
}
```

Output: 400
This is not an atomic operation.
count++ includes 3 steps:-
- Load count value
- increment it by 1
- assign back 

Say we have two threads T1 and T2 and count = 0. Both run parellelly, both increment by 1 and assign back,
now the count value is 1 but it should be 2. Not thread safe.

```java
public class Main {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        Thread t1 = new Thread(() -> {
            for(int i=0; i<200; i++) {
                resource.increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for(int i=0; i<200; i++) {
                resource.increment();
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(resource.get());
    }
}
```

```java
public class SharedResource {
    int counter;

    public void increment() {
        counter++;
    }

    public int get() {
        return counter;
    }
}
```

Output: 371

## 2 solutions:

1. Using lock like synchronized
2. Using lock free operation like AtomicInteger

### 1. Using lock like synchronized

```java
public class SharedResource {
    int counter;

    public synchronized void increment() {
        counter++;
    }

    public int get() {
        return counter;
    }
}
```

### 2. Using lock free operation like AtomicInteger

```java
public class SharedResource {
    private AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        counter.incrementAndGet();
    }

    public int get() {
        return counter.get();
    }
}
``` 
![](/diagrams/CAS2.png)

- Suppose now we have two threads and the incrementAndGet() method is called, both will read 0 from memory
- Case 1: T1 wins the CAS T1: CAS( memory = 0, expected = 0, new = 1 ) → SUCCESS
- Memory is now updated: counter = 1
- Case 2: T2 attempts CAS but fails T2: CAS( memory = 1, expected = 0, new = 1 ) → FAILS
- Because the expected value (=0) does NOT match the actual value (=1). Reads current = 1
- So T2 retries:CAS( memory = 1, expected = 1, new = 2 ) → SUCCESS!
- Both threads execute the first iteration of the do-block at the same time
- But only one thread succeeds inside CAS, and the other one fails. (CAS is atomic at CPU level → only one thread can succeed.)



## Volatile

![](/diagrams/volatile.png)
- Each cpu core has their individual l1 cache. Suppose T1 and T2 are running on core 1 and core 2 respectively and working on x= 10. t1 does x++ and it puts it into L1 cache. t2 reads x checks l1, l2 cache then checks memory where x is still 10 thus it will read 10. Periodically l1 cache sync up with each other, but the sync might not have happened here. 
- Volatile makes sure that read and write should happen from memory. 
- Not thread safe. Doesnt guarantee concurrency. 