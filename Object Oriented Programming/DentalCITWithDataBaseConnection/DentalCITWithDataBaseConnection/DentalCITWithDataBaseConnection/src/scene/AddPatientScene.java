package scene;

/**
 * Student Name - Mohammed Alom
 * Student No - R 00144214
 * Course - Software Development (Object Oriented Programming Java - CIT Second Year Project Second Part Dental Management with Database Connection)
 */

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This class is about the adding patient scene
 * 
 * @author Mohammed
 *
 */
public class AddPatientScene extends BaseScenePopUp {
	private VBox vBoxContents;
	private HBox hBoxName;
	private HBox hBoxAddress;
	// private HBox hBoxContact;

	private HBox hboxButtons;
	private Label labelName;
	private Label labelAddress;
	// private Label labelContact;

	private TextField textName;
	private TextField textAddress;
	// private TextField textContact;

	private Button buttonAdd;
	private Button buttonCancel;
	private Insets inset1, inset2/* , inset3 */;

	// Adding patient scene method

	public AddPatientScene(Controller control, int xSize, int ySize) {
		super(control, xSize, ySize);
		this.vBoxContents = new VBox();
		this.hBoxName = new HBox();
		this.hBoxAddress = new HBox();
		// this.hBoxContact = new HBox();

		this.hboxButtons = new HBox();
		this.labelName = new Label("Name :\t");
		this.labelAddress = new Label("Address :\t");
		// this.labelContact = new Label("ContactNo :\t");

		this.textName = new TextField();
		this.textAddress = new TextField();
		// this.textContact = new TextField();

		this.buttonAdd = new Button("Add");
		this.buttonCancel = new Button("Cancel");
		this.inset1 = new Insets(40);
		this.inset2 = new Insets(8);
		// this.inset3 = new Insets(10);

		this.buttonAdd.setPadding(inset2);
		this.buttonAdd.setTranslateX(32);
		this.buttonAdd.setTranslateY(40);
		this.buttonCancel.setPadding(inset2);
		this.buttonCancel.setTranslateX(82);
		this.buttonCancel.setTranslateY(40);

		this.hBoxName.getChildren().setAll(this.labelName, this.textName);
		this.hBoxAddress.getChildren().setAll(this.labelAddress, this.textAddress);
		// this.hBoxContact.getChildren().setAll(this.labelContact,
		// this.textContact);

		this.hboxButtons.getChildren().setAll(this.buttonAdd, this.buttonCancel);
		this.vBoxContents.getChildren().setAll(this.hBoxName, this.hBoxAddress,
				/* this.hBoxContact, */ this.hboxButtons);
		super.getMainPane().setCenter(this.vBoxContents);
		super.getMainPane().setPadding(this.inset1);
	}

	// ADD Button

	public void initAddButton() {
		this.buttonAdd.setOnAction(e -> {
			super.getControl().addPatient(textName,
					textAddress/* , textContact */);
			this.textName.clear();
			this.textAddress.clear();
		});
	}

	// Cancel button
	public void initCancelButton() {
		this.buttonCancel.setOnAction(e -> {
			super.getControl().closePopUpStage();
		});
	}
}
