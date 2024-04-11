package DesignPatterns.Behaviorial;


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
interface VendingMachineState {
    void insertMoney(int amount) throws Exception;

    void selectItem(String item) throws Exception;

    void dispenseItem() throws Exception;
}

// Concrete implementation of the VendingMachineState interface representing the 'NoMoneyState'
class NoMoneyState implements VendingMachineState {
    private final VendingMachine vendingMachine;

    public NoMoneyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertMoney(int amount) {
        System.out.println(amount + " inserted");
        vendingMachine.setState(vendingMachine.getHasMoneyState());
    }

    @Override
    public void selectItem(String item) throws Exception {
        throw new Exception("Please insert money first");

    }

    @Override
    public void dispenseItem() throws Exception {
        throw new Exception("Please insert money first");
    }
}

// Concrete implementation of the VendingMachineState interface representing the 'HasMoneyState'
class HasMoneyState implements VendingMachineState {
    private final VendingMachine vendingMachine;

    public HasMoneyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertMoney(int amount) throws Exception {
        throw new Exception("Money already inserted");

    }

    @Override
    public void selectItem(String item) {
        System.out.println("Item " + item + " selected");
        vendingMachine.setState(vendingMachine.getSoldState());
    }

    @Override
    public void dispenseItem() throws Exception {
        throw new Exception("Please select an item first");
    }
}

// Concrete implementation of the VendingMachineState interface representing the 'SoldState'
class SoldState implements VendingMachineState {
    private final VendingMachine vendingMachine;

    public SoldState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertMoney(int amount) throws Exception {
        throw new Exception("Please wait, we're already dispensing an item");

    }

    @Override
    public void selectItem(String item )throws Exception {
        throw new Exception("Please wait, we're already dispensing an item");
    }

    @Override
    public void dispenseItem() {
        System.out.println("Item dispensed");
        vendingMachine.setState(vendingMachine.getNoMoneyState());
    }
}

// Context class representing the vending machine
class VendingMachine {
    private final VendingMachineState noMoneyState;
    private final VendingMachineState hasMoneyState;
    private final VendingMachineState soldState;
    private VendingMachineState currentState;

    public VendingMachine() {
        noMoneyState = new NoMoneyState(this);
        hasMoneyState = new HasMoneyState(this);
        soldState = new SoldState(this);
        currentState = noMoneyState;
    }

    public void setState(VendingMachineState state) {
        this.currentState = state;
    }

    public VendingMachineState getNoMoneyState() {
        return noMoneyState;
    }

    public VendingMachineState getHasMoneyState() {
        return hasMoneyState;
    }

    public VendingMachineState getSoldState() {
        return soldState;
    }

    public void insertMoney(int amount) throws Exception {
        currentState.insertMoney(amount);
    }

    public void selectItem(String item) throws Exception {
        currentState.selectItem(item);
    }

    public void dispenseItem() throws Exception {
        currentState.dispenseItem();
    }
}

public class StatePatternExample {
    public static void main(String[] args) throws Exception {
        VendingMachine vendingMachine = new VendingMachine();

        // Simulating different interactions with the vending machine
        //vendingMachine.selectItem("Coke");
        vendingMachine.insertMoney(5);
        vendingMachine.selectItem("Coke");
        vendingMachine.dispenseItem();
    }
}
