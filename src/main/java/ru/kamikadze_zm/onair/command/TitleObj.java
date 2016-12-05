package ru.kamikadze_zm.onair.command;

import java.util.Objects;
import ru.kamikadze_zm.onair.OnAirParserException;
import ru.kamikadze_zm.onair.command.parameter.Duration;
import ru.kamikadze_zm.onair.command.parameter.Fade;
import ru.kamikadze_zm.onair.command.parameter.util.ParameterParser;

public abstract class TitleObj extends Command {

    private static final String DEFAULT_DURATION = "0:00:00.01";
    
    private final String objectName;
    private final Duration duration;
    private final Fade fadeIn;

    public TitleObj(CommandKey key, String command) {
        super(key);
        this.objectName = ParameterParser.getTitleObjName(command);
        if (this.objectName == null) {
            throw new OnAirParserException("Отсутствует отсутствует название титровального объекта");
        }
        Duration duration = ParameterParser.getDuration(command);
        if (duration != null) {
            this.duration = duration;
        } else {
            this.duration = new Duration(DEFAULT_DURATION);
        }
        this.fadeIn = ParameterParser.getFade(command);
//        if (this.fadeIn == null) {
//            throw new RuntimeException("Отсутствует время появления");
//        }
    }
    
    public TitleObj(CommandKey key, String objectName, Duration duration, Fade fadeIn) {
        super(key);
        if (objectName == null) {
            throw new OnAirParserException("Отсутствует отсутствует название титровального объекта");
        }
        if (duration == null) {
            duration = new Duration(DEFAULT_DURATION);
        }
        this.objectName = objectName;
        this.duration = duration;
        this.fadeIn = fadeIn;
    }

    @Override
    public String toSheduleRow() {
        StringBuilder sb = new StringBuilder(commandKey.getKey()).append(" ");
        sb.append(objectName).append(" ")
                .append(duration.toString()).append(" ");
        if (fadeIn != null) {
            sb.append(fadeIn.toString());
        }
        return sb.toString();
    }

//    @Override
//    public int hashCode() {
//        int hash = 5;
//        hash = 11 * hash + Objects.hashCode(this.objectName);
//        hash = 11 * hash + Objects.hashCode(this.duration);
//        hash = 11 * hash + Objects.hashCode(this.fadeIn);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final TitleObj other = (TitleObj) obj;
//        if (!Objects.equals(this.objectName, other.objectName)) {
//            return false;
//        }
//        if (!Objects.equals(this.duration, other.duration)) {
//            return false;
//        }
//        if (!Objects.equals(this.fadeIn, other.fadeIn)) {
//            return false;
//        }
//        return true;
//    }
}
