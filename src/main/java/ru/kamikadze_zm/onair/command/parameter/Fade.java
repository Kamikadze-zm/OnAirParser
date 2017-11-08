package ru.kamikadze_zm.onair.command.parameter;

/**
 *
 * Длительность исчезновения/появления
 */
public class Fade {

    private final int ss;
    private final int xx;
    private final long fade;

    /**
     *
     * @param fade - строка формата ss.xx
     */
    public Fade(String fade) {
        String[] parts = fade.split("(:|\\.)");
        this.ss = Integer.parseInt(parts[0]);
        int xx = Integer.parseInt(parts[1]);
        if (parts[1].length() == 1) {
            xx = xx * 10;
        }
        this.xx = xx;
        this.fade = calculateFadeDuration(ss, xx);
    }

    /**
     *
     * @param ss секунды
     * @param xx сотые секунды
     */
    public Fade(int ss, int xx) {
        this.ss = ss;
        this.xx = xx;
        this.fade = calculateFadeDuration(ss, xx);
    }

    /**
     *
     * @return Длительность исчезновения/появления в милисекундах
     */
    public long getFade() {
        return fade;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("[")
                .append(ss)
                .append(".")
                .append(String.format("%02d", xx))
                .append("]")
                .toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + (int) (this.fade ^ (this.fade >>> 32));
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
        final Fade other = (Fade) obj;
        if (this.fade != other.fade) {
            return false;
        }
        return true;
    }

    private static long calculateFadeDuration(int ss, int xx) {
        return (ss * 100 + xx) * 10;
    }
}
