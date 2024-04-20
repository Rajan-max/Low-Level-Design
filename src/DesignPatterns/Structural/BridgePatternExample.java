package DesignPatterns.Structural;

/*
The Bridge Pattern is a structural design pattern that decouples an abstraction from its implementation so that the
two can vary independently.
It allows the abstraction and implementation to be developed independently and then composed together dynamically at runtime.

This pattern allows for greater flexibility and extensibility by separating the abstraction and implementation
into separate hierarchies. It also promotes code re-usability and maintainability
by allowing new abstractions and implementations to be added without modifying existing code.

Let's illustrate the Bridge Pattern with an example of a shape drawing application where we have different types of
shapes (such as circle, square, etc.) and different types of drawing implementations
(such as drawing with a pen, drawing with a pencil, etc.).

* */


// Implementor interface
interface DrawingAPI {
    void drawCircle(double x, double y, double radius);

    void drawSquare(double x, double y, double side);
}

// Abstraction Interface
abstract class Shape {
    protected DrawingAPI drawingAPI;

    Shape(DrawingAPI drawingAPI) {
        this.drawingAPI = drawingAPI;
    }

    abstract void draw();
}

// Concrete Abstraction
class Circle extends Shape {
    private final double x, y, radius;

    Circle(double x, double y, double radius, DrawingAPI drawingAPI) {
        super(drawingAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    void draw() {
        drawingAPI.drawCircle(x, y, radius);
    }
}

class Square extends Shape {
    private final double x, y, side;

    Square(double x, double y, double side, DrawingAPI drawingAPI) {
        super(drawingAPI);
        this.x = x;
        this.y = y;
        this.side = side;
    }

    @Override
    void draw() {
        drawingAPI.drawSquare(x, y, side);
    }
}

// Concrete Implementor
class DrawingAPIPen implements DrawingAPI {
    @Override
    public void drawCircle(double x, double y, double radius) {
        System.out.printf("Drawing circle with pen at (%.2f, %.2f) with radius %.2f%n", x, y, radius);
    }

    @Override
    public void drawSquare(double x, double y, double side) {
        System.out.printf("Drawing square with pen at (%.2f, %.2f) with side %.2f%n", x, y, side);
    }
}

class DrawingAPIPencil implements DrawingAPI {
    @Override
    public void drawCircle(double x, double y, double radius) {
        System.out.printf("Drawing circle with pencil at (%.2f, %.2f) with radius %.2f%n", x, y, radius);
    }

    @Override
    public void drawSquare(double x, double y, double side) {
        System.out.printf("Drawing square with pencil at (%.2f, %.2f) with side %.2f%n", x, y, side);
    }
}

// Client
public class BridgePatternExample {
    public static void main(String[] args) {
        DrawingAPI pen = new DrawingAPIPen();
        DrawingAPI pencil = new DrawingAPIPencil();

        Shape circle = new Circle(1, 2, 3, pen);
        circle.draw();

        Shape square = new Square(4, 5, 6, pencil);
        square.draw();
    }
}
