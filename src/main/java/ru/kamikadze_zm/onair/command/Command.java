package ru.kamikadze_zm.onair.command;

public abstract class Command {

    protected final CommandKey commandKey;

    public Command(CommandKey commandKey) {
        this.commandKey = commandKey;
    }
    
    public CommandKey getCommandKey() {
        return commandKey;
    }
    
    //приведение к строке расписания .air
    public abstract String toSheduleRow();
    
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
        return CommandKey.UNKNOWN_COMMAND;
    }
    
    //ключи команд
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
        UNKNOWN_COMMAND("unknown");

        //ключ как в расписании
        private final String key;
        
        private CommandKey(String key) {
            this.key = key;
        }
        
        public String getKey() {
            return key;
        }
    }

}
