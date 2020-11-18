package xyz.pary.command.parameter;

import java.util.Locale;

/**
 *
 * Длительность исчезновения/появления
 */
public class Fade {

    private final double fade;

    /**
     *
     * @param fade строка формата ss.xx
     */
    public Fade(String fade) {
        this.fade = Double.parseDouble(fade);
    }

    /**
     *
     * @param fade длительность исчезновения/появления в секундах
     */
    public Fade(double fade) {
        this.fade = fade;
    }

    /**
     *
     * @return Длительность исчезновения/появления в секундах
     */
    public double getFade() {
        return fade;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "[%.2f]", fade);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (int) (Double.doubleToLongBits(this.fade) ^ (Double.doubleToLongBits(this.fade) >>> 32));
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
        if (Double.doubleToLongBits(this.fade) != Double.doubleToLongBits(other.fade)) {
            return false;
        }
        return true;
    }
}
