# IMMUTABLE CLASSES

## IMMUTABLE CLASS:
- We can not change the value of an object once it is created.
- Declare class as 'final' so that it can not be extended.
- All class members should be private. So that direct access can be avoided.
- And class members are initialized only once using constructor.
- There should not be any setter methods, which is generally use to change the value.
- Just getter methods. And returns Copy of the member variable.
- Example: String, Wrapper Classes etc.

```java
import java.util.ArrayList;
import java.util.List;

final class MyImmutableClass {
    private final String name;
    private final List<Object> petNameList;
    
    MyImmutableClass(String name, List<Object> petNameList) {
        this.name = name;
        this.petNameList = petNameList;
    }
    
    public String getName() {
        return name;
    }
    
    public List<Object> getPetNameList() {
        //this is required, because making list final,
        // means you can not now point it to new list, but still can add, delete values in it
        //so thats why we send the copy of it.
        return new ArrayList<>(petNameList);
    }
}
```

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        List<Object> petNames = new ArrayList<>();
        petNames.add("sj");
        petNames.add("pj");
        
        MyImmutableClass obj = new MyImmutableClass("myName", petNames);
        obj.getPetNameList().add("hello");
        System.out.println(obj.getPetNameList());
    }
}
```

**Output:**
```
[sj, pj]
```

Since we're passing the copy of the list, new items won't be added to it hence making it truly final.
