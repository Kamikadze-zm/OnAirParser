package ru.kamikadze_zm.onair.command;

import ru.kamikadze_zm.onair.command.parameter.IName;

public class WaitFollow extends Command implements IName {

    private String comment;
    
    //wait follow 0 Comment
    public WaitFollow(String command) {
        super(CommandKey.WAIT_FOLLOW);
        this.comment = command.substring(14);
    }
    
    public WaitFollow(String comment, boolean isComment) {
        super(CommandKey.WAIT_FOLLOW);
        this.comment = comment;
    }

    @Override
    public String getName() {
        return comment;
    }
    
    @Override
    public String toSheduleRow() {
        return commandKey.getKey() + " 0 " + comment;
    }
}
