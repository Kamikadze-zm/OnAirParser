package ru.kamikadze_zm.onair.command.parameter.util;

import java.util.regex.Pattern;

class RegExps {

    private RegExps() {
    }    
    
    static Pattern duration() {
        return Pattern.compile(" (\\d|\\d\\d]):\\d\\d:\\d\\d\\.\\d\\d ");
    }
    
    static Pattern parallelDuration() {
        return Pattern.compile("\\((\\d|\\d\\d):\\d\\d:\\d\\d.\\d\\d\\)");
    }
    
    static Pattern markIn() {
        return Pattern.compile("<(\\d|\\d\\d]):\\d\\d:\\d\\d.\\d\\d>");
    }
    
    static Pattern fade() {
        return Pattern.compile("\\[(\\d|\\d\\d])\\.\\d\\d\\]");
    }
    
    static Pattern titleObjName() {
        return Pattern.compile("\\{.*\\}");
    }
}
