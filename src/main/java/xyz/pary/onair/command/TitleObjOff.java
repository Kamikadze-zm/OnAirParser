package xyz.pary.onair.command;

import xyz.pary.onair.command.parameter.Duration;
import xyz.pary.onair.command.parameter.Fade;

/**
 *
 * Команда выключения титровального объекта
 */
public class TitleObjOff extends TitleObj {

    /**
     *
     * @param command команда в виде строки расписания
     */
    public TitleObjOff(String command) {
        super(CommandKey.TITLE_OBJ_OFF, command);
    }

    /**
     *
     * @param objectName название титровального объекта в фигурных скобках {}
     * @param duration длительнсоть
     * @param fadeIn фейд
     */
    public TitleObjOff(String objectName, Duration duration, Fade fadeIn) {
        super(CommandKey.TITLE_OBJ_OFF, objectName, duration, fadeIn);
    }

    @Override
    public String getName() {
        return super.getName() + " Off";
    }
}
