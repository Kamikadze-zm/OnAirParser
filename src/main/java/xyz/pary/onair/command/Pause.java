package xyz.pary.onair.command;

import xyz.pary.command.parameter.Duration;
import xyz.pary.onair.command.parameter.util.ParameterParser;

/**
 *
 * Пауза
 */
public class Pause extends Command {
    
    private static final String DEFAULT_PAUSE = "00:00:30.00";
    
    private final Duration duration;
    private final String comment;

    /**
     *
     * @param command команда в виде строки расписания
     */
    public Pause(String command) {
        super(CommandKey.PAUSE);
        //pause 0:03:20.00
        //pause 00:20:20.00 новости
        String durStr = " " + command.substring(6, 17).trim() + " ";
        Duration duration = ParameterParser.getDuration(durStr);
        if (duration != null) {
            this.duration = duration;
            this.comment = command.substring(17).trim();
        } else {
            this.duration = new Duration(DEFAULT_PAUSE);
            this.comment = "";
        }
    }

    /**
     *
     * @param duration длительность паузы
     * @param comment комментарий
     */
    public Pause(Duration duration, String comment) {
        super(CommandKey.PAUSE);
        if (duration != null) {
            this.duration = duration;
        } else {
            this.duration = new Duration(DEFAULT_PAUSE);
        }
        if (comment != null) {
            this.comment = comment;
        } else {
            this.comment = "";
        }
    }
    
    @Override
    public String getName() {
        return "";
    }
    
    @Override
    public Duration getDuration() {
        return duration;
    }
    
    @Override
    public String toSheduleRow() {
        //pause 0:00:01.00
        StringBuilder sb = new StringBuilder(commandKey.getKey()).append(" ");
        sb.append(duration.toString())
                .append(" ").append(comment);
        return sb.toString();
    }
}
