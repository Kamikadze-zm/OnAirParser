package xyz.pary.onair.command;

import xyz.pary.onair.command.parameter.Duration;

/**
 *
 * Команда "Исполняться за предыдущим"
 */
public class WaitFollow extends Command {

    private String comment;
    
    //wait follow 0 Comment

    /**
     *
     * @param command команда в виде строки расписания
     */
    public WaitFollow(String command) {
        super(CommandKey.WAIT_FOLLOW);
        this.comment = command.substring(14);
    }
    
    /**
     *
     * @param comment комментарий
     * @param isComment бесполезный параметр
     */
    public WaitFollow(String comment, boolean isComment) {
        super(CommandKey.WAIT_FOLLOW);
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
