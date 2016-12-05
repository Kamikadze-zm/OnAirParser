package ru.kamikadze_zm.onair.command;

import ru.kamikadze_zm.onair.command.parameter.Duration;
import ru.kamikadze_zm.onair.command.parameter.Fade;

public class TitleObjOn extends TitleObj {

    public TitleObjOn(String command) {
        super(CommandKey.TITLE_OBJ_ON, command);
    }
    
    public TitleObjOn(CommandKey key, String objectName, Duration duration, Fade fadeIn) {
        super(CommandKey.TITLE_OBJ_ON, objectName, duration, fadeIn);
    }
}
