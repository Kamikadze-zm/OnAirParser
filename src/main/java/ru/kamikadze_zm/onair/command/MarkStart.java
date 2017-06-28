package ru.kamikadze_zm.onair.command;

/**
 *
 * Метка старта
 */
public class MarkStart extends Mark {

    /**
     *
     * @param command команда в виде строки расписания
     */
    public MarkStart(String command) {
        super(CommandKey.MARK_START, command);
    }

    /**
     *
     * @param markName название метки
     * @param comment комментарий
     */
    public MarkStart(String markName, String comment) {
        super(CommandKey.MARK_START, markName, comment);
    }
}
