package xyz.pary.onair.command;

import xyz.pary.onair.command.parameter.Duration;
import xyz.pary.onair.command.parameter.Fade;

/**
 *
 * Команда активного ожидания заданного времени
 */
public class WaitTimeActive extends WaitTime {

    /**
     *
     * @param command команда в виде строки расписания
     */
    public WaitTimeActive(String command) {
        super(CommandKey.WAIT_TIME_ACTIVE, command);
    }

    /**
     *
     * @param time время старта
     * @param fadeOut фейд
     * @param comment комментарий
     */
    public WaitTimeActive(Duration time, Fade fadeOut, String comment) {
        super(CommandKey.WAIT_TIME_ACTIVE, time, fadeOut, comment);
    }

    @Override
    public String toSheduleRow() {
        //wait time 7:00:00.00 [5.00] active comment
        StringBuilder sb = new StringBuilder(commandKey.getKey()).append(" ");
        sb.append(time.toString()).append(" ")
                .append(fadeOut.toString()).append(" ")
                .append("active ");
        if (comment != null) {
            sb.append(comment);
        }
        return sb.toString();
    }
}
