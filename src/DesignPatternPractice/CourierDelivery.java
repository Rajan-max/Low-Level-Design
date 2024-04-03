package DesignPatternPractice;

/*
 * This uses concept of factory design pattern
 *
 * */

interface Transport {
    void delivery();
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

interface LogisticsFactory {
    Transport createTransport();
}

class RoadLogistics implements LogisticsFactory {
    @Override
    public Transport createTransport() {
        return new Truck();
    }
}

class RailLogistics implements LogisticsFactory {
    @Override
    public Transport createTransport() {
        return new Train();
    }
}

class WaterLogistics implements LogisticsFactory {
    @Override
    public Transport createTransport() {
        return new Ship();
    }
}

public class CourierDelivery {
    public static void main(String[] args) {
        LogisticsFactory roadFactory = new RoadLogistics();
        Transport truck = roadFactory.createTransport();
        truck.delivery();

        LogisticsFactory railFactory = new RailLogistics();
        Transport train = railFactory.createTransport();
        train.delivery();

        LogisticsFactory waterFactory = new WaterLogistics();
        Transport ship = waterFactory.createTransport();
        ship.delivery();
    }
}
