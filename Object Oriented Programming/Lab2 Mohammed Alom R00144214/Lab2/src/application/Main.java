/*
 * This is the Main class 
 */
package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	// lunching the program
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {

		//Creating instance of DifferentShapes class
		DifferentShapes differentShapes = new DifferentShapes();
		Stage stage1 = new Stage(); //Creating the stage for different shapes
		stage1.setTitle("Different Shapes"); //Setting the title of the stage
		differentShapes.start(stage1); //Starting the stage1

		
		//Creating instance of CircleApp class
		CircleApp c1 = new CircleApp();
		Stage stage2 = new Stage(); // Create a new stage
		stage2.setTitle("My Circle App"); // Set the stage title
		try {
			c1.start(stage2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		//Creating instance of RectangleApp class
		RectangleApp r1 = new RectangleApp();
		Stage stage3 = new Stage();// Create a new stage
		stage3.setTitle("Rectagle Window");//Setting the title of the stage
		r1.start(stage3);
		
	}//ending the start menthod
}//ending the main
