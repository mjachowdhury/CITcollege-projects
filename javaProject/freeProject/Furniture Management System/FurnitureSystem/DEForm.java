import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;

public class DEForm extends DEObjects.DEPanel {
	private GridBagConstraints grid_bag = new GridBagConstraints();
	public final int BOTH		= GridBagConstraints.BOTH;
	public final int NONE		= GridBagConstraints.NONE;
	public final int EAST		= GridBagConstraints.EAST;
	public final int WEST		= GridBagConstraints.WEST;
	public UI.DELabel Header;

	public DEForm() {
		super( new GridBagLayout(),true );
		Header = new UI().new DELabel( "" );
		DETool.coloringHeader( Header );
	}

	public void gridx( int gridx ) {
		grid_bag.gridx = gridx;
	}

	public void gridy( int gridy ) {
		grid_bag.gridy = gridy;
	}

	public void gridwidth( int gridwidth ) {
		grid_bag.gridwidth = gridwidth;
	}

	public void gridheight( int gridheight ) {
		grid_bag.gridheight = gridheight;
	}

	public void insets( int top, int left, int low, int right ) {
		grid_bag.insets		= new Insets( top, left, low, right );
	}

	public void fill( int constraints ) {
		grid_bag.fill = constraints;
	}

	public void anchor( int constraints ) {
		grid_bag.anchor		= constraints;
	}

	public Component add( Component component ) {
		add( component, grid_bag );
		return component;
	}

	public Component[] add( Component components[] ) {
		for ( int a = 0; a < components.length; a++ )
			add( components[ a ], grid_bag );
		return components;
	}

	//===================================================
		public void show( Component component[] ) {
			setVisible( component, true );
		}

		public void hide( Component component[] ) {
			setVisible( component, false );
		}
	//======================================================
		public void setVisible( Component component[], boolean should ) {
			for( int a = 0; a < component.length; a++ ) {
				component[ a ].setVisible( should );
			}
		}
	//=============================================================
		public void enable( Component component[] ) {
			setEnabled( component, true );
		}

		public void disable( Component component[] ) {
			setEnabled( component, false );
		}

		public void setEnabled( Component component[], boolean should ) {
			for( int a = 0; a < component.length; a++ ) {
				if ( ! (component[ a ] instanceof DEObjects.DELabel) )
					component[ a ].setEnabled( should );
			}
		}
}