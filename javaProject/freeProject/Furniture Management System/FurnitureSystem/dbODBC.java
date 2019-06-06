import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
public class dbODBC {
	private String Database;
	private Connection Conn;
	private Statement Sttmnt;
   	private static ResultSet RstSt;
   	private String currentSQL;
   	private static String FileName;

   	public dbODBC() {
		this( DETool.getDatabaseFileName() );
	}

   	public dbODBC( String databaseFileName  ) {
		getConnection( databaseFileName );
	}

	public dbODBC( Connection conn ) {
		Conn = conn;
		DETool.getDatabaseFileName();
		try {
			Sttmnt = Conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );
		}
		catch( Exception exception ) {}
	}

	private synchronized void getConnection( String databaseFileName ) {
		Database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=";
		try {
			Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
			FileName = databaseFileName;
			Database += FileName.trim() + ";DriverID=22;READONLY=true}";
			Conn = DriverManager.getConnection( Database ,"","");
			Conn.setAutoCommit( true );
			Sttmnt = Conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );
		}
		catch( Exception exception ) {
			System.out.println
			( "Error in dbODBC.declaration..."
			+ "\n" + exception.getMessage() );
			getConnection( databaseFileName );
		}
	}

	public void executeUpdate( String SQL ) {
		try {
			Sttmnt.executeUpdate( SQL );
			currentSQL = SQL;
		}
		catch( Exception exception ) {
			System.out.println
			( "Error in dbODBC.executeUpdate()..."
			+ "\n" + exception.getMessage() );
		}
	}

	public synchronized void executeQuery( String SQL ) {
		try {
			RstSt = Sttmnt.executeQuery( SQL );
			currentSQL = SQL;
		}
		catch( Exception exception ) {
			System.out.println
			( "Error in dbODBC.executeQuery()..."
			+ "\n" + exception.getMessage() );
		}
	}

	public Connection getConnection() {
		return Conn;
	}

	public void close() {
		try {
			Conn.close();
		}
		catch ( Exception exception ) {}
	}

	public String getCurrentSQL() {
		return currentSQL;
	}

	public int findColumn( String fieldName ) {
		try {
			return RstSt.findColumn( fieldName );
		}
		catch( Exception exception ) {
			System.out.println
			( "Error in dbODBC.findColumn..."
			+ "\n" + exception.getMessage() );
			return -1;
		}
	}

	public static String getFilename() {
		return FileName;
	}

	public ResultSet getResultSet() {
		return RstSt;
	}

	public boolean move( String direction ) {
		boolean Moved=false;
		try {
			if ( direction.equals( "Next" ) ) {
				if ( !RstSt.isLast() ) {
					if ( RstSt.next() )	Moved = true;
				}
			}
			else if ( direction.equals( "Previous" ) ) {
				if ( !RstSt.isFirst() ) {
					if ( RstSt.previous() )	Moved = true;
				}
			}
			else if ( direction.equals( "First" ) ) {
				if ( RstSt.first() ) 	Moved = true;
			}
			else if ( direction.equals( "Last" ) ) {
				if ( RstSt.last() )	Moved = true;
			}
		}
		catch( Exception exception ) {
			System.out.println
			( "Error in dbODBC.Move( " + direction + " )..."
			+ "\n" + exception.getMessage() );
		}
		return Moved;
	}


	public java.sql.Date getDate( String fieldName ) {
		try {
			return RstSt.getDate( fieldName );
		}
		catch( Exception exception ) {
			System.out.println
			( "Error in dbODBC.getDate( " + fieldName + " )..."
			+ "\n" + exception.getMessage() );
			return null;
		}
	}

	public String getString( String fieldName ) {
		try {
			return RstSt.getString( fieldName );
		}
		catch( Exception exception ) {
			System.out.println
			( "Error in dbODBC.getString( " + fieldName + " )..."
			+ "\n" + exception.getMessage() );
			return "";
		}
	}

	public String getString( int columnIndex ) {
		try {
			return RstSt.getString( columnIndex );
		}
		catch( Exception exception ) {
			System.out.println
			( "Error in dbODBC.getString( " + columnIndex + " )..."
			+ "\n" + exception.getMessage() );
			return "";
		}
	}
}