package ru.kamikadze_zm.onair.command.parameter;

/**
 *
 * Метка старта видео
 */
public class MarkIn extends Duration {

    /**
     *
     * @param duration - строка формата hh:mm:ss.xx
     */
    public MarkIn(String duration) {
        super(duration);
    }

    /**
     *
     * @param hh часы
     * @param mm минуты
     * @param ss секунды
     * @param xx сотые секунды
     */
    public MarkIn(int hh, int mm, int ss, int xx) {
        super(hh, mm, ss, xx);
    }

    /**
     *
     * @param ms метка старта в милисекундах
     */
    public MarkIn(long ms) {
        super(ms);
    }

    @Override
    public String toString() {
        return "<" + super.toString() + ">";
    }
}
