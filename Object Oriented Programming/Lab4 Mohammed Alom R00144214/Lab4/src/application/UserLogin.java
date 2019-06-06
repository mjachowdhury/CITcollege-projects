package application;

/**
 * Student Name - Mohammed Alom
 * Student No - R 00144214
 * Course - Software Development (Object Oriented Programming Java - CIT Second Year Project Second Part Dental Management with Database Connection)
 */
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class is about to user login wi the password 2018 if password is correct
 * it will take to the next scene which is user display screen. if user entred
 * wrong pin then it will display alert message about wrong pin.
 * 
 * @author Mohammed
 *
 */
public class UserLogin {
	
	public void startIntruder(Stage stage) {

		BorderPane loginPane = new BorderPane();
		VBox loginVBox = new VBox();

		Label pin = new Label("Enter Pin");
		PasswordField password = new PasswordField();
		password.setMaxWidth(150);
		password.setFocusTraversable(false);

		password.setPromptText("Pin Code");

		password.setOnKeyPressed((event) -> {
			if (event.getCode() == KeyCode.ENTER) {
				checkUserLoginDetails(password, stage); // calling the method
			}
		});

		loginVBox.getChildren().addAll(pin, password);
		loginVBox.setAlignment(Pos.CENTER);
		loginPane.setTop(loginVBox);
		Scene scene1 = new Scene(loginPane, 500, 500);
		stage.setTitle("User Login Screen");
		stage.setScene(scene1);
	}

	/**
	 * This method will verify the user pin
	 * 
	 * @param password
	 * @param st
	 */
	public void checkUserLoginDetails(PasswordField password, Stage st) {
		String pin = "2018";
		System.out.println(password.getText());
		if (password.getText().equals(pin)) {
			new UserDisplayScreen().start(st);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Invalid Pin");
			alert.setContentText("Invalid Pin, Please Try Again");
			alert.showAndWait();
			password.clear();
		}
	}
}
