package com.taksila.javabyexample.projects.minitennis;

import org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel;

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
public class MiniTennisApp extends JFrame implements ActionListener {

    JTextField difficultyLevelFld;
    int defaultDifficultyLevel = 1;
    JLabel scoreLabel;
    JTextField scoreFld;
    MiniTennis miniTennis;


    public static void main( String[] args ) {
        /*MiniTennisApp miniTennisApp = new MiniTennisApp();
        miniTennisApp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);*/

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new SubstanceOfficeBlue2007LookAndFeel());
                    //UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
                } catch (Exception e) {
                    System.out.println("Failed to Initialize Skin");
                }
                MiniTennisApp miniTennisApp = new MiniTennisApp();
                miniTennisApp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });

    }

    public MiniTennisApp() {
        this.setTitle( "Mini Tennis");
        addWindowListener( new ConfirmOnClose() );
        initializeGUI();
        this.setSize( 600, 500 );
        centreWindow( this );
        setVisible( true );
        //The following two lines must be executed after the frame is visiable
        //Otherwise the focus is not given to the panel and no key listeners will work
        miniTennis.setFocusable( true );
        miniTennis.requestFocus();
        //miniTennis.setScoreLabel( scoreLabel );
        miniTennis.setScoreField( scoreFld );
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

    private void initializeGUI() {
        JPanel content = (JPanel)this.getContentPane();
        content.setLayout( new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2,2,2,2);

        JPanel tempPanel = new JPanel();
        JLabel label = new JLabel( "Mini Tennis", SwingConstants.CENTER );
        Font initialFont = label.getFont();
        Font bigFont = label.getFont().deriveFont( Font.BOLD, 30f );
        label.setFont( bigFont );
        tempPanel.add( label );
        c.anchor = GridBagConstraints.NORTH;
        addComponent( content, c, tempPanel, 0,0,7 );
        c.anchor = GridBagConstraints.CENTER;
        label = new JLabel( "Level (1-5): " );
        difficultyLevelFld = new JTextField( String.valueOf( defaultDifficultyLevel ), 2);
        addComponent( content, c, label, 0,1,1 );
        addComponent( content, c, difficultyLevelFld, 1,1,1 );
        scoreLabel = new JLabel( "Score: 0" );
        //addComponent( content, c, scoreLabel, 3,1,1 );
        scoreFld = new JTextField( "0", 4 );
        scoreFld.setFont( bigFont );
        scoreFld.setHorizontalAlignment( JTextField.RIGHT);
        c.gridheight = 2;
        c.anchor = GridBagConstraints.EAST;
        addComponent( content, c, scoreFld, 3,1,3 );
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;
        JButton button = new JButton( "Reset" );
        button.setActionCommand( "reset" );
        button.addActionListener( this );
        addComponent( content, c, button, 0,2,1 );
        button = new JButton( "Start" );
        button.setActionCommand( "start" );
        button.addActionListener( this );
        addComponent( content, c, button, 1,2,1 );
        button = new JButton( "Stop" );
        button.setActionCommand( "stop" );
        button.addActionListener( this );
        addComponent( content, c, button, 2,2,1 );

        miniTennis = new MiniTennis();
        addComponent( content, c, miniTennis, 0,3,7 );



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
        else if (actCommand.equalsIgnoreCase("start")) {
            String difficultyLevelStr = difficultyLevelFld.getText();
            int difficultyLevel = Integer.parseInt( difficultyLevelStr );
            miniTennis.setDifficultlylevel( difficultyLevel );
            miniTennis.startGame();
            miniTennis.setFocusable( true );
            miniTennis.requestFocus();
        }
        else if (actCommand.equalsIgnoreCase("stop")) {
            miniTennis.stopGame();
            miniTennis.setFocusable( true );
            miniTennis.requestFocus();
        }

    }

    private void reset() {
        miniTennis.resetGame();
    }





}
