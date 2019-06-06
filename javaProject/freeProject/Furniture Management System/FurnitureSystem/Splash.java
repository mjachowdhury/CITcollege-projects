ximport javax.swing.JWindow;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Insets;
import java.awt.Dimension;

public class Splash extends JWindow {
	private Dimension ScreenSize = DETool.getScreenSize();
	public Splash () {
		super();
		JLabel SystemLogo = new JLabel ( new ImageIcon ( "icon/SystemLogo.jpg" ) );
		getContentPane().add ( SystemLogo, "Center" );
		pack();
		setLocation ( ScreenSize.width / 2 - getWidth() / 2, ScreenSize.height / 2 - getHeight() / 2);
		show();
		for (int i = 0; i <= 100000; i++) { }
		ExtFrame SalesRepCustomerFrame 			= new ExtFrame();
		DEContainer SalesRepCustomerContainer 	= new DEContainer( new Insets( 6, 6, 6, 6 ), SalesRepCustomerFrame  );
		SalesRepCustomer SalesRepCustomerUI 	= new SalesRepCustomer().toLog( false );
		SalesRepCustomerContainer.add( SalesRepCustomerUI );
		SalesRepCustomerFrame.add( SalesRepCustomerContainer );
		SalesRepCustomerUI.validate();
		SalesRepCustomerFrame.pack();

		SalesRepCustomerFrame.toFront();
		hide();
		dispose();
	}
	public static void main ( String args[] ) {
		new Splash ();
	}
}