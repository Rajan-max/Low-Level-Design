package DesignPatternPractice.MediumQuestions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

enum VehicleType {
    CAR,
    MOTORBIKE
}

interface PaymentProcess {
    double calculateParkingFee();
}

class Vehicle {
    private final String vehicleNumber;
    private final VehicleType type;

    public Vehicle(String vehicleNumber, VehicleType type) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }


    public VehicleType getType() {
        return type;
    }
}

class ParkingSpot {
    private final int spotNumber;
    private boolean available;
    private Vehicle vehicles;

    public ParkingSpot(int spotNumber) {
        this.spotNumber = spotNumber;
        this.available = true;
        this.vehicles = null;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public Vehicle getVehicle() {
        return vehicles;
    }

    public void parkVehicle(Vehicle vehicles) {
        this.vehicles = vehicles;
        this.available = false;
    }

    public void removeVehicle() {
        this.vehicles = null;
        this.available = true;
    }
}

class ParkingLot {
    private final List<ParkingSpot> spots;

    public ParkingLot(int capacity) {
        spots = new ArrayList<>();
        for (int i = 1; i <= capacity; i++) {
            spots.add(new ParkingSpot(i));
        }
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }
}

class TwoWheelerPayment implements PaymentProcess {

    @Override
    public double calculateParkingFee() {
        return 5.0;
    }
}

class FourWheelerPayment implements PaymentProcess {

    @Override
    public double calculateParkingFee() {
        return 10.0;
    }
}

class ParkingController {
    private final ParkingLot parkingLot;

    public ParkingController(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingSpot parkVehicle(Vehicle vehicles) {
        for (ParkingSpot spot : parkingLot.getSpots()) {
            if (spot.isAvailable()) {
                spot.parkVehicle(vehicles);
                return spot;
            }
        }
        return null; // No available spots
    }

    public double removeVehicle(Vehicle vehicles) {
        for (ParkingSpot spot : parkingLot.getSpots()) {
            if (!spot.isAvailable() && Objects.equals(spot.getVehicle().getVehicleNumber(), vehicles.getVehicleNumber())) {
                spot.removeVehicle();
                PaymentProcess paymentProcess;
                if (vehicles.getType().equals(VehicleType.CAR)) {  //Can be more scale using factory-pattern
                    paymentProcess = new FourWheelerPayment();
                } else {
                    paymentProcess = new TwoWheelerPayment();
                }
                return paymentProcess.calculateParkingFee();
            }
        }
        return -1.0; // Vehicle not found in any spot
    }

    public ParkingSpot findAvailableSpot(VehicleType type) {
        for (ParkingSpot spot : parkingLot.getSpots()) {
            if (spot.isAvailable()) {
                return spot;
            }
        }
        return null; // No available spots
    }
}

public class ParkingProblem {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(10);
        ParkingController controller = new ParkingController(parkingLot);

        Vehicle car1 = new Vehicle("ABC123", VehicleType.CAR);
        Vehicle car2 = new Vehicle("XYZ456", VehicleType.CAR);
        Vehicle bike1 = new Vehicle("DEF789", VehicleType.MOTORBIKE);
        Vehicle bike2 = new Vehicle("DEF7899", VehicleType.MOTORBIKE);

        ParkingSpot spot1 = controller.parkVehicle(car1);
        ParkingSpot spot2 = controller.parkVehicle(car2);
        ParkingSpot spot3 = controller.parkVehicle(bike1);

        System.out.println("Car 1 parked at spot: " + spot1.getSpotNumber());
        System.out.println("Car 2 parked at spot: " + spot2.getSpotNumber());
        System.out.println("Bike 1 parked at spot: " + spot3.getSpotNumber());

        // Example: Calculate parking fee for two-wheelers
        double twoWheelerFee = controller.removeVehicle(bike1);
        System.out.println("Parking fee for two-wheelers: $" + twoWheelerFee);

        // Example: Calculate parking fee for four-wheelers
        double fourWheelerFee = controller.removeVehicle(car1);
        System.out.println("Parking fee for four-wheelers: $" + fourWheelerFee);

        //Calculating fee without parking
        double notParkedFee = controller.removeVehicle(bike2);
        System.out.println("Parking fee for two-wheelers: $" + notParkedFee);
    }
}



