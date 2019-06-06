/*
 * This class will create a Rectangle and text will show inside the rectangle
 */
package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RectangleApp extends Application {

	@Override
	public void start(Stage primaryStage) {

		Rectangle rectangle = new Rectangle(200, 100, Color.CORNSILK); //Creating the Rectangle with color
		//setting the rectangle ArcWidth and Height
		rectangle.setArcWidth(30);
		rectangle.setArcHeight(30);
		rectangle.setEffect(new DropShadow(10, 5, 5, Color.GRAY));

		Text text = new Text("Rectangle");//creating the text
		text.setFont(new Font("Verdana Bold", 18));
		// text.setEffect(new Reflection());

		StackPane stackPane = new StackPane(); //Creating the StackPane with height and width
		stackPane.setPrefHeight(200);
		stackPane.setPrefWidth(400);
		stackPane.getChildren().addAll(rectangle, text); //adding the rectangle and text inside the stackpane

		final Scene scene = new Scene(stackPane, Color.LIGHTBLUE);//Creating the new Scene with background color
		primaryStage.setTitle("My Rectangle App");

		primaryStage.setScene(scene);
		primaryStage.show();
	}

}