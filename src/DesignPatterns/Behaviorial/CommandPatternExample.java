package DesignPatterns.Behaviorial;

/*
Command is a behavioral design pattern that turns a request into a stand-alone object that contains all information
about the request. This transformation lets you pass requests as a method arguments, delay
or queue a request’s execution, and support undoable operations.
This pattern allows you to decouple sender and receiver of a command, and also
allows for the support of undoable operations.
* */


// Command interface
interface Command {
    void execute();
}

// Receiver class
class Light {
    void turnOn() {
        System.out.println("Light is on");
    }

    void turnOff() {
        System.out.println("Light is off");
    }
}

// Concrete command for turning on the light
class TurnOnLightCommand implements Command {
    private final Light light;

    TurnOnLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

// Concrete command for turning off the light
class TurnOffLightCommand implements Command {
    private final Light light;

    TurnOffLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

// Invoker class
class RemoteControl {
    private Command command;

    void setCommand(Command command) {
        this.command = command;
    }

    void pressButton() {
        command.execute();
    }
}

// Client class
public class CommandPatternExample {
    public static void main(String[] args) {
        // Receiver
        Light light = new Light();

//        // Concrete commands
//        Command turnOnCommand = new TurnOnLightCommand(light);
//        Command turnOffCommand = new TurnOffLightCommand(light);

        // Invoker
        RemoteControl remoteControl = new RemoteControl();

        // Turning on the light
        remoteControl.setCommand(new TurnOnLightCommand(light));
        remoteControl.pressButton();

        // Turning off the light
        remoteControl.setCommand(new TurnOffLightCommand(light));
        remoteControl.pressButton();
    }
}

