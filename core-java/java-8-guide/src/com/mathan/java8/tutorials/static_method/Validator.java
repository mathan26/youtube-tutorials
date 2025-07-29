package com.mathan.java8.tutorials.static_method;

public interface Validator {
    public  static boolean isValidName(String name) {
        System.out.println("interface implementation");

        return !name.isEmpty();
    }
}
