package DesignPatterns.Creational;


/*
*The Builder Design Pattern is a creational pattern that allows you to construct complex objects step by step.
*The Builder Design Pattern allows you to construct complex objects while providing flexibility
and separation between the construction process and the actual object creation.
*It's particularly useful when dealing with objects that have multiple configuration options or variations.

** In this example:

We have a Pizza class representing the complex object we want to build.
There's an abstract PizzaBuilder class with methods for building different parts of the pizza.
Concrete builder classes (MargheritaPizzaBuilder and PepperoniPizzaBuilder)
extend the PizzaBuilder class to provide specific implementations for building different types of pizzas.
The Chef class acts as the director, controlling the construction process and guide the steps and working with different builders to create pizzas.
In the main method, we demonstrate how the builder pattern is used to create two different types of pizzas.
* */


// Abstract builder class
interface PizzaBuilder {

    void buildDough();

    void buildSauce();

    void buildTopping();

}

// Product class
class Pizza {
    private final String dough;
    private final String sauce;
    private final String topping;

    public Pizza(String dough, String sauce, String topping) {
        this.dough = dough;
        this.sauce = sauce;
        this.topping = topping;
    }

    public void describe() {
        System.out.println("Pizza with " + dough + " dough, " + sauce + " sauce, and " + topping + " topping.");
    }
}

// Concrete builder classes
class MargheritaPizzaBuilder implements PizzaBuilder {

    String doughType;
    String sauceType;
    String toppingType;

    @Override
    public void buildDough() {
        doughType = "pan crust";
    }

    @Override
    public void buildSauce() {
        sauceType = "tomato";
    }

    @Override
    public void buildTopping() {
        toppingType = "mozzarella cheese";
    }

    public Pizza getResult() {
        return new Pizza(doughType, sauceType, toppingType);
    }
}

class PepperoniPizzaBuilder implements PizzaBuilder {
    String doughType;
    String sauceType;
    String toppingType;


    @Override
    public void buildDough() {
        doughType = "pan crust";
    }

    @Override
    public void buildSauce() {
        sauceType = "spicy";
    }

    @Override
    public void buildTopping() {
        toppingType = "peperoni";
    }

    public Pizza getResult() {
        return new Pizza(doughType, sauceType, toppingType);
    }
}


// Director class
class Chef {
    private PizzaBuilder pizzaBuilder;

    public void setPizzaBuilder(PizzaBuilder pizzaBuilder) {
        this.pizzaBuilder = pizzaBuilder;
    }


    public void constructPizza() {
        pizzaBuilder.buildDough();
        pizzaBuilder.buildSauce();
        pizzaBuilder.buildTopping();
    }
}

public class BuilderPatternExample {
    public static void main(String[] args) {
        Chef chef = new Chef();

        MargheritaPizzaBuilder margheritaBuilder = new MargheritaPizzaBuilder();
        chef.setPizzaBuilder(margheritaBuilder);
        chef.constructPizza();

        Pizza margheritaPizza = margheritaBuilder.getResult();
        margheritaPizza.describe(); // Output: Pizza with thin crust dough, tomato sauce, and mozzarella cheese topping.

    }
}

