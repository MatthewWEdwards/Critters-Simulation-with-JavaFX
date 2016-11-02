package assignment5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
	
	public static Pane root = new Pane();


	public static void main(String[] args) {
		//Launch controller
		
		//TODO: write controller code
		
		//End controller code
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
        
		int btnHeight = 30;
		int btnWidth = 150;
		
		
		for(int i = 0; i < 20000; i++){
			Critter.makeCritter("Craig");
		}
		
		Button displayBtn = new Button();
		displayBtn.relocate(50, 300);
		displayBtn.setMinSize(btnWidth, btnHeight);
		root.getChildren().add(displayBtn);
		displayBtn.setText("Display World");
		displayBtn.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent event) {
	    		Critter.displayWorld();
	        }
		});    
		
		
		
		
		Button makeCritterBtn = new Button();
		makeCritterBtn.relocate(50, 200);
		makeCritterBtn.setMinSize(btnWidth, btnHeight);
		root.getChildren().add(makeCritterBtn);
		makeCritterBtn.setText("Make Critter");			
		makeCritterBtn.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent event) {
	    		Critter.displayWorld();
	        }
		});    

		

	
		
		

		
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}



    
}
