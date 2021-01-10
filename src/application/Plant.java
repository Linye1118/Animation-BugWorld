package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Plant {

	private double x;
	private double y;
	private double size;
	private String species = "Plant";
	
	private Polygon symbol;

	public Plant(double x, double y, double size) {
		super();
		this.x = x;
		this.y = y;
		this.size = size;
		this.symbol();
	}

	public Polygon symbol() {
		Double[] points = {(x-size/2), (y+size), x, y, (x+size/2), (y+size)};
		Polygon triangle = new Polygon();
		triangle.getPoints().addAll(points);
		triangle.setFill(Color.GREEN);
		this.symbol = triangle;
		return symbol;
	}

	public void growOnClick() {
		this.size= size+1;
	    this.symbol=this.symbol();
	    
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}
	
	public Polygon getSymbol() {
		return symbol;
	}

	public void setSymbol(Polygon symbol) {
		this.symbol = symbol;
	}

}
