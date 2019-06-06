/*
 * This file is part of com.swtworkbench.ed.
 *
 * com.swtworkbench.ed is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License
 * as published by the Free Software Foundation.
 *
 * com.swtworkbench.ed is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with com.swtworkbench.ed; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.swtworkbench.ed.controls.mro.internal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * The MRO's canvas
 * @author daveo
 */
public class MROCanvas extends Canvas {

    /**
     * @param parent
     * @param style
     */
    public MROCanvas(Composite parent, int style) {
        super(parent, style);
        addFocusListener(new MROFocusListener());
        addPaintListener(new MROPaintListener());
        addTraverseListener(new MROTraverseListener());
        addMouseListener(new MROMouseListener());
    }
    
    private boolean gotFocus = false;

    private class MROFocusListener implements FocusListener {
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.FocusListener#focusGained(org.eclipse.swt.events.FocusEvent)
         */
        public void focusGained(FocusEvent e) {
            gotFocus = true;
            redraw();
        }
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.FocusListener#focusLost(org.eclipse.swt.events.FocusEvent)
         */
        public void focusLost(FocusEvent e) {
            gotFocus = false;
            redraw();
        }
    }
    
    private String helpText = "";
    
    /**
     * @param helpText The helpText to set.
     */
    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }
    
    /**
     * @return Returns the helpText.
     */
    public String getHelpText() {
        return helpText;
    }
    
    private class MROPaintListener implements PaintListener {
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.PaintListener#paintControl(org.eclipse.swt.events.PaintEvent)
         */
        public void paintControl(PaintEvent e) {
            if (!helpText.equals(""));
                e.gc.drawText(helpText, 3, 3, true);
            if (gotFocus) {
                Rectangle bounds = getBounds();
                e.gc.setLineStyle(SWT.LINE_DOT);
                e.gc.setLineWidth(1);
                e.gc.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_RED));
                bounds.width -= 3;
                bounds.height -= 3;
                bounds.x = 0;
                bounds.y = 0;
                e.gc.drawRectangle(bounds);
            }
        }
    }
    
    private class MROTraverseListener implements TraverseListener {
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.TraverseListener#keyTraversed(org.eclipse.swt.events.TraverseEvent)
         */
        public void keyTraversed(TraverseEvent e) {
            e.doit=true;
        }
    }
    
    private class MROMouseListener extends MouseAdapter {
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.MouseAdapter#mouseDown(org.eclipse.swt.events.MouseEvent)
         */
        public void mouseDown(MouseEvent e) {
            setFocus();
        }
    }
}
