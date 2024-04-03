package SolidPrinciples;//Dependency Inversion Principle
/*
This means that a particular class should not depend directly on another class, but on an abstraction (interface) of this class.
Applying this principle reduces dependency on specific implementations and makes our code more reusable.

In this improved version, the SolidPrinciples.NotificationService depends on the SolidPrinciples.NotificationSender interface rather than concrete
implementations. Now, you can easily switch between email and SMS senders without modifying the SolidPrinciples.NotificationService class.
This adheres to the Dependency Inversion Principle.
Using interfaces or abstract classes to define abstractions between high-level and low-level modules is a key concept
 in applying SolidPrinciples.DIP. It allows for more flexibility in changing
 implementations without affecting the overall structure of the code.
* */

/*
 Without applying SolidPrinciples.DIP:
 class SolidPrinciples.EmailSender {
    public void sendEmail(String recipient, String message) {
        // Code to send email
    }
 }

 class SolidPrinciples.NotificationService {
    private SolidPrinciples.EmailSender emailSender;

    public SolidPrinciples.NotificationService() {
        this.emailSender = new SolidPrinciples.EmailSender();
    }

    public void sendNotification(String recipient, String message) {
        emailSender.sendEmail(recipient, message);
    }
}
In this example, the SolidPrinciples.NotificationService is directly dependent on the concrete SolidPrinciples.EmailSender class,
violating the SolidPrinciples.DIP. If we decide to change the notification channel from email to SMS,
we would need to modify the SolidPrinciples.NotificationService class, which is not ideal.

* */


interface NotificationSender {
    void send(String recipient, String message);
}

class EmailSender implements NotificationSender {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Email Sender: ");
        System.out.println(recipient +" "+ message);
        // Code to send email
    }
}

class SMSSender implements NotificationSender {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Sms Sender");
        System.out.println(recipient +" "+ message);
        // Code to send SMS
    }
}

class NotificationService {
    private NotificationSender sender;

    public NotificationService(NotificationSender sender) {
        this.sender = sender;
    }

    public void sendNotification(String recipient, String message) {
        sender.send(recipient, message);
    }
}

public class DIP {
    public static void main(String[] args) {
        NotificationService service=new NotificationService(new EmailSender());
        service.sendNotification("rajan","Hello");

    }
}
