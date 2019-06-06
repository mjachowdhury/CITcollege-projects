import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Insets;
import java.awt.Dimension;
import java.util.Calendar;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

public class FurnitureSystem extends MenuForm implements ActionListener, ItemListener {
	private DEMenuUI uiDesigned = new DEMenuUI();
	private DEMenuUI.FurnitureSystem aFurnitureSystem = uiDesigned.new FurnitureSystem();
	private Date currDate 	= new Date ();																				//Creating Object.
	private SimpleDateFormat dateFormat 	= new SimpleDateFormat ( "dd MMMM yyyy", Locale.getDefault() );	//Changing Format.
	private String DateString = dateFormat.format ( currDate );																			//Storing Date.
	private UIManager.LookAndFeelInfo Looks[] = UIManager.getInstalledLookAndFeels ();
	private DEObjects.DEChoice cboLookAndFeel = uiDesigned.new DEChoice();
	private String UserID, UserName, UserDepartment, SecurityMode = "High";
	//========================================================
	private IntFrame CustomerOrderFrame 				= new IntFrame( "Customer", true, false, true, false );
	private DEContainer CustomerOrderContainer 		= new DEContainer( new Insets( 6, 6, 6, 6 ), CustomerOrderFrame );
	private CustomerOrder CustomerOrderUI;
	//========================================================
	private IntFrame SalesRepCustomerFrame 			= new IntFrame( "SalesRepCustomer", true, false, true, false );
	private DEContainer SalesRepCustomerContainer = new DEContainer( new Insets( 6, 6, 6, 6 ), SalesRepCustomerFrame );
	private SalesRepCustomer SalesRepCustomerUI;
	//==========================================================
	private IntFrame TypeProductFrame 					= new IntFrame( "TypeProduct", true, false, true, false );
	private DEContainer TypeProductContainer 			= new DEContainer( new Insets( 6, 6, 6, 6 ), TypeProductFrame );
	private TypeProduct TypeProductUI;
	//==========================================================
	private IntFrame SalesRepTeamFrame 				= new IntFrame( "SalesRepTeam", true, false, true, false );
	private DEContainer SalesRepTeamContainer 		= new DEContainer( new Insets( 6, 6, 6, 6 ), SalesRepTeamFrame );
	private SalesRepTeam SalesRepTeamUI;
	//==========================================================
	private IntFrame DEDeleteFrame 				= new IntFrame( "Delete Record", true, false, true, false );
	private DEContainer DEDeleteContainer 		= new DEContainer( new Insets( 6, 6, 6, 6 ), DEDeleteFrame );
	private DEDelete DeleteUI;
	//==========================================================
	private Calendar LastTime = Calendar.getInstance();
	private int TheTime =  LastTime.get( LastTime.SECOND );
	private int TimeNow =  LastTime.get( LastTime.SECOND );

	private dbODBC odbc = new dbODBC();
	public FurnitureSystem( String userID, String userName, String userDepartment ) {
		super();
		setSize ( new Dimension ( 750, 560) );

		setVisible(true);
		try {
			java.awt.Robot rob = new java.awt.Robot();

			// move cursor to the top, left corner
			java.awt.Point p = this.getLocationOnScreen();
			rob.mouseMove(p.x + 5, p.y + 5);

			// press and release left mouse button
			rob.mousePress( java.awt.event.InputEvent.BUTTON1_MASK );
			rob.mouseRelease( java.awt.event.InputEvent.BUTTON1_MASK );

			// send an 'x' key press
			rob.keyPress( java.awt.event.KeyEvent.VK_X);

			rob.mouseMove( getWidth() / 2,  getHeight() / 2 );
		}

		catch( Exception exception ) { }
		setTitle( aFurnitureSystem.Title );
		setIconImage ( getToolkit().getImage( aFurnitureSystem.IconAddress ) );
		cboLookAndFeel.addItem( "1. Metal" );
		cboLookAndFeel.addItem( "2. Motif" );
		cboLookAndFeel.addItem( "3. Windows");
		cboLookAndFeel.addItemListener( this );

		setToolBarButton( 	aFurnitureSystem.ToolBarButtonImages,
									aFurnitureSystem.ToolBarButtonToolTipTexts,
									aFurnitureSystem.ToolBarButtonSeparators,
									this
								);
		String StatusBarTexts[] 				= { userName + " from " + userDepartment, " Today is " + DateString + " "};
		int StatusBarAlignments[]			= { DEMenuUI.DELabel.LEFT, DEMenuUI.DELabel.RIGHT };
		String StatusBarToolTipTexts[] 	= { "Program Title", "System Current Date" };
		String StatusBarLocations[] 		= { "West", "East" };

		setStatusBar( StatusBarTexts, StatusBarAlignments, StatusBarToolTipTexts, StatusBarLocations );
		getDEStatusBar().add( cboLookAndFeel, "Center" );

		try {
			CustomerOrderUI = new CustomerOrder( this );
			CustomerOrderContainer.add( CustomerOrderUI );
			CustomerOrderFrame.add( CustomerOrderContainer );
			CustomerOrderUI.validate();

			SalesRepCustomerUI = new SalesRepCustomer( this );
			SalesRepCustomerContainer.add( SalesRepCustomerUI );
			SalesRepCustomerFrame.add( SalesRepCustomerContainer );
			SalesRepCustomerUI.validate();

			TypeProductUI = new TypeProduct( this );
			TypeProductContainer.add( TypeProductUI );
			TypeProductFrame.add( TypeProductContainer );
			TypeProductUI.validate();

			SalesRepTeamUI = new SalesRepTeam( this );
			SalesRepTeamContainer.add( SalesRepTeamUI );
			SalesRepTeamFrame.add( SalesRepTeamContainer );
			SalesRepTeamUI.validate();

			DeleteUI = new DEDelete( this );
			DEDeleteContainer.add( DeleteUI );
			DEDeleteFrame.add( DEDeleteContainer );
			DeleteUI.validate();
		} catch (Exception exception ) { System.out.println("error " + exception); }

		UserID = userID;
		UserName = userName;
		UserDepartment = userDepartment;

		validate();
	}

	public String getUserID() {
		return UserID;
	}

	public String getUserName() {
		return UserName;
	}

	public String getUserDepartment() {
		return UserDepartment;
	}

	public String getSecurityMode() {
		return SecurityMode;
	}

	public java.sql.Connection getConnection() {
		return odbc.getConnection();
	}

	public void refresh( String userID, String userName, String userDepartment ) {
		UserID = userID;
		UserName = userName;
		UserDepartment = userDepartment;

		currDate 	= new Date ();																				//Creating Object.
		dateFormat 	= new SimpleDateFormat ( "dd MMMM yyyy", Locale.getDefault() );
		DateString  = dateFormat.format ( currDate );

		String StatusBarTexts[] 				= { userName + " from " + userDepartment, " Today is " + DateString + " "};
		int StatusBarAlignments[]			= { DEMenuUI.DELabel.LEFT, DEMenuUI.DELabel.RIGHT };
		String StatusBarToolTipTexts[] 	= { "Program Title", "System Current Date" };
		String StatusBarLocations[] 		= { "West", "East" };

		getDEStatusBar().removeAll();
		setStatusBar( StatusBarTexts, StatusBarAlignments, StatusBarToolTipTexts, StatusBarLocations );
		getDEStatusBar().add( cboLookAndFeel, "Center" );
		getDEStatusBar().validate();
	}



	public synchronized void actionPerformed ( ActionEvent event ) {
		System.out.println( event.getActionCommand() );
		String command = event.getActionCommand();
		LastTime = LastTime.getInstance();
		TimeNow = LastTime.get( LastTime.SECOND );
		if ( TimeNow - TheTime >= 2 ||  TheTime - TimeNow >= 2 ) {
			if ( command.equals( "Add New" ) && ! UserDepartment.equals( "Accounts" ) ) {
				if ( UserDepartment.equals( "Sales" ) || UserDepartment.equals( "SalesL" )) {
					SecurityMode = "Medium";
					if ( UserDepartment.equals( "SalesL" ) )
						SecurityMode = "Low";
					CustomerOrderUI.toAddNew().setVisible( true );
						AddForm( CustomerOrderFrame );
						CustomerOrderFrame.show();
				}
				else if ( UserDepartment.equals( "HR" ) ) {
					SecurityMode = "Low";
					SalesRepCustomerUI.toAddNew().setVisible( true );
						AddForm( SalesRepCustomerFrame );
						SalesRepCustomerFrame.show();
				}
				else if ( UserDepartment.equals( "Stock" ) ) {
					SecurityMode = "Low";
					TypeProductUI.toAddNew().setVisible( true );
						AddForm( TypeProductFrame );
						TypeProductFrame.show();
				}
			}
			else if ( command.equals( "Search Record" )   && ! UserDepartment.equals( "Accounts" ) ) {
				if ( UserDepartment.equals( "Sales" ) || UserDepartment.equals( "SalesL" ) ) {
					SecurityMode = "Medium";
					if ( UserDepartment.equals( "SalesL" ) )
						SecurityMode = "Low";
						AddForm( CustomerOrderFrame );
						CustomerOrderFrame.show();
					CustomerOrderUI.toSearch().setVisible( true );
				}
				else if ( UserDepartment.equals( "HR" ) ) {
					SecurityMode = "Low";
					SalesRepCustomerUI.toDetail().setVisible( true );
						AddForm( SalesRepCustomerFrame );
						SalesRepCustomerFrame.show();
				}
				else if ( UserDepartment.equals( "Stock" ) ) {
					SecurityMode = "Low";

					TypeProductUI.toSearch().setVisible( true );
						AddForm( TypeProductFrame );
						TypeProductFrame.show();
				}
			}
			else if ( command.equals( "Search Order" ) ) {
				SecurityMode = "Low";
				AddForm( CustomerOrderFrame );
				CustomerOrderFrame.show();
				CustomerOrderUI.setVisible( true );
				CustomerOrderUI.toSearchOrder();
			}
			else if ( command.equals( "List Product" ) ) {
				SecurityMode = "high";

				TypeProductUI = new TypeProduct( this );
				TypeProductContainer.removeAll();
				TypeProductContainer.add( TypeProductUI );

				TypeProductUI.toProducts().setVisible( true );
				AddForm( TypeProductFrame );
				TypeProductFrame.show();
			}
			else if ( command.equals( "Frequency" ) ) {
				SecurityMode = "High";
				SalesRepCustomerUI = new SalesRepCustomer( this );
				SalesRepCustomerContainer.removeAll();
				SalesRepCustomerContainer.add( SalesRepCustomerUI );

				SalesRepCustomerUI.toAllCustomers().setVisible( true );
				AddForm( SalesRepCustomerFrame );
				SalesRepCustomerFrame.show();
			}
			else if ( command.equals( "Team Performance" ) ) {
				SecurityMode = "high";
				SalesRepTeamUI = new SalesRepTeam( this );
				SalesRepTeamContainer.removeAll();
				SalesRepTeamContainer.add( SalesRepTeamUI );

				AddForm( SalesRepTeamFrame );
				SalesRepTeamFrame.show();

				SalesRepTeamUI.toPerformance().setVisible( true );
			}
			else if ( command.equals( "Delete Record" ) && ! UserDepartment.equals( "Accounts" ) ) {
				SecurityMode = "Low";
					DeleteUI = new DEDelete( this );
					DEDeleteContainer.removeAll();
					DEDeleteContainer.add( DeleteUI  );

					DeleteUI.toResult().setVisible( true );
					AddForm( DEDeleteFrame );
					DEDeleteFrame.show();
			}
			else if ( command.equals( "Change Password" ) ) {
				if ( UserDepartment.equals( "Sales" ) || UserDepartment.equals( "SalesL" ) )
					SecurityMode = "Medium";
				if ( UserDepartment.equals( "HR" ) )
					SecurityMode = "Low";

				SalesRepCustomerUI.toChangePassword().setVisible( true );

					AddForm( SalesRepCustomerFrame );
					SalesRepCustomerFrame.show();
			}
			else if ( command.equals( "Log Off" ) ) {
				SalesRepCustomerUI = new SalesRepCustomer( this );
				SalesRepCustomerContainer.removeAll();
				SalesRepCustomerContainer.add( SalesRepCustomerUI );

				SalesRepCustomerUI.toLog( true ).setVisible( true );
				AddForm( SalesRepCustomerFrame );
				SalesRepCustomerFrame.show();
			}
			else if ( command.equals( "Quit" ) ) {
				odbc.close();
				System.exit( 0 );
			}

			TheTime = TimeNow;
		}
	}

	public void itemStateChanged (ItemEvent event ) {
		changeLookAndFeel( cboLookAndFeel.getSelectedIndex() );
	}

	public void changeLookAndFeel ( int value ) {
		try {
			UIManager.setLookAndFeel( Looks[ value ].getClassName() );
			SwingUtilities.updateComponentTreeUI( this );
			SwingUtilities.updateComponentTreeUI( CustomerOrderFrame );
			SwingUtilities.updateComponentTreeUI( SalesRepTeamFrame );
			SwingUtilities.updateComponentTreeUI( TypeProductFrame);
			SwingUtilities.updateComponentTreeUI( SalesRepCustomerFrame );
		}
		catch (Exception e) { }
	}
}