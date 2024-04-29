package DesignPatternPractice;


import java.util.ArrayList;
import java.util.List;

class User {
    private int userId;
    private String name;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}

abstract class Vehicle1 {
    private int vehicleId;
    private String type;

    public Vehicle1(int vehicleId, String type) {
        this.vehicleId = vehicleId;
        this.type = type;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getType() {
        return type;
    }
}

class TwoWheeler extends Vehicle1 {
    public TwoWheeler(int vehicleId) {
        super(vehicleId, "Two Wheeler");
    }
}

class FourWheeler extends Vehicle1 {
    public FourWheeler(int vehicleId) {
        super(vehicleId, "Four Wheeler");
    }
}

class Store {
    private final List<Vehicle1> vehicles;

    public Store() {
        vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle1 vehicle) {
        vehicles.add(vehicle);
    }

    public List<Vehicle1> getAvailableVehicles() {
        return vehicles;
    }
}

class Payment {
    public boolean processPayment(double amount) {
        // Logic to process payment
        return true; // Assuming payment is always successful for demonstration
    }
}

class RentalSystem {
    private final List<User> users;
    private final List<RentalTransaction> transactions;
    private final Store store;
    private final Payment payment;

    public RentalSystem(Store store, Payment payment) {
        users = new ArrayList<>();
        transactions = new ArrayList<>();
        this.store = store;
        this.payment = payment;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public RentalTransaction rentVehicle(User user, Vehicle1 vehicle) {
        if (store.getAvailableVehicles().contains(vehicle) && users.contains(user)) {
            RentalTransaction transaction = new RentalTransaction(transactions.size() + 1, user, vehicle, 1);
            transactions.add(transaction);
            System.out.println("Vehicle rented successfully. Transaction ID: " + transaction.getRentalId());
            return transaction;
        } else {
            System.out.println("Cannot rent vehicle. User or vehicle not found.");
            return null;
        }
    }

    public void returnVehicle(RentalTransaction transaction) {
        if (transactions.contains(transaction)) {
            // Process payment
            double amount = transaction.getRentalDuration() * 10.0; // Assuming $10 per day rental fee
            if (payment.processPayment(amount)) {
                transactions.remove(transaction);
                System.out.println("Vehicle returned successfully. Payment processed.");
            } else {
                System.out.println("Payment processing failed. Vehicle cannot be returned.");
            }
        } else {
            System.out.println("Invalid transaction. Vehicle cannot be returned.");
        }
    }
}

class RentalTransaction {
    private final int rentalId;
    private final User user;
    private final Vehicle1 vehicle1;
    private final int rentalDuration;

    public RentalTransaction(int rentalId, User user, Vehicle1 vehicle1, int rentalDuration) {
        this.rentalId = rentalId;
        this.user = user;
        this.vehicle1 = vehicle1;
        this.rentalDuration = rentalDuration;
    }

    public int getRentalId() {
        return rentalId;
    }

    public User getUser() {
        return user;
    }

    public Vehicle1 getVehicle() {
        return vehicle1;
    }

    public int getRentalDuration() {
        return rentalDuration;
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        // Creating users
        User user1 = new User(1, "John");
        User user2 = new User(2, "Alice");

        // Creating vehicles
        TwoWheeler bike1 = new TwoWheeler(101);
        FourWheeler car1 = new FourWheeler(102);

        // Creating store
        Store store = new Store();
        store.addVehicle(bike1);
        store.addVehicle(car1);

        // Creating payment system
        Payment payment = new Payment();

        // Creating rental system
        RentalSystem rentalSystem = new RentalSystem(store, payment);

        // Adding users to the rental system
        rentalSystem.addUser(user1);
        rentalSystem.addUser(user2);

        // Renting vehicles
        rentalSystem.rentVehicle(user1, bike1);
        rentalSystem.rentVehicle(user2, car1);

        // Returning vehicles
        // Assume returning based on transaction, so we need to retrieve the transaction first
        RentalTransaction transaction = rentalSystem.rentVehicle(user1, bike1);
        if (transaction != null) {
            rentalSystem.returnVehicle(transaction);
        }
    }
}

