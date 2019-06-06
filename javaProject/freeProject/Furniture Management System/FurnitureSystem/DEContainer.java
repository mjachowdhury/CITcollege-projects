import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Component;
import com.sun.java.swing.plaf.windows.*;

public class DEContainer extends DEObjects.DEPanel implements ComponentListener {
	private int alignmentX;
	private int alignmentY;
	private GridBagConstraints grid = new GridBagConstraints();
	private Container Parent;
	private JPanel Child;

	public DEContainer( Insets insets, Container parent ) {
		super( new GridBagLayout(), false );
		grid.insets = insets;
		Parent = parent;
	}

	public void add( JPanel panel ) {
		removeAll();
		add( panel, grid );
		panel.addComponentListener( this );
		Child = panel;
	}

	public void componentHidden	( ComponentEvent event ) {
		if ( Parent instanceof ExtFrame ) {
			ExtFrame frame = (ExtFrame) Parent;
			frame.dispose();
		}
		else if ( Parent instanceof IntFrame ) {
			IntFrame frame = (IntFrame) Parent;
			frame.dispose();
		}
	}
	public void componentMoved	( ComponentEvent event ) {}
	public void componentShown	( ComponentEvent event ) {
		Child.setVisible( true );
	}
	public void componentResized( ComponentEvent event ) {
		setSize( getPreferredSize() );
		Parent.validate();
		Parent.setSize( Parent.getPreferredSize() );
		if ( Parent.getParent() instanceof JDesktopPane ) {
			JDesktopPane DesktopPane = (JDesktopPane) Parent.getParent();
			DesktopPane.validate();
			IntFrame internalFrame = (IntFrame) Parent;
			if ( internalFrame.getWidth() >= DesktopPane.getWidth() && internalFrame.getHeight() >= DesktopPane.getHeight())
				internalFrame.setSize( DesktopPane.getWidth(), DesktopPane.getHeight() );

			else if ( internalFrame.getWidth() < DesktopPane.getWidth() && internalFrame.getHeight() >= DesktopPane.getHeight())
				internalFrame.setSize( internalFrame.getWidth() + 15, DesktopPane.getHeight() );

			else if ( internalFrame.getWidth() >= DesktopPane.getWidth() && internalFrame.getHeight() < DesktopPane.getHeight())
				internalFrame.setSize( DesktopPane.getWidth(), internalFrame.getHeight() );


				internalFrame.setLocation( 	( DesktopPane.getWidth() - internalFrame.getWidth() ) / 2,
											( DesktopPane.getHeight() - internalFrame.getHeight() ) / 2
										);
		}
		Parent.validate();
	}
}

class ExtFrame extends JFrame {
	private Dimension ScreenSize = DETool.getScreenSize();
	public ExtFrame() {
		super();
		getContentPane().setLayout( new BorderLayout() );
		setLocation( ScreenSize.width, ScreenSize.height );
		setVisible( true );
		setFont( DEObjects.font );
	}

	public void setSize( Dimension size  ) {
		setLocation( 	( ScreenSize.width 	- (int) size.width ) / 2,
							( ScreenSize.height 	- (int) size.height ) / 2
	   			   		);
	   	super.setSize( size );
	}

	public void add( JPanel panel ) {
		getContentPane().add( new JScrollPane( panel ) );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
	}
}

class IntFrame extends JInternalFrame {
	public IntFrame( String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable ) {
		super( title, resizable, closable, maximizable, iconifiable );
		getContentPane().setLayout( new BorderLayout() );
		setVisible( true );
		setFont( DEObjects.font );
	}

	public void add( JPanel panel ) {
		getContentPane().add( new JScrollPane( panel ) );
	}
}

class MenuForm extends ExtFrame implements MouseListener {
	private static DEMenuUI uiDesigned = new DEMenuUI();
	private DEMenuUI.DEToolBar 						ToolBar = uiDesigned.new DEToolBar();
	public DEMenuUI.DEButton 						ToolBarButton[];
	private static DEMenuUI.DEPanel 				StatusBar = uiDesigned.DEPanel ( new BorderLayout( 10, 10 ), true );
	private DEMenuUI.DELabel 							StatusLabel[];
	public JDesktopPane DesktopPane = new JDesktopPane ();

	public MenuForm() {
		super();
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add ( new JScrollPane( ToolBar ), BorderLayout.NORTH);
		getContentPane().add ( new JScrollPane( DesktopPane ), BorderLayout.CENTER);
		getContentPane().add ( StatusBar, BorderLayout.SOUTH);
		ToolBar.addMouseListener( this );
	}

	public void enable( Component component[] ) {
		setEnabled( component, true );
	}

	public void disable( Component component[] ) {
		setEnabled( component, false );
	}

	public void setEnabled( Component component[], boolean should ) {
		for( int a = 0; a < component.length; a++ ) {
			component[ a ].setEnabled( should );
		}
	}

	public void AddForm( JInternalFrame internalFrame ) {
		DesktopPane.removeAll();
		DesktopPane.setVisible( false );
		DesktopPane.setVisible( true );
		DesktopPane.revalidate();
		DesktopPane.add( internalFrame );
		internalFrame.restoreSubcomponentFocus();
		internalFrame.show();
	}

	public boolean openChildWindow (  JInternalFrame internalFrame ) {
		DesktopPane.validate();
		JInternalFrame childs[] = DesktopPane.getAllFrames ();
		boolean haveFrame = false;
		if ( childs.length > 0 ) {
			for (int i = 0; i < childs.length; i++ ) {
				if (  childs[ i ].getTitle().equals( internalFrame.getTitle() ) ) {
					internalFrame.validate();
					haveFrame = true;
				}
				else {
					childs[ i ].setVisible( false );
					childs[ i ].pack();
					DesktopPane.remove( childs[ i ] );
					DesktopPane.validate();
				}
			}
		}
		return haveFrame;
	}

	public void setToolBarButton( Icon images[], String toolTipTexts[], boolean separator[], ActionListener that ) {
		ToolBarButton = new DEMenuUI.DEButton[ images.length];
		for ( int a=0; a<images.length; a++)
		{
			ToolBarButton[ a ] = uiDesigned.new DEButton( "", images[ a ] );
			ToolBarButton[ a ].setToolTipText( toolTipTexts[ a ] );
			ToolBarButton[ a ].setActionCommand( toolTipTexts[ a ] );
			ToolBarButton[ a ].addActionListener(that);
			if ( separator[ a ] )
				ToolBar.addSeparator();
			ToolBar.add( ToolBarButton[ a ] );
		}
	}

	public DEMenuUI.DEToolBar getDEToolBar() {
		return ToolBar;
	}

	public void setStatusBar( String texts[], int alignments[], String toolTipTexts[], String locations[] ) {
		StatusLabel = new DEMenuUI.DELabel[ texts.length ];
		for ( int a=0; a<texts.length; a++) {
			StatusLabel[ a ] = uiDesigned.new DELabel( texts[ a ], alignments[ a ] );
			StatusLabel[ a ].setToolTipText( toolTipTexts[ a ] );
			StatusBar.add( StatusLabel[ a ], locations[a]);
		}
	}

	public DEMenuUI.DEPanel getDEStatusBar() {
		return StatusBar;
	}

	public void mouseClicked( MouseEvent event ) {
		if ( ToolBarButton[ 0 ].getText().equals( "" ) ) {
			for( int a = 0; a < ToolBarButton.length; a++ )
				ToolBarButton[ a ].setText( ToolBarButton[ a ].getToolTipText() );
		}
		else {
			for( int a = 0; a < ToolBarButton.length; a++ ) {
				ToolBarButton[ a ].setActionCommand( ToolBarButton[ a ].getToolTipText() );
				ToolBarButton[ a ].setText( "" );
			}
		}
	}
	public void mouseEntered( MouseEvent event ){}
	public void mouseExited( MouseEvent event ){}
	public void mousePressed( MouseEvent event ){}
	public void mouseReleased( MouseEvent event ){}
}


