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

/**
 * This class about Add payment scene for the project
 * 
 * @author Mohammed
 *
 */
public class AddPaymentScene extends BaseScenePopUp {
	// buttons
	private Button buttonAdd;
	private Button buttonCancel;
	// panes
	private HBox hBoxMain;
	private HBox hBoxButtons;
	// lists misc
	private TextField fieldAmount;
	private Label labelAmount;
	// misc
	private Insets insets;

	// Add payment scene method

	public AddPaymentScene(Controller newControl, int xSize, int ySize) {
		super(newControl, xSize, ySize);
		// buttons
		this.insets = new Insets(15);
		this.buttonAdd = new Button("Add");
		this.buttonCancel = new Button("Cancel");
		// panes
		this.hBoxMain = new HBox();
		this.hBoxButtons = new HBox();
		// lists misc
		this.labelAmount = new Label("Amount : ");
		this.fieldAmount = new TextField();
		// populate panes
		this.hBoxMain.getChildren().addAll(this.labelAmount, this.fieldAmount);
		this.hBoxButtons.getChildren().addAll(this.buttonAdd, this.buttonCancel);
		super.getMainPane().setCenter(this.hBoxMain);
		super.getMainPane().setBottom(this.hBoxButtons);
		// setting parameters
		this.hBoxMain.setTranslateY(40);
		this.hBoxMain.setTranslateX(45);
		this.hBoxButtons.setTranslateY(-40);
		this.hBoxButtons.setTranslateX(75);
		this.buttonCancel.setTranslateX(50);
		this.buttonAdd.setPadding(insets);
		this.buttonCancel.setPadding(insets);
	}

	// Add button'
	public void initAddButton() {
		this.buttonAdd.setOnAction(e -> {
			super.getControl().addPayment(fieldAmount);
		});
	}

	// Cancel button
	public void initCancelButton() {
		this.buttonCancel.setOnAction(e -> {
			super.getControl().closePopUpStage();
		});
	}

}
