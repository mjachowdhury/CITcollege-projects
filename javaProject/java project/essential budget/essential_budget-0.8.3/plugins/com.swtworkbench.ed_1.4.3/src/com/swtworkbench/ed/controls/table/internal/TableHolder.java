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
package com.swtworkbench.ed.controls.table.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.swtworkbench.ed.aware.adapters.pojo.POJOFieldAdapter;
import com.swtworkbench.ed.aware.adapters.pojo.POJOObjectAdapter;
import com.swtworkbench.ed.aware.adapters.pojo.UnableToSaveException;
import com.swtworkbench.ed.aware.interfaces.IRecordObjectAdapter;
import com.swtworkbench.ed.aware.interfaces.IRecordValidListener;
import com.swtworkbench.ed.aware.iterator.IAwareIterator;
import com.swtworkbench.ed.aware.iterator.IteratorAdapterFactory;
import com.swtworkbench.ed.aware.masterdetail.IMasterObjectChangeListener;
import com.swtworkbench.ed.aware.masterdetail.MasterObjectChangeEvent;
import com.swtworkbench.ed.aware.model.IModelChangeListener;
import com.swtworkbench.ed.aware.model.IObjectFactory;
import com.swtworkbench.ed.aware.model.Model;
import com.swtworkbench.ed.aware.model.ModelChangeAdapter;
import com.swtworkbench.ed.aware.model.NewObject;
import com.swtworkbench.ed.controls.table.IEditControlFactory;
import com.swtworkbench.ed.controls.table.VirtualTable;
import com.swtworkbench.ed.reflect.Property;
import com.swtworkbench.ed.reflect.Reflect;
import com.swtworkbench.swtutils.logger.Logger;

/** (non-API)
 * Class TableHolder.  A Composite that holds a Table and manages its size
 * 
 * @author daveo
 */
public class TableHolder extends Composite {
    private final VirtualTable parent;
    protected final Table table;
    protected SliderHolder verticalSlider;
    private SliderHolder horizontalSlider;

    /*
     * Initialization...
     */
    
    public TableHolder(Composite parent, int style) {
        super(parent, SWT.NULL);
        this.parent = (VirtualTable)parent;
        table = new Table(this, style);
        setBackground(table.getBackground());
        addControlListener(controlListener);
        table.addFocusListener(tableFocusListener);
        table.addKeyListener(tableKeyListener);
    }
    
    public Table getControl() {
        return table;
    }
    
    public void setVerticalSlider(SliderHolder verticalSlider) {
        this.verticalSlider = verticalSlider;
        verticalSlider.getSlider().addSelectionListener(verticalSliderSelectionListener);
    }

    public void setHorizontalSlider(SliderHolder horizontalSlider) {
        this.horizontalSlider = horizontalSlider;
        horizontalSlider.getSlider().addSelectionListener(horizontalSliderSelectionListener);
    }
    
    /*
     * ColumnProperty stuff
     */

    private LinkedList columnPropertyNames = new LinkedList();
    
    public void addColumnProperty(String columnPropertyName) {
        columnPropertyNames.add(columnPropertyName);
    }
    
    /*
     * Attach a data set...
     */

    protected Object container;
    protected IAwareIterator iterator;
    protected Method getSize;
    
    private void attachContainer(Object container)
        throws
            SecurityException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException
    {
        if (this.container != null) {
            Model.getDefault().removeModelChangeListener(this.container, modelChangeListener);
        }
        this.container = container;
        Model.getDefault().addModelChangeListener(container, modelChangeListener);
        this.iterator = IteratorAdapterFactory.iterator(container);
        getSize = container.getClass().getMethod("size", new Class[] {});
    }

    protected int getContainerSize() {
        Integer result = (Integer)Reflect.invokei(container, getSize, new Object[] {});
        if (result != null)
            return result.intValue();
        else
            return 0;
    }
    
    private String propertyName = null;
    private Property property = null;
    private IObjectFactory objectFactory = null;
    private IModelChangeListener propertyModelChangeListener = null;
    
    public void setInputProperty(String propertyName) {
        this.propertyName = propertyName;
    }
    
    public void setInput(Object bean) throws UnableToSaveException 
    {
        if (property == null && propertyName == null)
            throw new IllegalArgumentException("Must provide a property name before calling setInput()");
            
        if (property == null)
            property = new Property(bean, propertyName);            
            
        if (objectFactory != null) {
            Model.getDefault().unregisterObjectFactory(property.getInput());
        }
        if (propertyModelChangeListener != null) {
            Model.getDefault().removeModelChangeListener(property.getInput(), propertyModelChangeListener);
        }
        
        property = new Property(bean, property.getPropertyName());
        objectFactory = property.getObjectFactory();
        
        if (objectFactory != null) {
            Model.getDefault().registerObjectFactory(property.get(), objectFactory);
        }
        propertyModelChangeListener = property.getChangeListener();
        if (propertyModelChangeListener != null) {
            Model.getDefault().addModelChangeListener(property.get(), propertyModelChangeListener);
        }
        
        try {
            setCollection(property.get());
        } catch (SecurityException e) {
            throw new UnableToSaveException(e);
        } catch (NoSuchMethodException e) {
            throw new UnableToSaveException(e);
        } catch (IllegalAccessException e) {
            throw new UnableToSaveException(e);
        } catch (InvocationTargetException e) {
            throw new UnableToSaveException(e);
        } catch (UnableToSaveException e) {
            throw e;
        }
    }
    
    public void edit(Object bean, String propertyName) throws
        SecurityException,
        NoSuchMethodException,
        IllegalAccessException,
        InvocationTargetException,
        UnableToSaveException 
    {
        this.propertyName = propertyName;
        
        if (objectFactory != null) {
            Model.getDefault().unregisterObjectFactory(property.getInput());
        }
        if (propertyModelChangeListener != null) {
            Model.getDefault().removeModelChangeListener(property.getInput(), propertyModelChangeListener);
        }
        property = new Property(bean, propertyName);
        objectFactory = property.getObjectFactory();
        if (objectFactory != null) {
            Model.getDefault().registerObjectFactory(property.get(), objectFactory);
        }
        propertyModelChangeListener = property.getChangeListener();
        if (propertyModelChangeListener != null) {
            Model.getDefault().addModelChangeListener(property.get(), propertyModelChangeListener);
        }
        setCollection(property.get());
    }
    
    public void edit(Object bean, String propertyName, IEditControlFactory editControlCreator) throws
        SecurityException,
        NoSuchMethodException,
        IllegalAccessException,
        InvocationTargetException,
        UnableToSaveException 
    {
        this.propertyName = propertyName;
        
        if (objectFactory != null) {
            Model.getDefault().unregisterObjectFactory(property.getInput());
        }
        if (propertyModelChangeListener != null) {
            Model.getDefault().removeModelChangeListener(property.getInput(), propertyModelChangeListener);
        }
        property = new Property(bean, propertyName);
        objectFactory = property.getObjectFactory();
        if (objectFactory != null) {
            Model.getDefault().registerObjectFactory(property.get(), objectFactory);
        }
        propertyModelChangeListener = property.getChangeListener();
        if (propertyModelChangeListener != null) {
            Model.getDefault().addModelChangeListener(property.get(), propertyModelChangeListener);
        }
        setCollection(property.get(), editControlCreator);
    }
    
    /**
     * Method getInput.  Returns the input object
     * @return The input object
     */
    public Object getInput() {
        if (property != null)
            return property.getInput();
        else
            return null;
    }
    
    /**
     * Method getPropertyName.  Returns the name of the property containing
     * the collection to be edited.
     * 
     * @return The name of the property containing the collection to edit
     */
    public String getInputProperty() {
        return propertyName;
    }

    private void setCollection(Object container)
        throws
            SecurityException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            UnableToSaveException 
    {
        // If something needs saving, make sure we can before we get started...
        validateAllFields();
        
        attachContainer(container);

        // If this is the first time...
        if (currentControlCreator == null) {
            currentControlCreator = new PrototypeBasedControlFactory(table);
        }
        
        loadTable();
    }

    private void setCollection(Object container, IEditControlFactory editControlCreator)
        throws
            SecurityException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            UnableToSaveException 
    {
        // If something needs saving, make sure we can before we get started...
        validateAllFields();
        
        attachContainer(container);

        currentControlCreator = editControlCreator;
        loadTable();
    }
    
    /*
     * Manage visible editors...
     */
    
    // Public interface to visible editors... //
    
    public int getSelectionIndex() {
        return curRow;
    }
    
    public int getSelectionColumn() {
        return curCol;
    }
    
    public Point getSelectionCoordinates() {
        return new Point(curCol, curRow);
    }
    
    public int getTopIndex() {
        return getTopRow();
    }
    
    public void select(int index) {
        if (index < 0 || index >= rows.size())
            throw new IllegalArgumentException("Attempted to select a nonexistant row");
        cell(0, index).setFocus();
    }
    
    public Object getRowObject(int rowNum) {
        if (rows.size() > 0) {
            Row row = (Row) rows.get(rowNum);
            return row.objectAdapter.getInput();
        }
        return null;
    }
    
    public Object getSelection() {
        if (rows.size() > 0) {
            Row row = (Row) rows.get(curRow);
            return row.objectAdapter.getInput();
        }
        return null;
    }
    
    public Control getSelectedControl() {
        return cell(curCol, curRow);
    }
    
    public Control getCellControl(int col, int row) {
        return cell(col, row);
    }
    
    public void setTopIndex(int topIndex) {
        try {
            validateAllFields();
            setTopRow(topIndex);
            resizeTableContents();
            refreshData();
        } catch (UnableToSaveException e) {
            // Intentionally ignore this if it happens (see JavaDoc for API)
        }
    }
    
    // Encapsulate the top row field
    
    protected void setTopRow(int topRow) {
        this.topRow = topRow;
        verticalSlider.getSlider().setSelection(topRow);
    }

    protected int getTopRow() {
        return topRow;
    }

    // Focus listener handling...
    
    private LinkedList focusListeners = new LinkedList();
    
    public void addFocusListener(FocusListener l) {
        focusListeners.addLast(l);
    }
    
    public void removeFocusListener(FocusListener l) {
        focusListeners.remove(l);
    }
    
    protected void fireFocusGainedEvent(FocusEvent e) {
        for (Iterator i = focusListeners.iterator(); i.hasNext();) {
            FocusListener element = (FocusListener) i.next();
            element.focusGained(e);
        }
    }

    protected void fireFocusLostEvent(FocusEvent e) {
        for (Iterator i = focusListeners.iterator(); i.hasNext();) {
            FocusListener element = (FocusListener) i.next();
            element.focusLost(e);
        }
    }
    
    // Key listener handling...
    
    private LinkedList keyListeners = new LinkedList();
    
    public void addKeyListener(FocusListener l) {
        keyListeners.addLast(l);
    }
    
    public void removeKeyListener(FocusListener l) {
        keyListeners.remove(l);
    }
    
    public void fireKeyPressedEvent(KeyEvent e) {
        e.widget=parent;
        for (Iterator i = keyListeners.iterator(); i.hasNext();) {
            KeyListener element = (KeyListener) i.next();
            element.keyPressed(e);
        }
    }
    
    public void fireKeyReleasedEvent(KeyEvent e) {
        e.widget=parent;
        for (Iterator i = keyListeners.iterator(); i.hasNext();) {
            KeyListener element = (KeyListener) i.next();
            element.keyReleased(e);
        }
    }
    
    // Row change event handling...
    
    private LinkedList rowChangeListeners = new LinkedList();
    
    public void addRowChangeListener(IMasterObjectChangeListener l) {
        rowChangeListeners.addLast(l);
    }
    
    public void removeRowChangeListener(IMasterObjectChangeListener l) {
        rowChangeListeners.remove(l);
    }
    
    protected boolean fireRowChangeRequest(int oldCol, int oldRow, int newCol, int newRow) {
        MasterObjectChangeEvent e = new MasterObjectChangeEvent(parent, oldCol, oldRow, newCol, newRow);
        for (Iterator i = rowChangeListeners.iterator(); i.hasNext();) {
            IMasterObjectChangeListener element = (IMasterObjectChangeListener) i.next();
            element.requestChange(e);
        }
        return e.doit;
    }

    protected void fireRowChangeEvent(int oldCol, int oldRow, int newCol, int newRow) {
        MasterObjectChangeEvent e = new MasterObjectChangeEvent(parent, oldCol, oldRow, newCol, newRow);
        for (Iterator i = rowChangeListeners.iterator(); i.hasNext();) {
            IMasterObjectChangeListener element = (IMasterObjectChangeListener) i.next();
            element.masterObjectChanged(e);
        }
    }

    // Visible editor implementation...

    /*
     * Track number/top visible row.  numVisibleRows will change if the Table
     * gets resized.  These instance variables are initialized when the data
     * set is attach()ed to the VirtualTable. 
     */
    protected int numVisibleRows = -1;            // Number of visible rows
    protected int topRow = -1;                    // The top visible row
    protected int curCol=0, curRow=0;             // The currently-focused cell
    protected ArrayList rows = new ArrayList();   // The visible editor rows
    private IEditControlFactory currentControlCreator = null;
    
    private class Row {
        public IRecordObjectAdapter objectAdapter = new POJOObjectAdapter();
        public TableItem item = null;
        protected Control[] cols = null;
        
        public Row(int numColumns) {
            cols = new Control[numColumns];
            objectAdapter.addRecordValidListener(recordValidListener);
        }
        
        public void setCell(int col, Control control) {
            cols[col] = control;
            control.addFocusListener(focusListener);
            control.addKeyListener(keyListener);
            control.addTraverseListener(traverseListener);
        }
        public Control cell(int col) {
            return cols[col];
        }
        public int rowNum() {
            for (int result=0; result<table.getItemCount(); ++result) {
                if (item == table.getItem(result))
                    return result;
            }
            return -1;
        }
        public int colNum(Control control) {
            for (int result=0; result < cols.length; ++result) {
                if (control == cols[result])
                    return result;
            }
            return -1;
        }
        private IRecordValidListener recordValidListener = new IRecordValidListener() {
            public void isValid(boolean isValid) {
                rowValid(this, isValid);
            }
        };
        private FocusListener focusListener = new FocusListener() {
            private void goBackToTheOldCell() {
                Display.getCurrent().asyncExec(new Runnable() {
                    public void run() {
                        setFocus(curCol, curRow);
                    }
                });
            }
            public void focusGained(FocusEvent e) {
                int newCol = colNum((Control)e.widget);
                int newRow = rowNum();

                // If we're locked into the old row, then go back there
                if (!invalidRows.isEmpty() && newRow != curRow && !iCreated) {
                    goBackToTheOldCell();
                    return;
                }
                
                // Fire the row change event to give clients a chance
                // to override this action
                if (curRow != newRow || (curCol==newCol && curRow==newRow)) {
                    if (!fireRowChangeRequest(curCol, curRow, newCol, newRow)) {
                        goBackToTheOldCell();
                        return;
                    }
                }
                
                // See if the selection was resized out of view
                boolean selectionWasInvisible=false;
                if (curRow >= numVisibleRows)
                    selectionWasInvisible=true;
                
                // Keep track of the new current col/row
                curCol = newCol;
                curRow = newRow;
                fireRowChangeEvent(curCol, curRow, newCol, newRow);
                fireFocusGainedEvent(e);
                
                // If the selection was resized out of view and is now in view,
                // remove the unnecessary rows.
                if (selectionWasInvisible && curRow < numVisibleRows)
                    resizeTableContents();
            }
            public void focusLost(FocusEvent e) {
                fireFocusLostEvent(e);
            }
        };
        private KeyListener keyListener = new KeyListener() {
            public void keyPressed(KeyEvent e) {
                fireKeyPressedEvent(e);
                try {
                    final int numObjects = getContainerSize();
                    final int possibleVisibleRows =
                        (getClientArea().height
                            - table.getHeaderHeight()
                            - 2 * table.getGridLineWidth())
                            / table.getItemHeight();
                    
                    switch (e.keyCode) {
                        case SWT.ARROW_DOWN:
                            /*
                             * Feature in SWT: Up and down arrow switch the
                             * current choice in a CCombo.  Therefore, we
                             * can't also move the focus using up and down arrow.
                             * 
                             * SWT's behavior is the same even if CTRL or ALT
                             * is held down, so we can't even use Ctrl-arrow
                             * to change focus.
                             */
                            if (cell(curCol) instanceof CCombo && e.stateMask == 0) break;

                            objectAdapter.validateAllFields();
                            if (curRow < numVisibleRows-1)
                                setFocus(curCol, curRow+1);
                            else if (getTopRow() + numVisibleRows < numObjects){
                                table.setRedraw(false);
                                try {
                                    setTopRow(getTopRow() + 1);
                                    refreshData();
                                } finally {
                                    table.setRedraw(true);
                                }
                            }
                            return;
                        case SWT.ARROW_UP:
                            /*
                             * Feature in SWT: Up and down arrow switch the
                             * current choice in a CCombo.  Therefore, we
                             * can't also move the focus using up and down arrow.
                             * 
                             * SWT's behavior is the same even if CTRL or ALT
                             * is held down, so we can't even use Ctrl-arrow
                             * to change focus.
                             */
                            if (cell(curCol) instanceof CCombo && e.stateMask == 0) break;

                            objectAdapter.validateAllFields();
                            if (curRow > 0)
                                setFocus(curCol, curRow-1);
                            else if (getTopRow() > 0) {
                                table.setRedraw(false);
                                try {
                                    setTopRow(getTopRow() - 1);
                                    refreshData();
                                    resizeTableContents();
                                } finally {
                                    table.setRedraw(true);
                                }
                            }
                            return;
                        case SWT.PAGE_DOWN:
                            objectAdapter.validateAllFields();
                            table.setRedraw(false);
                            try {
                                if (getTopRow() + possibleVisibleRows >= numObjects) {
                                    setFocus(curCol, table.getItemCount()-1);
                                } else {
                                    setTopRow(getTopRow() + (possibleVisibleRows-3));
                                    resizeTableContents();
                                    refreshData();
                                    fixFocusRow(numObjects);
                                }
                            } finally {
                                table.setRedraw(true);
                            }
                            return;

                        case SWT.PAGE_UP:
                            objectAdapter.validateAllFields();
                            table.setRedraw(false);
                            try {
                                if (getTopRow() < 1) {
                                    setFocus(curCol, 0);
                                } else {
                                    setTopRow(getTopRow() - (possibleVisibleRows-3));
                                    if (getTopRow() < 0) setTopRow(0);
                                    refreshData();
                                    resizeTableContents();
                                }
                            } finally {
                                table.setRedraw(true);
                            }
                            return;

                        case SWT.HOME:
                            if ((e.stateMask & SWT.CONTROL) != 0) {
                                objectAdapter.validateAllFields();
                                table.setRedraw(false);
                                try {
                                    if (getTopRow() < 1) {
                                        setFocus(curCol, 0);
                                    } else {
                                        Display.getCurrent().asyncExec(new Runnable() {
                                            public void run() {
                                                setFocus(curCol, 0);
                                                setTopRow(0);
                                                resizeTableContents();
                                                try {
                                                    refreshData();
                                                } catch (UnableToSaveException e) {
                                                    Logger.log().error(e, "We just got here, there should be nothing to save!");
                                                }
                                            }
                                        });
                                    }
                                } finally {
                                    table.setRedraw(true);
                                }
                                return;
                            }
                            return;
                            
                        case SWT.END:
                            if ((e.stateMask & SWT.CONTROL) != 0) {
                                objectAdapter.validateAllFields();
                                table.setRedraw(false);
                                try {
                                    if (getTopRow() + possibleVisibleRows >= numObjects) {
                                        setFocus(curCol, table.getItemCount()-1);
                                    } else {
                                        Display.getCurrent().asyncExec(new Runnable() {
                                            public void run() {
                                                setTopRow(numObjects - possibleVisibleRows);
                                                resizeTableContents();
                                                try {
                                                    refreshData();
                                                } catch (UnableToSaveException e) {
                                                    Logger.log().error(e, "We just got here, there should be nothing to save!");
                                                }
                                                setFocus(curCol, table.getItemCount()-1);
                                            }
                                        });
                                    }
                                } finally {
                                    table.setRedraw(true);
                                }
                                return;
                            }
                            return;
                        /*
                         * The architecture here is that the actual insertion/
                         * deletion takes place in the key event.  But the UI
                         * updates occur in the IModelChangeListener.
                         */
                        case SWT.INSERT:
                            if (isInsertEnabled()) {
                                Display.getDefault().asyncExec(new Runnable() {
                                    public void run() {
                                        try {
                                            objectAdapter.saveDirtyFields();
                                            objectAdapter.validateAllFields();
                                            iCreated = true;
                                            
                                            // This implicitly fires the "created" event
                                            Model.getDefault().create(container);
                                        } catch (UnableToSaveException e) {
                                            // This is the normal action if the user hasn't saved changes
                                            // and the current changes are invalid.
                                        }
                                    }
                                });
                            }
                            return;
                        case SWT.DEL:
                            int size=0;
                            try {
                                size = getContainerSize();
                            } catch (Exception e1) {}
                        
                            if ((e.stateMask & SWT.CONTROL) != 0 && size > 0 && isDeleteEnabled())
                                Display.getDefault().asyncExec(new Runnable() {
                                    public void run() {
                                        Object toDelete;
                                        try {
                                            toDelete = iterator.at(topRow + curRow);
                                        } catch (NoSuchElementException e) {
                                            // This happens if the user requests
                                            // deletes faster than we can process
                                            // them.  In that case, just eat the
                                            // event.
                                            return;
                                        }
                            
                                        boolean canDelete = Model.getDefault().fireVetoableObjectDeletionEvent(
                                            container,
                                            topRow + curRow,
                                            toDelete);
                                
                                        if (canDelete) {
                                            iterator.remove();
                                            iDeleted = true;
    
                                            Model.getDefault().fireObjectDeletedEvent(
                                                container,
                                                topRow + curRow,
                                                toDelete);
                                        }
                                    }
                                });
                            return;
                    }
                    switch (e.character) {
                        case '\r':
                            int newRow = curRow;
                            int newCol = curCol;
                            if (newRow >= rows.size() && newCol >= cols.length)
                                return;

                            boolean foundCell = false;
                            while (!foundCell) {
                                ++newCol;
                                if (newCol >= cols.length)
                                    newCol = 0;
                                Control editor = getCellControl(newCol, curRow);
                                foundCell=true;
                                if (editor instanceof Label || editor instanceof CLabel)
                                    foundCell = false;
                            }
                            if (newCol <= curCol) {
                                objectAdapter.validateAllFields();
                                ++newRow;
                            }
                            if (newRow >= numVisibleRows  && 
                                    getTopRow() + numVisibleRows < getContainerSize()) {
                                --newRow;
                                table.setRedraw(false);
                                try {
                                    setTopRow(getTopRow() + 1);
                                    refreshData();
                                } finally {
                                    table.setRedraw(true);
                                }
                            }

                            setFocus(newCol, newRow);
                            return;
                    }
                } catch (UnableToSaveException ex) {}
            }
            public void keyReleased(KeyEvent e) {
                fireKeyReleasedEvent(e);
            }
        };

        private TraverseListener traverseListener = new TraverseListener() {
            public void keyTraversed(TraverseEvent e) {
                try {
                    int newRow = curRow;
                    int newCol = curCol;
                    switch (e.detail) {
                        case SWT.TRAVERSE_TAB_NEXT:
                            e.detail = SWT.TRAVERSE_NONE;
                            boolean foundCell = false;
                            while (!foundCell) {
                                ++newCol;
                                if (newCol >= cols.length)
                                    newCol = 0;
                                Control editor = getCellControl(newCol, curRow);
                                foundCell=true;
                                if (editor instanceof Label || editor instanceof CLabel)
                                    foundCell = false;
                            }
                            if (newCol <= curCol) {
                                objectAdapter.validateAllFields();
                                ++newRow;
                            }

                            if (newRow >= numVisibleRows && 
                                    getTopRow() + numVisibleRows < getContainerSize()) {
                                --newRow;
                                table.setRedraw(false);
                                try {
                                    setTopRow(getTopRow() + 1);
                                    refreshData();
                                } finally {
                                    table.setRedraw(true);
                                }
                            }
                            
                            // If the current row has invalid fields, don't permit
                            // the user to leave it
                            if (newRow != curRow && !invalidRows.isEmpty())
                                break;
                                
                            setFocus(newCol, newRow);
                            break;
                        case SWT.TRAVERSE_TAB_PREVIOUS:
                            e.detail = SWT.TRAVERSE_NONE;

                            foundCell = false;
                            while (!foundCell) {
                                --newCol;
                                if (newCol < 0)
                                    newCol = cols.length-1;
                                Control editor = getCellControl(newCol, curRow);
                                foundCell=true;
                                if (editor instanceof Label || editor instanceof CLabel)
                                    foundCell = false;
                            }
                            if (newCol >= curCol) {
                                objectAdapter.validateAllFields();
                                --newRow;
                            }

                            if (newRow < 0 && getTopRow() > 0) {
                                ++newRow;
                                table.setRedraw(false);
                                try {
                                    setTopRow(getTopRow() - 1);
                                    refreshData();
                                    resizeTableContents();
                                } finally {
                                    table.setRedraw(true);
                                }
                            }

                            // If the current row has invalid fields, don't permit
                            // the user to leave it
                            if (newRow != curRow && !invalidRows.isEmpty())
                                break;

                            setFocus(newCol, newRow);
                            break;
                    }
                } catch (UnableToSaveException e1) {}
            }
        };
    }
    
    /*
     * These flags indicate if this TableHolder did the actual inserting/deleting
     */
    protected boolean iDeleted = false;
    protected Object toResort = null;
    protected boolean iCreated = false;
    
    private class VirtualTableModelChangeListener extends ModelChangeAdapter {
        public void objectCreated(Object container, final NewObject newObject) {
            try {
                // Avoid CoModificationException--get a new iterator
                iterator = IteratorAdapterFactory.iterator(container);
            } catch (Exception e) {
                Logger.log().error(e, "Unable to get a new iterator");
            }
    
            int max=getContainerSize();
            
            int lastVisibleRow = topRow + numVisibleRows - 1;
    
            // If I created it, I have to move the focus to the new row so that
            // the user can edit it.  Otherwise, I just refresh if the new object
            // is visible.
            //
            // If the new object is the same object as was just deleted, this
            // means that the object was re-sorted due to an edit and I still
            // have to move the focus to it.
            if (iCreated || newObject.newObject == toResort) {
                // Update the vertical slider
                verticalSlider.getSlider().setMaximum(max);
    
                try {
                    // If the new object is in the visible range, just
                    // refresh the table's data and move the focus to it
                    if (topRow <= newObject.position &&
                        newObject.position <= lastVisibleRow)
                    {
                        resizeTableContents();
                        refreshData();
                        setFocus(curCol, newObject.position - topRow);
                        return;
                    }
                        
                    // If the new object is before the visible range,
                    // scroll the visible range, then select the new object
                    if (newObject.position < topRow) {
                        setTopRow(newObject.position);
                        resizeTableContents();
                        refreshData();
                        setFocus(curCol, 0);
                        return;
                    }
                    
                    // The new object must be after the visible range.
                    // Is the visible range smaller than it could be, given
                    // the window size?  (This happens when there are fewer
                    // total elements than fit in the window.)
                    int numPossibleRows =
                        (getClientArea().height
                            - table.getHeaderHeight()
                            - 2 * table.getGridLineWidth())
                            / table.getItemHeight();
                        
                    if (numVisibleRows < numPossibleRows && newObject.position < topRow + numPossibleRows) {
                        resizeTableContents();
                        setFocus(curCol, newObject.position - topRow);
                        return;
                    }
                        
                    // Nope. Scroll the visible range, then select the new object
                    int newTopRow = newObject.position - numVisibleRows + 1;
                    if (newTopRow < 0) newTopRow = 0;
                    setTopRow(newTopRow);
                    refreshData();
                    setFocus(curCol, newObject.position - newTopRow);
                } catch (UnableToSaveException e1) {
                    // We should never get here if the insert succeeded
                    Logger.log().error(e1, "Exception while refreshing after insert");
                }
            } else {
                // The general idea here is to refresh so that the curRow
                // does not have to change.  We deliberately only allow 
                // invalid data in the current row, so all objects before
                // and after the current row should be in a valid state
                // and therefore can be refreshed.
    
                // Update the scroll bar appropriately
                verticalSlider.getSlider().setMaximum(max);
                int selection = getSelectionIndex() + topRow;
                if (newObject.position <= selection) {
                    verticalSlider.getSlider().setSelection(selection+1);
                }
                
                // If the new object is before the visible region, we don't
                // have to do anything
                if (newObject.position < topRow) return;
                
                // If the new object is after the last possible row, we don't
                // have to do anything
                int numPossibleRows = 
                    (getClientArea().height
                        - table.getHeaderHeight()
                        - 2 * table.getGridLineWidth())
                        / table.getItemHeight();
                int lastPossibleVisibleRow = topRow + numPossibleRows;
                if (lastPossibleVisibleRow < newObject.position) return;
                
                // If we just inserted the 0th row
                if (numVisibleRows < 1) {
                    resizeTableContents();
                } else {
                    // If we've gotten here, we know that the new object is visible.
                    // Is the new object before the current row?
                    if (newObject.position <= curRow && numVisibleRows >= 1) {
                        // Increment the top position and refresh up to (but not 
                        // including) the current row
                        setTopRow(topRow+1);
                        refreshUpToCurrentRow();
                    } else {
                        // Refresh everything after (but not including) the 
                        // current row
                        resizeTableContents();
                        refreshContentsAfterRow(curRow);
                    }
                }
            }
        }
        
        public void objectDeleted(
            Object container,
            int deletedPositionInCollection,
            Object removed) 
        {
            // If the current row's object is being removed and it appears again
            // in another place, we know that the row was re-sorted and we'd
            // better move the focus with it.
            if (removed == getSelection())
                toResort = removed;
            
            // Avoid CoModificationException--if I didn't make the change,
            // I need to get a new iterator.
            if (iDeleted) {
                iDeleted = false;
            } else {
                try {
                    // Avoid CoModificationException--get a new iterator
                    iterator = IteratorAdapterFactory.iterator(container);
                } catch (Exception e) {
                    Logger.log().error(e, "Unable to get a new iterator");
                }
            }
    
            // Next, update the scroll bar...
            
            // Get the new total number of objects and the current selection
            int max=0;
            try {
                max = getContainerSize();
            } catch (Exception e) {
                Logger.log().error(e, "Unable to get a new maxsize");
            }
            int selection = getSelectionIndex() + topRow;
            
            // Make sure the selection is within the allowed range
            if (selection >= max-1) {
                selection = max-1;
                if (selection < 0) selection = 0;
                verticalSlider.getSlider().setSelection(selection);
            }
            // Update the maximum
            verticalSlider.getSlider().setMaximum(max);
    
            // If the object that was deleted is before the visible range or
            // if the object that was deleted is after the visible range, we're
            // done. 
            int lastVisibleRow = topRow + numVisibleRows - 1;
//            Logger.log().debug("ObjectDeleted: deletedPosition=" + deletedPositionInCollection + " topRow=" + topRow + " curRow=" + curRow + " containerSize=" + getContainerSize() + " numVisibleRows=" + numVisibleRows + " lastVisibleRow=" + lastVisibleRow);
            if (deletedPositionInCollection < topRow || deletedPositionInCollection > lastVisibleRow)
                return;
    
            // Get the current row object
            Row currentRow = (Row) rows.get(curRow);
            if (currentRow == null) {
                Logger.log().error(new Exception(), "Unable to get current row object");
            }
    
            // If the deleted object happens to be the current row, undo any
            // changes to it
            if (deletedPositionInCollection - topRow == selection) {
                currentRow.objectAdapter.undoChanges();
                setFocus(curCol, selection);
            }
    
            // If the last object in the collection just got deleted, 
            // clear the 0th row, then we're done
            // 
            // Can't do this.  The correct behavior is to set the focus to 
            // the underlying table object in the delete refresh event
//            if (max <= 0) {
//                currentRow.objectAdapter.clear();
//                currentRow.objectAdapter.setDirty(false);
//                try {
//                    currentRow.objectAdapter.setInput(null);
//                } catch (UnableToSaveException e1) {
//                    Logger.log().error(e1, "Should always be able to edit null");
//                }
//                currentRow.objectAdapter.setDirty(false);
//                return;
//            }
            
            // Add the delete event to the delete refresh queue.  If there
            // are no fields in an invalid state, refresh now.  Otherwise,
            // the refresh will occur automatically when all invalid fields
            // have been changed to a valid state.
            queueDeleteRefresh(new DeleteRefreshEvent(container, deletedPositionInCollection, removed));
            
            if (invalidRows.isEmpty()) {
                processDeleteQueue();
            }
        }
    };
    
    /**
     * Handle inserts/deletes in the underlying container.
     */
    protected IModelChangeListener modelChangeListener = new VirtualTableModelChangeListener();
    
    /**
     * Class DeleteRefreshEvent.  Encapsulates the information required to refresh
     * the table after a delete.
     */
    private class DeleteRefreshEvent {
        public final Object container;
        public final int deletedPositionInCollection;
        public final Object removed;
        
        public DeleteRefreshEvent(Object container, int deletedPosition, Object removed) {
            this.container = container;
            this.deletedPositionInCollection = deletedPosition;
            this.removed = removed;
        }
    }

    private LinkedList deleteRefreshQueue = new LinkedList();
    
    /**
     * Method queueDeleteRefresh.  
     * @param event
     */
    protected void queueDeleteRefresh(DeleteRefreshEvent event) {
        deleteRefreshQueue.addLast(event);
    }

    /**
     * Method processDeleteQueue.
     *   
     * This method is called when the table's data needs to be refreshed
     * due to rows being deleted.
     */
    protected void processDeleteQueue() {
        for (Iterator i = deleteRefreshQueue.iterator(); i.hasNext();) {
            DeleteRefreshEvent event = (DeleteRefreshEvent) i.next();
            i.remove();
            
            // If the deleted item is before, move the focus up
            if (event.deletedPositionInCollection - topRow < curRow && curRow > 0) {
                setFocus(curCol, curRow-1);
            }
            
            // If we deleted the last element in the collection
            if (event.deletedPositionInCollection - topRow >= numVisibleRows - 1 && topRow + numVisibleRows >= getContainerSize() && numVisibleRows > 1) {
                setFocus(curCol, curRow-1);
            }

            // If there are no more items in the collection, make sure the 
            // table gets the focus
            if (topRow <= 0 && getContainerSize() < 1) {
                Display.getDefault().asyncExec(new DeleteRefreshRunnable(event, curRow, numVisibleRows));
                Display.getDefault().asyncExec(new Runnable() {
                    public void run() {
                        table.setFocus();
                    }
                });
                return;
            }

            // Refresh the table contents
            Display.getDefault().asyncExec(new DeleteRefreshRunnable(event, curRow, numVisibleRows));
        }
    }
    
    protected class DeleteRefreshRunnable implements Runnable {
        private DeleteRefreshEvent event;
        private int curRow, numVisibleRows;
        
        public DeleteRefreshRunnable(DeleteRefreshEvent e, int curRow, int numVisibleRows) {
            this.event = e;
            this.curRow = curRow;
            this.numVisibleRows = numVisibleRows;
        }
        
        public void run() {
            if (numVisibleRows < 2 && topRow > 0) {
                setTopRow(topRow-1);
            }

            // Delete the row and move everything up
            resizeTableContents();
            if (getContainerSize() > 0) {
                try {
                    refreshData();
                } catch (UnableToSaveException e) {
                    Logger.log().error(e, "Changes should have been undone.  This should not happen");
                }
            }
        }
    }

    protected static HashSet invalidRows = new HashSet();
    
    protected void rowValid(IRecordValidListener listener, boolean isValid) {
        if (isValid) {
            invalidRows.remove(listener);
        } else {
            invalidRows.add(listener);
        }
        if (invalidRows.isEmpty()) {
            verticalSlider.getSlider().setEnabled(true);
            processDeleteQueue();
        } else {
            verticalSlider.getSlider().setEnabled(false);
        }
    }
    
    /**
     * Method setFocus.  Sets the focus to the specified row and column 
     * relative to the top left visible item.  If (col, row) is outside the
     * visible range, the request is ignored.
     * 
     * @param col
     * @param row
     */
    public void setFocus(final int col, final int row) {
        if (row >= table.getItemCount())
            return;
        if (row < 0)
            return;
        final Row r = (Row) this.rows.get(row);
        
        // Make sure the setFocus() command is the last thing on the event queue
        Display.getCurrent().asyncExec(new Runnable(){
            public void run() {
                table.showItem(r.item);
                r.cell(col).setFocus();
                curCol = col;
                curRow = row;
                // If this is the end of a new row creation, reset the flag
                iCreated = false;
            }
        });
    }
    
    /**
     * Method fixFocusRow.  Makes sure that the focus row is not beyond the 
     * end of the table's data.
     * 
     * @param numObjects The number of objects in the underlying collection
     */
    protected void fixFocusRow(final int numObjects) {
        if (getTopRow() + curRow >= numObjects) {
            setFocus(curCol, numVisibleRows-1);
        }
    }

    /**
     * Method cell.  Returns the edit control associated with the specified
     * visible column and row in the table.
     * 
     * @param col
     * @param row
     * @return The edit control associated with the specified cell
     */
    protected Control cell(int col, int row) {
        if (rows.size() > 0) {
            Row r = (Row) rows.get(row);
            if (col < r.cols.length)
                return r.cols[col];
            else
                return null;
        } else
            return null;
    }
    
    /**
     * Method emptyTable.  Empty the table.  Done at dispose() time.
     */
    protected void emptyTable() {
        // Dispose the edit controls we created...
        // FIXME: An apparent SWT bug causes NPEs here...  Commented out temporariliy...
//        for(int row=0; row<table.getItemCount(); ++row) {
//            for (int col=0; col<table.getColumnCount(); ++col) {
//                cell(col,row).dispose();
//            }
//        }
        rows.clear();
        
        // Empty the table
//        if (!table.isDisposed())
//            table.removeAll();
    }

    /**
     * Method loadTable.  Actually load the data into the table and begin editing
     * it.
     * 
     * @param editControlCreator The IEditControlFactory
     * 
     * @throws NoSuchMethodException The usual reflection exceptions
     * @throws IllegalAccessException The usual reflection exceptions
     * @throws InvocationTargetException The usual reflection exceptions
     * @throws UnableToSaveException If unsaved data is invalid
     */
    protected void loadTable()
        throws
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            UnableToSaveException 
    {
        setTopRow(0);
        resizeTableContents();
        if (numVisibleRows >= 1)
            refreshData();
        
        // Add listeners on the table
        if (!addedListeners) {
            TableColumn[] columns = table.getColumns();
            for (int i = 0; i < columns.length; i++) {
                columns[i].addControlListener(controlListener);
            }
            table.addControlListener(controlListener);
            table.addMouseListener(mouseListener);
            table.addDisposeListener(disposeListener);
            addedListeners = true;
        }
    }
    
    /**
     * Method refreshData.  Refresh the data in the edit controls so that they
     * reflect the current scroll position.
     * 
     * @throws UnableToSaveException if any editor has unsaved data in it that 
     * cannot be saved because it fails validity checks.
     */
    protected void refreshData() throws UnableToSaveException {
        // Make sure that any unsaved data can be saved first
        validateAllFields();
        
        // Actually perform the refresh
        iterator.at(getTopRow());
        iterator.previous();
        for (int visibleRow=0; visibleRow < numVisibleRows; ++visibleRow) {
            Row current = (Row) rows.get(visibleRow);
            try {
                current.objectAdapter.setInput(iterator.next());
            } catch (UnableToSaveException e) {
                Logger.log().error(e, "Should always be able to save");
            }
        }
    }
    
    /**
     * Method refreshUpToCurrentRow.  Refreshes all rows up to but not
     * including the current row, which might have unsaved data.
     */
    protected void refreshUpToCurrentRow() {
        iterator.at(getTopRow());
        iterator.previous();
        for (int visibleRow=0; visibleRow < curRow; ++visibleRow) {
            Row current = (Row) rows.get(visibleRow);
            try {
                current.objectAdapter.setInput(iterator.next());
            } catch (UnableToSaveException e) {
                Logger.log().error(e, "This row shouldn't have been modified; it shouldn't need saving!");
            }
        }
    }
    
    /**
     * Method resizeContentsAfterCurrentRow.  Refreshes / resizes contents
     * after but not including the specified row, which might have unsaved data.
     */
    protected void refreshContentsAfterRow(int refreshRow) {
        // Refresh all visible rows
        iterator.at(topRow + refreshRow+1);
        iterator.previous();
        for (int visibleRow=refreshRow+1; visibleRow < numVisibleRows; ++visibleRow) {
            Row current = (Row) rows.get(visibleRow);
            try {
                if (iterator == null)
                    Logger.log().error(new Exception(), "iterator is null!");
                Object toEdit = iterator.next();
                if (toEdit == null)
                    Logger.log().error(new Exception(), "Object toEdit == null!");
                if (current == null)
                    Logger.log().error(new Exception(), "Row current == null!");
                current.objectAdapter.setInput(toEdit);
            } catch (UnableToSaveException e) {
                // This can happen if a new row was appended and the initial
                // values of one or more fields is invalid.
            }
        }
    }
    
    /**
     * Method validateAllFields.  Makes sure that any editors with unsaved data
     * in them can be saved.
     * 
     * @throws UnableToSaveException if any editor has unsaved data in it that 
     * cannot be saved because it fails validity checks.
     */
    public void validateAllFields() throws UnableToSaveException {
        if (rows.size() < 1)
            return;
        for (int visibleRow=0; visibleRow < numVisibleRows; ++visibleRow) {
            Row current = (Row) rows.get(visibleRow);
            current.objectAdapter.validateAllFields();
        }
    }
    
    //---- Event handler utility methods ------------------------------------
    
    private boolean resizing=false;
    
    /**
     * Method resizeTable.  Make sure that the table has exactly the same number
     * of edit control rows in it as visible rows and that each row has an associated
     * POJOObjectAdapter
     */
    protected void resizeTableContents() {
        if (resizing)               // Prevent recursive calls...
            return;
        
        if (table.isDisposed())
            return;

        resizing=true;
        try {
            if (table.getItemHeight() == 0)
                return;
            numVisibleRows = (getClientArea().height - table.getHeaderHeight() - 2*table.getGridLineWidth()) / table.getItemHeight();
            //++numVisibleRows;           // Allow for the partial row at the end
            if (numVisibleRows < 0)
                numVisibleRows = 0;
            
            // Try to keep the focus within the visible region
            int startRow = numVisibleRows;
            if (startRow < 0) startRow = 0;
            if (curRow >= startRow-1) {
                try {
                    validateAllFields();
                    if (curRow > 1)
                        setFocus(curCol, curRow-1);
                } catch (UnableToSaveException e) {}
            }
            
            // Make sure numRowsInContainer isn't more than we've got altogether
            int numRowsInContainer = getContainerSize();
            if (numVisibleRows > numRowsInContainer - getTopRow())
                numVisibleRows = numRowsInContainer - getTopRow();
            
            int itemCount = table.getItemCount();
            
            if (numVisibleRows == itemCount)
                return;
            
            // If we don't have as many rows as we need... 
            if (itemCount < numVisibleRows) {
                rows.ensureCapacity(numVisibleRows);
                
                iterator.at(getTopRow()+itemCount);
                iterator.previous();

                for (int newRow=itemCount; newRow < numVisibleRows; ++newRow) {
                    Object element = iterator.next();
                    Row row = new Row(columnPropertyNames.size());
                    row.item = new TableItem(table, SWT.NULL);
                    if (newRow >= rows.size())
                        rows.add(row);
                    else
                        rows.set(newRow, row);
                    for (int col=0; col < columnPropertyNames.size(); ++col) {
                        row.setCell(col, currentControlCreator.newInstance(table, col));
                        TableEditor tableEditor = new TableEditor(table);
                        tableEditor.grabHorizontal = true;
                        tableEditor.grabVertical = true;
                        tableEditor.setEditor(row.cell(col), row.item, col);
                        row.objectAdapter.add(new POJOFieldAdapter(row.cell(col), (String)columnPropertyNames.get(col)));
                        try {
                            row.objectAdapter.setInput(element);
                        } catch (UnableToSaveException e) {
                            Logger.log().error(e, "This is a new row; should be no need to save.");
                        }
                    }
                }
            }
            // Else we have too many rows...
            else {
                // Compute the last visible row (where we'll start adding or removing)
                startRow = numVisibleRows;
                if (startRow < 0) startRow = 0;
            
                // Don't delete the row that has the focus unless the last object
                // in the table just got deleted...
                if (curRow >= startRow && getContainerSize() > 0) {
                    startRow = curRow + 1;
                }
                
                // Make sure we don't delete a row that has dirty invalid data
                for (int rowToCheck = startRow; rowToCheck < table.getItemCount(); ++rowToCheck) {
                    try {
                        Row row = (Row) rows.get(rowToCheck);
                        row.objectAdapter.validateAllFields();
                    } catch (UnableToSaveException e) {
                        startRow = rowToCheck+1;
                    }
                }
                    
                for (int rowToDelete = startRow; rowToDelete < table.getItemCount(); ++rowToDelete) {
                    for (int col=0; col<table.getColumnCount(); ++col) {
                        cell(col,rowToDelete).dispose();
                    }
                    rows.set(rowToDelete, null);
                }
                table.remove(startRow, table.getItemCount()-1);
            }
        } finally {
            resizing = false;
        }
    }
    
    /*
     * Resize the underlying table (due to a window or column resize event)
     */
    
    protected void resizeTableControl() {
        Point size = getSize();
        Point tableSize = table.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
        int horizontalScrollBounds=tableSize.x - size.x;

        // Never make the table smaller, only make it bigger.
        if (tableSize.x < 3*size.x) {
            size.x = 3*size.x;
        } else if (tableSize.x > 3*size.x) {
            size.x = 3*tableSize.x;
        }
        if (tableSize.y > size.y) {
            size.y = tableSize.y;
        } else {
            size.y = size.y + table.getItemHeight() + table.getGridLineWidth();
        }
        table.setSize(size);
        
        Slider h = horizontalSlider.getSlider();
        if (horizontalScrollBounds > 0) {
            h.setIncrement(1);
            h.setPageIncrement(100);
            h.setMaximum(horizontalScrollBounds);
            h.setMinimum(0);
            h.setThumb(1);
            horizontalSlider.setVisible(true);
        } else {
            horizontalSlider.setVisible(false);
        }
    }
    
    /*
     * Scroll the table
     */
    
    protected void updateHorizSlider() {
        int selection = horizontalSlider.getSlider().getSelection();
        table.setLocation(-1 * selection, 0);
    }
    
    protected void initVertSlider() {
        if (table.getItemHeight() == 0) return;
        Slider v = verticalSlider.getSlider();
        final int possibleVisibleRows =
            (getClientArea().height
                - table.getHeaderHeight()
                - 2 * table.getGridLineWidth())
                / table.getItemHeight();
        int collectionSize = getContainerSize();
        if (numVisibleRows < collectionSize) {
            int overflow = collectionSize - possibleVisibleRows;
            int pageIncrement = possibleVisibleRows;
            if (pageIncrement > overflow) pageIncrement = overflow;
            v.setIncrement(1);
            v.setPageIncrement(pageIncrement);
            v.setMaximum(collectionSize);
            v.setMinimum(0);
            v.setThumb(1);
            verticalSlider.setVisible(true);
        } else {
            verticalSlider.setVisible(false);
        }
    }
    
    protected void updateVertSlider() {
        try {
            validateAllFields();
            setTopRow(verticalSlider.getSlider().getSelection());
            resizeTableContents();
            refreshData();
            fixFocusRow(getContainerSize());
        } catch (UnableToSaveException e) {
        }
    }
    
    
    //---Event listeners are here---------------------------------------------

    private boolean addedListeners = false;
    
    private ControlListener controlListener = new ControlListener() {
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.ControlListener#controlMoved(org.eclipse.swt.events.ControlEvent)
         */
        public void controlMoved(ControlEvent e) {
        }
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.ControlListener#controlResized(org.eclipse.swt.events.ControlEvent)
         */
        public void controlResized(ControlEvent e) {
            resizeTableControl();   // This works because we never shrink the table
            resizeTableContents();
            initVertSlider();
        }
    };
    
    private SelectionListener horizontalSliderSelectionListener = new SelectionListener() {
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
         */
        public void widgetDefaultSelected(SelectionEvent e) {
            widgetSelected(e);
        }
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
         */
        public void widgetSelected(SelectionEvent e) {
            updateHorizSlider();
        }
    };

    private SelectionListener verticalSliderSelectionListener = new SelectionListener() {
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
         */
        public void widgetDefaultSelected(SelectionEvent e) {
            widgetSelected(e);
        }
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
         */
        public void widgetSelected(SelectionEvent e) {
            updateVertSlider();
        }
    };

    /*
     * Trap mouse clicks that happen to land on the Table and redirect them to
     * the closest cell in the table...
     */
    private MouseListener mouseListener = new MouseListener() {
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.MouseListener#mouseDown(org.eclipse.swt.events.MouseEvent)
         */
        public void mouseDown(MouseEvent e) {
            if (table.getItemCount() < 1) return;
            
            Rectangle clientArea = table.getClientArea();
            Point hit = new Point(e.x, e.y);
            
            int index = table.getTopIndex();
            int numVisible = clientArea.height / table.getItemHeight();
            int lastVisible = index + numVisible;
            if (lastVisible > table.getItemCount())
                lastVisible = table.getItemCount();
            
            // Iterate over all the visible cells
            while (index < lastVisible) {
                //TableItem item = table.getItem(index);
                int cellBottom=0;
                for (int col=0; col < table.getColumnCount(); ++col) {
                    Rectangle cellBounds = cell(col,index).getBounds();
                    cellBottom = cellBounds.y + cellBounds.height;
                    cellBounds.width += 1;
                    cellBounds.height += 1;
                    if (cellBounds.contains(hit)) {
                        cell(col,index).setFocus();
                        return;
                    }
                }
                // Did the vertical bounds of our search bounding box just 
                // pass the hit point?
                if (cellBottom > hit.y) {
                    // It did, the hit point must be to the right of the
                    // current row, so select the rightmost cell and quit
                    cell(table.getColumnCount()-1,index).setFocus();
                    return;
                }
                ++index;
            }
            
            // Is the hit point below some column but after the end of the table?
            for (int col=0; col < table.getColumnCount(); ++col) {
                Rectangle cellBounds = cell(col,0).getBounds();
                if (cellBounds.x+cellBounds.width > hit.x) {
                    // Yes: select the cell most immediately above the hit point.
                    cell(col,lastVisible-1).setFocus();
                    return;
                }
            }
            
            // The hit point must be below and to the right of all cells.
            cell(table.getColumnCount()-1,lastVisible-1).setFocus();
        }
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.MouseListener#mouseUp(org.eclipse.swt.events.MouseEvent)
         */
        public void mouseUp(MouseEvent e) {
        }
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.MouseListener#mouseDoubleClick(org.eclipse.swt.events.MouseEvent)
         */
        public void mouseDoubleClick(MouseEvent e) {
        }
    };
    
    private FocusListener tableFocusListener = new FocusListener() {
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.FocusListener#focusGained(org.eclipse.swt.events.FocusEvent)
         */
        public void focusGained(FocusEvent e) {
            if (getContainerSize() > 0) {
                Control focusControl = cell(curCol, curRow);
                if (focusControl != null)
                    focusControl.setFocus();
            }
        }
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.FocusListener#focusLost(org.eclipse.swt.events.FocusEvent)
         */
        public void focusLost(FocusEvent e) {
        }
    };
    
    private KeyListener tableKeyListener = new KeyListener() {
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events.KeyEvent)
         */
        public void keyPressed(KeyEvent e) {
            if (e.keyCode == SWT.INSERT) {
                if (isInsertEnabled()) {
                    Display.getDefault().asyncExec(new Runnable() {
                        public void run() {
                            try {
                                if (curRow >= 0 && rows.size() > 0) {
                                    Row curRowObject = (Row) rows.get(curRow);
                                    if (curRowObject != null) {
                                        curRowObject.objectAdapter.saveDirtyFields();
                                        curRowObject.objectAdapter.validateAllFields();
                                    }
                                }
                                iCreated = true;

                                // This implicitly fires the "created" event
                                Model.getDefault().create(container);
                            } catch (UnableToSaveException e) {
                                // This is the normal action if the user hasn't saved changes
                                // and the current changes are invalid.
                            }
                        }
                    });
                }
            }
        }
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events.KeyEvent)
         */
        public void keyReleased(KeyEvent e) {
        }
    };

    private DisposeListener disposeListener = new DisposeListener() {
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
         */
        public void widgetDisposed(DisposeEvent e) {
            emptyTable();
        }
    };

    private boolean insertEnabled = true;

    /**
     * Method isInsertEnabled.  
     * @return
     */
    public boolean isInsertEnabled() {
        return insertEnabled;
    }

    /**
     * Method setInsertEnabled.  
     * @param b
     */
    public void setInsertEnabled(boolean b) {
        insertEnabled = b;
    }
    
    private boolean deleteEnabled = true;

    /**
     * Method isDeleteEnabled.  
     * @return
     */
    public boolean isDeleteEnabled() {
        return deleteEnabled;
    }

    /**
     * Method setDeleteEnabled.  
     * @param b
     */
    public void setDeleteEnabled(boolean b) {
        deleteEnabled = b;
    }

}
