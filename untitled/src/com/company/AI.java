package com.company;

import java.util.Random;

class AI {

    static void turn(Field field) {
        char[][] array = field.getArray();
        int size = array.length;

        Random rng = new Random();

        while (true) {
            int x = rng.nextInt(size);
            int y = rng.nextInt(size);
            if (array[y][x] == '-') {
                array[y][x] = 'X';
                break;
            }
        }
    }
}
