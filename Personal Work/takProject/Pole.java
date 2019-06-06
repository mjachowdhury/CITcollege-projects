package com.taksila.javabyexample.projects.hanoi;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Stack;

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
public class Pole extends JPanel {

    private int posx;
    private int posy;
    private int poleWidth;
    private Point refPoint;
    private Color poleColor;
    private Stack<Disk> disks;

    public Pole( int posx, int posy, int poleWidth, Point refPoint, Color poleColor ) {
        this.posx = posx;
        this.posy = posy;
        this.poleWidth = poleWidth;
        this.refPoint = refPoint;
        this.poleColor = poleColor;
        this.disks = new Stack<Disk>();
    }

    @Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent(g);
        this.removeAll();
        g.setColor( Color.BLACK );
        g.fillRect( this.posx, this.posy, poleWidth, refPoint.y - this.posy);
    }

    public Point getPoleBottomMidPoint() {
        Point point = new Point();
        point.x = this.posx + poleWidth/2;
        point.y = refPoint.y;
        return point;
    }

    public int getPoleHeight() {
        return refPoint.y - this.posy;
    }



    public void addDisk( Disk disk) {
        disks.add( disk );
    }

    public int getDiskCount() {
        return disks.size();
    }

    public Disk popDisk() {
        return (Disk)disks.pop();
    }

    public Disk peekDisk() {
        return (Disk)disks.peek();
    }

    public void moveTopDiskTo( Pole destinationPole ) {
        Disk disk = disks.peek();
        disks.pop();
        disk.setPole( destinationPole );
    }

    public void paintDisks( Graphics g) {
        Iterator it = disks.iterator();
        Disk disk;
        while( it.hasNext() ) {
            disk = (Disk)it.next();
            disk.paintComponent( g );
        }
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public int getPoleWidth() {
        return poleWidth;
    }

    public void setPoleWidth(int poleWidth) {
        this.poleWidth = poleWidth;
    }

    public Point getRefPoint() {
        return refPoint;
    }

    public void setRefPoint(Point refPoint) {
        this.refPoint = refPoint;
    }

    public Color getPoleColor() {
        return poleColor;
    }

    public void setPoleColor(Color poleColor) {
        this.poleColor = poleColor;
    }


}
