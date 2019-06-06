package com.taksila.javabyexample.projects.sierpinskitriangle;


import org.pushingpixels.substance.api.skin.SubstanceMarinerLookAndFeel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Copyright (c) 2016, Taksila LLC. All rights reserved.
 * <p>
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * <p>
 * - Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * <p>
 * - Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * <p>
 * - Neither the name of Taksila LLC or the names of its
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 * <p>
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * <p>
 * Created by Taksila LLC on 12/8/2016.
 */
public class SierpinskiTriangleApp extends JFrame implements ActionListener {

    JTextField triangleWidthFld;
    JTextField levelsFld;
    SierpinskiTriangle sierpinskiTriangle;
    JButton color1Button;
    JButton color2Button;
    JButton color3Button;
    Color color1 = Color.BLACK;
    Color color2 = Color.RED;
    Color color3 = Color.GREEN;

    public static void main( String[] args ) {
        /*SierpinskiTriangleApp sierpinskiTriangleApp = new SierpinskiTriangleApp();
        sierpinskiTriangleApp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //centreWindow( sierpinskiTriangleApp );
        sierpinskiTriangleApp.setSize(sierpinskiTriangleApp.getMaximumSize());
        sierpinskiTriangleApp.setVisible(true);*/

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new SubstanceMarinerLookAndFeel());
                    //UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
                } catch (Exception e) {
                    System.out.println("Failed to Initialize Skin");
                }
                SierpinskiTriangleApp sierpinskiTriangleApp = new SierpinskiTriangleApp();
                sierpinskiTriangleApp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                sierpinskiTriangleApp.setSize(sierpinskiTriangleApp.getMaximumSize());
                sierpinskiTriangleApp.setVisible(true);
            }
        });
    }

    public SierpinskiTriangleApp() {
        this.setTitle( "Sierpinski Triangle Application");
        //this.setSize( 1200, 800 );
        addWindowListener( new ConfirmOnClose() );
        initializeGUI();
    }

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    class ConfirmOnClose extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            int confirm = JOptionPane.showConfirmDialog(e.getWindow(), "Are you sure you want to exit?");
            if( confirm == 0 ) {
                e.getWindow().dispose();
                System.exit(0);
            }
        }
    }

    public void initializeGUI() {
        JPanel content = (JPanel)this.getContentPane();
        content.setLayout( new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2,2,2,2);

        JPanel tempPanel = new JPanel();
        JLabel label = new JLabel( "Sierpinski Triangle", SwingConstants.CENTER );
        Font bigFont = label.getFont().deriveFont( Font.BOLD, 25f );
        label.setFont( bigFont );
        tempPanel.add( label );
        c.anchor = GridBagConstraints.NORTH;
        addComponent( content, c, tempPanel, 0,0,5 );
        //c.anchor = GridBagConstraints.WEST;
        addComponent( content,c,triangleInfoPanel(), 0,1,5 );
        addComponent( content,c,trianglePanel(), 0,2,5 );

    }

    public void addComponent( JPanel content, GridBagConstraints c, Component comp, int column, int row, int gridwidth ) {
        c.gridx = column;
        c.gridy = row;
        c.gridwidth = gridwidth;
        content.add( comp, c );
    }

    private JPanel triangleInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout( new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2,2,2,2);
        c.anchor = GridBagConstraints.WEST;
        JLabel label = new JLabel( "Triangle Width: " );
        triangleWidthFld = new JTextField( "600", 4);
        addComponent( panel, c, label, 0,0,1 );
        addComponent( panel, c, triangleWidthFld, 1,0,1 );
        label = new JLabel( "Levels: ");
        levelsFld = new JTextField( "3", 1 );
        addComponent( panel, c, label, 0,1,1 );
        addComponent( panel, c, levelsFld, 1,1,1 );
        color1Button = new JButton( "Color1" );
        color2Button = new JButton( "Color2" );
        color3Button = new JButton( "Color3" );
        color1Button.addActionListener( this );
        color2Button.addActionListener( this );
        color3Button.addActionListener( this );
        color1Button.setActionCommand( "setcolor1");
        color2Button.setActionCommand( "setcolor2");
        color3Button.setActionCommand( "setcolor3");
        color1Button.setBackground( color1 );
        color2Button.setBackground( color2 );
        color3Button.setBackground( color3 );
        addComponent( panel, c, color1Button, 0,2,1 );
        addComponent( panel, c, color2Button, 1,2,1 );
        addComponent( panel, c, color3Button, 2,2,1 );
        JButton button = new JButton( "Redraw Triangle" );
        button.setActionCommand( "redrawtriangle" );
        button.addActionListener( this );
        addComponent( panel, c, button, 1,3,1 );

        return panel;
    }

    private JPanel trianglePanel() {
        JPanel panel = new JPanel();
        panel.setLayout( new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2,2,2,2);
        c.anchor = GridBagConstraints.CENTER;
        sierpinskiTriangle = new SierpinskiTriangle();
        addComponent( panel, c, sierpinskiTriangle, 0,1,1 );
        return panel;
    }

    public void actionPerformed(ActionEvent evt) {
        String actCommand = evt.getActionCommand();
        if (actCommand.equalsIgnoreCase("redrawtriangle")) {
            redrawTriangle();
        }
        else if( actCommand.equalsIgnoreCase( "setcolor1" ) ) {
            Color newColor = JColorChooser.showDialog(this, "Text Color", color1Button.getBackground() );
            if( newColor != null ) {
                color1 = newColor;
                color1Button.setBackground( color1 );
            }
        }
        else if( actCommand.equalsIgnoreCase( "setcolor2" ) ) {
            Color newColor = JColorChooser.showDialog(this, "Text Color", color2Button.getBackground() );
            if( newColor != null ) {
                color2 = newColor;
                color2Button.setBackground( color2 );
            }
        }
        else if( actCommand.equalsIgnoreCase( "setcolor3" ) ) {
            Color newColor = JColorChooser.showDialog(this, "Text Color", color3Button.getBackground() );
            if( newColor != null ) {
                color3 = newColor;
                color3Button.setBackground( color3 );
            }
        }


    }

    private void redrawTriangle() {
        String widthstr = triangleWidthFld.getText();
        String levelsstr = levelsFld.getText();
        int width = Integer.parseInt( widthstr );
        int levels = Integer.parseInt( levelsstr );
        sierpinskiTriangle.setTriangleWidth( width );
        sierpinskiTriangle.setLevels( levels );
        sierpinskiTriangle.setColor1( color1 );
        sierpinskiTriangle.setColor2( color2 );
        sierpinskiTriangle.setColor3( color3 );
        sierpinskiTriangle.repaint();
        return;
    }

}
