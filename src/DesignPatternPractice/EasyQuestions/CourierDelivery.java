package DesignPatternPractice.EasyQuestions;

/*
 * This uses concept of factory design pattern
 *
 * */

enum TransportType {
    TRUCK, TRAIN, SHIP
}


interface Transport {
    void delivery();
}


abstract class LogisticsFactory {
    public static Transport createTransport(TransportType transportType) {
        return switch (transportType) {
            case TRUCK -> new Truck();
            case TRAIN -> new Train();
            case SHIP -> new Ship();
            default -> throw new IllegalArgumentException("Unsupported transport type: " + transportType);
        };
    }
}

class Truck implements Transport {
    @Override
    public void delivery() {
        System.out.println("Delivery By Road Ways");
    }
}

class Train implements Transport {
    @Override
    public void delivery() {
        System.out.println("Delivery By Rail Ways");
    }
}

class Ship implements Transport {
    @Override
    public void delivery() {
        System.out.println("Delivery by Water Ways");
    }
}

class RoadLogistics extends LogisticsFactory {
}

class RailLogistics extends LogisticsFactory {
}

class WaterLogistics extends LogisticsFactory {
}

public class CourierDelivery {
    public static void main(String[] args) {
        Transport ship = LogisticsFactory.createTransport(TransportType.SHIP);
        ship.delivery();
    }
}

