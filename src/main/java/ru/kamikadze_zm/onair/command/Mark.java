package ru.kamikadze_zm.onair.command;

import ru.kamikadze_zm.onair.OnAirParserException;
import ru.kamikadze_zm.onair.command.parameter.IName;
import ru.kamikadze_zm.onair.command.parameter.util.ParameterParser;

public class Mark extends Command implements IName {

    private final String markName;
    private String comment;

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
    public String toSheduleRow() {
        //markstart {AdMark} 0 Начало рекламного блока
        StringBuilder sb = new StringBuilder(commandKey.getKey()).append(" ");
        sb.append(markName).append(" 0 ");
        if (comment != null) {
            sb.append(comment);
        }
        return sb.toString();
    }
}
