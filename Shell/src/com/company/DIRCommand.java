package com.company;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Objects;

@Command
public class DIRCommand extends DIR implements ICommand {
    private static final String NAME = "dir";
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy HH:mm");

    @Override
    public void execute() {
        int files = 0;
        int folders = 0;

        for (File element : Objects.requireNonNull(getFile().listFiles())) {
            String type = "\t";
            String length = "";

            if (element.isDirectory()) {
                type = "<DIR>";
                folders++;
            } else {
                length += element.length();
                files++;
            }

            System.out.println(sdf.format(element.lastModified()) + "\t" + type + "\t" + length + "\t" + element.getName());
        }

        System.out.format("\t%d файлов\n\t%d папок %d байт свободно \n",files, folders, getFile().getFreeSpace());
    }

    @Override
    public String getName() {
        return NAME;
    }
}
