package DesignPatterns.Behaviorial;

/*
  The Strategy Design Pattern is a behavioral pattern that allows you to define a family of algorithms,
  encapsulate each algorithm in a separate class, and make these classes interchangeable.
  This pattern enables you to choose an algorithm at runtime without altering the context that uses the algorithm.
  It's especially useful when you have different variations of an algorithm or behavior
  and want to switch between them dynamically.
* */

// Strategy interface
interface SortingStrategy {
    void sort(int[] array);
}

// Concrete strategy implementations
class BubbleSort implements SortingStrategy {
    @Override
    public void sort(int[] array) {
        // Implement bubble sort algorithm
        System.out.println("Sorting array using Bubble Sort.");
    }
}

class QuickSort implements SortingStrategy {
    @Override
    public void sort(int[] array) {
        // Implement quick sort algorithm
        System.out.println("Sorting array using Quick Sort.");
    }
}

// Context class
class ArraySorter {
    private SortingStrategy sortingStrategy;

    public void setSortingStrategy(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public void sortArray(int[] array) {
        sortingStrategy.sort(array);
    }
}

public class StrategyDesignPattern {
    public static void main(String[] args) {
        int[] array = { 7, 2, 5, 1, 8 };

        ArraySorter arraySorter = new ArraySorter();

        SortingStrategy bubbleSort = new BubbleSort();
        arraySorter.setSortingStrategy(bubbleSort);
        arraySorter.sortArray(array);

        SortingStrategy quickSort = new QuickSort();
        arraySorter.setSortingStrategy(quickSort);
        arraySorter.sortArray(array);
    }
}
