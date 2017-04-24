package ru.kamikadze_zm.onair.command;

import ru.kamikadze_zm.onair.OnAirParserException;
import ru.kamikadze_zm.onair.command.parameter.Duration;
import ru.kamikadze_zm.onair.command.parameter.Fade;
import ru.kamikadze_zm.onair.command.parameter.IFade;
import ru.kamikadze_zm.onair.command.parameter.ITime;
import ru.kamikadze_zm.onair.command.parameter.util.ParameterParser;

public class WaitTime extends Command implements ITime, IFade {

    private static final String DEFAULT_FADE = "5.00";

    protected final Duration time;
    protected final Fade fadeOut;
    protected String comment;

    public WaitTime(String command) {
        this(CommandKey.WAIT_TIME, command);
    }
    
    public WaitTime(CommandKey key, String command) {
        super(key);
        this.time = ParameterParser.getDuration(command);
        if (this.time == null) {
            throw new OnAirParserException("Отсутствует время старта");
        }
        Fade fadeOut = ParameterParser.getFade(command);
        if (fadeOut != null) {
            this.fadeOut = fadeOut;
        } else {
            this.fadeOut = new Fade(DEFAULT_FADE);
        }
        int commentIndex = -1;
        if (CommandKey.WAIT_TIME == key) {
           commentIndex = command.indexOf("]") + 2; 
        } else {
            commentIndex = command.indexOf("]") + 9;
        }
        
        if (commentIndex < command.length() && commentIndex > 0) {
            this.comment = command.substring(commentIndex);
        }
    }

    public WaitTime(Duration time, Fade fadeOut, String comment) {
        this(CommandKey.WAIT_TIME, time, fadeOut, comment);
    }

    protected WaitTime(CommandKey key, Duration time, Fade fadeOut, String comment) {
        super(key);
        if (time == null) {
            throw new OnAirParserException("Отсутствует время старта");
        }
        if (fadeOut == null) {
            fadeOut = new Fade(DEFAULT_FADE);
        }
        this.time = time;
        this.fadeOut = fadeOut;
        this.comment = comment;
    }

    @Override
    public Duration getTime() {
        return time;
    }

    @Override
    public Duration getDuration() {
        return new Duration();
    }

    @Override
    public Fade getFade() {
        return fadeOut;
    }

    @Override
    public String getName() {
        return comment;
    }

    @Override
    public String toSheduleRow() {
        //wait time 17:00:00.00 [5.00] comment
        StringBuilder sb = new StringBuilder(commandKey.getKey()).append(" ");
        sb.append(time.toString()).append(" ")
                .append(fadeOut.toString()).append(" ");
        if (comment != null) {
            sb.append(comment);
        }
        return sb.toString();
    }
}
