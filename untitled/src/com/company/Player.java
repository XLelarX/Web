package com.company;

import java.util.Scanner;

class Player {
    private Name name;

    Player(Name name) {
        this.name = name;
    }

    void turn(Name player, Field field) {
        char mark;

        if (player == Name.PLAYER1)
            mark = 'O';
        else
            mark = 'X';

        turnPlayer( mark, field);

    }

    void turn(Field field) {
        char mark = 'O';

        turnPlayer(mark, field);
    }

    private void turnPlayer(char mark, Field field) {
        Scanner in = new Scanner(System.in);
        char[][] array = field.getArray();

        int y = in.nextInt();
        int x = in.nextInt();
        while (true)
            if (array[y][x] == '-') {
                array[y][x] = mark;
                break;
            } else {
                System.out.println("enter correct coordinates");
                y = in.nextInt();
                x = in.nextInt();
            }
    }

    Name getName() {
        return name;
    }

}
