package com.company;

import java.util.LinkedList;
import java.util.Scanner;

class PlayerList {
    private LinkedList<Player> playerList = new LinkedList<>();

    int gameMode() {
        Scanner in = new Scanner(System.in);
        System.out.println("choose game mode : \n 1.isPVP\n 2.isPVE");
        switch (in.nextInt()) {
            case 1:
                isPVP();
                return 1;
            case 2:
                isPVE();
                return 2;
            default:
                return -1;
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

    LinkedList<Player> getPlayerList() {
        return playerList;
    }
}
