package de.futurehome.tanksimulator;

public class Tank {
    private double fuellstand;
    private final double MAX_FUELLSTAND = 200.0;

    public Tank(double fuellstand) {
        this.fuellstand = fuellstand;
    }

    public double getFuellstand() {
        return fuellstand;
    }

    public void setFuellstand(double fuellstand) {
        this.fuellstand = fuellstand;
    }

    public void einfuellen(double menge) {
        this.fuellstand += menge;
        if (this.fuellstand > MAX_FUELLSTAND) {
            this.fuellstand = MAX_FUELLSTAND;
        }
    }

    public void verbrauchen(double menge) {
        this.fuellstand -= menge;
        if (this.fuellstand < 0) {
            this.fuellstand = 0;
        }
    }

    public void zuruecksetzen() {
        this.fuellstand = 0;
    }

    public double getProzent() {
        return (this.fuellstand / MAX_FUELLSTAND) * 100;
    }
}
