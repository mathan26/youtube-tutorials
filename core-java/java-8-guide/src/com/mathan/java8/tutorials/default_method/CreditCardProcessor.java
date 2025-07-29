package com.mathan.java8.tutorials.default_method;

public class CreditCardProcessor implements PaymentProcessor{
    @Override
    public void pay(double amount) {
        System.out.println("Paid rupees "+amount+ " using Credit card");
        logTransaction(amount);
    }
}
