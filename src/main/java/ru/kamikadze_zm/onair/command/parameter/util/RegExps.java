package ru.kamikadze_zm.onair.command.parameter.util;

import java.util.regex.Pattern;

class RegExps {

    private RegExps() {
    }    
    
    static Pattern duration() {
        //movie 0:00:60.00 D:\Ролики на день\Anons\anons1.avi
        //пробел цифра или 2 цифры : 2 цифры : 2 цифры . 2 цифры пробел
        return Pattern.compile(" (\\d|\\d\\d):\\d\\d:\\d\\d\\.\\d\\d ");
    }
    
    static Pattern parallelDuration() {
        //titleObjLoad {Бегучка} (0:02:50.92) D:\Строка\20161203 СЭТ коммерция.txt
        //( цифра или 2 цифры : 2 цифры : 2 цифры . 2 цифры )
        return Pattern.compile("\\((\\d|\\d\\d):\\d\\d:\\d\\d.\\d\\d\\)");
    }
    
    //использовать последним (может совпасть с именем файла или комментарием)
    static Pattern zeroDuration() {
        //titleObjLoad {AnnouncerNow} 0 D:\Анонсер текущего фильма\AnnouncerTask_2017_01_13
        //пробел 0 пробел
        return Pattern.compile(" 0 ");
    }
    
    static Pattern markIn() {
        //movie <0:42:36.60> 0:10:46.40 [0.12] H:\декабрь\16+\ДоРе Аллегрова.mpeg
        //< цифра или 2 цифры : 2 цифры : 2 цифры . 2 цифры >
        return Pattern.compile("<(\\d|\\d\\d):\\d\\d:\\d\\d.\\d\\d>");
    }
    
    static Pattern fade() {
        //movie <0:42:36.60> 0:10:46.40 [0.12] H:\декабрь\16+\ДоРе Аллегрова.mpeg
        //[ цифра или 2 цифры . 2 цифры ]
        return Pattern.compile("\\[(\\d|\\d\\d)\\.\\d\\d\\]");
    }
    
    static Pattern titleObjName() {
        //titleObjLoad {Бегучка} (0:02:50.92) D:\Строка\20161203 СЭТ коммерция.txt
        //{ любые символы }
        return Pattern.compile("\\{.*\\}");
    }
}
