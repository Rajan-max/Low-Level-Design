package DesignPatterns.Behaviorial;

/*
The Null Object Pattern is a design pattern used to encapsulate the absence of an object by providing
a substitute object with neutral or no behavior.
This pattern helps to avoid null references that can lead to NullPointerExceptions
and simplify code by providing a consistent interface for both real and null objects.
* */

interface Vehicle {
    int getTankCapacity();

    int getSeatingCapacity();
}

class Car implements Vehicle {

    @Override
    public int getTankCapacity() {
        return 10;
    }

    @Override
    public int getSeatingCapacity() {
        return 6;
    }
}

//Null Object Behaviour
class NullVehicle implements Vehicle {

    @Override
    public int getTankCapacity() {
        return 0;
    }

    @Override
    public int getSeatingCapacity() {
        return 0;
    }
}

class VehicleFactory {
    public static Vehicle getVehicleObject(String vehicleType) {
        if ("car".equalsIgnoreCase(vehicleType)) {
            return new Car();
        }
        return new NullVehicle();
    }
}

public class NullObjectPatternExample {
    public static void main(String[] args) {
        Vehicle vehicle = VehicleFactory.getVehicleObject("Car");
        printVehicle(vehicle);

        System.out.println("Now doing for null object");
        Vehicle vehicle1 = VehicleFactory.getVehicleObject("Bike");
        printVehicle(vehicle1);
    }

    public static void printVehicle(Vehicle vehicle) {
        System.out.println(vehicle.getTankCapacity() + "L");
        System.out.println(vehicle.getSeatingCapacity() + " Person");
    }
}
