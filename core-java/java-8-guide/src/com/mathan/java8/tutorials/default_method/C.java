package com.mathan.java8.tutorials.default_method;

public class C  implements A, B{
    @Override
    public void print() {

    }

    @Override
    public void hello() {
       B.super.hello();
       A.displayFromA();
    }

    public  static void displayFromA() {
        System.out.println("Display from C");
    }

    public static void main(String[] args) {
        C obj = new C();
        obj.hello();
        obj.displayFromA();
        A.displayFromA();
    }
}
