package ru.kamikadze_zm.onair.command;

import ru.kamikadze_zm.onair.OnAirParserException;
import ru.kamikadze_zm.onair.command.parameter.Duration;
import ru.kamikadze_zm.onair.command.parameter.Fade;
import ru.kamikadze_zm.onair.command.parameter.IFade;
import ru.kamikadze_zm.onair.command.parameter.IName;
import ru.kamikadze_zm.onair.command.parameter.ITime;
import ru.kamikadze_zm.onair.command.parameter.util.ParameterParser;

public class WaitTime extends Command implements ITime, IFade, IName {

    private static final String DEFAULT_FADE = "5.00";

    private final Duration time;
    private final Fade fadeOut;
    private String comment;

    public WaitTime(String command) {
        super(CommandKey.WAIT_TIME);
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
        int commentIndex = command.indexOf("]") + 2;
        if (commentIndex < command.length()) {
            this.comment = command.substring(commentIndex);
        }
    }

    public WaitTime(Duration time, Fade fadeOut, String comment) {
        super(CommandKey.WAIT_TIME);
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
