package DesignPatterns;

/*
  The Observer design pattern is a behavioral design pattern where an object, known as the subject, maintains a list of
  dependents, known as observers, that are notified of any changes in the subject's state.
  The pattern is used to define a one-to-many dependency between objects so that when one
  object changes state, all its dependents are notified and updated automatically.

  ************                 **************
  * subject   *    has-a       *  observer   *
  * observable*<-------------- *             *
  *           *                *             *
  *************                ***************
        |                                |\
        |                                | \
        |           observer concrete1   |  \ observer concrete class2
  ***************
  *   subject    *
  *   concrete   *
  *   class      *
  ***************

  Let's go through an example of the Observer pattern in Java. Suppose we have a weather station,
  and we want to notify various displays whenever the weather conditions change.
* */


import java.util.ArrayList;
import java.util.List;

// Subject interface
interface Subject {
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}

// Observer interface
interface Observer {
    void update(float temperature);
}

// Concrete subject
class WeatherStation implements Subject {
    private List<Observer> observers;
    private float temperature;

    public WeatherStation() {
        this.observers = new ArrayList<>();
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }
}

// Concrete observers
class BrowserDisplay implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("Temperature Display: " + temperature + " degrees Celsius");
    }
}

class MobileAppDisplay implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("Mobile App: The current temperature is " + temperature + " degrees Celsius");
    }
}

public class ObserverPatternExample {
    public static void main(String[] args) {
        // Create a weather station
        WeatherStation weatherStation = new WeatherStation();

        // Create observers (displays)
        Observer browserDisplay=new BrowserDisplay();
        Observer mobileAppDisplay=new MobileAppDisplay();

        // Register observers with the subject
        weatherStation.registerObserver(browserDisplay);
        weatherStation.registerObserver(mobileAppDisplay);

        // Simulate a change in temperature
        weatherStation.setTemperature(25.5f);

        // Output:
        // Temperature Display: 25.5 degrees Celsius
        // Mobile App: The current temperature is 25.5 degrees Celsius

        // Unregister an observer (remove the temperature display)
        weatherStation.removeObserver(browserDisplay);

        // Simulate another change in temperature
        weatherStation.setTemperature(30.0f);

        // Output:
        // Mobile App: The current temperature is 30.0 degrees Celsius
    }
}

