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
        Matcher pDurMatcher = RegExps.parallelDuration().matcher(command);
        if (pDurMatcher.find()) {
            int s = pDurMatcher.start();
            int e = pDurMatcher.end();
            return new ParallelDuration(command.substring(s + 1, e - 1));
        } else {
            Matcher durMatcher = RegExps.duration().matcher(command);
            if (durMatcher.find()) {
                int s = durMatcher.start();
                int e = durMatcher.end();
                return new Duration(command.substring(s + 1, e - 1));
            } else {
                return null;
            }
        }
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
        if (fadeMatcher.find()) {
            return command.substring(fadeMatcher.end() + 1);
        } else {
            Matcher parallelDurationMatcher = RegExps.parallelDuration().matcher(command);
            if (parallelDurationMatcher.find()) {
                return command.substring(parallelDurationMatcher.end() + 1);
            }
            Matcher durationMatcher = RegExps.duration().matcher(command);
            if (durationMatcher.find()) {
                return command.substring(durationMatcher.end());
            }
            if (command.startsWith(CommandKey.MOVIE.getKey())) {
                return command.substring(6);
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
