package main;

public class Schiedsrichter extends Mitglied{

	private int gefiffeneSpiele;
	
	public Schiedsrichter(String name, String telefon_Nr, boolean jahresBeitragBezahlt
			, int gefiffeneSpiele) {
		super(name, telefon_Nr, jahresBeitragBezahlt);
		this.gefiffeneSpiele = gefiffeneSpiele;
	}

	public int getGefiffeneSpiele() {
		return gefiffeneSpiele;
	}

	public void setGefiffeneSpiele(int gefiffeneSpiele) {
		this.gefiffeneSpiele = gefiffeneSpiele;
	}
}

