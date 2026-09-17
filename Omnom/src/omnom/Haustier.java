package omnom;

public class Haustier {

    // Attribute
    private int hunger; 
    private int muede;
    private int zufrieden;
    private int gesund;
    private String name; 

    // Konstruktoren
    public Haustier() {
        this.hunger = 100;
        this.muede = 100;
        this.zufrieden = 100;
        this.gesund = 100;
    }

    public Haustier(String name) {
        this(); // Ruft den Standard-Konstruktor auf, um die int-Werte auf 100 zu setzen
        this.name = name;
    }

    // Getter & Setter
    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        if (hunger >= 0 && hunger <= 100) {
            this.hunger = hunger;
        }
    }

    public int getMuede() {
        return muede;
    }

    public void setMuede(int muede) {
        if (muede >= 0 && muede <= 100) {
            this.muede = muede;
        }
    }

    public int getZufrieden() {
        return zufrieden;
    }

    public void setZufrieden(int zufrieden) {
        if (zufrieden >= 0 && zufrieden <= 100) {
            this.zufrieden = zufrieden;
        }
    }

    public int getGesund() {
        return gesund;
    }

    public void setGesund(int gesund) {
        if (gesund >= 0 && gesund <= 100) {
            this.gesund = gesund;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Methoden (vorerst ohne Inhalt)
    public void fuettern(int anzahl) {
        setHunger(this.hunger + anzahl);
    }

    public void schlafen(int dauer) {
        setMuede(this.muede + dauer);
    }

    public void spielen(int dauer) {
        setZufrieden(this.zufrieden + dauer);
    }

    public void heilen() {
        
    }
}