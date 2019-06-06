import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class DEDelete extends DEForm implements ActionListener, ItemListener {
	private DEDeleteUI uiDesigned = new DEDeleteUI();
	private DETableUI TableUI = new DETableUI();
	private DETableUI.ResultsModel	ResultsModel = TableUI.new ResultsModel();
	private DETableUI.DETable DBTable = uiDesigned.DBTable;

	private DEDeleteUI.DEChoice	AllDBTable = uiDesigned.AllDBTable;

	private DEDeleteUI.DETextArea SearchSQL = uiDesigned.SearchSQL;

	private UI.DEButtons 	forTable = uiDesigned.forTable.add( uiDesigned.TableOptions, this );

	private String SecurityMode = "High";
	private FurnitureSystem TheSystem;
	public DEDelete( FurnitureSystem theSystem ) {
		super();
		TheSystem = theSystem;
		//==================================================
		gridx( 0 ); fill( BOTH ); gridwidth( 2 );insets( 0, 0, 3, 0);
			gridy( 0 ); add( Header );

		insets( 3, 6, 6, 6);
			gridy( 3 ); add( DBTable.withScrollbar() );
				DBTable.setModel( ResultsModel );
			gridy( 4 ); add( forTable );

		fill(  NONE ); gridwidth( 1 ); anchor( EAST ); insets( 3, 6, 3, 3);
			gridy( 1 ); add( AllDBTable.FLabel );
			gridy( 2 ); add( SearchSQL.FLabel );

		gridx( 1 ); anchor( WEST); insets( 3, 3, 3, 6);
			gridy( 1 ); add( AllDBTable );
			gridy( 2 ); add( SearchSQL.withScrollbar() );

			AllDBTable.addItemListener( this );

		validate();
	}

	public DEDelete toResult() {
		Header.setText( "Search Result" );
		AllDBTable.setVisible( true );
		SearchSQL.setVisible( true );
			SearchSQL.setEnabled( false );
		DBTable.setVisible( true );
		forTable.setVisible( true );
		doResult();
		return this;
	}

	private synchronized void doResult() {
		String TheAllDBTable = (String) AllDBTable.getSelectedItem();
		SearchSQL.setText( uiDesigned.AllSearchSQL[ AllDBTable.getSelectedIndex() ] );

		int RecordCount = SQLTool.RecordCount( SearchSQL.getText() );

		dbODBC odbc = new dbODBC( TheSystem.getConnection() );
		odbc.executeQuery( SearchSQL.getText() );

		ResultsModel.setResultSet( odbc.getResultSet(), RecordCount );

		DBTable.AdjustColumnWidths();
	}

	private void toDelete() {
		AllDBTable.setVisible( true );
		SearchSQL.setVisible( true );
		SearchSQL.setEnabled( false );
		DBTable.setVisible( true );
		forTable.setVisible( true );
		doDelete();
	}

	private void doDelete() {
		String TheAllDBTable = (String) AllDBTable.getSelectedItem();
		int RowCount = DBTable.getRowCount();
		int SelectedRow = DBTable.getSelectedRow();
		dbODBC odbc = new dbODBC( TheSystem.getConnection() );
		int ColumnIndex;
		if ( RowCount > 0 ) {
			if ( TheAllDBTable.equals( "Customer" ) ) {
				if ( TheSystem.getUserDepartment().equals( "Sales" ) || TheSystem.getUserDepartment().equals( "SalesL" ) ) {
					ColumnIndex = odbc.findColumn( "CustomerID" );
					if ( ColumnIndex >= 0 && SelectedRow >= 0 ) {
						String TheCustomerID = (String) DBTable.getValueAt( SelectedRow, ColumnIndex-1 );
						odbc.executeUpdate( "Delete From Customer Where Customer.CustomerID = '" + TheCustomerID + "' ");
					}
				}
				else {
					JOptionPane.showMessageDialog	( 	this, "Only User from Sales or SalesL Department has permission to Delete the Customer Record",
																		"Record NOT DELETE", JOptionPane.WARNING_MESSAGE );
				}
			}
			else if ( TheAllDBTable.equals( "Employee" ) ) {
				if ( TheSystem.getUserDepartment().equals( "HR" ) ) {
					ColumnIndex = odbc.findColumn( "StaffID" );
					if ( ColumnIndex >= 0 && SelectedRow >= 0 ) {
						String TheStaffID = (String) DBTable.getValueAt( SelectedRow, ColumnIndex -1);
						System.out.println( ColumnIndex );
						odbc.executeUpdate( "Delete From Employee Where StaffID = '" + TheStaffID +  "' ");
					}
				}
				else {
					JOptionPane.showMessageDialog	( 	this, 	"Only User from HR Department has permission to Delete the Employee Record",
																				"Record NOT DELETE", JOptionPane.WARNING_MESSAGE );
				}
			}
			else if ( TheAllDBTable.equals( "Order" ) ) {
				ColumnIndex = odbc.findColumn( "OrderID" );
				if ( ColumnIndex >= 0 && SelectedRow >= 0 ) {
					String TheOrderID = (String) DBTable.getValueAt( SelectedRow, ColumnIndex -1 );
					odbc.executeUpdate( "Delete From [Order] Where OrderID = '" + TheOrderID + "' ");
				}
			}
			else if ( TheAllDBTable.equals( "Sales Team" ) ) {
				ColumnIndex = odbc.findColumn( "TeamID" );
				if ( ColumnIndex >= 0 && SelectedRow >= 0 ) {
					String TheTeamID = (String) DBTable.getValueAt( SelectedRow, ColumnIndex -1);
					System.out.println( TheTeamID );
					odbc.executeUpdate( "Delete From [Sales Team] Where TeamID = '" + TheTeamID + "' ");
				}
			}
			else if ( TheAllDBTable.equals( "Product" ) ) {
				if ( TheSystem.getUserDepartment().equals( "Stock" ) ) {
					ColumnIndex = odbc.findColumn( "ProductID" );
					if ( ColumnIndex >= 0 && SelectedRow >= 0 ) {
						String TheProductID = (String) DBTable.getValueAt( SelectedRow, ColumnIndex -1 );
						odbc.executeUpdate( "Delete From Product Where ProductID = '" + TheProductID + "' ");
					}
				}
				else {
					JOptionPane.showMessageDialog	( 	this, 	"Only User from Stock Department has permission to Delete the Product Record",
																				"Record NOT DELETE", JOptionPane.WARNING_MESSAGE );
				}
			}
			else if ( TheAllDBTable.equals( "Type" ) ) {
				ColumnIndex = odbc.findColumn( "TypeID" );
				if ( ColumnIndex >= 0 && SelectedRow >= 0 ) {
					String TheTypeID = (String) DBTable.getValueAt( SelectedRow, ColumnIndex -1 );
					odbc.executeUpdate( "Delete From Type Where TypeID = '" + TheTypeID + "' ");
				}
			}
		}
		else
			JOptionPane.showMessageDialog	( 	this, 	"Only one row can be Selected",
																		"Record NOT DELETE", JOptionPane.WARNING_MESSAGE );

		doResult();
	}

	private void toCancel() {
		setVisible( false );
	}


	public void actionPerformed( ActionEvent event ) {
		System.out.println( event.getActionCommand() );
		String command = event.getActionCommand();
		if ( command.equals( "Delete Record" ) )
			toDelete();
		else if ( command.equals( "Cancel" ) )
			toCancel();
	}

	//=======================itemListener=================================
	public void itemStateChanged( ItemEvent event ) {
		doResult();
	}
}