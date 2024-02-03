package DesignPatternPractice;


import java.util.ArrayList;
import java.util.List;

interface Observable {
    void addObserver(Observer obj);

    void removeObserver(Observer obj);

    void notifyAllObserver();
}

interface Observer {
    void update();
}

class IphoneStock implements Observable {
    List<Observer> observerList;
    int stock;

    public IphoneStock() {
        observerList = new ArrayList<>();
        stock = 0;
    }

    @Override
    public void addObserver(Observer obj) {
        observerList.add(obj);
    }

    @Override
    public void removeObserver(Observer obj) {
        observerList.remove(obj);
    }

    void updateStock(int newStock) {
        stock += newStock;
        if (stock > 0) {
            notifyAllObserver();
        }
        stock=0;
    }

    @Override
    public void notifyAllObserver() {
        for (Observer obj : observerList) {
            obj.update();
        }
    }
}

class MessageAlert implements Observer {
    IphoneStock obj;
    String phoneNum;

    public MessageAlert(IphoneStock obj, String phoneNum) {
        this.obj = obj;
        this.phoneNum = phoneNum;
    }

    @Override
    public void update() {
        sendAlertMessage();
    }

    public void sendAlertMessage() {
        System.out.println("Alert Message Sent to: " + phoneNum);
        System.out.println("Message:" + "Iphone Stock update to: " + obj.stock);
    }
}

class EmailAlert implements Observer {

    IphoneStock obj;
    String emailId;

    public EmailAlert(IphoneStock obj, String emailId) {
        this.obj = obj;
        this.emailId = emailId;
    }

    @Override
    public void update() {
        sendAlertEmail();
    }

    public void sendAlertEmail() {
        System.out.println("Alert Email Sent to: " + emailId);
        System.out.println("Message:" + "Iphone Stock update to: " + obj.stock);
    }
}

public class AmazonPriceAlertUsingObserverPattern {
    public static void main(String[] args) {
        IphoneStock iphoneStock = new IphoneStock();
        Observer messageAlertObserver = new MessageAlert(iphoneStock, "+91-7390918547");
        Observer emailAlertObserver = new EmailAlert(iphoneStock, "jaiswalrajan460@gmail.com");

        iphoneStock.addObserver(messageAlertObserver);
        iphoneStock.addObserver(emailAlertObserver);
        iphoneStock.updateStock(10);
        iphoneStock.updateStock(0);
    }
}
