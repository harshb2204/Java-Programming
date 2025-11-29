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