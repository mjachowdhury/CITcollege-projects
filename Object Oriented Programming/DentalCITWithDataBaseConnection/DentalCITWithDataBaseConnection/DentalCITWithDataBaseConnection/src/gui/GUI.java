/**
 * Student Name - Mohammed Alom
 * Student No - R 00144214
 * Course - Software Development (Object Oriented Programming Java - CIT Second Year Project Second Part Dental Management with Database Connection)
 */
package gui;

import java.util.ArrayList;

import controller.Controller;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ProcedureType;
import scene.AddPatientScene;
import scene.AddPaymentScene;
import scene.AddProcedureScene;
import scene.AddProcedureTypeScene;
import scene.InvoiceScene;
import scene.LogInScene;
import scene.PatientScene;
import scene.ProcPayScene;

/**
 * This class will create all the GUI for the Dental Management system.
 * 
 * @author Mohammed
 *
 */
public class GUI {
	private LogInScene sceneLogin;
	private PatientScene scenePatient;
	private AddPatientScene popUpAddPatient;
	private InvoiceScene sceneInvoice;
	private ProcPayScene sceneProcPay;
	private AddProcedureScene popUpAddProcedure;
	private AddPaymentScene popUpAddPayment;
	private AddProcedureTypeScene popUpNewProc;
	private Stage primaryStage;
	private Stage popUpStage;
    
    public GUI(Controller control, Stage newPrimaryStage){
        this.sceneLogin = new LogInScene(control, 306, 200);//Login Screen
        this.popUpAddPatient = new AddPatientScene(control, 306, 200); //Add patient screen size
        this.popUpAddProcedure = new AddProcedureScene(control, 450, 200);//Add procedure screen size
        this.popUpAddPayment = new AddPaymentScene(control, 306, 200);//Add payment screen size
        this.popUpNewProc = new AddProcedureTypeScene(control, 306, 200);
        this.scenePatient = new PatientScene(control, 700, 600);//Main screen after login
        this.sceneInvoice = new InvoiceScene(control, 700, 600);//Invoice Scene
        this.sceneProcPay = new ProcPayScene(control, 700, 600);
        this.popUpStage = new Stage();
        this.popUpStage.initModality(Modality.APPLICATION_MODAL);
        this.primaryStage = newPrimaryStage;
    }
    
	public void initGUI() {
		this.primaryStage.setScene(sceneLogin.getScene());
		this.primaryStage.setTitle("Dentist Management");
		this.primaryStage.show();
	}

	public void initSceneLogin() {
		this.sceneLogin.populateComboBox();
		this.sceneLogin.comboBoxSelect();
	}

	public void initSceneLoginButtons() {
		this.sceneLogin.initLoginButton(this.primaryStage, this.scenePatient.getScene());
		this.sceneLogin.initExitButton();
	}

	public void initScenePatient() {
		this.scenePatient.populateDetails();
	}

	public void initScenePatientButtons() {
		this.scenePatient.logoutButton(this.primaryStage, this.sceneLogin.getScene());
		this.scenePatient.reportButton();
		this.scenePatient.sortButton();
		this.scenePatient.sortButtonNum();
		this.scenePatient.addPatientButton();
		this.scenePatient.removePatientButton();
		this.scenePatient.invoiceSceneButton(this.primaryStage, this.sceneInvoice.getScene());
	}

	public void initSceneInvoice() {
		this.sceneInvoice.populateDetails();
	}

	public void initSceneInvoiceButtons() {
		this.sceneInvoice.logoutButton(this.primaryStage, this.sceneLogin.getScene());
		this.sceneInvoice.reportButton();
		this.sceneInvoice.backButton(this.primaryStage, this.scenePatient.getScene());
		this.sceneInvoice.addInvoiceButton();
		this.sceneInvoice.removeInvoiceButton();
		this.sceneInvoice.procPaySceneButton(this.primaryStage, this.sceneProcPay.getScene());
	}

	public void initSceneProcPay() {
		this.sceneProcPay.populateDetails();
	}

	public void initSceneProcPayButtons() {
		this.sceneProcPay.logoutButton(this.primaryStage, this.sceneLogin.getScene());
		this.sceneProcPay.reportButton();
		this.sceneProcPay.backButton(this.primaryStage, this.sceneInvoice.getScene());
		this.sceneProcPay.addProcedureButton();
		this.sceneProcPay.addPaymentButton();
		this.sceneProcPay.removeProcedureButton();
		this.sceneProcPay.removePaymentButton();
		this.sceneProcPay.initNewButton();
	}

	public void resetScenes() {
		this.sceneLogin.reset();
		this.scenePatient.clearDetails();
	}

	public void initAddPatient() {
		this.popUpStage.setScene(this.popUpAddPatient.getScene());
		this.popUpStage.setTitle("Add Patient");
		this.popUpStage.show();
		this.popUpAddPatient.initAddButton();
		this.popUpAddPatient.initCancelButton();
	}

	public void initAddProcedure(ArrayList<ProcedureType> list) {
		this.popUpStage.setScene(this.popUpAddProcedure.getScene());
		this.popUpStage.setTitle("Add Procedure");
		this.popUpStage.show();
		this.popUpAddProcedure.initAddButton();
		this.popUpAddProcedure.initCancelButton();
		this.popUpAddProcedure.populateComboBox(list);
		this.popUpAddProcedure.comboBoxSelect();
	}

	public void initAddPayment() {
		this.popUpStage.setScene(this.popUpAddPayment.getScene());
		this.popUpStage.setTitle("Add Payment");
		this.popUpStage.show();
		this.popUpAddPayment.initAddButton();
		this.popUpAddPayment.initCancelButton();
	}

	public void initAddNewProcedure() {
		this.popUpStage.setScene(this.popUpNewProc.getScene());
		this.popUpStage.setTitle("New Procedure");
		this.popUpStage.show();
		this.popUpNewProc.initAddButton();
		this.popUpNewProc.initCancelButton();
	}

	public void closePopUpStage() {
		this.popUpStage.close();
	}

}
