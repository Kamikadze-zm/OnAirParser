package ru.kamikadze_zm.onair.command;

public class UnknownCommand extends Command {

    private final String command;

    public UnknownCommand(String command) {
        super(CommandKey.UNKNOWN_COMMAND);
        this.command = command;
    }

    @Override
    public String toSheduleRow() {
        return command;
    }
}
