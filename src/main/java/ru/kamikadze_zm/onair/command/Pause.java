package ru.kamikadze_zm.onair.command;

import ru.kamikadze_zm.onair.command.parameter.Duration;
import ru.kamikadze_zm.onair.command.parameter.util.ParameterParser;

public class Pause extends Command {

    private static final String DEFAULT_PAUSE = "0:00:30.00";
    
    private final Duration duration;

    public Pause(String command) {
        super(CommandKey.PAUSE);
        Duration duration = ParameterParser.getDuration(command);
        if (duration != null) {
            this.duration = duration;
        } else {
            this.duration = new Duration(DEFAULT_PAUSE);
        }
    }
    
    public Pause(Duration duration) {
        super(CommandKey.PAUSE);
        if (duration != null) {
            this.duration = duration;
        } else {
            this.duration = new Duration(DEFAULT_PAUSE);
        }
    }

    @Override
    public String toSheduleRow() {
        //pause 0:00:01.00
        StringBuilder sb = new StringBuilder(commandKey.getKey()).append(" ");
        sb.append(duration.toString());
        return sb.toString();
    }
}
