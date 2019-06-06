package application;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Main extends Application {

	private Controller controller = new Controller();
	private Stage window, regWindow, addPatientWindow, invoiceWindow, manageProceduresWindow, reportWindow;
	private Scene logInScene, regScene, patientMgtScene, addPatientScene, invoiceScene, manageProceduresScene,
			reportScene;
	private Dentist dentist;
	private TableView<Patient> patientsTable;
	private TableView<Procedure> procedureListTable;
	private ComboBox<Invoice> invoiceNoCmbBox;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		window.setTitle("Dental Clinic");
		window.getIcons().add(new Image("img/icon.png"));
		window.setOnCloseRequest(e -> {
			e.consume();
			controller.closeProgram(window);
		});

		// Layout
		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(25, 25, 10, 25));
		root.setVgap(10);
		root.setHgap(10);

		// Scene title
		Text sceneTitle = new Text("Dentist Login");
		sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
		root.add(sceneTitle, 0, 0, 2, 1);

		root.add(new Separator(), 0, 1, 2, 1);

		// Username label
		Label userLabel = new Label("Username:");
		root.add(userLabel, 0, 2);

		// Username input
		TextField userInput = new TextField();
		userInput.setText("mohammed"); /* Remove this later */
		userInput.setPromptText("Enter username here...");
		root.add(userInput, 1, 2);

		// Password label
		Label passLabel = new Label("Password:");
		root.add(passLabel, 0, 3);

		// Password input
		PasswordField passInput = new PasswordField();
		passInput.setText("alom"); /* Remove this later */
		passInput.setPromptText("Enter password here...");
		root.add(passInput, 1, 3);

		// Login button
		Button logInBtn = new Button("Log In");
		logInBtn.setPrefWidth(70);

		// Register hyperlink
		Hyperlink registerHyp = new Hyperlink("Register");
		registerHyp.setOnAction(e -> registerForm());

		// HBox for the buttons
		HBox hbBtn = new HBox(30);
		hbBtn.setAlignment(Pos.TOP_RIGHT);
		hbBtn.getChildren().addAll(registerHyp, logInBtn);
		root.add(hbBtn, 1, 4);

		// Handling events
		logInBtn.setOnAction(e -> {
			try {
				controller.loadFromFile();
				dentist = controller.validateLogIn(userInput, passInput);
				patientManagement(); // shows the patient management form
				controller.loadPatientsToTable(dentist, patientsTable);
			} catch (Exception ex) {
				controller.showAlert(Alert.AlertType.ERROR, "Invalid Login", "Invalid username or password.",
						"Please try entering your credentials again.");
			}
		});

		// Setting the scene
		logInScene = new Scene(root, 350, 220);
		window.setScene(logInScene);
		window.show();
	}

	private void registerForm() {
		final BooleanProperty firstTime = new SimpleBooleanProperty(true);

		regWindow = new Stage();
		regWindow.setTitle("Register");
		regWindow.getIcons().add(new Image("img/icon.png"));
		regWindow.initModality(Modality.APPLICATION_MODAL);

		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(10));
		root.setVgap(10);
		root.setHgap(10);

		// Image
		ImageView img = new ImageView();
		img.setImage(new Image("img/newUser.png"));
		HBox imgHBox = new HBox();
		imgHBox.setAlignment(Pos.CENTER);
		imgHBox.getChildren().add(img);
		root.add(imgHBox, 0, 0, 5, 1);

		// Scene title
		Text sceneTitle = new Text("New Account");
		sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
		HBox titleHBox = new HBox();
		titleHBox.setAlignment(Pos.CENTER);
		titleHBox.getChildren().add(sceneTitle);
		root.add(titleHBox, 0, 1, 5, 1);

		// Username input
		TextField usernameInput = new TextField();
		usernameInput.setPromptText("Username");
		usernameInput.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue && firstTime.get()) {
				root.requestFocus();
				firstTime.setValue(false);
			}
		});
		root.add(usernameInput, 0, 2, 5, 1);

		// Name input
		TextField nameInput = new TextField();
		nameInput.setPromptText("Full Name");
		root.add(nameInput, 0, 3, 5, 1);

		// Address input
		TextField addressInput = new TextField();
		addressInput.setPromptText("Address");
		root.add(addressInput, 0, 4, 5, 1);

		// Password input
		TextField passwordInput = new TextField();
		passwordInput.setPromptText("Password");
		root.add(passwordInput, 0, 5, 5, 1);

		// Confirm password input
		TextField confPassInput = new TextField();
		confPassInput.setPromptText("Confirm Password");
		root.add(confPassInput, 0, 6, 5, 1);

		// Register Button
		Button registerBtn = new Button("Register");
		registerBtn.setPrefSize(120, 30);
		HBox btnHBox = new HBox();
		btnHBox.setAlignment(Pos.CENTER);
		btnHBox.getChildren().add(registerBtn);
		root.add(btnHBox, 0, 7, 5, 1);

		// Handling the button event
		registerBtn.setOnAction(e -> {
			controller.loadFromFile();
			controller.validateRegistration(usernameInput, passwordInput, confPassInput, nameInput, addressInput,
					regWindow);
		});

		regScene = new Scene(root, 300, 350);
		regWindow.setScene(regScene);
		regWindow.showAndWait();
	}

	private void patientManagement() {
		window.setTitle("Patient Management");
		window.getIcons().add(new Image("img/icon.png"));
		window.setResizable(false);

		// Layout
		BorderPane root = new BorderPane();
		VBox topSection = new VBox();

		// Menu Bar + Tool Bar
		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(window.widthProperty());
		ToolBar toolBar = new ToolBar();
		toolBar.prefWidthProperty().bind(window.widthProperty());

		// Menu and Menu items
		Menu accountMenu = new Menu("Welcome, Dr. " + dentist.getName());
		MenuItem changePassMenuItem = new MenuItem("Change Password"); /* add this later */
		MenuItem logOutMenuItem = new MenuItem("Log Out");
		accountMenu.getItems().addAll(changePassMenuItem,logOutMenuItem);

		// Adding all the menus to the menu bar
		menuBar.getMenus().addAll(accountMenu);

		// Creating some buttons
		// Button loadBtn = new Button();
		Button saveBtn = new Button();
		Button addPatientBtn = new Button();
		Button remPatientBtn = new Button();

		// Setting graphics to the buttons
		// loadBtn.setGraphic(new ImageView("img/load.png"));
		saveBtn.setGraphic(new ImageView("img/save.png"));
		addPatientBtn.setGraphic(new ImageView("img/addPatient.png"));
		remPatientBtn.setGraphic(new ImageView("img/remPatient.png"));

		// Tool tips for the buttons
		// Tooltip loadBtnTT = new Tooltip("Load");
		// loadBtn.setTooltip(loadBtnTT);
		Tooltip saveBtnTT = new Tooltip("Save");
		saveBtn.setTooltip(saveBtnTT);
		Tooltip addPatientBtnTT = new Tooltip("Add Patient");
		addPatientBtn.setTooltip(addPatientBtnTT);
		Tooltip remPatientBtnTT = new Tooltip("Remove Patient");
		remPatientBtn.setTooltip(remPatientBtnTT);

		// Adding the buttons to the tool bar
		toolBar.getItems().addAll(saveBtn, addPatientBtn, remPatientBtn);
		toolBar.setPadding(new Insets(5, 10, 5, 10));

		// Title for the table
		Text tableTitle = new Text("Patients");
		tableTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

		// Adding the title to an HBox
		HBox titleHBox = new HBox();
		titleHBox.getChildren().add(tableTitle);
		titleHBox.setAlignment(Pos.BOTTOM_LEFT);

		// Controls for searching
		TextField searchTF = new TextField();
		searchTF.setPromptText("enter name here...");
		Button searchBtn = new Button("Search");
		searchBtn.setMinWidth(80);

		// Adding the search controls to an HBox
		HBox searchHBox = new HBox(10);
		searchHBox.setPrefWidth(260);
		searchHBox.getChildren().addAll(searchTF, searchBtn);

		// Combining the search and title controls to one a grid layout
		GridPane filterGrid = new GridPane();
		filterGrid.setPadding(new Insets(10, 10, 0, 10));
		filterGrid.setHgap(362);
		filterGrid.add(titleHBox, 0, 1);
		filterGrid.add(searchHBox, 1, 1);

		// Adding the menu bar, tool bar, and filter controls to the VBox for
		// top section
		topSection.getChildren().addAll(menuBar, toolBar, filterGrid);

		// Table view
		patientsTable = new TableView<>();

		// Columns for the table
		TableColumn<Patient, Integer> patientNoCol = new TableColumn<>("Patient No");
		patientNoCol.setMinWidth(100);
		patientNoCol.setCellValueFactory(new PropertyValueFactory<>("patientNo"));

		TableColumn<Patient, String> nameCol = new TableColumn<>("Name");
		nameCol.setMinWidth(128);
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Patient, String> addressCol = new TableColumn<>("Address");
		addressCol.setMinWidth(200);
		addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

		TableColumn<Patient, String> phoneNoCol = new TableColumn<>("Phone Number");
		phoneNoCol.setMinWidth(200);
		phoneNoCol.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));

		// Adding the columns to the table
		patientsTable.getColumns().addAll(patientNoCol, nameCol, addressCol, phoneNoCol);

		// Adding the table into a VBox
		VBox tableVBox = new VBox();
		tableVBox.getChildren().add(patientsTable);
		tableVBox.setAlignment(Pos.CENTER);
		tableVBox.setPadding(new Insets(10));

		// Buttons for the bottom section of the form
		Button manageProceduresBtn = new Button("Manage Procedures");
		// manageProceduresBtn.setMaxWidth(157);
		Button viewInvoicesBtn = new Button("View Invoices");
		// viewInvoicesBtn.setMinWidth(100);
		Button reportButton = new Button("Report");
		reportButton.setMinWidth(80);
		// Adding the view invoices button to an HBox
		HBox bottomSection = new HBox(200);

		bottomSection.setAlignment(Pos.BOTTOM_CENTER);
		bottomSection.getChildren().addAll(reportButton, manageProceduresBtn, viewInvoicesBtn);
		bottomSection.setPadding(new Insets(10, 10, 10, 10));

		// Setting all nodes to the layout
		root.setTop(topSection);
		root.setCenter(tableVBox);
		root.setBottom(bottomSection);

		// Handling events
		manageProceduresBtn.setOnAction(e -> {
			try {
				// controller.initialiseProcList();
				// controller.saveProceduresToFile();
				controller.loadProceduresFromFile();
			} catch (Exception ex) {
			} finally {
				manageProceduresForm();
				// controller.loadProceduresListToTable(temp);
			}
		});

		// new for report button
		reportButton.setOnAction(e -> {
			//this.controller.reportToFile();
			this.controller.generateReport(dentist.getUsername(),dentist.getPassword());

		});

		searchBtn.setOnAction(e -> controller.handleSearchFilter(searchTF, patientsTable));
		saveBtn.setOnAction(e -> controller.saveToFile());
		addPatientBtn.setOnAction(e -> addPatientForm(patientsTable));
		remPatientBtn.setOnAction(e -> controller.handleRemovePatientBtn(dentist, patientsTable));
		viewInvoicesBtn.setOnAction(e -> {
			if (patientsTable.getSelectionModel().getSelectedIndex() < 0) {
				controller.showAlert(Alert.AlertType.ERROR, "Invalid Selection", "No patient selected.",
						"Please select a patient and try again.");
			} else
				patientInvoiceForm();
		});
		logOutMenuItem.setOnAction(e -> {
			window.close();
			start(window);
		});

		// Setting the scene
		patientMgtScene = new Scene(root, 900, 600);
		window.setScene(patientMgtScene);
		window.show();
	}

	public void addPatientForm(TableView<Patient> table) {
		final BooleanProperty firstTime = new SimpleBooleanProperty(true);

		addPatientWindow = new Stage();
		addPatientWindow.setTitle("Register");
		addPatientWindow.getIcons().add(new Image("img/icon.png"));
		addPatientWindow.initModality(Modality.APPLICATION_MODAL);

		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(10));
		root.setVgap(10);
		root.setHgap(10);

		// Image
		ImageView img = new ImageView();
		img.setImage(new Image("img/newPatient.png"));
		HBox imgHBox = new HBox();
		imgHBox.setAlignment(Pos.CENTER);
		imgHBox.getChildren().add(img);
		root.add(imgHBox, 0, 0, 5, 1);

		// Scene title
		Text sceneTitle = new Text("New Patient");
		sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
		HBox titleHBox = new HBox();
		titleHBox.setAlignment(Pos.CENTER);
		titleHBox.getChildren().add(sceneTitle);
		root.add(titleHBox, 0, 1, 5, 1);

		// Name input
		TextField nameInput = new TextField();
		nameInput.setPromptText("Name");
		root.add(nameInput, 0, 2, 5, 1);

		nameInput.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue && firstTime.get()) {
				root.requestFocus();
				firstTime.setValue(false);
			}
		});

		// Address Input
		TextField addressInput = new TextField();
		addressInput.setPromptText("Address");
		root.add(addressInput, 0, 3, 5, 1);

		// Phone number input
		TextField phoneNoInput = new TextField();
		phoneNoInput.setPromptText("Phone number");
		root.add(phoneNoInput, 0, 4, 5, 1);

		// Add patient button
		Button addPatientBtn = new Button("Add Patient");
		addPatientBtn.setPrefSize(120, 30);
		addPatientBtn.setOnAction(e -> {
			controller.handleAddPatientBtn(addPatientWindow, dentist, table, nameInput, addressInput, phoneNoInput);
		});
		HBox btnHBox = new HBox();
		btnHBox.setAlignment(Pos.CENTER);
		btnHBox.getChildren().add(addPatientBtn);
		root.add(btnHBox, 0, 5, 5, 1);

		addPatientScene = new Scene(root, 300, 300);
		addPatientWindow.setScene(addPatientScene);
		addPatientWindow.showAndWait();
	}

	public void patientInvoiceForm() {
		invoiceWindow = new Stage();
		invoiceWindow.setTitle("Invoices");
		invoiceWindow.getIcons().add(new Image("img/icon.png"));
		invoiceWindow.initModality(Modality.APPLICATION_MODAL);
		invoiceWindow.setResizable(false);
		invoiceWindow.setOnCloseRequest(e -> {
			e.consume();
			controller.closeProgram(invoiceWindow);
		});

		// Layout
		BorderPane root = new BorderPane();
		VBox topSection = new VBox();
		VBox middleSection = new VBox(10);
		VBox bottomSection = new VBox();

		// Tool Bar
		ToolBar toolBar = new ToolBar();
		toolBar.prefWidthProperty().bind(window.widthProperty());

		// Creating some buttons
		Button saveBtn = new Button();
		Button addInvoiceBtn = new Button();
		Button removeInvoiceBtn = new Button();

		// Adding the buttons to the tool bar
		toolBar.getItems().addAll(saveBtn, addInvoiceBtn, removeInvoiceBtn);
		toolBar.setPadding(new Insets(5, 10, 5, 10));

		// Invoices details left section
		Label invoiceNoLbl = new Label("Invoice No:");
		invoiceNoCmbBox = new ComboBox<>();
		Label invoiceDateLbl = new Label("Invoice Date:");
		DatePicker invoiceDatePicker = new DatePicker();
		invoiceDatePicker.setEditable(false);
		Label dentistNameLbl = new Label("Dentist's Name:");
		TextField dentistNameInput = new TextField();
		dentistNameInput.setEditable(false);

		// Grid pane for the left section
		GridPane leftSection = new GridPane();
		leftSection.setVgap(10);
		leftSection.setHgap(10);
		leftSection.add(invoiceNoLbl, 0, 0);
		leftSection.add(invoiceNoCmbBox, 1, 0);
		leftSection.add(invoiceDateLbl, 0, 1);
		leftSection.add(invoiceDatePicker, 1, 1);
		leftSection.add(dentistNameLbl, 0, 2);
		leftSection.add(dentistNameInput, 1, 2);

		// Invoices details left section
		Label patientNameLbl = new Label("Patient's Name:");
		TextField patientNameInput = new TextField();
		patientNameInput.setEditable(false);
		Label addressLbl = new Label("Address:");
		TextField addressInput = new TextField();
		addressInput.setEditable(false);
		Label phoneNoLbl = new Label("Phone No:");
		TextField phoneNoInput = new TextField();
		phoneNoInput.setEditable(false);

		// Grid pane for the left section
		GridPane rightSection = new GridPane();
		rightSection.setVgap(10);
		rightSection.setHgap(10);
		rightSection.add(patientNameLbl, 0, 0);
		rightSection.add(patientNameInput, 1, 0);
		rightSection.add(addressLbl, 0, 1);
		rightSection.add(addressInput, 1, 1);
		rightSection.add(phoneNoLbl, 0, 2);
		rightSection.add(phoneNoInput, 1, 2);

		// Combining the two grids together into one HBox
		HBox invoiceHBox = new HBox(30);
		invoiceHBox.getChildren().addAll(leftSection, rightSection);

		// Title for the procedures table
		Text procTableTitle = new Text("Procedures");
		procTableTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));

		// Adding the title to an HBox
		HBox title1HBox = new HBox();
		title1HBox.getChildren().add(procTableTitle);
		title1HBox.setAlignment(Pos.BOTTOM_LEFT);

		// Adding the menu bar, tool bar, and filter controls to the VBox for
		// top section
		topSection.getChildren().add(toolBar);

		// Procedures Table view
		TableView<Procedure> proceduresTable = new TableView<>();

		// Columns for the table
		TableColumn<Procedure, Integer> procNoCol = new TableColumn<>("Procedure No");
		procNoCol.setMinWidth(100);
		procNoCol.setCellValueFactory(new PropertyValueFactory<>("procNo"));

		TableColumn<Procedure, String> procNameCol = new TableColumn<>("Name");
		procNameCol.setMinWidth(300);
		procNameCol.setCellValueFactory(new PropertyValueFactory<>("procName"));

		TableColumn<Procedure, Double> procCostCol = new TableColumn<>("Cost");
		procCostCol.setMinWidth(200);
		procCostCol.setCellValueFactory(new PropertyValueFactory<>("procCost"));

		// Adding the columns to the table
		proceduresTable.getColumns().addAll(procNoCol, procNameCol, procCostCol);

		// Controls for procedures table
		ComboBox<Procedure> proceduresCmb = new ComboBox<>();
		proceduresCmb.setMinWidth(150);
		Button addProcedureToTableBtn = new Button("Add");
		addProcedureToTableBtn.setMinWidth(40);
		Button addProcDoneBtn = new Button("Done");
		addProcDoneBtn.setMinWidth(40);
		Button addProcedureBtn = new Button();
		Button removeProcedureBtn = new Button();

		// Adding the combo box and add button to an HBox
		HBox addingProcHBox = new HBox(5);
		addingProcHBox.getChildren().addAll(proceduresCmb, addProcedureToTableBtn, addProcDoneBtn);

		// Adding the buttons for the procedures table to an HBox
		HBox procButtonsHBox = new HBox(5);
		procButtonsHBox.getChildren().addAll(addProcedureBtn, removeProcedureBtn);

		// Adding all the procedure controls to an HBox
		HBox procControlsHBox = new HBox(326);
		procControlsHBox.getChildren().addAll(procButtonsHBox);

		// Title for the table
		Text paymentTableTitle = new Text("Payments");
		paymentTableTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));

		// Adding the title to an HBox
		HBox title2HBox = new HBox();
		title2HBox.getChildren().add(paymentTableTitle);
		title2HBox.setAlignment(Pos.BOTTOM_LEFT);

		// Payments Table view
		TableView<Payment> paymentsTable = new TableView<>();

		// Columns for the table
		TableColumn<Payment, Integer> paymentNoCol = new TableColumn<>("Payment No");
		paymentNoCol.setMinWidth(100);
		paymentNoCol.setCellValueFactory(new PropertyValueFactory<>("paymentNo"));

		TableColumn<Payment, Double> paymentAmtCol = new TableColumn<>("Amount");
		paymentAmtCol.setMinWidth(300);
		paymentAmtCol.setCellValueFactory(new PropertyValueFactory<>("paymentAmt"));

		TableColumn<Payment, LocalDate> paymentDateCol = new TableColumn<>("Date of Payment");
		paymentDateCol.setMinWidth(200);
		paymentDateCol.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

		// Adding the columns to the table
		paymentsTable.getColumns().addAll(paymentNoCol, paymentAmtCol, paymentDateCol);

		// Controls for payments table
		TextField paymentAmtTF = new TextField();
		paymentAmtTF.setPromptText("Enter amount here...");
		paymentAmtTF.setMinWidth(80);
		DatePicker paymentDate = new DatePicker();
		// paymentDate.setMinWidth(60);
		Button addPaymentToTableBtn = new Button("Add");
		addPaymentToTableBtn.setMinWidth(40);
		Button addPaymentDoneBtn = new Button("Done");
		addPaymentDoneBtn.setMinWidth(40);
		Button addPaymentBtn = new Button();
		Button removePaymentBtn = new Button();

		// Adding the textfield and date picker to an HBox
		HBox addingPaymentHBox = new HBox(5);
		addingPaymentHBox.getChildren().addAll(paymentAmtTF, paymentDate, addPaymentToTableBtn, addPaymentDoneBtn);

		// Adding the buttons for the procedures table to an HBox
		HBox paymentButtonsHBox = new HBox(5);
		paymentButtonsHBox.getChildren().addAll(addPaymentBtn, removePaymentBtn);

		// Adding all the procedure controls to an HBox
		HBox paymentControlsHBox = new HBox(148);
		paymentControlsHBox.getChildren().addAll(paymentButtonsHBox);

		// Adding the tables into a VBox
		middleSection.getChildren().addAll(invoiceHBox, title1HBox, proceduresTable, procControlsHBox, title2HBox,
				paymentsTable, paymentControlsHBox);
		middleSection.setAlignment(Pos.CENTER);
		middleSection.setPadding(new Insets(10));

		// Bottom Section
		Button closeBtn = new Button("Close Window");
		closeBtn.setOnAction(e -> invoiceWindow.close());

		Label invoiceTotalLbl = new Label("Invoice Total:");
		Label invoiceTotal = new Label();
		Label totalPaymentLbl = new Label("Total Payment:");
		Label totalPayment = new Label();
		Label balanceLbl = new Label("Balance:");
		Label balance = new Label();
		invoiceTotal.setStyle("-fx-border-color: grey; -fx-padding: 2;");
		totalPayment.setStyle("-fx-border-color: grey; -fx-padding: 2;");
		balance.setStyle("-fx-border-color: grey; -fx-padding: 2;");
		invoiceTotal.setMinWidth(80);
		totalPayment.setMinWidth(80);
		balance.setMinWidth(80);

		// HBox for the button
		HBox closeBtnHBox = new HBox();
		closeBtnHBox.getChildren().add(closeBtn);
		closeBtn.setMinWidth(100);
		// closeBtnHBox.setPadding(new Insets(50, 10, 10, 10));
		closeBtnHBox.setAlignment(Pos.BOTTOM_LEFT);

		// Grid pane for the total section
		GridPane totalGrid = new GridPane();
		totalGrid.setAlignment(Pos.BOTTOM_RIGHT);
		totalGrid.setVgap(10);
		totalGrid.setHgap(10);
		totalGrid.add(invoiceTotalLbl, 0, 0);
		totalGrid.add(invoiceTotal, 1, 0);
		totalGrid.add(totalPaymentLbl, 0, 1);
		totalGrid.add(totalPayment, 1, 1);
		totalGrid.add(balanceLbl, 0, 2);
		totalGrid.add(balance, 1, 2);

		// Adding the button and total section to an HBox
		HBox bottomHBox = new HBox(370);
		bottomHBox.setPadding(new Insets(10));
		bottomHBox.getChildren().addAll(closeBtnHBox, totalGrid);

		// Adding the grid pane to the bottom section
		bottomSection.getChildren().add(bottomHBox);

		// Setting graphic to the buttons
		saveBtn.setGraphic(new ImageView("img/save.png"));
		addInvoiceBtn.setGraphic(new ImageView("img/addInvoice.png"));
		removeInvoiceBtn.setGraphic(new ImageView("img/removeInvoice.png"));
		addProcedureBtn.setGraphic(new ImageView("img/add.png"));
		removeProcedureBtn.setGraphic(new ImageView("img/remove.png"));
		addPaymentBtn.setGraphic(new ImageView("img/add.png"));
		removePaymentBtn.setGraphic(new ImageView("img/remove.png"));

		// Tool tips for the buttons
		Tooltip saveBtnTT = new Tooltip("Save");
		saveBtn.setTooltip(saveBtnTT);
		Tooltip newInvoiceTT = new Tooltip("New Invoice");
		addInvoiceBtn.setTooltip(newInvoiceTT);
		Tooltip removeInvoiceTT = new Tooltip("Remove Invoice");
		removeInvoiceBtn.setTooltip(removeInvoiceTT);
		Tooltip addProcToolTip = new Tooltip("Add Procedure");
		addProcedureBtn.setTooltip(addProcToolTip);
		Tooltip remProcToolTip = new Tooltip("Remove Procedure");
		removeProcedureBtn.setTooltip(remProcToolTip);
		Tooltip addPaymentToolTip = new Tooltip("Add Payment");
		addPaymentBtn.setTooltip(addPaymentToolTip);
		Tooltip removePaymentToolTip = new Tooltip("Remove Payment");
		removePaymentBtn.setTooltip(removePaymentToolTip);

		// Handling events
		controller.populateInvoiceComboBox(patientsTable, invoiceNoCmbBox);
		controller.loadInvoiceInformation(dentist, patientsTable, invoiceNoCmbBox, invoiceDatePicker, dentistNameInput,
				patientNameInput, addressInput, phoneNoInput, proceduresTable, paymentsTable, invoiceTotal,
				totalPayment, balance, addPaymentBtn);

		invoiceNoCmbBox.setOnAction(e -> controller.loadInvoiceInformation(dentist, patientsTable, invoiceNoCmbBox,
				invoiceDatePicker, dentistNameInput, patientNameInput, addressInput, phoneNoInput, proceduresTable,
				paymentsTable, invoiceTotal, totalPayment, balance, addPaymentBtn));

		addProcedureBtn.setOnAction(e -> {
			procControlsHBox.getChildren().add(addingProcHBox);
			controller.loadProceduresFromFile();
			controller.loadProceduresToComboBox(proceduresCmb);
			addProcedureBtn.setDisable(true);
		});
		addProcDoneBtn.setOnAction(e -> {
			procControlsHBox.getChildren().remove(addingProcHBox);
			addProcedureBtn.setDisable(false);
		});
		addPaymentBtn.setOnAction(e -> {
			paymentControlsHBox.getChildren().add(addingPaymentHBox);
			paymentDate.setValue(LocalDate.now());
			addPaymentBtn.setDisable(true);
		});
		addPaymentDoneBtn.setOnAction(e -> {
			paymentControlsHBox.getChildren().remove(addingPaymentHBox);
			addPaymentBtn.setDisable(false);
		});
		saveBtn.setOnAction(e -> controller.saveToFile());
		addInvoiceBtn.setOnAction(e -> controller.addNewInvoice(dentist, patientsTable, invoiceNoCmbBox,
				invoiceDatePicker, dentistNameInput, patientNameInput, addressInput, phoneNoInput, invoiceTotal,
				totalPayment, balance));
		removeInvoiceBtn.setOnAction(e -> controller.removeInvoice(patientsTable, invoiceNoCmbBox));
		addProcedureToTableBtn.setOnAction(e -> controller.handleAddProcedureToTableBtn(patientsTable, invoiceNoCmbBox,
				proceduresCmb, proceduresTable, invoiceTotal, totalPayment, balance));
		removeProcedureBtn.setOnAction(e -> controller.handleRemoveProcedureBtn(proceduresTable, invoiceNoCmbBox,
				invoiceTotal, totalPayment, balance));
		addPaymentToTableBtn.setOnAction(e -> controller.handleAddPaymentToTableBtn(patientsTable, paymentsTable,
				invoiceNoCmbBox, paymentAmtTF, paymentDate, totalPayment, balance, invoiceTotal));
		removePaymentBtn.setOnAction(e -> controller.handleRemovePaymentBtn(paymentsTable, invoiceNoCmbBox,
				totalPayment, balance, invoiceTotal));

		// Setting all nodes to the layout
		root.setTop(topSection);
		root.setCenter(middleSection);
		root.setBottom(bottomSection);

		// Setting the scene
		invoiceScene = new Scene(root, 800, 750);
		invoiceWindow.setScene(invoiceScene);
		invoiceWindow.showAndWait();
	}

	public void manageProceduresForm() {
		manageProceduresWindow = new Stage();
		manageProceduresWindow.setTitle("Manage Procedures");
		manageProceduresWindow.getIcons().add(new Image("img/icon.png"));
		manageProceduresWindow.initModality(Modality.APPLICATION_MODAL);
		manageProceduresWindow.setOnCloseRequest(e -> {
			e.consume();
			controller.closeProcedureWindow(manageProceduresWindow);
		});

		VBox root = new VBox(10);
		root.setPadding(new Insets(10));

		// Table title
		Text proceduresTableTitle = new Text("Procedure List");
		proceduresTableTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));

		// Title HBox
		HBox titleHBox = new HBox();
		titleHBox.getChildren().add(proceduresTableTitle);
		titleHBox.setAlignment(Pos.BOTTOM_LEFT);

		// Procedures table
		procedureListTable = new TableView<>();

		// Columns for the table
		TableColumn<Procedure, Integer> procNoCol = new TableColumn<>("Procedure No");
		procNoCol.setMinWidth(100);
		procNoCol.setCellValueFactory(new PropertyValueFactory<>("procNo"));

		TableColumn<Procedure, String> procNameCol = new TableColumn<>("Name");
		procNameCol.setMinWidth(203);
		procNameCol.setCellValueFactory(new PropertyValueFactory<>("procName"));

		TableColumn<Procedure, Double> procCostCol = new TableColumn<>("Cost");
		procCostCol.setMinWidth(145);
		procCostCol.setCellValueFactory(new PropertyValueFactory<>("procCost"));

		// Adding the columns to the table
		procedureListTable.getColumns().addAll(procNoCol, procNameCol, procCostCol);

		// Controls for adding and removing procedures
		HBox controlsHBox = new HBox(10);
		TextField procNameInput = new TextField();
		procNameInput.setPromptText("Procedure Name");
		procNameInput.setPrefWidth(167);
		TextField procCostInput = new TextField();
		procCostInput.setPromptText("Cost");
		procCostInput.setPrefWidth(60);
		Button addProcedureBtn = new Button("Add");
		// addProcedureBtn.setMinWidth(40);
		Button removeProcedureBtn = new Button("Remove");
		// removeProcedureBtn.setMinWidth(40);
		Button saveProceduresBtn = new Button("Save");
		// saveProceduresBtn.setMinWidth(40);
		Button exitBtn = new Button("Exit");
		exitBtn.setOnAction(e -> controller.closeProgram(manageProceduresWindow));
		// exitBtn.setMinWidth(40);
		controlsHBox.getChildren().addAll(procNameInput, procCostInput, addProcedureBtn, removeProcedureBtn,
				saveProceduresBtn, exitBtn);
		// controlsHBox.setPadding(new Insets(10));
		controlsHBox.setAlignment(Pos.CENTER);

		// Handling events
		controller.loadProceduresListToTable(procedureListTable);
		controller.setProceduresSaved(true);
		addProcedureBtn
				.setOnAction(e -> controller.addProcedureToTheList(procedureListTable, procNameInput, procCostInput));
		removeProcedureBtn.setOnAction(e -> controller.removeProcedureFromTheList(procedureListTable));
		saveProceduresBtn.setOnAction(e -> controller.saveProceduresToFile());

		root.getChildren().addAll(titleHBox, procedureListTable, controlsHBox);
		manageProceduresScene = new Scene(root, 550, 300);
		manageProceduresWindow.setScene(manageProceduresScene);
		manageProceduresWindow.showAndWait();
	}

}