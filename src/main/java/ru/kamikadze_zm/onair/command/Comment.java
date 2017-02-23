package ru.kamikadze_zm.onair.command;

import ru.kamikadze_zm.onair.command.parameter.IName;

public class Comment extends Command implements IName {

    private final String comment;
    
    public Comment(String command) {
        super(CommandKey.COMMENT);
        this.comment = command.substring(10);
    }
    
    public Comment(String comment, boolean isComment) {
        super(CommandKey.COMMENT);
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
    
    @Override
    public String getName() {
        return comment;
    }

    @Override
    public String toSheduleRow() {
        return commandKey.getKey() + " 0 " + comment;
    }

//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 67 * hash + Objects.hashCode(this.comment);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Comment other = (Comment) obj;
//        if (!Objects.equals(this.comment, other.comment)) {
//            return false;
//        }
//        return true;
//    }
}
