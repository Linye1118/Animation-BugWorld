package application;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bug {
	private double x;
	private double y;
	private double size;
	
	private String species;

	private double speed=5;
	private double dx, dy;
	private int energy;
	private String food;
	private String predator;
	private Circle symbol;
	
	private double xEnd;
	private double yEnd;
	
	public Bug(double x, double y, double size) {
		super();
		this.x = x;
		this.y = y;
		this.size = size;
		this.symbol();
		this.dPos();
	}
	
	public Circle symbol() {
		Circle circle = new Circle(x, y, size);
		circle.setFill(Color.BLACK);
		this.symbol=circle;
		return circle;
	}

	public void printPos() {
		System.out.println(species + " X:" + x + " Y: "+ y + " Size: "+ size);
	}
	
	public void dPos() {
		double xTarget = Math.random()*400;
		double yTarget = Math.random()*300;
		
		double dis = Math.sqrt((xTarget-x)*(xTarget-x)+(yTarget-y)*(yTarget-y));
		double steps = dis/speed;
		this.dx = (xTarget-x)/steps;
		this.dy = (yTarget-y)/steps;
	}
	
	public void randomMove(Scene scene) {
		
		double xTarget = Math.random()*400;
		double yTarget = Math.random()*300;
		
		double dis = Math.sqrt((xTarget-x)*(xTarget-x)+(yTarget-y)*(yTarget-y));
		double steps = dis/speed;
		dx = (xTarget-x)/steps;
		dy = (yTarget-y)/steps;
		
		x = x + dx;
		y = y + dy;
			
		symbol.setTranslateX(symbol.getTranslateX() + dx);
		symbol.setTranslateY(symbol.getTranslateY() + dy);

	}
	
	public Plant smellFood(BugWorld2 w) {
		Plant target = null;
		for (Plant f: w.plants)	{
			if (target==null || target.getSize()<f.getSize()) {
				target = f;
			}
		}
		return target;
	}
	
	public void moveForFood(Plant f, Scene scene) {
		if (f == null) {
			this.randomMove(scene);
		}else {
			double dis = Math.sqrt((f.getX()-x)*(f.getX()-x) + (f.getY()-y)*(f.getY()-y));
			double steps = dis/speed;
			dx = (x - f.getX())/steps;
			dy = (y - f.getY())/steps;
			xEnd = this.symbol.getCenterX()-dx;
			yEnd = this.symbol.getCenterY()-dy;
		}
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

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public String getPredator() {
		return predator;
	}

	public void setPredator(String predator) {
		this.predator = predator;
	}

	public Circle getSymbol() {
		return symbol;
	}

	public void setSymbol(Circle symbol) {
		this.symbol = symbol;
	}

	public double getxEnd() {
		return xEnd;
	}

	public void setxEnd(double xEnd) {
		this.xEnd = xEnd;
	}

	public double getyEnd() {
		return yEnd;
	}

	public void setyEnd(double yEnd) {
		this.yEnd = yEnd;
	}
}
