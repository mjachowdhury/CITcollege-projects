/**
 * Student Name - Mohammed Alom
 * Student No - R 00144214
 * Course - Software Development (Object Oriented Programming Java - CIT Second Year Project Second Part Dental Management with Database Connection)
 */
package main;

import controller.Controller;
import database.DBConnection;
import gui.GUI;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This is the main class. Program will run from here.
 * 
 * @author Mohammed
 *
 */
public class MainProgram extends Application {

	@Override
	public void start(Stage primaryStage) {
		// User name and password for the mysql workbench
		DBConnection db = new DBConnection("root", "mysql_2018");

		Controller control = new Controller(db);
		GUI gui = new GUI(control, primaryStage);
		control.setGUI(gui);
		gui.initSceneLogin();
		gui.initSceneLoginButtons();
		gui.initGUI();
	}

	public static void main(String[] args) {
		launch(args);
	}
}