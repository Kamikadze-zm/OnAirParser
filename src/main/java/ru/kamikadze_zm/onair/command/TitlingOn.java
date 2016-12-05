package ru.kamikadze_zm.onair.command;

public class TitlingOn extends Command {

    public TitlingOn() {
        super(CommandKey.TITLING_ON);
    }

    @Override
    public String toSheduleRow() {
        return commandKey.getKey();
    }
}
