package xyz.pary.onair.command.parameter;

/**
 *
 * Длительность для паралельного выполнения команд
 */
public class ParallelDuration extends Duration {

    /**
     *
     * @param duration - строка формата hh:mm:ss.xx
     */
    public ParallelDuration(String duration) {
        super(duration);
    }

    /**
     *
     * @param hh часы
     * @param mm минуты
     * @param ss секунды
     * @param xx сотые секунды
     */
    public ParallelDuration(int hh, int mm, int ss, int xx) {
        super(hh, mm, ss, xx);
    }

    /**
     *
     * @param ms длительность в милисекундах
     */
    public ParallelDuration(long ms) {
        super(ms);
    }

    @Override
    public String toString() {
        return "(" + super.toString() + ")";
    }
}
