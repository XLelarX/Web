package com.company;

public class Main {

    public static void main(String[] args) {
        A a = new A();
        System.out.println(A.s);
        A.s = 20;
        A b = new A();
        System.out.println(A.s);
    }
}
