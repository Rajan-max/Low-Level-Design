package DesignPatternPractice.MediumQuestions;

import java.util.List;

enum TrafficLightState {
    RED,
    YELLOW,
    GREEN
}

class TrafficLight {
    private final int redDuration;
    private final int yellowDuration;
    private final int greenDuration;
    private TrafficLightState trafficLightState;

    public TrafficLight(int redDuration, int yellowDuration, int greenDuration, TrafficLightState trafficLightState) {
        this.redDuration = redDuration;
        this.yellowDuration = yellowDuration;
        this.greenDuration = greenDuration;
        this.trafficLightState = trafficLightState;
    }

    public int getRedDuration() {
        return redDuration;
    }

    public int getYellowDuration() {
        return yellowDuration;
    }

    public int getGreenDuration() {
        return greenDuration;
    }

    public TrafficLightState getTrafficLightState() {
        return trafficLightState;
    }

    public void setTrafficLightState(TrafficLightState trafficLightState) {
        this.trafficLightState = trafficLightState;
    }
}

class IntersectionController {
    private final List<TrafficLight> trafficLights;
    int currentIndex;
    boolean emergencyMode;

    public IntersectionController(List<TrafficLight> trafficLights) {
        this.trafficLights = trafficLights;
        this.currentIndex = 0;
        this.emergencyMode = false;
    }

    public void switchSignal() {
        if (emergencyMode) {
            return;
        }
        TrafficLight currentTrafficLight = trafficLights.get(currentIndex);
        System.out.println("Current Signal is: " + currentTrafficLight.getTrafficLightState());
        TrafficLightState nextTrafficState = getNextState(currentTrafficLight.getTrafficLightState());
        System.out.println("Next Signal is: " + nextTrafficState);
        currentTrafficLight.setTrafficLightState(nextTrafficState);
        currentIndex = (currentIndex + 1) % trafficLights.size();
    }

    private TrafficLightState getNextState(TrafficLightState trafficLightState) {
        return switch (trafficLightState) {
            case RED -> TrafficLightState.GREEN;
            case GREEN -> TrafficLightState.YELLOW;
            case YELLOW -> TrafficLightState.RED;
            default -> throw new IllegalStateException("Unexpected state: " + trafficLightState);
        };
    }

    public void setEmergencyMode(boolean emergencyMode) {
        this.emergencyMode = emergencyMode;
    }
}

class TrafficSignalSystem {
    private final List<IntersectionController> intersectionControllers;

    public TrafficSignalSystem(List<IntersectionController> intersectionControllers) {
        this.intersectionControllers = intersectionControllers;
    }

    public void switchAllSignals() {
        for (IntersectionController intersectionController : intersectionControllers) {
            intersectionController.switchSignal();
        }
    }

    public void setEmergencyModeForAll(boolean emergencyModeState) {
        for (IntersectionController intersectionController : intersectionControllers) {
            intersectionController.setEmergencyMode(emergencyModeState);
        }
    }
}

class ControlPanel {
    private final TrafficSignalSystem trafficSignalSystem;

    public ControlPanel(TrafficSignalSystem trafficSignalSystem) {
        this.trafficSignalSystem = trafficSignalSystem;
    }

    public void switchSignals() {
        trafficSignalSystem.switchAllSignals();
    }

    public void setEmergencyMode() {
        trafficSignalSystem.setEmergencyModeForAll(Boolean.TRUE);
    }

    public void relaxEmergencyMode() {
        trafficSignalSystem.setEmergencyModeForAll(Boolean.FALSE);
    }
}


public class TrafficSignalSystemDriver {
    public static void main(String[] args) {
        TrafficLight redLight = new TrafficLight(10, 15, 20, TrafficLightState.RED);
        TrafficLight yellowLight = new TrafficLight(10, 15, 20, TrafficLightState.YELLOW);
        TrafficLight greenLight = new TrafficLight(10, 15, 20, TrafficLightState.GREEN);

        IntersectionController intersectionController = new IntersectionController(List.of(redLight, yellowLight, greenLight));
        TrafficSignalSystem trafficSignalSystem = new TrafficSignalSystem(List.of(intersectionController));

        ControlPanel controlPanel = new ControlPanel(trafficSignalSystem);
        controlPanel.switchSignals();
        controlPanel.setEmergencyMode();

    }
}
