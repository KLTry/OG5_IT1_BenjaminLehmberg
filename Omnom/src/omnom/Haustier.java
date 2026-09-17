package omnom;

public class Haustier {

	//Attribute
	private int hunger; 
	private int muede;
	private int zufrieden;
	private int gesund;
	private String name; 
	
	//MEthoden
	
	public Haustier() {};
	public Haustier(String name) {
		this.hunger = 100;
		this.muede = 100; 
		this.zufrieden = 100;
		this.gesund = 100; 
	}

	public int getHunger() {
		return hunger;
	}

	public void setHunger(int hunger) {
		if(hunger > 0 && hunger < 100) {
		this.hunger = hunger;
		}
	}

	public int getMuede() {
		return muede;
	}

	public void setMuede(int muede) {
		if(hunger > 0 && hunger < 100) {
		this.muede = muede;
		}
	}

	public int getZufrieden() {
		return zufrieden;
	}

	public void setZufrieden(int zufrieden) {
		if(hunger > 0 && hunger < 100) {
		this.zufrieden = zufrieden;
		}
	}

	public int getGesund() {
		return gesund;
	}

	public void setGesund(int gesund) {
		if(hunger > 0 && hunger < 100) {
		this.gesund = gesund;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(hunger > 0 && hunger < 100) {
		this.name = name;
		}
	};
	
	public void fuettern(int anzahl) {
		this.hunger += hunger + anzahl;
		
	};
	
	public void schlafen(int dauer) {
		
	};
	
	public void spielen(int dauer) {
		
	};
	
	public void heilen() {
		
	};
	
	
	
	
}
