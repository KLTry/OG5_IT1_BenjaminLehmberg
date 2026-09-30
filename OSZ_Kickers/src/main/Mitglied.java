package main;

public abstract class Mitglied extends Mensch{

	//Attribute
	private String telefon_Nr; 
	private boolean jahresBeitragBezahlt; 
	
	//Methoden
	public Mitglied(String name, String telefon_Nr, boolean jahresBeitragBezahlt) {
		super(name);
		this.telefon_Nr = telefon_Nr;
		this.jahresBeitragBezahlt = jahresBeitragBezahlt;
	}

	public String getTelefon_Nr() {
		return telefon_Nr;
	}

	public void setTelefon_Nr(String telefon_Nr) {
		this.telefon_Nr = telefon_Nr;
	}

	public boolean isJahresBeitragBezahlt() {
		return jahresBeitragBezahlt;
	}

	public void setJahresBeitragBezahlt(boolean jahresBeitragBezahlt) {
		this.jahresBeitragBezahlt = jahresBeitragBezahlt;
	}
	
}
