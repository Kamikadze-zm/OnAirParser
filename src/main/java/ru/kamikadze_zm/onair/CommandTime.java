package ru.kamikadze_zm.onair;

import ru.kamikadze_zm.onair.command.Command;
import ru.kamikadze_zm.onair.command.parameter.Duration;

public class CommandTime {

    private final Command command;
    private final Duration startTime;

    public CommandTime(Command command, Duration startTime) {
        this.command = command;
        this.startTime = startTime;
    }

    public Command getCommand() {
        return command;
    }

    public Duration getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return startTime.toString() + "   " + command.toSheduleRow();
    }
}
