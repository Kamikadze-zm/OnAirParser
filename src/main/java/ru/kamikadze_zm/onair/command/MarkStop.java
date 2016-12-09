package ru.kamikadze_zm.onair.command;

public class MarkStop extends Mark {

    public MarkStop(String command) {
        super(CommandKey.MARK_STOP, command);
    }

    public MarkStop(String markName, String comment) {
        super(CommandKey.MARK_STOP, markName, comment);
    }
}
