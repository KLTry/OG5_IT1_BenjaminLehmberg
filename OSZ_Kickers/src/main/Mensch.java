package main;

public abstract class Mensch {
	
	//Attribute
	private String name; 
	
	//Methoden
	public Mensch(String name) {
		this.name = name; 
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
