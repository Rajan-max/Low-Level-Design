package DesignPatterns.Structural;

/*
The Composite Pattern is a structural design pattern that allows you to compose objects into tree-like structures to
represent part-whole hierarchies. It lets clients treat individual objects and compositions of objects uniformly.
This pattern allows clients to work with individual objects and compositions of objects uniformly,
simplifying the code and making it easier to manage complex hierarchies.
* */

import java.util.ArrayList;
import java.util.List;

// Component interface
interface FileSystemComponent {
    void print();
}

// Leaf class
class File implements FileSystemComponent {
    private final String name;

    File(String name) {
        this.name = name;
    }

    @Override
    public void print() {
        System.out.println("File: " + name);
    }
}

// Composite class
class Directory implements FileSystemComponent {
    private final String name;
    private final List<FileSystemComponent> components;

    Directory(String name) {
        this.name = name;
        this.components = new ArrayList<>();
    }

    void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    @Override
    public void print() {
        System.out.println("Directory: " + name);
        for (FileSystemComponent component : components) {
            component.print();
        }
    }
}

// Client class
public class CompositePatternExample {
    public static void main(String[] args) {
        // Create files
        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");

        // Create subdirectory
        Directory subDirectory = new Directory("Subdirectory");
        File file3 = new File("file3.txt");
        subDirectory.addComponent(file3);

        // Create root directory
        Directory root = new Directory("Root");
        root.addComponent(file1);
        root.addComponent(file2);
        root.addComponent(subDirectory);

        // Print the file system hierarchy
        root.print();
    }
}
