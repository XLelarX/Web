package com.company;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@Command
public class TimeCommand implements ICommand {
    private static final String NOW_TIME = "Текущее время : ";
    private static final String ENTER_NEW_TIME = "Введите новое время : ";
    private static final String TIME_PATTEN = "(([0-1]?[0-9])|(2[0-3]))(:([0-5]?[0-9])){2},?\\d{0,3}";
    private static final String WRONG_TIME = "Указано недопустимое время";
    private static final String NAME = "time";

    private Scanner in = new Scanner(System.in);
    private Date time = new Date();
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss,S");

    @Override
    public void execute() {
        System.out.println(NOW_TIME + sdf.format(time));

        while (true) {
            System.out.print(ENTER_NEW_TIME);
            String str = in.next();

            if (str.matches(TIME_PATTEN)) {
                setTime(str);
                return;
            } else System.out.println(WRONG_TIME);
        }
    }

    private void setTime(String str) {
        String[] array = str.split("[:,]");
        time.setHours(Integer.parseInt(array[0]));
        time.setMinutes(Integer.parseInt(array[1]));
        time.setSeconds(Integer.parseInt(array[2]));
    }

    @Override
    public String getName() {
        return NAME;
    }
}
