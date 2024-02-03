package DesignPatterns;


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
The Chef class acts as the director, controlling the construction process and working with different builders to create pizzas.
In the main method, we demonstrate how the builder pattern is used to create two different types of pizzas.
* */



// Product class
class Pizza {
    private String dough;
    private String sauce;
    private String topping;

    public void setDough(String dough) {
        this.dough = dough;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public void describe() {
        System.out.println("Pizza with " + dough + " dough, " + sauce + " sauce, and " + topping + " topping.");
    }
}

// Abstract builder class
abstract class PizzaBuilder {
    protected Pizza pizza = new Pizza();

    public abstract void buildDough();

    public abstract void buildSauce();

    public abstract void buildTopping();

    public Pizza getPizza() {
        return pizza;
    }
}

// Concrete builder classes
class MargheritaPizzaBuilder extends PizzaBuilder {
    @Override
    public void buildDough() {
        pizza.setDough("thin crust");
    }

    @Override
    public void buildSauce() {
        pizza.setSauce("tomato");
    }

    @Override
    public void buildTopping() {
        pizza.setTopping("mozzarella cheese");
    }
}

class PepperoniPizzaBuilder extends PizzaBuilder {
    @Override
    public void buildDough() {
        pizza.setDough("pan crust");
    }

    @Override
    public void buildSauce() {
        pizza.setSauce("spicy");
    }

    @Override
    public void buildTopping() {
        pizza.setTopping("pepperoni");
    }
}

// Director class
class Chef {
    private PizzaBuilder pizzaBuilder;

    public void setPizzaBuilder(PizzaBuilder pizzaBuilder) {
        this.pizzaBuilder = pizzaBuilder;
    }

    public Pizza getPizza() {
        return pizzaBuilder.getPizza();
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

        PizzaBuilder margheritaBuilder = new MargheritaPizzaBuilder();
        chef.setPizzaBuilder(margheritaBuilder);
        chef.constructPizza();

        Pizza margheritaPizza = chef.getPizza();
        margheritaPizza.describe(); // Output: Pizza with thin crust dough, tomato sauce, and mozzarella cheese topping.

        PizzaBuilder pepperoniBuilder = new PepperoniPizzaBuilder();
        chef.setPizzaBuilder(pepperoniBuilder);
        chef.constructPizza();

        Pizza pepperoniPizza = chef.getPizza();
        pepperoniPizza.describe(); // Output: Pizza with pan crust dough, spicy sauce, and pepperoni topping.
    }
}

