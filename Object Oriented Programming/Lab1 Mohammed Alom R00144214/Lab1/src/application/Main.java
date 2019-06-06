package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class Main extends Application {
	@Override
	public void start(Stage stage) {

		// Drawing a Rectangle
		Rectangle rectangle = new Rectangle();

		// Setting the properties of the rectangle
		rectangle.setX(450.0f);
		rectangle.setY(75.0f);
		rectangle.setWidth(300.0f);
		rectangle.setHeight(150.0f);

		// Setting the color
		rectangle.setFill(Color.RED);

		// rectangle.setStroke(Color.BLUE);

		
		// Drawing a Circle
		Circle circle = new Circle();

		// Setting the properties of the circle
		circle.setCenterX(200.0f);
		circle.setCenterY(150.0f);
		circle.setRadius(100.0f);

		// Setting the color
		circle.setFill(Color.GREEN);

		// Creating a Group object
		Group root = new Group(rectangle, circle);

		// Creating a scene object
		Scene scene = new Scene(root, 1000, 300);

		// Setting title to the Stage
		stage.setTitle("Drawing Shapes");

		// Adding scene to the stage
		stage.setScene(scene);

		// Displaying the contents of the stage
		stage.show();
	}

	// lunching the program
	public static void main(String args[]) {
		launch(args);
	}
}