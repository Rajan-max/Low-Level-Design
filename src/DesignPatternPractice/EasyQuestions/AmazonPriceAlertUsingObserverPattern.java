package DesignPatternPractice.EasyQuestions;


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
class StockManager {
    private int stock;

    public void updateStock(int newStock) {
        stock += newStock;
    }

    public int getStock() {
        return stock;
    }
    public void resetStock() {
        stock = 0;
    }
}

class IphoneStock implements Observable {
    private final List<Observer> observerList;
    private final StockManager stockManager;

    public IphoneStock() {
        this.observerList = new ArrayList<>();
        this.stockManager = new StockManager();
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
        stockManager.updateStock(newStock);
        if (stockManager.getStock() > 0) {
            notifyAllObserver();
        }
        stockManager.resetStock();
    }

    @Override
    public void notifyAllObserver() {
        for (Observer obj : observerList) {
            obj.update();
        }
    }
}

interface MessageObserver extends Observer {
    void sendAlertMessage(String message);
}

interface EmailObserver extends Observer {
    void sendAlertEmail(String message);
}

class MessageAlert implements MessageObserver {
    private final IphoneStock iphoneStock;
    private final String phoneNum;

    public MessageAlert(IphoneStock iphoneStock, String phoneNum) {
        this.iphoneStock = iphoneStock;
        this.phoneNum = phoneNum;
    }

    @Override
    public void update() {
        String message = "Iphone stock is available. Please check the website.";
        sendAlertMessage(message);
    }

    @Override
    public void sendAlertMessage(String message) {
        System.out.println("Alert Message Sent to: " + phoneNum);
        System.out.println("Message: " + message);
    }
}

class EmailAlert implements EmailObserver {
    private final IphoneStock iphoneStock;
    private final String emailId;

    public EmailAlert(IphoneStock iphoneStock, String emailId) {
        this.iphoneStock = iphoneStock;
        this.emailId = emailId;
    }

    @Override
    public void update() {
        String message = "Iphone stock is available. Please check the website.";
        sendAlertEmail(message);
    }

    @Override
    public void sendAlertEmail(String message) {
        System.out.println("Alert Email Sent to: " + emailId);
        System.out.println("Message: " + message);
    }
}


public class AmazonPriceAlertUsingObserverPattern {
    public static void main(String[] args) {
        IphoneStock iphoneStock = new IphoneStock();
        Observer messageAlertObserver = new EmailAlert(iphoneStock,"jaiswalrajan460@gmail.com");
        Observer emailAlertObserver = new MessageAlert(iphoneStock, "9999999999");

        iphoneStock.addObserver(messageAlertObserver);
        iphoneStock.addObserver(emailAlertObserver);
        iphoneStock.updateStock(10);
        iphoneStock.updateStock(0);
    }
}

