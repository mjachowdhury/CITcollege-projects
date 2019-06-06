import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

public class UI extends DEObjects {
	public DEButton DEButton( String text, Icon icon ) {
		return new DEButton( text, icon);
	}
	public DELabel DELabel( String text ) {
		return new DELabel( text );
	}

	public DETextField DETextField( int column ) {
		return new DETextField( column );
	}

	public DEPasswordField DEPasswordField( int column ) {
		return new DEPasswordField( column );
	}

	public DETextArea DETextArea( int row, int column ) {
		return new DETextArea( row, column );
	}

	public DEChoice DEChoice( int column ) {
		return new DEChoice( column );
	}

	public DEButtons DEButtons( int row, int column, int verticalGap, int horizontalGap ) {
		return new DEButtons( row, column, verticalGap, horizontalGap );
	}
	//========================================================================================
	public class DEButtons extends DEPanel {
		private DEButton button[];

		public DEButtons( int row, int column, int verticalGap, int horizontalGap ) {
			super( new GridLayout( row, column, verticalGap, horizontalGap ), false );
		}

		public DEButtons add( String texts[], ActionListener actionListener ) {
			this.add( texts, null, actionListener );
			return this;
		}

		public DEButtons add( String texts[], Icon picture[], ActionListener actionListener ) {
			button = new UI.DEButton[ texts.length ];
			if ( picture == null ) picture = new Icon[ texts.length ];
			for ( byte a = 0; a < texts.length; a++ ) {
				button[ a ] = new DEButton( texts[ a ], picture[ a ] );
				button[ a ].addActionListener( actionListener );
			}

			for ( byte a = 0; a < button.length ; a++)
				add( button[ a ] );

			return this;
		}

		public void setVisible( boolean visible ) {
			super.setVisible( visible );
			if ( button != null && visible ) button[ 0 ].requestFocus();
		}
	}
	//=========================================================================
	public class SalesTeam {
		private DETableUI TableUI = new DETableUI();
		private DETableUI.DETable tblSalesTeam() {
			DETableUI.DETable table = TableUI.new DETable ()
			{	public boolean isCellEditable (int iRows, int iCols)
				{	return false;	//Disable All Columns of Table.
				}
			};
			table.setPreferredScrollableViewportSize( new java.awt.Dimension( 600, 150 ) );
			return table;
		}
		//============================================================================

		public DETableUI.DETable 	SalesTeamTable = tblSalesTeam();

		public DETextField TeamID 			= DETextField( 10 ).setDELabel( DELabel( "Team ID" ) ),
									SalesTeamInfo 	= DETextField( 20 ).setDELabel( DELabel( "Sales Team Info" ) );

		public DEChoice AllTeamID 	= DataForAllTeamID();

		private DEChoice DataForAllTeamID() {
			DEChoice aDEChoice = DEChoice( 10 ).setDELabel( DELabel( "Team ID" ) );
			String Items[] = SQLTool.getStrings( "TeamID", 0, "Select TeamID From [Sales Team]" );
			aDEChoice.setModel( new DefaultComboBoxModel( Items ) );
			return aDEChoice;
		}

		public String 	TableOptions[] 	= DETool.toStringArray( "&Team, &Print, &Done", ',', 3 ),
							Table2Options[] = DETool.toStringArray( "&Member, &Graph, &Print, &Done", ',', 4 ),
							PieOptions[]		= DETool.toStringArray( "&Print Pie Chart, &Back", ',', 2 );

		public DEButtons 	forTable	= DEButtons( 1, 0, 3, 3 ),
									forTable2	= DEButtons( 1, 0, 3, 3 ),
									forPie		= DEButtons( 1, 0, 3, 3 );
	}


	public class SalesRep {
		private String sKey = " FuRnItUrE !  ";
		//===================================================================
		private DETableUI TableUI = new DETableUI();
		private DETableUI.DETable tblCustomer() {
			DETableUI.DETable table = TableUI.new DETable ()
			{	public boolean isCellEditable (int iRows, int iCols)
				{	return false;	//Disable All Columns of Table.
				}
			};
			table.setPreferredScrollableViewportSize( new java.awt.Dimension( 550, 150 ) );
			table.AdjustColumnWidths();
			return table;
		}
		public DETableUI.DETable CustomerTable = tblCustomer();
		public DETextField
			EmployeeID 		= DETextField( 10 ).setDELabel( DELabel( "Employee ID" ) ),
			EmployeeName 	= DETextField( 20 ).setDELabel( DELabel( "Employee Name" ) ),
			ContactNO			= DETextField( 10 ).setDELabel( DELabel( "Contact NO" ) );

		public DEPasswordField
			OldPassword 			= DEPasswordField( 10 ).setKeySecure( sKey ).setDELabel( DELabel( "Old Password" ) ),
			NewPassword 		= DEPasswordField( 10 ).setKeySecure( sKey ).setDELabel( DELabel( "New Password" ) ),
			ConfirmPassword 	= DEPasswordField( 10 ).setKeySecure( sKey ).setDELabel( DELabel( "Confirm Password" ) );

		public DEChoice	AllEmployeeID			= DataForAllEmployeeID(),
								AllSalesEmployeeID	= DataForAllSalesEmployeeID(),
								Department				= DataForAllDepartment();

		private DEChoice DataForAllEmployeeID() {
			DEChoice aDEChoice = DEChoice( 10 ).setDELabel( DELabel( "Employee ID" ) );
			String Items[] = SQLTool.getStrings( "StaffID", 0, "Select "+
																							"Employee.StaffID " +
																					"From " +
																							"Employee" );
			aDEChoice.setModel( new javax.swing.DefaultComboBoxModel( Items ) );
			return aDEChoice;
		}

		private DEChoice DataForAllSalesEmployeeID() {
			DEChoice aDEChoice = DEChoice( 10 ).setDELabel( DELabel( "Employee ID" ) );
			String Items[] = SQLTool.getStrings( "StaffID", 0, "Select "+
																							"Employee.StaffID, " +
																							"Employee.Department " +
																					"From " +
																							"Employee " +
																					"Where " +
																							"Employee.Department = 'Sales'" );
			aDEChoice.setModel( new DefaultComboBoxModel( Items ) );
			return aDEChoice;
		}

		private DEChoice DataForAllDepartment() {
			DEChoice aDEChoice = DEChoice( 15 ).setDELabel( DELabel( "Department" ) );
			String Items[] = DETool.toStringArray( "HR, Accounts, Stock, Sales, SalesL", ',', 5 );
			aDEChoice.setModel( new javax.swing.DefaultComboBoxModel( Items ) );
			return aDEChoice;
		}

		public DETextArea Address 		= DETextArea( 2, 20 ).setDELabel( DELabel( "Address" ) );

		public String DetailOptions[] 	= DETool.toStringArray( "&Edit, &Done", ',', 2 ),
						PasswordOptions[]	= DETool.toStringArray( "C&onfirmed, &Cancel", ',', 2 ),
						AddNewOptions[]	= DETool.toStringArray( "&Save Record, &Refresh, &Cancel", ',', 3 ),
						EditOptions[]			= DETool.toStringArray( "&Update Record, &Refresh, &Cancel", ',', 3 ),
						LoginOptions[]		= DETool.toStringArray( "&Login, &Cancel", ',', 2 ),
						TableOptions[]		= DETool.toStringArray( "&Print Records, &Back", ',', 2 );

		public DEButtons forDetail	= DEButtons( 1, 0, 3, 3 ),
							forPassword	= DEButtons( 1, 0, 3, 3 ),
							forAddNew	= DEButtons( 1, 0, 3, 3 ),
							forEdit			= DEButtons( 1, 0, 3, 3 ),
							forLogin		= DEButtons( 1, 0, 3, 3 ),
							forTable		= DEButtons( 1, 0, 3, 3 );
	}

	public class Customer {

		public DETextField 	CustomerID 		= DETextField( 10 ).setDELabel( DELabel( "Customer ID" ) ),
									CustomerName 	= DETextField( 20 ).setDELabel( DELabel( "Customer Name" ) ),
									CustomerIC		= DETextField( 10 ).setDELabel( DELabel( "Customer IC" ) ),
									ContactNO			= DETextField( 10 ).setDELabel( DELabel( "Contact NO" ) );

		public DETextArea Address 		= DETextArea( 2, 20 ).setDELabel( DELabel( "Address" ) );
		//=============================================================================
		public String SearchOptions[] 		= DETool.toStringArray( "&Validate, &Add New, &Cancel", ',', 3 ),
						ValidateOptions[]		= DETool.toStringArray( "&Make Order, &Edit Record, &Back", ',', 3 ),
						AddNewOptions[]		= DETool.toStringArray( "&Save Record, &Refresh, &Cancel", ',', 3 ),
						EditOptions[]				= DETool.toStringArray( "&Update Record, &Refresh, &Cancel", ',', 3 );

		public DEButtons forSearch	= DEButtons( 1, 0, 3, 3 ),
						forValidate		= DEButtons( 1, 0, 3, 3 ),
						forAddNew		= DEButtons( 1, 0, 3, 3 ),
						forEdit				= DEButtons( 1, 0, 3, 3 );

	}

	public class Type {
		public DETextField 	TypeID 	= DETextField( 10 ).setDELabel( DELabel( "Type ID" ) ),
									TypeInfo 	= DETextField( 20 ).setDELabel( DELabel( "Type Info" ) );

		public DEChoice		AllTypeID	= DataForTypeID(),
									AllTypeInfo = DataForTypeInfo();

		private DEChoice DataForTypeID() {
			DEChoice aDEChoice = DEChoice( 20 ).setDELabel( DELabel( "Type ID" ) );
			String Items[] = SQLTool.getStrings( "TypeID", 0, "Select TypeID From Type" );
			aDEChoice.setModel( new DefaultComboBoxModel( Items ) );
			return aDEChoice;
		}

		private DEChoice DataForTypeInfo() {
			DEChoice aDEChoice = DEChoice( 20 ).setDELabel( DELabel( "Type info" ) );
			String Items[] = SQLTool.getStrings( "TypeInfo", 0, "Select TypeInfo From Type" );
			aDEChoice.setModel( new DefaultComboBoxModel( Items ) );
			return aDEChoice;
		}
	}


	public class Order {
		private DETextField txtOrderDate() {
								DETextField textfield = DETextField( 10 );
								textfield.setText( DETool.TodayDate );
								return textfield;
							};
		//==========================================================================
		public DETextField 	OrderID 	= DETextField( 10 ).setDELabel( DELabel( "Order ID" ) ),
								OrderDate 	= txtOrderDate().setDELabel( DELabel( "Order Date" ) );

		public String forDateOptions[] = DETool.toStringArray( "&Today's Date, C&hange", ',', 2 );

		public DEButtons forDate = DEButtons( 1, 0, 3, 3 );
	}

	public class OrderLine {
		private DETableUI TableUI = new DETableUI();
		private DETableUI.DETable tblOrderLine() {
			final String colsName[] = { "Product ID", "Product Name" , "Quantity", "Unit Price", "Total" };
			Object rowRec[][] = new Object [ 0 ][ 5 ];	;
			DETableUI.DETable table = TableUI.new DETable ( rowRec, colsName )
			{	public boolean isCellEditable (int iRows, int iCols)
				{	return false;	//Disable All Columns of Table.
				}
			};
			table.setPreferredScrollableViewportSize( new java.awt.Dimension( 550, 150 ) );
			table.getColumn( 0 ).setPreferredWidth( 100 );
			table.getColumn( 1 ).setPreferredWidth( 240 );
			table.getColumn( 2 ).setPreferredWidth( 100 );
			table.getColumn( 3 ).setPreferredWidth( 100 );
			table.getColumn( 4 ).setPreferredWidth( 100 );
			table.revalidate();
			return table;
		}
		//============================================================================

		private DETableUI TableUI2 = new DETableUI();
		private DETableUI.DETable tblOrderLine2() {
			DETableUI.DETable table = TableUI2.new DETable ()
			{	public boolean isCellEditable (int iRows, int iCols)
				{	return false;	//Disable All Columns of Table.
				}
			};
			table.setPreferredScrollableViewportSize( new java.awt.Dimension( 620, 250 ) );
			return table;
		}

		public DETableUI.DETable 	OrderLineTable 	= tblOrderLine();
		public DETableUI.DETable 	OrderLineTable2 	= tblOrderLine2();

		public DETextField 	GrandTotal = DETextField( 10 ).setDELabel( DELabel( "Grand Total" ) );

		public String 	forTableOptions[] 		= DETool.toStringArray( "&Add, &Remove", ',', 2 ),
						forSalesOrderOptions[]	= DETool.toStringArray( "&Save Order, &Print Receipt, &Cancel", ',', 3 );

		public DEButtons forTable		= DEButtons( 1, 0, 3, 3 ),
						 forSalesOrder	= DEButtons( 1, 0, 3, 3 );
	}

	public class Product {
		private DETableUI TableUI = new DETableUI();
		private DETableUI.DETable tblProduct() {
			DETableUI.DETable table = TableUI.new DETable ()
			{	public boolean isCellEditable (int iRows, int iCols)
				{	return false;	//Disable All Columns of Table.
				}
			};
			table.setPreferredScrollableViewportSize( new java.awt.Dimension( 620, 250 ) );
			return table;
		}
		//===================================================================================
		public DETextField 	ProductID 	= DETextField( 10 ).setDELabel( DELabel( "Product ID" ) ),
						 		ProductName = DETextField( 20 ).setDELabel( DELabel( "Product Name" ) ),
								Quantity		= DETextField( 10 ).setDELabel( DELabel( "Quantity" ) ),
								UnitPrice		= DETextField( 10 ).setDELabel( DELabel( "Unit Price" ) );

		public DETableUI.DETable ProductTable = tblProduct();

		public String 	SearchOptions[]			= DETool.toStringArray( "&Validate, &Cancel", ',', 2 ),
							ValidateOptions[] 		= DETool.toStringArray( "&Edit, &Print Record, &Cancel", ',', 3 ),
							EditOptions[] 			= DETool.toStringArray( "&Update, &Refresh, &Cancel", ',', 3 ),
							AddNewOptions[]		= DETool.toStringArray( "&Save, &Refresh, &Cancel", ',', 3 ),
							ProductsOptions[]		= DETool.toStringArray( "&Criteria, &Print Records, &Done", ',', 3 );

		public DEButtons 	forSearch		= DEButtons( 1, 0, 3, 3 ),
									forValidate	= DEButtons( 1, 0, 3, 3 ),
									forEdit			= DEButtons( 1, 0, 3, 3 ),
			 						forAddNew	= DEButtons( 1, 0, 3, 3 ),
			 						forProducts	= DEButtons( 1, 0, 3, 3 );
	}
}

