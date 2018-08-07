package com.company;

class Checker {

    static void checkWin(Field field) {
        char[][] array = field.getArray();

        checkHorizon(array);

        checkVertical(array);

        checkDiagonal(array);

        checkInvDiagonal(array);
    }

    private static void checkInvDiagonal(char[][] field) {
        int x = 1;
        int y = 1;
        if (field[y][x] == field[y + 1][x - 1] && field[y][x] == field[y - 1][x + 1] && field[y][x] != '-') {
            System.out.println("win");
            System.exit(0);
        }
    }

    private static void checkDiagonal(char[][] field) {
        int y = 1;
        if (field[y][y] == field[y - 1][y - 1] && field[y][y] == field[y + 1][y + 1] && field[y][y] != '-') {
            System.out.println("win");
            System.exit(0);
        }
    }

    private static void checkVertical(char[][] field) {
        int y = 1;
        for (int x = 0; x < field.length; x++)
            if (field[y][x] == field[y - 1][x] && field[y][x] == field[y + 1][x] && field[y][x] != '-') {
                System.out.println("win");
                System.exit(0);
            }
    }

    private static void checkHorizon(char[][] field) {
        int x = 1;
        for (char[] element : field)
            if (element[x] == element[x - 1] && element[x] == element[x + 1] && element[x] != '-') {
                System.out.println("win");
                System.exit(0);
            }
    }

}
