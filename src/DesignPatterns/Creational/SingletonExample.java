package DesignPatterns.Creational;

/*
  The Singleton design pattern is a creational pattern that ensures a class has only one instance and
  provides a global point of access to that instance.
  It is useful when exactly one object is needed to coordinate actions across the system.

  The Singleton class has a private static instance variable that holds the single instance of the class.

  The constructor of the Singleton class is private to prevent instantiation from outside the class.

   Here's an example of a Singleton pattern implementation in Java:
* */

// Singleton class
class Singleton {
    // The single instance of the class
    private static Singleton instance;

    // Private constructor to prevent instantiation from outside the class
    private Singleton() {
        // Initialization code, if needed
    }

    // Public method to provide the instance of the class
    public static Singleton getInstance() {
        // Create the instance if it does not exist
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    // Other methods and fields can be added here
    public void doSomething() {
        System.out.println("Singleton is doing something.");
    }
}

public class SingletonExample {
    public static void main(String[] args) {
        // Obtain the Singleton instance
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();

        // Check if both instances are the same
        System.out.println("Are both instances the same? " + (singleton1 == singleton2));

        // Use the Singleton instance
        singleton1.doSomething();
        singleton2.doSomething();
    }
}

