package com.taksila.javabyexample.projects.hanoi;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Random;

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
public class Hanoi extends JPanel {
    int numberOfDiscs;
    private int PREF_W = 600;
    private int PREF_H = 400;
    Point refPoint;
    Pole poleA, poleB, poleC;
    Color[] colors = {Color.BLUE, Color.CYAN, Color.GREEN,
            Color.MAGENTA, Color.ORANGE, Color.PINK,
            Color.RED, Color.DARK_GRAY, Color.BLACK, Color.YELLOW};
    int waitTime = 1000;
    int moveWaitTime = 10;

    public Hanoi(int numberOfDiscs) {
        super();
        this.numberOfDiscs = numberOfDiscs;
        this.setBackground(Color.WHITE );
        initialize();
    }

    @Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent(g);
        this.removeAll();
        Graphics2D g2 = (Graphics2D)g;
        float thickness = 2;
        Stroke oldStroke = g2.getStroke();
        g2.setStroke(new BasicStroke(thickness));
        g2.drawLine(refPoint.x, refPoint.y,  refPoint.x + PREF_W, refPoint.y );
        g2.setStroke(oldStroke);
        poleA.paintComponent( g );
        g.drawString( "Pole A", poleA.getPosx() - 10, refPoint.y + 25 );
        poleB.paintComponent( g );
        g.drawString( "Pole B", poleB.getPosx() - 10, refPoint.y + 25 );
        poleC.paintComponent( g );
        g.drawString( "Pole C", poleC.getPosx() - 10, refPoint.y + 25 );
        //initializeDisks( g, poleA );
        poleA.paintDisks( g );
        poleB.paintDisks( g );
        poleC.paintDisks( g );

    }

    public void initialize() {
        refPoint = new Point( 0, 300 );
        poleA = new Pole( 95, 50, 10, refPoint, Color.BLACK );
        poleB = new Pole( 295, 50, 10, refPoint, Color.BLACK );
        poleC = new Pole( 495, 50, 10, refPoint, Color.BLACK );
        int initialDiskWidth = 180;
        int diskWidth = 0;
        Color diskColor;
        Disk disk;
        for( int count = 0; count < numberOfDiscs; count ++ ) {
            diskWidth =  initialDiskWidth - count * 10;
            diskColor = colors[count];
            disk = new Disk( poleA, diskWidth, diskColor, count + 1 );
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    public int getNumberOfDiscs() {
        return numberOfDiscs;
    }

    public void setNumberOfDiscs(int numberOfDiscs) {
        this.numberOfDiscs = numberOfDiscs;
    }

    public void solve() {
        moveDiscs( numberOfDiscs, "A", "B", "C", poleA, poleB, poleC );
    }

    public void moveDiscs( int n, String start, String intermediate, String end,
                                    Pole startPole, Pole intermediatePole, Pole endPole ) {
        if( n == 1 ) {
            //Moved nth disc to the end pole
            System.out.println( "Move " + n + " disc from " + start + "  to " + end );
            //moveTopDisk( startPole, endPole );
            startPole.moveTopDiskTo( endPole );
            paintComponent( getGraphics() );
            try {
                Thread.sleep(waitTime);
            }
            catch( InterruptedException ex ) {
                //Ignore exception
            }
        }
        else {
            //Move n-1 discs from start pole to the intermediate pole
            moveDiscs( n-1, start, end, intermediate, startPole, endPole, intermediatePole );
            //Moved nth disc to the end pole
            System.out.println( "Move " + n + " disc from " + start + "  to " + end );
            startPole.moveTopDiskTo( endPole );
            paintComponent( getGraphics() );
            try {
                Thread.sleep(waitTime);
            }
            catch( InterruptedException ex ) {
                //Ignore exception
            }
            //Moved n-1 discs from intermediate pole to end pole
            moveDiscs( n-1, intermediate, start, end, intermediatePole, startPole, endPole );
        }
    }

    private void moveTopDisk( Pole startPole, Pole endPole) {
        Disk topDisk = startPole.peekDisk();
        //Move up
        int targetX = startPole.getPosx();
        int targetY = startPole.getPosy() - 2 * topDisk.diskHeight;
        topDisk.setDiskMovingFlag( true );
        moveUp( topDisk, targetY, 1 );
        targetX = endPole.getPosx() - topDisk.diskWidth/2;
        if( startPole.getPosx() < endPole.getPosx() ) {
            //moveright
            moveRight( topDisk, targetX, 1 );
        }
        else {
            //moveleft
            moveLeft( topDisk, targetX, 1 );
        }
        targetY = endPole.getPoleBottomMidPoint().y - ( endPole.getDiskCount() + 1 ) * topDisk.diskHeight;
        moveDown( topDisk, targetY, 1 );
        topDisk.setDiskMovingFlag( false );
    }

    private void moveUp( Disk disk, int targetY, int moveSize ) {
        while( disk.getPosy() > targetY ) {
            disk.setPosy( disk.getPosy() - moveSize );
            paintComponent( getGraphics() );
            try {
                Thread.sleep(moveWaitTime);
            }
            catch( InterruptedException ex ) {
                //Ignore exception
            }
        }

    }

    private void moveDown( Disk disk, int targetY, int moveSize ) {
        while( disk.getPosy() < targetY ) {
            disk.setPosy( disk.getPosy() + moveSize );
            paintComponent( getGraphics() );
            try {
                Thread.sleep(moveWaitTime);
            }
            catch( InterruptedException ex ) {
                //Ignore exception
            }
        }

    }

    private void moveRight( Disk disk, int targetX, int moveSize ) {
        while( disk.getPosx() < targetX ) {
            disk.setPosx( disk.getPosx() + moveSize );
            paintComponent( getGraphics() );
            try {
                Thread.sleep(moveWaitTime);
            }
            catch( InterruptedException ex ) {
                //Ignore exception
            }
        }

    }

    private void moveLeft( Disk disk, int targetX, int moveSize ) {
        while( disk.getPosx() > targetX ) {
            disk.setPosx( disk.getPosx() - moveSize );
            paintComponent( getGraphics() );
            try {
                Thread.sleep(moveWaitTime);
            }
            catch( InterruptedException ex ) {
                //Ignore exception
            }
        }

    }

}
