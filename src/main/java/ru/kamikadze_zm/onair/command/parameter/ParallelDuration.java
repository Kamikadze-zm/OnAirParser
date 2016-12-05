package ru.kamikadze_zm.onair.command.parameter;

public class ParallelDuration extends Duration {

    /**
     *
     * @param duration - строка формата hh:mm:ss.xx
     */
    public ParallelDuration(String duration) {
        super(duration);
    }
    
    public ParallelDuration(int hh, int mm, int ss, int xx) {
        super(hh, mm, ss, xx);
    }
    
    @Override
    public String toString() {
        return "(" + super.toString() + ")";
    }
}
