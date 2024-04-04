package DesignPatterns.Creational;

/*
The Factory Design Pattern is a creational pattern that provides an interface for creating objects in a super class,
 but allows subclasses to alter the type of objects that will be created.
 It's particularly useful when you have multiple classes that share a common interface and
 you want to delegate the responsibility of creating instances to a separate factory class.

In this example, we have the following components:

Product Interface (Shape): This defines the interface that all concrete shape classes will implement.
In this case, Circle and Square are the concrete implementations of the Shape interface.

Concrete Implementations of Products (Circle and Square): These are the classes that implement the Shape interface.
 They provide the actual behavior for drawing the respective shapes.

Factory Class (ShapeFactory): This class is responsible for creating instances of the products (shapes)
based on the input provided. It encapsulates the creation logic,
allowing the client code to create objects without directly knowing the concrete class being instantiated.

Client Code (FactoryPatternExample): This is where the factory and products are used.
The client code creates a ShapeFactory instance and uses it to create Shape objects (circles and squares).
* */


// Product interface
interface Shape {
    void draw();
}

// Concrete implementations of the product interface
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Square");
    }
}

// Factory class to create instances of shapes
class ShapeFactory {
    public Shape createShape(String shapeType) {
        if (shapeType.equalsIgnoreCase("circle")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("square")) {
            return new Square();
        } else {
            throw new IllegalArgumentException("Invalid shape type");
        }
    }
}

public class FactoryPatternExample {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();

        Shape circle = factory.createShape("circle");
        circle.draw(); // Output: Drawing a Circle

        Shape square = factory.createShape("square");
        square.draw(); // Output: Drawing a Square
    }
}
