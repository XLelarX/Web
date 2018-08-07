package com.company;


import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        System.out.println(IConvertor.class);

        Reflections reflections = new Reflections();

        System.out.println(reflections.getSubTypesOf(IConvertor.class));

        reflections = new Reflections(reflections.getSubTypesOf(IConvertor.class));

        List<IConvertor> lc = new ArrayList<>();

        for (Class clazz : reflections.getTypesAnnotatedWith(Convertor.class))
            lc.add((IConvertor) clazz.newInstance());

        for (IConvertor clazz : lc)
            clazz.convert();

    }
}

