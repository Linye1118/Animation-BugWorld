package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle {
	private double x;
	private double y;
	private double size;
	private Rectangle symbol;
	
	
	public Obstacle(double x, double y, double size) {
		super();
		this.x = x;
		this.y = y;
		this.size = size;
		this.symbol();
	}
	
	
	public Rectangle symbol() {
		Rectangle rect = new Rectangle(x, y, size, size);
		rect.setFill(Color.ORANGE);
		this.symbol = rect;
		return symbol;
	}
	
	public double getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public double getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public Rectangle getSymbol() {
		return symbol;
	}

	public void setSymbol(Rectangle symbol) {
		this.symbol = symbol;
	}

}
