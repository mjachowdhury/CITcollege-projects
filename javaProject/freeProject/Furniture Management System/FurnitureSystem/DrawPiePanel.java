import java.awt.*;
import javax.swing.*;
import java.util.*;
public class DrawPiePanel extends JPanel {
	private int Sector[];
	private String Notes[];
	private String Title;

	public DrawPiePanel( String title ) {
		Title = title;
	}

	public void addSector( int value[], String notes[] ) {
		int Total = 0;
		for( int a = 0; a < value.length; a++ ) {
			Total = Total + value[ a ];
		}
		Sector =  new int[ value.length ];
		for ( int b = 0; b < value.length; b++ ) {
			Sector[ b ] = value[ b ] * 360  / Total;
		}
		int Remain = 360;
		for ( int b = 0; b < value.length; b++ ) {
			Remain = Remain - Sector[ b ];
		}

		for ( int b = 1; b <= Remain; b++ ) {
			Sector[ b ] = Sector[ b ]  + 1 ;
		}

		Notes = notes;
		paintComponent( this.getGraphics() );
	}

	public void paintComponent( Graphics g )  {
		if ( isEnabled() )
		drawPie( g );
	}

   	public Dimension getPreferredSize() {
		return new Dimension( 500, 300 );
	}

   	public void drawPie( Graphics g) {
		int TotalSectorNow = 0;
		Graphics2D g2d = ( Graphics2D ) g;
		int color1;
		int color2;
		int color3;
		int Line = 30;
		g.setFont( new Font( "Verdana", Font.BOLD + Font.ITALIC, 13 ) );
		g2d.drawString( Title, 500 / 2, 12 );

		if ( Sector != null ) {
			for ( int a = 0; a < Sector.length; a++ ) {
				color1 = (int) (Math.random() * 1000) % 255;
				color2 = (int) (Math.random() * 1000) % 255;
				color3 = (int) (Math.random() * 1000) % 255;
				g2d.setPaint( new Color( color1, color2, color3 ) );
				g2d.fillArc( 20, 6, 290, 290, TotalSectorNow, Sector[ a ] );

				g2d.drawString( Notes[ a ] + "  " + ( Sector[ a ] * 100 / 360 ) + "%", 350, Line );
				Line = Line + 14;
				TotalSectorNow = TotalSectorNow + Sector[ a ];
			}
		}
   	}
}