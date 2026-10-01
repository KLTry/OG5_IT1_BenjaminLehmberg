package main;

public class Trainer extends Mitglied {

	private char lizenzKlasse;
	private double aufwandsEntschuldigung_in_Euro; 
	
	public Trainer(String name, String telefon_Nr, boolean jahresBeitragBezahlt, char lizenzKlasse, 
			double aufwandsEntschuldigung_in_Euro ) {
		super(name, telefon_Nr, jahresBeitragBezahlt);
		this.aufwandsEntschuldigung_in_Euro = aufwandsEntschuldigung_in_Euro;
		this.lizenzKlasse = lizenzKlasse;
		}

	public char getLizenzKlasse() {
		return lizenzKlasse;
	}

	public void setLizenzKlasse(char lizenzKlasse) {
		this.lizenzKlasse = lizenzKlasse;
	}

	public double getAufwandsEntschuldigung_in_Euro() {
		return aufwandsEntschuldigung_in_Euro;
	}

	public void setAufwandsEntschuldigung_in_Euro(double aufwandsEntschuldigung_in_Euro) {
		this.aufwandsEntschuldigung_in_Euro = aufwandsEntschuldigung_in_Euro;
	}
}
