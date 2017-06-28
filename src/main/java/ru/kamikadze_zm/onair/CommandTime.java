package ru.kamikadze_zm.onair;

import ru.kamikadze_zm.onair.command.Command;
import ru.kamikadze_zm.onair.command.parameter.Duration;

/**
 *
 * Содержит команду и время её страта
 */
public class CommandTime {

    private final Command command;
    private final Duration startTime;

    /**
     *
     * @param command команда
     * @param startTime время старта
     */
    public CommandTime(Command command, Duration startTime) {
        this.command = command;
        this.startTime = startTime;
    }

    /**
     *
     * @return команду
     */
    public Command getCommand() {
        return command;
    }

    /**
     *
     * @return время старта команды
     */
    public Duration getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return startTime.toString() + "   " + command.toSheduleRow();
    }
}
