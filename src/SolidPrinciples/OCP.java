package SolidPrinciples;

/*
*  The Open-Closed Principle (SolidPrinciples.OCP) states that software entities (classes, modules, functions, etc.)
* should be open for extension but closed for modification. In other words, you should be able to
* add new functionality or behaviors without altering existing code.
*  This helps maintain the stability of existing systems while allowing for easy expansion.
The SolidPrinciples.Payment class is the base class that follows the SolidPrinciples.OCP by providing a default pay method.
* Subclasses such as SolidPrinciples.CreditCardPayment and SolidPrinciples.PayPalPayment extend the SolidPrinciples.Payment class and provide specific
* implementations for payment processing.
 If you want to add a new payment method, you can create a new subclass and override the pay method.
* */
class PaymentProcessor {
    public void processPayment(Payment payment) {
        payment.pay();
    }
}

//without using ocp:
/*
  class SolidPrinciples.PaymentProcessor {
    public void processPayment(SolidPrinciples.Payment payment) {
        if (payment instanceof SolidPrinciples.CreditCardPayment) {
            // Process credit card payment
        } else if (payment instanceof SolidPrinciples.PayPalPayment) {
            // Process PayPal payment
        } else if (payment instanceof BitcoinPayment) {
            // Process Bitcoin payment (newly added)
        }
    }
}
Suppose you want to add a new payment method called BitcoinPayment. Without SolidPrinciples.OCP,
you might be tempted to directly modify the SolidPrinciples.PaymentProcessor class to accommodate the new payment method:
* */

class Payment {
    public void pay() {
        // Default behavior
    }
}

class CreditCardPayment extends Payment {
    @Override
    public void pay() {
        System.out.println("Credit card payment processed");
        // SolidPrinciples.Payment processing logic specific to credit card payments
    }
}

class PayPalPayment extends Payment {
    @Override
    public void pay() {
        System.out.println("PayPal payment processed");
        // SolidPrinciples.Payment processing logic specific to PayPal payments
    }
}
class BitCoinPayment extends Payment {
    @Override
    public void pay() {
        System.out.println("Bitcoin payment processed");
        // SolidPrinciples.Payment processing logic specific to PayPal payments
    }
}

public class OCP {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        Payment creditCardPayment = new CreditCardPayment();
        Payment payPalPayment = new PayPalPayment();
        Payment bitCoinPayment=new BitCoinPayment();

        processor.processPayment(creditCardPayment); // Credit card payment processed
        processor.processPayment(payPalPayment);     // PayPal payment processed
        processor.processPayment(bitCoinPayment);
    }
}
