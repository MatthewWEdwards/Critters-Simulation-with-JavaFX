package assignment5;

import java.io.File;
import java.lang.reflect.Modifier;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Screen;


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
		
		
		
		ObservableList<String> crittersAvailable = FXCollections.observableArrayList();
		String path = System.getProperty("user.dir") + "\\src\\assignment5"; // on linux /src/assignment5
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

			String className = new String();
			//TODO: make this reflection work with all files (headers)
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()){
		    	className = listOfFiles[i].getName();
		    	Class<?> testClass = Class.forName("assignment5." + className.substring(0, className.length() - 5));
		    	//assert(false);
		    	
		    	if(Modifier.isAbstract(testClass.getModifiers()) || className.equals("InvalidCritterException.java")){
		    		continue;
		    	}

				Object newCrit = testClass.newInstance();
				if(newCrit instanceof Critter){
					crittersAvailable.add(listOfFiles[i].getName().substring(0, className.length() - 5));
				}
		      }
		    }
		    final ComboBox<String> selectCritter = new ComboBox<>(crittersAvailable);
		    selectCritter.setEditable(true);
			selectCritter.relocate(0, 250);
			root.getChildren().add(selectCritter);
			selectCritter.setValue("Craig");
			
			TextField numCritters = new TextField();
			root.getChildren().add(numCritters);
			numCritters.relocate(0, 100);
			

			
		Button makeCritterBtn = new Button();
		makeCritterBtn.relocate(50, 200);
		makeCritterBtn.setMinSize(btnWidth, btnHeight);
		root.getChildren().add(makeCritterBtn);
		makeCritterBtn.setText("Make Critter");			
		makeCritterBtn.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	    	 public void handle(ActionEvent event) {
	    		try {
	    			int numToCreate = 1;
	    			String textBox = numCritters.getText();
	    			if(!textBox.isEmpty()){
		    			if(!checkIfInt(textBox.trim(), 0)){
		    				return;
		    			}else{
		    				numToCreate = Integer.parseInt(textBox.trim());
		    				if(numToCreate == 0){
		    					numToCreate = 1;
		    				}
		    			}
	    			}
	    			for(int i = 0; i < numToCreate; i++)
	    				Critter.makeCritter(selectCritter.getValue());
	    			Critter.displayWorld();
				} catch (InvalidCritterException e) {
				}
	        }
		});    
		
		Button quitBtn = new Button();
		quitBtn.relocate(50, 350);
		quitBtn.setMinSize(btnWidth, btnHeight);
		root.getChildren().add(quitBtn);
		quitBtn.setText("Quit");			
		quitBtn.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent event) {    
	    		System.exit(1);
	    	}
		});

		
		TextField numTimeSteps = new TextField();
		root.getChildren().add(numTimeSteps);
		numTimeSteps.relocate(200, 100);
		
		Button timeStepBtn = new Button();
		timeStepBtn.relocate(250, 200);
		timeStepBtn.setMinSize(btnWidth, btnHeight);
		root.getChildren().add(timeStepBtn);
		timeStepBtn.setText("Do Time Step(s)");			
		timeStepBtn.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent event) {
	    			int numToStep = 1;
	    			String textBox2 = numTimeSteps.getText();
	    			if(!textBox2.isEmpty()){
	    				if(!checkIfInt(textBox2.trim(), 0)){
	    					return;
	    				}else{
	    					numToStep = Integer.parseInt(textBox2.trim());
	    					if(numToStep == 0){
	    						numToStep = 1;
	    					}
	    				}
	    			}
	    		
	    			for(int i = 0; i < numToStep; i++)
	    				Critter.worldTimeStep();
	    			Critter.displayWorld();
				
	        }
		});   
	
		
		

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setScene(new Scene(root, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight()));
		primaryStage.show();
	}


	public static boolean checkIfInt(String input, int index){
		if(index >= input.length()){
			return false;
		}
		charLoop:
			for(int i = index; i < input.length(); i++){
				for(char k = '0'; k <= '9'; k++){
	        		if(input.charAt(i) == k){
	        			continue charLoop;
	        		}     	
				}
				return false;
			}
			return true;
	}
    
}
