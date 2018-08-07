package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println("enter field size (9, 81)");

        Field field = new Field(3);

        field.showField();

        PlayerList playerList = new PlayerList();

        int i = 1;


        if (playerList.gameMode() == 1) {
            Player player = playerList.getPlayerList().getFirst();

            while (true) {
                System.out.println("enter coordinates of cell");

                player.turn(player.getName(), field);

                conclusion(field);

                if (i % 2 == 1)
                    player = playerList.getPlayerList().getLast();
                else
                    player = playerList.getPlayerList().getFirst();

                i++;
            }

        } else {
            Player player = playerList.getPlayerList().getFirst();
            System.out.println("enter difference (0/1)");
            //int dif = in.nextInt();

            while (true) {
                System.out.println("enter coordinates of cell");

                player.turn(field);


                conclusion(field);


                AI.turn(field);


                conclusion(field);

            }
        }
    }

    private static void conclusion(Field field) {
        field.showField();
        Checker.checkWin(field);
    }
}
