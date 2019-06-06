package com.taksila.javabyexample.projects.laf;

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
public class LAFOptionsPane extends JFrame implements ActionListener {
    private JFrame guiFrame;

    public void setGUIFrame(JFrame guiFrame) {
        this.guiFrame = guiFrame;
    }




    String[][] supportedLookAndFeel = new String[][] {
            {"Autumn", "org.pushingpixels.substance.api.skin.SubstanceAutumnLookAndFeel" },
            {"Business Black Steel", "org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel"},
            {"Business Blue Steel", "org.pushingpixels.substance.api.skin.SubstanceBusinessBlueSteelLookAndFeel"},
            {"Business", "org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel"},
            {"Challenger Deep", "org.pushingpixels.substance.api.skin.SubstanceChallengerDeepLookAndFeel"},
            {"Creame Coffee", "org.pushingpixels.substance.api.skin.SubstanceCremeCoffeeLookAndFeel"},
            {"Creme", "org.pushingpixels.substance.api.skin.SubstanceCremeLookAndFeel"},
            {"Dust Coffee", "org.pushingpixels.substance.api.skin.SubstanceDustCoffeeLookAndFeel"},
            {"Dust", "org.pushingpixels.substance.api.skin.SubstanceDustLookAndFeel"},
            {"Emerald Dusk", "org.pushingpixels.substance.api.skin.SubstanceEmeraldDuskLookAndFeel"},
            {"Gemini", "org.pushingpixels.substance.api.skin.SubstanceGeminiLookAndFeel"},
            {"Graphite Aqua", "org.pushingpixels.substance.api.skin.SubstanceGraphiteAquaLookAndFeel"},
            {"Graphite Glass", "org.pushingpixels.substance.api.skin.SubstanceGraphiteGlassLookAndFeel"},
            {"Graphite", "org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel"},
            {"Magellan", "org.pushingpixels.substance.api.skin.SubstanceMagellanLookAndFeel"},
            {"Mariner", "org.pushingpixels.substance.api.skin.SubstanceMarinerLookAndFeel"},
            {"Mist Aqua", "org.pushingpixels.substance.api.skin.SubstanceMistAquaLookAndFeel"},
            {"Mist Silver", "org.pushingpixels.substance.api.skin.SubstanceMistSilverLookAndFeel"},
            {"Moderate", "org.pushingpixels.substance.api.skin.SubstanceModerateLookAndFeel"},
            {"Nebula Brick Wall", "org.pushingpixels.substance.api.skin.SubstanceNebulaBrickWallLookAndFeel"},
            {"Nebula", "org.pushingpixels.substance.api.skin.SubstanceNebulaLookAndFeel"},
            {"Office Black 2007", "org.pushingpixels.substance.api.skin.SubstanceOfficeBlack2007LookAndFeel"},
            {"Office Blue 2007", "org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel"},
            {"Office Silver 2007", "org.pushingpixels.substance.api.skin.SubstanceOfficeSilver2007LookAndFeel"},
            {"Raven", "org.pushingpixels.substance.api.skin.SubstanceRavenLookAndFeel"},
            {"Sahara", "org.pushingpixels.substance.api.skin.SubstanceSaharaLookAndFeel"},
            {"Twilight", "org.pushingpixels.substance.api.skin.SubstanceTwilightLookAndFeel"}

    };


    public LAFOptionsPane() {
        this.setTitle( "LAF Options");
        this.setSize( 500, 500 );
        addWindowListener( new ConfirmOnClose() );
        initializeGUI();
    }

    public void initializeGUI() {
        JPanel content = (JPanel)this.getContentPane();
        content.setLayout( new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2,2,2,2);
        c.gridx = 0;    //0th column
        c.gridy = 0;    //oth Row
        c.gridwidth = 4;
        JLabel label = new JLabel( "Select Look and Feel", SwingConstants.CENTER );
        Font bigFont = label.getFont().deriveFont( Font.BOLD, 25f );
        label.setFont( bigFont );
        content.add( label,c );
        JButton button = null;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.BOTH;
        for( int count = 0; count < supportedLookAndFeel.length; count++ ) {
            button = createButton( supportedLookAndFeel[count][0] );
            if( count > 0 && (count % 10 == 0 ) ) {
                c.gridx = c.gridx + 1;
            }
            c.gridy = ( count % 10 ) + 1;
            content.add( button, c );
        }
    }

    public JButton createButton(String text) {
        JButton button = new JButton( text );
        button.setActionCommand( text );
        button.addActionListener( this );
        return button;
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
                //System.exit(0);
            }
        }
    }

    public void actionPerformed(ActionEvent evt) {
        String actCommand = evt.getActionCommand();
        String lafClassName = "";
        for( int count = 0; count < supportedLookAndFeel.length; count++ ) {
            if( actCommand.equalsIgnoreCase( supportedLookAndFeel[count][0] ) ) {
                lafClassName =  supportedLookAndFeel[count][1];

            }
        }
        if( lafClassName.isEmpty() ) {
            lafClassName = UIManager.getSystemLookAndFeelClassName();
        }
        try {
            UIManager.setLookAndFeel(lafClassName);
            SwingUtilities.updateComponentTreeUI(guiFrame);
            guiFrame.pack();
            this.repaint();
        }
        catch(Exception ex ) {
            System.out.println( "Caught Exception: " + ex.getMessage() );
        }

    }

}
