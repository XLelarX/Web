package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.*;

class Field {
    private static final char CROSS = 'X';
    private static final char EMPTY_CELL = '-';
    private static final char ZERO = 'O';
    private static final String DRAWN = "drawn";
    private static final String WIN = "win";
    private static final String ENTER_CORRECT_COORDINATES = "enter correct coordinates (cell is not empty)";
    private static final String ENTER_CORRECT_ARRAY_COORDINATES = "enter correct coordinates (array)";
    private static final String PROBLEMS_WITH_LOADING_SAVE = "Problems with loading save";
    private static final String PROBLEMS_WITH_SAVING = "Problems with saving";
    private static final String PATHNAME = "C:\\Users\\user\\IdeaProjects\\save.txt";
    private static final String ENTER_CORRECT_DIFFICULTY = "enter correct difficulty (1/2)";
    private static final String ENTER_DIFFERENCE = "enter difference (1/2)";

    private char array[][];

    transient private int size;

    private int dif = 0;
    private int turn = 0;
    private int mode = 0;

    private static Scanner in = new Scanner(System.in);

    class PlayerList {
        private static final String CHOOSE_GAME_MODE = "choose game mode : \n 1.PVP\n 2.PVE";
        private static final String ENTER_CORRECT_GAME_MODE = "enter correct game mode : \n 1.PVP\n 2.PVE";

        private LinkedList<Player> playerList = new LinkedList<>();

        int gameMode() {
            while (true) {
                try {
                    return chooseGameMode();
                } catch (MExc e) {
                    mode = 0;
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

        int chooseGameMode() throws MExc {
            try {
                if (mode == 0) {
                    System.out.println(CHOOSE_GAME_MODE);
                    mode = in.nextInt();
                }

                switch (mode) {
                    case 1:
                        isPVP();
                        return 1;
                    case 2:
                        isPVE();
                        return 2;
                    default:
                        throw new MExc(ENTER_CORRECT_GAME_MODE);
                }

            } catch (InputMismatchException e) {
                in.next();
                throw new MExc(ENTER_CORRECT_GAME_MODE);
            }
        }

        LinkedList<Player> getPlayerList() {
            return playerList;
        }
    }

    Field() {
        size = 3;
        array = new char[size][size];
        for (int i = 0; i < size; i++)
            Arrays.fill(array[i], EMPTY_CELL);
    }

    private Field(char array[][], int dif, int turn, int mode) {
        this.array = array;
        this.dif = dif;
        this.turn = turn;
        this.mode = mode;
    }

    void showField() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                System.out.print(array[i][j] + " ");
            System.out.println();
        }
    }

    public void turn() {
        Random rng = new Random();

        if (dif == 1) {
            fillRandomCell(rng);
        } else {
            if (array[1][1] == EMPTY_CELL) {
                array[1][1] = CROSS;
            } else if (turn == 1) {
                while (true) {
                    int x = rng.nextInt(size);
                    int y = rng.nextInt(size);
                    if ((array[y][x] == EMPTY_CELL) && ((x + y) % 2 == 0)) {
                        array[y][x] = CROSS;
                        break;
                    }
                }
            } else {
                checkForTurn();
            }
        }

        turn++;
    }

    private void fillRandomCell(Random rng) {
        while (true) {
            int x = rng.nextInt(size);
            int y = rng.nextInt(size);
            if (array[y][x] == EMPTY_CELL) {
                array[y][x] = CROSS;
                break;
            }
        }
    }

    private void checkForTurn() {
        Random rng = new Random();

        if (!checkHorizonForAI() && !checkVerticalForAI() && !checkDiagonalForAI() && !checkInvDiagonalForAI()) {
            fillRandomCell(rng);
        }
    }

    private boolean checkInvDiagonalForAI() {
        int x = 1;

        for (int y = array.length - 2; y >= 0; y--, x++) {
            if (array[y][x] == array[y + 1][x - 1] && array[y][x] == ZERO) {
                if (y + 2 < array.length && x - 2 >= 0) {
                    if (array[y + 2][x - 2] == EMPTY_CELL) {
                        array[y + 2][x - 2] = CROSS;
                        return true;
                    }
                } else if (y - 1 >= 0 && x + 1 < array.length) {
                    if (array[y - 1][x + 1] == EMPTY_CELL) {
                        array[y - 1][x + 1] = CROSS;
                        return true;
                    }
                }
            } else if (y + 2 < array.length && x - 2 >= 0 && array[y + 1][x - 1] == EMPTY_CELL) {
                if (array[y][x] == ZERO && array[y + 2][x - 2] == ZERO) {
                    array[y + 1][x - 1] = CROSS;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalForAI() {

        for (int y = 1; y < array.length; y++) {
            if (array[y][y] == array[y - 1][y - 1] && array[y][y] == ZERO) {
                if (y - 2 >= 0) {
                    if (array[y - 2][y - 2] == EMPTY_CELL) {
                        array[y - 2][y - 2] = CROSS;
                        return true;
                    }
                } else if (y + 1 < array.length)
                    if (array[y + 1][y + 1] == EMPTY_CELL) {
                        array[y + 1][y + 1] = CROSS;
                        return true;
                    }
            } else if (y - 2 >= 0 && array[y - 1][y - 1] == EMPTY_CELL) {
                if (array[y][y] == ZERO && array[y - 2][y - 2] == ZERO) {
                    array[y - 1][y - 1] = CROSS;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVerticalForAI() {

        for (int x = 0; x < array.length; x++)
            for (int y = 1; y < array.length; y++)
                if (array[y][x] == array[y - 1][x] && array[y][x] == ZERO) {
                    if (y - 2 >= 0) {
                        if (array[y - 2][x] == EMPTY_CELL) {
                            array[y - 2][x] = CROSS;
                            return true;
                        }
                    } else if (y + 1 < array.length)
                        if (array[y + 1][x] == EMPTY_CELL) {
                            array[y + 1][x] = CROSS;
                            return true;
                        }
                } else if (y - 2 >= 0 && array[y - 1][x] == EMPTY_CELL) {
                    if (array[y][x] == ZERO && array[y - 2][x] == ZERO) {
                        array[y - 1][x] = CROSS;
                        return true;
                    }
                }
        return false;
    }

    private boolean checkHorizonForAI() {

        for (int y = 0; y < array.length; y++)
            for (int x = 1; x < array.length; x++) {
                if (array[y][x] == array[y][x - 1] && array[y][x] == ZERO) {
                    if (x - 2 >= 0) {
                        if (array[y][x - 2] == EMPTY_CELL) {
                            array[y][x - 2] = CROSS;
                            return true;
                        }
                    } else if (x + 1 < array.length)
                        if (array[y][x + 1] == EMPTY_CELL) {
                            array[y][x + 1] = CROSS;
                            return true;
                        }

                } else if (x - 2 >= 0 && array[y][x - 1] == EMPTY_CELL) {
                    if (array[y][x] == ZERO && array[y][x - 2] == ZERO) {
                        array[y][x - 1] = CROSS;
                        return true;
                    }
                }
            }
        return false;
    }

    void checkWin() {

        checkDrawn();

        checkHorizon();

        checkVertical();

        checkDiagonal();

        checkInvDiagonal();
    }

    private void checkDrawn() {
        for (char[] aField : array) {
            for (int j = 0; j < array.length; j++) {
                if (aField[j] == EMPTY_CELL) return;
            }
        }
        System.out.println(DRAWN);

        endGame();
    }

    private void endGame() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(array));

        System.exit(0);
    }

    private void checkInvDiagonal() {
        int x = 1;
        int y = 1;
        if (array[y][x] == array[y + 1][x - 1] && array[y][x] == array[y - 1][x + 1] && array[y][x] != EMPTY_CELL) {
            System.out.println(WIN);
            endGame();
        }
    }

    private void checkDiagonal() {
        int y = 1;
        if (array[y][y] == array[y - 1][y - 1] && array[y][y] == array[y + 1][y + 1] && array[y][y] != EMPTY_CELL) {
            System.out.println(WIN);
            endGame();
        }
    }

    private void checkVertical() {
        int y = 1;
        for (int x = 0; x < array.length; x++)
            if (array[y][x] == array[y - 1][x] && array[y][x] == array[y + 1][x] && array[y][x] != EMPTY_CELL) {
                System.out.println(WIN);
                endGame();
            }
    }

    private void checkHorizon() {
        int x = 1;
        for (char[] element : array)
            if (element[x] == element[x - 1] && element[x] == element[x + 1] && element[x] != EMPTY_CELL) {
                System.out.println(WIN);
                endGame();
            }
    }

    void turn(Name player) {
        char mark;

        if (player == Name.PLAYER1)
            mark = ZERO;
        else
            mark = CROSS;
        while (true)
            try {
                turnPlayer(mark);
                return;
            } catch (MExc e) {
                System.err.println(e.getMessage());
            }
    }

    private void turnPlayer(char mark) throws MExc {
        int x;
        int y;

        while (true)
            try {
                y = in.nextInt();
                x = in.nextInt();


                while (true)
                    try {
                        if (array[y][x] == EMPTY_CELL) {
                            array[y][x] = mark;
                            return;
                        } else
                            throw new MExc(ENTER_CORRECT_COORDINATES);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new MExc(ENTER_CORRECT_ARRAY_COORDINATES);
                    }

            } catch (InputMismatchException e) {
                in.next();
                throw new MExc(ENTER_CORRECT_ARRAY_COORDINATES);
            }
    }

    public void autoSave() throws MExc {
        try {
            FileOutputStream fos = new FileOutputStream(new File(PATHNAME));

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    fos.write(array[i][j]);
                }
            }

            fos.write('0' + dif);

            fos.write('0' + turn);

            fos.write('0' + mode);

//            Gson gson = new GsonBuilder().setPrettyPrinting().create();


//            System.out.println(gson.toJson(new Field(array, dif, turn, mode)));

            fos.close();

        } catch (IOException e) {
            throw new MExc(PROBLEMS_WITH_SAVING);
        }
    }

    public void loadLastGame() throws MExc {
        try {
            FileInputStream fis = new FileInputStream(new File(PATHNAME));

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    array[i][j] = (char) fis.read();
                }
            }

            dif = fis.read() - '0';

            turn = fis.read() - '0';

            mode = fis.read() - '0';

            fis.close();

        } catch (IOException e) {
            throw new MExc(PROBLEMS_WITH_LOADING_SAVE);
        }
    }

    void checkDifficulty() throws MExc {
        int d = 0;

        try {
            if (dif == 0) {
                System.out.println(ENTER_DIFFERENCE);

                d = in.nextInt();
            } else return;

            String a = "" + d;

            if (a.matches("[1,2]"))
                dif = d;
            else throw new MExc(ENTER_CORRECT_DIFFICULTY);

        } catch (InputMismatchException e) {
            in.next();
            throw new MExc(ENTER_CORRECT_DIFFICULTY);
        }
    }

    Player whoseTurn(PlayerList playerList) {
        turn++;
        return turn % 2 == 1 ? playerList.getPlayerList().getLast() : playerList.getPlayerList().getFirst();
    }
}
