package ru.kamikadze_zm.onair.command;

/**
 *
 * Метка остановки
 */
public class MarkStop extends Mark {

    /**
     *
     * @param command команда в виде строки расписания
     */
    public MarkStop(String command) {
        super(CommandKey.MARK_STOP, command);
    }

    /**
     *
     * @param markName название метки
     * @param comment комментарий
     */
    public MarkStop(String markName, String comment) {
        super(CommandKey.MARK_STOP, markName, comment);
    }
}
