package ru.kamikadze_zm.onair.command;

import ru.kamikadze_zm.onair.command.parameter.IDuration;
import ru.kamikadze_zm.onair.command.parameter.IName;

/**
 *
 * Базовый класс команды. Содержит: ключ команды, перечисление возможных ключей команды, метод парсинга команды, абстрактный метод формирания команды
 * в виде строки расписания
 */
public abstract class Command implements IName, IDuration {

    protected final CommandKey commandKey;

    /**
     *
     * @param commandKey ключ команды
     */
    protected Command(CommandKey commandKey) {
        this.commandKey = commandKey;
    }

    /**
     *
     * @return Ключ команды
     */
    public CommandKey getCommandKey() {
        return commandKey;
    }

    /**
     * Преобразует команду к строке расписания .air
     *
     * @return команду в виде строки расписания
     */
    public abstract String toSheduleRow();

    /**
     * Парсит команду в виде строки расписания и возвращает соответсятвующий ключ
     *
     * @param command команда в виде строки расписания
     * @return Ключ команды
     */
    public static CommandKey parseCommandKey(String command) {
        if (command.startsWith(CommandKey.MOVIE.getKey())) {
            return CommandKey.MOVIE;
        }
        if (command.startsWith(CommandKey.TITLE_OBJ_LOAD.getKey())) {
            return CommandKey.TITLE_OBJ_LOAD;
        }
        if (command.startsWith(CommandKey.COMMENT.getKey())) {
            return CommandKey.COMMENT;
        }
        if (command.startsWith(CommandKey.TITLE_MOVIE.getKey())) {
            return CommandKey.TITLE_MOVIE;
        }
        if (command.startsWith(CommandKey.MARK_START.getKey())) {
            return CommandKey.MARK_START;
        }
        if (command.startsWith(CommandKey.MARK_STOP.getKey())) {
            return CommandKey.MARK_STOP;
        }
        if (command.startsWith(CommandKey.PAUSE.getKey())) {
            return CommandKey.PAUSE;
        }
        if (command.startsWith(CommandKey.TITLE_OBJ_ON.getKey())) {
            return CommandKey.TITLE_OBJ_ON;
        }
        if (command.startsWith(CommandKey.TITLE_OBJ_OFF.getKey())) {
            return CommandKey.TITLE_OBJ_OFF;
        }
        if (command.startsWith(CommandKey.TITLING_ON.getKey())) {
            return CommandKey.TITLING_ON;
        }
        if (command.startsWith(CommandKey.WAIT_TIME.getKey())) {
            if (command.contains("active")) {
                return CommandKey.WAIT_TIME_ACTIVE;
            }
            return CommandKey.WAIT_TIME;
        }
        if (command.startsWith(CommandKey.SWITCH_SHEDULE.getKey())) {
            return CommandKey.SWITCH_SHEDULE;
        }
        if (command.startsWith(CommandKey.WAIT_OPERATOR.getKey())) {
            return CommandKey.WAIT_OPERATOR;
        }
        if (command.startsWith(CommandKey.WAIT_FOLLOW.getKey())) {
            return CommandKey.WAIT_FOLLOW;
        }
        return CommandKey.UNKNOWN_COMMAND;
    }

    /**
     * Ключи команд
     */
    public static enum CommandKey {
        COMMENT("comment"),
        WAIT_TIME("wait time"),
        WAIT_TIME_ACTIVE("wait time"),
        PAUSE("pause"),
        SWITCH_SHEDULE("switch shedule"),
        MOVIE("movie"),
        TITLE_OBJ_LOAD("titleObjLoad"),
        TITLE_MOVIE("titleMovie"),
        MARK_START("markstart"),
        MARK_STOP("markstop"),
        TITLE_OBJ_ON("titleObjOn"),
        TITLE_OBJ_OFF("titleObjOff"),
        TITLING_ON("titlingOn"),
        WAIT_OPERATOR("wait operator"),
        WAIT_FOLLOW("wait follow"),
        UNKNOWN_COMMAND("unknown");

        //ключ как в расписании
        private final String key;

        private CommandKey(String key) {
            this.key = key;
        }

        /**
         *
         * @return ключ команды, как в расписании
         */
        public String getKey() {
            return key;
        }
    }

}
