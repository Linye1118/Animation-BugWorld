package application;
	
import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;


public class BugWorld2 extends Application {
	protected int width=400, height=400;
	GridPane grid;
	boolean animateFlag = false;
	boolean modeFlag = false;
	public List<Bug> bugs = new ArrayList<>();
	public List<Plant> plants = new ArrayList<>();
	public List<Obstacle> obstacles = new ArrayList<>();
	
	public BugWorld2() {

	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			HBox hbox = controlBox();
			root.setBottom(controlBox());
			this.grid = drawGrid();
			root.setCenter(grid);
			
			Scene scene = new Scene(root,width,height);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			initializeRandom();
			animation();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void animation() {
		try {
			Transition transition = new Transition() {
				{
					setCycleDuration(Duration.millis(2000));
				}
				
	            @Override
	            protected void interpolate(double frac) {
	            	if(animateFlag == false)
	    				return;
	            	grid.getChildren().clear();
	            	for (Bug b: bugs) 
	            	{	 
	            		Circle c = b.getSymbol();
	            		//int random = 1;// (int) ( 1 + Math.random()*5);
	            		Bounds boundsInScene = c.localToScene(c.getBoundsInLocal());
	            		if(boundsInScene.getMinY() <= 0|| 
	            		boundsInScene.getMaxY() >= grid.getHeight()) 
	            		{
	                        b.setDy(b.getDy()*-1);
	                    } 
	            		else if(boundsInScene.getMaxX() >= grid.getWidth() || 
	            				boundsInScene.getMinX() <= 0) 
	            		{
	            			b.setDx(b.getDx()*-1);
	            		}
	            		c.setCenterX(boundsInScene.getMinX());
	                    c.setCenterY(boundsInScene.getMinY());
	                    
	                    c.setTranslateX(c.getTranslateX() + b.getDx());
	         			c.setTranslateY(c.getTranslateY() + b.getDy());            		
	        		
	            		grid.getChildren().add(c);	            		
	    			}  
	            }     
	        };
	        
	        transition.setCycleCount(Animation.INDEFINITE);
	        transition.setAutoReverse(true);	        
	        transition.play();
		}

		catch (Exception e) {
			e.printStackTrace();
		}	
	}

	private GridPane drawGrid() {
		GridPane grid = new GridPane();
	    grid.setStyle("-fx-background-color: #999999;");
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(0, 10, 0, 10));

	    return grid;
	}

	private HBox controlBox() {
		HBox hbox = new HBox();
		Button btnAnimate = new Button("Start");
		btnAnimate.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> switchAniBtn(btnAnimate));
		
		
		Button btnMode = new Button("Random");
		btnMode.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> switchModeBtn(btnMode));
		
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		hbox.getChildren().addAll(btnAnimate, btnMode);
		return hbox;
	}
	
	private void switchAniBtn(Button btn) {
		this.animateFlag = (!this.animateFlag);
		if (animateFlag==true) {
			btn.setText("Stop");
		}else {
			btn.setText("Start");
		}		
	}
	
	private void switchModeBtn(Button btn) {
		this.modeFlag = (!this.modeFlag);
		if (modeFlag==true) {
			btn.setText("Smell Food");
		}else {
			btn.setText("Random");
		}
	}
	
	private void loadAllToGrid() {
		for (Bug b: bugs) {
			grid.getChildren().add(b.getSymbol());	
		}
		for(Plant p: plants) {
			grid.getChildren().add(p.getSymbol());
		}
		for(Obstacle o: obstacles) {
			grid.getChildren().add(o.getSymbol());
		}
	}

	private void initializeRandom() {
		for (int i = 0; i < 5; i++) { //5 obstacles
			double x = (double)(Math.random()*width);
			double y = (double)(Math.random()*height);
			double s = (double)(Math.random()*30); //obstacle size [1-20]
			obstacles.add(new Obstacle(x, y, s));
		}
		
		for (int i = 0; i < 10; i++) { //10 bugs
			double x = (double)(Math.random()*width);
			double y = (double)(Math.random()*height);
			if(this.onObstacle(x, y)==false) {
				
				if (Math.random() < 0.2) {
					bugs.add(new Butterfly(x, y, 4));
				} else if (Math.random()<0.75){
					bugs.add(new Ant(x, y, 2));
				} else {
					bugs.add(new Beetle(x, y, 6));
				} 
			}	
		}
		
		for (int i = 0; i < 10; i++) { //5 plants
			double x = (double)(Math.random()*width);
			double y = (double)(Math.random()*height);
			if(this.onObstacle(x, y)!=true) {
				
				if (Math.random()<0.5) {
					double s = (double)(Math.random()*50); //plant size [1-20]
					plants.add(new Plant(x, y, s));
				}else {
					plants.add(new Plant(x, y, 15));
				}
			}
		}
	}
	
	public boolean inBoundary(double x, double y, double size) {
		if (x-size>0 && x+size<width-1 && y-size>0 && y+size<height-1) {
			return true;
		}
		return false;
	}
	
	public boolean onObstacle(double x, double y) {
		for (Obstacle o : obstacles) {
			if ((x>=o.getX() && x<=o.getX()+o.getSize()) && (y>=o.getY() && y<=o.getY()+o.getSize())) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
