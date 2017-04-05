package ru.kamikadze_zm.onair.command;

import ru.kamikadze_zm.onair.command.parameter.Duration;

public class WaitOperator extends Command {

    private String comment;

    //wait operator 0 Comment
    public WaitOperator(String command) {
        super(CommandKey.WAIT_OPERATOR);
        this.comment = command.substring(16);
    }

    public WaitOperator(String comment, boolean isComment) {
        super(CommandKey.WAIT_OPERATOR);
        this.comment = comment;
    }

    @Override
    public String getName() {
        return comment;
    }

    @Override
    public Duration getDuration() {
        return new Duration();
    }

    @Override
    public String toSheduleRow() {
        return commandKey.getKey() + " 0 " + comment;
    }
}
