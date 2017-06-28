package ru.kamikadze_zm.onair.command;

import ru.kamikadze_zm.onair.OnAirParserException;
import ru.kamikadze_zm.onair.command.parameter.Duration;
import ru.kamikadze_zm.onair.command.parameter.Fade;
import ru.kamikadze_zm.onair.command.parameter.IFade;
import ru.kamikadze_zm.onair.command.parameter.util.ParameterParser;

/**
 *
 * Полноэкранный титр – фильм с прозрачностью
 */
public class TitleMovie extends Command implements IFade {

    private static final String DEFAULT_FADE = "0.10";

    private final Duration duration;
    private final Fade fadeIn;
    private final String fileName;

    /**
     *
     * @param command команда в виде строки расписания
     */
    public TitleMovie(String command) {
        super(CommandKey.TITLE_MOVIE);
        this.duration = ParameterParser.getDuration(command);
        if (this.duration == null) {
            throw new OnAirParserException("Отсутствует длительность");
        }
        Fade fadeIn = ParameterParser.getFade(command);
        if (fadeIn != null) {
            this.fadeIn = fadeIn;
        } else {
            this.fadeIn = new Fade(DEFAULT_FADE);
        }
        this.fileName = ParameterParser.getFileName(command);
        if (this.fileName == null) {
            throw new OnAirParserException("Отсутствует имя файла");
        }
    }

    /**
     *
     * @param duration длительность
     * @param fadeIn фейд
     * @param fileName название файла
     */
    public TitleMovie(Duration duration, Fade fadeIn, String fileName) {
        super(CommandKey.TITLE_MOVIE);
        if (duration == null) {
            throw new OnAirParserException("Отсутствует длительность");
        }
        if (fadeIn == null) {
            fadeIn = new Fade(DEFAULT_FADE);
        }
        if (fileName == null) {
            throw new OnAirParserException("Отсутствует имя файла");
        }
        this.duration = duration;
        this.fadeIn = fadeIn;
        this.fileName = fileName;
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
    public String getName() {
        int i = fileName.lastIndexOf("\\");
        if (i > -1) {
            return fileName.substring(i + 1);
        }
        return fileName;
    }

    @Override
    public String toSheduleRow() {
        //titleMovie 0:00:02.96 [0.10] D:\M_alf\tor.avi
        StringBuilder sb = new StringBuilder(commandKey.getKey()).append(" ");
        sb.append(duration.toString()).append(" ")
                .append(fadeIn.toString()).append(" ")
                .append(fileName);
        return sb.toString();
    }

//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 73 * hash + Objects.hashCode(this.duration);
//        hash = 73 * hash + Objects.hashCode(this.fadeIn);
//        hash = 73 * hash + Objects.hashCode(this.fileName);
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
//        final TitleMovie other = (TitleMovie) obj;
//        if (!Objects.equals(this.fileName, other.fileName)) {
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
