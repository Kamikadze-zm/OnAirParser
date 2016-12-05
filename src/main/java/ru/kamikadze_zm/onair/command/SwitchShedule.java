package ru.kamikadze_zm.onair.command;

public class SwitchShedule extends Command {

    public SwitchShedule() {
        super(CommandKey.SWITCH_SHEDULE);
    }

    @Override
    public String toSheduleRow() {
        return commandKey.getKey();
    }
}
