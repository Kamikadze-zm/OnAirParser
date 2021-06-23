package xyz.pary.onair.command;

import xyz.pary.onair.command.parameter.Duration;

/**
 *
 * Команда "Ждать оператора"
 */
public class WaitOperator extends Command {

    private String comment;

    //wait operator 0 Comment
    /**
     *
     * @param command команда в виде строки расписания
     */
    public WaitOperator(String command) {
        super(CommandKey.WAIT_OPERATOR);
        this.comment = command.substring(16);
    }

    /**
     *
     * @param comment комментарий
     * @param isComment бесполезный параметр
     */
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
