package com.company;

import java.util.Arrays;

class Field {
    private char array[][];
    private int size;

    Field() {
        size = 3;
        array = new char[size][size];
        for (int i = 0; i < size; i++)
            Arrays.fill(array[i], '-');
    }

    Field(int size) {
        this.size = size;
        array = new char[size][size];
        for (int i = 0; i < size; i++)
            Arrays.fill(array[i], '-');
    }

    void showField() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                System.out.print(array[i][j] + " ");
            System.out.println();
        }
    }




//        if (dif == 1) {
//            if (array[1][1] == '-') {
//                array[1][1] = 'X';
//            } else {
//
//                // checkWinAI();
//
//            }
//        }


//    void checkWinAI() {
//        checkHorizonAI();
//
//        checkVerticalAI();
//
//        checkDiagonalAI();
//
//        checkInvDiagonalAI();
//    }
//
//    private void checkInvDiagonalAI() {
//        int x = 1;
//        for (int y = 1; y >= 0; y--) {
//            if (array[y][x] == array[y + 1][x - 1] && array[y][x] != '-') {
//                System.out.println("win");
//                System.exit(0);
//            } else break;
//            x++;
//        }
//    }
//
//    private void checkDiagonalAI() {
//        for (int y = 1; y < size; y++) {
//            if (array[y][y] == array[y - 1][y - 1] && array[y][y] == array[y + 1][y + 1] && array[y][y] != '-') {
//                System.out.println("win");
//                System.exit(0);
//            } else break;
//        }
//    }
//
//    private void checkVerticalAI() {
//        for (int x = 0; x < size; x++)
//            for (int y = 1; y < size; y++)
//                if (array[y][x] == array[y - 1][x] && array[y][x] == array[y + 1][x] && array[y][x] != '-') {
//                    System.out.println("win");
//                    System.exit(0);
//                } else break;
//    }
//
//    private void checkHorizonAI() {
//        for (int y = 0; y < size; y++)
//            for (int x = 1; x < size - 1; x++) {
//                if (array[y][x] == array[y][x - 1] && array[y][x] == array[y][x + 1] && array[y][x] != '-') {
//                    System.out.println("win");
//                    System.exit(0);
//                } else break;
//            }
//    }


    char[][] getArray() {
        return array;
    }
}
