package com.company;

class Cell {
    private char x;
    private char y;

    Cell() {

    }

    Cell(char x, char y) {
        this.x = x;
        this.y = y;
    }

    public char getX() {
        return x;
    }

    public void setX(char x) {
        this.x = x;
    }

    public char getY() {
        return y;
    }

    public void setY(char y) {
        this.y = y;
    }
}
