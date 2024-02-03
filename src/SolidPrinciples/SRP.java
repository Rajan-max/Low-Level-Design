package SolidPrinciples;

class Customer {
    private String name;
    private String email;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void updateProfile(String newName) {
        this.name = newName;
        System.out.println("Profile updated: New name - " + newName);
    }

    public String getName() {
        return name;
    }
}

class OrderProcessor {
    public void placeOrder(Customer customer, Item[] items) {
        // Simulate order processing
        System.out.println("Order placed for customer: " + customer.getName());
        System.out.println("Items:");
        for (Item item : items) {
            System.out.println("- " + item.getName());
        }
    }

    public void cancelOrder(int orderID) {
        // Simulate order cancellation
        System.out.println("Order canceled: Order ID - " + orderID);
    }
}

class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class SRP {
    public static void main(String[] args) {
        Customer customer = new Customer("Alice", "alice@example.com");
        OrderProcessor orderProcessor = new OrderProcessor();

        // Update customer profile
        customer.updateProfile("Alicia");

        // Place an order
        Item[] items = {new Item("Apple"), new Item("Banana")};
        orderProcessor.placeOrder(customer, items);

        // Cancel an order
        int orderID = 12345;
        orderProcessor.cancelOrder(orderID);
    }
}
