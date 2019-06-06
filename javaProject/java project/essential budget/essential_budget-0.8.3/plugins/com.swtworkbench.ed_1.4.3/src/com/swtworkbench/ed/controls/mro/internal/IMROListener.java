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

import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;

import com.swtworkbench.ed.controls.table.internal.SliderHolder;

/**
 * Class IMROListener.  This defines the interface between the MRO and any object
 * that wants to use it (such as an MROTable).
 * 
 * @author djo
 */
public interface IMROListener {
	/**
     * Forwards the underlying IRecordObjectAdapter's valid event up the
     * containership tree.
     * 
	 * @param sender The RecordObjectEditor managing the underlying cell.
	 * @param isValid If the IRecordObjectAdapter's editors are all valid.
	 */
	public abstract void elementValid(RecordObjectEditor sender, boolean isValid);

	/**
     * Forwards the SWT focusGained event from the underlying edit control.
     * 
     * @param sender The RecordObjectEditor managing the underlying cell.
	 * @param e The SWT event
	 */
	public abstract void elementFocusGained(RecordObjectEditor sender, FocusEvent e);

	/**
     * Forwards the SWT focusLost event from the underlying edit control.
     * 
     * @param sender The RecordObjectEditor managing the underlying cell.
     * @param e The SWT event
	 */
	public abstract void elementFocusLost(RecordObjectEditor sender, FocusEvent e);

	/**
     * Forwards the SWT keyPressed event from the underlying edit control.
     * 
     * @param sender The RecordObjectEditor managing the underlying cell.
     * @param e The SWT event
	 */
	public abstract void elementKeyPressed(RecordObjectEditor sender, KeyEvent e);

	/**
     * Forwards the SWT keyReleased event from the underlying edit control.
     * 
     * @param sender The RecordObjectEditor managing the underlying cell.
     * @param e The SWT event
	 */
	public abstract void elementKeyReleased(RecordObjectEditor sender, KeyEvent e);

	/**
     * Forwards the SWT keyTraversed event from the underlying edit control.
     * 
     * @param sender The RecordObjectEditor managing the underlying cell.
     * @param e The SWT event
	 */
	public abstract void elementKeyTraversed(RecordObjectEditor sender,
			TraverseEvent e);

    /**
     * Indicate that a cell's data needs to be refreshed.
     * 
     * @param sender
     *            The RecordObjectEditor managing the underlying cell.
     * @param position
     *            The offset of the RecordObjectEditor relative to the 0th
     *            RecordObjectEditor in the MRO.
     */
    public abstract void cellRefresh(RecordObjectEditor sender, int position);

    /**
     * The MRO was just initialized or resized.  Update the vertical slider,
     * @param sender The MRO that was just resized
     */
    public abstract void mroResized(MRO sender);

    /**
     * Tell the MROListener that a selection event just happened on the
     * vertical slider
     * 
     * @param verticalSlider The vertical slider.
     * @param e The SelectionEvent
     */
    public abstract void vertSliderSelected(SliderHolder verticalSlider, SelectionEvent e);

}