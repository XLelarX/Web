package com.company;

class MExc extends Exception {

    private String msg;

    MExc(String msg) {
        this.msg = msg;
    }

    MExc() {

    }

    public String getMessage() {
        return msg;
    }
}
