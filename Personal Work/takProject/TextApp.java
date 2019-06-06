package com.taksila.javabyexample.projects.text;


import com.taksila.javabyexample.projects.laf.LAFUtil;
import org.pushingpixels.substance.api.skin.SubstanceBusinessBlueSteelLookAndFeel;

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
 * Created by Taksila LLC on 12/12/2016.
 */
public class TextApp extends JFrame implements ActionListener {

    JTextArea textArea;

    public static void main( String[] args ) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        /*TextApp txtApp = new TextApp();
        txtApp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        centreWindow( txtApp );
        txtApp.setVisible(true);*/

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new SubstanceBusinessBlueSteelLookAndFeel());
                    //UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
                } catch (Exception e) {
                    System.out.println("Failed to Initialize Skin");
                }
                TextApp txtApp = new TextApp();
                txtApp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                centreWindow( txtApp );
                txtApp.setVisible(true);
            }
        });
    }

    public TextApp() {
        this.setTitle( "Text Application");
        this.setSize( 900, 500 );
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
            int confirm = JOptionPane.showConfirmDialog(e.getWindow(), "Are you sure you want exit?");
            if( confirm == 0 ) {
                e.getWindow().dispose();
                System.exit(0);
            }
        }
    }

    public void initializeGUI() {
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontFamilies = graphicsEnvironment.getAvailableFontFamilyNames();
        String[] fontStyles = { "Plain", "Italic", "Bold", "Bold Italic" };
        Integer[] fontSizes = { 8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72};

        JPanel content = (JPanel)this.getContentPane();
        content.setLayout( new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2,2,2,2);
        c.gridx = 0;    //0th column
        c.gridy = 0;    //oth Row
        c.gridwidth = 1;
        JButton lafButton = LAFUtil.getLAFOptionsButton(this);
        content.add( lafButton,c );
        c.gridx = 1;    //0th column
        c.gridwidth = 3;  // Title spans two columns
        JLabel label = new JLabel( "Text Colors and Styles", SwingConstants.CENTER );
        Font bigFont = label.getFont().deriveFont( Font.BOLD, 25f );
        label.setFont( bigFont );
        content.add( label,c );

        c.gridx = 0;    //0th column
        c.gridy = 1;    //1st Row
        c.gridwidth = 1;  // Title spans two columns
        JComboBox<String> fontFamiliesFld = new JComboBox<String>(fontFamilies);
        fontFamiliesFld.setActionCommand( "fontfamily");
        fontFamiliesFld.addActionListener( this );
        content.add( fontFamiliesFld, c );

        c.gridx = 1;    //1st column
        c.gridy = 1;    //1st Row
        c.gridwidth = 1;
        JComboBox<String> fontSytlesFld = new JComboBox<String>(fontStyles);
        fontSytlesFld.setActionCommand( "fontstyle");
        fontSytlesFld.addActionListener( this );
        content.add( fontSytlesFld, c );

        c.gridx = 2;    //2nd column
        c.gridy = 1;    //1st Row
        c.gridwidth = 1;
        JComboBox<Integer> fontSizeFld = new JComboBox<Integer>(fontSizes);
        fontSizeFld.setActionCommand( "fontsize");
        fontSizeFld.addActionListener( this );
        content.add( fontSizeFld, c );

        c.gridx = 3;    //3rd column
        c.gridy = 1;    //1st Row
        c.gridwidth = 1;
        JButton textColorChooser = new JButton( "A" );
        textColorChooser.addActionListener( this );
        textColorChooser.setActionCommand( "textcolorchooser" );
        content.add( textColorChooser, c );

        c.gridx = 0;    //2nd column
        c.gridy = 2;    //2nd Row
        c.gridwidth = 5;  // Title spans two columns
        textArea = new JTextArea( "You can type the text here", 10, 60 );
        JScrollPane scroll = new JScrollPane (textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        content.add( scroll, c );
    }

    public void actionPerformed(ActionEvent evt) {
        String actCommand = evt.getActionCommand();
        if( actCommand.equalsIgnoreCase( "fontfamily" ) ) {
            JComboBox cb = (JComboBox) evt.getSource();
            String fontFamily = (String) cb.getSelectedItem();
            Font currFont = textArea.getFont();
            Font newFont = new Font( fontFamily, currFont.getStyle(), currFont.getSize() );
            textArea.setFont( newFont );
        }
        else if( actCommand.equalsIgnoreCase( "fontstyle" ) ) {
            JComboBox cb = (JComboBox) evt.getSource();
            String fontStyleStr = (String) cb.getSelectedItem();
            int fontStyle = Font.PLAIN;
            if( fontStyleStr.equalsIgnoreCase( "Bold" ) ) {
                fontStyle = Font.BOLD;
            }
            else if( fontStyleStr.equalsIgnoreCase( "Italic" ) ) {
                fontStyle = Font.ITALIC;
            }
            else if( fontStyleStr.equalsIgnoreCase( "Bold Italic" ) ) {
                fontStyle = Font.BOLD | Font.ITALIC;
            }
            Font font = textArea.getFont().deriveFont( fontStyle );
            textArea.setFont( font );
        }
        else if( actCommand.equalsIgnoreCase( "fontsize" ) ) {
            JComboBox cb = (JComboBox) evt.getSource();
            Integer fontSize = (Integer) cb.getSelectedItem();
            Font font = textArea.getFont().deriveFont( fontSize.intValue() + 0.0f );
            textArea.setFont( font );
        }
        else if( actCommand.equalsIgnoreCase( "textcolorchooser" ) ) {
            Color newColor = JColorChooser.showDialog(this, "Text Color", Color.GREEN );
            if( newColor != null ) {
                textArea.setForeground( newColor );
            }
        }
    }
}
