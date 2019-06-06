import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
import javax.swing.JToolBar;
import java.awt.LayoutManager;

public class DEMenuUI extends DEObjects {
	public class DEToolBar extends JToolBar {
		public DEToolBar() {
			super();
		}
	}

	//=======================================================
	public DEToolBar DEToolBar() {
		return new DEToolBar();
	}

	public DEButton DEButton( String text, Icon icon ) {
		return new DEButton( text, icon);
	}

	public DEPanel DEPanel( LayoutManager layoutManager, boolean haveBorder ) {
		return new DEPanel( layoutManager, haveBorder );
	}

	public DELabel DELabel( String text ) {
		return new DELabel( text );
	}
	//=====================================================================
	public class FurnitureSystem {
		public String Title = "Furniture System";
		public String IconAddress = "Images/network.gif";

		public Icon ToolBarButtonImages[]	= 	{	new ImageIcon ( "icon/AddNew.jpg" ),
																	new ImageIcon ( "icon/SearchRecord.jpg" ),
																	new ImageIcon ( "icon/SearchOrder.jpg" ),
																	new ImageIcon ( "icon/ListProduct.jpg" ),
																	new ImageIcon ( "icon/DeleteRecord.jpg" ),
																	new ImageIcon ( "icon/Frequency.jpg" ),
																	new ImageIcon ( "icon/TeamPerformance.jpg" ),
																	new ImageIcon ( "icon/ChangePassword.jpg" ),
																	new ImageIcon ( "icon/LogOff.jpg" ),
																	new ImageIcon ( "icon/Quit.jpg" )
																};

		public String ToolBarButtonToolTipTexts[] = {	"Add New",
																			"Search Record",
																			"Search Order",
																			"List Product",
																			"Delete Record",
																			"Frequency",
																			"Team Performance",
																			"Change Password",
																			"Log Off",
																			"Quit"
																		};

		public boolean ToolBarButtonSeparators[] 	= { false, false, true, true, true, false, true, false, true, false };

		//============================================================================
	}
}
