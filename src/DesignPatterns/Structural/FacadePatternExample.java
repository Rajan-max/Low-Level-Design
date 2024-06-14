package DesignPatterns.Structural;

/*
The Facade design pattern provides a simplified interface to a complex subsystem.
It defines a higher-level interface that makes the subsystem easier to use.
This pattern is particularly useful when a system is very complex or difficult to understand and
when you want to hide the complexity from the client.
* */

/*
Imagine a home theater system with several components: an Amplifier, a DVD player, a Projector, a Screen, and Lights.
Instead of interacting with each component individually, the client can use a HomeTheaterFacade to
control the whole system with simple methods.
* */

// Amplifier.java
class Amplifier {
    public void on() {
        System.out.println("Amplifier is on.");
    }

    public void off() {
        System.out.println("Amplifier is off.");
    }

    public void setVolume(int level) {
        System.out.println("Amplifier volume set to " + level);
    }
}

// DVDPlayer.java
class DVDPlayer {
    public void on() {
        System.out.println("DVD Player is on.");
    }

    public void off() {
        System.out.println("DVD Player is off.");
    }

    public void play(String movie) {
        System.out.println("Playing movie: " + movie);
    }
}

// Projector.java
class Projector {
    public void on() {
        System.out.println("Projector is on.");
    }

    public void off() {
        System.out.println("Projector is off.");
    }

    public void wideScreenMode() {
        System.out.println("Projector in widescreen mode.");
    }
}

// Screen.java
class Screen {
    public void down() {
        System.out.println("Screen going down.");
    }

    public void up() {
        System.out.println("Screen going up.");
    }
}

// Lights.java
class Lights {
    public void dim(int level) {
        System.out.println("Lights dimmed to " + level + "%");
    }

    public void on() {
        System.out.println("Lights are on.");
    }
}

class HomeTheaterFacade {
    private final Amplifier amp;
    private final DVDPlayer dvd;
    private final Projector projector;
    private final Screen screen;
    private final Lights lights;

    public HomeTheaterFacade() {
        this.amp = new Amplifier();
        this.dvd = new DVDPlayer();
        this.projector = new Projector();
        this.screen = new Screen();
        this.lights = new Lights();
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        lights.dim(10);
        screen.down();
        projector.on();
        projector.wideScreenMode();
        amp.on();
        amp.setVolume(5);
        dvd.on();
        dvd.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting down the movie theater...");
        lights.on();
        screen.up();
        projector.off();
        amp.off();
        dvd.off();
    }
}


public class FacadePatternExample {
    public static void main(String[] args) {
        HomeTheaterFacade homeTheater = new HomeTheaterFacade();

        homeTheater.watchMovie("Inception");
        System.out.println();
        homeTheater.endMovie();
    }
}

