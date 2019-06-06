package com.taksila.javabyexample.projects.sierpinskitriangle;


import javax.swing.*;
import java.awt.*;

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
public class SierpinskiTriangle extends JPanel {
    private int triangleWidth = 600;
    private int levels = 3;
    private int PREF_W = 600;
    private int PREF_H = 600;
    private Color color1 = Color.BLACK;
    private Color color2 = Color.RED;
    private Color color3 = Color.GREEN;

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public void setColor3(Color color3) {
        this.color3 = color3;
    }
    public int getTriangleWidth() {
        return triangleWidth;
    }

    public void setTriangleWidth(int triangleWidth) {
        this.triangleWidth = triangleWidth;
    }

    public int getLevels() {
        return levels;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }

    @Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
        this.removeAll();
        //Create top level triangle
        int traingleHeight = (int)getHeight(getTriangleWidth() );
        Point p1 = new Point( getTriangleWidth()/2, 0 );
        Point p2 = new Point( 0, traingleHeight );
        Point p3 = new Point( getTriangleWidth(), traingleHeight );
        displayTriangles( g, levels, p1, p2, p3, this.color1 );
    }

    private double getHeight( int width ) {
        return width * Math.sqrt(3)/2;
    }

    private void displayTriangles( Graphics g, int level, Point p1, Point p2, Point p3, Color fillColor ) {
        Graphics2D g2 = (Graphics2D)g;
        //display triagnle
        g2.drawLine(p1.x, p1.y, p2.x, p2.y );
        g2.drawLine(p1.x, p1.y, p3.x, p3.y );
        g2.drawLine(p2.x, p2.y, p3.x, p3.y );
        int xpoints[] = { p1.x, p2.x, p3.x };
        int ypoints[] = { p1.y, p2.y, p3.y };
        g2.setColor( fillColor );
        g2.fillPolygon( xpoints, ypoints, 3 );
        level = level - 1;
        if( level <=0 ) {
            return;
        }
        if( fillColor == this.color1 ) {
            fillColor = this.color2;
        }
        else if( fillColor == this.color2 ) {
            fillColor = this.color3;
        }
        else if( fillColor == this.color3 ) {
            fillColor = this.color1;
        }
        Point p12 = getMidPoint(p1, p2);
        Point p13 = getMidPoint(p1, p3);
        Point p23 = getMidPoint(p2, p3);
        displayTriangles(g, level, p1, p12, p13, fillColor );
        displayTriangles(g, level, p2, p12, p23, fillColor );
        displayTriangles(g, level, p3, p13, p23, fillColor );

    }

    private Point getMidPoint( Point p1, Point p2 ) {
        return new Point( (p1.x+p2.x)/2, (p1.y+p2.y)/2);
    }

    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

}
