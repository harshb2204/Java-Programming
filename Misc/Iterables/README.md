# Iterables in Java

## Overview
In Java, the `Iterable` interface is a fundamental part of the Collections Framework that allows objects to be iterated over using the enhanced for-loop (for-each loop). It's the root interface for all collection classes.

## Iterable Interface

The `Iterable<T>` interface is defined in the `java.lang` package and contains a single method:

```java
public interface Iterable<T> {
    Iterator<T> iterator();
}
```

## Iterator Interface

The `Iterator<E>` interface provides methods to iterate over a collection:

```java
public interface Iterator<E> {
    boolean hasNext();    // Returns true if there are more elements
    E next();            // Returns the next element
    void remove();       // Removes the last element returned by next() (optional)
}
```

## Key Features

### 1. For-Each Loop Support
Any class that implements `Iterable` can be used with the enhanced for-loop:

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
for (String name : names) {
    System.out.println(name);
}
```

### 2. Built-in Iterable Collections
Most collection classes in Java implement `Iterable`:
- `List` implementations (`ArrayList`, `LinkedList`, etc.)
- `Set` implementations (`HashSet`, `TreeSet`, etc.)
- `Queue` implementations (`PriorityQueue`, `LinkedList`, etc.)
- `Map` implementations (via `keySet()`, `values()`, `entrySet()`)



## Iterator Methods Explained

### hasNext()
- Returns `true` if there are more elements to iterate over
- Returns `false` if the iterator has reached the end

### next()
- Returns the next element in the iteration
- Throws `NoSuchElementException` if there are no more elements
- Advances the iterator position

### remove() (Optional Operation)
- Removes the last element returned by `next()`
- Can only be called once per call to `next()`
- Throws `UnsupportedOperationException` if not supported
- Throws `IllegalStateException` if called without calling `next()` first

## Common Patterns

### 1. Manual Iteration
```java
Iterator<String> iterator = list.iterator();
while (iterator.hasNext()) {
    String element = iterator.next();
    if (someCondition) {
        iterator.remove(); // Safe removal during iteration
    }
}
```

### 2. For-Each Loop (Recommended)
```java
for (String element : list) {
    System.out.println(element);
    // Note: Cannot remove elements during for-each iteration
}
```

