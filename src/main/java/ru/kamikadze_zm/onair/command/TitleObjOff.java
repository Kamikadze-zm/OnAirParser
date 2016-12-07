package ru.kamikadze_zm.onair.command;

import ru.kamikadze_zm.onair.command.parameter.Duration;
import ru.kamikadze_zm.onair.command.parameter.Fade;

public class TitleObjOff extends TitleObj {

    public TitleObjOff(String command) {
        super(CommandKey.TITLE_OBJ_OFF, command);
    }
    
    public TitleObjOff(CommandKey key, String objectName, Duration duration, Fade fadeIn) {
        super(CommandKey.TITLE_OBJ_OFF, objectName, duration, fadeIn);
    }
    
    @Override
    public String getName() {
        return super.getName() + " Off";
    }
}
