package application;

/*
 * This class will create different shapes.
 */
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class DifferentShapes extends Application {
	 
	@Override
	public void start(Stage stage) {

		//Creating the BordePane
		BorderPane pane = new BorderPane();

		// Creating a Hexagon
		Polygon hexagon = new Polygon();

		// Adding coordinates to the polygon
	    hexagon.getPoints().addAll(new Double[]{ 	         
	    		  200.0, 50.0, 
	    		  400.0, 50.0, 
	    		  450.0, 150.0,          
	    		  400.0, 250.0, 
	    		  200.0, 250.0,                   
	    		  150.0, 150.0, 
	      }); 
		BorderPane.setAlignment(hexagon, Pos.CENTER);//fixing the position of the hexagon
		pane.setTop(hexagon);
		hexagon.setFill(Color.BLUE);

		// Drawing a Rectangle with heighrt and width
		Rectangle rectangle = new Rectangle();

		rectangle.setWidth(150.0f);
		rectangle.setHeight(250.0f);

		BorderPane.setAlignment(rectangle, Pos.CENTER_LEFT); //fixing the position of the rectangle
		pane.setLeft(rectangle);

		// Setting the color
		rectangle.setFill(Color.ORANGE);
		//rectangle.setStroke(Color.BLUE);

		// Drawing a Rectangle
		Rectangle rectangle2 = new Rectangle();

		rectangle2.setWidth(250.0f);
		rectangle2.setHeight(250.0f);
		rectangle2.setArcWidth(15);
		rectangle2.setArcHeight(25);

		// Setting the color
		rectangle2.setFill(Color.RED);

		BorderPane.setAlignment(rectangle2, Pos.CENTER);
		pane.setCenter(rectangle2);

		// Creating cross line
		LinePane line1 = new LinePane();
		// pane.setAlignment(line1, Pos.CENTER);
		pane.setRight(line1);
		line1.setPrefWidth(400);

		// Creating a Pentagon
		Polygon polygon = new Polygon();

		// Adding coordinates to the polygon
		polygon.getPoints().addAll(new Double[] { 
			175.0,45.0,225.0,45.0,
			265.0,75.0,265.0,125.0,
			225.0,155.0,175.0,155.0,
			135.0,125.0,135.0,75.0
			 
	     }); 

		BorderPane.setAlignment(polygon, Pos.BOTTOM_CENTER);
		pane.setBottom(polygon);
		polygon.setFill(Color.BROWN);

		// Creating a scene object
		Scene scene = new Scene(pane, 1000, 1000,Color.AQUA);

		// Setting title to the Stage
		stage.setTitle("Drawing Different Shapes");

		// Adding scene to the stage
		stage.setScene(scene);

		// Displaying the contents of the stage
		stage.show();

	 
	}

 
	//This method is for creating the cross line
	class LinePane extends Pane {
		public LinePane() {
			Line line1 = new Line(150, 150, 150, 150);
			line1.endXProperty().bind(widthProperty().subtract(90));
			line1.endYProperty().bind(heightProperty().subtract(200));
			line1.setStrokeWidth(40);
			line1.setStroke(Color.GREEN);
			getChildren().add(line1);

			Line line2 = new Line(150, 150, 150, 150);
			line2.startXProperty().bind(widthProperty().subtract(90));
			line2.endYProperty().bind(heightProperty().subtract(200));
			line2.setStrokeWidth(40);
			line2.setStroke(Color.GREEN);
			getChildren().add(line2);
		}
	}
}
