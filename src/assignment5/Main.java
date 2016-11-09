
package assignment5;

import java.io.BufferedReader;

import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.TimerTask;
import java.util.*;

import javafx.scene.text.Font;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Screen;


public class Main extends Application {
	
	public static Pane root = new Pane();
	public static Text stepCountText = null;
	public static boolean go = false;
	Timer timer = new Timer();
	public static int animationFrame = 1;

	public static void main(String[] args) {
		//Launch controller
		
		//TODO: write controller code
		
		//End controller code
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	
		double screenSizeHeight = Screen.getPrimary().getVisualBounds().getHeight();
		double screenSizeWidth = Screen.getPrimary().getVisualBounds().getWidth();
		int btnHeight = (int) (.05*screenSizeHeight); //30
		int btnWidth = (int) (.1*screenSizeWidth);//150;
		
		ObservableList<String> crittersAvailable = FXCollections.observableArrayList();
		String path = System.getProperty("user.dir") + "/src/assignment5"; // on linux /src/assignment5
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
			selectCritter.relocate((.17*screenSizeWidth), (.33*screenSizeHeight)); //275, 250
			root.getChildren().add(selectCritter);
			selectCritter.setValue("Craig");
			
			
		Text titleText = new Text((.02*screenSizeWidth), (.04*screenSizeHeight), "Project 5 Critters 2\nRegan Stehle and Matthew Edwards");//25, 25
		titleText.setFont(new Font(25));
		root.getChildren().add(titleText);
		
		Text makeCritterText = new Text((.02*screenSizeWidth), (.32*screenSizeHeight), "Enter number of critters"); //50, 245
		makeCritterText.setFont(new Font(15));
		root.getChildren().add(makeCritterText);
		
		Text selectCritterText = new Text((.17*screenSizeWidth), (.32*screenSizeHeight), "Select critter to make or run stats"); //275, 245
		selectCritterText.setFont(new Font(15));
		root.getChildren().add(selectCritterText);
		
		Text animationSpeedText = new Text((.17*screenSizeWidth), (.62*screenSizeHeight), "Enter number of steps per frame"); 
		animationSpeedText.setFont(new Font(15));
		root.getChildren().add(animationSpeedText);
		
		Text stepText = new Text((.02*screenSizeWidth), (.52*screenSizeHeight), "Enter number of steps"); //50, 395
		stepText.setFont(new Font(15));
		root.getChildren().add(stepText);
		
		stepCountText = new Text((.02*screenSizeWidth), (.68*screenSizeHeight), "Steps since start: 0"); //50, 500
		stepCountText.setFont(new Font(15));
		root.getChildren().add(stepCountText);
		
		Text seedText = new Text((.02*screenSizeWidth), (.12*screenSizeHeight), "Enter seed"); //50, 95
		stepText.setFont(new Font(15));
		root.getChildren().add(seedText);
		
	    ByteArrayOutputStream grabStats = new ByteArrayOutputStream();
		PrintStream statsOut = new PrintStream(grabStats);
		System.setOut(statsOut);
	    Text statsText = new Text();
	    statsText.relocate((.17*screenSizeWidth), (.46*screenSizeHeight)); //275, 375
	    stepText.setFont(new Font(15));
		root.getChildren().add(statsText);
		statsText.setWrappingWidth((.12*screenSizeWidth));
		
		Text makeErrorSeed = new Text((.02*screenSizeWidth), (.26*screenSizeHeight), "Invalid number"); //50, 180
		makeErrorSeed.setFont(new Font(15));
		makeErrorSeed.setFill(Color.RED);
		
		Text makeErrorMake = new Text((.02*screenSizeWidth), (.46*screenSizeHeight), "Invalid number"); //50, 330
		makeErrorMake.setFont(new Font(15));
		makeErrorMake.setFill(Color.RED);
		
		Text makeErrorStep = new Text((.02*screenSizeWidth), (.66*screenSizeHeight), "Invalid number"); //50, 480
		makeErrorStep.setFont(new Font(15));
		makeErrorStep.setFill(Color.RED);
		
		Text makeErrorAnimation = new Text((.17*screenSizeWidth), (.76*screenSizeHeight), "Invalid number"); //50, 480
		makeErrorAnimation.setFont(new Font(15));
		makeErrorAnimation.setFill(Color.RED);
		
			
		TextField numCritters = new TextField();
		root.getChildren().add(numCritters);
		numCritters.relocate((.02*screenSizeWidth), (.33*screenSizeHeight)); //50, 250
		
		TextField numTimeSteps = new TextField();
		root.getChildren().add(numTimeSteps);
		numTimeSteps.relocate((.02*screenSizeWidth), (.53*screenSizeHeight)); //50, 400
		
		TextField seedField = new TextField();
		root.getChildren().add(seedField);
		seedField.relocate((.02*screenSizeWidth), (.13*screenSizeHeight)); //50, 100
		
		TextField animationField = new TextField();
		root.getChildren().add(animationField);
		animationField.relocate((.17*screenSizeWidth), (.63*screenSizeHeight));
		
		Button makeCritterBtn = new Button();
		makeCritterBtn.relocate((.02*screenSizeWidth), (.38*screenSizeHeight)); //50, 285
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
		    				root.getChildren().add(makeErrorMake);
		    				return;
		    			}else{
		    				root.getChildren().remove(makeErrorMake);
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
		quitBtn.relocate((.02*screenSizeWidth), (.8*screenSizeHeight)); //50, 900
		quitBtn.setMinSize(btnWidth, btnHeight);
		root.getChildren().add(quitBtn);
		quitBtn.setText("Quit");			
		quitBtn.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent event) {    
	    		System.exit(1);
	    	}
		});

		Button timeStepBtn = new Button();
		timeStepBtn.relocate((.02*screenSizeWidth), (.58*screenSizeHeight)); //50, 435
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
		    				root.getChildren().add(makeErrorStep);
	    					return;
	    				}else{
		    				root.getChildren().remove(makeErrorStep);
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
		
		Button startAnimationBtn = new Button();
		startAnimationBtn.relocate((.17*screenSizeWidth), (.68*screenSizeHeight)); //50, 435
		startAnimationBtn.setMinSize(btnWidth, btnHeight);
		root.getChildren().add(startAnimationBtn);
		startAnimationBtn.setText("Start Animation");			
		startAnimationBtn.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent event) {
	    			int numToStep = 1;
	    			String textBox2 = animationField.getText();
	    			if(!textBox2.isEmpty()){
	    				if(!checkIfInt(textBox2.trim(), 0)){
		    				root.getChildren().add(makeErrorAnimation);
	    					return;
	    				}else{
		    				root.getChildren().remove(makeErrorAnimation);
	    					numToStep = Integer.parseInt(textBox2.trim());
	    					if(numToStep == 0){
	    						numToStep = 1;
	    					}
	    				}
	    			}
	    			go = true;
	    			//animationFrame = numToStep;
	    			
	    			doAnimation(numToStep, primaryStage);
	    		
	    			
	    			
	        }
		});   
		
		Button stopAnimationBtn = new Button();
		stopAnimationBtn.relocate((.17*screenSizeWidth), (.8*screenSizeHeight)); //50, 900
		stopAnimationBtn.setMinSize(btnWidth, btnHeight);
		root.getChildren().add(stopAnimationBtn);
		stopAnimationBtn.setText("Stop Animation");			
		stopAnimationBtn.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent event) {    
	    		go = false;
	    	}
		});
		
		Button displayBtn = new Button();
		displayBtn.relocate((.17*screenSizeWidth), (.19*screenSizeHeight)); //275, 135
		displayBtn.setMinSize(btnWidth, btnHeight);
		root.getChildren().add(displayBtn);
		displayBtn.setText("Display World");
		displayBtn.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent event) {
	    		Critter.displayWorld();
	        }
		});    
		
		Button seedBtn = new Button();
		seedBtn.relocate((.02*screenSizeWidth), (.19*screenSizeHeight)); //50, 135
		seedBtn.setMinSize(btnWidth, btnHeight);
		root.getChildren().add(seedBtn);
		seedBtn.setText("Set Seed");
		seedBtn.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent event) {
	    		String seed = seedField.getText();
	    		if(!checkIfInt(seed.trim(), 0)){ // checks if the second part of the current string is solely an integer
    				root.getChildren().add(makeErrorSeed);
	        		System.out.println("error processing: " + seed);
				}
	    		else{
    				root.getChildren().remove(makeErrorSeed);
        			Critter.setSeed(Integer.parseInt(seed.substring(0, seed.length())));
	    		}

	        }
		});    
		
		Button statsBtn = new Button();
		statsBtn.relocate((.17*screenSizeWidth), (.38*screenSizeHeight)); //275, 285
		statsBtn.setMinSize(btnWidth, btnHeight);
		root.getChildren().add(statsBtn);
		statsBtn.setText("Display Stats");
		statsBtn.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	    	 public void handle(ActionEvent event) {
	    		try {
					Class<?> critter = Class.forName("assignment5." + selectCritter.getValue());
					Method statsm = critter.getMethod("runStats", List.class);
					statsm.invoke(critter, Critter.getInstances(selectCritter.getValue()));
					statsText.setText("");
					String nextStatsLine = grabStats.toString();
					statsText.setText("Stats:\n" + nextStatsLine);
					grabStats.reset();

				} catch (InvalidCritterException | ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException  e) {}
	        }
		});    
		

	

		
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setScene(new Scene(root, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight()));
		primaryStage.show();
		
		
		
	}
	
	
	
	public static void doAnimation(int numToStep, Stage primaryStage){
		
		do {
			for(int i = 0; i < numToStep; i++)
				Critter.worldTimeStep();
			Critter.displayWorld();	
			
			try {
			Thread.sleep(5000);
			} catch (InterruptedException e) {
				go = false;
			}
			
		} while(go);
		
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
