package xyz.pary.onair.command;

import xyz.pary.onair.OnAirParserException;
import xyz.pary.onair.command.parameter.Duration;
import xyz.pary.onair.command.parameter.util.ParameterParser;

/**
 *
 * @author Базовый класс метки
 */
public class Mark extends Command {

    private final String markName;
    private String comment;

    /**
     *
     * @param key ключ команды
     * @param command команда в виде строки расписания
     */
    public Mark(CommandKey key, String command) {
        super(key);
        this.markName = ParameterParser.getTitleObjName(command);
        if (this.markName == null) {
            throw new OnAirParserException("Отсутствует название метки");
        }
        int commentIndex = command.indexOf("0") + 2;
        if (commentIndex < command.length()) {
            this.comment = command.substring(commentIndex);
        }
    }

    /**
     *
     * @param commandKey ключ команды
     * @param markName название метки
     * @param comment комментарий
     */
    public Mark(CommandKey commandKey, String markName, String comment) {
        super(commandKey);
        this.markName = markName;
        this.comment = comment;
    }

    @Override
    public String getName() {
        return comment;
    }

    @Override
    public Duration getDuration() {
        return new Duration();
    }

    @Override
    public String toSheduleRow() {
        //markstart {AdMark} 0 Начало рекламного блока
        StringBuilder sb = new StringBuilder(commandKey.getKey()).append(" ");
        sb.append("{").append(markName).append("}")
                .append(" 0 ");
        if (comment != null) {
            sb.append(comment);
        }
        return sb.toString();
    }
}
