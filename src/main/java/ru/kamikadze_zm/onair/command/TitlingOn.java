package ru.kamikadze_zm.onair.command;

import ru.kamikadze_zm.onair.command.parameter.Duration;

/**
 *
 * Команда включения титров. В расписании голубая буква Т
 */
public class TitlingOn extends Command {

    public TitlingOn() {
        super(CommandKey.TITLING_ON);
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public Duration getDuration() {
        return new Duration();
    }

    @Override
    public String toSheduleRow() {
        return commandKey.getKey();
    }
}
