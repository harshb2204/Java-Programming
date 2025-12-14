## Queue

![](/diagrams/queue.png)

* QUEUE is an Interface, child of Collection interface.
* Generally QUEUE follows FIFO approach, but there are exceptions like PriorityQueue
* Supports all the methods available in Collection + some other methods mentioned below:

## Methods Available in Queue Interface

| S.No. | Methods Available in Queue Interface | Usage |
|-------|--------------------------------------|-------|
| 1. | `add()` | Inserts the element into the queue. Returns `True` if insertion is successful and throws an `Exception` if insertion fails. Null element insertion is not allowed and will throw a `NullPointerException` (NPE). |
| 2. | `offer()` | Inserts the element into the queue. Returns `True` if insertion is successful and `False` if insertion fails. Null element insertion is not allowed and will throw a `NullPointerException` (NPE). |
| 3. | `poll()` | Retrieves and removes the head of the queue. Returns `null` if the Queue is Empty. |
| 4. | `remove()` | Retrieves and removes the head of the queue. Returns `Exception (NoSuchElementException)` if the Queue is Empty. |
| 5. | `peek()` | Retrieves the value present at the head of the queue but does not remove it. Returns `null` if the Queue is Empty. |
| 6. | `element()` | Retrieves the value present at the head of the queue but does not remove it. Returns an `Exception (NoSuchElementException)` if the Queue is Empty. |

## PriorityQueue

**Hierarchy:**
```
Iterable
  ↑
Collection
  ↑
Queue
  ↑
PriorityQueue
```

PriorityQueue implements the `Queue` interface, which extends the `Collection` interface, and `Collection` extends the `Iterable` interface.

### Overview

* Its of 2 types, Minimum Priority Queue and Maximum Priority Queue
* It is based on priority Heap (Min Heap and Max Heap).
* Elements are ordered according to either Natural Ordering (by default) or by Comparator provided during queue construction time.

### Min Priority Queue

#### Code Example

```java
public class MinPriorityQueueExample {
    public static void main(String args[]) {
        //min priority queue, used to solve problems of min heap.
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        minPQ.add(5);
        minPQ.add(2);
        minPQ.add(8);
        minPQ.add(1);
        
        //lets print all the values
        minPQ.forEach((Integer val) -> System.out.println(val));
        
        //remove top element from the PQ and print
        while(!minPQ.isEmpty())
        {
            int val = minPQ.poll();
            System.out.println("remove from top:" + val);
        }
    }
}
```

**Output:**
```
1
2
8
5
remove from top:1
remove from top:2
remove from top:5
remove from top:8
```

#### Tree Construction and Heapify Process

Let's trace through how the PriorityQueue builds the min-heap structure step by step:

**Step 1: Adding 5**
```
        5
```
Initial state: Only one element, already a valid min-heap.

**Step 2: Adding 2**
```
        5
       /
      2
```
After adding 2, we need to heapify up. Since 2 < 5, we swap:
```
        2
       /
      5
```
Now 2 is at the root (min-heap property maintained).

**Step 3: Adding 8**
```
        2
       / \
      5   8
```
Adding 8 as the right child. Since 8 > 2 and 8 > 5, no swap needed. Min-heap property is maintained.

**Step 4: Adding 1**
```
        2
       / \
      5   8
     /
    1
```
After adding 1, we need to heapify up. Since 1 < 5, we swap:
```
        2
       / \
      1   8
     /
    5
```
Now 1 < 2, so we swap again:
```
        1
       / \
      2   8
     /
    5
```
Final min-heap structure after all insertions.

**Internal Array Representation:**
The PriorityQueue internally stores elements in an array. The final structure would be:
```
Index: 0  1  2  3
Value: 1  2  8  5
```

**Why forEach() shows: 1, 2, 8, 5?**
The `forEach()` method iterates through the internal array representation, which gives: 1, 2, 8, 5. This is the level-order traversal of the heap tree structure.

**Why poll() removes in order: 1, 2, 5, 8?**
The `poll()` method:
1. Removes the root (minimum element = 1)
2. Moves the last element (5) to the root
3. Heapifies down to maintain min-heap property
4. Repeats until the queue is empty

**After poll() removes 1:**
```
Before:        1          After:        2
              / \                    / \
             2   8    →             5   8
            /                      
           5                       
```
5 moves to root, then heapifies down (5 > 2, so swap with smaller child 2).

**After poll() removes 2:**
```
Before:        2          After:        5
              / \                    /
             5   8    →             8
```
8 moves to root, then heapifies down (5 < 8, so 5 stays as root).

**After poll() removes 5:**
```
Before:        5          After:        8
              /                      
             8    →                   
```
Only 8 remains, which is removed next.

This is why `poll()` returns elements in ascending order (1, 2, 5, 8), maintaining the min-heap property throughout the removal process.