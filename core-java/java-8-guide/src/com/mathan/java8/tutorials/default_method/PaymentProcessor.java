package com.mathan.java8.tutorials.default_method;

public interface PaymentProcessor {
    void pay(double amount);

    default void logTransaction(double amount) {
        System.out.println("Transaction of amount "+amount+" has been logged");
    }
}
