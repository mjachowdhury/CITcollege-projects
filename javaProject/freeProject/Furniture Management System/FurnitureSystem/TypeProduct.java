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

public class TypeProduct extends DEForm implements ActionListener, ItemListener, MouseListener, KeyListener {
	private dbODBC odbc;
	private UI uiDesigned 		= new UI();
	private DETableUI TableUI = new DETableUI();
	private UI.Type Type			= uiDesigned.new Type();
	private UI.Product Product 	= uiDesigned.new Product();
	private DETableUI.ResultsModel	ProductResultsModel = TableUI.new ResultsModel();

	private UI.DETextField 	ProductID 		= Product.ProductID,
										ProductName	= Product.ProductName,
										Quantity		= Product.Quantity,
										UnitPrice		= Product.UnitPrice,
										TypeID 		= Type.TypeID;

	private UI.DEChoice		AllTypeInfo= Type.AllTypeInfo;

	private DETableUI.DETable ProductTable = Product.ProductTable;

	private UI.DEButtons 	forSearch		= Product.forSearch.add( Product.SearchOptions, this ),
									forValidate 	= Product.forValidate.add( Product.ValidateOptions, this ),
									forEdit 			= Product.forEdit.add( Product.EditOptions, this ),
									forAddNew	= Product.forAddNew.add( Product.AddNewOptions, this ),
									forProducts	= Product.forProducts.add( Product.ProductsOptions, this );

	private Component 	group[] 	= { ProductID, ProductName },
									group1[]	= { UnitPrice, Quantity },
									group2[] 	= { TypeID, AllTypeInfo },
									group3[]	= { ProductTable },
									group4[]	= { forSearch, forValidate, forEdit, forAddNew, forProducts };

	private String SecurityMode = "High";
	private FurnitureSystem TheSystem;
	private boolean AllEditable;

	public TypeProduct ( FurnitureSystem theSystem ) {
		super();
		TheSystem = theSystem;
		//==================================================
		gridx( 0 ); fill( BOTH ); gridwidth( 3 ); insets( 0, 0, 3, 0);
			gridy( 0 ); add( Header );
		insets( 3, 6, 6, 6);
			gridy( 7 ); add( ProductTable.withScrollbar() );
				ProductTable.setModel( ProductResultsModel );
		insets( 3, 6, 6, 6);
			gridy( 8 ); add( group4 );
		//======================================================================
		fill(  NONE ); gridwidth( 1 ); anchor( EAST ); insets( 3, 6, 3, 3);
			gridy( 1 ); add( ProductID.FLabel );
			gridy( 2 ); add( ProductName.FLabel );
			gridy( 3 ); add( Quantity.FLabel );
			gridy( 4 ); add( UnitPrice.FLabel );
			gridy( 5 ); add( TypeID.FLabel );
			gridy( 6 ); add( AllTypeInfo.FLabel );
		//====================================================
		gridx( 1 ); gridheight( 1 ); anchor( WEST); insets( 3, 3, 3, 6);
			gridy( 1 ); add( ProductID );
			gridy( 2 ); add( ProductName );
			gridy( 3 ); add( Quantity );
			gridy( 4 ); add( UnitPrice );
			gridy( 5 ); add( TypeID );
			gridy( 6 ); add( AllTypeInfo );

			AllTypeInfo.addItemListener( this );

		validate();
	}

	public TypeProduct toSearch() {
		Header.setText( "Search Product" );
		show( group ); enable( group );
			ProductName.setEnabled( false );
		show( group1 ); disable( group1 );
		show( group2 ); disable( group2 );
		hide( group3 );
		hide( group4 );
		//========================================
			doSearch();
		//=========================================
		getParent().validate();
		forSearch.setVisible( true );
		ProductID.requestFocus();
		return this;
	}

	private void doSearch() {
		ProductID.selectAll();
		ProductName.setText( "" );
		Quantity.setText( "0" );
		UnitPrice.setText( "RM 0.00" );

		if ( AllTypeInfo.getItemCount() > 0 )
			AllTypeInfo.select( AllTypeInfo.getItemCount() - 1 );

		ProductID.addKeyListener( this );
		UnitPrice.removeMouseListener( this );
		Quantity.removeMouseListener( this );

		AllTypeInfo.select( 0 );
	}

	public TypeProduct toProducts() {
		Header.setText( "List Products" );
		hide( group );
		hide( group1 );
		show( group2 ); enable( group2 );
			TypeID.setVisible( false );
		show( group3 );
		hide( group4 );

		ProductID.removeKeyListener( this );
		forProducts.setVisible( true );
		doProducts();
		return this;
	}

	private void doProducts() {
		odbc = new dbODBC( TheSystem.getConnection() );
		String TheTypeInfo = (String) AllTypeInfo.getSelectedItem();
		String SQL = "Select " +
								"Product.ProductID, " +
								"Product.ProductName, " +
								"Product.Quantity, " +
								"Product.UnitPrice " +
							"From " +
								"Product, " +
								"[Type] " +
							"Where " +
								"Type.TypeID = Product.TypeID And " +
								"Type.TypeInfo = '" +  TheTypeInfo + "' ";
		int RecordCount = SQLTool.RecordCount( SQL );
		SQL = SQL +
					"Order By " +
						"Product.ProductID ";
		odbc.executeQuery( SQL );
		ProductResultsModel.setResultSet( odbc.getResultSet(), RecordCount );

		ProductTable.AdjustColumnWidths();

		int RowCount			= 	ProductTable.getRowCount();
		int SelectColumn 	= 	ProductTable.findColumn( "UnitPrice" );
		ProductTable.validate();
		String Value;
		if ( SelectColumn >= 0 ) {
			for ( int a = 0; a< RowCount; a++ ) {
				Value = (String) ProductTable.getValueAt( a, SelectColumn );
				ProductTable.setValueAt( DETool.toCurrency( Value ), a, SelectColumn );
			}
		}

		ProductTable.getColumn( ProductTable.findColumn( "ProductName" ) ).setPreferredWidth( 260 );
	}

	private void toCriteria() {
		Header.setText( "List Products" );
		hide( group );
			ProductName.setVisible( true );
			ProductName.setEnabled( true );
		hide( group1 );
		hide( group2 );
		show( group3 );
		hide( group4 );

		ProductID.removeKeyListener( this );
		doCriteria();
		forProducts.setVisible( true );
	}

	private void doCriteria() {
		String TheProductName = ProductName.getText();
		if ( ! TheProductName.equals( "" ) ) {
			odbc = new dbODBC( TheSystem.getConnection() );
			String SQL = "Select " +
									"Product.ProductID, " +
									"Product.ProductName, " +
									"Product.Quantity, " +
									"Product.UnitPrice " +
								"From " +
									"Product " +
								"Where " +
									"Product.ProductName Like '%" +  TheProductName  + "%' ";
			int RecordCount = SQLTool.RecordCount( SQL );
			SQL = SQL +
						"Order By " +
							"Product.ProductID ";
			odbc.executeQuery( SQL );
			ProductResultsModel.setResultSet( odbc.getResultSet(), RecordCount );

			ProductTable.AdjustColumnWidths();

			int RowCount			= 	ProductTable.getRowCount();
			int SelectColumn 	= 	ProductTable.findColumn( "UnitPrice" );
			ProductTable.validate();
			String Value;
			if ( SelectColumn >= 0 ) {
				for ( int a = 0; a< RowCount; a++ ) {
					Value = (String) ProductTable.getValueAt( a, SelectColumn );
					ProductTable.setValueAt( DETool.toCurrency( Value ), a, SelectColumn );
				}
			}

			ProductTable.getColumn( ProductTable.findColumn( "ProductName" ) ).setPreferredWidth( 260 );
		}
	}


	private void toValidate() {
		Header.setText( "Product Options" );
		show( group ); disable( group );
		show( group1 ); disable( group1 );
		show( group2 ); disable( group2 );
		hide( group3 );
		hide( group4 );
		forValidate.setVisible( true );
	}

	private boolean doValidate() {
		odbc = new dbODBC( TheSystem.getConnection() );
		String theProductID = ProductID.getText().trim();
		if ( theProductID.equals( "" ) )
			JOptionPane.showMessageDialog	( 	this, "Please Input some data", "Record Not Found",  JOptionPane.WARNING_MESSAGE );
		else {
			odbc.executeQuery( 	"Select " +
												"Product.ProductName, " +
												"Product.Quantity, " +
												"Product.UnitPrice, " +
												"Type.TypeInfo " +
											"From " +
												"Product, " +
												"[Type] " +
											"Where " +
												"Product.TypeID = Type.TypeID And " +
												"Product.ProductID = '" + theProductID + "' "
										);

			if ( odbc.move( "Next" ) ) {
				ProductName.setText( odbc.getString( "ProductName" ) );
				Quantity.setText( odbc.getString( "Quantity" ) );
				UnitPrice.setText( DETool.toCurrency( odbc.getString( "UnitPrice" ) ) );

				AllTypeInfo.select( odbc.getString( "TypeInfo" ) );

				return true;
			}
			else {
				JOptionPane.showMessageDialog	( 	this, "Bad Product ID", "Record Not Found",  JOptionPane.INFORMATION_MESSAGE );
			}
		}
		return false;
	}

	public TypeProduct toAddNew() {
		Header.setText( "Add New Product" );
		show( group ); enable( group );
			Quantity.setEnabled( false );
		show( group1 ); disable( group1 );
		show( group2 ); enable( group2 );
		hide( group3 );
		hide( group4 );
		//========================================
					doAddNew();
		//=========================================
		forAddNew.setVisible( true );

		ProductID.requestFocus();
		return this;
	}

	private void doAddNew() {
		ProductID.setText( "" );
		ProductName.setText( "" );
		Quantity.setText( "0" );
		UnitPrice.setText( "RM 0.00" );

		AllTypeInfo.setEditable( true );
		AllTypeInfo.addItem( "" );
		AllTypeInfo.select( "" );

		UnitPrice.removeMouseListener( this );
		Quantity.removeMouseListener( this );
		UnitPrice.addMouseListener( this );
		Quantity.addMouseListener( this );
		ProductID.removeKeyListener( this );

		Quantity.selectAll();
		UnitPrice.select( 3, UnitPrice.getText().length() );
	}

	private void toEdit() {
		Header.setText( "Edit Record" );
		show( group ); disable( group );
			ProductName.setEnabled( true );
		show( group1 ); disable( group1 );
		show( group2 ); enable( group2 );
			TypeID.setEnabled( false );
		hide( group3 );
		hide( group4 );

		AllTypeInfo.removeItem( "" );
		AllTypeInfo.setEditable( true );

		UnitPrice.removeMouseListener( this );
		Quantity.removeMouseListener( this );
		UnitPrice.addMouseListener( this );
		Quantity.addMouseListener( this );
		ProductID.removeKeyListener( this );

		forEdit.setVisible( true );
		ProductName.requestFocus();
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

	private void toEditQuantity() {
		String Message = 	"1. To INCREASE Quantity ( e.g. +50 ). \n" +
									"2. To DEDUCT Quantity ( e.g. -100 ). \n" +
									"3. To REPLACE Quantity ( e.g. 70 ).\n" +
									"Current Quantity value is " + Quantity.getText();
		String Title = "Input Quantity";
		String Data = JOptionPane.showInputDialog	( 	this, Message, Title, JOptionPane.QUESTION_MESSAGE );
		if ( Data != null && ! Data.equals( "" ) ) {
			int intValue = Integer.parseInt( Quantity.getText() );
			Data = Integer.toString( DETool.parseQuantity( intValue, Data ) );
			Quantity.setText( Data );
		}
	}

	private void toEditUnitPrice() {
		String Data = JOptionPane.showInputDialog	( 	this, "Input a double value ( e.g. 50.50 )", "Input UnitPrice", JOptionPane.QUESTION_MESSAGE );
		if ( Data != null && ! Data.equals( "" ) ) {
			try {
				double DoubleValue = Double.parseDouble( Data );
				UnitPrice.setText( DETool.toCurrency( Double.toString( DoubleValue ) ) );
			}
			catch ( Exception exception ) {
				JOptionPane.showMessageDialog	( 	this, "The Data input should be a double Value", "Wrong Input", JOptionPane.INFORMATION_MESSAGE );
			}
		}
	}

	private void toCancel() {
		if ( forEdit.isVisible() ) {
			if ( doValidate() )
				toValidate();
		}
		else if ( forAddNew.isVisible() )
			toSearch();
		else if ( forValidate.isVisible() )
			toSearch();
		else if ( forSearch.isVisible() )
			setVisible( false );
	}

	private void toSave() {
		odbc = new dbODBC( TheSystem.getConnection() );
		boolean toBreak = true;

		String TheProductID = ProductID.getText();
		String TheProductName = ProductName.getText();
		String TheQuantity = Quantity.getText();
		String TheUnitPrice = UnitPrice.getText();
		String TheTypeID = TypeID.getText();
		String TheTypeInfo = (String) AllTypeInfo.getSelectedItem();

		if ( TheProductID.equals( "" ) || TheProductName.equals( "" ) || TheQuantity.equals( "" ) || TheUnitPrice.equals( "" ) || TheTypeID.equals( "" ) )
			JOptionPane.showMessageDialog	( 	this, "Imcomplete Form", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );

		else if ( TheTypeID.length() != 2 )
			JOptionPane.showMessageDialog	( 	this, "The length of Type ID should 2 characters", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );

		else if ( TheProductID.length() != 5 )
			JOptionPane.showMessageDialog	( 	this, "The length of Product ID should 5 characters", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );

		else if ( TheProductName.length() > 50 )
			JOptionPane.showMessageDialog	( 	this, "The length of Product Name should not greater than 50 characters", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );

		else {
			odbc.executeQuery( 	"Select " +
												"Product.ProductID " +
											"From " +
												"Product " +
											"Where " +
												"Product.ProductID = '" + TheProductID + "' "
										);
			if ( odbc.move( "Next" ) ) {
				JOptionPane.showMessageDialog	( 	this, "Product ID redundant", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
				toBreak = true;
			}
			else
				toBreak = false;

			if ( ! toBreak ) {
				odbc.executeQuery( 	"Select " +
													"Type.TypeID " +
												"From " +
													"[Type] " +
												"Where " +
													"Type.TypeInfo = '" + TheTypeInfo + "' "
											);

				if ( odbc.move( "Next" ) ) {
					if ( ! TheTypeID.equals( odbc.getString( "TypeID" ) ) ) {
						JOptionPane.showMessageDialog	( 	this, "New Type ID but Redundant Type Info", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
					}
					else
						toBreak = false;
				}
				else {
					odbc.executeQuery( 	"Select " +
														"Type.TypeInfo " +
													"From " +
														"[Type] " +
													"Where " +
														"Type.TypeID = '" + TheTypeID + "' "
												);
					if ( odbc.move( "Next" ) ) {
						if ( ! TheTypeInfo.equals( odbc.getString( "TypeInfo" ) ) ) {
							odbc.executeUpdate( "Update " +
																"[Type] " +
															"Set " +
																"Type.TypeInfo = '" + TheTypeInfo + "' " +
															"Where " +
																"Type.TypeID = '" + TheTypeID + "' "
														);

							//===================check isUpdated Type Table=============================
							if ( SQLTool.CheckRecordExists( odbc, "Select " +
																						"Type.TypeID " +
																					"From " +
																						"[Type] " +
																					"Where " +
																						"Type.TypeID = '" + TheTypeID + "' And " +
																						"Type.TypeInfo = '" + TheTypeInfo + "' " ) ) {

								JOptionPane.showMessageDialog	( 	this, "Successful", "Type Info UPDATED", JOptionPane.INFORMATION_MESSAGE );
								toBreak = false;
							}
						}
					}
					else {
						odbc.executeUpdate( "Insert Into " +
															"[Type] " +
														"Values ( '" +
															TheTypeID + "', '" +
															TheTypeInfo + "' ) "
													);
						//===================check isUpdated Type Table=============================
						if ( SQLTool.CheckRecordExists( odbc, "Select " +
																					"Type.TypeID " +
																				"From " +
																					"[Type] " +
																				"Where " +
																					"Type.TypeID = '" + TheTypeID + "' And " +
																					"Type.TypeInfo = '" + TheTypeInfo + "' " ) ) {

							JOptionPane.showMessageDialog	( 	this, "Successful", "New Type SAVED", JOptionPane.INFORMATION_MESSAGE );
							toBreak = false;
						}
					}
				}
			}

			if ( ! toBreak ) {
				odbc.executeUpdate( 	"Insert Into " +
													"Product " +
												"Values ( '" +
													TheProductID + "', '" +
													TheProductName + "', '" +
													Double.toString( DETool.CurrencyToDouble( TheUnitPrice ) ) + "', '" +
													TheQuantity + "', '" +
													TheTypeID + "' ) "
											);
				//======================Check isSaved Product Table=========================
				odbc.executeQuery( 	"Select " +
													"Product.UnitPrice, " +
													"Product.Quantity " +
												"From " +
													"Product " +
												"Where " +
													"Product.ProductID = '" + TheProductID + "' And " +
													"Product.ProductName = '" + TheProductName + "' And " +
													"Product.TypeID = '" + TheTypeID + "' "
											);
				if ( odbc.move( "Next" ) ) {
					if ( odbc.getString( "Quantity" ).equals( TheQuantity ) ) {
						if ( Double.parseDouble( odbc.getString( "UnitPrice" ) ) == DETool.CurrencyToDouble( TheUnitPrice ) ) {
							JOptionPane.showMessageDialog	( 	this, "Successful", "Record SAVED", JOptionPane.INFORMATION_MESSAGE );
							toSearch();
						}
					}
				//======================================================================
				}
				else
					JOptionPane.showMessageDialog	( 	this, "System may not save The Record", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
			}
		}
	}

	private void toUpdate() {
		odbc = new dbODBC( TheSystem.getConnection() );
		boolean toBreak = true;

		String TheProductID = ProductID.getText();
		String TheProductName = ProductName.getText();
		String TheQuantity = Quantity.getText();
		String TheUnitPrice = UnitPrice.getText();
		String TheTypeID = TypeID.getText();
		String TheTypeInfo = (String) AllTypeInfo.getSelectedItem();

		if ( TheProductID.equals( "" ) || TheProductName.equals( "" ) || TheQuantity.equals( "" ) || TheUnitPrice.equals( "" ) || TheTypeID.equals( "" ) )
				JOptionPane.showMessageDialog	( 	this, "Imcomplete Form", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );

		else if ( TheProductName.length() > 50 )
				JOptionPane.showMessageDialog	( 	this, "The length of Product Name should not greater than 50 characters", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );

		else {
			odbc.executeQuery( 	"Select " +
												"Type.TypeInfo " +
											"From " +
												"[Type] " +
											"Where " +
												"Type.TypeID = '" + TheTypeID + "' "
										);
			if ( odbc.move( "Next" ) ) {
				if ( ! TheTypeInfo.equals( odbc.getString( "TypeInfo" ) ) ) {
					odbc.executeUpdate( "Update " +
														"[Type] " +
													"Set " +
														"Type.TypeInfo = '" + TheTypeInfo + "' " +
													"Where " +
														"Type.TypeID = '" + TheTypeID + "' "
												);

					//===================check isUpdated Type Table=============================
					if ( SQLTool.CheckRecordExists( odbc, "Select " +
																				"Type.TypeID " +
																			"From " +
																				"[Type] " +
																			"Where " +
																				"Type.TypeID = '" + TheTypeID + "' And " +
																				"Type.TypeInfo = '" + TheTypeInfo + "' " ) ) {

						toBreak = false;
						JOptionPane.showMessageDialog	( 	this, "Successful", "Type Info UPDATED", JOptionPane.INFORMATION_MESSAGE );
					}
				}
				else
					toBreak = false;
			}
			//=============================Update Product table========================
			if ( ! toBreak ) {
				odbc.executeUpdate( 	"Update " +
													"Product " +
												"Set " +
													"Product.ProductName = '" + TheProductName + "', " +
													"Product.UnitPrice = '" + Double.toString( DETool.CurrencyToDouble( TheUnitPrice ) ) + "', " +
													"Product.Quantity = '" + TheQuantity + "', " +
													"Product.TypeID = '" + TheTypeID + "' " +
												"Where " +
													"Product.ProductID = '" + TheProductID + "' "
											);
				//======================Check isSaved Product Table=========================
				odbc.executeQuery( 	"Select " +
													"Product.UnitPrice, " +
													"Product.Quantity " +
												"From " +
													"Product " +
												"Where " +
													"Product.ProductID = '" + TheProductID + "' And " +
													"Product.ProductName = '" + TheProductName + "' And " +
													"Product.TypeID = '" + TheTypeID + "' "
											);
				if ( odbc.move( "Next" ) ) {
					if ( odbc.getString( "Quantity" ).equals( TheQuantity ) ) {
						if ( Double.parseDouble( odbc.getString( "UnitPrice" ) ) == DETool.CurrencyToDouble( TheUnitPrice ) ) {
							JOptionPane.showMessageDialog	( 	this, "Successful", "Record SAVED", JOptionPane.INFORMATION_MESSAGE );
							if ( doValidate() )
								toValidate();
						}
					}
				//======================================================================
				}
				else
					JOptionPane.showMessageDialog	( 	this, "System may not save The Record", "Record NOT SAVE", JOptionPane.INFORMATION_MESSAGE );
			}
		}
	}

	private void toDone() {
		setVisible( false );
	}

	private void toPrintRecords() {
		final ExtFrame PrintFrame 				= new ExtFrame();
		PrintFrame.setResizable( false );
		PrintFrame.setTitle( "Print Preview" );
		PrintFrame.getContentPane().setLayout( new java.awt.FlowLayout() );
		DEObjects Objects = new DEObjects();
		final DEObjects.DETextArea PrintPreview = Objects.new DETextArea( 20, 41 );
		PrintFrame.setVisible( true );
		PrintFrame.getContentPane().add( PrintPreview.withScrollbar() );
		PrintPreview.withScrollbar().setPreferredSize( PrintPreview.getPreferredSize().getSize() );
		String Text = "";
		if ( ! ProductName.isVisible() ) {
			Text = "Type Info : " + (String)  AllTypeInfo.getSelectedItem() + "\t" + " \t" + "\n" +
						"\n";
		}
		Text = Text + "Product ID" + " \t" + "Product Name" + " \t\t\t\t\t" + "Quantity" + " \t" + "Unit Price" +"\n";

		Object AllValueOfProductID[]			= ProductTable.getAllValueAtColumn( "ProductID" );
		Object AllValueOfProductName[]		= ProductTable.getAllValueAtColumn( "ProductName" );
		Object AllValueOfQuantity[]			= ProductTable.getAllValueAtColumn( "Quantity" );
		Object AllValueOfUnitPrice[]			= ProductTable.getAllValueAtColumn( "UnitPrice" );

		int RowCount = AllValueOfProductID.length;
		for ( int a = 0; a < RowCount; a++ ) {
			Text = Text + 	(String) AllValueOfProductID[ a ] + " \t " + (String) AllValueOfProductName[ a ] + "\t";
			if ( ((String) AllValueOfProductName[ a ]).length() < 25 )
				Text = Text + "\t\t\t";
			else if ( ((String) AllValueOfProductName[ a ]).length() < 28 )
				Text = Text +"\t" + "\t";
			else if ( ((String) AllValueOfProductName[ a ]).length() < 30 )
				Text = Text +"\t";

			if ( ((String) AllValueOfProductName[ a ]).length() <= 15 )
				Text = Text + "\t";
			else if ( ((String) AllValueOfProductName[ a ]).length() < 11 )
				Text = Text + "\t" + "\t";
			else if ( ((String) AllValueOfProductName[ a ]).length() < 9 )
				Text = Text + "\t" + "\t" + "\t";

			Text = Text + (String) AllValueOfQuantity[ a ] +   " \t\t " + (String) AllValueOfUnitPrice[ a ] +" \n";
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

		PrintFrame.getContentPane().add( OKButton );
		PrintFrame.getContentPane().add( CancelButton );
		PrintFrame.setSize( PrintFrame.getPreferredSize() );
		PrintFrame.validate();
	}

	public void actionPerformed( ActionEvent event ) {
		System.out.println( event.getActionCommand() );
		String command = event.getActionCommand();
		if ( command.equals( "Validate" ) ) {
			if ( doValidate() )
				toValidate();
		}
		else if ( command.equals( "Edit" ) )
			toEdit();
		else if ( command.equals( "Cancel" ) )
			toCancel();
		else if ( command.equals( "Update" ) )
			toUpdate();
		else if ( command.equals( "Save" ) )
			toSave();
		else if ( command.equals( "Done" ) )
			toDone();
		else if ( command.equals( "Refresh" ) )
			toRefresh();
		else if ( command.equals( "Print Records" ) )
			toPrintRecords();
		else if ( command.equals( "Criteria" ) )  {
			toCriteria();
			ProductName.requestFocus();
		}
	}
	//========================mouseListener============================
	public void mouseClicked( MouseEvent event ) {
		if ( event.getSource() == Quantity )
			toEditQuantity();
		else if ( event.getSource() == UnitPrice )
			toEditUnitPrice();
	}
	public void mouseEntered( MouseEvent event ){}
	public void mouseExited( MouseEvent event ){}
	public void mousePressed( MouseEvent event ){}
	public void mouseReleased( MouseEvent event ){}
	//========================KeyListener============================
		public void keyPressed( KeyEvent event ) {
			if ( event.getKeyCode() == event.VK_ENTER ) {
				if ( doValidate() )
					toValidate();
			}
		}
		public void keyReleased( KeyEvent event ) {}
		public void keyTyped( KeyEvent event ) {}
	//======================ItemListener=============================
	public void itemStateChanged( ItemEvent event ) {
		if ( event.getSource() == AllTypeInfo ) {
			odbc = new dbODBC( TheSystem.getConnection() );
			String SQL = "Select " +
									"Type.TypeID " +
								"From " +
									"[Type] " +
								"Where " +
									"Type.TypeInfo = '" + AllTypeInfo.getSelectedItem() + "' ";
			odbc.executeQuery( SQL );
			if ( odbc.move( "Next" ) ) {
				TypeID.setText( odbc.getString( "TypeID" ) );
				if ( ProductTable.isVisible() )
					doProducts();
			}
			else if ( ! forAddNew.isVisible() && ! forEdit.isVisible() )
				TypeID.setText( "" );
		}
	}
}
