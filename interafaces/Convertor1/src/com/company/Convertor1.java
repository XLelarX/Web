package com.company;

@Convertor
public class Convertor1 implements IConvertor{

    @Override
    public void convert() {
        System.err.println("Convertor1");
    }
}