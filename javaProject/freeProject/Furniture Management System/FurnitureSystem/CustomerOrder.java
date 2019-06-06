import javax.swing.JSeparator;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class CustomerOrder extends DEForm implements ActionListener, MouseListener, ItemListener, KeyListener {
	private dbODBC odbc;
	private UI uiDesigned = new UI();
	private UI.SalesRep SalesRep 	= uiDesigned.new SalesRep();
	private UI.Customer Customer 	= uiDesigned.new Customer();
	private UI.Type Type					= uiDesigned.new Type();
	private UI.Order Order				= uiDesigned.new Order();
	private UI.OrderLine OrderLine	= uiDesigned.new OrderLine();

	private UI.DEChoice		AllSalesEmployeeID 	= SalesRep.AllSalesEmployeeID,
										AllTypeInfo		= Type.AllTypeInfo;

	private UI.DETextField 	EmployeeName 	= SalesRep.EmployeeName,

									CustomerID 		= Customer.CustomerID,
									CustomerName 	= Customer.CustomerName,
									CustomerIC		= Customer.CustomerIC,
									ContactNO			= Customer.ContactNO,

									OrderID			= Order.OrderID,
									OrderDate			= Order.OrderDate,

									GrandTotal		= OrderLine.GrandTotal;

	private UI.DETextArea Address 	= Customer.Address;

	private DETableUI.DETable OrderLineTable = OrderLine.OrderLineTable,
											OrderLineTable2 = OrderLine.OrderLineTable2;

	private DETableUI TableUI = new DETableUI();
	private DETableUI.ResultsModel	OrderResultsModel = TableUI.new ResultsModel();

	private JSeparator 	Separator 	= new JSeparator(),
						Separator1 	= new JSeparator(),
						Separator2 	= new JSeparator();

	private UI.DEButtons forSearch 	= Customer.forSearch.add( Customer.SearchOptions, this ),
						forValidate 			= Customer.forValidate.add( Customer.ValidateOptions, this ),
						forAddNew			= Customer.forAddNew.add( Customer.AddNewOptions, this ),
						forEdit					= Customer.forEdit.add( Customer.EditOptions, this ),

						forDate					= Order.forDate.add( Order.forDateOptions, this ),

						forTable				= OrderLine.forTable.add( OrderLine.forTableOptions, this ),
						forSalesOrder		= OrderLine.forSalesOrder.add( OrderLine.forSalesOrderOptions, this );

	private Component 	group[]		= {	Separator, AllSalesEmployeeID, EmployeeName },
									group1[]	= {	CustomerID, CustomerName },
									group2[]	= {	CustomerIC, ContactNO, Address, AllTypeInfo },
									group3[]	= {	Separator1, OrderID, OrderDate, forDate },
									group4[]	= { forSearch, forValidate, forAddNew, forEdit },
									group5[]	= { Separator2, OrderLineTable, OrderLineTable2, forTable, forSalesOrder, GrandTotal };

	private boolean ValueChanged = false;
	private String CustomerWhoMakeOrder;
	private boolean OrderReceiptPrinted;
	private String SecurityMode = "High";
	private FurnitureSystem TheSystem;

	public CustomerOrder( FurnitureSystem theSystem ) {
		super();
		//AllEditable = allEditable;
		SecurityMode = theSystem.getSecurityMode();
		TheSystem = theSystem;
		//=====================================================
		gridx( 0 ); fill( BOTH ); gridwidth( 4 );insets( 0, 0, 3, 0);
			gridy( 0 ); add( Header );

		insets( 3, 0, 3, 0);
			gridy( 3 ); add( Separator );
			gridy( 11 ); add( Separator1 );
			gridy( 17 ); add( Separator2 );
		insets( 3, 6, 6, 6);
			gridy( 10 ); add( group4 );
			//gridy( 12 ); add( Separator1 );
			gridy( 12 ); add( OrderLineTable.withScrollbar() );
			//gridy( 16 ); add( forTable );
			//gridy( 17 ); add( Separator2 );
			gridy( 18 ); add( forSalesOrder );

			gridy( 19 ); add( OrderLineTable2.withScrollbar() );
				OrderLineTable2.setModel( OrderResultsModel );
		insets( 3, 6, 3, 3); gridwidth( 2 );
			gridy( 9 ); add( forDate );
			gridy( 13 ); add( forTable );

		//=================================================================
		fill(  NONE ); gridwidth( 1 ); anchor( EAST ); insets( 3, 6, 3, 3);
			gridy( 4 ); add( OrderID.FLabel );
			gridy( 5 ); add( OrderDate.FLabel );

		gridx( 2 );
			gridy( 1 ); add( AllSalesEmployeeID.FLabel );
			gridy( 2 ); add( EmployeeName.FLabel );
			//gridy( 3 ); add( Separator );
			gridy( 4 ); add( CustomerID.FLabel );
			gridy( 5 ); add( CustomerName.FLabel );
			gridy( 6 ); add( CustomerIC.FLabel );
			gridy( 7 ); add( ContactNO.FLabel );
			gridy( 8 ); add( Address.FLabel );
			gridy( 9 ); add( AllTypeInfo.FLabel );
			//gridy( 10 ); add( group4 );
			//gridy( 11 ); add( Separator1 );
			//gridy( 12 ); add( OrderLineTable.withScrollbar() );
			gridy( 13 ); add( GrandTotal.FLabel );

		gridx( 1 ); anchor( WEST); insets( 3, 3, 3, 6);
			gridy( 4 ); add( OrderID );
			gridy( 5 ); add( OrderDate );

		gridx( 3 );
			gridy( 1 ); add( AllSalesEmployeeID );
			gridy( 2 ); add( EmployeeName );
			//gridy( 3 ); add( Separator );
			gridy( 4 ); add( CustomerID );
			gridy( 5 ); add( CustomerName );
			gridy( 6 ); add( CustomerIC );
			gridy( 7 ); add( ContactNO );
			gridy( 8 ); add( Address.withScrollbar() );
			gridy( 9 ); add( AllTypeInfo );
			//gridy( 10 ); add( group4 );
			//gridy( 11 ); add( Separator1 );
			//gridy( 12 ); add( OrderLineTable.withScrollbar() );
			gridy( 13 ); add( GrandTotal );

			OrderLineTable.addMouseListener( this );
			AllSalesEmployeeID.addItemListener( this );

		validate();
	}

	public CustomerOrder toSearch() {
		Header.setText( "Search Customer" );
		hide( group );
		show( group1 ); enable( group1 );
			AllTypeInfo.setVisible( false );
		hide( group2 );
		hide( group3 );
		hide( group4 );
		hide( group5 );
		forSearch.setVisible( true );

		CustomerID.addKeyListener( this );
		CustomerName.addKeyListener( this );
		CustomerID.requestFocus();
		CustomerID.selectAll();
		CustomerName.selectAll();
		return this;
	}

	public CustomerOrder toSearchOrder() {
		Header.setText( "Sales Order" );
		hide( group );
		show( group1 );	disable( group1 );
		hide( group2 );
		show( group3 ); disable( group3 );
		hide( group4 );
		show( group5 );	enable( group5 );

		OrderLineTable.setVisible( false );
		forDate.setVisible( false );
		forTable.setVisible( false );
		forSalesOrder.setVisible( false );
		GrandTotal.setVisible( false );

		doSearchOrder();

		return this;
	}

	private void doSearchOrder() {
		odbc = new dbODBC( TheSystem.getConnection() );
		String TheOrderID = JOptionPane.showInputDialog( 	TheSystem, "Order ID", "Input", JOptionPane.QUESTION_MESSAGE );
		if ( TheOrderID != null && ! TheOrderID.equals( "" ) ) {
			String SQL= "Select " +
									"Customer.CustomerID, " +
									"Customer.CustomerName, " +
									"Type.TypeInfo, " +
									"Order.OrderID, " +
									"Order.OrderDate " +
								"From " +
									"[Type], " +
									"[Order], " +
									"[Order Line] OrderLine, " +
									"Customer " +
								"Where " +
									"Type.TypeID = Customer.TypeID And " +
									"Customer.CustomerID = Order.CustomerID And " +
									"Order.OrderID = OrderLine.OrderID And " +
									"Order.OrderID = '" +  TheOrderID + "' ";

			odbc.executeQuery( SQL );
			if ( odbc.move( "Next" ) ) {
				SimpleDateFormat dateFormat 	= new SimpleDateFormat ( "dd/MM/yyyy", Locale.getDefault() );
				CustomerID.setText( odbc.getString( "CustomerID" ) );
				CustomerName.setText( odbc.getString( "CustomerName" ) );
				AllTypeInfo.select( odbc.getString( "TypeInfo" ) );
				OrderID.setText( odbc.getString( "OrderID" ) );
				OrderDate.setText( dateFormat.format( odbc.getDate( "OrderDate" ) ) );

				SQL= "Select " +
							"Product.ProductID, " +
							"Product.ProductName, " +
							"OrderLine.Quantity " +
						"From " +
							"Product, " +
							"[Order], " +
							"[Order Line] OrderLine " +
						"Where " +
							"Product.ProductID = OrderLine.ProductID And " +
							"Order.OrderID = OrderLine.OrderID And " +
							"Order.OrderID = '" +  TheOrderID + "' ";

				int RecordCount = SQLTool.RecordCount( SQL );
				odbc.executeQuery( SQL );
				OrderResultsModel.setResultSet( odbc.getResultSet(), RecordCount );
				OrderLineTable2.AdjustColumnWidths();
				OrderLineTable2.getColumn( OrderLineTable2.findColumn( "ProductName" ) ).setPreferredWidth( 260 );
				OrderLineTable2.validate();
			}
		}
		else
			setVisible( false );
	}

	public CustomerOrder toAddNew() {
		Header.setText( "Add New Customer" );
		SecurityMode = TheSystem.getSecurityMode();
		show( group );
			if ( SecurityMode.equals( "Low" ) ) {
				enable( group );
				EmployeeName.setEnabled( false );
			}
			else disable( group );
		show( group1 ); enable( group1 );
			CustomerID.setEnabled( false );
		show( group2 ); enable( group2 );
		hide( group3 );
		hide( group4 );
		hide( group5 );
		forAddNew.setVisible( true );
		//========================================
			doAddNew();
		//=========================================
		CustomerName.requestFocus();
		return this;
	}

	private void doAddNew() {
		odbc = new dbODBC( TheSystem.getConnection() );
		AllSalesEmployeeID.select( TheSystem.getUserID() );
		EmployeeName.setText( TheSystem.getUserName() );
		//=========================AutoAssign==============================
		odbc.executeQuery( 	"Select " +
											"Customer.CustomerID " +
										"From " +
											"Customer " +
										"Order By " +
											"Customer.CustomerID"
									);
		if ( odbc.move( "Next" ) ) {
			if ( odbc.move( "Last" ) ) {
				String theCustomerID = odbc.getString( "CustomerID" );
				char LeftString = 'C';
				String RightString = theCustomerID.substring( 1, theCustomerID.length() );
				int newValue = Integer.parseInt( RightString ) + 1 + 100000;
				String toStringValue = Integer.toString( newValue );
				CustomerID.setText( LeftString + toStringValue.substring( 1, toStringValue.length() ) );
			}
		}
		else
			CustomerID.setText( 'C' + "00001" );
		//================================================================
		CustomerName.setText( "" );
		CustomerIC.setText( "" );
		ContactNO.setText( "" );
		Address.setText( "" );
	}

	private void toValidate() {
		Header.setText( "Customer Detail" );

		show( group );	disable( group );
		show( group1 ); disable( group1 );
		show( group2 );	disable( group2 );
		hide( group3 );
		hide( group4 );
		hide( group5 );
		forValidate.setVisible( true );
	}

	private boolean doValidate() {
		odbc = new dbODBC( TheSystem.getConnection() );
		String theCustomerID = CustomerID.getText().trim();
		String theCustomerName = CustomerName.getText().trim();

		if ( OrderLineTable.getRowCount() > 0 && ! CustomerWhoMakeOrder.equals( theCustomerID ) ) {
			int RowCount = OrderLineTable.getRowCount();
			for( int a = 0; a < RowCount; a++ )
				toRemoveProduct();
		}

		if ( theCustomerID.equals( "" ) && theCustomerName.equals( "" ) ) {
			JOptionPane.showMessageDialog	( 	this, "Please Input some data", "Record Not Found",  JOptionPane.WARNING_MESSAGE );
		}
		else {
			if ( ! theCustomerID.equals( "" ) ) {
					odbc.executeQuery( 	"Select " +
														"Customer.CustomerID, " +
														"Customer.CustomerName, " +
														"Customer.CustomerIC, " +
														"Customer.ContactNo, " +
														"Customer.Address, " +
														"Type.TypeInfo, " +
														"Employee.StaffID, " +
														"Employee.StaffName " +
													"From " +
														"Customer, " +
														"Type, " +
														"Employee " +
													"Where " +
														"Customer.TypeID = Type.TypeID " +
													"And " +
														"Customer.StaffID = Employee.StaffID " +
													"And " +
														"Customer.CustomerID = '" + theCustomerID + "'"
												);

			}
			else if ( ! theCustomerName.equals( "" ) ) {
				odbc.executeQuery( 	"Select " +
													"Customer.CustomerID, " +
													"Customer.CustomerName, " +
													"Customer.CustomerIC, " +
													"Customer.ContactNo, " +
													"Customer.Address, " +
													"Type.TypeInfo, " +
													"Employee.StaffID, " +
													"Employee.StaffName " +
												"From " +
													"Customer, " +
													"Type, " +
													"Employee " +
												"Where " +
													"Customer.TypeID = Type.TypeID " +
												"And " +
													"Customer.StaffID = Employee.StaffID " +
												"And " +
													"Customer.CustomerName = '" + theCustomerName + "'"
											);
			}

			if ( odbc.move( "Next" ) ) {
				AllSalesEmployeeID.select( odbc.getString( "StaffID" ) );
				EmployeeName.setText( odbc.getString( "StaffName" ) );

				CustomerID.setText( odbc.getString( "CustomerID" ) );
				CustomerName.setText( odbc.getString( "CustomerName" ) );
				CustomerIC.setText( odbc.getString( "CustomerIC" ) );
				ContactNO.setText( odbc.getString( "ContactNO" ) );
				Address.setText( odbc.getString( "Address" ) );

				AllTypeInfo.select( odbc.getString( "TypeInfo" ) );

				CustomerID.removeKeyListener( this );
				CustomerName.removeKeyListener( this );
				return true;
			}
			else {
				JOptionPane.showMessageDialog	( 	this, "Bad Customer ID or Customer Name", "Record Not Found",  JOptionPane.INFORMATION_MESSAGE );
			}
		}
		return false;
	}

	private void toEdit() {
		Header.setText( "Edit Customer Detail" );
		SecurityMode = TheSystem.getSecurityMode();
		show( group );
		show( group1 );
		if ( SecurityMode.equals( "Low" ) ) {
			enable( group );
			EmployeeName.setEnabled( false );
			enable( group1 );
			CustomerID.setEnabled( false );
		}
		else {
			disable( group );
			disable( group1 );
			CustomerName.setEnabled( true );
		}
		show( group2 ); enable( group2 );
		hide( group3 );
		hide( group4 );
		hide( group5 );
		forEdit.setVisible( true );
		CustomerName.requestFocus();
	}

	private void toRefresh() {
		if ( forEdit.isVisible() ) {
			doValidate();
		}
		else if ( forAddNew.isVisible() ) {
			doAddNew();
			toAddNew();
		}
	}

	private void toCancel() {
		if ( forEdit.isVisible() ) {
			doValidate();
			toValidate();
		}
		else if ( forAddNew.isVisible() )
			toSearch();
		else if ( forSearch.isVisible() )
			setVisible( false );
		else if ( forSalesOrder.isVisible() )
			toValidate();
	}

	private void toBack() {
		if ( forValidate.isVisible() )
			toSearch();
	}

	private void toSaveRecord() {
		odbc = new dbODBC( TheSystem.getConnection() );
		boolean RecordSaved = false;
		String TheEmployeeID = (String) AllSalesEmployeeID.getSelectedItem();

		String TheCustomerID = CustomerID.getText();
		String TheCustomerName = CustomerName.getText();
		String TheCustomerIC = CustomerIC.getText();
		String TheContactNO = ContactNO.getText();
		String TheAddress = Address.getText();

		String TheTypeInfo = (String) AllTypeInfo.getSelectedItem();
		String TheTypeID;

		if ( TheCustomerName.equals( "" ) || TheCustomerIC.equals( "" ) || TheContactNO.equals( "" ) || TheAddress.equals( "" ) )
			JOptionPane.showMessageDialog	( 	this, "Imcomplete Form", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
		else if ( TheCustomerName.length() > 20 )
			JOptionPane.showMessageDialog	( 	this, "The length of Customer Name should not greater than 20 characters", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
		else if ( TheCustomerIC.length() > 12 )
			JOptionPane.showMessageDialog	( 	this, "The length of Customer IC should not greater than 12 characters", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
		else if ( TheContactNO.length() > 10 )
			JOptionPane.showMessageDialog	( 	this, "The length of Contact NO should not greater than 10 characters", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
		else {
			odbc.executeQuery( 	"Select " +
												"Type.TypeID " +
											"From " +
												"[Type] " +
											"Where " +
												"Type.TypeInfo = '" + TheTypeInfo + "' "
										);
			if ( odbc.move( "Next" ) ) {
				TheTypeID = odbc.getString( "TypeID" );
				odbc.executeUpdate( "Insert Into " +
													"Customer " +
												"Values ( '" +
													TheCustomerID + "', '" +
													TheCustomerName + "', '" +
													TheCustomerIC + "', '" +
													TheContactNO + "', '" +
													TheAddress + "', '" +
													TheTypeID + "', '" +
													TheEmployeeID + "' ) "
											);
				//======================Check isSaved Customer Table=========================
				if ( SQLTool.CheckRecordExists( odbc, "Select " +
																	"Customer.CustomerID " +
																"From " +
																	"Customer " +
																"Where " +
																	"Customer.CustomerID = '" + TheCustomerID + "' " ) ) {
					JOptionPane.showMessageDialog	( 	this, "Successful", "Record SAVED", JOptionPane.INFORMATION_MESSAGE );
					toSearch();
					RecordSaved = true;
				}
				//==============================================================================
			}

			if ( ! RecordSaved )
				JOptionPane.showMessageDialog	( 	this, "Database Error", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
		}
	}

	private void toUpdateRecord() {
		odbc = new dbODBC( TheSystem.getConnection() );
		boolean RecordUpdated = false;
		String TheEmployeeID = (String) AllSalesEmployeeID.getSelectedItem();

		String TheCustomerID = CustomerID.getText();
		String TheCustomerName = CustomerName.getText();
		String TheCustomerIC = CustomerIC.getText();
		String TheContactNO = ContactNO.getText();
		String TheAddress = Address.getText();

		String TheTypeInfo = (String) AllTypeInfo.getSelectedItem();
		String TheTypeID;

		if ( TheCustomerName.equals( "" ) || TheCustomerIC.equals( "" ) || TheContactNO.equals( "" ) || TheAddress.equals( "" ) )
			JOptionPane.showMessageDialog	( 	this, "Imcomplete Form", "Record NOT UPDATE", JOptionPane.INFORMATION_MESSAGE );
		else if ( TheCustomerName.length() > 20 )
			JOptionPane.showMessageDialog	( 	this, "The length of Customer Name should not greater than 20 characters", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
		else if ( TheCustomerIC.length() > 12 )
			JOptionPane.showMessageDialog	( 	this, "The length of Customer IC should not greater than 12 characters", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
		else if ( TheContactNO.length() > 10 )
			JOptionPane.showMessageDialog	( 	this, "The length of Contact NO should not greater than 10 characters", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
		else {
			odbc.executeQuery( 	"Select " +
												"Type.TypeID " +
											"From " +
												"[Type] " +
											"Where " +
												"Type.TypeInfo = '" + TheTypeInfo + "' "
										);
			if ( odbc.move( "Next" ) ) {
				TheTypeID = odbc.getString( "TypeID" );
				odbc.executeUpdate( "Update " +
													"Customer " +
												"Set " +
													"Customer.CustomerName = '" + TheCustomerName + "', " +
													"Customer.CustomerIC = '" + TheCustomerIC + "', " +
													"Customer.ContactNo = '" + TheContactNO + "', " +
													"Customer.Address = '" + TheAddress + "', " +
													"Customer.TypeID = '" + TheTypeID + "', " +
													"Customer.StaffID = '" + TheEmployeeID + "' " +
												"Where " +
													"Customer.CustomerID = '" + TheCustomerID + "' "
											);
				//======================Check isUpdated Customer Table=========================
				String SQL = "Select " +
										"Customer.CustomerName, " +
										"Customer.CustomerIC, " +
										"Customer.ContactNo, " +
										"Customer.Address, " +
										"Customer.TypeID, " +
										"Customer.StaffID " +
									"From " +
										"Customer " +
									"Where " +
										"Customer.CustomerID = '" + TheCustomerID + "' ";

				if ( SQLTool.CheckRecordExists( odbc, SQL ) ) {
					JOptionPane.showMessageDialog	( 	this, "Successful", "Record UPDATED", JOptionPane.INFORMATION_MESSAGE );
					toValidate();
					RecordUpdated = true;
				}
				//======================================================================
			}
			if ( ! RecordUpdated )
				JOptionPane.showMessageDialog	( 	this, "The System may not update the Record", "Record NOT UPDATE",  JOptionPane.INFORMATION_MESSAGE );
		}
	}

	private void toMakeOrder() {
		Header.setText( "Sales Order Form" );
		hide( group );
		show( group1 );	disable( group1 );
		hide( group2 );
			AllTypeInfo.setVisible( true );
			AllTypeInfo.setEnabled( false );
		show( group3 ); disable( group3 );
		hide( group4 );
		show( group5 );	enable( group5 );
			GrandTotal.setEnabled( false );
			OrderLineTable2.setVisible( false );
	}

	private void doMakeOrder() {
		odbc = new dbODBC( TheSystem.getConnection() );
		//=========================AutoAssign==============================
			odbc.executeQuery( 	"Select " +
												"Order.OrderID " +
											"From " +
												"[Order] " +
											"Order By " +
												"Order.OrderID"
										);
			if ( odbc.move( "Next" ) ) {
				if ( odbc.move( "Last" ) ) {
					String theCustomerID = odbc.getString( "OrderID" );
					String LeftString = "PO";
					String RightString = theCustomerID.substring( 2, theCustomerID.length() );
					int newValue = Integer.parseInt( RightString ) + 1 + 10000;
					String toStringValue = Integer.toString( newValue );
					OrderID.setText( LeftString + toStringValue.substring( 1, toStringValue.length() ) );
				}
			}
			else
				OrderID.setText( "PO" + "1000" );
		//================================================================
	}

	private void toAddProduct() {
		OrderLineTable.addRow();
		OrderLineTable.clearSelection();
		if ( OrderLineTable.getRowCount() >= 0 )
			OrderLineTable.setRowSelectionInterval(  OrderLineTable.getRowCount() - 1, OrderLineTable.getRowCount() - 1 );
		if ( ! inputProductID() ) {
			OrderLineTable.removeRow( OrderLineTable.getSelectedRow() );
			if ( OrderLineTable.getRowCount() > 0 && OrderLineTable.getSelectedRowCount() <= 1 )
				OrderLineTable.setRowSelectionInterval(  OrderLineTable.getRowCount() - 1, OrderLineTable.getRowCount() - 1 );
		}
		else {
			if ( OrderLineTable.getRowCount() > 1 ) {
				if ( OrderReceiptPrinted ) {
					OrderReceiptPrinted = false;
					JOptionPane.showMessageDialog	( 	this, 	"The Order Line Table has been modified after the receipt has been printed.",
																							"Data has changed",  JOptionPane.INFORMATION_MESSAGE );
				}
			}
		}

		if ( OrderLineTable.getRowCount() == 1 ) {
			CustomerWhoMakeOrder = CustomerID.getText();
			OrderReceiptPrinted = false;
		}

	}

	private void toRemoveProduct() {
		if ( OrderLineTable.getSelectedRow() >= 0 ) {
			OrderLineTable.removeRow( OrderLineTable.getSelectedRow() );
			CalcGrandTotal();
			if ( OrderLineTable.getRowCount() > 0 && OrderLineTable.getSelectedRowCount() <= 1 )
				OrderLineTable.setRowSelectionInterval(  OrderLineTable.getRowCount() - 1, OrderLineTable.getRowCount() - 1 );
		}

		if ( OrderReceiptPrinted && ! CustomerWhoMakeOrder.equals( "" ) ) {
			OrderReceiptPrinted = false;
			JOptionPane.showMessageDialog	( 	this, 	"The Order Line Table has been modified after the receipt has been printed.",
																					"Data has changed",  JOptionPane.INFORMATION_MESSAGE );
		}

		if ( OrderLineTable.getRowCount() == 0 ) {
			CustomerWhoMakeOrder = "";
		}
	}

	private void toTodayDate() {
		OrderDate.setText( DETool.TodayDate );
	}

	private void toChange() {
		SimpleDateFormat dateFormat 	= new SimpleDateFormat ( "dd/MM/yyyy", Locale.getDefault() );

		String Day 	= JOptionPane.showInputDialog	( 	this, "Day (2 digit)", "Input Day",  JOptionPane.QUESTION_MESSAGE );
		String Month;
		String Year;
		if ( Day != null && !Day.equals( "" ) ) {
			Month 	= JOptionPane.showInputDialog	( 	this, "Month (2 digit)", "Input Month",  JOptionPane.QUESTION_MESSAGE );
			if ( Month != null && !Month.equals( "" ) ) {
				Year 	= JOptionPane.showInputDialog	( 	this, "Year (4 digit)", "Input Year",  JOptionPane.QUESTION_MESSAGE );
				if ( Year != null && !Year.equals( "" ) && Year.length() <= 4 ) {
					try {
						OrderDate.setText( dateFormat.format( dateFormat.parse(  Day + "/" + Month + "/" + Year ) ) );
					}
					catch ( ParseException exception ) {}
				}
			}
		}
	}

	private void toSaveOrder() {
		odbc = new dbODBC( TheSystem.getConnection() );
		boolean RecordSaved = false;
		if ( OrderLineTable.getRowCount() > 0 ) {
			String TheOrderID 	= OrderID.getText();
			String TheOrderDate 	= OrderDate.getText();
			String TheCustomerID = CustomerID.getText();

			Object AllValueOfProductID[]	= OrderLineTable.getAllValueAtColumn( "Product ID" );
			Object AllValueOfQuantity[]	= OrderLineTable.getAllValueAtColumn( "Quantity" );
			Object AllValueOfUnitPrice[]	= OrderLineTable.getAllValueAtColumn( "Unit Price" );

			odbc.executeUpdate( "Insert Into " +
												"[Order] " +
											"Values ( '" +
												TheOrderID + "', '" +
												TheOrderDate + "', '" +
												TheCustomerID + "' ) "
										);
			//================check isNewOrder created=======================================
			String SQL = "Select " +
									"Order.OrderID " +
								"From " +
									"[Order] " +
								"Where " +
									"Order.OrderID = '" + TheOrderID + "' ";

			if ( SQLTool.CheckRecordExists( odbc, SQL ) ) {
				//==============================Update Product Quantity===============================
				int RowCount = AllValueOfProductID.length;
				int QuantityInStock;
				int RemainStockQuantity;
				String Text = "\t" +  " Order Line Item " + "\t" + "\n";
				Text = Text + "\n";
				Text = Text + TheOrderID + "\n";
				Text = Text + "\n";
				for ( int a = 0; a < RowCount; a++ ) {

					odbc.executeUpdate( "Insert Into " +
														"[Order Line] " +
													"Values ( '" +
														(String) TheOrderID + "', '" +
														(String) AllValueOfProductID[ a ] + "', '" +
														(String) AllValueOfQuantity[ a ] + "', '" +
														Double.toString( DETool.CurrencyToDouble( (String) AllValueOfUnitPrice[ a ] ) ) + "' ) "
												);

					odbc.executeQuery( 	"Select " +
														"Product.Quantity " +
													"From " +
														"Product " +
													"Where " +
														"Product.ProductID = '" + (String) AllValueOfProductID[ a ] + "' "
												);

					if ( odbc.move( "Next" ) ) {
						QuantityInStock = Integer.parseInt( odbc.getString( "Quantity" ) );

						RemainStockQuantity = QuantityInStock - Integer.parseInt( (String) AllValueOfQuantity[ a ] );

						odbc.executeUpdate( 	"Update " +
															"Product " +
														"Set " +
															"Product.Quantity = '" + Integer.toString( RemainStockQuantity ) + "' " +
														"Where " +
															"Product.ProductID = '" + (String) AllValueOfProductID[ a ] + "' "
													);
						//=========================check update===================================
						odbc.executeQuery( 	"Select " +
															"Product.Quantity, " +
															"Product.ProductName " +
														"From " +
															"Product " +
														"Where " +
															"Product.ProductID = '" + (String) AllValueOfProductID[ a ] + "' "
												);
						if ( odbc.move( "Next" ) ) {
							if ( QuantityInStock != Integer.parseInt( odbc.getString( "Quantity" ) ) ) {
								JOptionPane.showMessageDialog	( 	this, 	odbc.getString( "ProductName" ) + ": \n" +
																							"Last Quantity = " + QuantityInStock + "\n" +
																							"After Deduction = " + RemainStockQuantity,
																							"Successful Updated",  JOptionPane.INFORMATION_MESSAGE );
								Text = Text + AllValueOfProductID[ a ] + " \t" + AllValueOfQuantity[ a ] + "\n";
							}
							else
								JOptionPane.showMessageDialog	( 	this, 	odbc.getString( "ProductName" ) + ": \n" +
																							"The Product Quantity in the Stock still remain the Original Value",
																							"Unsuccessful Update",  JOptionPane.ERROR_MESSAGE );
						}
						//=======================================================================
						if ( a == RowCount - 1 ) {
							RecordSaved = true;
							DEPrint.printComponent( uiDesigned.new DETextArea( 20, 40 ) );
						}
					}
					else
						JOptionPane.showMessageDialog	( 	this, "System can not deduct Quantity in Stock due to wrong SQL statement.",
																			"Product Quantity Deduction",  JOptionPane.ERROR_MESSAGE );
				}

				if ( RecordSaved ) {
					JOptionPane.showMessageDialog	( 	this, "Successful", "Record SAVED",  JOptionPane.INFORMATION_MESSAGE );
					CustomerWhoMakeOrder = "";
					toSearch();
				}
				else
					JOptionPane.showMessageDialog	( 	this, "The System may not update the Record", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
			}
			else
				JOptionPane.showMessageDialog	( 	this, "System may not Create a new Order",
																			"Unsuccessful Creating a new Order",  JOptionPane.ERROR_MESSAGE );
		}
	}

	private void toPrintReceipt() {
		final ExtFrame PrintFrame 				= new ExtFrame();
		PrintFrame.setResizable( false );
		PrintFrame.setTitle( "Print Preview" );
		PrintFrame.getContentPane().setLayout( new java.awt.FlowLayout() );
		DEObjects Objects = new DEObjects();
		final DEObjects.DETextArea PrintPreview = Objects.new DETextArea( 20, 41 );
		String Text = "\n" +
							"\n" +
							"\n" +
							"\n" +
							"Order ID : " + OrderID.getText() + " \t" + "\t" + "\t" + "\t" + "Order Date : " + OrderDate.getText() + "\n" +
							"Customer ID: " + CustomerID.getText() + " \t" + "\t" + "\t" + "\t" + "Customer Name : " + CustomerName.getText() + "\n" +
							"\n" +
							"Product ID" + " \t" + "Product Name" + " \t" + "\t"+ "\t" + "Quantity" + " \t " + "Unit Price" + " \t " + "Total" + "\n";

		Object AllValueOfProductID[]		= OrderLineTable.getAllValueAtColumn( "Product ID" );
		Object AllValueOfProductName[]	= OrderLineTable.getAllValueAtColumn( "Product Name" );
		Object AllValueOfQuantity[]		= OrderLineTable.getAllValueAtColumn( "Quantity" );
		Object AllValueOfUnitPrice[]		= OrderLineTable.getAllValueAtColumn( "Unit Price" );
		Object AllValueOfTotal[]				= OrderLineTable.getAllValueAtColumn( "Total" );

		int RowCount = AllValueOfProductID.length;
		for ( int a = 0; a < RowCount; a++ ) {
			Text = Text + 	(String) AllValueOfProductID[ a ] + "  \t" + (String) AllValueOfProductName[ a ] + "\t" + "\t";
			if ( ((String) AllValueOfProductName[ a ]).length() < 18 )
				Text = Text + "\t";
			if ( ((String) AllValueOfProductName[ a ]).length() < 15 )
				Text = Text + "\t";
			if ( ((String) AllValueOfProductName[ a ]).length() < 12 )
				Text = Text + "\t";
			if ( ((String) AllValueOfProductName[ a ]).length() < 9 )
				Text = Text + "\t";
			Text = Text + (String) AllValueOfQuantity[ a ] + "\t" + (String) AllValueOfUnitPrice[ a ] + "\t" +
								(String) AllValueOfTotal[ a ] +  "\n";
		}
		for ( int a = 0; a < 10 - RowCount; a++ ) {
			Text = Text + "\n";
		}
		Text = Text + "\t" + "\t" + "\t" + "\t " + "\t" + "\t" + "\t"+ "\t" + "Grand Total : " + "\t" +  GrandTotal.getText();

		Text = Text + 	"\n" +
								"\n" +
							 	"\n" +
								"\n" +
								"Sales Representative : " + TheSystem.getUserName() + "\t" + "\t " + "Customer : " + CustomerName.getText();
		PrintPreview.setText( Text );
		//PrintPreview.setEditable( false );
		DEObjects.DEButton 	OKButton = Objects.new DEButton( "&OK", null ),
										CancelButton = Objects.new DEButton( "&Cancel", null );
		OKButton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent event ) {
				DEPrint.printComponent( PrintPreview );
				OrderReceiptPrinted = true;
				PrintFrame.dispose();
			}
		} );
		CancelButton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent event ) {
				OrderReceiptPrinted = false;
				PrintFrame.dispose();
			}
		} );

		PrintFrame.getContentPane().add( PrintPreview.withScrollbar() );
		PrintPreview.withScrollbar().setPreferredSize( PrintPreview.getPreferredSize().getSize() );
		PrintFrame.getContentPane().add( OKButton );
		PrintFrame.getContentPane().add( CancelButton );
		PrintFrame.setVisible( true );
		PrintFrame.pack();
		PrintFrame.validate();
	}

	//===========================actionListener============================
	public void actionPerformed( ActionEvent event ) {
		System.out.println( event.getActionCommand() );
		String command = event.getActionCommand();
		if ( command.equals( "Add New" ) )
			toAddNew();
		else if ( command.equals( "Validate" ) ) {
			if ( doValidate() )
				toValidate();
		}
		else if ( command.equals( "Edit Record" ) )
			toEdit();
		else if ( command.equals( "Cancel" ) )
			toCancel();
		else if ( command.equals( "Back" ) )
			toBack();
		else if ( command.equals( "Save Record" ) )
			toSaveRecord();
		else if ( command.equals( "Update Record" ) )
			toUpdateRecord();
		else if ( command.equals( "Make Order" ) ) {
			doMakeOrder();
			toMakeOrder();
		}
		else if ( command.equals( "Add" ) )
			toAddProduct();
		else if ( command.equals( "Remove" ) )
			toRemoveProduct();
		else if ( command.equals( "Today's Date" ) )
			toTodayDate();
		else if ( command.equals( "Change" ) )
			toChange();
		else if ( command.equals( "Refresh" ) )
			toRefresh();
		else if ( command.equals( "Save Order" ) ) {
			if ( OrderReceiptPrinted )
				toSaveOrder();
			else {
				if ( OrderLineTable.getSelectedRowCount() > 0 ) {
					JOptionPane.showMessageDialog	( 	this, "Receipt should be printed before saving the Order",
																			"Record NOT SAVE",  JOptionPane.INFORMATION_MESSAGE );
				}
				else {
					JOptionPane.showMessageDialog	( 	this, "Table Empty",
																			"Record NOT SAVE",  JOptionPane.INFORMATION_MESSAGE );
				}
			}
		}
		else if ( command.equals( "Print Receipt" ) ) {
			if ( OrderLineTable.getSelectedRowCount() > 0 )
				toPrintReceipt();
			else
				JOptionPane.showMessageDialog	( 	this, "Table Empty",
																			"Receipt NOT PRINT",  JOptionPane.INFORMATION_MESSAGE );
		}
	}
	//========================KeyListener============================
	public void keyPressed( KeyEvent event ) {
		if ( event.getKeyCode() == event.VK_ENTER ) {
			if ( ValueChanged ) {
				if ( doValidate() )
					toValidate();
				ValueChanged = false;
			}
		}
		else
			ValueChanged = true;
	}
	public void keyReleased( KeyEvent event ) {}
	public void keyTyped( KeyEvent event ) {}
	//========================mouseListener============================
	public void mouseClicked( MouseEvent event ) {
		DETableUI.DETable Table = ( DETableUI.DETable ) event.getSource();
		int SelectedColumn = Table.getSelectedColumn();
		if ( SelectedColumn == Table.findColumn( "Product ID" ) )
			inputProductID();
		else if ( SelectedColumn == Table.findColumn( "Quantity" ) )
			inputQuantity();
	}
	public void mouseEntered( MouseEvent event ){}
	public void mouseExited( MouseEvent event ){}
	public void mousePressed( MouseEvent event ){}
	public void mouseReleased( MouseEvent event ){}
	//=======================itemListener=================================
	public void itemStateChanged( ItemEvent event ) {
		if ( AllSalesEmployeeID.isVisible() || !Header.getText().equals( "Search Customer" ) ) {
			odbc = new dbODBC( TheSystem.getConnection() );
			String SelectedEmployeeID = (String) AllSalesEmployeeID.getSelectedItem();
			odbc.executeQuery( 	"Select " +
												"Employee.StaffID, " +
												"Employee.StaffName " +
											"From " +
													"Employee " +
											"Where " +
													"Employee.StaffID = '" + SelectedEmployeeID + "' "
										);
			if ( odbc.move( "Next" ) ) {
				EmployeeName.setText( odbc.getString( "StaffName" ) );
			}
		}
	}

	//=============================Popup=================================
	private boolean inputProductID() {
		if ( OrderLineTable.getSelectedRowCount() == 1 ) {
			int SelectedRow = OrderLineTable.getSelectedRow();
			String ColumnName = "Product ID";
			int SelectColumn = OrderLineTable.findColumn( ColumnName );
			if ( SelectColumn >= 0 ) {
				String Data = JOptionPane.showInputDialog( this, ColumnName, "Input", JOptionPane.QUESTION_MESSAGE );
				if ( Data != null ) {
					if ( !Data.equals( "" ) )
						return ValidateProductID( Data, SelectedRow );
					else if ( Data.equals( "" ) )
						if ( OrderLineTable.getValueAt( SelectedRow, OrderLineTable.findColumn( ColumnName ) ) == null )
							return false;
					if ( OrderLineTable.getValueAt( SelectedRow, OrderLineTable.findColumn( "Quantity" ) ) == null )
						OrderLineTable.setValueAt( "1", SelectedRow, OrderLineTable.findColumn( "Quantity" ) );
					return true;
				}
			}
			else
				System.out.println( "CustomerOrder.inputProductID()......ColumnName not found" );
		}
		else
			System.out.println( "No row is selected" );
		return false;
	}

	private boolean inputQuantity() {
		if ( OrderLineTable.getSelectedRowCount() == 1 ) {
			String ColumnName = "Quantity";
			int SelectedRow = OrderLineTable.getSelectedRow();
			int SelectColumn = OrderLineTable.findColumn( ColumnName );
			if ( SelectColumn >= 0 ) {
				String Message = 	"1. To INCREASE Quantity ( e.g. +50 ). \n" +
											"2. To DEDUCT Quantity ( e.g. -100 ). \n" +
											"3. To REPLACE Quantity ( e.g. 70 ).\n" +
											"Current Quantity value in table is " + OrderLineTable.getValueAt( SelectedRow, SelectColumn );
				String Title = "Input " + ColumnName;
				String Data = JOptionPane.showInputDialog	( 	this, Message, Title, JOptionPane.QUESTION_MESSAGE );
				if ( Data != null && ! Data.equals( "" ) ) {
					int intValue = Integer.parseInt( (String) OrderLineTable.getValueAt( SelectedRow, SelectColumn ) );
					Data = Integer.toString( DETool.parseQuantity( intValue, Data ) );
					return ValidateQuantity( Data, SelectedRow );
				}
			}
			else
				System.out.println( "CustomerOrder.inputQuantity()......ColumnName not found" );
		}
		else
			System.out.println( "No row is selected" );
		return false;
	}

	//============================check redundacy ProductID=======================
	private boolean isRedundantProductID( String scanFor ) {
		int RowCount = OrderLineTable.getRowCount();
		if ( RowCount > 0 ) {
			int SelectColumn = OrderLineTable.findColumn( "Product ID" );
			String AllProductID[] = new String[ RowCount ];
			for ( int a = 0; a < RowCount; a++ ) {
				AllProductID[ a ] = (String) OrderLineTable.getValueAt( a, SelectColumn );
			}
			for( int b = 0; b < AllProductID.length; b++ ) {
				if ( AllProductID[ b ] != null ) {
					if ( AllProductID[ b ].equals( scanFor ) ) {
						JOptionPane.showMessageDialog	( 	this, "Product ID cannot be redundant in the table.", "Invalid Product ID",  JOptionPane.INFORMATION_MESSAGE );
						return true;
					}
				}
			}
		}
		return false;
	}
	//========================Validate ProductID input======================
	private boolean ValidateProductID( String theProductID, int SelectedRow ) {
		if ( 	OrderLineTable.findColumn( "Product ID" ) > -1 && OrderLineTable.findColumn( "Product Name" ) > -1 &&
				OrderLineTable.findColumn( "Quantity" ) > -1 && OrderLineTable.findColumn( "Unit Price" ) > -1 &&
				OrderLineTable.findColumn( "Total" ) > -1
			)
		{
			if ( ! isRedundantProductID( theProductID ) ) {
				odbc = new dbODBC( TheSystem.getConnection() );
				odbc.executeQuery( 	"Select " +
													"Product.ProductID, " +
													"Product.ProductName, " +
													"Product.UnitPrice " +
												"From " +
													"Product " +
												"Where " +
													"Product.ProductID = '" + theProductID + "'"
											);
				if ( odbc.move( "Next" ) ) {
						int SelectColumn = OrderLineTable.findColumn( "Product ID" );
						OrderLineTable.setValueAt( odbc.getString( "ProductID" ), SelectedRow, SelectColumn );

						SelectColumn = OrderLineTable.findColumn( "Product Name" );
						OrderLineTable.setValueAt( odbc.getString( "ProductName" ), SelectedRow, SelectColumn );

						SelectColumn = OrderLineTable.findColumn( "Quantity" );
						OrderLineTable.setValueAt( "1", SelectedRow, SelectColumn );

						SelectColumn = OrderLineTable.findColumn( "Unit Price" );
						//String TheUnitPrice = odbc.getString( "UnitPrice" );
						//String CurrencyValue = "RM " + TheUnitPrice.substring( 0, TheUnitPrice.length() - 2 );
						OrderLineTable.setValueAt( DETool.toCurrency( odbc.getString( "UnitPrice" ) ), SelectedRow, SelectColumn );
						if ( OrderReceiptPrinted ) {
							OrderReceiptPrinted = false;
							JOptionPane.showMessageDialog	( 	this, 	"The Order Line Table has been modified after the receipt has been printed.",
																						"Data has changed",  JOptionPane.INFORMATION_MESSAGE );
						}
						return ValidateQuantity( "1", SelectedRow);
				}
				else
					JOptionPane.showMessageDialog	( 	this, "Product is Not Found.", "Invalid Product ID",  JOptionPane.INFORMATION_MESSAGE );
			}
		}
		else
				JOptionPane.showMessageDialog	( 	this, "Table Error", "Invalid Product ID",  JOptionPane.INFORMATION_MESSAGE );
		return false;
	}

	//==========================Validate Quantity input===========================
	private boolean ValidateQuantity( String theQuantity, int SelectedRow ) {
		if ( Integer.parseInt( theQuantity ) >= 0 ) {
			odbc = new dbODBC( TheSystem.getConnection() );
			String theProductID = (String) OrderLineTable.getValueAt( SelectedRow, OrderLineTable.findColumn( "Product ID" ) );
			odbc.executeQuery( 	"Select " +
												"Product.ProductID, " +
												"Product.Quantity, " +
												"Product.UnitPrice " +
											"From " +
												"Product " +
											"Where " +
												"Product.ProductID = '" + theProductID + "'"
										);
			if ( odbc.move( "Next" ) ) {
				String QuantityInStore = odbc.getString( "Quantity" );
				if ( Integer.parseInt( QuantityInStore ) >= Integer.parseInt( theQuantity ) ) {
					int SelectColumn = OrderLineTable.findColumn( "Quantity" );
					String OriginalQuantityValue = (String) OrderLineTable.getValueAt( SelectedRow, SelectColumn );
					OrderLineTable.setValueAt( theQuantity, SelectedRow, SelectColumn );

					if ( ! OriginalQuantityValue.equals( (String) OrderLineTable.getValueAt( SelectedRow, SelectColumn ) ) ) {
						if ( OrderReceiptPrinted ) {
							OrderReceiptPrinted = false;
							JOptionPane.showMessageDialog	( 	this, 	"The Order Line Table has been modified after the receipt has been printed.",
																						"Data has changed",  JOptionPane.INFORMATION_MESSAGE );
						}
					}

					SelectColumn = OrderLineTable.findColumn( "Total" );
					double UnitPrice = Double.parseDouble( odbc.getString( "UnitPrice" ) );
					double Total = Integer.parseInt( theQuantity ) * UnitPrice;
					OrderLineTable.setValueAt( DETool.toCurrency( Total ), SelectedRow, SelectColumn );
					CalcGrandTotal();

					return true;
				}
				else {
					String Message = "The Quantity of Product(" + odbc.getString( "ProductID" ) + ") in Stock are only " + QuantityInStore + ".";
					JOptionPane.showMessageDialog	( 	this, Message, "Check Available", JOptionPane.INFORMATION_MESSAGE );
				}
			}
			else
				JOptionPane.showMessageDialog	( 	this, "Product is Not Found.", "Invalid Product ID",  JOptionPane.INFORMATION_MESSAGE );
		}
		else
			JOptionPane.showMessageDialog	( 	this, "Invalid Quantity value = " + theQuantity + ".", "Wrong Input",  JOptionPane.WARNING_MESSAGE );
		return false;
	}
	//===============================Calc GrandTotal===============================
	private void CalcGrandTotal() {
		int RowCount = OrderLineTable.getRowCount();
		double Result = 0.00;
		if ( RowCount > 0 ) {
			int SelectColumn = OrderLineTable.findColumn( "Total" );
			String Value;
			double AllTotal[] = new double[ RowCount ];
			for ( int a = 0; a < RowCount; a++ ) {
				Value = (String) OrderLineTable.getValueAt( a, SelectColumn );
				AllTotal[ a ] = Double.parseDouble( Value.substring( 3, Value.length() ) );
			}
			for( int b = 0; b < AllTotal.length; b++ )
				Result = Result + AllTotal[ b ];

			GrandTotal.setText( DETool.toCurrency( Result ) );
		}
		else
			GrandTotal.setText( DETool.toCurrency( Result ) );
	}
}