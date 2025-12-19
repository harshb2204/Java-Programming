# Java Collections Framework

Monday, 7 August 2023 8:16 PM

## What is Java Collections Framework?

* Added in Java version 1.2
* Collections is nothing but a group of Objects.
* Present in `java.util` package.
* Framework provide us the architecture to manage these "group of objects" i.e. add, update, delete, search etc.

## Why we need Java Collections Framework?

* Prior to JCF, we have Array, Vector, Hash tables.
* But problem with that is, there is no common interface, so its difficult to remember the methods for each.

## Code Example

```java
public class Main {
    public static void main(String[] args) {
        int arr[] = new int[4];
        //inset an element in an array
        arr[0] = 1;
        //get front element
        int val = arr[0];

        Vector<Integer> vector = new Vector();
        //insert an element in vector
        vector.add(1);
        //get element
        vector.get(0);
    }
}
```

## Java Collections Framework Hierarchy
![](/diagrams/collectionshierarchy.png)
![](/diagrams/collectionhierarchy1.png)

## Iterable

Iterable: Used to TRAVERSE the collection.

Below are the methods which are frequently used:

1. **`iterator()`**
   - **Available in:** Java1.5
   - **Usage:** It returns the Iterator object, which provides below methods to iterate the collection.
     - `hasNext()`: Returns true, if there are more elements in collection
     - `next()`: Returns the next element in the iteration
     - `remove()`: Removes the last element returned by iterator

2. **`forEach()`**
   - **Available in:** Java1.8
   - **Usage:** Iterate Collection using Lambda expression. Lambda expression is called for each element in the collection.

### Code Example

```java
public class Main {
    public static void main(String[] args) {
        List<Integer> values = new ArrayList<>();
        values.add(1);
        values.add(2);
        values.add(3);
        values.add(4);

        //using iterator
        System.out.println("Iterating the values using iterator method");
        Iterator<Integer> valuesIterator = values.iterator();
        while (valuesIterator.hasNext()){
            int val = valuesIterator.next();
            System.out.println(val);
            if(val == 3){
                valuesIterator.remove();
            }
        }

        System.out.println("Iterating the values using for-each loop");
        for(int val : values) {
            System.out.println(val);
        }

        //using forEach method
        System.out.println("testing forEach method");
        values.forEach((Integer val) -> System.out.println(val));
    }
}
```

**Output:**
```
Iterating the values using iterator method
1
2
3
4
Iterating the values using for-each loop
1
2
4
testing forEach method
1
2
4
```

## Collection Interface

Collection: It represents the group of objects. Its an interface which provides methods to work on group of objects.

Below are the most common used methods which are implemented by its child classes like ArrayList, Stack, LinkedList etc.

| S.NO | METHODS | Available in | USAGE |
|------|---------|--------------|-------|
| 1. | `size()` | Java 1.2 | It returns the total number of elements present in the collection. |
| 2. | `isEmpty()` | Java 1.2 | Used to check if collection is empty or has some value. It return true/false. |
| 3. | `contains()` | Java 1.2 | Used to search an element in the collection, returns true/false. |
| 4. | `toArray()` | Java 1.2 | It convert collection into an Array. |
| 5. | `add()` | Java 1.2 | Used to insert an element in the collection. |
| 6. | `remove()` | Java 1.2 | Used to remove an element from the collection. |
| 7. | `addAll()` | Java 1.2 | Used to insert one collection in another collection. |
| 8. | `removeAll()` | Java 1.2 | Remove all the elements from the collections, which are present in the collection passed in the parameter. |
| 9. | `clear()` | Java 1.2 | Remove all the elements from the collection. |
| 10. | `equals()` | Java 1.2 | Used to check if 2 collections are equal or not. |
| 11. | `stream()` and `parallelStream()` | Java 1.8 | Provide effective way to work with collection like filtering, processing data etc. |
| 12. | `iterator()` | Java 1.2 | As Iterable interface added in java 1.5, so before this, this method was used to iterate the collection and still can be used. |

## Collection vs Collections

**Collection** is part of Java Collection Framework. And its an interface, which expose various methods which is implemented by various collection classes like ArrayList, Stack, LinkedList etc.

**Collections** is a Utility class and provide static methods, which are used to operate on collections like sorting, swapping, searching, reverse, copy etc.

### Code Example

```java
public class Main {
    public static void main(String[] args) {
        List<Integer> values = new ArrayList<>();
        values.add(1);
        values.add(3);
        values.add(2);
        values.add(4);
        
        System.out.println("max value:" + Collections.max(values));
        System.out.println("min value:" + Collections.min(values));
        Collections.sort(values);
        System.out.println("sorted");
        values.forEach((Integer val) -> System.out.println(val));
    }
}
```

### Methods

- sort
- binarySearch
- get
- reverse
- shuffle
- swap
- copy
- min
- max
- rotate
- unmodifiableCollection