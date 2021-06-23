package xyz.pary.onair.command;

import xyz.pary.onair.command.parameter.Duration;

/**
 *
 * Неизвестная команда. Остается как есть
 */
public class UnknownCommand extends Command {

    private final String command;

    public UnknownCommand(String command) {
        super(CommandKey.UNKNOWN_COMMAND);
        this.command = command;
    }

    @Override
    public String getName() {
        return command;
    }

    @Override
    public Duration getDuration() {
        return new Duration();
    }

    @Override
    public String toSheduleRow() {
        return command;
    }
}
