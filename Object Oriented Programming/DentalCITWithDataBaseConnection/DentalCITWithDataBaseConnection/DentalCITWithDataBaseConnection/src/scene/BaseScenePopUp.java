package scene;
/**
 * Student Name - Mohammed Alom
 * Student No - R 00144214
 * Course - Software Development (Object Oriented Programming Java - CIT Second Year Project Second Part Dental Management with Database Connection)
 */

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * This is abstract class for the all the pop up scene
 * 
 * @author Mohammed
 *
 */
abstract public class BaseScenePopUp {
	private Scene scene;
	private BorderPane mainPane;
	private Controller control;

	public BaseScenePopUp(Controller newControl, int xSize, int ySize) {
		this.control = newControl;
		this.mainPane = new BorderPane();
		this.scene = new Scene(this.mainPane, xSize, ySize);
	}

	public Controller getControl() {
		return control;
	}

	public BorderPane getMainPane() {
		return this.mainPane;
	}

	public Scene getScene() {
		return this.scene;
	}

}
