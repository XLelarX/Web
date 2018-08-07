package com.company;


import javafx.scene.effect.Reflection;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    private static final String DO_YOU_WANT_LOAD_LAST_GAME = "do you want load last game?\n 1.Yes\n 2.No";
    private static final String ENTER_CORRECT_DIFFICULTY = "enter correct difficulty (1/2)";
    private static final String ENTER_COORDINATES_OF_CELL = "enter coordinates of cell";
    private static final String ENTER_DIFFERENCE = "enter difference (1/2)";

    private static Scanner in = new Scanner(System.in);

    private static Field field = new Field();

    public static void main(String[] args) {

        PlayerList playerList = new PlayerList();

        int i = 1;

        System.out.println(DO_YOU_WANT_LOAD_LAST_GAME);

        if (in.nextInt() == 1) {
            try {
                field.loadLastGame();
            } catch (MExc e) {
                System.out.println(e.getMessage());
            }
        }

        field.showField();

        if (playerList.gameMode() == 1) {
//            field.setMode();
            Player player = playerList.getPlayerList().getFirst();

            while (true) {
                System.out.println(ENTER_COORDINATES_OF_CELL);

                field.turn(player.getName());

                conclusion();

                if (i % 2 == 1)
                    player = playerList.getPlayerList().getLast();
                else
                    player = playerList.getPlayerList().getFirst();

                i++;
            }

        } else {
            Player player = playerList.getPlayerList().getFirst();
            System.out.println(ENTER_DIFFERENCE);

            while (true)
                try {
                    checkDifficulty();
                    break;
                } catch (MExc e) {
                    System.err.println(e.getMessage());
                }

            while (true) {
                System.out.println(ENTER_COORDINATES_OF_CELL);

                field.turn(player.getName());

                conclusion();
                System.out.println();
                field.turn();

                conclusion();
            }
        }
    }

    private static void conclusion() {
        field.showField();
        field.checkWin();
        try {
            field.autoSave();
        } catch (MExc e) {
            System.err.println(e.getMessage());
        }
    }

    private static void checkDifficulty() throws MExc {
        int dif;

        try {
            dif = in.nextInt();
        } catch (InputMismatchException e) {
            in.next();
            throw new MExc(ENTER_CORRECT_DIFFICULTY);
        }

        String a = "" + dif;

        if (a.matches("[1,2]"))
            field.setDif(dif);
        else throw new MExc(ENTER_CORRECT_DIFFICULTY);
    }
}
