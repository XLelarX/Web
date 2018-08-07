package ru.lelar;

import org.reflections.*;

public class Main {
    public static void main(String[] args) {
        Reflections r = new Reflections();
        for (String clazz : r.getAllTypes())
            System.out.println(clazz);;

        System.err.println("get");
    }
}
