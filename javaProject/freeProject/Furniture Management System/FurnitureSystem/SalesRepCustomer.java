import javax.swing.JSeparator;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class SalesRepCustomer extends DEForm implements ActionListener, ItemListener, KeyListener {
	private UI uiDesigned = new UI();
	private DETableUI TableUI = new DETableUI();
	private UI.SalesRep SalesRep 		= uiDesigned.new SalesRep();
	private UI.Customer Customer 		= uiDesigned.new Customer();
	private UI.SalesTeam SalesTeam	= uiDesigned.new SalesTeam();
	private DETableUI.ResultsModel	ResultsModel = TableUI.new ResultsModel();

	private UI.DETextField 	EmployeeName 	= SalesRep.EmployeeName,
										ContactNO			= SalesRep.ContactNO,
										SalesTeamInfo 	= SalesTeam.SalesTeamInfo;

	private UI.DEPasswordField 	OldPassword 	= SalesRep.OldPassword,
											NewPassword 	= SalesRep.NewPassword,
										ConfirmPassword 	= SalesRep.ConfirmPassword;

	private UI.DETextArea 	Address 			= SalesRep.Address;

	private UI.DEChoice		AllTeamID 			= SalesTeam.AllTeamID,
										AllEmployeeID		= SalesRep.AllEmployeeID,
										Department			= SalesRep.Department;

	private DETableUI.DETable CustomerTable = SalesRep.CustomerTable;

	private JSeparator 	Separator 	= new JSeparator(),
								Separator1	= new JSeparator();

	private UI.DEButtons forDetail 		= SalesRep.forDetail.add( SalesRep.DetailOptions, this ),
									forPassword 	= SalesRep.forPassword.add( SalesRep.PasswordOptions, this ),
									forAddNew	= SalesRep.forAddNew.add( SalesRep.AddNewOptions, this ),
									forEdit			= SalesRep.forEdit.add( SalesRep.EditOptions, this ),
									forLogin		= SalesRep.forLogin.add( SalesRep.LoginOptions, this ),
									forTable		= SalesRep.forTable.add( SalesRep.TableOptions, this );

	private Component 	group[]		= {	AllEmployeeID, EmployeeName },
									group1[]	= { Separator, ContactNO, Address, },
									group2[]	= { Department, AllTeamID, SalesTeamInfo },
									group3[]	= {	Separator1, OldPassword, NewPassword, ConfirmPassword },
									group4[]	= { forDetail, forPassword, forAddNew, forEdit, forLogin },
									group5[]	= { CustomerTable, forTable };

	private String SecurityMode = "High";
	private FurnitureSystem TheSystem;

	private String newTeam;
	private String newStaff;

	private boolean reLog = false;

	public SalesRepCustomer( FurnitureSystem theSystem ) {
		this();
		TheSystem = theSystem;
	}

	public SalesRepCustomer() {
		super();
		//==================================================
		gridx( 0 ); fill( BOTH ); gridwidth( 2 );insets( 0, 0, 3, 0);
			gridy( 0 ); add( Header );

		insets( 3, 0, 3, 0);
			gridy( 3 ); add( Separator );
			gridy( 9 ); add( Separator1 );

		insets( 3, 6, 6, 6);
			gridy( 14 ); add( CustomerTable.withScrollbar() );
				CustomerTable.setModel( ResultsModel );
			gridy( 15 ); add( group4 );
			gridy( 15 ); add( forTable );
		//======================================================================
		fill(  NONE ); gridwidth( 1 ); anchor( EAST ); insets( 3, 6, 3, 3);
			gridy( 1 ); add( AllEmployeeID.FLabel );
			gridy( 2 ); add( EmployeeName.FLabel );
			//gridy( 3 ); add( Separator );
			gridy( 4 ); add( Department.FLabel );
			gridy( 5 ); add( ContactNO.FLabel );
			gridy( 6 ); add( Address.FLabel );
			gridy( 7 ); add( AllTeamID.FLabel );
			gridy( 8 ); add( SalesTeamInfo.FLabel );
			//gridy( 9 ); add( Separator1 );
			gridy( 10 ); add( OldPassword.FLabel );
			gridy( 11 ); add( NewPassword.FLabel );
			gridy( 12 ); add( ConfirmPassword.FLabel );

		gridx( 1 ); anchor( WEST); insets( 3, 3, 3, 6);
			gridy( 1 ); add( AllEmployeeID );
			gridy( 2 ); add( EmployeeName );
			//gridy( 3 ); add( Separator );
			gridy( 4 ); add( Department );
			gridy( 5 ); add( ContactNO );
			gridy( 6 ); add( Address.withScrollbar() );
			gridy( 7 ); add( AllTeamID );
			gridy( 8 ); add( SalesTeamInfo );
			//gridy( 9 ); add( Separator1 );
			gridy( 10 ); add( OldPassword );
			gridy( 11 ); add( NewPassword );
			gridy( 12 ); add( ConfirmPassword );

		AllEmployeeID.setEditable( true );
		AllEmployeeID.addItemListener( this );
		AllTeamID.addItemListener( this );
		Department.addItemListener( this );
		validate();
	}

	public SalesRepCustomer toLog( boolean reLog ) {
		this.reLog = reLog;
		toLog();
		return this;
	}

	public void toLog() {
		Header.setText( "Login" );
		show( group );	enable( group );
			AllEmployeeID.FLabel.setText( "User ID" );
			EmployeeName.setVisible( false );
		hide( group1 );
		hide( group2 );
		show( group3 ); hide( group3 );
			OldPassword.setVisible( true );
			OldPassword.FLabel.setText( "Password" );
		hide( group4 );
		forLogin.setVisible( true );
		hide( group5 );

		OldPassword.addKeyListener( this );
		AllEmployeeID.addActionListener( this );
		AllEmployeeID.select( "" );
	}

	private void toLogin() {
		dbODBC odbc = new dbODBC();
		String EmployeeID = (String) AllEmployeeID.getSelectedItem();
		String Password = OldPassword.getPasswordText();
		odbc.executeQuery( 	"Select " +
												"Employee.StaffID, " +
												"Employee.StaffName, " +
												"Employee.Password, " +
												"Employee.Department " +
										"From " +
												"Employee " +
										"Where " +
												"Employee.StaffID = '" + EmployeeID + "'"
									);
		if ( odbc.move( "Next" ) ) {
			if ( Password.equals( odbc.getString( "Password" ) )  || odbc.getString( "Password" ) == null ) {
				setVisible( false );
				if ( reLog ) {
					TheSystem.refresh( odbc.getString( "StaffID" ), odbc.getString( "StaffName" ), odbc.getString( "Department" ) );
					setVisible( false );
				}
				else
					new FurnitureSystem( odbc.getString( "StaffID" ), odbc.getString( "StaffName" ), odbc.getString( "Department" ) );

				odbc.close();
			}
			else {
				OldPassword.setText( "" );
				JOptionPane.showMessageDialog	( 	this, "The Password doesn't match", "User NOT FOUND", JOptionPane.INFORMATION_MESSAGE );
				OldPassword.requestFocus();
			}
		}
		else {
			AllEmployeeID.select( "" );
			JOptionPane.showMessageDialog	( 	this, "The User ID doesn't match", "User NOT FOUND", JOptionPane.INFORMATION_MESSAGE );
		}
	}

	public SalesRepCustomer toDetail() {
		SecurityMode = TheSystem.getSecurityMode();
		Header.setText( "Sales Representative Detail" );
		show( group );	disable( group );
			if ( SecurityMode.equals( "Low" ) || SecurityMode.equals( "Medium" ) )
				AllEmployeeID.setEnabled( true );
		show( group1 ); disable( group1 );
		show( group2 ); disable( group2 );
		hide( group3 );
		hide( group4 );
		forDetail.setVisible( true );
		hide( group5 );

		doDetail();
		return this;
	}

	private void doDetail() {
		AllEmployeeID.removeItem( newStaff );
		AllEmployeeID.select( TheSystem.getUserID() );
		toValidate();
		toValidateOther();
	}

	public SalesRepCustomer toChangePassword() {
		Header.setText( "Change Password" );
		AllEmployeeID.select( TheSystem.getUserID() );
		toValidate();

		show( group );	disable( group );
		hide( group1 );
		hide( group2 );
		show( group3 ); enable( group3 );
			Department.setVisible( false );
		hide( group4 );
			forPassword.setVisible( true );
		hide( group5 );


			AllEmployeeID.removeItem( newStaff );
			OldPassword.setText( "" );
			NewPassword.setText( "" );
			ConfirmPassword.setText( "" );
			OldPassword.requestFocus();

		return this;
	}

	public void toEdit() {
		SecurityMode = TheSystem.getSecurityMode();
		Header.setText( "Edit Record" );
		disable( group );
		EmployeeName.setEnabled( true );
		enable( group1 );
		disable( group2 );
			if ( SecurityMode.equals( "Low" ) || SecurityMode.equals( "Medium" ) )
				enable( group2 );
		hide( group3 );
		hide( group4 );
			forEdit.setVisible( true );

		hide( group5 );

			AllEmployeeID.removeItem( newStaff );
			AllTeamID.removeItem( newTeam );
			EmployeeName.requestFocus();
	}

	public SalesRepCustomer toAddNew() {
		SecurityMode = TheSystem.getSecurityMode();
		if ( SecurityMode.equals( "Low" ) ) {
			autoAssign();
			Header.setText( "Add New Record" );
			show( group );	disable( group );
				EmployeeName.setEnabled( true );
			show( group1 );	enable( group1 );
				AllTeamID.setVisible( false );
				SalesTeamInfo.setVisible( false );

			show( group2 ); enable( group2 );

			show( group3 ); enable( group3 );
				OldPassword.setVisible( false );
			hide( group4 );
				forAddNew.setVisible( true );

			hide( group5 );

			if ( ! AllEmployeeID.getSelectedItem().equals( (Object) newStaff ) ) {
				AllEmployeeID.removeItem( newStaff );
				AllEmployeeID.addItem( newStaff );
				AllEmployeeID.select( newStaff );

				AllTeamID.removeItem( newTeam );
				AllTeamID.addItem( newTeam );
			}

			toValidateOther();
			EmployeeName.requestFocus();
		}
		return this;
	}

	public SalesRepCustomer toAllCustomers() {
		Header.setText( "All Customer" );
		show( group );	disable( group );
			AllEmployeeID.setEnabled( true );
		hide( group1 );
		hide( group2 );
		hide( group3 );
		hide( group4 );
		show( group5 );

		doDetail();

		return this;
	}

	private synchronized void doAllCustomers() {
		String TheEmployeeID = (String) AllEmployeeID.getSelectedItem();

		if ( TheEmployeeID != null ) {
			int RecordCount = SQLTool.RecordCount( "Select " +
																			"Distinct Order.CustomerID " +
																		"From " +
																			"Customer, " +
																			"[Order] " +
																		"Where " +
																			"Customer.CustomerID = Order.CustomerID And " +
																			"Customer.StaffID = '" + TheEmployeeID +"' "
																		);
			dbODBC odbc = new dbODBC( TheSystem.getConnection() );
			odbc.executeQuery( 	"Select " +
												"FrequencyTable.CustomerID, " +
												"FrequencyTable.CustomerName, " +
												"TotalTable.Total, " +
												"FrequencyTable.Frequency " +
											"From " +
												"( " +
													"Select " +
														"Customer.CustomerID, " +
														"Customer.CustomerName, " +
														"Count( Customer.CustomerID ) As Frequency " +
													"From " +
														"Customer, " +
														"[Order] " +
													"Where " +
														"Customer.CustomerID = Order.CustomerID And " +
														"Customer.StaffID = '" + TheEmployeeID + "' " +
													"Group By " +
														"Customer.CustomerID, " +
														"Customer.CustomerName " +
												") FrequencyTable, " +
												"( " +
													"Select " +
														"Order.CustomerID, " +
														"Sum( OrderLine.Quantity * OrderLine.UnitPrice ) As Total " +
													"From " +
														"[Order Line] OrderLine, " +
														"[Order] " +
													"Where " +
														"Order.OrderID = OrderLine.OrderID " +
													"Group By " +
														"Order.CustomerID " +
												") TotalTable " +
											"Where " +
												"FrequencyTable.CustomerID = TotalTable.CustomerID " +
											"Order By " +
												"FrequencyTable.Frequency "
										);
			ResultsModel.setResultSet( odbc.getResultSet(), RecordCount );

			int RowCount			= 	CustomerTable.getRowCount();
			int SelectColumn 	= 	CustomerTable.findColumn( "Total" );
			CustomerTable.validate();
			String Value;
			if ( SelectColumn >= 0 ) {
				for ( int a = 0; a< RowCount; a++ ) {
					Value = (String) CustomerTable.getValueAt( a, SelectColumn );
					CustomerTable.setValueAt( DETool.toCurrency( Value ), a, SelectColumn );
				}
			}

			CustomerTable.AdjustColumnWidths();
			CustomerTable.getColumn( SelectColumn ).setPreferredWidth( 150 );
		}
	}

	private void toRefresh() {
		if ( forEdit.isVisible() ) {
			toValidate();
		}
		else if ( forAddNew.isVisible() ) {
			EmployeeName.setText( "" );
			ContactNO.setText( "" );
			Address.setText( "" );
			NewPassword.setText( "" );
			ConfirmPassword.setText( "" );
			String TheTeamID = (String) AllTeamID.getSelectedItem();
			AllTeamID.select( newTeam );
			AllTeamID.select( 0 );
			AllTeamID.select( TheTeamID );
		}
	}

	private void toSaveRecord() {
		boolean HaveTeam = false;
		boolean toBreak = true;
		dbODBC odbc = new dbODBC( TheSystem.getConnection() );
		String TheNewPassword = NewPassword.getPasswordText();
		String TheConfirmPassword = ConfirmPassword.getPasswordText();
		if ( TheConfirmPassword.equals( TheNewPassword ) ) {
			String TheEmployeeID = (String) AllEmployeeID.getSelectedItem();
			String TheEmployeeName = EmployeeName.getText();
			String TheContactNO = ContactNO.getText();
			String TheAddress = Address.getText();
			String TheDepartment  = (String) Department.getSelectedItem();
			String TheTeamID = (String) AllTeamID.getSelectedItem();
			String TheSalesTeamInfo = SalesTeamInfo.getText();

			if ( TheEmployeeName.equals( "" ) || TheContactNO.equals( "" ) || TheAddress.equals( "" ) || TheNewPassword.equals( "" ) )
				JOptionPane.showMessageDialog	( 	this, "Imcomplete Form", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
			else if ( TheEmployeeName.length() > 20 )
				JOptionPane.showMessageDialog	( 	this, "The length of Employee Name should not greater than 20 characters", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
			else if ( TheContactNO.length() > 10 )
				JOptionPane.showMessageDialog	( 	this, "The length of Contact NO should not greater than 10 characters", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
			else {

				if ( TheDepartment.equals( "Sales" ) || TheDepartment.equals( "SalesL" ) )
					HaveTeam = true;

				if ( HaveTeam ) {
					if ( TheSalesTeamInfo.equals( "" ) )
						JOptionPane.showMessageDialog	( 	this, "Imcomplete Form", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
					if ( TheSalesTeamInfo.equals( "[ new Team ]" ) )
						JOptionPane.showMessageDialog	( 	this, "Please Input another Sales Team Info", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
					else if ( TheTeamID.length() != 2 )
						JOptionPane.showMessageDialog	( 	this, "The length of Type ID should 2 characters", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
					else if ( TheSalesTeamInfo.length() > 50 )
						JOptionPane.showMessageDialog	( 	this, "The length of Sales Team Info should not greater than 50 characters", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
					else {
						odbc.executeQuery( 	"Select " +
															"TeamID " +
														"From " +
															"[Sales Team] " +
														"Where " +
															"SalesTeamInfo = '" + TheSalesTeamInfo + "' "
													);

						if ( odbc.move( "Next" ) ) {
							if ( ! TheTeamID.equals( odbc.getString( "TeamID" ) ) ) {
								JOptionPane.showMessageDialog	( 	this, "New Team ID but Redundant SalesTeamInfo", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
							}
							else
								toBreak = false;
						}
						else {
							odbc.executeQuery( 	"Select " +
																"SalesTeam.SalesTeamInfo " +
															"From " +
																"[Sales Team] SalesTeam " +
															"Where " +
																"SalesTeam.TeamID = '" + TheTeamID + "' "
														);
							if ( odbc.move( "Next" ) ) {
								if ( ! TheSalesTeamInfo.equals( odbc.getString( "SalesTeamInfo" ) ) ) {
									odbc.executeUpdate( "Update " +
																		"[Sales Team] SalesTeam " +
																	"Set " +
																		"SalesTeam.SalesTeamInfo = '" + TheSalesTeamInfo + "' " +
																	"Where " +
																		"SalesTeam.TeamID = '" + TheTeamID + "' "
																);
									//===================check isUpdated Sales Team Table=============================
									if ( SQLTool.CheckRecordExists( odbc, "Select " +
																								"SalesTeam.TeamID " +
																							"From " +
																								"[Sales Team] SalesTeam " +
																							"Where " +
																								"SalesTeam.TeamID = '" + TheTeamID  + "' And " +
																								"SalesTeam.SalesTeamInfo = '" + TheSalesTeamInfo + "' " ) ) {

										JOptionPane.showMessageDialog	( 	this, "Successful", "Sales Team Info UPDATED", JOptionPane.INFORMATION_MESSAGE );
										toBreak = false;
									}
								}
							}
							else {
								odbc.executeUpdate( "Insert Into " +
																	"[Sales Team] " +
																"Values ( '" +
																	TheTeamID + "', '" +
																	TheSalesTeamInfo + "' ) "
															);
								//===================check isUpdated Type Table=============================
								if ( SQLTool.CheckRecordExists( odbc, "Select " +
																							"SalesTeam.TeamID " +
																						"From " +
																							"[Sales Team] SalesTeam " +
																						"Where " +
																							"SalesTeam.TeamID = '" + TheTeamID  + "' And " +
																							"SalesTeam.SalesTeamInfo = '" + TheSalesTeamInfo + "' " ) ) {

									JOptionPane.showMessageDialog	( 	this, "Successful", "New Team SAVED", JOptionPane.INFORMATION_MESSAGE );
									toBreak = false;
								}
							}
						}
					}
				}
				else
					toBreak = false;

				if ( ! toBreak ) {
					if ( HaveTeam ) {
						odbc.executeUpdate( 	"Insert Into " +
															"Employee " +
														"Values ( '" +
															TheEmployeeID + "', '" +
															TheEmployeeName + "', '" +
															TheContactNO + "', '" +
															TheAddress + "', '" +
															TheNewPassword + "', '" +
															TheTeamID + "', '" +
															TheDepartment + "' ) "
														);
						//======================Check isSaved Product Table=========================
						if ( SQLTool.CheckRecordExists( odbc, "Select " +
																					"Employee.StaffID " +
																				"From " +
																					"Employee " +
																				"Where " +
																					"Employee.StaffID = '" + TheEmployeeID + "' And " +
																					"Employee.StaffName = '" + TheEmployeeName + "' And " +
																					"Employee.ContactNo = '" + TheContactNO + "' And " +
																					"Employee.Address  = '" + TheAddress  + "' And " +
																					"Employee.Password = '" + TheNewPassword + "' And " +
																					"Employee.TeamID = '" + TheTeamID + "' And " +
																					"Employee.Department = '" + TheDepartment + "' " ) ) {
							JOptionPane.showMessageDialog	( 	this, "Successful", "Record SAVED", JOptionPane.INFORMATION_MESSAGE );
							AllEmployeeID.select( TheEmployeeID );
							autoAssign();
							toDetail();
						}
						//======================================================================
						else
							JOptionPane.showMessageDialog	( 	this, "System may not save The Record", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
					}
					else {
						TheTeamID = null;
						odbc.executeUpdate( 	"Insert Into " +
															"Employee " +
														"Values ( '" +
															TheEmployeeID + "', '" +
															TheEmployeeName + "', '" +
															TheContactNO + "', '" +
															TheAddress + "', '" +
															TheNewPassword + "', " +
															TheTeamID + ", '" +
															TheDepartment + "' ) "
														);
						//======================Check isSaved Staff Table=========================
						if ( SQLTool.CheckRecordExists( odbc, "Select " +
																					"Employee.StaffID " +
																				"From " +
																					"Employee " +
																				"Where " +
																					"Employee.StaffID = '" + TheEmployeeID + "' And " +
																					"Employee.StaffName = '" + TheEmployeeName + "' And " +
																					"Employee.ContactNo = '" + TheContactNO + "' And " +
																					"Employee.Address  = '" + TheAddress  + "' And " +
																					"Employee.Password = '" + TheNewPassword + "' And " +
																					"Employee.TeamID Is " + TheTeamID + " And " +
																					"Employee.Department = '" + TheDepartment + "' " ) ) {
							JOptionPane.showMessageDialog	( 	this, "Successful", "Record SAVED", JOptionPane.INFORMATION_MESSAGE );

							AllEmployeeID.select( TheEmployeeID );
							autoAssign();
							toDetail();
						//======================================================================
						}
						else
							JOptionPane.showMessageDialog	( 	this, "System may not save The Record", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
					}
				}
			}
		}
		else
			JOptionPane.showMessageDialog	( 	this, "The Password doesn't match", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );

		NewPassword.setText( "" );
		ConfirmPassword.setText( "" );
	}

	private void toUpdateRecord() {
		boolean HaveTeam = false;
		boolean toBreak = true;
		dbODBC odbc = new dbODBC( TheSystem.getConnection() );
		String TheEmployeeID = (String) AllEmployeeID.getSelectedItem();
		String TheEmployeeName = EmployeeName.getText();
		String TheContactNO = ContactNO.getText();
		String TheAddress = Address.getText();
		String TheDepartment  = (String) Department.getSelectedItem();
		String TheTeamID = (String) AllTeamID.getSelectedItem();
		String TheSalesTeamInfo = SalesTeamInfo.getText();

		if ( TheEmployeeName.equals( "" ) || TheContactNO.equals( "" ) || TheAddress.equals( "" ) )
			JOptionPane.showMessageDialog	( 	this, "Imcomplete Form", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
		else if ( TheEmployeeName.length() > 20 )
			JOptionPane.showMessageDialog	( 	this, "The length of Employee Name should not greater than 20 characters", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
		else if ( TheContactNO.length() > 10 )
			JOptionPane.showMessageDialog	( 	this, "The length of Contact NO should not greater than 10 characters", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
		else {

			toBreak = false;
			if ( TheDepartment.equals( "Sales" ) || TheDepartment.equals( "SalesL" ) )
				HaveTeam = true;
			else if ( ! ( Department.equals( "Sales" ) || Department.equals( "SalesL" ) ) ) {
				System.out.println( TheEmployeeID );
				if ( SQLTool.RecordCount( "Select " +
															"Customer.CustomerID " +
														"From " +
															"Customer " +
														"Where " +
															"Customer.StaffID =  '" + TheEmployeeID + "' ") > 0 ) {
					toBreak = true;
					if ( Department.equals( "Sales" ) )
						Department.select( "Sales" );
					else if ( Department.equals( "SalesL" ) )
						Department.select( "SalesL" );
					JOptionPane.showMessageDialog	( 	this, "Employee are associated with customer", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
				}
			}

			if ( ! toBreak ) {
				if ( HaveTeam ) {
					if ( TheSalesTeamInfo.equals( "" ) )
						JOptionPane.showMessageDialog	( 	this, "Imcomplete Form", "Record NOT UPDATE", JOptionPane.INFORMATION_MESSAGE );
					if ( TheSalesTeamInfo.equals( "[ new Team ]" ) )
						JOptionPane.showMessageDialog	( 	this, "Please Input another Sales Team Info", "Record NOT UPDATE", JOptionPane.INFORMATION_MESSAGE );
					else if ( TheTeamID.length() != 2 )
						JOptionPane.showMessageDialog	( 	this, "The length of Type ID should 2 characters", "Record NOT UPDATE", JOptionPane.INFORMATION_MESSAGE );
					else if ( TheSalesTeamInfo.length() > 50 )
						JOptionPane.showMessageDialog	( 	this, "The length of Sales Team Info should not greater than 50 characters", "Record NOT UPDATE", JOptionPane.INFORMATION_MESSAGE );
					else {
						odbc.executeQuery( 	"Select " +
															"TeamID " +
														"From " +
															"[Sales Team] " +
														"Where " +
															"SalesTeamInfo = '" + TheSalesTeamInfo + "' "
													);
						if ( odbc.move( "Next" ) ) {
							if ( ! TheTeamID.equals( odbc.getString( "TeamID" ) ) ) {
								JOptionPane.showMessageDialog	( 	this, "Redundant SalesTeamInfo", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
								toBreak = true;
							}
							else
								toBreak = false;
						}
						else {
							odbc.executeQuery( 	"Select " +
																"SalesTeam.SalesTeamInfo " +
															"From " +
																"[Sales Team] SalesTeam " +
															"Where " +
																"SalesTeam.TeamID = '" + TheTeamID + "' "
														);
							if ( odbc.move( "Next" ) ) {
								if ( ! TheSalesTeamInfo.equals( odbc.getString( "SalesTeamInfo" ) ) ) {
									odbc.executeUpdate( "Update " +
																		"[Sales Team] SalesTeam " +
																	"Set " +
																		"SalesTeam.SalesTeamInfo = '" + TheSalesTeamInfo + "' " +
																	"Where " +
																		"SalesTeam.TeamID = '" + TheTeamID + "' "
																);
									//===================check isUpdated Sales Team Table=============================
									if ( SQLTool.CheckRecordExists( odbc, "Select " +
																								"SalesTeam.TeamID " +
																							"From " +
																								"[Sales Team] SalesTeam " +
																							"Where " +
																								"SalesTeam.TeamID = '" + TheTeamID  + "' And " +
																								"SalesTeam.SalesTeamInfo = '" + TheSalesTeamInfo + "' " ) ) {

										JOptionPane.showMessageDialog	( 	this, "Successful", "Sales Team Info UPDATED", JOptionPane.INFORMATION_MESSAGE );
										toBreak = false;
									}
								}
							}
						}
					}
				}
				else
					toBreak = false;
			}

			if ( ! toBreak ) {
				if ( HaveTeam ) {
					odbc.executeUpdate( 	"Update " +
														"Employee " +
													"Set " +
														"Employee.StaffName = '" + TheEmployeeName + "', " +
														"Employee.ContactNo = '" + TheContactNO + "', " +
														"Employee.Address = '" + TheAddress + "', " +
														"Employee.TeamID = '" + TheTeamID + "', " +
														"Employee.Department = '" + TheDepartment + "' " +
													"Where " +
														"Employee.StaffID = '" + TheEmployeeID + "' "
													);
					//======================Check isSaved Product Table=========================
					if ( SQLTool.CheckRecordExists( odbc, "Select " +
																				"Employee.StaffID " +
																			"From " +
																				"Employee " +
																			"Where " +
																				"Employee.StaffID = '" + TheEmployeeID + "' And " +
																				"Employee.StaffName = '" + TheEmployeeName + "' And " +
																				"Employee.ContactNo = '" + TheContactNO + "' And " +
																				"Employee.Address  = '" + TheAddress  + "' And " +
																				"Employee.TeamID = '" + TheTeamID + "' And " +
																				"Employee.Department = '" + TheDepartment + "' " ) ) {
						JOptionPane.showMessageDialog	( 	this, "Successful", "Record UPDATED", JOptionPane.INFORMATION_MESSAGE );
						toDetail();
						AllEmployeeID.select( TheEmployeeID );
					}
					//======================================================================
					else
						JOptionPane.showMessageDialog	( 	this, "System may not update The Record", "Record NOT UPDATE", JOptionPane.INFORMATION_MESSAGE );
				}
				else {
					TheTeamID = null;
					odbc.executeUpdate( 	"Update " +
														"Employee " +
													"Set " +
														"Employee.StaffName = '" + TheEmployeeName + "', " +
														"Employee.ContactNo = '" + TheContactNO + "', " +
														"Employee.Address = '" + TheAddress + "', " +
														"Employee.TeamID = " + TheTeamID + ", " +
														"Employee.Department = '" + TheDepartment + "' " +
													"Where " +
														"Employee.StaffID = '" + TheEmployeeID + "' "
													);
					//======================Check isSaved Product Table=========================
					if ( SQLTool.CheckRecordExists( odbc, "Select " +
																				"Employee.StaffID " +
																			"From " +
																				"Employee " +
																			"Where " +
																				"Employee.StaffID = '" + TheEmployeeID + "' And " +
																				"Employee.StaffName = '" + TheEmployeeName + "' And " +
																				"Employee.ContactNo = '" + TheContactNO + "' And " +
																				"Employee.Address  = '" + TheAddress  + "' And " +
																				"Employee.TeamID Is " + TheTeamID + " And " +
																				"Employee.Department = '" + TheDepartment + "' " ) ) {
						JOptionPane.showMessageDialog	( 	this, "Successful", "Record UPDATED", JOptionPane.INFORMATION_MESSAGE );
						toDetail();
					//======================================================================
					}
					else
						JOptionPane.showMessageDialog	( 	this, "System may not update The Record", "Record NOT UPDATE", JOptionPane.INFORMATION_MESSAGE );
				}
			}
		}
	}

	private void toConfirmed() {
		dbODBC odbc = new dbODBC( TheSystem.getConnection() );
		String TheEmployeeID = (String) AllEmployeeID.getSelectedItem();
		String TheOldPassword = OldPassword.getPasswordText();
		String TheNewPassword = NewPassword.getPasswordText();
		String TheConfirmPassword = ConfirmPassword.getPasswordText();
		if ( TheConfirmPassword.equals( TheNewPassword ) ) {
			odbc.executeQuery( 	"Select " +
													"Employee.StaffID, " +
													"Employee.Password " +
											"From " +
													"Employee " +
											"Where " +
													"Employee.StaffID = '" + TheEmployeeID + "'"
										);
			if ( odbc.move( "Next" ) ) {

				if ( TheOldPassword.equals( odbc.getString( "Password" ) ) || ( TheOldPassword.equals( "" ) && odbc.getString( "Password" ) == null )  ) {
					odbc.executeUpdate( 	"Update " +
														"Employee " +
													"Set " +
														"Employee.Password = '" + TheNewPassword + "' " +
													"Where " +
														"Employee.StaffID = '" + TheEmployeeID + "'"
												);
					//===================check isUpdated Password Table=============================
					if ( SQLTool.CheckRecordExists( odbc, "Select " +
																				"Employee.StaffID " +
																			"From " +
																				"Employee " +
																			"Where " +
																				"Employee.StaffID = '" + TheEmployeeID + "' And " +
																				"Employee.Password = '" + TheNewPassword + "' " ) ) {
						JOptionPane.showMessageDialog	( 	this, "Successful", "Password UPDATED", JOptionPane.INFORMATION_MESSAGE );
						setVisible( false );
					}
					else
						JOptionPane.showMessageDialog	( 	this, "System may not update your password", "Password NOT UPDATE", JOptionPane.INFORMATION_MESSAGE );
				}
				else
					JOptionPane.showMessageDialog	( 	this, "The Old Password is not correct", "Password NOT UPDATE", JOptionPane.INFORMATION_MESSAGE );
			}
			else
				JOptionPane.showMessageDialog	( 	this, "The System is facing an error on linking database", "Password NOT UPDATE", JOptionPane.INFORMATION_MESSAGE );
		}
		else
			JOptionPane.showMessageDialog	( 	this, "The Confirm Password does not match New Password", "Password NOT UPDATE", JOptionPane.INFORMATION_MESSAGE );

		OldPassword.setText( "" );
		NewPassword.setText( "" );
		ConfirmPassword.setText( "" );
	}

	private void toDone() {
		setVisible( false );
	}

	private void toBack() {
		toCancel();
	}

	private void toCancel() {
		if ( forLogin.isVisible() ) {
			setVisible( false );
			if ( ! reLog )
				System.exit( 0 );
		}
		else if ( forEdit.isVisible() ) {
			String TheEmployeeID = (String) AllEmployeeID.getSelectedItem();
			toDetail();
			AllEmployeeID.select( TheEmployeeID );
		}
		else if ( forAddNew.isVisible() )
			toDetail();
		else if ( forPassword.isVisible() ) {
			setVisible( false );
			OldPassword.setText( "" );
			NewPassword.setText( "" );
			ConfirmPassword.setText( "" );
		}
		else if ( forTable.isVisible() )
			toDetail();
	}

	private void toPrintRecords() {
		final ExtFrame PrintFrame 				= new ExtFrame();
		PrintFrame.setResizable( false );
		PrintFrame.setTitle( "Print Preview" );
		PrintFrame.getContentPane().setLayout( new java.awt.FlowLayout() );
		DEObjects Objects = new DEObjects();
		final DEObjects.DETextArea PrintPreview = Objects.new DETextArea( 20, 36 );
		String Text = "Employee ID : " + (String) AllEmployeeID.getSelectedItem() + "\t" + " \t" + "\n" +
							"Employee Name : " + EmployeeName.getText() + "\t" + " \t"  + "\n" +
							"\n" +
							"Customer ID" + " \t" + "Customer Name" + "\t" + "\t" + "Total" + "\t" + "\t" + "Frequency" + "\n";

		Object AllValueOfCustomerID[]		= CustomerTable.getAllValueAtColumn( "CustomerID" );
		Object AllValueOfCustomerName[]	= CustomerTable.getAllValueAtColumn( "CustomerName" );
		Object AllValueOfTotal[]					= CustomerTable.getAllValueAtColumn( "Total" );
		Object AllValueOfFrequency[]			= CustomerTable.getAllValueAtColumn( "Frequency" );

		int RowCount = AllValueOfCustomerID.length;
		for ( int a = 0; a < RowCount; a++ ) {
			Text = Text + 	(String) AllValueOfCustomerID[ a ] + " \t\t " + (String) AllValueOfCustomerName[ a ] + "\t\t" +
									(String) AllValueOfTotal[ a ] + "\t" + "\t" + (String) AllValueOfFrequency[ a ] +  " \n";
		}

		PrintPreview.setText( Text );
		//PrintPreview.setEditable( false );
		DEObjects.DEButton 	OKButton = Objects.new DEButton( "&OK", null ),
										CancelButton = Objects.new DEButton( "&Cancel", null );
		OKButton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent event ) {
				DEPrint.printComponent( PrintPreview );
				PrintFrame.dispose();
			}
		} );
		CancelButton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent event ) {
				PrintFrame.dispose();
			}
		} );

		PrintFrame.getContentPane().add( PrintPreview.withScrollbar() );
		PrintPreview.withScrollbar().setPreferredSize( PrintPreview.getPreferredSize().getSize() );
		PrintFrame.getContentPane().add( OKButton );
		PrintFrame.getContentPane().add( CancelButton );
		PrintFrame.setVisible( true );
		PrintFrame.setSize( PrintFrame.getPreferredSize() );
		PrintFrame.validate();
	}

	private void autoAssign() {
		dbODBC odbc = new dbODBC( TheSystem.getConnection() );
		//=========================AutoAssign==============================
		odbc.executeQuery( 	"Select " +
											"SalesTeam.TeamID " +
										"From " +
											"[Sales Team] SalesTeam " +
										"Order By " +
											"SalesTeam.TeamID"
							);
		if ( odbc.move( "Next" ) ) {
			if ( odbc.move( "Last" ) ) {
				String theTeamID = odbc.getString( "TeamID" );
				String LeftString = "T";
				String RightString = "" + theTeamID.charAt( 1 );
				int newValue = Integer.parseInt( RightString ) + 1;
				String toStringValue = Integer.toString( newValue );
				newTeam = LeftString + toStringValue;
			}
		}
		else
			newTeam = "T1";

		//==========================AutoAssign Employee==============================
		odbc.executeQuery( 	"Select " +
											"Employee.StaffID " +
										"From " +
											"Employee " +
										"Order By " +
											"Employee.StaffID"
									);


		if ( odbc.move( "Next" ) ) {
			if ( odbc.move( "Last" ) ) {
				String theStaffID = odbc.getString( "StaffID" );
				String LeftString = "EP";
				String RightString = theStaffID.substring( 2, theStaffID.length() );
				int newValue = Integer.parseInt( RightString ) + 1 + 1000;
				String toStringValue = Integer.toString( newValue );
				newStaff = LeftString + toStringValue.substring( 1, toStringValue.length() );
			}
		}
		else
			newStaff = "EP001";
		//================================================================
	}

	private void toValidate() {
		dbODBC odbc = new dbODBC( TheSystem.getConnection() );
		String SelectedEmployeeID = (String) AllEmployeeID.getSelectedItem();
		odbc.executeQuery( 	"Select " +
											"Employee.StaffID, " +
											"Employee.StaffName, " +
											"Employee.Department, " +
											"Employee.ContactNo, " +
											"Employee.Address, " +
											"Employee.TeamID " +
										"From " +
											"Employee " +
										"Where " +
											"Employee.StaffID = '" + SelectedEmployeeID + "'"
									);
		if ( odbc.move( "Next" ) ) {
			EmployeeName.setText( odbc.getString( "StaffName" ) );
			ContactNO.setText(  odbc.getString( "ContactNo" ) );
			Address.setText(  odbc.getString( "Address" ) );
			String TheTeamID = odbc.getString( "TeamID" );
			String aDepartment = odbc.getString( "Department" );
			if ( TheTeamID != null  )
				AllTeamID.select( TheTeamID );

			Department.select( aDepartment );
			if ( CustomerTable.isVisible() )
					doAllCustomers();
		}
		else {
			EmployeeName.setText( "" );

			ContactNO.setText( "" );
			Address.setText( "" );
		}
	}

	public synchronized void toValidateOther() {
		dbODBC odbc = new dbODBC( TheSystem.getConnection() );
		String SelectedTeamID = (String) AllTeamID.getSelectedItem();
		odbc.executeQuery( 	"Select " +
											"SalesTeam.TeamID, " +
											"SalesTeam.SalesTeamInfo " +
										"From " +
											"[Sales Team] SalesTeam " +
										"Where " +
											"SalesTeam.TeamID = '" + SelectedTeamID + "'"
									);
		if ( odbc.move( "Next" ) )
			SalesTeamInfo.setText( odbc.getString( "SalesTeamInfo" ) );
		else if ( SelectedTeamID.equals( newTeam ) )
			SalesTeamInfo.setText( "[ new Team ]" );
		//====================================================
		String SelectedDepartment = (String) Department.getSelectedItem();
		if ( SelectedDepartment.equals( "Sales" ) || SelectedDepartment.equals( "SalesL" ) ) {
			show( group2 );
			if ( CustomerTable.isVisible() ) {
				AllTeamID.setVisible( false );
				disable( group2 );
			}
		}
		else {
			hide( group2 );
			Department.setVisible( true );
			if ( CustomerTable.isVisible() )
				disable( group2 );
		}
	}

	public void actionPerformed( ActionEvent event ) {
		System.out.println( event.getActionCommand() );
		String command = event.getActionCommand();
		if( command.equals( "Edit" ) )
			toEdit();
		else if ( command.equals( "Done" ) )
			toDone();
		else if ( command.equals( "Cancel" ) )
			toCancel();
		else if ( command.equals( "Save Record" ) )
			toSaveRecord();
		else if ( command.equals( "Update Record" ) )
			toUpdateRecord();
		else if ( command.equals( "Confirmed" ) )
			toConfirmed();
		else if ( command.equals( "Login" ) )
			toLogin();
		else if ( command.equals( "Back" ) )
			toBack();
		else if ( command.equals( "Refresh" ) )
			toRefresh();
		else if ( command.equals( "Print Records" ) )
			toPrintRecords();
		else if ( command.equals( "comboBoxEdited" ) ) {
			AllEmployeeID.removeActionListener( this );
			toLogin();
			AllEmployeeID.addActionListener( this );
		}
	}

	//=======================itemListener=================================
	public void itemStateChanged( ItemEvent event ) {
		if ( ! forLogin.isVisible() ) {
			if ( event.getSource() == AllEmployeeID ) {
				toValidate();
			}
			//==================================================
			toValidateOther();
		}
	}
	//========================KeyListener============================
	public void keyPressed( KeyEvent event ) {
		if ( event.getKeyCode() == event.VK_ENTER ) {
			if ( forLogin.isVisible() ) {
				if ( ! OldPassword.getPasswordText().equals( "" ) )
					toLogin();
			}
		}
	}
	public void keyReleased( KeyEvent event ) {}
	public void keyTyped( KeyEvent event ) {}
	//========================mouseListener============================
}