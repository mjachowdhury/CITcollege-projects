/*
 * This class will create a circle and a text message written inside the circle
 */
package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.stage.Stage;

// java 8 code.
public class CircleApp extends Application {

	@Override
	public void start(final Stage myStage) throws Exception {
		Text text = createText("Circle");//Creating the text for circle and calling the method createText
		Circle circle = encircle(text);//creating the circle and calling the method encircle putting the text

		StackPane layout = new StackPane();//creating the StackPane with height and width
		layout.setPrefHeight(400);
		layout.setPrefWidth(500);
		layout.getChildren().addAll(circle, text);//Adding the circle and text with StackPane
		layout.setPadding(new Insets(20));

		myStage.setScene(new Scene(layout, Color.LIGHTBLUE));
		myStage.show();
	}

	//Creating the text method
	private Text createText(String string) {
		Text text = new Text(string);
		text.setBoundsType(TextBoundsType.VISUAL);
		text.setStyle("-fx-font-family: \"Times New Roman\";" 
					+ "-fx-font-style: italic;" 
					+ "-fx-font-size: 48px;");

		return text;
	}

	//Creating the Circle method
	private Circle encircle(Text text) {
		Circle circle = new Circle();

		circle.setFill(Color.ORCHID);
		final double PADDING = 10;
		circle.setRadius(getWidth(text) / 2 + PADDING);//Calling the getWidth method

		return circle;
 
	}

	//Creating the width of the circle method
	private double getWidth(Text text) {
		new Scene(new Group(text));
		text.applyCss();

		return text.getLayoutBounds().getWidth();
	}
}//ending the CircleApp 