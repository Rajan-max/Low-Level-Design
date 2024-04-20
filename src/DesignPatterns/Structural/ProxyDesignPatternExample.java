package DesignPatterns.Structural;

/*
The Proxy Pattern is a structural design pattern that provides a surrogate or placeholder for another object to control
access to it. This pattern is useful when you want to add an extra layer of control over accessing an object,
such as adding lazy initialization, access control, logging, or monitoring.

* */


// Subject interface
interface Image {
    void display();
}

// Real subject
class RealImage implements Image {
    private final String filename;

    RealImage(String filename) {
        this.filename = filename;
        loadImageFromDisk();
    }

    private void loadImageFromDisk() {
        System.out.println("Loading image: " + filename);
        // Simulate loading image from disk
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + filename);
        // Simulate displaying image
    }
}

// Proxy class
class ProxyImage implements Image {
    private final String filename;
    private RealImage realImage;

    ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

// Client class
public class ProxyDesignPatternExample {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("image1.jpg");
        Image image2 = new ProxyImage("image2.jpg");

        // Image1 will be loaded from disk
        image1.display();
        // Image1 will be displayed from memory (no loading from disk)
        image1.display();

        // Image2 will be loaded from disk
        image2.display();
    }
}


