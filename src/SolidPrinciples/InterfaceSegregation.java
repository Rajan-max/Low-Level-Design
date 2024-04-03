package SolidPrinciples;/*
The Interface Segregation Principle (ISP) states that a client should not be forced to depend on interfaces it does not use.
 In other words, an interface should contain only the methods that are relevant to its implementing classes.
 This principle encourages the creation of smaller, focused interfaces that cater to specific behaviors.


* */

interface TextMessageSender {
    void sendTextMessage(String recipient, String message);
}

interface MultimediaMessageSender {
    void sendMultimediaMessage(String recipient, byte[] media);
}

class Phone implements TextMessageSender {
    @Override
    public void sendTextMessage(String recipient, String message) {
        System.out.println("Sending text message to " + recipient + ": " + message);
    }
}

class SmartPhone implements TextMessageSender, MultimediaMessageSender {
    @Override
    public void sendTextMessage(String recipient, String message) {
        System.out.println("Sending text message to " + recipient + ": " + message);
    }

    @Override
    public void sendMultimediaMessage(String recipient, byte[] media) {
        System.out.println("Sending multimedia message to " + recipient);
    }
}

public class InterfaceSegregation {
    public static void main(String[] args) {
        TextMessageSender phone = new Phone();
        SmartPhone smartphone = new SmartPhone();

        phone.sendTextMessage("Alice", "Hello from phone");
        smartphone.sendTextMessage("Bob", "Hello from smartphone");
        smartphone.sendMultimediaMessage("Charlie", new byte[]{0x01, 0x02, 0x03});
    }
}
