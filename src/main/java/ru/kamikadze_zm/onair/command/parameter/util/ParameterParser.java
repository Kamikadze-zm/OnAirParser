package ru.kamikadze_zm.onair.command.parameter.util;

import java.util.regex.Matcher;
import ru.kamikadze_zm.onair.command.Command.CommandKey;
import ru.kamikadze_zm.onair.command.parameter.Duration;
import ru.kamikadze_zm.onair.command.parameter.Fade;
import ru.kamikadze_zm.onair.command.parameter.MarkIn;
import ru.kamikadze_zm.onair.command.parameter.ParallelDuration;

public class ParameterParser {

    private ParameterParser() {
    }

    public static Duration getDuration(String command) {
        //сначала проверяем параллельное выполнение
        Matcher pDurMatcher = RegExps.parallelDuration().matcher(command);
        if (pDurMatcher.find()) {
            int s = pDurMatcher.start();
            int e = pDurMatcher.end();
            return new ParallelDuration(command.substring(s + 1, e - 1));
            //если нет - ищем обычную длительность
        } else {
            Matcher durMatcher = RegExps.duration().matcher(command);
            if (durMatcher.find()) {
                int s = durMatcher.start();
                int e = durMatcher.end();
                return new Duration(command.substring(s + 1, e - 1));
            } else {
                Matcher zDurMatcher = RegExps.zeroDuration().matcher(command);
                if (zDurMatcher.find()) {
                    return new Duration();
                }
            }
        }
        return null;
    }

    

    public static MarkIn getMarkIn(String command) {
        Matcher matcher = RegExps.markIn().matcher(command);
        if (matcher.find()) {
            int s = matcher.start();
            int e = matcher.end();
            return new MarkIn(command.substring(s + 1, e - 1));
        } else {
            return null;
        }
    }

    public static Fade getFade(String command) {
        Matcher matcher = RegExps.fade().matcher(command);
        if (matcher.find()) {
            int s = matcher.start();
            int e = matcher.end();
            return new Fade(command.substring(s + 1, e - 1));
        } else {
            return null;
        }
    }

    public static String getFileName(String command) {
        Matcher fadeMatcher = RegExps.fade().matcher(command);
        //movie <0:42:36.60> 0:10:46.40 [0.12] H:\декабрь\16+\ДоРе Аллегрова.mpeg
        //ели присутствует фейд - берем фейд + 1
        if (fadeMatcher.find()) {
            return command.substring(fadeMatcher.end() + 1);
        } else {
            //movie 0:00:60.00 D:\Ролики на день\Anons\anons1.avi
            //нет фейда, есть длительность - берем длительность + 1
            Matcher parallelDurationMatcher = RegExps.parallelDuration().matcher(command);
            if (parallelDurationMatcher.find()) {
                return command.substring(parallelDurationMatcher.end() + 1);
            }
            Matcher durationMatcher = RegExps.duration().matcher(command);
            if (durationMatcher.find()) {
                return command.substring(durationMatcher.end());
            }
            //movie D:\Ролики на день\Ролики_4x3\Evrosvet_151211_15.avi
            //нет ни фейда, ни длительности, если это видео берем ключ команды + 1
            if (command.startsWith(CommandKey.MOVIE.getKey())) {
                return command.substring(6);
            }

            //titleObjLoad {AnnouncerNow} 0 D:\Анонсер текущего фильма\AnnouncerTask_2017_01_13
            Matcher zeroDurationMatcher = RegExps.zeroDuration().matcher(command);
            if (zeroDurationMatcher.find()) {
                return command.substring(zeroDurationMatcher.end());
            }
            return null;
        }
    }

    public static String getTitleObjName(String command) {
        Matcher matcher = RegExps.titleObjName().matcher(command);
        if (matcher.find()) {
            int s = matcher.start();
            int e = matcher.end();
            return command.substring(s, e);
        } else {
            return null;
        }
    }
}
