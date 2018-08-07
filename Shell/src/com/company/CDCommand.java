package com.company;

import java.io.File;

@Command
public class CDCommand extends DIR implements ICommand {
    private static final String ERROR = "Системе не удается найти указанный путь.";
    private static String path = "";
    private static final String NAME = "cd";

    CDCommand() {
    }

    @Override
    public void execute() {
        File a = new File(path);

        if (a.isDirectory())
            setFile(a);
        else System.out.println(ERROR);
    }

    @Override
    public String getName() {
        return NAME;
    }

    public static void setPath(String path) {
        if (path.startsWith("C:\\"))
            CDCommand.path = path;
        else CDCommand.path = DIR.getPath() + "\\" +  path;
    }

    public static String getPath() {
        return path;
    }
}
