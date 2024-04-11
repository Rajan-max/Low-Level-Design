package DesignPatterns.Behaviorial;

/*
* The Chain of Responsibility is a behavioral design pattern where a request is passed along a chain
* of handlers. Each handler decides either to process the request or to pass it to the next handler in the chain.

Let's illustrate this pattern with an example of a purchase approval process in a company.
* Different managers have different spending limits for approving purchases.
* If a purchase request exceeds a manager's spending limit, it should be forwarded to the
* next higher-level manager until it's either approved or rejected.
* */



// Request class representing a purchase request
class PurchaseRequest {
    private final String item;
    private final double amount;

    public PurchaseRequest(String item, double amount) {
        this.item = item;
        this.amount = amount;
    }

    public String getItem() {
        return item;
    }

    public double getAmount() {
        return amount;
    }
}

// Handler interface representing a manager who can approve purchase requests
interface PurchaseApprover {
    void setNextApprover(PurchaseApprover nextApprover);

    void processRequest(PurchaseRequest request);
}

// Concrete handler representing a manager with a spending limit
class Manager implements PurchaseApprover {
    private final double spendingLimit;
    private final String level;
    private PurchaseApprover nextApprover;

    public Manager(double spendingLimit,String level) {
        this.spendingLimit = spendingLimit;
        this.level=level;
    }

    @Override
    public void setNextApprover(PurchaseApprover nextApprover) {
        this.nextApprover = nextApprover;
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() <= spendingLimit) {
            System.out.println("Purchase request for " + request.getItem() + " approved by Manager "+ level +" with spending limit $" + spendingLimit);
        } else if (nextApprover != null) {
            System.out.println( level + " Manager with spending limit $" + spendingLimit + " cannot approve, forwarding to next level");
            nextApprover.processRequest(request);
        } else {
            System.out.println("No manager can approve this purchase request.");
        }
    }
}

public class ChainOfResponsibilityPatternExample {
    public static void main(String[] args) {
        // Creating a chain of approvers
        PurchaseApprover manager1 = new Manager(1000,"level-1");
        PurchaseApprover manager2 = new Manager(2000,"level-2");
        PurchaseApprover manager3 = new Manager(5000,"level-3");

        // Linking the managers in the chain
        manager1.setNextApprover(manager2);
        manager2.setNextApprover(manager3);
        manager3.setNextApprover(null);

        // Simulating purchase requests
        PurchaseRequest request1 = new PurchaseRequest("Laptop", 1500);
        PurchaseRequest request2 = new PurchaseRequest("Printer", 3000);
        PurchaseRequest request3 = new PurchaseRequest("Desk", 400);

        // Processing the purchase requests
        manager1.processRequest(request1);
        manager1.processRequest(request2);
        manager1.processRequest(request3);
    }
}


