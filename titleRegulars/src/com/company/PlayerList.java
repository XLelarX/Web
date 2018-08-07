package com.company;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

class PlayerList {
    private static final String CHOOSE_GAME_MODE = "choose game mode : \n 1.PVP\n 2.PVE";
    private static final String ENTER_CORRECT_GAME_MODE = "enter correct game mode : \n 1.PVP\n 2.PVE";
    private LinkedList<Player> playerList = new LinkedList<>();

    private static Scanner in = new Scanner(System.in);


    int gameMode() {

        System.out.println(CHOOSE_GAME_MODE);
        while (true) {
            try {
                return chooseGameMode();
            } catch (MExc e) {
                System.err.println(ENTER_CORRECT_GAME_MODE);
            }
        }
    }

    private void isPVP() {
        playerList.add(new Player(Name.PLAYER1));
        playerList.add(new Player(Name.PLAYER2));
    }

    private void isPVE() {
        playerList.add(new Player(Name.PLAYER1));
        playerList.add(new Player(Name.AI));
    }

    private int chooseGameMode() throws MExc {
        try {
            switch (in.nextInt()) {
                case 1:
                    isPVP();
                    return 1;
                case 2:
                    isPVE();
                    return 2;
                default:
                    throw new MExc();
            }
        } catch (InputMismatchException e) {
            in.next();
            throw new MExc();
        }
    }

    LinkedList<Player> getPlayerList() {
        return playerList;
    }
}
