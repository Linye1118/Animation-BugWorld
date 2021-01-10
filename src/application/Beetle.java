package application;

public class Beetle extends Bug {
	private String species = "Beetle";
	
	public Beetle(double x, double y, double size) {
		super(x, y, size);
		// TODO Auto-generated constructor stub
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}
}
