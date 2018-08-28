package application;
/**
 * Student Name - Mohammed Alom
 * Student No - R 00144214
 * Course - Software Development (Object Oriented Programming Java - CIT Second Year Project Second Part Dental Management with Database Connection)
 */

import javafx.scene.layout.VBox;

/**
 * This class will style the different room based on the requirements on the task
 * @author Mohammed
 *
 */
public class StylingUserInterface {

	public void setRoomStyle(String roomType, VBox room, boolean activeAlarm) {
		if (activeAlarm == false) {
			if (roomType.equals("Sitting Room")) {
				room.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 3;" + "-fx-border-color: yellow;"
						+ "-fx-background-color: white;");
			}
			if (roomType.equals("Kitchen")) {
				room.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 3;" + "-fx-border-color: blue;"
						+ "-fx-background-color: white;");
			}
			if (roomType.equals("Bedroom")) {
				room.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 3;" + "-fx-border-color: green;"
						+ "-fx-background-color: white;");
			}
			if (roomType.equals("Living Room")) {
				room.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 3;" + "-fx-border-color: purple;"
						+ "-fx-background-color: white;");
			}
		} else if (activeAlarm == true) {
			if (roomType.equals("Sitting Room")) {
				room.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 3;" + "-fx-border-color: yellow;"
						+ "-fx-background-color: red;");
			}
			if (roomType.equals("Kitchen")) {
				room.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 3;" + "-fx-border-color: blue;"
						+ "-fx-background-color: red;");
			}
			if (roomType.equals("Bedroom")) {
				room.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 3;" + "-fx-border-color: green;"
						+ "-fx-background-color: red;");
			}
			if (roomType.equals("Living Room")) {
				room.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 3;" + "-fx-border-color: purple;"
						+ "-fx-background-color: red;");
			}
		}
	}
}
