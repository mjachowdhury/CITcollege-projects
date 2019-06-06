import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.BorderFactory;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Insets;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DETableUI extends DEObjects {
	private dbODBC odbc = new dbODBC();
	public DefaultTableModel TableModel;
	private boolean usingResultModel = false;
	public class DETable extends JTable {
		private DEScrollPane
		ScrollBar = new DEScrollPane	( 	this,
														DEScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
														DEScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
													);

		public DETable() {
			super();
			setColumnSelectionAllowed( false );
			setRowHeight( 25 );
			getTableHeader().setDefaultRenderer( new LabelRenderer( true ) );
			//setDefaultRenderer( Object.class, new LabelRenderer( false ) );
			setAutoResizeMode( AUTO_RESIZE_OFF );
			setForeground( LabelFontColor );
		}

		public DETable( Object row[][], Object column[] ) {
			this( TableModel = new DefaultTableModel( row, column ) );
		}

		public DETable( TableModel tableModel ) {
			this();
			setModel( tableModel );
		}

		public void AdjustColumnWidths() {
			int ColumnCount = getColumnCount();
			int ColumnNameLength = 0;
			for ( int a = 0; a < ColumnCount; a++ ) {
				ColumnNameLength = getColumnName( a ).length();
				getColumn( a ).setPreferredWidth( (ColumnNameLength*14) + ColumnNameLength );
			}
		}

		public int findColumn( String ColumnName ) {
			int ColumnCount = getColumnCount();
			for( int a = 0; a < ColumnCount; a++ ) {
				if ( getColumnName( a ).equals( ColumnName ) )
					return a;
			}
			return -1;
		}

		public Font getFont() {
			return font;
		}

		public Color getGridColor() {
			return DETool.useColor;
		}

		public void addRow() {
			TableModel.addRow( new String[ getColumnCount() ] );
		}

		public void removeRow(int row) {
			TableModel.removeRow( row );
		}

		public DEScrollPane withScrollbar() {
			return ScrollBar;
		}

		public TableColumn getColumn( int index ) {
			return getColumn( getColumnName( index ) );
		}

		public Object[] getAllValueAtColumn( String ColumnName ) {
			int RowCount = getRowCount();
			int SelectColumn = findColumn( ColumnName );
			Object AllValues[] = null;
			if ( RowCount > 0 && SelectColumn >= 0 ) {
				AllValues = new Object[ RowCount ];
				for( int a = 0; a < RowCount; a++ ) {
					AllValues[ a ] = this.getValueAt( a, SelectColumn );
				}
			}
			return AllValues;
		}

		public void setVisible( boolean visible ) {
			super.setVisible( visible );
			ScrollBar.setVisible( visible );
		}
	}

	public class LabelRenderer extends DELabel implements TableCellRenderer {
		public LabelRenderer( boolean border ) {
			super( "" );
			if ( border ) {
				setHorizontalAlignment( CENTER );
				setBorder( BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "" ) );
			}
			else
				setHorizontalAlignment( RIGHT );
		}

		public Dimension getPreferredSize() {
			DELabel temp = new DELabel( getText() );
			add( temp );
			Dimension Size = new Dimension	( 	(int)200 ,
												(int)temp.getPreferredSize().getHeight() * 4 / 3
											);

			remove( temp );
			return Size;
		}

		public Component getTableCellRendererComponent(	JTable jTable, Object obj, boolean isSelected,
														boolean hasFocus, int row, int column) {
			setText((String) obj );
			return this;
		}
	}

	class ResultsModel extends DefaultTableModel  {
		public void setResultSet( ResultSet results, int recordCount ) {
			try {
				ResultSetMetaData metadata = results.getMetaData();

				int columns =  metadata.getColumnCount();    	// Get number of columns

				// Get the column names
				String columnIdentifiers[] = new String[ columns ];
				for( int i = 0; i < columns; i++ )
					columnIdentifiers[ i ] = metadata.getColumnName( i + 1 );

				// Get all rows.
				String dataVector[][] = new String[ recordCount ][ columns ];                     // New Vector to store the data
				for ( int a = 0; a < recordCount; a++ ) {
					if ( results.next() ) {
						for( int i = 0; i < columns; i++ ) {          // For each column
							dataVector[ a ][ i ] = results.getString( columnIdentifiers[ i ] );    // retrieve the data item
						}
					}
				}
				setDataVector( dataVector, columnIdentifiers );
			  	fireTableChanged( null );           // Signal the table there is new model data
			}
			catch ( SQLException sqle ) {
					System.err.println( sqle );
					fireTableChanged( null );
			}

		}
	}
}