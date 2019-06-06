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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Slider;

import com.swtworkbench.ed.aware.adapters.pojo.UnableToSaveException;
import com.swtworkbench.ed.aware.masterdetail.IMaster;
import com.swtworkbench.ed.aware.masterdetail.IMasterObjectChangeListener;
import com.swtworkbench.ed.aware.masterdetail.MasterObjectChangeEvent;
import com.swtworkbench.ed.controls.mrotable.IPartControlFactory;
import com.swtworkbench.ed.controls.table.internal.SliderHolder;
import com.swtworkbench.swtutils.logger.Logger;


/**
 * Class MRO. A MRO holds a bunch of Composite objects, arranging them in a grid
 * or table as appropriate for the requested layout. It also creates
 * RecordObjectEditors for each Composite, and provides an interface to them so
 * that each Composite can be used to edit actual objects. If the Composites
 * occupy more space horizontally than is available, a scroll bar is
 * automatically provided. However, no more Composites are allowed vertically
 * than will fit.
 * <p>
 * The internal Composites may not have variable heights or widths. They must
 * always keep the same height and width no matter what data is placed inside
 * them. MRO's behavior is undefined in the event that the internal composites
 * have varying heights or widths.
 * </p>
 * 
 * @author djo
 */
public class MRO extends Composite implements IMaster {
    
    private final MRO theThis;
    /**
     * Construct an MRO.  Accept the usual SWT parent and style parameters.
     * The style bits are just passed on to the constructor of the enclosing
     * Composite.
     * 
	 * @param parent The SWT parent
	 * @param style Style bits that are accepted by any Composite
	 */
	public MRO(Composite parent, int style) {
        super(parent, style);
        theThis = this;
        
        GridLayout layout = new GridLayout(2, false);
        layout.marginHeight=0;
        layout.marginWidth=0;
        layout.horizontalSpacing=0;
        layout.verticalSpacing=0;
        setLayout(layout);
        
        // Children...
        
        bodyHolder = new Composite(this, SWT.NULL);
        GridData gd = new GridData();
        gd.grabExcessHorizontalSpace=true;
        gd.grabExcessVerticalSpace=true;
        gd.horizontalAlignment=GridData.FILL;
        gd.verticalAlignment=GridData.FILL;
        bodyHolder.setLayoutData(gd);
        
        body = new Composite(bodyHolder, SWT.NULL);
        layout = new GridLayout(1, false);
        layout.marginHeight=0;
        layout.marginWidth=0;
        layout.horizontalSpacing=0;
        layout.verticalSpacing=0;
        body.setLayout(layout);
        
        // Header
        headerHolder = new Composite(body, SWT.NULL);
        gd = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
        headerHolder.setLayoutData(gd);
        headerHolder.setLayout(new FillLayout());
        headerHolder.setVisible(false);
        
        headerSeparator = new Label(body, SWT.SEPARATOR | SWT.HORIZONTAL);
        headerSeparator.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));
        headerSeparator.setVisible(false);
        
        setHeaderFactory(null);
        
        mro = new MROCanvas(body, SWT.NULL);
        gd = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
        mro.setLayoutData(gd);
        mro.setLayout(new MROLayout(this));

        mroKeyListener = new MROKeyListener();
        mro.addKeyListener(mroKeyListener);
        mro.addTraverseListener(new MROTraverseListener());
        body.addKeyListener(mroKeyListener);
        bodyHolder.addKeyListener(mroKeyListener);
        addKeyListener(mroKeyListener);
        
        verticalSlider = new SliderHolder(this, SWT.VERTICAL);
        horizontalSlider = new SliderHolder(this, SWT.HORIZONTAL);

        verticalSlider.getSlider().addSelectionListener(verticalSliderSelectionListener);
        horizontalSlider.getSlider().addSelectionListener(horizontalSliderSelectionListener);
        
        bodyHolder.addControlListener(controlListener);
        
        /*
         * This is an ugly hack to force the layout managers to properly
         * layout the window.
         */
        Display.getCurrent().asyncExec(new Runnable() {
            public void run() {
                setSize(getSize().x, getSize().y-1);
                setSize(getSize().x, getSize().y+1);
            }
        });
	}
    
    /**
     * @return Returns the MRO's help text
     */
    public String getHelpText() {
        return mro.getHelpText();
    }
    
    /**
     * Sets the MRO's help text
     * @param text The text string to set
     */
    public void setHelpText(String text) {
        mro.setHelpText(text);
    }
    
    private MROKeyListener mroKeyListener;
    
    /* (non-Javadoc)
     * @see org.eclipse.swt.widgets.Widget#dispose()
     */
    public void dispose() {
        super.dispose();
        mro.removeKeyListener(mroKeyListener);
        body.removeKeyListener(mroKeyListener);
        bodyHolder.removeKeyListener(mroKeyListener);
        removeKeyListener(mroKeyListener);
        
        verticalSlider.getSlider().removeSelectionListener(verticalSliderSelectionListener);
        horizontalSlider.getSlider().removeSelectionListener(horizontalSliderSelectionListener);
        
        bodyHolder.removeControlListener(controlListener);
    }

    /*
     * Resize the underlying MRO (due to a window or column resize event).
     * 
     * We don't use a layout manager for the underlying MRO composite because
     * at times we want its width to be bigger than its parent.  Then we can
     * scroll it horizontally by moving its origin relative to its parent.
     */
    protected void resizeMRO() {
        Point size = bodyHolder.getSize();
        Point mroSize = mro.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
        if (mroSize.x < size.x) mroSize.x = size.x;
        body.setBounds(0, 0, mroSize.x, size.y);
        ensureCorrectNumberOfCells();

        // Initialize the horizontal scroll bar
        int horizontalScrollBounds=mroSize.x - size.x;
        Slider h = horizontalSlider.getSlider();
        if (horizontalScrollBounds > 0) {
            h.setIncrement(1);
            h.setPageIncrement(100);
            h.setMaximum(horizontalScrollBounds);
            h.setMinimum(0);
            h.setThumb(1);
            if (!horizontalSlider.isVisible()) {
                horizontalSlider.setVisible(true);
            }
        } else {
            if (horizontalSlider.isVisible()) {
                horizontalSlider.setVisible(false);
            }
        }
    }
    
    protected final Composite headerHolder;
    protected final Composite bodyHolder;
    protected final Composite body;
    protected final Label headerSeparator;
    protected Composite header = null;
    protected final MROCanvas mro;
    protected final SliderHolder verticalSlider;
    protected final SliderHolder horizontalSlider;
    
    /*
     * defaultHeaderFactory is the "null" header factory.  ie: we're using
     * the null object pattern to deal with the case where there is not
     * a header factory.
     */
    IPartControlFactory defaultHeaderFactory = new IPartControlFactory() {
        public Composite createPartControl(Composite parent) {
            Composite partControl = new Composite(parent, SWT.NULL);
            partControl.setLayout(new Layout() {
                protected Point computeSize(Composite composite, int wHint,
                        int hHint, boolean flushCache) {
                    return new Point(1,1);
                }
                protected void layout(Composite composite, boolean flushCache) {
                    composite.setSize(1,1);
                }
            });
            return partControl;
        }
    };
    
    IPartControlFactory headerFactory = defaultHeaderFactory;;

    /**
	 * @return Returns the verticalSlider.
	 */
	public SliderHolder getVerticalSlider() {
		return verticalSlider;
	}
    
    /**
     * Sets the header factory.  Pass null to indicate that no header should
     * be used.  By default there is no header.
     * 
	 * @param headerFactory The headerFactory to set.
	 */
	public void setHeaderFactory(IPartControlFactory headerFactory) {
        if (headerFactory == null)
            headerFactory = defaultHeaderFactory;
        this.headerFactory = headerFactory;
        if (header != null) 
            header.dispose();
        header = headerFactory.createPartControl(headerHolder);
        headerHolder.setVisible(true);
        if (headerFactory == defaultHeaderFactory)
            headerSeparator.setVisible(false);
        else
            headerSeparator.setVisible(true);
        layout(true);
	}
    
    /**
	 * @return Returns the headerFactory.
	 */
	public IPartControlFactory getHeaderFactory() {
		return headerFactory;
	}
    
    /**
     * Returns the underlying SWT control
     * @return the underlying SWT control
     */
    public Canvas getControl() {
    	return mro;   
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.swt.widgets.Control#setBackground(org.eclipse.swt.graphics.Color)
     */
    public void setBackground(Color color) {
        super.setBackground(color);
        bodyHolder.setBackground(color);
        body.setBackground(color);
        headerHolder.setBackground(color);
        mro.setBackground(color);
    }
    
    private IPartControlFactory partControlFactory;

	/**
	 * @return Returns the partControlFactory.
	 */
	public IPartControlFactory getPartControlFactory() {
		return partControlFactory;
	}
	/**
	 * @param partControlFactory The partControlFactory to set.
	 */
	public void setPartControlFactory(IPartControlFactory partControlFactory) {
		this.partControlFactory = partControlFactory;
	}
    
    private int numRows = 0;
    
    /**
     * @return Returns the numRows.  Note that the actual number of rows may
     * be less than numRows which returns the number of rows that have been
     * requested.
     */
    public int getNumRows() {
        return numRows;
    }
    /**
     * Sets the number of rows in the MRO.
     * @param numRows The numRows to set.
     */
    public void setNumRows(int numRows) {
        int oldNumRows = numRows;
        this.numRows = numRows;
        ensureCorrectNumberOfCells();
    }
    
    /**
     * @return the actual number of visible rows in the MRO.
     */
    public int getActualNumRows() {
        return recordObjectEditors.size() / numColumns;
    }
    
    /**
     * @return the maximum number of rows that can be displayed.  If the 
     * mro is setNumRows() larger than getMaxNumRows, then getActualNumRows()
     * will == getMaxNumRows();
     */
    public int getMaxVisibleRows() {
        if (cellHeight < 0)
            return 1;
        else
            return mro.getSize().y / cellHeight;
    }

    private int numColumns = 1;
    
    /**
     * Possible future API
     * @return Returns the numColumns.
     */
    protected int getNumColumns() {
        return numColumns;
    }
    /**
     * Possible future API
     * @param numColumns The numColumns to set.
     */
    private void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
        ensureCorrectNumberOfCells();
    }

    private int curColumn=-1;
    
    /** (non-api)
     * @return Returns the curCol.
     */
    protected int getCurColumn() {
        return curColumn;
    }
    
    private int curRow=-1;
    /**
     * @return Returns the curRow.
     */
    public int getCurRow() {
        return curRow;
    }

    private int curElement=-1;
    
    /**
	 * @return Returns the curElement.
	 */
	public int getCurElement() {
		return curElement;
	}
    
    
    private boolean settingFocus = false;
    
    /**
     * Since setFocus() actually executes in the background, it is possible
     * for some events to call setFocus() multiple times before a previous
     * call has finished executing.  This method returns if the background
     * part of the setFocus() operation is still running so that clients can 
     * avoid queuing multiple setFocus() requests at once.
     * <p>
     * Note that if multiple setFocus() commands are queued, the first one to
     * complete will clear the isSettingFocus() flag.  This is done because
     * some platforms may merge setFocus() events and so we cannot rely on
     * the same number of setFocus events to execute as we requested.
     * 
     * @return true if a pending setFocus() operation is still executing in the 
     * background, false if at least one pending setFocus() operation has
     * completed.
     */
    public boolean isSettingFocus() {
        return settingFocus;
    }
    
    /**
     * @param newCol
     * @param newRow
     * @param newElement
     */
    protected void setFocus(final int newCol, final int newRow, final int newElement) {
        settingFocus = true;
        RecordObjectEditor editor = getEditorAt(newCol, newRow);
        
        // If there's no editor, that means there's 0 elements in our collection.
        // Set the focus to the MRO canvas
        if (editor == null) {
            mro.setFocus();
            settingFocus = false;
            return;
        }
        
        final Control toFocus = editor.getCellElement(newElement);
        Display.getCurrent().asyncExec(new Runnable() {
            public void run() {
                toFocus.setFocus();
                settingFocus = false;
            }
        });
    }
    
    /**
     * @param newCol
     * @param newRow
     * @param newElement
     */
    protected void setFocusNow(final int newCol, final int newRow, final int newElement) {
        RecordObjectEditor editor = getEditorAt(newCol, newRow);

        // If there's no editor, that means there's 0 elements in our collection.
        // Set the focus to the MRO canvas
        if (editor == null) {
            mro.setFocus();
            settingFocus = false;
            return;
        }
        
        final Control toFocus = editor.getCellElement(newElement);
        toFocus.setFocus();
    }
    
    /**
     * Sets the focus to the specified element within the specified row.  Note
     * that the actual focus setting will be queued to run in the background
     * and the control will return immediately to the caller.<p>
     * 
     * <p>If the caller needs to guard against reentrant calls to setFocus(),
     * use the isSettingFocus() method to detect if the focus is already in
     * the process of being set.</p>
     * 
     * @param newRow The new row
     * @param newElement The new element within that row
     */
    public void setFocus(int newRow, int newElement) {
        setFocus(0, newRow, newElement);
    }
    
    /**
     * Sets the focus to the specified element within the specified row.  This
     * version of the methos performs the setFocus call immediately; it does
     * not queue it in a Runnable to run later.<p>
     * 
     * @param newRow The new row
     * @param newElement The new element within that row
     */
    public void setFocusNow(int newRow, int newElement) {
        setFocusNow(0, newRow, newElement);
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.swt.widgets.Composite#setFocus()
     */
    public boolean setFocus() {
        if (getNumRows() < 1)
            return mro.setFocus();
        else {
            setFocus(curColumn, curRow, curElement);
            return true;
        }
    }

    /*
     * Here's where we keep our actual row edit objects.  We never delete
     * row editors.  Instead, we hide them and cache them.  Since there's an
     * upper-bound on how many of these can fit on the screen, this does not
     * result in a memory leak.
     */
    protected LinkedList recordObjectEditors = new LinkedList();
    private LinkedList spareEditors = new LinkedList();
    
    /**
     * @param editor  Given a RecordObjectEditor that is currently being displayed,
     * return its column and row as a Point.
     * 
     * @return Point(col, row) of the specified RecordObjectEditor
     */
    public Point getPositionOf(RecordObjectEditor editor) {
        int i=0;
        for (Iterator editors = recordObjectEditors.iterator(); editors.hasNext();) {
            RecordObjectEditor current = (RecordObjectEditor) editors.next();
            if (editor == current) {
                return new Point(i%numColumns, i/numColumns);
            }
            ++i;
        }
        throw new IllegalArgumentException("Invalid RecordObjectEditor");
    }
    
    /**
     * Return the RecordObjectEditor at the specified (col, row).
     * 
     * @param col The column of the desired RecordObjectEditor
     * @param row The row of the desired RecordObjectEditor
     * @return The RecordObjectEditor corresponding to the specified (col, row)
     */
    protected RecordObjectEditor getEditorAt(int col, int row) {
        int position = row * numColumns + col;
        RecordObjectEditor editor = null;
        /*
         * There are more efficient algorithms than searching from the beginning
         * each time, so I'll change this if it proves to be too time-consuming...
         * On today's hardware, this is likely to be just *fine*. ;-)
         */
        Iterator iter = recordObjectEditors.iterator();
        for (int i=0; i <= position && iter.hasNext(); ++i) {
            editor = (RecordObjectEditor) iter.next();
        }
        return editor;
    }
    
    /**
     * Return the RecordObjectEditor at the specified (col, row).
     * 
     * @param col The column of the desired RecordObjectEditor
     * @param row The row of the desired RecordObjectEditor
     * @return The RecordObjectEditor corresponding to the specified (col, row)
     */
    public RecordObjectEditor getEditorAt(int row) {
        return getEditorAt(0, row);
    }

    private RecordObjectEditor curEditor = null;
    
    /**
     * @return Returns the curEditor.
     */
    public RecordObjectEditor getCurEditor() {
        if (curEditor != null)
            return curEditor;
        else if (curRow >= 0)
            return getEditorAt(curRow);
        else
            return getEditorAt(0);
    }

    private int cellWidth=-1, cellHeight=-1;
    
    /**
     * Returns the size of a cell as a Point.  Returns (-1, -1) if no cells
     * are visible.
     * 
     * @return a Point describing the size of a cell
     */
    public Point getCellSize() {
        if (cellWidth < 1) {
            if (!recordObjectEditors.isEmpty()) {
                RecordObjectEditor first = (RecordObjectEditor) recordObjectEditors.getFirst();
                Point cellSize = first.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT);
                cellWidth = cellSize.x;
                cellHeight = cellSize.y;
            }
        }
        return new Point(cellWidth, cellHeight);
    }
    
    /**
     * Make sure that we have as many rows/columns as requested and that they're
     * laid out properly. If new objects are needed, they are appended to the
     * end. If there are too many objects, objects from the end are removed
     * until the focused row is the last row. Then objects from the beginning
     * are removed and all rows are scrolled upward until there are the proper
     * number of rows.
     */
    private void ensureCorrectNumberOfCells() {
        int numObjectsNeeded = numRows * numColumns;
        int numObjectsGot = recordObjectEditors.size();
        
        // Make sure we have the cell dimensions
        if (cellWidth < 0) {
            if (numObjectsNeeded > 0) {
                RecordObjectEditor editor;
                if (numObjectsGot > 0) {
                    editor = (RecordObjectEditor) recordObjectEditors.getFirst();
                } else {
                    editor = new RecordObjectEditor(this);
                    recordObjectEditors.addLast(editor);
                    ++numObjectsGot;
                    if (mroListener != null) {
                        mroListener.cellRefresh(editor, 0);
                    }
                }
                Point dimensions = editor.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
                cellWidth = dimensions.x;
                cellHeight = dimensions.y;
                mro.layout(true);
            }
        }
        
        // Make sure the number of cells requested fits in our dimensions
        if (cellWidth < 0)
            numObjectsNeeded = 0;
        else {
        	// Figure out how many actual rows we will include
            int numRowsPossible = mro.getSize().y / (cellHeight + MROLayout.padding);
            if (numRows > 0 && numRowsPossible < 1)
            	numRowsPossible = 1;

            // Now compute the actual number of part objects we need
            if (numRowsPossible < numRows)
                numObjectsNeeded = numRowsPossible * numColumns;
        }
        
        if (numObjectsNeeded == numObjectsGot) return;
        
        // Now make sure the number of cells we've got is the same as the 
        // number of cells that have been requested.
        //
        // Note: SWT object construction/destruction is very expensive, so we
        // cache SWT objects we've created rather than destroying and re-creating
        // them.
        if (numObjectsNeeded > numObjectsGot) {
            for (int addingOffset = 0; addingOffset < numObjectsNeeded - numObjectsGot; ++addingOffset) {
                RecordObjectEditor editor;
                if (spareEditors.isEmpty()) {
                	editor = new RecordObjectEditor(this);
                	recordObjectEditors.addLast(editor);
                } else {
                	editor = (RecordObjectEditor) spareEditors.removeLast();
                	recordObjectEditors.addLast(editor);
                }
                if (mroListener != null) {
                    mroListener.cellRefresh(editor, numObjectsGot + addingOffset);
                }
                if (!editor.getControl().isVisible())
                	editor.getControl().setVisible(true);
            }
        } else {
            for (int numRowsToRemove = (numObjectsGot - numObjectsNeeded) / numColumns; 
                numRowsToRemove > 0; --numRowsToRemove) 
            {
                // If the current row is the last row, scroll it rather than remove it
//                Logger.log().debug("ActualNumRows = " + getActualNumRows());
//                Logger.log().debug("CurRow = " + curRow);
                if (curRow >= getActualNumRows() - 1) {
                    for (int col=0; col < numColumns; ++col) {
                        RecordObjectEditor editor = (RecordObjectEditor) recordObjectEditors.removeFirst();
                        spareEditors.addLast(editor);
                        editor.getControl().setVisible(false);
                    }
                    --curRow;
                } else {
                    for (int col=0; col < numColumns; ++col) {
                        RecordObjectEditor editor = (RecordObjectEditor) recordObjectEditors.removeLast();
                        spareEditors.addLast(editor);
                        editor.getControl().setVisible(false);
                    }
                }
            }
        }
        
        // Layout all of the cells
		mro.layout(true);
    }
    
    /**
     * Insert numCellsToInsert rows at position. Any "overflow" rows will be
     * deleted. The new cells will have no data bindings and a cellAdded event
     * will be fired for each new cell.
     * 
     * @param numRowsToInsert
     *            The number of rows of cells to insert
     * @param position
     *            The 0-based position at which the new cells should be
     *            inserted.
     */
    static int count=0;
    public void insert(int numRowsToInsert, int position) {
//        Logger.log().debug("Insert; serial#" + count);
//        ++count;
        // Find the insert position
        int recordObjectOffset=0;
        ListIterator iterator = recordObjectEditors.listIterator();
        for (int moveTo = position; moveTo > 0; --moveTo) {
            iterator.next();
            ++recordObjectOffset;
        }
        
        // Insert the new rows
        for (int current = numRowsToInsert * numColumns; current > 0; --current) {
            RecordObjectEditor editor;
            if (spareEditors.isEmpty()) {
                editor = new RecordObjectEditor(this);
                iterator.add(editor);
            } else {
                editor = (RecordObjectEditor) spareEditors.removeLast();
                iterator.add(editor);
                editor.getControl().setVisible(true);
            }
            if (mroListener != null) {
                mroListener.cellRefresh(editor, recordObjectOffset);
                ++recordObjectOffset;
            }
        }
        ensureCorrectNumberOfCells();
        
        // Update the CurRow counter
        Logger.log().debug(MRO.class, "position=" + position + "; curRow=" + curRow + "; numRowsToInsert=" + numRowsToInsert);
        if (position <= curRow)
            curRow += numRowsToInsert;
    }
    
	/**
     * Delete numRowsToDelete rows of cells at position. New cells will be
     * created at the end of the list until either the MRO's display is full or
     * numRows is reached. If fewer than numRows rows is the result, then
     * numRows will be adjusted as appropriate. The new cells will not be bound
     * to any data. If the RecordObjectAdapter of any deleted objecthas dirty
     * data, the undo method will be called to discard any changes.
     * 
     * @param numRowsToDelete
     *            The number of rows of cells to delete
     * @param position
     *            The 0-based position of the cell to delete
     * @param keepNumRowsConstant
     *            The MRO should have numRowsToDelete rows added to the end
     *            after deleting the rows so that numRows is not changed after
     *            executing this method.
     */
    public void delete(int numRowsToDelete, int position, boolean keepNumRowsConstant) {
        int oldCurRow = curRow;
        
        // Make sure we *can* delete that number of rows
        if (numRowsToDelete > getActualNumRows())
            throw new IllegalArgumentException("Somebody asked me to delete "
    				+ numRowsToDelete + " rows, but there are only "
    				+ getActualNumRows() + " rows in existence");
        
        // Find the delete position
        ListIterator iterator = recordObjectEditors.listIterator();
        for (int moveTo = position; moveTo > 0; --moveTo) {
            iterator.next();
        }
        
        // Delete the rows
        for (int current = numRowsToDelete * numColumns; current > 0; --current) {
            RecordObjectEditor editor = (RecordObjectEditor) iterator.next();
            iterator.remove();
            if (editor.getObjectAdapter().isDirty()) {
                editor.getObjectAdapter().undoChanges();
            }
            spareEditors.addLast(editor);
            if (!keepNumRowsConstant)
            	editor.getControl().setVisible(false);
        }
        
        if (keepNumRowsConstant) {
        	ensureCorrectNumberOfCells();
        } else {
           numRows -= numRowsToDelete;
           mro.layout(true);
        }

        // If we just deleted the jast row
        if (numRows < 1) {
            mro.setFocus();
            return;
        }
    
        // Update the CurRow counter
//        Logger.log().debug("" + curRow + " " + position + " " + numRowsToDelete);
        if (position <= oldCurRow && oldCurRow < position + numRowsToDelete) {
            if (oldCurRow >= numRows)
                curRow = numRows;
            setFocus(curRow, getCurElement());
        }
    }
    
    /**
     * Delete numRowsToDelete rows of cells at position. New cells will be
     * created at the end of the list until either the MRO's display is full or
     * numRows is reached. If fewer than numRows rows is the result, then
     * numRows will be adjusted as appropriate. The new cells will not be bound
     * to any data. If the RecordObjectAdapter of any deleted objecthas dirty
     * data, the undo method will be called to discard any changes.
     * 
     * @param numRowsToDelete
     *            The number of rows of cells to delete
     * @param position
     *            The 0-based position of the cell to delete
     */
    public void delete(int numRowsToDelete, int position) {
        delete(numRowsToDelete, position, true);
    }
    
    /**
     * Deletes numRows rows at the beginning and appends new rows at the end, 
     * scrolling all rows in between.
     * 
     * @param numRows
     */
    public void scrollUp(int numRows) {
        delete(numRows, 0);
    }
    
    /**
     * Inserts numRows rows at the beginning and deletes rows at the end in order
     * to scroll the view down.
     * 
     * @param numRows
     */
    public void scrollDown(int numRows) {
        insert(numRows, 0);
    }
    
    /**
     * Set the focus back to the MRO's idea of the current cell (normally due
     * to a validation failure).
     */
    public void goBackToTheOldCell() {
        setFocus(curColumn, curRow, curElement);
    }
    
    // ---- Forwarded Events ---------------------------------------------------
    
    private IMROListener mroListener = null;
    
    /**
     * Set the object that will listen to MRO events...
     * @param listener
     */
    public void setMROListener(IMROListener listener) {
    	mroListener = listener;
    }
    
    /** (non-api)
     * @param sender
     * @param isValid
     */
    public void elementValid(RecordObjectEditor sender, boolean isValid) {
        if (mroListener != null)
            mroListener.elementValid(sender, isValid);
    }

    /** (non-api)
     * @param sender
     * @param e
     */
    public void elementFocusGained(RecordObjectEditor sender, FocusEvent e) {
        if (curElement >= 0)
            curEditor = getEditorAt(curColumn, curRow);
        
        try {
            int newElement = sender.getChildElementNum((Control)e.widget);
            Point newColRow = getPositionOf(sender);

            if (newColRow.y != curRow && curEditor != null)
                curEditor.getObjectAdapter().validateAllFields();
            
            // Fire the row change event to give clients a chance
            // to override this action
            if (curRow != newColRow.y || (curColumn==newColRow.x && curRow==newColRow.y)) {
                if (!fireRowChangeRequest(curElement, curRow, newElement, newColRow.y)) {
                    goBackToTheOldCell();
                    return;
                }
            }

            fireRowChangeEvent(curColumn, curRow, newElement, newColRow.y);

            curElement = newElement;
            curColumn = newColRow.x;
            curRow = newColRow.y;
//            Logger.log().debug("FocusGained: (" + curColumn + ", " + curRow + ", " + curElement + ")");
        } catch (UnableToSaveException e1) {
            goBackToTheOldCell();
        }
        if (mroListener != null)
        	mroListener.elementFocusGained(sender, e);
    }

    /** (non-api)
     * @param sender
     * @param e
     */
    public void elementFocusLost(RecordObjectEditor sender, FocusEvent e) {
        if (mroListener != null)
            mroListener.elementFocusLost(sender, e);
    }

    /** (non-api)
     * @param sender
     * @param e
     */
    public void elementKeyPressed(RecordObjectEditor sender, KeyEvent e) {
        RecordObjectEditor current = getEditorAt(curColumn, curRow);
        switch (e.keyCode) {
            case SWT.ARROW_DOWN:
                if (getActualNumRows()-1 > getCurRow()) {
                    if (!(e.widget instanceof Combo || e.widget instanceof CCombo))
                        try {
                            current.getObjectAdapter().validateAllFields();
                            setFocus(curColumn, curRow+1, curElement);
                        } catch (UnableToSaveException e1) {}
                }
                break;
            case SWT.ARROW_UP:
                if (getCurRow() > 0) {
                    if (!(e.widget instanceof Combo || e.widget instanceof CCombo))
                        try {
                            current.getObjectAdapter().validateAllFields();
                            setFocus(curColumn, curRow-1, curElement);
                        } catch (UnableToSaveException e1) {}
                }
                break;
        }
        if (mroListener != null)
            mroListener.elementKeyPressed(sender, e);
    }

    /** (non-api)
     * @param sender
     * @param e
     */
    public void elementKeyReleased(RecordObjectEditor sender, KeyEvent e) {
        if (mroListener != null)
            mroListener.elementKeyReleased(sender, e);
    }

    /** (non-api)
     * @param sender
     * @param e
     */
    public void elementKeyTraversed(RecordObjectEditor sender, TraverseEvent e) {
        switch (e.detail) {
            case SWT.TRAVERSE_TAB_NEXT:
                if (getCurRow() < getActualNumRows()-1) {
                    if (sender != null && (Composite)sender.getControl() != null) {
                        Control[] tabList = ((Composite)sender.getControl()).getTabList();
                        if (e.widget == tabList[tabList.length-1]) {
                            e.doit = false;
                            setFocus(getCurRow()+1, 0);
                        }
                    }
                }
                break;
            case SWT.TRAVERSE_TAB_PREVIOUS:
                if (getCurRow() > 0) {
                    if (sender != null && (Composite)sender.getControl() != null) {
                        Control[] tabList = ((Composite)sender.getControl()).getTabList();
                        if (e.widget == tabList[0]) {
                            e.doit = false;
                            setFocus(getCurRow()-1, tabList.length-1);
                        }
                    }
                }
                break;
        }
        if (mroListener != null)
            mroListener.elementKeyTraversed(sender, e);
    }
    
    // ---- IMaster implementation ---------------------------------------------

    private LinkedList masterObjectChangeListeners = new LinkedList();
    
    /* (non-Javadoc)
	 * @see com.swtworkbench.ed.aware.masterdetail.IMaster#addMasterObjectChangeListener(com.swtworkbench.ed.aware.masterdetail.IMasterObjectChangeListener)
	 */
	public void addMasterObjectChangeListener(IMasterObjectChangeListener listener) {
		masterObjectChangeListeners.addLast(listener);
	}
    
    /* (non-Javadoc)
	 * @see com.swtworkbench.ed.aware.masterdetail.IMaster#removeMasterObjectChangeListener(com.swtworkbench.ed.aware.masterdetail.IMasterObjectChangeListener)
	 */
	public void removeMasterObjectChangeListener(IMasterObjectChangeListener listener) {
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

   /* (non-Javadoc)
	 * @see com.swtworkbench.ed.aware.masterdetail.IMaster#getMasterObject(int)
	 */
	public Object getMasterObject(int offset) {
		return getEditorAt(offset).getObjectAdapter().getInput();
	}

    // ---- Event handlers -----------------------------------------------------

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
            resizeMRO();
            if (mroListener != null)
                mroListener.mroResized(theThis);
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

    protected void updateHorizSlider() {
        int selection = horizontalSlider.getSlider().getSelection();
        body.setLocation(-1 * selection, 0);
    }
    
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
            if (mroListener != null)
                mroListener.vertSliderSelected(verticalSlider, e);
        }
    };

    private class MROKeyListener implements KeyListener {
        /* (non-Javadoc)
		 * @see org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events.KeyEvent)
		 */
		public void keyPressed(KeyEvent e) {
            elementKeyPressed(null, e);
		}
        /* (non-Javadoc)
		 * @see org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events.KeyEvent)
		 */
		public void keyReleased(KeyEvent e) {
            elementKeyReleased(null, e);
		}
    }

    private class MROTraverseListener implements TraverseListener {
		public void keyTraversed(TraverseEvent e) {
            elementKeyTraversed(null, e);
		}
    }
}


