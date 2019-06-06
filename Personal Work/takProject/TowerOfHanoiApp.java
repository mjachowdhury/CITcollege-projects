package com.taksila.javabyexample.projects.hanoi;

import org.pushingpixels.substance.api.skin.SubstanceRavenLookAndFeel;

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
 * Created by Taksila LLC on 7/3/2016.
 */
public class TowerOfHanoiApp extends JFrame implements ActionListener {

    JTextField numberOfDiscsFld;
    JPanel towerOfHanoiPanel;
    int defaultNumberOfDiscs = 1;
    Hanoi hanoi;


    public static void main( String[] args ) {
        /*TowerOfHanoiApp hanoiApp = new TowerOfHanoiApp();
        hanoiApp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        hanoiApp.setSize(700, 550);
        centreWindow( hanoiApp );
        hanoiApp.setVisible(true);*/

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new SubstanceRavenLookAndFeel());
                    //UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
                } catch (Exception e) {
                    System.out.println("Failed to Initialize Skin");
                }
                TowerOfHanoiApp hanoiApp = new TowerOfHanoiApp();
                hanoiApp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                hanoiApp.setSize(800, 600);
                centreWindow( hanoiApp );
                hanoiApp.setVisible(true);
            }
        });
    }

    public TowerOfHanoiApp() {
        this.setTitle( "Tower of Brahma");
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

        JPanel content = (JPanel)this.getContentPane();
        content.setLayout( new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2,2,2,2);

        JPanel tempPanel = new JPanel();
        JLabel label = new JLabel( "Tower of Brahma (Hanoi)", SwingConstants.CENTER );
        Font bigFont = label.getFont().deriveFont( Font.BOLD, 25f );
        label.setFont( bigFont );
        tempPanel.add( label );
        c.anchor = GridBagConstraints.NORTH;
        addComponent( content, c, tempPanel, 0,0,5 );
        c.anchor = GridBagConstraints.CENTER;
        label = new JLabel( "Number of discs (1 - 10): " );
        numberOfDiscsFld = new JTextField( String.valueOf( defaultNumberOfDiscs ), 2);
        addComponent( content, c, label, 0,1,1 );
        addComponent( content, c, numberOfDiscsFld, 1,1,1 );
        JButton button = new JButton( "reset" );
        button.setActionCommand( "reset" );
        button.addActionListener( this );
        addComponent( content, c, button, 0,3,1 );
        button = new JButton( "Solve" );
        button.setActionCommand( "solvehanoi" );
        button.addActionListener( this );
        addComponent( content, c, button, 1,3,1 );
        hanoi = new Hanoi( defaultNumberOfDiscs );
        addComponent( content, c, hanoi, 0,5,5 );

    }



    private void addComponent( JPanel content, GridBagConstraints c, Component comp, int column, int row, int gridwidth ) {
        c.gridx = column;
        c.gridy = row;
        c.gridwidth = gridwidth;
        content.add( comp, c );
    }

    public void actionPerformed(ActionEvent evt) {
        String actCommand = evt.getActionCommand();
        if (actCommand.equalsIgnoreCase("reset")) {
            reset();
        }
        else if (actCommand.equalsIgnoreCase("solvehanoi")) {
            solveHanoi();
        }
    }

    private void reset() {
        String diskCountStr = numberOfDiscsFld.getText();
        int diskCount = Integer.parseInt( diskCountStr );
        if( diskCount >= 10 ) {
            diskCount = 10;
        }
        else if ( diskCount <=1 ) {
            diskCount = 1;
        }
        hanoi.setNumberOfDiscs( diskCount );
        hanoi.initialize();
        hanoi.repaint();
    }

    private void solveHanoi() {
        hanoi.solve();
    }





}
