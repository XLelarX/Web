package com.company;

import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {
    private static final String ERROR = "\"%s\" не является внутренней или внешней\nкомандой, исполняемой программой или пакетным файлом.\n";

    private static Scanner in = new Scanner(System.in);

    private static List<ICommand> commandList = new ArrayList<>();

    private static String command;

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        initList();

        while (true) {
            System.out.print(DIR.getPath() + ">");

            try {
                setCommand();
                findClass(command);
            } catch (AppException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void setCommand() throws AppException {
        try {
            command = in.nextLine().replace(" ", "");
            CDCommand.setPath(command.substring(2));
            if (command.startsWith("cd"))
                command = command.substring(0, 2);

        } catch (NoSuchElementException e) {
            throw new AppException();
        }
    }

    private static void findClass(String command) throws InstantiationException, IllegalAccessException, AppException {
        for (ICommand element : commandList)
            if (element.getName().equals(command)) {
                execute(element.getClass().newInstance());
                return;
            }

        throw new AppException(String.format(ERROR, command));
    }

    private static void initList() throws InstantiationException, IllegalAccessException {
        Reflections reflections = new Reflections();

        for (Class clazz : reflections.getSubTypesOf(ICommand.class))
            commandList.add((ICommand) clazz.newInstance());
    }

    private static void execute(ICommand command) {
        for (ICommand element : commandList)
            if (command.getClass().equals(element.getClass())) {
                element.execute();
                return;
            }
    }
}
