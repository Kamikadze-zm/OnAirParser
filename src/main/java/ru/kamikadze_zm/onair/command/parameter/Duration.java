package ru.kamikadze_zm.onair.command.parameter;

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
    
    public Duration(int hh, int mm, int ss, int xx) {
        this.hh = hh;
        this.mm = mm;
        this.ss = ss;
        this.xx = xx;
        this.duration = calculateDuration(hh, mm, ss, xx);
    }

    public long getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        if (zeroDuration) {
            return String.valueOf(0);
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
}
