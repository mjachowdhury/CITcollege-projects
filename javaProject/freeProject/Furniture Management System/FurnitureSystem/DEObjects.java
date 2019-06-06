import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.Icon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;

public class DEObjects {
	public static Font font = new Font( "Verdana", 1, 13 );
	public static Color LabelFontColor = new Color( 70, 71, 88 );
	public static Color InputFontColor = new Color( 30, 60, 120 );
	public static Color BorderColor = DETool.BorderColor;
	public static int BorderBold = DETool.BorderBold;

	//===========================================================
	public class DEButton extends JButton {
		public DEButton( String text, Icon icon ) {
			super( text, icon );
			setFont( font );
			DETool.setMnemonicFor( this );
			setForeground( LabelFontColor );
		}

		public Insets getMargin() {
			return new Insets( 0, 5, 1, 5 );
	    }
	}
	//===========================================================
	public class DELabel extends JLabel {
		public DELabel( String text ) {
			this( text, CENTER );
		}

		public DELabel(String text, int horizontalAlignment) {
			super(text, null, horizontalAlignment);
			setFont( font );
			setForeground( LabelFontColor );
		}
	}
	//============================================================
	public class DETextField extends JTextField {
		public DELabel FLabel;

		public DETextField( int column ) {
			super( column );
			setFont( font );
			setForeground( InputFontColor );
		}
		//====================================================
		public DETextField setDELabel( DELabel label ) {
			FLabel = label;
			return this;
		}

		public void setVisible( boolean visible ) {
			super.setVisible( visible );
			FLabel.setVisible( visible );
		}
	}
	//=============================================================
	public class DEPasswordField extends JPasswordField {
		public DELabel FLabel;
		private String SecureObject = " FuRnItUrE !  ";
		private String KeySecure = "";

		public DEPasswordField( int column ) {
			super( column );
			setFont( font );
			setForeground( InputFontColor );
		}

		public DEPasswordField setKeySecure( String keySecure ) {
			KeySecure = keySecure;
			return this;
		}

		public void setText( String text ) {
			super.setText( "" );
		}

		public String getPasswordText() {
			char[] Password = this.getPassword();
			String Text = "";
			if ( Password != null )
				for( int a = 0; a < Password.length; a++ )
					Text = Text + Password[ a ];
			return Text;
		}

		public char[] getPassword() {
			if ( KeySecure.equals( SecureObject ) )
				return super.getPassword();
			return null;
		}

		public String getText() {
			return "";
		}

		public String getText(int offs, int len) {
			return "";
    	}

    	public Dimension getPreferredSize() {
			DETextField temp = new DETextField( getColumns() );
			add(temp);
			Dimension PreferredSize = new Dimension( 	(int) temp.getPreferredSize().getWidth(),
														(int) super.getPreferredSize().getHeight()
													);
			remove( temp );
			return PreferredSize;
		}
    	//===================================================================
    	public DEPasswordField setDELabel( DELabel label ) {
			FLabel = label;
			return this;
		}

		public void setVisible( boolean visible ) {
			super.setVisible( visible );
			FLabel.setVisible( visible );
		}


	}
	//===============================================================
	public class DEChoice extends JComboBox {
		private int Column;
		public DELabel FLabel;

		public DEChoice() {
			super();
			setFont( font );
			setForeground( InputFontColor );
		}

		public DEChoice( int column ) {
			this();
			Column = column;
		}

		public Dimension getPreferredSize() {
			DETextField temp = new DETextField( Column );
			add(temp);
			Dimension PreferredSize = new Dimension	( 	(int) temp.getPreferredSize().getWidth(),
																				(int) super.getPreferredSize().getHeight()
										  		 							);
			remove( temp );
			return PreferredSize;
		}

		public void select( String item ) {
			setSelectedItem( (Object) item );
		}

		public void select( int index ) {
			setSelectedIndex( index );
		}

		//========================================================
		public DEChoice setDELabel( DELabel label ) {
			FLabel = label;
			return this;
		}

		public void setVisible( boolean visible ) {
			super.setVisible( visible );
			FLabel.setVisible( visible );
		}
	}
	//==============================================================
	public class DETextArea extends JTextArea {
		private DEScrollPane ScrollBar = new DEScrollPane( this );
		public DELabel FLabel;

		public DETextArea( int row , int column ) {
			super( row, column );
			setLineWrap( true );
			setWrapStyleWord( true );
			setFont( font );
			setForeground( InputFontColor );
			setTabSize( 3 );
		}

		public Insets getMargin() {
			JTextField temp = new JTextField();
			return temp.getMargin();
	    }

		public Dimension getPreferredScrollableViewportSize() {
			DETextField temp = new DETextField( getColumns() );
			add(temp);
			Dimension Size = new Dimension( (int) temp.getPreferredSize().getWidth() - 2,
											(int) getPreferredSize().getHeight()
										);
			remove( temp );
			return Size;
		}

		public DEScrollPane withScrollbar() {
			return ScrollBar;
		}

		//==================================================
		public DETextArea setDELabel( DELabel label ) {
			FLabel = label;
			return this;
		}

		public void setVisible( boolean visible ) {
			ScrollBar.setVisible( visible );
			FLabel.setVisible( visible );
			super.setVisible( visible );
		}
	}
	//================================================================
	public class DEScrollPane extends JScrollPane {
		public DEScrollPane( Component view ) {
			this( view, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER );
		}

		public DEScrollPane( Component view, int vsbPolicy, int hsbPolicy ) {
			super( view, vsbPolicy, hsbPolicy );
		}
	}
	//====================================================================
	public static class DEPanel extends JPanel {
		public DEPanel( LayoutManager layoutManager, boolean haveBorder ) {
			super( layoutManager );
			if ( haveBorder )
				setBorder( BorderFactory.createLineBorder( BorderColor, BorderBold ) );
		}
	}
	//===================================================================
	DEObjects() { };
}