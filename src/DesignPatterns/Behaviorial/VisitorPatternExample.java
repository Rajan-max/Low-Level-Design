package DesignPatterns.Behaviorial;

/*
The Visitor Pattern is a behavioral design pattern that allows you to add new operations to existing
object structures without modifying those structures.
It achieves this by separating the algorithm from the object structure on which it operates.
* */

// Visitor interface
interface ComputerPartVisitor {
    void visit(Computer computer);
    void visit(Mouse mouse);
    void visit(Keyboard keyboard);
    void visit(Monitor monitor);
}

// Element interface
interface ComputerPart {
    void accept(ComputerPartVisitor visitor);
}

// Concrete element classes
class Computer implements ComputerPart {
    private final ComputerPart[] parts;

    Computer() {
        parts = new ComputerPart[] {new Mouse(), new Keyboard(), new Monitor()};
    }

    @Override
    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
        for (ComputerPart part : parts) {
            part.accept(visitor);
        }
    }
}

class Mouse implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
}

class Keyboard implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
}

class Monitor implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
}

// Concrete visitor class implementing operations for computer parts
class ComputerPartDisplayVisitor implements ComputerPartVisitor {
    @Override
    public void visit(Computer computer) {
        System.out.println("Displaying Computer");
    }

    @Override
    public void visit(Mouse mouse) {
        System.out.println("Displaying Mouse");
    }

    @Override
    public void visit(Keyboard keyboard) {
        System.out.println("Displaying Keyboard");
    }

    @Override
    public void visit(Monitor monitor) {
        System.out.println("Displaying Monitor");
    }
}

// Client class
public class VisitorPatternExample {
    public static void main(String[] args) {
        ComputerPart computer = new Computer();
        ComputerPartVisitor visitor = new ComputerPartDisplayVisitor();
        computer.accept(visitor);
    }
}


