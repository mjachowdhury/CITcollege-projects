/*******************************************************************************
 * Copyright (c) 2000, 2004 Advanced Systems Concepts, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     Advanced Systems Concepts - gap-setting code
 *     IBM Corporation - original implementation of sizing methods (taken from CLabel)
 *******************************************************************************/

package com.swtworkbench.swtutils.controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;

/**
 * Class CCLabel.  CCLabel is a CLabel that reduces the default amount of whitespace
 * that is put around the icon and text and makes that amount settable.  CLabel
 * by default has a lot more whitespace around the label than Label does.  This
 * implementation makes the default whitespace of both the same, with CCLabel
 * adjustable so that the old CLabel behavior is still available.
 * 
 * @author daveo@asc-iseries.com
 */
public class CCLabel extends CLabel {

    /** Gap between icon and text */
    private static final int GAP = 5;

    /** Margins */
    private int hIndent = 0;
    private int vIndent = 0;

    /**
     * @param parent
     * @param style
     */
    public CCLabel(Composite parent, int style) {
        super(parent, style);
    }

    public Point computeSize(int wHint, int hHint, boolean changed) {
        checkWidget();
        Point e = getTotalSize(getImage(), getText());
        if (wHint == SWT.DEFAULT){
            e.x += 2*hIndent;
        } else {
            e.x = wHint;
        }
        if (hHint == SWT.DEFAULT) {
            e.y += 2*vIndent;
        } else {
            e.y = hHint;
        }
        return e;
    }

    /**
     * Compute the minimum size.
     */
    private Point getTotalSize(Image image, String text) {
        Point size = new Point(0, 0);
        
        if (image != null) {
            Rectangle r = image.getBounds();
            size.x += r.width;
            size.y += r.height;
        }
            
        GC gc = new GC(this);
        if (text != null && text.length() > 0) {
            Point e = gc.textExtent(text);
            size.x += e.x;
            size.y = Math.max(size.y, e.y);
            if (image != null) size.x += GAP;
        } else {
            size.y = Math.max(size.y, gc.getFontMetrics().getHeight());
        }
        gc.dispose();
        
        return size;
    }
    
    /**
     * Returns the horizontal indent value.
     * @return Returns the hIndent.
     */
    public int getHIndent() {
        return hIndent;
    }
    
    /**
     * Sets the horizontal indent
     * @param indent The hIndent to set.
     */
    public void setHIndent(int indent) {
        hIndent = indent;
        getParent().layout(true);
    }
    
    /**
     * Returns the vertical indent
     * @return Returns the vIndent.
     */
    public int getVIndent() {
        return vIndent;
    }
    
    /**
     * Sets the vertical indent
     * @param indent The vIndent to set.
     */
    public void setVIndent(int indent) {
        vIndent = indent;
        getParent().layout(true);
    }
}
