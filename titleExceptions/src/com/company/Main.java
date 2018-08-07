package com.company;

import sun.security.util.SecurityConstants;
import sun.swing.SwingAccessor;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class Main {

    private static final String DO_YOU_WANT_LOAD_LAST_GAME = "do you want load last game?\n 1.Yes\n 2.No";
    private static final String ENTER_COORDINATES_OF_CELL = "enter coordinates of cell";
    private static final String ENTER_CORRECT_POINT = "enter correct point";

    private static Scanner in = new Scanner(System.in);

    private static Field field = new Field();

    private static JFrame frame = new JFrame("Game");

    public static void main(String[] args) throws InterruptedException {
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 100);
//        frame.setVisible(true);
//        TimeUnit.SECONDS.sleep(1);
//        JLabel lable = new JLabel("Lable");
//        frame.add(lable);
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                lable.setText("Hey");
//            }
//        });

        Field.PlayerList playerList = field.new PlayerList();
        System.out.println(DO_YOU_WANT_LOAD_LAST_GAME);

        while (true)
            try {
                loadLastGame();
                break;
            } catch (MExc e) {
                System.err.println(e.getMessage());
            }

        field.showField();

        int game = playerList.gameMode();

        if (game == 1) {

            Player player = field.whoseTurn(playerList);

            while (true) {
                System.out.println(ENTER_COORDINATES_OF_CELL);

                field.turn(player.getName());

                conclusion();

                player = field.whoseTurn(playerList);
            }
        } else if (game == 2) {
            Player player = playerList.getPlayerList().getFirst();


            while (true)
                try {
                    field.checkDifficulty();
                    break;
                } catch (MExc e) {
//                    System.err.println(e.getMessage());
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

    private static void loadLastGame() throws MExc {
        int mode;

        try {
            mode = in.nextInt();
        } catch (InputMismatchException e) {
            in.next();
            throw new MExc(ENTER_CORRECT_POINT);
        }

        String a = "" + mode;

        if (a.matches("1")) {
            field.loadLastGame();
        } else if (a.matches("[^2]"))
            throw new MExc(ENTER_CORRECT_POINT);
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
}
