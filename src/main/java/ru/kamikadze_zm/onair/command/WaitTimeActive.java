package ru.kamikadze_zm.onair.command;

import ru.kamikadze_zm.onair.OnAirParserException;
import ru.kamikadze_zm.onair.command.parameter.Duration;
import ru.kamikadze_zm.onair.command.parameter.Fade;
import ru.kamikadze_zm.onair.command.parameter.util.ParameterParser;

public class WaitTimeActive extends Command {

    private static final String DEFAULT_FADE = "5.00";
    
    private final Duration time;
    private final Fade fadeOut;

    public WaitTimeActive(String command) {
        super(CommandKey.WAIT_TIME_ACTIVE);
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
    }
    
    public WaitTimeActive(Duration time, Fade fadeOut) {
        super(CommandKey.WAIT_TIME_ACTIVE);
        if (time == null) {
            throw new OnAirParserException("Отсутствует время старта");
        }
        if (fadeOut == null) {
            fadeOut = new Fade(DEFAULT_FADE);
        }
        this.time = time;
        this.fadeOut = fadeOut;
    }

    @Override
    public String toSheduleRow() {
        //wait time 7:00:00.00 [5.00] active
        StringBuilder sb = new StringBuilder(commandKey.getKey()).append(" ");
        sb.append(time.toString()).append(" ")
                .append(fadeOut.toString()).append(" ")
                .append("active");
        return sb.toString();
    }
}
