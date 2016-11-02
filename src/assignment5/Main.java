package assignment5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
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
        
		Button btn = new Button();
		root.getChildren().add(btn);
		btn.setText("Display World");
		
	
		for(int i = 0; i < 90000; i++){
			Critter.makeCritter("Craig");
		}
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
			

            
        	@Override
            public void handle(ActionEvent event) {
        		Critter.displayWorld();
        //    		display_graphics.setFill(Color.GREEN);
        //		display_graphics.fillRect(200, 200, 200, 200);

            }
		});    
		
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}



    
}
