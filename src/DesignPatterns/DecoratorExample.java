package DesignPatterns;


/*
  The Decorator design pattern is a structural pattern that allows behavior to be added to an individual object,
  either statically or dynamically, without affecting the behavior of other objects from the same class.

  Involves a base component (interface or abstract class) and decorators that add specific behaviors
  to the component. The client interacts directly with the decorated component.

  In the Decorator pattern, you create a set of decorator classes that are used to wrap concrete components.
  These decorators add additional functionalities to the original object.
  It is an alternative to subclassing for extending functionality.

  Let's consider an example of a simple coffee ordering system where we want to add additional toppings to
  a basic coffee. We'll use the Decorator pattern to dynamically add toppings to the coffee.

  -Coffee is the interface representing the component (the basic coffee).

  -SimpleCoffee is the concrete component representing a simple coffee.

  -CoffeeDecorator is the abstract decorator class that implements the Coffee interface and holds a
  reference to a decorated coffee.

  -MilkDecorator and SugarDecorator are concrete decorators that add additional
  functionalities (milk and sugar) to the coffee.

  -The client code demonstrates how to order a simple coffee and then add milk
  and sugar to it using decorators.

  -This design allows you to combine different functionalities dynamically at runtime.
  It follows the Open/Closed Principle (open for extension, closed for modification)
  because you can introduce new decorators without modifying existing code.
* */

// Component interface
interface Coffee {
    double cost(); // Returns the cost of the coffee

    String description(); // Returns the description of the coffee
}

// Decorator abstract class
interface CoffeeDecorator extends Coffee {

}

// Concrete component
class SimpleCoffee implements Coffee {
    @Override
    public double cost() {
        return 2.0; // Cost of a simple coffee
    }

    @Override
    public String description() {
        return "Simple Coffee"; // Description of a simple coffee
    }
}

// Concrete decorators
class MilkDecorator implements CoffeeDecorator {

    Coffee baseCoffee;

    public MilkDecorator(Coffee baseCoffee) {
        this.baseCoffee = baseCoffee;
    }

    @Override
    public double cost() {
        return baseCoffee.cost() + 0.5; // Additional cost for milk
    }

    @Override
    public String description() {
        return baseCoffee.description() + ", Milk"; // Additional description for milk
    }
}

class SugarDecorator implements CoffeeDecorator {
    Coffee baseCoffee;

    public SugarDecorator(Coffee baseCoffee) {
        this.baseCoffee = baseCoffee;
    }

    @Override
    public double cost() {
        return baseCoffee.cost() + 0.2; // Additional cost for sugar
    }

    @Override
    public String description() {
        return baseCoffee.description() + ", Sugar"; // Additional description for sugar
    }
}

// Client code
public class DecoratorExample {
    public static void main(String[] args) {
        // Order a simple coffee
        Coffee coffee = new SimpleCoffee();
        System.out.println("Cost: $" + coffee.cost() + ", Description: " + coffee.description());

        // Add milk to the coffee
        Coffee milkCoffee = new MilkDecorator(coffee);
        System.out.println("Cost: $" + milkCoffee.cost() + ", Description: " + milkCoffee.description());

        // Add sugar to the coffee
        Coffee sugarMilkCoffee = new SugarDecorator(milkCoffee);
        System.out.println("Cost: $" + sugarMilkCoffee.cost() + ", Description: " + sugarMilkCoffee.description());
    }
}

