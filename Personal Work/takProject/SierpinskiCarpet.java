package com.taksila.javabyexample.projects.sierpinskicarpet;


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
public class SierpinskiCarpet extends JPanel {

    private int PREF_W = 600;
    private int PREF_H = 600;

    private int carpetSize = 600;
    private int levels = 7;
    private Color color1 = Color.BLUE;
    private Color color2 = Color.WHITE;


    public int getCarpetSize() {
        return carpetSize;
    }

    public void setCarpetSize(int carpetSize) {
        this.carpetSize = carpetSize;
    }

    public int getLevels() {
        return levels;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    @Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
        this.removeAll();
        //Create top level triangle
        g.setColor( color1 );
        g.fillRect(0, 0, carpetSize, carpetSize );
        drawCarpet( g,levels - 1, 0, 0, carpetSize );
    }

    private void drawCarpet( Graphics g, int level, int x, int y, int size ) {
        if( level <=0 ) {
            return;
        }
        int innerRectSize = size/3;
        g.setColor( color2 );
        g.fillRect( x + innerRectSize, y+innerRectSize, innerRectSize, innerRectSize );
        level = level - 1;
        drawCarpet( g, level, x, y, innerRectSize );                                    //FirstRow, first Rectangle
        drawCarpet( g, level, x + innerRectSize, y, innerRectSize );                    //FirstRow, second Rectangle
        drawCarpet( g, level, x + 2*innerRectSize, y, innerRectSize );                  //FirstRow, third Rectangle
        drawCarpet( g, level, x, y + innerRectSize, innerRectSize );                    //SecondRow, first Rectangle
        drawCarpet( g, level, x + 2*innerRectSize, y + innerRectSize, innerRectSize );  //SecondRow, third Rectnalge
        drawCarpet( g, level, x, y + 2*innerRectSize, innerRectSize );                  //ThirdRow, first Rectangle
        drawCarpet( g, level, x + innerRectSize, y + 2*innerRectSize, innerRectSize );  //ThirdRow, second rectangle
        drawCarpet( g, level, x + 2*innerRectSize, y+ 2*innerRectSize, innerRectSize ); //ThirdRow, third rectangle

    }


}
