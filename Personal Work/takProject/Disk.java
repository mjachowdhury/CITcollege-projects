package com.taksila.javabyexample.projects.hanoi;

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
 * Created by Taksila LLC on 7/3/2016.
 */
public class Disk extends JPanel {

    Pole pole;
    int posx;
    int posy;
    int diskWidth;
    int diskHeight = 25;
    Color diskColor;
    int diskPosition;
    boolean diskMovingFlag = false;

    public Disk( Pole pole, int diskWidth, Color diskColor, int diskPosition ) {
        this.diskWidth = diskWidth;
        this.diskColor = diskColor;
        this.diskPosition = diskPosition;
        this.setPole( pole );
    }

    public void setPole(Pole pole) {
        this.pole = pole;
        setDiskPosition( pole.getDiskCount() + 1 );
        Point poleBottomMidPoint = pole.getPoleBottomMidPoint();
        this.posx = poleBottomMidPoint.x - diskWidth/2;
        this.posy = poleBottomMidPoint.y - this.diskPosition * this.diskHeight;
        pole.addDisk( this );
    }

    public void setDiskPosition(int diskPosition) {
        this.diskPosition = diskPosition;
    }

    public boolean isDiskMovingFlag() {
        return diskMovingFlag;
    }

    public void setDiskMovingFlag(boolean diskMovingFlag) {
        this.diskMovingFlag = diskMovingFlag;
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

    @Override
    protected void paintComponent( Graphics g ) {
        /*if( diskMovingFlag ) {
            return;
        }*/
        super.paintComponent(g);
        this.removeAll();
        g.setColor( this.diskColor );
        g.fillRect( this.posx, this.posy,  diskWidth, diskHeight);

    }



}
