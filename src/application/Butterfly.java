package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Butterfly extends Bug {

	private String species = "Butterfly";
	private double x;
	private double y;
	private double size;
	private double speed=5;
	private double dx, dy;
	private ImageView image;
	
	public Butterfly(double x, double y, double size) {
		super(x, y, size);
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	public ImageView image() {
		Image bfImage = new Image(getClass().getResourceAsStream("butterfly.png"));
		ImageView imageView = new ImageView(bfImage);
		imageView.setX(x);
		imageView.setY(y);
		imageView.setFitHeight(20);
		imageView.setFitWidth(20);
		imageView.setPreserveRatio(true);
		return imageView;
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
			
		image.setTranslateX(image.getTranslateX() + dx);
		image.setTranslateY(image.getTranslateY() + dy);

	}

	public Plant smellFood(BugWorld2 w) {
		List<Plant> foods = new ArrayList<>();
		boolean find = false;
		for (Plant f : w.plants) {
			if (f.getSpecies().equals("Flower")) {
				foods.add(f);
				find = true;
			}else {
				find = false;
			}
		}
		if(find = false) {
			return null;
		}else {
			Plant target = null;
			double disMin = 0; 
			double disX = 0, disY = 0;
			for (Plant t: foods) {
				disX = Math.abs(t.getX()-x);
				disY = Math.abs(t.getY()-y);
				double dis = disX+disY;
				if (disMin == 0 || disMin > dis) {//find the closest target
					disMin = dis;
					target=t;
				}	
			}
			if (target!=null) {
				return target;
			}else {
				return null;
			}
		}
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
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

}
