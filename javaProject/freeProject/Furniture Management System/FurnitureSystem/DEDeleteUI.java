
public class DEDeleteUI extends DEObjects {

	public DEChoice	AllDBTable = DataForDBTable();

	private DEChoice DataForDBTable() {
		DEChoice aDEChoice = new DEChoice( 15 ).setDELabel( new DELabel( "Table" ) );
		String Items[] = DETool.toStringArray( "Customer, Employee, Order, Sales Team, Product, Type", ',', 6 );
		aDEChoice.setModel( new javax.swing.DefaultComboBoxModel( Items ) );
		return aDEChoice;
	}

	public DETextArea SearchSQL		= new DETextArea( 4, 30 ).setDELabel( new DELabel( "SQL" ) );

	public String AllSearchSQL[] ={ 	"Select " +
														"Customer.CustomerID, " +
														"Customer.CustomerName " +
													"From " +
														"Customer " +
													"Where " +
														"Customer.CustomerID Not In " +									//Customer
															" ( Select " +
																"Customer.CustomerID " +
															"From " +
																"Customer, " +
																"[Order] " +
															"Where " +
																"Customer.CustomerID = Order.CustomerID ) ",

													"Select " +
														"Employee.StaffID, " +
														"Employee.StaffName " +
													"From " +
														"Employee " +
													"Where " +
														"Employee.Department Like 'Sales' And " +						//Employee
														"Employee.StaffID Not In " +
															"( Select " +
																"Distinct Customer.StaffID " +
															"From " +
																"Customer ) Or " +
														"Employee.Department Not Like 'Sales' ",

													"Select " +
														"Order.OrderID, " +
														"Order.CustomerID " +
													"From " +
														"[Order] " +
													"Where " +
														"Order.OrderID Not In " +												//Order
															"( Select " +
																"Distinct OrderLine.OrderID " +
															"From [Order Line] OrderLine ) ",

													"Select " +
														"SalesTeam.TeamID, " +
														"SalesTeam.SalesTeamInfo " +
													"From " +
														"[Sales Team] SalesTeam " +											//SalesTeam
													"Where " +
														"SalesTeam.TeamID Not In " +
															"( Select " +
																"Employee.TeamID " +
															"From " +
																"Employee " +
															"Where " +
																"Employee.Department Like 'Sales' ) ",

													"Select " +
														"Product.ProductID, " +
														"Product.ProductName " +
													"From " +
														"Product " +																	//Product
													"Where " +
														"Product.ProductID Not In " +
															"( Select " +
																"Distinct OrderLine.ProductID " +
															"From [Order Line] OrderLine ) ",

													"Select " +
														"Type.TypeID, " +
														"Type.TypeInfo " +
													"From " +
														"Type " +
													"Where " +
														"Type.TypeID Not In " +
															"( Select " +
																"Distinct Product.TypeID " +									//Type
															"From Product ) And " +
														"Type.TypeID Not In " +
															"( Select " +
																"Distinct Customer.TypeID " +
															"From Customer ) "
											};


	private DETableUI TableUI = new DETableUI();
	private DETableUI.DETable tblDBTable() {
		DETableUI.DETable table = TableUI.new DETable ()
		{	public boolean isCellEditable (int iRows, int iCols)
			{	return false;	//Disable All Columns of Table.
			}
		};
		table.setPreferredScrollableViewportSize( new java.awt.Dimension( 100, 150 ) );
		return table;
	}
	public DETableUI.DETable DBTable = tblDBTable();

	public String TableOptions[] 	= DETool.toStringArray( "&Delete Record, &Cancel", ',', 2 );

	public UI.DEButtons forTable	= new UI().new DEButtons( 1, 0, 3, 3 );
}