package xyz.pary.onair.command;

import xyz.pary.onair.command.parameter.Duration;
import xyz.pary.onair.command.parameter.Fade;

/**
 *
 * Команда включения титровального объекта
 */
public class TitleObjOn extends TitleObj {

    /**
     *
     * @param command команда в виде строки расписания
     */
    public TitleObjOn(String command) {
        super(CommandKey.TITLE_OBJ_ON, command);
    }

    /**
     *
     * @param objectName название титровального объекта в фигурных скобках {}
     * @param duration длительность
     * @param fadeIn фейд
     */
    public TitleObjOn(String objectName, Duration duration, Fade fadeIn) {
        super(CommandKey.TITLE_OBJ_ON, objectName, duration, fadeIn);
    }

    @Override
    public String getName() {
        return super.getName() + " On";
    }
}
