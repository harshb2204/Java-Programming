# Comparator V/s Comparable

Comparator and Comparable both provides a way to sort the collection of objects.

### 1. Primitive collection sorting

```java
public class Main {
    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4};
        Arrays.sort(array); // Sorted in Ascending order

    }
}
```

**Internal Implementation:**
```java
public static void sort(@NotNull int[] a) {
    DualPivotQuicksort.sort(a, 0, a.length - 1, null, 0, 0);
}
```

---

### 2. Object collection sorting

**Car Class:**
```java
public class Car {
    String carName;
    String carType;

    Car(String name, String type) {
        this.carName = name;
        this.carType = type;
    }
}
```

**Main Class:**
```java
public class Main {
    public static void main(String[] args) {
        Car[] carArray = new Car[3];
        carArray[0] = new Car("SUV", "petrol");
        carArray[1] = new Car("Sedan", "diesel");
        carArray[2] = new Car("HatchBack", "CNG");

        Arrays.sort(carArray);
    }
}
```

**Error:**
```text
Exception in thread "main" java.lang.ClassCastException: Car cannot be cast to java.lang.Comparable
    at java.util.ComparableTimSort.countRunAndMakeAscending(ComparableTimSort.java:320)
    at java.util.ComparableTimSort.sort(ComparableTimSort.java:188)
    at java.util.Arrays.sort(Arrays.java:1246)
    at Main.main(Main.java:11)
```


- Say you have an array [5,1,3,4]
- Internally for sorting they compare 2 values, say 5 and 3. It needs to be decided if they 
have to swap or not, comparator and comparable expose certain methods which tell that you
need to swap.
- Above it does not find the compare method, in the Car which will tell us whether we have to swap or not. Now we can ask the question how can we even sort this car array of objects.

## How to sort the Object Array?

With the help of:

### 1. Comparator

**Method Signature:** `int compare(T obj1, T obj2)`

**Description:** Sorting algorithm uses this `compare` method of `Comparator` to compare 2 variables and decide whether to swap the variables or not.

**Method Returns:**
- **1:** if o1 is bigger than o2
- **0:** if o1 and o2 is equals
- **-1:** if o1 is smaller than o2

**Note:** Mostly in algorithm, if this method return 1, it swap the values.
![](/diagrams/compare.png)

#### Code Example

```java
public class Main {
    public static void main(String[] args) {
        Integer[] a = {6, 4, 1, 9, 2, 11};
        Arrays.sort(a, (Integer val1, Integer val2) -> val1-val2);
        for(int v : a) {
            System.out.println(v);
        }
    }
}
```

**Output:**
```
1
2
4
6
9
11
```

#### Descending Order Example

```java
public class Main {
    public static void main(String[] args) {
        Integer[] a = {6, 4, 1, 9, 2, 11};
        Arrays.sort(a, (Integer val1, Integer val2) -> val2-val1);
        for(int v : a) {
            System.out.println(v);
        }
    }
}
```

**Output:**
```
11
9
6
4
2
1
```

#### Sorting Custom Objects by Field

**Car Class:**
```java
public class Car {
    String carName;
    String carType;

    Car(String name, String type) {
        this.carName = name;
        this.carType = type;
    }
}
```

**Main Class:**
```java
public class Main {
    public static void main(String[] args) {
        Car[] carArray = new Car[3];
        carArray[0] = new Car("SUV", "petrol");
        carArray[1] = new Car("Sedan", "diesel");
        carArray[2] = new Car("HatchBack", "cng");
        
        Arrays.sort(carArray, (Car obj1, Car obj2) -> obj2.carType.compareTo(obj1.carType));
        
        for(Car car : carArray) {
            System.out.println(car.carName + ".." + car.carType);
        }
    }
}
```

**Output:**
```
HatchBack..cng
Sedan..diesel
SUV..petrol
```

**Explanation:** This example sorts the `Car` objects by their `carType` field in ascending alphabetical order. The Comparator lambda `(Car obj1, Car obj2) -> obj2.carType.compareTo(obj1.carType)` compares the `carType` strings. When `obj2.carType.compareTo(obj1.carType)` returns a negative value (meaning obj2's carType comes before obj1's alphabetically), the Comparator returns negative, placing obj1 before obj2, resulting in ascending order: cng, diesel, petrol.

#### Using Comparator Class Implementation

**Car Class:**
```java
public class Car {
    String carName;
    String carType;

    Car(String name, String type) {
        this.carName = name;
        this.carType = type;
    }
}
```

**CarNameComparator Class:**
```java
public class CarNameComparator implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) {
        return o2.carName.compareTo(o1.carName);
    }
}
```

**Main Class:**
```java
public class Main {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("suv", "petrol"));
        cars.add(new Car("sedan", "diesel"));
        cars.add(new Car("hatchback", "cng"));
        
        Collections.sort(cars, new CarNameComparator());
        
        cars.forEach((Car carobj) -> System.out.println(carobj.carName + ".." + carobj.carType));
    }
}
```

**Output:**
```
hatchback..cng
sedan..diesel
suv..petrol
```

**Explanation:** This example demonstrates creating a separate `Comparator` class (`CarNameComparator`) that implements the `Comparator<Car>` interface. The `compare` method compares cars by their `carName` field. The logic `o2.carName.compareTo(o1.carName)` sorts in ascending alphabetical order by car name. The `Collections.sort()` method uses this comparator to sort the list of cars, resulting in: hatchback, sedan, suv.



- Comparator gives many options - you can create lambda expressions, you can create a seperate class (one class doing it on car name and other on car type) but in comparable
you can only do one type of sorting. 
- With comparable you have to change the object class 

```java
    Integer[] arr = {1,5,67};
    Arrays.sort(arr); // internally it goes, passes an object and uses compareTo 
    // as the object is typecasted to comparable Integer has to implement comparable

```
- compareTo() does sorting in ascending order therefore comparable only does ascending.



