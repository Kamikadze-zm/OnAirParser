package xyz.pary.onair.command.parameter;

/**
 *
 * Длительность
 */
public class Duration {

    private int hh;
    private int mm;
    private int ss;
    private int xx;
    private long duration;
    private boolean zeroDuration;

    /**
     *
     * @param duration - строка формата hh:mm:ss.xx или 0
     */
    public Duration(String duration) {
        if (duration.length() == 1) {
            int zeroDuration = Integer.parseInt(duration);
            if (zeroDuration == 0) {
                this.zeroDuration = true;
            }
        } else {
            String[] parts = duration.split("(:|\\.)");
            this.hh = Integer.parseInt(parts[0]);
            this.mm = Integer.parseInt(parts[1]);
            this.ss = Integer.parseInt(parts[2]);
            this.xx = Integer.parseInt(parts[3]);
            this.duration = calculateDuration(hh, mm, ss, xx);
        }
    }

    /**
     *
     * Нулевая длительность - 0
     */
    public Duration() {
        this.zeroDuration = true;
    }

    /**
     *
     * @param hh часы
     * @param mm минуты
     * @param ss секунды
     * @param xx сотые секунды
     */
    public Duration(int hh, int mm, int ss, int xx) {
        this.hh = hh;
        this.mm = mm;
        this.ss = ss;
        this.xx = xx;
        this.duration = calculateDuration(hh, mm, ss, xx);
    }

    /**
     *
     * @param ms длительность в милисекундах
     */
    public Duration(long ms) {
        this.duration = ms;
        int[] parts = calculateDuration(ms);
        this.hh = parts[0];
        this.mm = parts[1];
        this.ss = parts[2];
        this.xx = parts[3];
    }

    /**
     *
     * @return Длительность в милисекундах
     */
    public long getDuration() {
        return duration;
    }

    /**
     * Прибавляет указанную длительность к текущей
     *
     * @param duration длительность, которую нужно прибавить
     * @return Новую длительность
     */
    public Duration add(Duration duration) {
        if (duration instanceof ParallelDuration) {
            return new Duration(this.duration);
        }
        return new Duration(this.duration + duration.getDuration());
    }

    @Override
    public String toString() {
        if (zeroDuration) {
            return String.valueOf(0);
        }
        if (hh >= 24) {
            hh = hh % 24;
        }
        return new StringBuilder()
                .append(hh)
                .append(":")
                .append(String.format("%02d", mm))
                .append(":")
                .append(String.format("%02d", ss))
                .append(".")
                .append(String.format("%02d", xx))
                .toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (int) (this.duration ^ (this.duration >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Duration other = (Duration) obj;
        if (this.duration != other.duration) {
            return false;
        }
        return true;
    }

    private static long calculateDuration(int hh, int mm, int ss, int xx) {
        return (((hh * 60 + mm) * 60 + ss) * 100 + xx) * 10;
    }

    private static int[] calculateDuration(long duration) {
        int[] parts = new int[4];

        //множители в милисекундах
        final int hhF = 3600000;
        final int mmF = 60000;
        final int ssF = 1000;
        final int xxF = 10;

        double hh = duration / hhF;
        parts[0] = (int) hh;//Math.round(hh);

        duration -= parts[0] * hhF;

        double mm = duration / mmF;
        parts[1] = (int) mm;//Math.round(mm);

        duration -= parts[1] * mmF;

        double ss = duration / ssF;
        parts[2] = (int) ss;//Math.round(ss);

        duration -= parts[2] * ssF;

        double xx = duration / xxF;
        parts[3] = (int) xx;//Math.round(xx);

        return parts;
    }
}
