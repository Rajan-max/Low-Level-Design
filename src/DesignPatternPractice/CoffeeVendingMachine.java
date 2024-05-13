package DesignPatternPractice;




/*
* The State Design Pattern is a behavioral design pattern that allows an object to alter
* its behavior when its internal state changes. This pattern is particularly useful
* when an object's behavior depends on its state and changes in its state should trigger changes in behavior.

Let's illustrate this pattern with an example of a vending machine that dispenses different items
* depending on its current state (e.g., whether it has enough money inserted,
* whether the item is available, etc.). In this example, we'll focus on a simplified version of
*  a vending machine that sells drinks.
* */

// Interface representing the states of the vending machine

enum CoffeeType {
    Cafe_Latte,
    Espresso,
    Americano,
}

interface MachineState {
    default void insertMoney(CoffeeMachine coffeeMachine, int amount) throws Exception {
        throw new Exception("Something went wrong! Please try again!");
    }

    default void selectItem(CoffeeMachine coffeeMachine, CoffeeType coffeeType) throws Exception {
        throw new Exception("Something went wrong! Please try again!");
    }

    default void dispenseItem(CoffeeMachine coffeeMachine) throws Exception {
        throw new Exception("Something went wrong! Please try again!");
    }
}

// Concrete implementation of the VendingMachineState interface representing the 'NoMoneyState'
class IdleState implements MachineState {

    public IdleState() {
        System.out.println("Please Insert Money!");
    }

    @Override
    public void insertMoney(CoffeeMachine coffeeMachine, int amount) throws Exception {
        System.out.println("Money Inserted: " + amount);
        coffeeMachine.setState(new SelectProduct());
    }
}

// Concrete implementation of the VendingMachineState interface representing the 'HasMoneyState'
class SelectProduct implements MachineState {
    CoffeeType coffeeType;

    public SelectProduct() {
        System.out.println("Please select the coffee of your type!");
    }

    @Override
    public void selectItem(CoffeeMachine coffeeMachine, CoffeeType coffeeType) throws Exception {
        System.out.println("Product Selected!");
        this.coffeeType = coffeeType;
        coffeeMachine.setState(new DispenseState(coffeeType.toString()));
    }
}

// Concrete implementation of the VendingMachineState interface representing the 'SoldState'
class DispenseState implements MachineState {
    String coffeeType;

    public DispenseState(String coffeeType) {
        this.coffeeType = coffeeType;
        System.out.println("Please wait, your item is being dispensed!");
    }

    @Override
    public void dispenseItem(CoffeeMachine coffeeMachine) throws Exception {
        System.out.println("Please Enjoy: " + coffeeType);
        coffeeMachine.setState(new IdleState());
    }
}

// Context class representing the vending machine
class CoffeeMachine {
    private MachineState currentState;

    public CoffeeMachine() {
        currentState = new IdleState();
    }

    public void setState(MachineState state) {
        this.currentState = state;
    }

    public MachineState getCurrentState() {
        return currentState;
    }

}


public class CoffeeVendingMachine {
    public static void main(String[] args) throws Exception {
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        // Simulating different interactions with the vending machine
        //vendingMachine.getCurrentState().selectItem("Coke");
        MachineState state = coffeeMachine.getCurrentState();
        state.insertMoney(coffeeMachine, 15);
        state = coffeeMachine.getCurrentState();
        state.selectItem(coffeeMachine, CoffeeType.Espresso);
        state = coffeeMachine.getCurrentState();
        state.dispenseItem(coffeeMachine);
    }
}
