package ru.kamikadze_zm.onair.command;

import ru.kamikadze_zm.onair.command.parameter.Duration;
import ru.kamikadze_zm.onair.command.parameter.Fade;

public class WaitTimeActive extends WaitTime {

    public WaitTimeActive(String command) {
        super(CommandKey.WAIT_TIME_ACTIVE, command);
    }
    
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
