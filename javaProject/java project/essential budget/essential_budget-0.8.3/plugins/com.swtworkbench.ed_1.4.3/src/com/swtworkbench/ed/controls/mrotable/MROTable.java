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
package com.swtworkbench.ed.controls.mrotable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Widget;

import com.swtworkbench.ed.aware.adapters.pojo.UnableToSaveException;
import com.swtworkbench.ed.aware.interfaces.IRecordObjectAdapter;
import com.swtworkbench.ed.aware.iterator.IAwareIterator;
import com.swtworkbench.ed.aware.iterator.IteratorAdapterFactory;
import com.swtworkbench.ed.aware.masterdetail.IDetail;
import com.swtworkbench.ed.aware.masterdetail.IMaster;
import com.swtworkbench.ed.aware.masterdetail.IMasterObjectChangeListener;
import com.swtworkbench.ed.aware.masterdetail.MasterObjectChangeEvent;
import com.swtworkbench.ed.aware.model.IModelChangeListener;
import com.swtworkbench.ed.aware.model.IObjectFactory;
import com.swtworkbench.ed.aware.model.Model;
import com.swtworkbench.ed.aware.model.ModelChangeAdapter;
import com.swtworkbench.ed.aware.model.NewObject;
import com.swtworkbench.ed.controls.mro.internal.IMROListener;
import com.swtworkbench.ed.controls.mro.internal.MRO;
import com.swtworkbench.ed.controls.mro.internal.RecordObjectEditor;
import com.swtworkbench.ed.controls.table.internal.SliderHolder;
import com.swtworkbench.ed.reflect.Property;
import com.swtworkbench.ed.reflect.Reflect;
import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class MROTable. A Virtual table control implemented using the multi-
 * record object rather than the SWT table control.
 * <p>
 * Note that even though MROTable extends Composite, it does not make sense
 * to put a layout manager on it.
 *
 * @author daveo
 */
public class MROTable extends Composite implements IMaster, IDetail {
    
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
    public MROTable(Composite parent, int style) {
        super(parent, SWT.NULL);
        setLayout(new FillLayout());
        mro = new MRO(this, style);
        Color white = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
        mro.setBackground(white);
        mro.setMROListener(new MROAdapter());
        mro.addMasterObjectChangeListener(mroRowChangeListener);
    }
    
    /**
     * Dispose this control and all internal resources it uses.
     */
    public void dispose() {
        super.dispose();
        Model.getDefault().removeModelChangeListener(input, modelChangeListener);
    }
    

    // Layout management here...
    
    private MRO mro;
    
    /**
     * @return the underlying MRO control.  Note that MRO is internal API 
     * (for now), so it can only be used as a Composite.
     */
    public MRO getControl() {
        return mro;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.swt.widgets.Composite#setFocus()
     */
    public boolean setFocus() {
        super.setFocus();
        return mro.setFocus();
    }
    
	/**
	 * @return Returns the headerFactory.
	 */
	public IPartControlFactory getHeaderFactory() {
		return mro.getHeaderFactory();
	}
    
	/**
	 * @param header The headerFactory to set.
	 */
	public void setHeaderFactory(IPartControlFactory header) {
		mro.setHeaderFactory(header);
	}
    
    /**
     * @return the MRO's help text
     */
    public String getHelpText() {
        return mro.getHelpText();
    }
    
    /**
     * Sets the help text that is displayed when an MROTable is empty
     * @param helpText The text string to display
     */
    public void setHelpText(String helpText) {
        mro.setHelpText(helpText);
    }
    
    //---- Manage any master-detail relationships in which this table is participating
    
    protected IMaster master = null;
    
    private IMasterObjectChangeListener detailChangeListener = new IMasterObjectChangeListener() {
        public void requestChange(MasterObjectChangeEvent e) {
            if (getInput() != null) {
                try {
                    validateAllFields();
                } catch (UnableToSaveException e1) {
                    e.doit = false;
                }
            }
        }
        public void masterObjectChanged(MasterObjectChangeEvent e) {
            final Object newInput = master.getMasterObject(e.newSelection.y);
            Display.getCurrent().asyncExec(new Runnable() {
				public void run() {
                    try {
                        setInput(newInput);
                    } catch (Exception ex) {
                        Logger.log().error(ex, "Unable to set the input even though all fields validated correctly.");
                    }
				}
			});
        }
    };
    
    private LinkedList masterObjectChangeListeners = new LinkedList();

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IMaster#addRowChangeListener(com.swtworkbench.ed.controls.table.RowChangeListener)
     */
    public void addMasterObjectChangeListener (IMasterObjectChangeListener listener) {
        masterObjectChangeListeners.addLast(listener);
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IMaster#removeRowChangeListener(com.swtworkbench.ed.controls.table.RowChangeListener)
     */
    public void removeMasterObjectChangeListener (IMasterObjectChangeListener listener) {
        masterObjectChangeListeners.remove(listener);
    }
    
    
    /**
     * Fire a RowChangeRequest event.  Note that in this context, the "col" 
     * field refers to the MRO's current "element".
     * 
     * @param oldCol The old col
     * @param oldRow The old row
     * @param newCol The col we want to move to
     * @param newRow The row we want to move to
     * @return true if all listeners permit the action; false otherwise 
     */
    protected boolean fireRowChangeRequest(int oldCol, int oldRow, int newCol, int newRow) {
        MasterObjectChangeEvent e = new MasterObjectChangeEvent(this, oldCol, oldRow, newCol, newRow);
        for (Iterator i = masterObjectChangeListeners.iterator(); i.hasNext();) {
            IMasterObjectChangeListener element = (IMasterObjectChangeListener) i.next();
            element.requestChange(e);
        }
        return e.doit;
    }

    /**
     * Indicate to all IMasterObjectChangeListeners that a row change has occurred
     * Note that in this context, the "col" field refers to the MRO's current "element".
     * 
     * @param oldCol The old col
     * @param oldRow The old row
     * @param newCol The new col
     * @param newRow The new row
     */
    protected void fireRowChangeEvent(int oldCol, int oldRow, int newCol, int newRow) {
        MasterObjectChangeEvent e = new MasterObjectChangeEvent(this, oldCol, oldRow, newCol, newRow);
        for (Iterator i = masterObjectChangeListeners.iterator(); i.hasNext();) {
            IMasterObjectChangeListener element = (IMasterObjectChangeListener) i.next();
            element.masterObjectChanged(e);
        }
    }

    private IMasterObjectChangeListener mroRowChangeListener = new IMasterObjectChangeListener() {
        public void requestChange(MasterObjectChangeEvent e) {
            e.doit = fireRowChangeRequest(e.oldSelection.x, e.oldSelection.y + topRow, e.newSelection.x, e.newSelection.y + topRow);
        }
        public void masterObjectChanged(MasterObjectChangeEvent e) {
            fireRowChangeEvent(e.oldSelection.x, e.oldSelection.y + topRow, e.newSelection.x, e.newSelection.y + topRow);
        }
    };
    
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IDetail#setMaster(com.swtworkbench.ed.aware.interfaces.IMaster)
     */
    public void setMaster(IMaster master) {
        if (this.master != null) {
            this.master.removeMasterObjectChangeListener(detailChangeListener);
        }
        this.master = master;
        master.addMasterObjectChangeListener(detailChangeListener);
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.masterdetail.IMaster#getMasterObject(int)
     */
    public Object getMasterObject(int offset) {
        return iterator.at(offset);
    }
    
    private Object input;
    
    /**
     * @return the input object that we're editing
     */
    public Object getInput() {
        return input;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.masterdetail.IDetail#setInput(java.lang.Object)
     */
    public void setInput(Object bean) throws UnableToSaveException {
        try {
            edit(bean, getPropertyName(), mro.getPartControlFactory());
        } catch (Exception e) {
            throw new UnableToSaveException(e);
        }
    }

    
    /**
     * Validate all unsaved fields in the current row.
     * 
     * @throws UnableToSaveException if any field doesn't validate.
     */
    protected void validateAllFields() throws UnableToSaveException {
        RecordObjectEditor editor = mro.getCurEditor();
        if (editor != null)
            mro.getCurEditor().getObjectAdapter().validateAllFields();
    }
    
    /**
     * Specify that this MROTable will edit the specified property name
     * of its input object using the given IPartControlFactory to generate
     * editors.
     * 
     * @param input The collection to edit
     * 
     * @param propertyName The string name of the property containing the
     * collection to be edited.
     * 
     * @param factory The IPartControlFactory that will be used to generate
     * rows for the MROTable.
     */
    public void edit(Object input, String propertyName, IPartControlFactory factory) 
        throws
            SecurityException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            UnableToSaveException 
    {
        this.propertyName = propertyName;
        mro.setPartControlFactory(factory);
        
        // Unregister any old object factory and model change listener
        if (objectFactory != null) {
            Model.getDefault().unregisterObjectFactory(property.getInput());
        }
        if (propertyModelChangeListener != null) {
            Model.getDefault().removeModelChangeListener(property.getInput(), propertyModelChangeListener);
        }
        
        // Get the new property and register its object factory and model change listener
        property = new Property(input, propertyName);
        objectFactory = property.getObjectFactory();
        if (objectFactory != null) {
            Model.getDefault().registerObjectFactory(property.get(), objectFactory);
        }
        propertyModelChangeListener = property.getChangeListener();
        if (propertyModelChangeListener != null) {
            Model.getDefault().addModelChangeListener(property.get(), propertyModelChangeListener);
        }
        
        // Update the current collection in the MROTable
        attachContainer(property.get());
    }
    
    // The name of the property to edit
    private String propertyName;
    private Property property = null;
    private IObjectFactory objectFactory = null;
    
    /**
	 * @return Returns the propertyName.
	 */
	public String getPropertyName() {
		return propertyName;
	}
    
    /**
     * Sets the name of the input property
     * 
     * @param The input property name
     */
    public void setInputProperty(String propertyName) {
        this.propertyName = propertyName;
    }
    
    /**
     * Sets the row factory
     * 
     * @param factory The IPartControl factory used to create row objects
     */
    public void setRowFactory(IPartControlFactory factory) {
        mro.setPartControlFactory(factory);
    }
    
    /**
	 * @return Returns the row factory.
	 */
	public IPartControlFactory getRowFactory() {
		return mro.getPartControlFactory();
	}
    
    //===Container Management here============================================
    
    private IModelChangeListener propertyModelChangeListener = null;
    protected IAwareIterator iterator;
    protected Method getSize;
    protected IModelChangeListener modelChangeListener = new ModelChangeListener();
    
    private void attachContainer(Object container)
        throws
            SecurityException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            UnableToSaveException
    {
        if (this.input != null) {
            validateAllFields();
            Model.getDefault().removeModelChangeListener(this.input, modelChangeListener);
        }
        this.input = container;
        Model.getDefault().addModelChangeListener(container, modelChangeListener);
        this.iterator = IteratorAdapterFactory.iterator(container);
        getSize = container.getClass().getMethod("size", new Class[] {});
        loadMRO();
    }
    
    protected int getContainerSize() {
        Integer result = (Integer)Reflect.invokei(input, getSize, new Object[] {});
        if (result != null)
            return result.intValue();
        else
            return 0;
    }


    //===MRO Management here==================================================
    
    private int topRow = 0;
    
    /**
     * Load the MRO with a new collection's data & reset the top row to the 0th
     * row.
     */
    private void loadMRO() {
        // Reset the MRO
        mro.setNumRows(0);
        
        // Initialize the first row
        topRow = 0;
        int collectionSize = getContainerSize();
        if (collectionSize > 0) {
            // We have to add the 1st row separately because otherwise the
            // MRO doesn't know the size of a row and can't compute
            // getMaxNumRows() below.
            mro.setNumRows(1);
        } else {
            return;
        }
        
        // Make the MRO contain the correct number of editors
        int currentNumRows = mro.getActualNumRows();
        if (currentNumRows < mro.getMaxVisibleRows()) {
            if (collectionSize != currentNumRows) {
                resizeMRO(mro);
            }
        }

        // Actually load the data
        for (int i=0; i < currentNumRows; ++i) {
            Object toEdit = iterator.at(i);
            RecordObjectEditor editor = mro.getEditorAt(i);
            edit(toEdit, editor);
        }
        
        // Initialize the scroll bar
        updateVertSlider();
    }
    
    /**
     * Refresh the data in the MRO because some parameter just changed
     */
    private void refreshMRO() {
        int currentNumRows = mro.getActualNumRows();
        for (int i=0; i < currentNumRows; ++i) {
            Object toEdit = iterator.at(i + topRow);
            RecordObjectEditor editor = mro.getEditorAt(i);
            editor.getObjectAdapter().undoChanges();
            edit(toEdit, editor);
        }
        
        // Update the scroll bar
        mro.getVerticalSlider().getSlider().setSelection(topRow);
    }
    
    /**
     * Refresh the data in the MRO because some parameter just changed
     */
    private void refreshUpToCurrentRow() {
        int curRow = mro.getCurRow();
        for (int i=0; i < curRow; ++i) {
            Object toEdit = iterator.at(i + topRow);
            RecordObjectEditor editor = mro.getEditorAt(i);
            edit(toEdit, editor);
        }
    }
    
    /**
     * Sets the top-displayed row in the table to the specified offset in the
     * collection.  Does not change the focused row unless the focused row 
     * is scrolled out of the view.  Only attempts to save the data in the
     * focused row if that row is scrolled out of the view.  If the data in
     * the focused row cannot be saved (and would be scrolled out of the 
     * visible range), the view is not altered.
     * 
     * <p>FIXME: The current implementation of scrolling only handles the case
     * where the focused object does not lose the focus.  The other case it 
     * handles is when the entire MRO is being refreshed with new data.  
     * Therefore, this method is marked protected.</p>
     * 
     * @param newTopRow The 0-based offset to display as the 0th row in the table.
     * @return true if successful; false otherwise.
     */
    protected boolean setTopRow(int newTopRow) {
        if (newTopRow >= getContainerSize()) 
            return false;
        
        // Process scrolling that refreshes the entire MRO
        int maxRowsPossible = mro.getMaxVisibleRows();
        int curNumRows = mro.getActualNumRows();
        if (Math.abs(topRow - newTopRow) >= curNumRows-1) {
            RecordObjectEditor curEditor = mro.getCurEditor();
            try {
				curEditor.getObjectAdapter().saveDirtyFields();
			} catch (UnableToSaveException e) {
                return false;
			}
            
            int focusRow = mro.getCurRow();
            int curElement = mro.getCurElement();
            
            topRow = newTopRow;
            refreshMRO();
            mro.setFocus(focusRow, curElement);
            
            // Update the scroll bar
            mro.getVerticalSlider().getSlider().setSelection(topRow);

            return true;
        }
        
        // Process scrolling that does not change the focus row
        int amountToScroll = newTopRow - topRow;
        topRow = newTopRow;
        
        // Actually scroll the MRO...
        if (amountToScroll > 0) {
            mro.delete(amountToScroll, 0);
        } else if (amountToScroll < 0) {
            mro.insert(-1 * amountToScroll, 0);
        }
        
        // Update the scroll bar
        mro.getVerticalSlider().getSlider().setSelection(topRow);

        return true;
    }

    /**
     * Make the underlying MRO have the correct number of rows given its present
     * size.
     */
    private void resizeMRO(MRO sender) {
        int visibleSize = getContainerSize() - topRow;
        int maxNumRows = mro.getCellSize().x == -1 ? 1 : mro.getMaxVisibleRows();
        int visibleRows = visibleSize <= maxNumRows ? visibleSize : maxNumRows;
        mro.setNumRows(visibleRows);
        
        // Update the scroll bar
        updateVertSlider();
    }
    
    /**
     * Refresh some cell in the MRO with the appropriate data
     */
    private void refreshMROCell(RecordObjectEditor editor, int offsetFromTop) {
        Object toEdit = iterator.at(topRow+offsetFromTop);
        edit(toEdit, editor);
    }
    
    /**
     * @param toEdit
     * @param with
     */
    private void edit(Object toEdit, RecordObjectEditor with) {
        try {
            IRecordObjectAdapter editor = with.getObjectAdapter();
            editor.undoChanges();    // Defensive programming...
            editor.setInput(toEdit);
        } catch (UnableToSaveException e) {
            Logger.log().error(e, "Editor should not be dirty.");
        }
    }
    
    /**
     * Initialize/update the vertical slider
     */
    private void updateVertSlider() {
        if (getContainerSize() == 0) {
            mro.getVerticalSlider().setVisible(false);
            return;
        }
        
        int collectionSize = getContainerSize();
        int maxVisible = mro.getMaxVisibleRows();
        Slider v = mro.getVerticalSlider().getSlider();
        if (maxVisible < collectionSize) {
            int overflow = collectionSize - maxVisible;
            int pageIncrement = maxVisible;
            if (pageIncrement > overflow) pageIncrement = overflow;
            v.setIncrement(1);
            v.setPageIncrement(pageIncrement);
            v.setMaximum(collectionSize - maxVisible);
            v.setMinimum(0);
            v.setThumb(1);
            mro.getVerticalSlider().setVisible(true);
        } else {
            mro.getVerticalSlider().setVisible(false);
        }
    }

    //===Insert/Delete here===================================================
    
    /*
     * These flags indicate if this Table did the actual inserting/deleting
     */
    protected boolean iDeleted = false;
    protected boolean iCreated = false;
    
    // Is this insertion really a re-sort operation?
    protected Object toResort = null;
    

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

    private class ModelChangeListener extends ModelChangeAdapter {

        /* (non-Javadoc)
         * @see com.swtworkbench.ed.aware.model.IModelChangeListener#objectCreated(java.lang.Object, com.swtworkbench.ed.aware.model.NewObject)
         */
        public void objectCreated(Object container, final NewObject newObject) {
            try {
                // Avoid CoModificationException--get a new iterator
                iterator = IteratorAdapterFactory.iterator(container);
            } catch (Exception e) {
                Logger.log().error(e, "Unable to get a new iterator");
            }
    
            int max=getContainerSize();
            int numVisibleRows = mro.getActualNumRows();
            int lastVisibleRow = topRow + numVisibleRows - 1;
    
            // If I created it, I have to move the focus to the new row so that
            // the user can edit it.  Otherwise, I just refresh if the new object
            // is visible.
            //
            // If the new object is the same object as was just deleted, this
            // means that the object was re-sorted due to an edit and I still
            // have to move the focus to it.
            if (iCreated || newObject.newObject == toResort) {
                // If the new object is in the visible range, just
                // refresh the table's data and move the focus to it
                if (topRow <= newObject.position &&
                    newObject.position <= lastVisibleRow)
                {
                    // Setting the focus first, forces any edited fields to
                    // save their contents before we do a refresh.
                    // If we refresh first, then we can lose edits (particularly)
                    // in edit mask fields.
                    mro.setFocus(newObject.position - topRow, mro.getCurElement());
                    Display.getCurrent().asyncExec(new Runnable() {
						public void run() {
                            refreshMRO();
                            resizeMRO(mro);
                            mro.setFocus(newObject.position - topRow, mro.getCurElement());
                            updateVertSlider();
						}
					});
                    return;
                }
                    
                // If the new object is before the visible range,
                // scroll the visible range, then select the new object
                if (newObject.position < topRow) {
                    setTopRow(newObject.position);
                    mro.setFocus(0, mro.getCurElement());
                    updateVertSlider();
                    return;
                }
                
                // The new object must be after the visible range.
                // Is the visible range smaller than it could be, given
                // the window size?  (This happens when there are fewer
                // total elements than fit in the window.)
                int numPossibleRows = mro.getMaxVisibleRows();
                    
                if (numVisibleRows < numPossibleRows
						&& newObject.position < topRow + numPossibleRows
						|| mro.getCellSize().x == -1) {
					resizeMRO(mro);
                    int curElement = mro.getCurElement();
                    if (curElement < 0) curElement = 0;
					mro.setFocus(newObject.position - topRow, 
                            curElement);
					return;
				}
                    
                // Nope. Scroll the visible range, then select the new object
                int newTopRow = newObject.position - numVisibleRows + 1;
                if (newTopRow < 0) newTopRow = 0;
                setTopRow(newTopRow);
                mro.setFocus(newObject.position - newTopRow, mro.getCurElement());
            } else {
                // The general idea here is to refresh so that the curRow
                // does not have to change.  We deliberately only allow 
                // invalid data in the current row, so all objects before
                // and after the current row should be in a valid state
                // and therefore can be refreshed.
    
                // If we just inserted the 0th row
                if (numVisibleRows < 1) {
                    resizeMRO(mro);
                    mro.getVerticalSlider().getSlider().setSelection(1);
                    return;
                }
                
                // If the new object is before the visible region, we don't
                // have to refresh, but the topRow must be incremented to 
                // reflect the new viewport position.  Increment it, update 
                // the scroll bar, and return.
                if (newObject.position <= topRow) {
                    ++topRow;
                    int selection = mro.getCurRow() + topRow;
                    mro.getVerticalSlider().getSlider().setSelection(selection+1);
                    updateVertSlider();
                    return;
                }
                
                // If the new object is after the last possible row, we don't
                // have to do anything
                int numPossibleRows = mro.getMaxVisibleRows();
                int lastPossibleVisibleRow = topRow + numPossibleRows;
                if (lastPossibleVisibleRow < newObject.position) {
                    updateVertSlider();
                    return;
                }
                
                // If we've gotten here, we know that the new object is visible.
                // Is the new object before the current row?
                if (newObject.position <= mro.getCurRow() && numVisibleRows >= 1) {
                    // Increment the top position and refresh up to (but not 
                    // including) the current row
                    ++topRow;
                    int selection = mro.getCurRow() + topRow;
                    mro.getVerticalSlider().getSlider().setSelection(selection+1);
                    refreshUpToCurrentRow();
                } else {
                    // Refresh everything after (but not including) the 
                    // current row
                    resizeMRO(mro);
                }
            }
            updateVertSlider();
        }

        /* (non-Javadoc)
         * @see com.swtworkbench.ed.aware.model.IModelChangeListener#objectDeleted(java.lang.Object, int, java.lang.Object)
         */
        public void objectDeleted(Object container, int position, Object removed) {
            // If the current row's object is being removed and it appears again
            // in another place, we know that the row was re-sorted and we'd
            // better move the focus with it.
            if (removed == mro.getCurEditor().getObjectAdapter().getInput())
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
            int selection = mro.getCurRow() + topRow;
            
            // Make sure the selection is within the allowed range
            if (selection >= max-1) {
                selection = max-1;
                if (selection < 0) selection = 0;
                mro.getVerticalSlider().getSlider().setSelection(selection);
            }
            // Update the maximum
            updateVertSlider();
    
            // If the object that was deleted is before the visible range or
            // if the object that was deleted is after the visible range, we're
            // done. 
            int numVisibleRows = mro.getActualNumRows();
            int lastVisibleRow = topRow + numVisibleRows - 1;
//            Logger.log().debug("ObjectDeleted: deletedPosition=" + position + " topRow=" + topRow + " curRow=" + mro.getCurRow() + " containerSize=" + getContainerSize() + " numVisibleRows=" + numVisibleRows + " lastVisibleRow=" + lastVisibleRow);
            if (position < topRow || position > lastVisibleRow)
                return;
    
            // Otherwise, undo any changes to the row that was deleted and delete
            // it from the MRO.
            mro.getEditorAt(position-topRow).getObjectAdapter().undoChanges();
            
            // Now refresh the visible MRO so that it reflects the state of the 
            // underlying collection
            if (lastVisibleRow >= getContainerSize()) {
                mro.delete(1, position-topRow, false);
            } else {
                if (lastVisibleRow < topRow + mro.getMaxVisibleRows())
                	mro.delete(1, position-topRow, false);
                else
                    mro.delete(1, position-topRow, true);
            }

            // If there are no more items in the collection, make sure the 
            // table gets the focus
            if (topRow <= 0 && getContainerSize() < 1) {
                Display.getDefault().asyncExec(new Runnable() {
                    public void run() {
                        mro.setFocus();
                    }
                });
            }

        }
    }
    

    //===Bubbled-up event processing here...==================================
    
    /**
     * Triggered whenever any bound field detects a keystroke.
     * @param sender The RecordObjectEditor sending the event.
     * @param e The KeyEvent
     */
    private void keyPressed(RecordObjectEditor sender, KeyEvent e) {
        if (mro.isSettingFocus())
            return;
        
        int curRow = mro.getCurRow();
        switch (e.keyCode) {
            case SWT.ARROW_DOWN:
                if (!(e.widget instanceof Combo || e.widget instanceof CCombo))
                    if (curRow == mro.getActualNumRows()-1 && topRow + curRow < getContainerSize()-1) {
                        setTopRow(topRow+1);
                        mro.setFocus(curRow, mro.getCurElement());
                    }
                break;
            case SWT.ARROW_UP:
                if (!(e.widget instanceof Combo || e.widget instanceof CCombo))
                    if (curRow == 0 && topRow > 0) {
                        setTopRow(topRow-1);
                        mro.setFocus(0, mro.getCurElement());
                        
                        increaseMROSize();
                    }
                break;
            case SWT.PAGE_DOWN:
                int actualNumRows = mro.getActualNumRows();
                if (topRow + actualNumRows < getContainerSize()) {
                    int newTopRow = topRow + actualNumRows;
                    int itemsInContainer=getContainerSize();
                    if (newTopRow > itemsInContainer-1)
                        newTopRow = itemsInContainer-1;
                    int visibleRows = itemsInContainer - newTopRow;
                    if (visibleRows < mro.getMaxVisibleRows())
                        mro.setNumRows(visibleRows);
                    setTopRow(newTopRow);
                }
                break;
            case SWT.PAGE_UP:
                if (topRow > 0) {
                    curRow = mro.getCurRow();
                    int curElement = mro.getCurElement();
                    
                    int maxVisible = mro.getMaxVisibleRows();
                    int newTopRow = topRow - maxVisible;
                    if (newTopRow < 0)
                        newTopRow = 0;
                    setTopRow(newTopRow);

                    increaseMROSize();
                    
                    mro.setFocus(curRow, curElement);
                }
                break;
            case SWT.HOME:
                if ((e.stateMask & SWT.CONTROL) != 0) {
                    if (topRow > 0)
                        setTopRow(0);
                    increaseMROSize();
                    mro.setFocus(0, 0);
                }
                break;
            case SWT.END:
                if ((e.stateMask & SWT.CONTROL) != 0) {
                    actualNumRows = mro.getActualNumRows();
                    if (topRow + actualNumRows < getContainerSize()) {
                        // If we need to scroll...
                        int maxVisible = mro.getMaxVisibleRows();
                        int newTopRow = getContainerSize() - maxVisible + 1;
                        setTopRow(newTopRow);
                        mro.setFocus(maxVisible-1, mro.getCurElement());
                    } else {
                        // Otherwise, just move the focus...
                        mro.setFocus(actualNumRows, mro.getCurElement());
                    }
                }
                break;
            /*
             * The architecture here is that the actual insertion/
             * deletion takes place in the key event.  But the UI
             * updates occur in the IModelChangeListener.
             */
            case SWT.INSERT:
                if (isInsertEnabled()) {
                    try {
                        RecordObjectEditor curEditor = mro.getCurEditor();
                        if (curEditor != null) {
                            IRecordObjectAdapter objectAdapter = curEditor.getObjectAdapter();
                            objectAdapter.saveDirtyFields();
                            objectAdapter.validateAllFields();
                        }
                        
                        // This implicitly fires the "created" event
                        iCreated = true;
                        Model.getDefault().create(input);
                    } catch (UnableToSaveException e2) {
                        // This is the normal action if the user hasn't saved changes
                        // and the current changes are invalid.
                    }
                }
                break;
            case SWT.DEL:
                int size=0;
                try {
                    size = getContainerSize();
                } catch (Exception e1) {}
            
                if ((e.stateMask & SWT.CONTROL) != 0)
                    if (size > 0 && isDeleteEnabled()) {
                        e.doit=false;
                        Display.getDefault().asyncExec(new Runnable() {
                            public void run() {
                                Object toDelete;
                                try {
                                    toDelete = iterator.at(topRow + mro.getCurRow());
                                } catch (NoSuchElementException e) {
                                    // This happens if the user requests
                                    // deletes faster than we can process
                                    // them.  In that case, just eat the
                                    // event.
                                    return;
                                }
                                
                                boolean canDelete = Model.getDefault().fireVetoableObjectDeletionEvent(
                                    input,
                                    topRow + mro.getCurRow(),
                                    toDelete);
                        
                                if (canDelete) {
                                    iterator.remove();
                                    iDeleted = true;

                                    Model.getDefault().fireObjectDeletedEvent(
                                        input,
                                        topRow + mro.getCurRow(),
                                        toDelete);
                                }
                            }
                        });
                    }
                break;
        }
    }

    /**
	 * 
	 */
	private void increaseMROSize() {
		int actualRowsVisible = mro.getActualNumRows();
		int itemsInContainer=getContainerSize();
		int visibleRows = itemsInContainer - topRow;
		if (actualRowsVisible < visibleRows)
		    mro.setNumRows(visibleRows);
	}

	/**
     * Triggered whenever any bound field detects a TraverseEvent.
     * @param sender The RecordObjectEditor sending the event.
     * @param e The KeyEvent
     */
    private void keyTraversed(RecordObjectEditor sender, TraverseEvent e) {
        switch (e.detail) {
        case SWT.TRAVERSE_TAB_NEXT:
            int curRow = mro.getCurRow();
            if (curRow >= mro.getActualNumRows()-1) {
                if (sender != null && (Composite)sender.getControl() != null) {
                    Control[] tabList = ((Composite)sender.getControl()).getTabList();
                    if (e.widget == tabList[tabList.length-1]) {
                        e.doit = false;
                        if (curRow == mro.getActualNumRows()-1 && topRow + curRow < getContainerSize()-1) {
                            setTopRow(topRow+1);
                            mro.setFocus(curRow, 0);
                        }
                    }
                }
            }
            break;
        case SWT.TRAVERSE_TAB_PREVIOUS:
            curRow = mro.getCurRow();
            if (curRow <= 0) {
                if (sender != null && (Composite)sender.getControl() != null) {
                    Control[] tabList = ((Composite)sender.getControl()).getTabList();
                    if (e.widget == tabList[0]) {
                        e.doit = false;
                        if (topRow > 0) {
                            setTopRow(topRow-1);
                            mro.setFocus(0, tabList.length-1);
                            increaseMROSize();
                        }
                    }
                }
            }
            break;
        }
    }

    /**
     * Try to scroll to a new location.  Validates user's data entry first.
     * 
     * @param selection
     */
    private boolean scrollTo(int selection) {
        try {
            int oldRow = mro.getCurRow();
            int oldElement = mro.getCurElement();
            int oldTopRow = topRow;
            
			mro.getCurEditor().getObjectAdapter().validateAllFields();
            setTopRow(selection);

            int delta = oldTopRow - selection;
            int numRowsVisible = mro.getActualNumRows();
            
            // If the focused element is still visible, bounce the focus there
            // else, put the focus on the same row as it was before or the last
            // row if the row it was on before is no longer visible.
            if (oldRow + delta >= 0 && oldRow + delta < numRowsVisible) {
                if (Display.getCurrent().getFocusControl() == mro.getVerticalSlider().getSlider())
                    mro.setFocus(oldRow+delta, oldElement);
            } else {
                if (oldRow > numRowsVisible)
                    oldRow = numRowsVisible-1;
                mro.setFocus(oldRow, oldElement);
            }
            
            if (selection < oldTopRow)
                increaseMROSize();
            
            return true;
		} catch (UnableToSaveException e) {
            // Only happens if we can't scroll
            return false;
		}
    }

    private class MROAdapter implements IMROListener {
        /* (non-Javadoc)
         * @see com.swtworkbench.ed.controls.mrotable.internal.IMROListener#rowFocusGained(com.swtworkbench.ed.controls.mrotable.internal.RecordObjectEditor, org.eclipse.swt.events.FocusEvent)
         */
        public void elementFocusGained(RecordObjectEditor sender, FocusEvent e) {
            // TODO Auto-generated method stub

        }
        /* (non-Javadoc)
         * @see com.swtworkbench.ed.controls.mrotable.internal.IMROListener#rowFocusLost(com.swtworkbench.ed.controls.mrotable.internal.RecordObjectEditor, org.eclipse.swt.events.FocusEvent)
         */
        public void elementFocusLost(RecordObjectEditor sender, FocusEvent e) {
            // TODO Auto-generated method stub

        }
        /* (non-Javadoc)
         * @see com.swtworkbench.ed.controls.mrotable.internal.IMROListener#rowKeyPressed(com.swtworkbench.ed.controls.mrotable.internal.RecordObjectEditor, org.eclipse.swt.events.KeyEvent)
         */
        public void elementKeyPressed(RecordObjectEditor sender, KeyEvent e) {
        	keyPressed(sender, e);
        }
        /* (non-Javadoc)
         * @see com.swtworkbench.ed.controls.mrotable.internal.IMROListener#rowKeyReleased(com.swtworkbench.ed.controls.mrotable.internal.RecordObjectEditor, org.eclipse.swt.events.KeyEvent)
         */
        public void elementKeyReleased(RecordObjectEditor sender, KeyEvent e) {
            // TODO Auto-generated method stub

        }
        /* (non-Javadoc)
         * @see com.swtworkbench.ed.controls.mrotable.internal.IMROListener#rowKeyTraversed(com.swtworkbench.ed.controls.mrotable.internal.RecordObjectEditor, org.eclipse.swt.events.TraverseEvent)
         */
        public void elementKeyTraversed(RecordObjectEditor sender, TraverseEvent e) {
        	keyTraversed(sender, e);
        }
        /* (non-Javadoc)
         * @see com.swtworkbench.ed.controls.mrotable.internal.IMROListener#rowValid(com.swtworkbench.ed.controls.mrotable.internal.RecordObjectEditor, boolean)
         */
        public void elementValid(RecordObjectEditor sender, boolean isValid) {
            // TODO Auto-generated method stub

        }
        /* (non-Javadoc)
         * @see com.swtworkbench.ed.controls.mro.internal.IMROListener#cellAdded(com.swtworkbench.ed.controls.mro.internal.RecordObjectEditor)
         */
        public void cellRefresh(RecordObjectEditor sender, int position) {
            refreshMROCell(sender, position);
        }
        /* (non-Javadoc)
         * @see com.swtworkbench.ed.controls.mrotable.internal.IMROListener#updateVertSlider()
         */
        public void mroResized(MRO sender) {
            resizeMRO(sender);
        }
        /* (non-Javadoc)
         * @see com.swtworkbench.ed.controls.mro.internal.IMROListener#vertSliderSelected(com.swtworkbench.ed.controls.table.internal.SliderHolder, org.eclipse.swt.events.SelectionEvent)
         */
        public void vertSliderSelected(SliderHolder verticalSlider, SelectionEvent e) {
            scrollTo(verticalSlider.getSlider().getSelection());
        }
    }



}


