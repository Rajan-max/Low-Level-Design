package DesignPatterns.Behaviorial;

/*
Template Method Pattern is a behavioral design pattern that defines the skeleton of an algorithm in the
superclass but lets subclasses override specific steps of the algorithm without changing its structure.
This pattern is useful when u want all classes to follow specific steps to process a task but also provide
flexibility in providing their own logic in that specific steps.
* */

// Abstract class defining the template method
abstract class CaffeineBeverage {
    // Template method
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    // Abstract methods to be implemented by subclasses
    abstract void brew();

    abstract void addCondiments();

    // Common methods
    void boilWater() {
        System.out.println("Boiling water");
    }

    void pourInCup() {
        System.out.println("Pouring into cup");
    }
}

// Concrete subclass implementing specific steps for coffee
class Coffee extends CaffeineBeverage {
    void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }
}

// Concrete subclass implementing specific steps for tea
class Tea extends CaffeineBeverage {
    void brew() {
        System.out.println("Steeping the tea");
    }

    void addCondiments() {
        System.out.println("Adding Lemon");
    }
}

// Client class
public class TemplatePatternExample {
    public static void main(String[] args) {
        CaffeineBeverage coffee = new Coffee();
        CaffeineBeverage tea = new Tea();

        System.out.println("Making Coffee...");
        coffee.prepareRecipe();

        System.out.println("\nMaking Tea...");
        tea.prepareRecipe();
    }
}


