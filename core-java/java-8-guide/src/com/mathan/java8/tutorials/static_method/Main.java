package com.mathan.java8.tutorials.static_method;

class  MyClass implements Validator {
    public  static boolean isValidName(String name) {
        System.out.println("Class implementation");
        return !name.isEmpty();
    }
}

public class Main {
    public static void main(String[] args) {
        if(Validator.isValidName("")) {
            System.out.println("Name is valid");
        }else{
            System.out.println("Plese enter valid name");
        }

        MyClass.isValidName("test");
    }
}
