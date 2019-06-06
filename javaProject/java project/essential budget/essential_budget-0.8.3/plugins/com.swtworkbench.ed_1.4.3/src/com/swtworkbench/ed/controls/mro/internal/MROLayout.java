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

import java.util.ListIterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

/** (non-api)
 * class MROLayout. A layout manager for the MRO object.
 * @author daveo
 */
public final class MROLayout extends Layout {

	/**
     * Construct an MROLayout.  MROLayout needs to be able to interrogate its
     * parent, so we pass the parent MRO into MROLayout's constructor.
     * 
	 * @param mro
	 */
	public MROLayout(MRO mro) {
		parent=mro;
	}

	private final MRO parent;
    public static final int padding=1;
	
	/* (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Layout#computeSize(org.eclipse.swt.widgets.Composite, int, int, boolean)
	 */
	protected Point computeSize(Composite composite, int wHint, int hHint,
			boolean flushCache) {
		Point result = parent.getCellSize();
		if (result.x >= 0) {
			result.x = (result.x + padding) * parent.getNumColumns();
			result.y *= (result.y + padding) * parent.getNumRows();
		} else
			result = new Point(SWT.DEFAULT, SWT.DEFAULT);
		return result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Layout#layout(org.eclipse.swt.widgets.Composite, boolean)
	 */
	protected void layout(Composite composite, boolean flushCache) {
		Point cellSize = parent.getCellSize();
        Point parentSize = composite.getSize();
        final int numCols = parent.getNumColumns();
        if (cellSize.x * numCols < parentSize.x)
            cellSize.x = parentSize.x / numCols;

        final int numRows = parent.recordObjectEditors.size() / numCols;
        
        // Handle the case of no rows
        if (numRows < 1) {
            return;
        }
        
        ListIterator i = parent.recordObjectEditors.listIterator();
        RecordObjectEditor editor = (RecordObjectEditor) i.next();
        i.previous();
        int editorPosY = editor.getControl().getLocation().y;

        /*
         * Optimize layout algorithm depending on if we're scrolling up or
         * down in order to minimize screen flicker.
         */
        
        if (editorPosY == 0 || editorPosY > cellSize.y + padding) {
            // If we're scrolling up, use this layout algorithm...
            while (i.hasNext()) {
                i.next();
            }
            for (int row = numRows-1; row >= 0; --row) {
                for (int col = 0; col < numCols; ++col) {
                    editor = (RecordObjectEditor) i.previous();
                    int x = col * (cellSize.x + padding);
                    int y = row * (cellSize.y + padding);
                    editor.getControl().setBounds(x, y, cellSize.x, cellSize.y);
                }
            }
        } else {
            // If we're scrolling down, use this layout algorithm...
            for (int row = 0; row < numRows; ++row) {
                for (int col = 0; col < numCols; ++col) {
                    editor = (RecordObjectEditor) i.next();
                    int x = col * (cellSize.x + padding);
                    int y = row * (cellSize.y + padding);
                    editor.getControl().setBounds(x, y, cellSize.x, cellSize.y);
                }
            }
        }
	}
}

