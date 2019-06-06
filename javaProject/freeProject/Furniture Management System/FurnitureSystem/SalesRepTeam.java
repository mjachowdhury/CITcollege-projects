import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class SalesRepTeam extends DEForm implements ActionListener, ItemListener {
	private UI uiDesigned = new UI();
	private UI.SalesTeam 	SalesTeam	= uiDesigned.new SalesTeam();
	private DETableUI TableUI = new DETableUI();
	private DETableUI.ResultsModel	ResultsModel = TableUI.new ResultsModel();

	private UI.DEChoice 		AllTeamID 		= SalesTeam.AllTeamID;
	private UI.DETextField	SalesTeamInfo 	= SalesTeam.SalesTeamInfo;

	public DETableUI.DETable 	SalesTeamTable = SalesTeam.SalesTeamTable;

	private UI.DEButtons 	forTable 		= SalesTeam.forTable.add( SalesTeam.TableOptions, this ),
									forTable2 		= SalesTeam.forTable2.add( SalesTeam.Table2Options, this ),
									forPie			= SalesTeam.forPie.add( SalesTeam.PieOptions, this );

	private DrawPiePanel Pie = new DrawPiePanel( "Team Performance" );

	private Component 	group[]		= { AllTeamID, SalesTeamInfo },
									group1[] 	= { forTable, forTable2, forPie };

	private String SecurityMode = "High";
	private FurnitureSystem TheSystem;

	public SalesRepTeam( FurnitureSystem theSystem ) {
		super();
		TheSystem = theSystem;
		//==================================================
		gridx( 0 ); fill( BOTH ); gridwidth( 2 );insets( 0, 0, 3, 0);
			gridy( 0 ); add( Header );

		insets( 3, 6, 6, 6);
			gridy( 3 ); add( SalesTeamTable.withScrollbar() );
				SalesTeamTable.setModel( ResultsModel );
			gridy( 4 ); add( Pie );
			gridy( 5 ); add( group1 );

		fill(  NONE ); gridwidth( 1 ); anchor( EAST ); insets( 3, 6, 3, 3);
			gridy( 1 ); add( AllTeamID.FLabel );
			gridy( 2 ); add( SalesTeamInfo.FLabel );

		gridx( 1 ); anchor( WEST); insets( 3, 3, 3, 6);
			gridy( 1 ); add( AllTeamID );
			gridy( 2 ); add( SalesTeamInfo );

		AllTeamID.addItemListener( this );
		validate();
	}

	public SalesRepTeam toPerformance() {
		Header.setText( "Staff Performance" );

		show( group ); enable( group );
			SalesTeamInfo.setEnabled( false );
		hide( group1 );
			forTable.setVisible( true );
		Pie.setVisible( false );
		SalesTeamTable.setVisible( true );
		//========================================
			doPerfomance();
		//=========================================
		return this;
	}

	private void toTeamPerformance() {
		Header.setText( "Team Performance" );
		hide( group );
			forTable.setVisible( true );
		hide( group1 );
			forTable2.setVisible( true );

		SalesTeamTable.setVisible( true );
		Pie.setVisible( false );
		//========================================
		doTeamPerfomance();
		//=========================================
	}

	private void doTeamPerfomance() {
		dbODBC odbc = new dbODBC( TheSystem.getConnection() );
		String TheSQL = "Select " +
									"* " +
								"From " +
									"( Select " +
										"SalesTeam.TeamID, " +
										"SalesTeam.SalesTeamInfo, " +
										"Sum(OrderLine.UnitPrice*OrderLine.Quantity) As Total " +
									"From " +
										"[Sales Team] SalesTeam, " +
										"Employee, " +
										"Customer, " +
										"[Order], " +
										"[Order Line] OrderLine " +
									"Where " +
										"Order.OrderID = OrderLine.OrderID And " +
										"Customer.CustomerID = Order.CustomerID And " +
										"Employee.StaffID = Customer.StaffID And " +
										"SalesTeam.TeamID = Employee.TeamID " +
									"Group By " +
										"SalesTeam.TeamID, " +
										"SalesTeam.SalesTeamInfo ) " +
								"Order By " +
									"Total Desc";

		int RecordCount = SQLTool.RecordCount( TheSQL );

		odbc.executeQuery( TheSQL );

		ResultsModel.setResultSet( odbc.getResultSet(), RecordCount );


		int RowCount			= 	SalesTeamTable.getRowCount();
		int SelectColumn 	= 	SalesTeamTable.findColumn( "Total" );
		SalesTeamTable.validate();
		String Value;
		if ( SelectColumn >= 0 ) {
			for ( int a = 0; a< RowCount; a++ ) {
				Value = (String) SalesTeamTable.getValueAt( a, SelectColumn );
				SalesTeamTable.setValueAt( DETool.toCurrency( Value ), a, SelectColumn );
			}
		}
		SalesTeamTable.AdjustColumnWidths();
		SalesTeamTable.getColumn( SelectColumn ).setPreferredWidth( 150 );
	}

	private void doPerfomance() {
		String TheTeamID = (String) AllTeamID.getSelectedItem();
		dbODBC odbc = new dbODBC( TheSystem.getConnection() );
		odbc.executeQuery( 	"Select " +
											"SalesTeam.SalesTeamInfo " +
										"From " +
											"[Sales Team] SalesTeam " +
										"Where " +
											"SalesTeam.TeamID = '" + TheTeamID + "' "
									);
		if ( odbc.move( "Next" ) ) {
			SalesTeamInfo.setText(  odbc.getString( "SalesTeamInfo" ) );

			String TheSQL = "Select " +
										"* " +
									"From " +
										"( Select " +
											"Employee.StaffID, " +
											"Employee.StaffName, " +
											"Employee.Department, " +
											"Sum(OrderLine.UnitPrice*OrderLine.Quantity) As Total " +
										"From " +
											"Employee, " +
											"Customer, " +
											"[Order], " +
											"[Order Line] OrderLine " +
										"Where " +
											"Order.OrderID = OrderLine.OrderID And " +
											"Customer.CustomerID = Order.CustomerID And " +
											"Employee.StaffID = Customer.StaffID And " +
											"Employee.TeamID = '" + TheTeamID + "' " +
										"Group By " +
											"Employee.StaffID, " +
											"Employee.StaffName, " +
											"Employee.Department ) " +
									"Order By " +
										"Total Desc";


			int RecordCount = SQLTool.RecordCount( TheSQL );

			odbc.executeQuery( TheSQL );

			ResultsModel.setResultSet( odbc.getResultSet(), RecordCount );


			int RowCount			= 	SalesTeamTable.getRowCount();
			int SelectColumn 	= 	SalesTeamTable.findColumn( "Total" );
			SalesTeamTable.validate();
			String Value;
			if ( SelectColumn >= 0 ) {
				for ( int a = 0; a< RowCount; a++ ) {
					Value = (String) SalesTeamTable.getValueAt( a, SelectColumn );
					SalesTeamTable.setValueAt( DETool.toCurrency( Value ), a, SelectColumn );
				}
			}
			SalesTeamTable.AdjustColumnWidths();
			SalesTeamTable.getColumn( SalesTeamTable.findColumn( "StaffName" ) ).setPreferredWidth( 200 );
			SalesTeamTable.getColumn( SelectColumn ).setPreferredWidth( 150 );
		}
	}

	private void toDone() {
		setVisible( false );
	}

	private void toGraph() {
		hide( group );
		hide( group1 );
			SalesTeamTable.setVisible( false );
			forPie.setVisible( true );
		Pie.setVisible( true );
		Object AllValueOfSalesTeamInfo[]	= SalesTeamTable.getAllValueAtColumn( "SalesTeamInfo" );
		Object AllValueOfTotal[]		= SalesTeamTable.getAllValueAtColumn( "Total" );
		String notes[] = new String[ AllValueOfSalesTeamInfo.length ];
		int sector[] = new int[ AllValueOfTotal.length ];
		for( int a = 0; a < AllValueOfSalesTeamInfo.length; a++ ) {
			notes[ a ] = (String) AllValueOfSalesTeamInfo[ a ];
			sector[ a ] = (int) DETool.CurrencyToDouble( (String) AllValueOfTotal[ a ] );
		}
		Pie.addSector( sector, notes );
	}

	private void toPrint() {
		final ExtFrame PrintFrame 				= new ExtFrame();
		PrintFrame.setResizable( false );
		PrintFrame.setTitle( "Print Preview" );
		PrintFrame.getContentPane().setLayout( new java.awt.FlowLayout() );
		DEObjects Objects = new DEObjects();
		final DEObjects.DETextArea PrintPreview = Objects.new DETextArea( 20, 41 );

		String Text;
		if ( SalesTeamInfo.isVisible() ) {
			Text = "\t                       " +  SalesTeamInfo.getText() + " \t" + "\n" +
						"\n" +
						"Staff ID" + "\t" + "Staff Name" + "     \t\t " + "Department" + "\t" + "Total" + "\n";

			Object AllValueOfStaffID[]			= SalesTeamTable.getAllValueAtColumn( "StaffID" );
			Object AllValueOfStaffName[]		= SalesTeamTable.getAllValueAtColumn( "StaffName" );
			Object AllValueOfDepartment[]			= SalesTeamTable.getAllValueAtColumn( "Department" );
			Object AllValueOfTotal[]			= SalesTeamTable.getAllValueAtColumn( "Total" );

			int RowCount = AllValueOfStaffID.length;
			for ( int a = 0; a < RowCount; a++ ) {
				Text = Text + 	(String) AllValueOfStaffID[ a ] + " \t " + (String) AllValueOfStaffName[ a ] + "     \t ";
				if ( ((String) AllValueOfStaffName[ a ]).length() < 15 )
					Text = Text + "\t";
				Text = Text + (String) AllValueOfDepartment[ a ] +   " \t " + (String) AllValueOfTotal[ a ] +" \n";
			}

			PrintPreview.setText( Text );
			//PrintPreview.setEditable( false );
		}
		else {
			Text= "\t" + " Team Preformance " + " \t" + "\n" +
					"\n" +
					"Sales Team Info" + " \t" + "Total" + "\n";

			Object AllValueOfSalesTeamInfo[]		= SalesTeamTable.getAllValueAtColumn( "SalesTeamInfo" );
			Object AllValueOfTotal[]		= SalesTeamTable.getAllValueAtColumn( "Total" );

			int RowCount = AllValueOfSalesTeamInfo.length;
			for ( int a = 0; a < RowCount; a++ ) {
				Text = Text + AllValueOfSalesTeamInfo[ a ] + " \t\t " + (String) AllValueOfTotal[ a ] + " \n";
			}

			PrintPreview.setText( Text );
			//PrintPreview.setEditable( false );
		}
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

	private void toPrintPieChart() {
		DEPrint.printComponent( Pie );
	}

	private void toBack() {
		 toTeamPerformance();
	 }

	public void actionPerformed( ActionEvent event ) {
		System.out.println( event.getActionCommand() );
		String command = event.getActionCommand();
		if ( command.equals( "Done" ) )
			toDone();
		else if ( command.equals( "Graph" ) )
			toGraph();
		else if ( command.equals( "Team" ) )
			toTeamPerformance();
		else if ( command.equals( "Print" ) )
			toPrint();
		else if ( command.equals( "Member" ) )
			toPerformance();
		else if ( command.equals( "Print Pie Chart" ) )
			toPrintPieChart();
		else if ( command.equals( "Back" ) )
			toBack();
	}
	//=======================itemListener=================================
	public void itemStateChanged( ItemEvent event ) {
		doPerfomance();
	}
}