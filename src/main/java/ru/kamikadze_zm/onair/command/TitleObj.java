package ru.kamikadze_zm.onair.command;

import java.util.Objects;
import ru.kamikadze_zm.onair.OnAirParserException;
import ru.kamikadze_zm.onair.command.parameter.Duration;
import ru.kamikadze_zm.onair.command.parameter.Fade;
import ru.kamikadze_zm.onair.command.parameter.IFade;
import ru.kamikadze_zm.onair.command.parameter.util.ParameterParser;

/**
 *
 * Базовый класс титровального объекта
 */
public abstract class TitleObj extends Command implements IFade {

    private static final String DEFAULT_DURATION = "0:00:00.01";

    protected final String objectName;
    private final Duration duration;
    private final Fade fadeIn;

    /**
     *
     * @param key ключ команды
     * @param command команда в виде строки расписания
     */
    public TitleObj(CommandKey key, String command) {
        super(key);
        this.objectName = ParameterParser.getTitleObjName(command).trim();
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

    /**
     *
     * @param key ключ команды
     * @param objectName название титровального объекта
     * @param duration длительность
     * @param fadeIn фейд
     */
    public TitleObj(CommandKey key, String objectName, Duration duration, Fade fadeIn) {
        super(key);
        if (objectName == null) {
            throw new OnAirParserException("Отсутствует название титровального объекта");
        }
        if (duration == null) {
            duration = new Duration(DEFAULT_DURATION);
        }
        this.objectName = objectName.trim();
        this.duration = duration;
        this.fadeIn = fadeIn;
    }

    @Override
    public String getName() {
        return objectName;
    }

    @Override
    public Duration getDuration() {
        return duration;
    }

    @Override
    public Fade getFade() {
        return fadeIn;
    }

    @Override
    public String toSheduleRow() {
        StringBuilder sb = new StringBuilder(commandKey.getKey()).append(" ");
        sb.append("{").append(objectName).append("} ")
                .append(duration.toString());
        if (fadeIn != null) {
            sb.append(" ").append(fadeIn.toString());
        }
        return sb.toString();
    }
}
