package com.mathan.java8.tutorials.default_method;

public interface A {
    default void hello() {
        System.out.println("Hello from A");
    }

    static void displayFromA(){
        System.out.println("Display from A");
    }
}
