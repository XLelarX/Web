package com.company;

@Command
public class ExitCommand implements ICommand {
    private static final String NAME = "exit";

    @Override
    public void execute() {
        System.exit(1);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
