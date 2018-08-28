package application;
/**
 * Student Name - Mohammed Alom
 * Student No - R 00144214
 * Course - Software Development (Object Oriented Programming Java - CIT Second Year Project Second Part Dental Management with Database Connection)
 */

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UserDisplayScreen {

	// Creating all the rooms with VBox

	VBox sittingRoomBox = new VBox();
	VBox kitchenBox = new VBox();
	VBox livingRoomBox = new VBox();
	VBox bedroomBox = new VBox();

	// ON/OFF button

	Button onOff = new Button("ON/OFF");
	Boolean switchOnOff = false;

	public void start(Stage stage) {
		BorderPane bp2 = new BorderPane();

		// EXIT button

		Button exit = new Button("EXIT");
		exit.setPrefWidth(100);
		exit.setOnAction(e -> stage.close());

		onOff.setOnAction(e -> activateOnOffButton());
		onOff.setPrefWidth(100);

		HBox onOffBox = new HBox();
		// setting the on/off button
		onOffBox.setAlignment(Pos.TOP_LEFT);
		onOffBox.getChildren().add(onOff);

		HBox exitBox = new HBox();
		// setting the on/off button
		exitBox.setAlignment(Pos.TOP_RIGHT);
		exitBox.getChildren().add(exit);

		// adding both on/off and exit
		HBox top = new HBox();
		top.getChildren().addAll(onOffBox, exitBox);
		top.setSpacing(250);
		bp2.setTop(top);

		VBox center = new VBox(); //adding the rooms to the center

		center.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 3;" + "-fx-border-color: black;"
				+ "-fx-background-color: white;");
		center.setMaxWidth(450); 
		center.setMaxHeight(300); 
		
		HBox centerTop = new HBox();
		HBox centerBottom = new HBox();
		centerTop.getChildren().add(createRoom("Sitting Room", sittingRoomBox));
		centerTop.getChildren().add(createRoom("Kitchen", kitchenBox));
		centerTop.setSpacing(50);
		centerBottom.getChildren().add(createRoom("Bedroom", bedroomBox));
		centerBottom.getChildren().add(createRoom("Living Room", livingRoomBox));
		centerBottom.setSpacing(50);
		center.getChildren().addAll(centerTop, centerBottom);
		center.setSpacing(50);

		bp2.setCenter(center);

		Scene scene2 = new Scene(bp2, 600, 500);// size of hte scene
		stage.setScene(scene2);
		stage.setTitle("Intruder Alarm Program");
	}
 
	/**
	 * This method will create the room
	 * @param roomType
	 * @param room
	 * @return
	 */

	public VBox createRoom(String roomType, VBox room) {
		room.setMinHeight(125);
		room.setMinWidth(200);
		room.setMaxHeight(125);
		room.setMaxWidth(200);
		room.setPadding(new Insets(2, 0, 0, 3));
		new StylingUserInterface().setRoomStyle(roomType, room, false);
		Label roomName = new Label(roomType);
		room.getChildren().add(roomName);
		return room;
	}

	/**
	 * THis method will work during when the user will press the ON/OFF button
	 */
	public void activateOnOffButton() {
		if (switchOnOff == false) {
			onOff.setStyle("-fx-text-fill: red;");
			addRemoveRadioButtons(sittingRoomBox, 1, "Sitting Room");
			addRemoveRadioButtons(kitchenBox, 1, "Kitchen");
			addRemoveRadioButtons(bedroomBox, 1, "Bedroom");
			addRemoveRadioButtons(livingRoomBox, 1, "Living Room");
			switchOnOff = true;
		} else {
			onOff.setStyle("-fx-text-fill: black;");
			addRemoveRadioButtons(sittingRoomBox, 0, "Sitting Room");
			addRemoveRadioButtons(kitchenBox, 0, "Kitchen");
			addRemoveRadioButtons(bedroomBox, 0, "Bedroom");
			addRemoveRadioButtons(livingRoomBox, 0, "Living Room");
			switchOnOff = false;
		}
	}

	/**
	 * adding add and remove button functionality 
	 * @param room
	 * @param choice
	 * @param roomType
	 */
	private void addRemoveRadioButtons(VBox room, int choice, String roomType) {
		if (choice == 0) {
			room.getChildren().clear();
			Label roomName = null;
			if (roomType.equals("Sitting Room")) {
				roomName = new Label(roomType);
			}
			if (roomType.equals("Kitchen")) {
				roomName = new Label(roomType);
			}
			if (roomType.equals("Bedroom")) {
				roomName = new Label(roomType);
			}
			if (roomType.equals("Living Room")) {
				roomName = new Label(roomType);
			}
			room.getChildren().add(roomName);
		}
		if (choice == 1) {
			GridPane buttonGrid = new GridPane();
			buttonGrid.setPadding(new Insets(15, 10, 0, 0));
			RadioButton onButton = new RadioButton();
			RadioButton offButton = new RadioButton();
			offButton.setSelected(true);

			Button intruder = new Button("INTRUDER");
			intruder.setPrefSize(80, 80);
			HBox intruderBox = new HBox();

			offButton.setOnAction(e -> offButtonPressed(offButton, onButton, room, intruder, intruderBox, roomType));

			onButton.setOnAction(e -> onButtonPressed(offButton, onButton, room, intruder, intruderBox));

			buttonGrid.add(new Label("On"), 0, 0);
			buttonGrid.add(onButton, 1, 0);
			buttonGrid.add(new Label("Off"), 0, 1);
			buttonGrid.add(offButton, 1, 1);
			buttonGrid.setStyle("-fx-background-color: white");
			buttonGrid.setHgap(2);
			buttonGrid.setVgap(2);
			buttonGrid.setMaxSize(60, 10);
			buttonGrid.setPadding(new Insets(0, 0, 0, 5));
			room.getChildren().add(buttonGrid);

			intruder.setOnAction(e -> activateAlarm(roomType, room));

		}
	}

	/**
	 * This method will verify whether off button button is pressed or not
	 * 
	 * @param off
	 * @param on
	 * @param room
	 * @param intruder
	 * @param intruderBox
	 * @param roomType
	 */

	public void offButtonPressed(RadioButton off, RadioButton on, VBox room, Button intruder, HBox intruderBox,
			String roomType) {
		if (off.isSelected() == true) {
			on.setSelected(false);
		}
		if (off.isSelected() == false) {
			on.setSelected(false);
			off.setSelected(true);
		}
		try {
			intruderBox.getChildren().remove(intruder);
			new StylingUserInterface().setRoomStyle(roomType, room, false);
		} catch (Exception buttonAlreadyAdded) {
			System.out.println("Intruder Button does not exist in this room at this time");
		}
	}

	/**
	 * This method will verify whether on button is pressed or not
	 * 
	 * @param off
	 * @param on
	 * @param room
	 * @param intruder
	 * @param intruderBox
	 */

	public void onButtonPressed(RadioButton off, RadioButton on, VBox room, Button intruder, HBox intruderBox) {
		if (on.isSelected() == false) {
			off.setSelected(true);
			on.setSelected(true);
		}
		if (on.isSelected() == true) {
			off.setSelected(false);
		}
		try {
			intruderBox.setAlignment(Pos.TOP_RIGHT);
			intruderBox.setPadding(new Insets(0, 3, 3, 0));
			intruderBox.getChildren().add(intruder);
			room.getChildren().add(intruderBox);
		} catch (Exception buttonAlreadyAdded) {
			System.out.println("This button has already been added to the room");
		}
	}

	/**
	 * This method takes parameter VBox room and String roomtype method will
	 * invoke when intruder alarm is pressed. method will also set the flashing
	 * time
	 * 
	 * @param roomType
	 * @param room
	 */

	public void activateAlarm(String roomType, VBox room) {
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.seconds(0.5),
						evt -> new StylingUserInterface().setRoomStyle(roomType, room, true)),
				new KeyFrame(Duration.seconds(1.0),
						evt -> new StylingUserInterface().setRoomStyle(roomType, room, false)));
		timeline.setCycleCount(3);
		timeline.play();

		PauseTransition delay = new PauseTransition(Duration.seconds(3.5));
		delay.setOnFinished(e -> new StylingUserInterface().setRoomStyle(roomType, room, true));
		delay.play();

	}
}
