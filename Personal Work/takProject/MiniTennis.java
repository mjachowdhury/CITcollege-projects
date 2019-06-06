package com.taksila.javabyexample.projects.minitennis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

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
public class MiniTennis extends JPanel implements ActionListener, KeyListener {
    /**
     * Fires one or more ActionEvents at specified intervals. An example use is a game that uses a Timer as the
     * trigger for drawing its frames. Timers are constructed by specifying both a delay parameter and an ActionListener.
     * The delay parameter is used to set both the initial delay and the delay between event firing, in milliseconds.
     * Once the timer has been started, it waits for the initial delay before firing its first ActionEvent to registered
     * listeners. After this first event, it continues to fire events every time the between-event delay has elapsed,
     * until it is stopped.
     */
    Timer timer = new Timer( 20, this );
    int basePosX = 0;
    int basePosY = 0;
    int baseVelX = 1;
    int baseVelY = 1;

    int currPosX = 0;
    int currPosY = 0;
    int currVelX = 2;
    int currVelY = 2;

    private int PREF_W = 480;
    private int PREF_H = 300;

    private int ballDia = 20;

    private int batWidth = 80;
    private int batHeight = 10;
    private int batPosX = 220;
    private int batPosY = PREF_H - 1 - batHeight;
    private int batVelX = 20;

    private JLabel scoreLabel;
    private JTextField scoreFld;
    private int score;
    private int scoreIncrement = 1;
    private int difficultlylevel = 1;

    Rectangle2D.Double bat;
    Ellipse2D circle;

    private static final String LEFT = "Left";
    private static final String RIGHT = "Right";

    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    public MiniTennis() {
        //Key binding - Map key to an string and then map String to an action - provides ability to control keys
        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), LEFT);
        this.getActionMap().put(LEFT, left);
        this.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), RIGHT);
        this.getActionMap().put(RIGHT, right);
        addKeyListener( this );
        setFocusable( true );
        setFocusTraversalKeysEnabled( false );
        this.requestFocus();
        score = 0;
    }

    @Override
    public void paintComponent( Graphics g ) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g.setColor( Color.BLACK );
        g.drawRect( 0, 0, PREF_W - 1, PREF_H - 1);
        //Draw bat
        bat = new Rectangle2D.Double( batPosX, batPosY, batWidth, batHeight );
        g2.setColor( Color.BLUE);
        g2.fill( bat );
        //Draw circle
        g2.setColor( Color.RED);
        circle = new Ellipse2D.Double(currPosX, currPosY, ballDia, ballDia );
        g2.fill( circle );
        if( bat.intersects( new Rectangle2D.Double(currPosX, currPosY, ballDia, ballDia) ) ) {
            // Collission detected
            currVelY = -currVelY;
            currPosY = currPosY + currVelY;
            incrementScore();
            displayScore();
        }
        //timer.start();
    }

    public void actionPerformed( ActionEvent e ) {
        if( currPosX <0 || currPosX > (PREF_W - ballDia-1 ) ) {
            currVelX = -currVelX;
        }
        if( currPosY <0   ) {
            currVelY = -currVelY;
        }
        currPosX = currPosX + currVelX;
        currPosY = currPosY + currVelY;
        if( currPosY > (PREF_H - ballDia-1 ) ) {
            //Reset score and stop game. Don't display the score. Next time when game is started, it will be displayed.
            //Collision of ball with the floor
            setScore( 0 );
            currPosX = 0;
            currPosY = 0;
            stopGame();
        }
        else {
            //Continue game
            repaint();
        }
    }

    public void keyPressed( KeyEvent e ){
        int code = e.getKeyCode();
        //System.out.println( "Keycode: " + code );
        if( code == KeyEvent.VK_LEFT ) {
            //System.out.println( "Left arrow key pressed" );
            if( batPosX > 0 ) {
                batPosX = batPosX - batVelX;
            }
            repaint();
        }
        else if( code == KeyEvent.VK_RIGHT ) {
            //System.out.println( "Right arrow key pressed" );
            if( batPosX <= ( PREF_W - batWidth - batVelX ) ) {
                batPosX = batPosX + batVelX;
            }
            repaint();
        }
    }

    public void keyReleased( KeyEvent e ) {

    }

    public void keyTyped( KeyEvent e ) {

    }




    private Action left = new AbstractAction(LEFT) {
        @Override
        public void actionPerformed(ActionEvent e) {
            //System.out.println(LEFT);
        }
    };
    private Action right = new AbstractAction(RIGHT) {
        @Override
        public void actionPerformed(ActionEvent e) {
            //System.out.println(RIGHT);
        }
    };

    public void setScoreLabel(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public void setScoreField( JTextField scoreFld ) {
        this.scoreFld = scoreFld;
    }

    public void setDifficultlylevel(int difficultlylevel) {
        if( difficultlylevel <= 1 ) {
            this.difficultlylevel = 1;
        }
        else if (difficultlylevel >= 5 ) {
            this.difficultlylevel = 5;
        }
        else {
            this.difficultlylevel = difficultlylevel;
        }
        scoreIncrement = this.difficultlylevel;
    }

    public void incrementScore() {
        score = score + scoreIncrement;
    }

    public void setScore( int score ) {
        this.score = score;
    }

    public void displayScore() {
        scoreFld.setText(  String.valueOf( score ) );
    }

    public void stopGame() {

        timer.stop();
    }

    public void startGame() {
        timer.setDelay( getDelay() );
        timer.start();
    }

    public void resetGame() {
        setScore( 0 );
        displayScore();
        setFocusable( true );
        requestFocus();
    }

    public int getDelay() {
        int delay = 20;
        if( this.difficultlylevel == 1 ) {
            delay = 20;
        }
        else if( this.difficultlylevel == 2 ) {
            delay = 16;
        }
        else if( this.difficultlylevel == 3 ) {
            delay = 12;
        }
        else if( this.difficultlylevel == 4 ) {
            delay = 8;
        }
        else if( this.difficultlylevel == 5 ) {
            delay = 4;
        }
        return delay;
    }



}
