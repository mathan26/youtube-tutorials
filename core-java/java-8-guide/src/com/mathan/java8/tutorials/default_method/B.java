package com.mathan.java8.tutorials.default_method;
@FunctionalInterface
public interface B {
    void print();
    default void hello() {
        System.out.println("Hello from B");
    }
}
