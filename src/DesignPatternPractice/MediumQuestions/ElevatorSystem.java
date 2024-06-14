package DesignPatternPractice.MediumQuestions;


import java.util.*;

enum Direction {
    UP, DOWN
}

class Floor {
    private final int floorNumber;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}

class ElevatorCar {
    private int currentFloor;
    private Direction direction;
    private final Set<Integer> internalButtonsPressed;
    private final Queue<Integer> externalRequests;

    public ElevatorCar() {
        this.currentFloor = 0; // Assuming the elevator starts at ground floor
        this.direction = Direction.UP; // Assuming the elevator starts moving up
        this.internalButtonsPressed = new HashSet<>();
        this.externalRequests = new LinkedList<>();
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void pressInternalButton(int floorNumber) {
        internalButtonsPressed.add(floorNumber);
    }

    public boolean isInternalButtonPressed(int floorNumber) {
        return internalButtonsPressed.contains(floorNumber);
    }

    public void addExternalRequest(int floorNumber) {
        externalRequests.add(floorNumber);
    }

    public boolean hasExternalRequests() {
        return !externalRequests.isEmpty();
    }

    public int getNextExternalRequest() {
        if(!externalRequests.isEmpty()) {
            return externalRequests.poll();
        }
        return 0;
    }

    public void move() {
        if (direction == Direction.UP) {
            currentFloor++;
        } else {
            currentFloor--;
        }
    }

    public void changeDirection() {
        if (currentFloor == 0) {
            direction = Direction.UP;
        } else if (currentFloor == 10) { // Assuming there are 10 floors
            direction = Direction.DOWN;
        }
    }
}

class Display {
    public void showFloorNumber(int floorNumber) {
        System.out.println("Current floor: " + floorNumber);
    }
}

class InternalButton {
    private final int floorNumber;

    public InternalButton(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}

class ExternalButton {
    private final int floorNumber;
    private final Direction direction;

    public ExternalButton(int floorNumber, Direction direction) {
        this.floorNumber = floorNumber;
        this.direction = direction;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public Direction getDirection() {
        return direction;
    }
}

class Building {
    private final List<Floor> floors;

    public Building(int numFloors) {
        floors = new ArrayList<>();
        for (int i = 0; i < numFloors; i++) {
            floors.add(new Floor(i));
        }
    }

    public List<Floor> getFloors() {
        return floors;
    }
}

class ElevatorController {
    private ElevatorCar elevatorCar;
    private Display display;
    private List<InternalButton> internalButtons;
    private List<ExternalButton> externalButtons;
    private Building building;

    public ElevatorController(Building building) {
        this.elevatorCar = new ElevatorCar();
        this.display = new Display();
        this.internalButtons = new ArrayList<>();
        this.externalButtons = new ArrayList<>();
        this.building = building;
    }

    public void addInternalButton(int floorNumber) {
        internalButtons.add(new InternalButton(floorNumber));
    }

    public void addExternalButton(int floorNumber, Direction direction) {
        externalButtons.add(new ExternalButton(floorNumber, direction));
    }

    public void handleExternalButtonPress() {
        // Logic to handle external button press
        for (ExternalButton button : externalButtons) {
            if (button.getFloorNumber() == elevatorCar.getCurrentFloor()) {
                elevatorCar.addExternalRequest(button.getFloorNumber());
            }
        }
    }

    public void handleInternalButtonPress() {
        // Logic to handle internal button press
    }

    public void operateElevator() {
        while (true) {
            // Check for internal and external button presses
            handleInternalButtonPress();
            handleExternalButtonPress();

            // Move the elevator
            elevatorCar.move();

            // Change direction if needed
            elevatorCar.changeDirection();

            // Update display
            display.showFloorNumber(elevatorCar.getCurrentFloor());

            // Handle multiple requests at the same time using LOOK algorithm
            while (elevatorCar.hasExternalRequests()) {
                int nextFloor = elevatorCar.getNextExternalRequest();
                if ((elevatorCar.getDirection() == Direction.UP && nextFloor >= elevatorCar.getCurrentFloor()) ||
                        (elevatorCar.getDirection() == Direction.DOWN && nextFloor <= elevatorCar.getCurrentFloor())) {
                    // Elevator is moving towards the requested floor
                    // No need to change direction, continue moving
                } else {
                    // Elevator needs to change direction to serve the request
                    elevatorCar.changeDirection();
                }
                // Add more logic here, such as stopping at floors in between if needed
            }

            // Check if any internal buttons pressed for the current floor
            if (elevatorCar.isInternalButtonPressed(elevatorCar.getCurrentFloor())) {
                // Open doors
                // Close doors after some time
                // Move to next floor
            }

            // Add delay to simulate real-time operation
            try {
                Thread.sleep(2000); // Delay of 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ElevatorSystem {
    public static void main(String[] args) {
        Building building = new Building(10); // Create a building with 10 floors
        ElevatorController controller = new ElevatorController(building);

        // Add internal buttons
        controller.addInternalButton(1);
        controller.addInternalButton(2);

        // Add external buttons
        controller.addExternalButton(3, Direction.UP);
        controller.addExternalButton(5, Direction.DOWN);

        // Start operating the elevator
        controller.operateElevator();
    }
}

