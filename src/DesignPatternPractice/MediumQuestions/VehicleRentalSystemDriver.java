package DesignPatternPractice.MediumQuestions;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

enum VehicleTypes {
    SEDAN, SUV, HATCHBACK, CONVERTIBLE, MOTORBIKE, SCOOTER
}

enum RentalStatus {
    ONGOING, COMPLETED
}

abstract class Vehicles {
    private final String registrationNumber;
    private final VehicleTypes type;
    private final String model;
    private boolean isAvailable;

    public Vehicles(String registrationNumber, VehicleTypes type, String model) {
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.model = model;
        this.isAvailable = true;
    }

    public String getRegistrationNumber() {
        return  registrationNumber;
    }

    public VehicleTypes getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

class Car extends Vehicles {
    public Car(String registrationNumber, VehicleTypes type, String model) {
        super(registrationNumber, type, model);
    }
}

class Bike extends Vehicles {
    public Bike(String registrationNumber, VehicleTypes type, String model) {
        super(registrationNumber, type, model);
    }
}

class Customer {
    private String id;
    private String name;
    private String drivingLicense;

    public Customer(String id, String name, String drivingLicense) {
        this.id = id;
        this.name = name;
        this.drivingLicense = drivingLicense;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }
}

class Rental {
    private String rentalId;
    private Vehicles vehicles;
    private Customer customer;
    private Date rentalDate;
    private Date returnDate;
    private RentalStatus status;

    public Rental(String rentalId, Vehicles vehicles, Customer customer, Date rentalDate) {
        this.rentalId = rentalId;
        this.vehicles = vehicles;
        this.customer = customer;
        this.rentalDate = rentalDate;
        this.status = RentalStatus.ONGOING;
    }

    public String getRentalId() {
        return rentalId;
    }

    public Vehicles getVehicle() {
        return vehicles;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public void completeRental(Date returnDate) {
        this.returnDate = returnDate;
        this.status = RentalStatus.COMPLETED;
        this.vehicles.setAvailable(true);
    }
}

class VehicleFactory {
    public static Vehicles createVehicle(String registrationNumber, VehicleTypes type, String model) {
        return switch (type) {
            case SEDAN, SUV, HATCHBACK, CONVERTIBLE -> new Car(registrationNumber, type, model);
            case MOTORBIKE, SCOOTER -> new Bike(registrationNumber, type, model);
            default -> throw new IllegalArgumentException("Invalid vehicle type");
        };
    }
}

class CustomerFactory {
    public static Customer createCustomer(String id, String name, String drivingLicense) {
        return new Customer(id, name, drivingLicense);
    }
}

class VehicleRentalSystem {
    private static VehicleRentalSystem instance;
    private final List<Vehicles> vehicles;
    private final List<Customer> customers;
    Map<String,Rental>rentalMap;

    private VehicleRentalSystem() {
        vehicles = new ArrayList<>();
        customers = new ArrayList<>();
        rentalMap=new ConcurrentHashMap<>();
    }

    public static VehicleRentalSystem getInstance() {
        if (instance == null) {
            instance = new VehicleRentalSystem();
        }
        return instance;
    }

    public void addVehicle(Vehicles vehicles) {
        this.vehicles.add(vehicles);
    }

    public void removeVehicle(String registrationNumber) {
        vehicles.removeIf(vehicles -> vehicles.getRegistrationNumber().equals(registrationNumber));
    }

    public List<Vehicles> getAvailableVehicles() {
        List<Vehicles> availableVehicles = new ArrayList<>();
        for (Vehicles vehicles : this.vehicles) {
            if (vehicles.isAvailable()) {
                availableVehicles.add(vehicles);
            }
        }
        return availableVehicles;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Rental rentVehicle(VehicleTypes vehicleTypes, String customerId) {
        Vehicles vehicle = this.vehicles.stream()
                .filter(v -> v.getType()==vehicleTypes && v.isAvailable())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Vehicle not available"));

        Customer customer = customers.stream()
                .filter(c -> c.getId().equals(customerId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Rental rental = new Rental(UUID.randomUUID().toString(), vehicle, customer, new Date());
        vehicle.setAvailable(false);
        rentalMap.put(rental.getRentalId(),rental);
        return rental;
    }

    public void returnVehicle(String rentalId, Date returnDate) {

        Rental rental=rentalMap.get(rentalId);
        if(rental==null){
            throw new RuntimeException("Rental not found or already completed");
        }
        rentalMap.remove(rental.getRentalId());

        rental.completeRental(returnDate);
    }

    public List<Rental> getRentalHistory() {
        return new ArrayList<>(rentalMap.values());
    }
}

public class VehicleRentalSystemDriver {
    public static void main(String[] args) {
        VehicleRentalSystem rentalSystem = VehicleRentalSystem.getInstance();

        Vehicles car1 = VehicleFactory.createVehicle("ABC123", VehicleTypes.SEDAN, "Toyota Camry");
        Vehicles bike1 = VehicleFactory.createVehicle("XYZ789", VehicleTypes.MOTORBIKE, "Yamaha R15");

        rentalSystem.addVehicle(car1);
        rentalSystem.addVehicle(bike1);

        Customer customer1 = CustomerFactory.createCustomer("C001", "John Doe", "D12345");
        rentalSystem.addCustomer(customer1);

        Rental rental=rentalSystem.rentVehicle(VehicleTypes.MOTORBIKE, "C001");
        System.out.println("Successfully rented: "+rental.getVehicle().getType() + " with transaction: "+ rental.getRentalId());
        List<Vehicles> availableVehicles = rentalSystem.getAvailableVehicles();
        for (Vehicles vehicles : availableVehicles) {
            System.out.println("Available Vehicle: " + vehicles.getModel());
        }
        try {
            rentalSystem.returnVehicle(rental.getRentalId(), new Date());
            System.out.println("Successfully returned Vehicle!");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {  //Returning the same vehicle again
            rentalSystem.returnVehicle(rental.getRentalId(), new Date());
            System.out.println("Successfully returned Vehicle!");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        availableVehicles=rentalSystem.getAvailableVehicles();
        for (Vehicles vehicles : availableVehicles) {
            System.out.println("Available Vehicle: " + vehicles.getModel());
        }

    }
}
