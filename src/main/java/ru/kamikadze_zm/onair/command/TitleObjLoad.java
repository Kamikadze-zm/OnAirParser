package ru.kamikadze_zm.onair.command;

import java.util.Objects;
import ru.kamikadze_zm.onair.OnAirParserException;
import ru.kamikadze_zm.onair.command.parameter.Duration;
import ru.kamikadze_zm.onair.command.parameter.Fade;
import ru.kamikadze_zm.onair.command.parameter.util.ParameterParser;

public class TitleObjLoad extends TitleObj {

    private final String fileName;

    public TitleObjLoad(String command) {
        super(CommandKey.TITLE_OBJ_LOAD, command);
        this.fileName = ParameterParser.getFileName(command);
        if (this.fileName == null) {
            throw new OnAirParserException("Отсутствует имя файла задания");
        }
    }
    
    public TitleObjLoad(CommandKey key, String objectName, Duration duration, Fade fadeIn,
            String fileName) {
        super(CommandKey.TITLE_OBJ_LOAD, objectName, duration, fadeIn);
        if (fileName == null) {
            throw new OnAirParserException("Отсутствует имя файла задания");
        }
        this.fileName = fileName;
    }

    @Override
    public String getName() {
        return super.getName() + " " + fileName;
    }

    @Override
    public String toSheduleRow() {
        //titleObjLoad {TitleObject_1} 0:00:05.20 D:\Spt\HappyNew.spt
        return super.toSheduleRow() + " " + fileName;
    }

//    @Override
//    public int hashCode() {
//        int hash = 5;
//        hash = 37 * hash + Objects.hashCode(this.fileName);
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
//        final TitleObjLoad other = (TitleObjLoad) obj;
//        if (!Objects.equals(this.fileName, other.fileName)) {
//            return false;
//        }
//        return true;
//    }
}
