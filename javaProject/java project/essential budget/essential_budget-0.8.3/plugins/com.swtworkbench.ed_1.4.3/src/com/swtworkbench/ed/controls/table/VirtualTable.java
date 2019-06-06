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
package com.swtworkbench.ed.controls.table;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;

import com.swtworkbench.ed.aware.adapters.pojo.UnableToSaveException;
import com.swtworkbench.ed.aware.masterdetail.IDetail;
import com.swtworkbench.ed.aware.masterdetail.IMaster;
import com.swtworkbench.ed.aware.masterdetail.IMasterObjectChangeListener;
import com.swtworkbench.ed.aware.masterdetail.MasterObjectChangeEvent;
import com.swtworkbench.ed.controls.table.internal.SliderHolder;
import com.swtworkbench.ed.controls.table.internal.TableHolder;
import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class VirtualTable.  An SWT table object that uses the Essential Data
 * framework to:<p>
 * 
 * 1) Be data-aware
 * 2) Be virtual (ie: only fetch/edit/display the visible table items)<p>
 * 
 * @author daveo
 */
public final class VirtualTable extends Composite implements IMaster, IDetail {
    private final Table table;
    protected final TableHolder tableHolder;
    
    /**
     * Constructs a new instance of this class given its parent
     * and a style value describing its behavior and appearance.
     * <p>
     * The style value is either one of the style constants defined in
     * class <code>SWT</code> which is applicable to instances of this
     * class, or must be built by <em>bitwise OR</em>'ing together 
     * (that is, using the <code>int</code> "|" operator) two or more
     * of those <code>SWT</code> style constants. The class description
     * lists the style constants that are applicable to the class.
     * Style bits are also inherited from superclasses.
     * </p>
     * <p>
     * Note that even though this class extends Composite, it does not
     * make sense to place a layout on it.
     * </p>
     *
     * @param parent a composite control which will be the parent of the new instance (cannot be null)
     * @param style the style of control to construct
     *
     * @exception IllegalArgumentException <ul>
     *    <li>ERROR_NULL_ARGUMENT - if the parent is null</li>
     * </ul>
     * @exception SWTException <ul>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the parent</li>
     *    <li>ERROR_INVALID_SUBCLASS - if this class is not an allowed subclass</li>
     * </ul>
     *
     * @see SWT#SINGLE
     * @see SWT#MULTI
     * @see SWT#CHECK
     * @see SWT#FULL_SELECTION
     * @see SWT#HIDE_SELECTION
     * @see Widget#checkSubclass
     * @see Widget#getStyle
     */
    public VirtualTable(Composite parent, int style) {
        super(parent, SWT.NULL);
        /*
                <composite>
                    <layoutData x:class="gridData" grabExcessHorizontalSpace="true" grabExcessVerticalSpace="true" horizontalAlignment="GridData.FILL" verticalAlignment="GridData.FILL"/>
                    <layout x:class="gridLayout" marginHeight="0" marginWidth="0" numColumns="1" horizontalSpacing="0" verticalSpacing="0"/>
                    <x:children>
                        <table x:id="table" x:style="BORDER" linesVisible="true" headerVisible="true">
                            <layoutData x:class="gridData" grabExcessHorizontalSpace="true" grabExcessVerticalSpace="true" horizontalAlignment="GridData.FILL" verticalAlignment="GridData.FILL"/>
                        </table>
                    </x:children>
                </composite>
                <composite>
                    <layoutData x:class="gridData" grabExcessVerticalSpace="true" verticalAlignment="GridData.FILL" widthHint="0"/>
                    <layout x:class="fillLayout"/>
                    <x:children>
                        <slider x:style="VERTICAL" x:id="verticalSlider"/>
                    </x:children>
                </composite>
                <composite>
                    <layoutData x:class="gridData" grabExcessHorizontalSpace="true" horizontalAlignment="GridData.FILL" heightHint="0"/>
                    <layout x:class="fillLayout"/>
                    <x:children>
                        <slider x:style="HORIZONTAL" x:id="horizontalSlider"/>
                    </x:children>
                </composite>
            </x:children>
            */
        
        GridLayout layout = new GridLayout(2, false);
        layout.marginHeight=0;
        layout.marginWidth=0;
        layout.horizontalSpacing=0;
        layout.verticalSpacing=0;
        setLayout(layout);
        
        // Children...
        tableHolder = new TableHolder(this, style);
        GridData gd = new GridData();
        gd.grabExcessHorizontalSpace=true;
        gd.grabExcessVerticalSpace=true;
        gd.horizontalAlignment=GridData.FILL;
        gd.verticalAlignment=GridData.FILL;
        tableHolder.setLayoutData(gd);
        table = tableHolder.getControl();
        
        SliderHolder verticalSlider = new SliderHolder(this, SWT.VERTICAL);
        SliderHolder horizontalSlider = new SliderHolder(this, SWT.HORIZONTAL);
        
        // Tell the tableHolder about the scroll bars...
        tableHolder.setVerticalSlider(verticalSlider);
        tableHolder.setHorizontalSlider(horizontalSlider);
    }
    
    /**
     * Returns the column at the given, zero-relative index in the
     * receiver. Throws an exception if the index is out of range.
     * If no <code>TableColumn</code>s were created by the programmer,
     * this method will throw <code>ERROR_INVALID_RANGE</code> despite
     * the fact that a single column of data may be visible in the table.
     * This occurs when the programmer uses the table like a list, adding
     * items but never creating a column.
     *
     * @param index the index of the column to return
     * @return the column at the given index
     *
     * @exception IllegalArgumentException <ul>
     *    <li>ERROR_INVALID_RANGE - if the index is not between 0 and the number of elements in the list minus 1 (inclusive)</li>
     * </ul>
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public TableColumn getColumn (int index) {
        return table.getColumn(index);
    }
    

    /**
     * Returns the number of columns contained in the receiver.
     * If no <code>TableColumn</code>s were created by the programmer,
     * this value is zero, despite the fact that visually, one column
     * of items is may be visible. This occurs when the programmer uses
     * the table like a list, adding items but never creating a column.
     *
     * @return the number of columns
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     * @exception SWTError <ul>
     *    <li>ERROR_CANNOT_GET_COUNT - if the operation fails because of an operating system failure</li>
     * </ul>
     */
    public int getColumnCount () {
        return table.getColumnCount();
    }
    
    /**
     * Returns an array of <code>TableColumn</code>s which are the
     * columns in the receiver. If no <code>TableColumn</code>s were
     * created by the programmer, the array is empty, despite the fact
     * that visually, one column of items may be visible. This occurs
     * when the programmer uses the table like a list, adding items but
     * never creating a column.
     * <p>
     * Note: This is not the actual structure used by the receiver
     * to maintain its list of items, so modifying the array will
     * not affect the receiver. 
     * </p>
     *
     * @return the items in the receiver
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public TableColumn [] getColumns () {
        return table.getColumns();
    }
    
    /**
     * Returns the width in pixels of a grid line.
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public int getGridLineWidth () {
        return table.getGridLineWidth();
    }
    
    /**
     * Returns the height of the receiver's header 
     *
     * @return the height of the header or zero if the header is not visible
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     * 
     * @since 2.0 
     */
    public int getHeaderHeight () {
        return table.getHeaderHeight();
    }
    
    /**
     * Returns <code>true</code> if the receiver's header is visible,
     * and <code>false</code> otherwise.
     * <p>
     * If one of the receiver's ancestors is not visible or some
     * other condition makes the receiver not visible, this method
     * may still indicate that it is considered visible even though
     * it may not actually be showing.
     * </p>
     *
     * @return the receiver's header's visibility state
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public boolean getHeaderVisible () {
        return table.getHeaderVisible();
    }
    
    /**
     * Returns the item at the given, zero-relative index in the
     * receiver. Throws an exception if the index is out of range.
     *
     * @param index the index of the item to return
     * @return the item at the given index
     *
     * @exception IllegalArgumentException <ul>
     *    <li>ERROR_INVALID_RANGE - if the index is not between 0 and the number of elements in the list minus 1 (inclusive)</li>
     * </ul>
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public TableItem getItem (int index) {
        return table.getItem(index);
    }
    
    /**
     * Returns the item at the given point in the receiver
     * or null if no such item exists. The point is in the
     * coordinate system of the receiver.
     *
     * @param point the point used to locate the item
     * @return the item at the given point
     *
     * @exception IllegalArgumentException <ul>
     *    <li>ERROR_NULL_ARGUMENT - if the point is null</li>
     * </ul>
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public TableItem getItem (Point point) {
        return table.getItem(point);
    }
    
    /**
     * Returns the number of items contained in the receiver.
     *
     * @return the number of items
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public int getItemCount () {
        return table.getItemCount();
    }
    
    /**
     * Returns the height of the area which would be used to
     * display <em>one</em> of the items in the receiver's.
     *
     * @return the height of one item
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public int getItemHeight () {
        return table.getItemHeight();
    }
    
    /**
     * Returns an array of <code>TableItem</code>s which are the items
     * in the receiver. 
     * <p>
     * Note: This is not the actual structure used by the receiver
     * to maintain its list of items, so modifying the array will
     * not affect the receiver. 
     * </p>
     *
     * @return the items in the receiver
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public TableItem [] getItems () {
        return table.getItems();
    }
    
    /**
     * Returns <code>true</code> if the receiver's lines are visible,
     * and <code>false</code> otherwise.
     * <p>
     * If one of the receiver's ancestors is not visible or some
     * other condition makes the receiver not visible, this method
     * may still indicate that it is considered visible even though
     * it may not actually be showing.
     * </p>
     *
     * @return the visibility state of the lines
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public boolean getLinesVisible () {
        return table.getLinesVisible();
    }
    
    /**
     * Returns the editor control associated with the table cell that
     * currently has focus.
     *
     * @return the editor control with focus
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public Control getSelectedControl() {
        return tableHolder.getSelectedControl();
    }
    
    /**
     * Returns the control associated with the editor cell in the specified
     * location relative to the top left of the visible table.
     *
     * @param col The cell's column
     * @param row The cell's row relative to the top of the visible table
     *
     * @return The editor cell associated with (col, row)
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public Control getCellControl(int col, int row) {
        return tableHolder.getCellControl(col, row);
    }
    
    /**
     * Returns the control associated with the editor cell in the specified
     * location relative to the top left of the visible table.
     *
     * @param p A Point representing the (col, row) of the desired cell
     *
     * @return The editor cell associated with (col, row)
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public Control getCellControl(Point p) {
        return tableHolder.getCellControl(p.x, p.y);
    }
    
    /**
     * Returns the number of selected items contained in the receiver.
     *
     * @return the number of selected items
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public int getSelectionCount () {
        return 1;
    }
    
    /**
     * Returns the object that is being edited in the selected row
     * 
     * @return the object that is currently being edited.
     */
    public Object getSelection() {
        return tableHolder.getSelection();
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IMaster#getSelection(int)
     */
    public Object getMasterObject(int row) {
        return tableHolder.getRowObject(row);
    }


    /**
     * Returns the zero-relative coordinates, relative to the top-left 
     * of the table, of the item which is currently selected in the 
     * receiver, or -1 if no item is selected.
     *
     * @return the coordinates of the selected cell
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public Point getSelectionCoordinates () {
        return tableHolder.getSelectionCoordinates();
    }
    
    /**
     * Returns the zero-relative index relative to the top of the 
     * table of the item which is currently selected in the receiver, 
     * or -1 if no item is selected.
     *
     * @return the index of the selected item
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public int getSelectionIndex () {
        return tableHolder.getSelectionIndex();
    }
    
    /**
     * Returns the zero-relative indices of the items which are currently
     * selected in the receiver.  The array is empty if no items are selected.
     * <p>
     * Note: This is not the actual structure used by the receiver
     * to maintain its selection, so modifying the array will
     * not affect the receiver. 
     * </p>
     * @return the array of indices of the selected items
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public int [] getSelectionIndices () {
        return new int[] {getSelectionIndex()};
    }
    
    /**
     * Returns the zero-relative index of the column that has focus within
     * the selected row.<p>
     * 
     * @return The currently-selected column
     */
    public int getSelectionColumn() {
        return tableHolder.getSelectionColumn();
    }
    
    /**
     * Returns the zero-relative index of the item which is currently
     * at the top of the receiver. This index can change when items are
     * scrolled or new items are added or removed.
     *
     * @return the index of the top item
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public int getTopIndex () {
        return tableHolder.getTopIndex();
    }
    
    /**
     * Searches the receiver's list starting at the first column
     * (index 0) until a column is found that is equal to the 
     * argument, and returns the index of that column. If no column
     * is found, returns -1.
     *
     * @param column the search column
     * @return the index of the column
     *
     * @exception IllegalArgumentException <ul>
     *    <li>ERROR_NULL_ARGUMENT - if the string is null</li>
     * </ul>
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public int indexOf (TableColumn column) {
        return table.indexOf(column);
    }
    
    /**
     * Returns <code>true</code> if the item is selected,
     * and <code>false</code> otherwise.  Indices out of
     * range are ignored.
     *
     * @param index the index of the item
     * @return the visibility state of the item at the index
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public boolean isSelected (int index) {
        return index == getSelectionIndex();
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IMaster#addRowChangeListener(com.swtworkbench.ed.controls.table.RowChangeListener)
     */
    public void addMasterObjectChangeListener (IMasterObjectChangeListener listener) {
        tableHolder.addRowChangeListener(listener);
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IMaster#removeRowChangeListener(com.swtworkbench.ed.controls.table.RowChangeListener)
     */
    public void removeMasterObjectChangeListener (IMasterObjectChangeListener listener) {
        tableHolder.removeRowChangeListener(listener);
    }
    
    //---- Manage any master-detail relationships in which this table is participating
    
    protected IMaster master = null;
    
    private IMasterObjectChangeListener masterChangeListener = new IMasterObjectChangeListener() {
        public void requestChange(MasterObjectChangeEvent e) {
            if (tableHolder.getInput() != null)
                try {
                    tableHolder.validateAllFields();
                } catch (UnableToSaveException e1) {
                    e.doit = false;
                }
        }
        public void masterObjectChanged(MasterObjectChangeEvent e) {
            try {
                Object newInput = master.getMasterObject(e.newSelection.y);
                setInput(newInput);
            } catch (Exception ex) {
                Logger.log().error(ex, "Unable to set the input even though all fields validated correctly.");
            }
        }
    };
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IDetail#setMaster(com.swtworkbench.ed.aware.interfaces.IMaster)
     */
    public void setMaster(IMaster master) {
        if (this.master != null) {
            this.master.removeMasterObjectChangeListener(masterChangeListener);
        }
        this.master = master;
        master.addMasterObjectChangeListener(masterChangeListener);
    }
    
    /**
     * Adds the listener to the collection of listeners who will
     * be notified when keys are pressed and released on the system keyboard, by sending
     * it one of the messages defined in the <code>KeyListener</code>
     * interface.
     *
     * @param listener the listener which should be notified
     *
     * @exception IllegalArgumentException <ul>
     *    <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     * </ul>
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     *
     * @see KeyListener
     * @see #removeKeyListener
     */
    public void addKeyListener (KeyListener listener) {
        if (listener == null) SWT.error (SWT.ERROR_NULL_ARGUMENT);
        tableHolder.addKeyListener(listener);
    }
    
    /**
     * Removes the listener from the collection of listeners who will
     * be notified when keys are pressed and released on the system keyboard.
     *
     * @param listener the listener which should be notified
     *
     * @exception IllegalArgumentException <ul>
     *    <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     * </ul>
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     *
     * @see KeyListener
     * @see #addKeyListener
     */
    public void removeKeyListener (KeyListener listener) {
        if (listener == null) SWT.error (SWT.ERROR_NULL_ARGUMENT);
        tableHolder.removeKeyListener(listener);
    }
    
    /**
     * Adds the listener to the collection of listeners who will
     * be notified when the receiver's focus changes, by sending
     * it one of the messages defined in the <code>FocusListener</code>
     * interface.
     *
     * @param listener the listener which should be notified
     *
     * @exception IllegalArgumentException <ul>
     *    <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     * </ul>
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     *
     * @see FocusListener
     * @see #removeFocusListener
     * @see FocusEvent
     */
    public void addFocusListener (FocusListener listener) {
        if (listener == null) SWT.error (SWT.ERROR_NULL_ARGUMENT);
        tableHolder.addFocusListener(listener);
    }
    
    /**
     * Removes the listener from the collection of listeners who will
     * be notified when the receiver's focus changes.
     *
     * @param listener the listener which should no longer be notified
     *
     * @exception IllegalArgumentException <ul>
     *    <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     * </ul>
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     *
     * @see FocusListener
     * @see #addFocusListener
     */
    public void removeFocusListener(FocusListener listener) {
        if (listener == null) SWT.error (SWT.ERROR_NULL_ARGUMENT);
        tableHolder.removeFocusListener(listener);
    }
    
    /**
     * Selects the item at the given zero-relative index in the receiver. 
     * If the item at the index was already selected, it remains
     * selected. Indices that are out of range are ignored.
     *
     * @param index the index of the item to select
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public void select (int index) {
        tableHolder.select(index);
    }
    
    /**
     * Marks the receiver's header as visible if the argument is <code>true</code>,
     * and marks it invisible otherwise. 
     * <p>
     * If one of the receiver's ancestors is not visible or some
     * other condition makes the receiver not visible, marking
     * it visible may not actually cause it to be displayed.
     * </p>
     *
     * @param visible the new visibility state
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public void setHeaderVisible (boolean show) {
        table.setHeaderVisible(show);
    }
    
    /**
     * Marks the receiver's lines as visible if the argument is <code>true</code>,
     * and marks it invisible otherwise. 
     * <p>
     * 
     * @param visible the new visibility state
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public void setLinesVisible (boolean show) {
        table.setLinesVisible(show);
    }
    
    /**
     * Selects the item at the given zero-relative index in the receiver. 
     * The current selected is first cleared, then the new item is selected.
     *
     * @param index the index of the item to select
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     *
     * @see Table#deselectAll()
     * @see Table#select(int)
     */
    public void setSelection (int index) {
        tableHolder.select(index);
    }
    
    /**
     * Sets the zero-relative index of the item which is currently
     * at the top of the receiver. This index can change when items
     * are scrolled or new items are added and removed.<p>
     * 
     * If there is unsaved data in an invalid state, this fails silently.<p>
     * 
     * Use validateUnsavedData() to determine if it is safe to call this
     * method.<p>
     *
     * @param index the index of the top item
     *
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     */
    public void setTopIndex (int index) {
        tableHolder.setTopIndex(index);
    }
    
    /**
     * Validates all unsaved (dirty) fields in the table.  If any 
     * fields are in an invalid state, throws UnableToSaveException.
     * The exception's message is the validation error message of the
     * invalid field.<p>
     * 
     * @throws UnableToSaveException if any field is invalid and cannot be saved
     */
    public void validateUnsavedData() throws UnableToSaveException {
        tableHolder.validateAllFields();
    }
    
    /**
     * Method isInsertEnabled.  Returns if insertion is enabled.
     * @return true if insertion is enabled; false otherwise.  true is the default.
     */
    public boolean isInsertEnabled() {
        return tableHolder.isInsertEnabled();
    }
    
    /**
     * Method setInsertEnabled.  Sets the insertion enabled status.
     * @param enabled true if insertion should be enabled; false otherwise.
     */
    public void setInsertEnabled(boolean enabled) {
        tableHolder.setInsertEnabled(enabled);
    }
    
    /**
     * Method isDeleteEnabled.  Returns if object deletion is enabled. 
     * @return true if deletion is enabled; false otherwise.  true is the default.
     */
    public boolean isDeleteEnabled() {
        return tableHolder.isDeleteEnabled();
    }
    
    /**
     * Method setDeleteEnabled.  Sets if deletion is allowed.
     * @param enabled true if deletion should be allowed; false otherwise.
     */
    public void setDeleteEnabled(boolean enabled) {
        tableHolder.setDeleteEnabled(enabled);
    }
    
    /**
     * Associates a column with a property name in the object to
     * be edited.  Call this method repeatedly for as many columns as your
     * Table supports.  It does not matter if the TableColumn objects actually
     * exist when this method is called as long as they exist before edit()
     * is called.
     * 
     * @param columnPropertyName The String name of the property to edit.
     */
    public void addColumnProperty(String columnPropertyName) {
        tableHolder.addColumnProperty(columnPropertyName);
    }
    
    /**
     * Method setInput.  Changes the input object for the VirtualTable.  Before
     * this API may be called, the name of the property to edit must be set
     * by either calling #setPropertyName() or by calling some version of
     * #edit(). 
     * 
     * @param bean The new input bean
     * 
     * @throws UnableToSaveException If existing data fails validation checks
     */
    public void setInput(Object bean) throws UnableToSaveException 
    {
        tableHolder.setInput(bean);
    }

    /**
     * Method edit.  Attach a container-full of objects to this Table.  Call
     * this method when the Table is entirely set up and otherwise ready to
     * display/edit your data.<p>
     * 
     * This version of edit() expects there to be a single (dummy) item in
     * the table hooked up to a single row of cell editor controls.  It will
     * use the type of these controls plus their style bits to create the 
     * actual controls that will be used to edit the table's data.  If a 
     * background color or text property has been set, it will remember this
     * as well when it creates the new control.<p>
     * 
     * If you need more control over the actual controls that are created to
     * edit data (ie: you need to set properties), use the other version of
     * edit that allows you to specify a callback object that can create
     * edit controls for each column.
     * 
     * @param bean The bean to edit
     * @param property The name of the property containing the collection to edit
     * 
     * @throws SecurityException The usual reflection exceptions
     * @throws NoSuchMethodException The usual reflection exceptions
     * @throws IllegalAccessException The usual reflection exceptions
     * @throws InvocationTargetException The usual reflection exceptions
     * @throws UnableToSaveException If existing modified data fails validation checks
     */
    public void edit(Object bean, String property)
        throws
            SecurityException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            UnableToSaveException 
    {
        tableHolder.edit(bean, property);
    }

    /**
     * Method edit.  Edit the objects in the specified container using this 
     * Table.  Call this method when the Table is entirely set up and 
     * otherwise ready to display/edit your data.<p>
     * 
     * This version of edit expects an IEditControlFactory that will create
     * the editors that will be used to edit each cell's data.<p>
     * 
     * @see com.swtworkbench.ed.aware.interfaces.navigators.AbstractNavigator#edit(java.lang.Object, java.lang.String)
     * 
     * @param bean The bean to edit
     * @param property The name of the property containing the collection to edit
     * @param editControlCreator The IEditControlFactory that will create each column's editors.
     * 
     * @throws SecurityException The usual reflection exceptions
     * @throws NoSuchMethodException The usual reflection exceptions
     * @throws IllegalAccessException The usual reflection exceptions
     * @throws InvocationTargetException The usual reflection exceptions
     * @throws UnableToSaveException If existing data fails validation checks
     */
    public void edit(Object bean, String property, IEditControlFactory editControlCreator)
        throws
            SecurityException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            UnableToSaveException 
    {
        tableHolder.edit(bean, property, editControlCreator);
    }
    
    /**
     * Method getInput.  Returns the current input bean.
     * 
     * @return The current input bean
     */
    public Object getInput() {
        return tableHolder.getInput();
    }
    
    /**
     * Method setInputProperty.  Sets the input property name.
     * 
     * @param propertyName The name of the property containing the collection 
     * to edit on the input object.
     */
    public void setInputProperty(String propertyName) {
        tableHolder.setInputProperty(propertyName);
    }
    
    /**
     * Method getInputProperty.  Returns the current input property name.
     * 
     * @return The current input property name
     */
    public Object getInputProperty() {
        return tableHolder.getInputProperty();
    }
    
    /**
     * Method getControl.  Returns the underlying table control
     * 
     * @return Table the underlying Table control
     */
    public Control getControl() {
        return table;
    }

}


