package ru.kamikadze_zm.onair.command.parameter;

public class MarkIn extends Duration {

    /**
     *
     * @param duration - строка формата hh:mm:ss.xx
     */
    public MarkIn(String duration) {
        super(duration);
    }

    public MarkIn(int hh, int mm, int ss, int xx) {
        super(hh, mm, ss, xx);
    }

    @Override
    public String toString() {
        return "<" + super.toString() + ">";
    }
}
