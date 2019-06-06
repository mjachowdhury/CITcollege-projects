import java.awt.Component;
import java.awt.Insets;
import java.awt.Color;
import java.util.Calendar;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.AbstractButton;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DETool {
	public static final Color useColor = new Color( 140, 170, 230 );
	public static final Color BorderColor = useColor;
	public static final int BorderBold = 1;

	public static void setMnemonicFor( DEObjects.DEButton button ) {
		String text = button.getText();
		int index = button.getText().indexOf( '&' );

		if ( index >= 0 )
			button.setMnemonic( text.charAt( text.indexOf( '&' ) + 1 ) );
		button.setText( replaceFirst( text, '&' ) );
	}

	public static void setMnemonicFor( AbstractButton button ) {
		String text = button.getText();
		int index = button.getText().indexOf( '&' );

		if ( index >= 0 )
			button.setMnemonic( text.charAt( text.indexOf( '&' ) + 1 ) );
		button.setText( replaceFirst( text, '&' ) );
	}

	private static String replaceFirst( String text, char subject ) {
		String new_text = "";
		String old_text	= text.trim();
		int index = text.indexOf( subject );

		if ( index == 0 )
			new_text = old_text.substring( index + 1, old_text.length() );
		else if ( index > 0 )
			new_text = old_text.substring( 0, index ).concat( old_text.substring( index + 1, old_text.length() ) );
		else
			new_text = old_text;

		return new_text;
	}

	public static void coloringHeader( DEObjects.DELabel DELabel ) {
		DELabel.setOpaque( true );
		DELabel.setBackground( useColor );
		DELabel.setBorder( BorderFactory.createLineBorder( useColor , 1 ) );
		DELabel.setForeground( Color.white );
	}

	public static String[] toStringArray( String referText, char splitWith, int arrayLength ) {
		String temp[] = new String[ arrayLength ];
		for ( int a = 0; a < temp.length; a++ )
			temp[ a ] = referText.substring( indexOf( referText, splitWith, a ) + 1,
										indexOf( referText, splitWith, a + 1 )
									   ).trim();
		return temp;
	}

	private static int indexOf( String referText, char searchFor, int skip ) {
		int index = 0;
		int a = 0;
		int passed = 0;
		if ( skip != 0 ) {
			for ( a = 0; a < referText.length(); a++ ) {
				index = a;
				if ( referText.charAt( a ) == searchFor )
				{	passed++;
					if ( passed == skip )
						break;
				}
			}
			index = a;
		}
		else
			index = -1;

		return index;
	}

	public static String TodayDate = getTodayDate();

	private static String getTodayDate() {
		Date currDate 	= new Date();
		SimpleDateFormat dateFormat 	= new SimpleDateFormat ( "dd/MM/yyyy", Locale.getDefault() );
		return dateFormat.format ( currDate );
	}

	public static Dimension getScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}

	public static boolean isNumber( String text ) {
		for ( int a = 0; a < text.length(); a++ )
			if ( !Character.isDigit( text.charAt( a ) ) )
				return false;
		return true;
	}

	public static boolean isAlphaNumeric ( String text ) {
		for ( int a = 0; a < text.length(); a++ )
			if ( !Character.isDigit( text.charAt( a ) ) )
				if ( !Character.isLetter( text.charAt( a ) ) )
					return false;
		return true;
	}

	public static boolean isAlphabet ( String text ) {
		for ( int a = 0; a < text.length(); a++ )
			if ( Character.isDigit( text.charAt( a ) ) || !Character.isLetter( text.charAt( a ) ) )
					return false;
		return true;
	}

	public static String getDatabaseFileName() {
		return "IAD Project.mdb";
	}

	public static int parseQuantity( int value, String text ) {
		int result = value;
		if ( Character.isDigit( text.charAt( 0 ) ) )
			return Integer.parseInt( text );
		else if ( text.length() > 1  ) {
			if ( isNumber( text.substring( 1, text.length() ) ) ) {
				int value2 = Integer.parseInt( text.substring( 1, text.length() ) );
				if ( text.startsWith( "+" ) )
					result += value2;
				else if ( text.startsWith( "-" ) )
					result -= value2;
			}
		}
		return result;
	}

	public static String toCurrency( double Value ) {
		String StringValue = Double.toString( Value );
		if ( StringValue.charAt( StringValue .length() - 2 ) == '.' )
			return ( "RM " + StringValue  + "0" );
		else
			return ( "RM " + StringValue );
	}

	public static String toCurrency( String Value ) {
		double doubleValue = Double.parseDouble( Value );
		return toCurrency( doubleValue );
	}

	public static double CurrencyToDouble( final String Value ) {
		String TheValue = Value;
		int DotCount = 0;
		String StringValue = "";
		double DoubleValue = 0.00;

		for ( int a = TheValue.length() - 1; a >= 0; a-- ) {
			if ( DotCount > 1 ) break;
			if ( Character.isDigit( TheValue.charAt( a ) ) || TheValue.charAt( a ) == '.' ) {
				StringValue = TheValue.charAt( a ) + StringValue;
				if ( TheValue.charAt( a ) == '.' ) DotCount++;
			}
			else
				break;
		}
		if ( ! StringValue.equals( "" ) )
			DoubleValue = Double.parseDouble( StringValue );

		return DoubleValue;
	}
}

class SQLTool extends DETool {
	public static int RecordCount( String SQL ) { //for RecordCount
		dbODBC odbc = new dbODBC();
		String TheSQL = SQL;
		int Total=0;
		odbc.executeQuery( TheSQL );
		while ( odbc.move( "Next" ) ) {
			try {
				odbc.getResultSet().getString( 1 );
			}
			catch ( Exception exception ) { break; }
			Total++;
		}
		return Total;
	}

	public static String[] getStrings( String fieldName, int indexCol, String SQL ) {
		//for Combobox, choice, list, Grouping data
		dbODBC odbc = new dbODBC();
		String DataGroup[] = new String[ RecordCount( SQL ) ];
		Object tmp;
		int row = 0;
		int indexOf = -1;
		odbc.executeQuery( SQL );
		if ( ! fieldName.equals("") ) {
			indexOf = odbc.findColumn( fieldName );
		}

		if ( indexOf == -1 ) {
			indexOf = indexCol;
		}

		while ( odbc.move( "Next" ) ) {
			DataGroup[ row++ ] = odbc.getString( indexOf ).toString();
		}
		return DataGroup;
	}

	public static boolean CheckRecordExists( dbODBC odbc, String SQL ) {
		odbc.executeQuery( SQL );
		if ( odbc.move( "Next" ) )
			return true;
		System.out.println( "Error on SQLTool.isExist: Wrong Parameter." );
		return false;
	}
}

