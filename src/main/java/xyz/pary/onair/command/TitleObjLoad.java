package xyz.pary.onair.command;

import java.util.Objects;
import xyz.pary.onair.OnAirParserException;
import xyz.pary.onair.command.parameter.Duration;
import xyz.pary.onair.command.parameter.Fade;
import xyz.pary.onair.command.parameter.util.ParameterParser;

/**
 *
 * Команда загрузки задания в титровальный объект
 */
public class TitleObjLoad extends TitleObj {

    private final String fileName;

    /**
     *
     * @param command команда в виде строки
     */
    public TitleObjLoad(String command) {
        super(CommandKey.TITLE_OBJ_LOAD, command);
        this.fileName = ParameterParser.getFileName(command);
        if (this.fileName == null) {
            throw new OnAirParserException("Отсутствует имя файла задания");
        }
    }

    /**
     *
     * @param objectName название титровального объекта
     * @param duration длительность
     * @param fadeIn фейд
     * @param fileName название файла
     */
    public TitleObjLoad(String objectName, Duration duration, Fade fadeIn,
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(super.objectName);
        hash = 79 * hash + Objects.hashCode(this.fileName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TitleObjLoad other = (TitleObjLoad) obj;
        if (!Objects.equals(this.fileName, other.fileName) || !Objects.equals(this.objectName, other.objectName)) {
            return false;
        }
        return true;
    }

}
