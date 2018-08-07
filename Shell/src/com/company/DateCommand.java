package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@Command
public class DateCommand implements ICommand {
    private static final String ENTER_NEW_DATE = "Введите новую дату (дд-мм-гг) : ";
    private static final String NOW_DATE = "Текущая дата : ";
    private static final String DATE_PATTERN = "(([12]?[0-9])|(0?[1-9])|(3[01])).(([0]?[1-9])|([1][0-2])).([2-9]\\d{3})";
    private static final String WRONG_DATE = "Указана недопустимая дата";
    private static final String NAME = "date";

    private Scanner in = new Scanner(System.in);
    private Date date = new Date();

    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public void execute() {
        System.out.println(NOW_DATE + sdf.format(date));
        while (true) {
            System.out.print(ENTER_NEW_DATE);
            String newDate = in.next();

            if (newDate.matches(DATE_PATTERN)) {
                setDate(newDate);
                return;
            } else System.out.println(WRONG_DATE);
        }
    }

    private void setDate(String str) {
        String[] array = str.split("\\.");
        date.setDate(Integer.parseInt(array[0]));
        date.setMonth(Integer.parseInt(array[1]) - 1);
        date.setYear(Integer.parseInt(array[2]) - 1900);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
