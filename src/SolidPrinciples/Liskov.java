package SolidPrinciples;

//SolidPrinciples.Liskov Substitution Problem
/*
In this code, the printArea method expects a SolidPrinciples.Shape object, and both SolidPrinciples.Circle and SolidPrinciples.Square are able to be passed
to this method without any issues. This demonstrates the SolidPrinciples.Liskov Substitution Principle in action.
The subclasses are substitutable for the superclass in a way that doesn't break the functionality of the program.

If a subclass violates the LSP, it may lead to unexpected behavior when substituting instances of the
subclass for instances of the superclass.
For example, if a SolidPrinciples.Square class were to override the calculateArea method to calculate the area differently from a square
(e.g., calculating it based on the diagonal), it would violate the LSP and potentially lead to incorrect results
when used in place of the SolidPrinciples.Shape class.

In summary, the SolidPrinciples.Liskov Substitution Principle encourages creating subclasses that can be seamlessly used in place
of their superclass, maintaining the expected behavior and functionality of the program.

* */
class Shape {
    public double calculateArea() {
        return 0;
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Square extends Shape {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }
}


public class Liskov {
    public static void printArea(Shape shape) {
        System.out.println("Area: " + shape.calculateArea());
    }

    public static void main(String[] args) {
        Shape circle = new Circle(5.0);
        Shape square = new Square(4.0);

        printArea(circle);  // Prints the area of the circle
        printArea(square);  // Prints the area of the square
    }
}
