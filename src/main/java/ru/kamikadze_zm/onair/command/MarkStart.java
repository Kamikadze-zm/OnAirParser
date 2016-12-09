package ru.kamikadze_zm.onair.command;

public class MarkStart extends Mark {

    public MarkStart(String command) {
        super(CommandKey.MARK_START, command);
    }

    public MarkStart(String markName, String comment) {
        super(CommandKey.MARK_START, markName, comment);
    }
}
