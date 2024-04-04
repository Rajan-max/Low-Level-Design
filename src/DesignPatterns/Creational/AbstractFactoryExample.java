package DesignPatterns.Creational;


//The Abstract Factory Design Pattern is a creational design pattern that provides an interface
// for creating families of related or dependent products without specifying their concrete classes.
// It is useful when you need to create multiple related objects, but you want to ensure that
// the objects created are compatible with each other.

/*
* Abstract Factory defines an interface for creating all distinct products but leaves the actual product
* creation to concrete factory classes. Each factory type corresponds to a certain product variety.

The client code calls the creation methods of a factory object instead of creating products
* directly with a constructor call (new operator).
*  Since a factory corresponds to a single product variant, all its products will be compatible.

Client code works with factories and products only through their abstract interfaces.
* This lets the client code work with any product variants, created by the factory object.
* You just create a new concrete factory class and pass it to the client code.
* */

// Abstract Product A
interface CPU {
    String getSpecification();
}
// Concrete Product A1
class IntelCPU implements CPU {
    @Override
    public String getSpecification() {
        return "Intel Core i7";
    }
}

// Concrete Product A2
class AMDCPU implements CPU {
    @Override
    public String getSpecification() {
        return "AMD Ryzen 9";
    }
}

// Abstract Product B
interface GPU {
    String getSpecification();
}

// Concrete Product B1
class NvidiaGPU implements GPU {
    @Override
    public String getSpecification() {
        return "Nvidia GeForce RTX 3080";
    }
}

// Concrete Product B2
class AMDGPU implements GPU {
    @Override
    public String getSpecification() {
        return "AMD Radeon RX 6800 XT";
    }
}

// Abstract Factory
interface ComputerFactory {
    CPU createCPU();

    GPU createGPU();
}




// Concrete Factory 1
class HighEndComputerFactory implements ComputerFactory {
    @Override
    public CPU createCPU() {
        return new IntelCPU();
    }

    @Override
    public GPU createGPU() {
        return new NvidiaGPU();
    }
}

// Concrete Factory 2
class MidRangeComputerFactory implements ComputerFactory {
    @Override
    public CPU createCPU() {
        return new AMDCPU();
    }

    @Override
    public GPU createGPU() {
        return new AMDGPU();
    }
}

// Client class
class ComputerShop {
    private final ComputerFactory computerFactory;

    public ComputerShop(ComputerFactory computerFactory) {
        this.computerFactory = computerFactory;
    }

    public void assembleComputer() {
        CPU cpu = computerFactory.createCPU();
        GPU gpu = computerFactory.createGPU();

        System.out.println("Assembling computer with:");
        System.out.println("CPU: " + cpu.getSpecification());
        System.out.println("GPU: " + gpu.getSpecification());
    }


}

public class AbstractFactoryExample {
    public static void main(String[] args) {
        // High-end computer
        ComputerFactory highEndFactory = new HighEndComputerFactory();
        ComputerShop highEndComputerShop = new ComputerShop(highEndFactory);
        highEndComputerShop.assembleComputer();

        // Mid-range computer
        ComputerFactory midRangeFactory = new MidRangeComputerFactory();
        ComputerShop midRangeComputerShop = new ComputerShop(midRangeFactory);
        midRangeComputerShop.assembleComputer();
    }
}
