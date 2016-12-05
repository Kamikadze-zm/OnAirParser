package ru.kamikadze_zm.onair.command.parameter;

public class Duration {

    private final int hh;
    private final int mm;
    private final int ss;
    private final int xx;
    private final long duration;

    /**
     *
     * @param duration - строка формата hh:mm:ss.xx
     */
    public Duration(String duration) {
        String[] parts = duration.split("(:|\\.)");
        this.hh = Integer.parseInt(parts[0]);
        this.mm = Integer.parseInt(parts[1]);
        this.ss = Integer.parseInt(parts[2]);
        this.xx = Integer.parseInt(parts[3]);
        this.duration = calculateDuration(hh, mm, ss, xx);
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
