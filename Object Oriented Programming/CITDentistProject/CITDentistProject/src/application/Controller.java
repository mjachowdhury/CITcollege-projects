package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

public final class Controller {

	String loggedDentist;
	String loggedPassword;

	private ArrayList<Dentist> dentistList = new ArrayList<>();
	private ArrayList<Procedure> proceduresList = new ArrayList<>();
	private ArrayList<Patient> patientList;

	private ObservableList<Patient> patientObservableListList;
	private ObservableList<Procedure> proceduresObservableList;
	private ObservableList<Invoice> invoicesObservableList;
	private ObservableList<Procedure> patientProcsObservableList;
	private ObservableList<Payment> patientPaymentObservableList;

	private double totalPaymentAmt, balanceAmt;
	private Boolean saved = true;
	private Boolean proceduresSaved = true;

	public void loadFromFile() {
		try {
			ObjectInputStream ois_Dentist = new ObjectInputStream(new FileInputStream("database.ser"));
			dentistList = (ArrayList<Dentist>) ois_Dentist.readObject();
			Patient.COUNTER = ois_Dentist.readInt();
			Invoice.COUNTER = ois_Dentist.readInt();
			Procedure.COUNTER = ois_Dentist.readInt();
			Payment.COUNTER = ois_Dentist.readInt();
			ois_Dentist.close();
		} catch (Exception ex) {
		}
	}

	public void saveToFile() {
		try {
			ObjectOutputStream oos_Dentist = new ObjectOutputStream(new FileOutputStream("database.ser"));
			oos_Dentist.writeObject(dentistList);
			oos_Dentist.writeInt(Patient.COUNTER);
			oos_Dentist.writeInt(Invoice.COUNTER);
			oos_Dentist.writeInt(Procedure.COUNTER);
			oos_Dentist.writeInt(Payment.COUNTER);
			oos_Dentist.close();
			saved = true;
		} catch (Exception ex) {
		}
	}

	public Dentist validateLogIn(TextField usernameInput, TextField passwordInput) {
		Dentist currentDentist = null;
		Boolean found = false;

		for (Dentist dentist : dentistList) {
			if (usernameInput.getText().equals(dentist.getUsername())) {
				if (passwordInput.getText().equals(dentist.getPassword())) {
					currentDentist = dentist;
					found = true;
				}
			}
		}

		if (found)
			return currentDentist;
		else {
			usernameInput.clear();
			passwordInput.clear();
			return null;
		}
	}

	public void validateRegistration(TextField usernameInput, TextField passwordInput, TextField confirmPasswordInput,
			TextField nameInput, TextField addressInput, Stage window) {
		Dentist newDentist;
		Boolean okay = true;

		if (usernameInput.getText().equals("") || nameInput.getText().equals("") || addressInput.getText().equals("")
				|| passwordInput.getText().equals("") || confirmPasswordInput.getText().equals("")) {
			okay = false;
			showAlert(Alert.AlertType.ERROR, "Empty Fields", "One or more text fields are empty",
					"Please fill up all the information needed and try again.");
			usernameInput.clear();
		} else if (!(passwordInput.getText().equals(confirmPasswordInput.getText()))) {
			okay = false;
			showAlert(Alert.AlertType.ERROR, "Password Mismatch", "Passwords doesn't match",
					"Please input the passwords again.");
			passwordInput.clear();
			confirmPasswordInput.clear();
		} else {
			for (Dentist dentist : dentistList) {
				if (usernameInput.getText().equals(dentist.getUsername())) {
					okay = false;
					showAlert(Alert.AlertType.ERROR, "Username Not Available", "Username already exists",
							"Please pick another username and try again.");
					usernameInput.clear();
				}
			}
		}
		if (okay) {
			try {
				newDentist = new Dentist(usernameInput.getText(), nameInput.getText(), addressInput.getText(),
						passwordInput.getText());
				dentistList.add(newDentist);
				saveToFile();
				showAlert(Alert.AlertType.INFORMATION, "Successful!", "User account has been created.",
						"You can now use the account to login to the application.");
				usernameInput.clear();
				nameInput.clear();
				addressInput.clear();
				passwordInput.clear();
				confirmPasswordInput.clear();
				window.close();
			} catch (Exception ex) {
			}

		}
	}

	public void loadPatientsToTable(Dentist dentist, TableView<Patient> table) {
		patientList = dentist.getPatients();
		patientObservableListList = FXCollections.observableArrayList(patientList);
		table.setItems(patientObservableListList);
	}

	public void handleAddPatientBtn(Stage window, Dentist dentist, TableView<Patient> table, TextField nameInput,
			TextField addressInput, TextField phoneNoInput) {
		if (nameInput.getText().equals("") || addressInput.getText().equals("") || phoneNoInput.getText().equals("")) {
			showAlert(Alert.AlertType.ERROR, "Empty Fields", "There are fields not filled out.",
					"Please fill out all the fields and try again.");
		} else {
			// Instantiating a new patient that will be added to the list
			Patient patient = new Patient();

			// Setting up the values entered by the user to the patient object
			patient.setName(nameInput.getText());
			patient.setAddress(addressInput.getText());
			patient.setPhoneNo(phoneNoInput.getText());

			// Adding the patient to the table and patient list of the dentist
			table.getItems().add(patient);
			dentist.addPatient(patient);

			// Clearing the controls
			nameInput.clear();
			addressInput.clear();
			phoneNoInput.clear();
			window.close();
			saved = false;
		}
	}

	public void handleRemovePatientBtn(Dentist dentist, TableView<Patient> table) {
		int selectedIndex = table.getSelectionModel().getSelectedIndex();
		try {
			table.getItems().remove(selectedIndex);
			dentist.removePatient(selectedIndex);
			saved = false;
		} catch (Exception e) {
			showAlert(Alert.AlertType.ERROR, "Invalid Selection", "No patient selected.",
					"Please select a patient to remove and try again.");
		}
	}

	public void handleSearchFilter(TextField searchField, TableView<Patient> table) {
		ArrayList<Patient> filteredPatient;

		try {
			if (searchField.getText().equals(""))
				table.setItems(patientObservableListList);
			else {
				try {
					filteredPatient = new ArrayList<>();
					for (Patient p : patientList) {
						if (searchField.getText().toLowerCase().equals(p.getName().toLowerCase())) {
							filteredPatient.add(p);
						}
					}
					ObservableList<Patient> filteredPatientList = FXCollections.observableArrayList(filteredPatient);
					table.setItems(filteredPatientList);
				} catch (Exception ex) {
				}
			}
		} catch (Exception ex) {
			showAlert(Alert.AlertType.ERROR, "Patient Not Found", "Patient cannot be found from the list.",
					"Please enter the full name of the patient and try again.");
		}
	}

	public void initialiseProcList() {
		Procedure p1 = new Procedure("Extractions", 80.00);
		Procedure p2 = new Procedure("Root Canal", 600.00);
		Procedure p3 = new Procedure("Fillings", 80.00);
		Procedure p4 = new Procedure("Crowns", 800.00);
		Procedure p5 = new Procedure("Dentures", 450.00);
		Procedure p6 = new Procedure("Teeth Whitening", 250.00);

		proceduresList.add(p1);
		proceduresList.add(p2);
		proceduresList.add(p3);
		proceduresList.add(p4);
		proceduresList.add(p5);
		proceduresList.add(p6);
	}

	public void saveProceduresToFile() {
		try {
			ObjectOutputStream oos_Procedure = new ObjectOutputStream(new FileOutputStream("proceduresList.ser"));
			oos_Procedure.writeObject(proceduresList);
			oos_Procedure.writeInt(Procedure.COUNTER);
			oos_Procedure.close();
			saved = true;
		} catch (Exception ex) {
		}
	}

	public void loadProceduresFromFile() {
		try {
			ObjectInputStream ois_Procedure = new ObjectInputStream(new FileInputStream("proceduresList.ser"));
			proceduresList = (ArrayList<Procedure>) ois_Procedure.readObject();
			Procedure.COUNTER = ois_Procedure.readInt();
			ois_Procedure.close();
		} catch (Exception ex) {
		}
	}

	public void loadProceduresToComboBox(ComboBox<Procedure> comboBox) {
		proceduresObservableList = FXCollections.observableArrayList(proceduresList);
		comboBox.setItems(proceduresObservableList);
		comboBox.setValue(proceduresObservableList.get(0));
	}

	public void populateInvoiceComboBox(TableView<Patient> patientsTable, ComboBox<Invoice> invoiceCmb) {
		Patient patient = patientsTable.getSelectionModel().getSelectedItem();

		if (patient.getP_invoiceList().size() <= 0) {
			showAlert(Alert.AlertType.INFORMATION, "Empty Invoice", "No Invoices found.",
					"Click the add button to add a new invoice.");
		} else {
			try {
				invoicesObservableList = FXCollections.observableArrayList(patient.getP_invoiceList());
				invoiceCmb.setItems(invoicesObservableList);
				invoiceCmb.getSelectionModel().selectFirst();
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
	}

	public void loadPatientProceduresToTable(Invoice invoice, TableView<Procedure> proceduresTable) {
		patientProcsObservableList = FXCollections.observableArrayList(invoice.getIn_procList());
		proceduresTable.setItems(patientProcsObservableList);
	}

	public void loadPatientPaymentsToTable(Invoice invoice, TableView<Payment> paymentsTable) {
		patientPaymentObservableList = FXCollections.observableArrayList(invoice.getIn_paymentList());
		paymentsTable.setItems(patientPaymentObservableList);
	}

	public void calculateTotalPayment(Invoice invoice) {
		for (int i = 0; i < invoice.getIn_paymentList().size(); i++) {
			totalPaymentAmt += invoice.getIn_paymentList().get(i).getPaymentAmt();
		}
	}

	public void setMoneyValues(Invoice invoice, Label invoiceTotal, Label totalPayment, Label balance) {
		invoiceTotal.setText("\u20ac" + Double.toString(invoice.getInvoiceAmt()));
		totalPayment.setText("\u20ac" + Double.toString(totalPaymentAmt));
		String[] splitInvoiceTotal = invoiceTotal.getText().split(" ");
		balanceAmt = Double.parseDouble(splitInvoiceTotal[1]) - totalPaymentAmt;
		balance.setText("\u20ac" + Double.toString(balanceAmt));
	}

	public void loadInvoiceInformation(Dentist dentist, TableView<Patient> patientsTable, ComboBox<Invoice> invoiceCmb,
			DatePicker datePicker, TextField dentistName, TextField patientName, TextField address, TextField phoneNo,
			TableView<Procedure> proceduresTable, TableView<Payment> paymentsTable, Label invoiceTotal,
			Label totalPayment, Label balance, Button addPayment) {
		totalPaymentAmt = 0;
		Patient patient = patientsTable.getSelectionModel().getSelectedItem();
		Invoice invoice = invoiceCmb.getValue();

		try {
			datePicker.setValue(invoice.getDate());
			dentistName.setText(dentist.getName());
			patientName.setText(patient.getName());
			address.setText(patient.getAddress());
			phoneNo.setText(patient.getPhoneNo());
			loadPatientProceduresToTable(invoice, proceduresTable);
			loadPatientPaymentsToTable(invoice, paymentsTable);
			calculateTotalPayment(invoice);
			setMoneyValues(invoice, invoiceTotal, totalPayment, balance);
		} catch (Exception ex) {
		} finally {
			if (patient.getP_invoiceList().size() < 0) {
				showAlert(Alert.AlertType.ERROR, "Invoice Not Available", "No invoices available for the patient.",
						"Click the add invoice button to add a new one.");
			}
		}
	}

	public void addNewInvoice(Dentist dentist, TableView<Patient> patientsTable, ComboBox<Invoice> invoiceCmb,
			DatePicker datePicker, TextField dentistName, TextField patientName, TextField address, TextField phoneNo,
			Label invoiceTotal, Label totalPayment, Label balance) {
		Patient patient = patientsTable.getSelectionModel().getSelectedItem();

		try {
			// Creating a new invoice object
			Invoice invoice = new Invoice();

			// Setting up the default values for the controls
			dentistName.setText(dentist.getName());
			datePicker.setValue(LocalDate.now());
			patientName.setText(patient.getName());
			address.setText(patient.getAddress());
			phoneNo.setText(patient.getPhoneNo());

			// Setting up the values for the invoice object
			LocalDate date = datePicker.getValue();
			invoice.setDate(date);
			patient.addInvoice(invoice);

			// Populating the invoice combo box
			populateInvoiceComboBox(patientsTable, invoiceCmb);
			invoiceCmb.getSelectionModel().selectLast();

			// Setting up money values
			setMoneyValues(invoice, invoiceTotal, totalPayment, balance);
			saved = false;
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public void removeInvoice(TableView<Patient> patientsTable, ComboBox<Invoice> invoiceComboBox) {
		Patient patient = patientsTable.getSelectionModel().getSelectedItem();
		try {
			patient.removeInvoice(invoiceComboBox.getSelectionModel().getSelectedIndex());
			populateInvoiceComboBox(patientsTable, invoiceComboBox);
			invoiceComboBox.getSelectionModel().selectLast();
			saved = false;
		} catch (Exception ex) {
		}
	}

	public void handleAddProcedureToTableBtn(TableView<Patient> patientsTable, ComboBox<Invoice> invoiceCmb,
			ComboBox<Procedure> procedureCmb, TableView<Procedure> proceduresTable, Label invoiceTotal,
			Label totalPayment, Label balance) {
		Patient patient = patientsTable.getSelectionModel().getSelectedItem();
		Invoice invoice = invoiceCmb.getValue();
		Procedure procedure = procedureCmb.getValue();

		try {
			for (int i = 0; i < patient.getP_invoiceList().size(); i++) {
				if (invoice.getInvoiceNo() == patient.getP_invoiceList().get(i).getInvoiceNo()) {
					patient.getP_invoiceList().get(i).addProcedure(procedure);
				}
			}
			invoice.setInvoiceAmt(0.0);
			loadPatientProceduresToTable(invoice, proceduresTable);
			setMoneyValues(invoice, invoiceTotal, totalPayment, balance);
			saved = false;
		} catch (Exception ex) {
		}

	}

	public void handleRemoveProcedureBtn(TableView<Procedure> proceduresTable, ComboBox<Invoice> invoiceCmb,
			Label invoiceTotal, Label totalPayment, Label balance) {
		int selectedIndex = proceduresTable.getSelectionModel().getSelectedIndex();
		Invoice invoice = invoiceCmb.getValue();
		try {
			proceduresTable.getItems().remove(selectedIndex);
			invoice.removeProcedure(selectedIndex);
			invoice.setInvoiceAmt(0.00);
			setMoneyValues(invoice, invoiceTotal, totalPayment, balance);
			saved = false;
		} catch (Exception e) {
			showAlert(Alert.AlertType.ERROR, "Invalid Selection", "No procedure selected.",
					"Please select a procedure to remove and try again.");
		}
	}

	public void handleAddPaymentToTableBtn(TableView<Patient> patientsTable, TableView<Payment> paymentsTable,
			ComboBox<Invoice> invoiceCmb, TextField paymentAmt, DatePicker paymentDate, Label totalPayment,
			Label balance, Label invoiceTotal) {
		Patient patient = patientsTable.getSelectionModel().getSelectedItem();
		Invoice invoice = invoiceCmb.getValue();
		paymentDate.setValue(LocalDate.now());
		LocalDate date = paymentDate.getValue();

		try {
			Payment payment = new Payment();
			payment.setPaymentAmt(Double.parseDouble(paymentAmt.getText()));
			payment.setPaymentDate(date);
			for (int i = 0; i < patient.getP_invoiceList().size(); i++) {
				if (invoice.getInvoiceNo() == patient.getP_invoiceList().get(i).getInvoiceNo()) {
					patient.getP_invoiceList().get(i).addPayment(payment);
				}
			}
			loadPatientPaymentsToTable(invoice, paymentsTable);
			for (int i = 0; i < invoice.getIn_paymentList().size(); i++) {
				totalPaymentAmt += invoice.getIn_paymentList().get(i).getPaymentAmt();
			}
			setMoneyValues(invoice, invoiceTotal, totalPayment, balance);
			saved = false;
		} catch (Exception ex) {
			showAlert(Alert.AlertType.ERROR, "Invalid Selection", "No payment selected.",
					"Please select a payment to remove and try again.");
		}
	}

	public void handleRemovePaymentBtn(TableView<Payment> paymentsTable, ComboBox<Invoice> invoiceCmb,
			Label totalPayment, Label balance, Label invoiceTotal) {
		int selectedIndex = paymentsTable.getSelectionModel().getSelectedIndex();
		Invoice invoice = invoiceCmb.getValue();
		totalPaymentAmt = 0;
		try {
			paymentsTable.getItems().remove(selectedIndex);
			invoice.removePayment(selectedIndex);
			loadPatientPaymentsToTable(invoice, paymentsTable);
			for (int i = 0; i < invoice.getIn_paymentList().size(); i++) {
				totalPaymentAmt += invoice.getIn_paymentList().get(i).getPaymentAmt();
			}
			setMoneyValues(invoice, invoiceTotal, totalPayment, balance);
			saved = false;
		} catch (Exception e) {
			showAlert(Alert.AlertType.ERROR, "Invalid Selection", "No payment selected.",
					"Please select a payment to remove and try again.");
		}
	}

	public void loadProceduresListToTable(TableView<Procedure> tableView) {
		proceduresObservableList = FXCollections.observableList(proceduresList);
		tableView.setItems(proceduresObservableList);
	}

	public void addProcedureToTheList(TableView<Procedure> tableView, TextField procedureName, TextField cost) {
		Double costInput = 0.00;
		Boolean okay = true;

		if (procedureName.getText().equals("") || cost.getText().equals("")) {
			okay = false;
			showAlert(Alert.AlertType.ERROR, "Empty Fields", "Missing fields found.",
					"Please enter all the information needed and try again.");
		} else {
			try {
				costInput = Double.parseDouble(cost.getText());
			} catch (Exception ex) {
				okay = false;
				showAlert(Alert.AlertType.ERROR, "Invalid Cost", "The cost you entered is invalid.",
						"Please enter a valid cost..");
				cost.clear();
			}
		}
		if (okay) {
			proceduresSaved = false;
			Procedure procedure = new Procedure();
			procedure.setProcName(procedureName.getText());
			procedure.setProcCost(costInput);
			proceduresList.add(procedure);
			procedureName.clear();
			cost.clear();
			loadProceduresListToTable(tableView);
		}
	}

	public void removeProcedureFromTheList(TableView<Procedure> tableView) {
		// loadProceduresListToTable(tableView);
		int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
		try {
			// tableView.getItems().remove(selectedIndex);
			// proceduresObservableList.remove(selectedIndex);
			proceduresList.remove(selectedIndex);
			loadProceduresListToTable(tableView);
			proceduresSaved = false;
		} catch (Exception e) {
			showAlert(Alert.AlertType.ERROR, "Invalid Selection", "No procedure selected.",
					"Please select a procedure to remove and try again.");
		}
	}

	public void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}

	public void closeProgram(Stage window) {
		if (!saved) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Exit");
			alert.setHeaderText("Changes not saved.");
			alert.setContentText("Do you really want to exit?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				window.close();
			}
		} else
			window.close();
	}

	public void closeProcedureWindow(Stage proceduresWindow) {
		if (!proceduresSaved) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Exit");
			alert.setHeaderText("Changes not saved.");
			alert.setContentText("Do you really want to exit?");

			Optional<ButtonType> answer = alert.showAndWait();
			if (answer.get() == ButtonType.OK) {
				proceduresWindow.close();
			}
		} else
			proceduresWindow.close();
	}

	public void setProceduresSaved(Boolean proceduresSaved) {
		this.proceduresSaved = proceduresSaved;
	}
	
	public String generateReport(String loggedInDentist, String loggedPassword) {
		String report = "REPORT\n\n";

		Dentist currentDentist = null;
		Boolean found = false;

		for (Dentist dentist : dentistList) {
			if (loggedInDentist.equals(dentist.getUsername())) {
				if (loggedPassword.equals(dentist.getPassword())) {
					dentist = currentDentist;
					found = true;
				}
			}
		}

		if (found)

			try {

				PrintWriter writer = new PrintWriter("report.txt");
				writer.println("Patients Report for Dentist : " + currentDentist);
				writer.println("-----------------------------------------------");
				//for (Patient p : currentDentist.getdName().patientList) {
				for (Patient p : patientList) {	
					writer.println(p.toString1());
					for (Invoice h : p.getP_invoiceList()) {
						writer.println(h.toString1());
					}

				}

				writer.close();
			} catch (IOException n) {
				n.printStackTrace();
			}

		return report;
	}
	
	
	
		/*for (Dentist dentist : dentistList) {
			if (dentist.dName.equals(currentDentist.getUsername())) {
			//if (currentDentist.getUsername().equals(dentist.getUsername()))	{
				if (dentist.getPassword().equals(currentDentist.getPassword())) {
				//if(currentDentist.getPassword().equals(dentist.getPassword())){
					currentDentist = dentist;
					found = true;

					try {

						PrintWriter writer = new PrintWriter("report.txt");
						writer.println("Patients Report for Dentist : " + loggedDentist);
						writer.println("-----------------------------------------------");
						for (Patient p : dentist.patientList) {
							writer.println(p.toString1());
							for (Invoice h : p.getP_invoiceList()) {
								writer.println(h.toString());
							}

						}
						writer.close();
					} catch (IOException n) {
						n.printStackTrace();
					}
				}

			}

		}
		return report;
	}*/
	

	/*public String generateReport() {
		String report = "REPORT\n\n";

		Dentist currentDentist = null;
		Boolean found = false;

		for (Dentist dentist : dentistList) {
			if (dentist.getName().equals(dentist.getUsername())) {
				if (dentist.getPassword().equals(dentist.getPassword())) {
					currentDentist = dentist;
					found = true;

					try {

						PrintWriter writer = new PrintWriter("report.txt");
						writer.println("Patients Report for Dentist : " + loggedDentist);
						writer.println("-----------------------------------------------");
						for (Patient p : dentist.patientList) {
							writer.println(p.toString1());
							for (Invoice h : p.getP_invoiceList()) {
								writer.println(h.toString());
							}

						}
						writer.close();
					} catch (IOException n) {
						n.printStackTrace();
					}
				}

			}

		}
		return report;
	}
	
*/	
	/*public void reportToFile() {
		FileWriter output;
		String fileName = "report.txt", report = "";
		try {
			output = new FileWriter(fileName);
			report += this.generateReport();
			output.write(report);
			output.close();
		} catch (IOException k) {
			System.out.println(k);
		}
	}*/

}
	// *****Misc methods*****

	/*
	  @SuppressWarnings("unchecked") public String generateReport() { String
	  report = "REPORT\n\n"; ArrayList<Dentist> dentistList = new
	  ArrayList<>();
	  
	 
	  
	  // @SuppressWarnings("unchecked") Iterator<Dentist> tempList =
	  this.dentistList.iterator(); if(tempList.next() != null){
	  //dentistList.add(new Dentist(tempList.get(i), this.dentistList));
	  dentistList.add(new Dentist(tempList.next(),this.dentistList)); }
	  
	  ArrayList<String> tempList = (ArrayList<String>)
	  this.dentistList.listIterator(); for(int i = 0; i < tempList.size();
	  i++){ dentistList.add(new Dentist(tempList.get(i), this.dentistList)); }
	  
	  for(int i = 0; i < dentistList.size(); i++){ report +=
	  dentistList.get(i).toString(); //dentists
	  
	  for(int j = 0; j < dentistList.get(i).getPatientList().size(); j++){
	  report += dentistList.get(i).getPatient(j).toString(); //patients
	  
	  for(int k = 0; k <
	  dentistList.get(i).getPatient(j).getPatientInvoiceList().size(); k++){
	 
	  report += dentistList.get(i).getInvoice(j,k).toString(); //invoice
	  
	  for(int l = 0; l < dentistList.get(i).getProcedureList(j, k).size();
	  l++){ report += dentistList.get(i).getProcedure(j, k, l).toString(); }
	  for(int h = 0; h < dentistList.get(i).getPaymentList(j, k).size(); h++){
	  report += dentistList.get(i).getPayment(j, k, h).toString(); } } } }
	  
	  return report; }
	 */

	/*public void loginDentist(String name, String password) {
		for (Dentist logView : dentistList) {
			if (logView.getName().equals(name) && logView.getPassword().equals(password)) {

				this.loggedDentist = name;
				this.loggedPassword = password;
			}
		}
	}*/

	
		//}
		
		
	 

	