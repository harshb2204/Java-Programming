// 1. Abstraction: Hides implementation details using abstract classes
abstract class Vehicle {
    abstract void start(); // Abstract method (no implementation)
}

// 2. Inheritance: Car inherits from Vehicle
class Car extends Vehicle {
    private String model;

    // 3. Encapsulation: Private variable with Getter & Setter
    public Car(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    void start() { // Implementation of abstract method
        System.out.println(model + " starts with a key.");
    }

    // 4. Polymorphism: Method Overloading (Compile-time Polymorphism)
    public void speedUp() {
        System.out.println(model + " is speeding up.");
    }

    public void speedUp(int increment) {
        System.out.println(model + " is speeding up by " + increment + " km/h.");
    }
}

public class Main {
    public static void main(String[] args) {
        Car myCar = new Car("Toyota");

        myCar.start(); // Calls overridden method (Runtime Polymorphism)
        myCar.speedUp(); // Calls first speedUp() method
        myCar.speedUp(20); // Calls overloaded speedUp(int) method

        System.out.println("Car Model: " + myCar.getModel()); // Encapsulation in action
    }
}
