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
package com.swtworkbench.ed.aware.navigators;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Slider;

import com.swtworkbench.ed.aware.adapters.pojo.UnableToSaveException;
import com.swtworkbench.ed.aware.interfaces.IRecordObjectAdapter;
import com.swtworkbench.ed.aware.interfaces.IRecordValidListener;
import com.swtworkbench.ed.aware.iterator.IteratorAdapterFactory;
import com.swtworkbench.ed.aware.model.IModelChangeListener;
import com.swtworkbench.ed.aware.model.Model;
import com.swtworkbench.ed.aware.model.ModelChangeAdapter;
import com.swtworkbench.ed.aware.model.NewObject;
import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class SliderNavigator.  A data-aware class that hooks up a Slider, an
 * IRecordObjectAdapter, and a collection.<p>
 * 
 * Expects a collection with a size() method and either a listIterator() or
 * an iterator() method.
 * 
 * @author daveo
 */
public class SliderNavigator extends AbstractNavigator {
    
    protected final Slider slider;
    
    protected IRecordObjectAdapter recordObject = null;
    private boolean deleteEnabled = true;
    private boolean insertEnabled = true;
    
    /**
     * Constructor SliderNavigator.  Construct a Slider along with the SliderNavigator
     * 
     * @param parent The parent Composite
     * @param style The SWT style bits for the Slider
     */
    public SliderNavigator(Composite parent, int style) {
        slider = new Slider(parent, style);
    }
    
    /**
     * Constructor SliderNavigator.  Construct a SliderNavigator attached to an
     * existing SWT Slider object.
     * 
     * @param slider
     */
    public SliderNavigator(Slider slider) {
        this.slider = slider;
    }
    


    /**
     * Method edit.  Edit the data in container using the specified 
     * IRecordObjectAdapter.  The objects in the container
     * will become the items scrolled over by the Slider.  The 
     * IRecordObjectAdapter will be used to display/edit the specified 
     * collection of objects.
     * 
     * @param bean The JavaBean containing the collection to edit.
     * @param propertyName The name of the property containing the collection.
     * @param recordObject The IRecordObjectAdapter to use to display/edit the collection.
     * 
     * @throws SecurityException if something goes wrong with reflection
     * @throws NoSuchMethodException if something goes wrong with reflection
     * @throws IllegalAccessException if something goes wrong with reflection
     * @throws InvocationTargetException if something goes wrong with reflection 
     * 
     * @throws UnableToSaveException If the IRecordObjectAdapter currently has
     * unsaved data and cannot save it due to invalid data in some editor.
     */
    public void edit(Object container, String propertyName, final IRecordObjectAdapter recordObject) throws SecurityException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, UnableToSaveException {
        super.edit(container, propertyName);
        
        Model.getDefault().removeModelChangeListener(oldContainer, modelChangeListener);
        Model.getDefault().addModelChangeListener(container, modelChangeListener);

        if (this.recordObject != null) {
            this.recordObject.removeKeyListener(keyListener);
            this.recordObject.removeRecordValidListener(recordValidListener);
        }

        this.recordObject = recordObject;

        // Set the slider defaults
        slider.setIncrement(1);
        slider.setPageIncrement(1);
        slider.setMaximum(getSize());
        slider.setMinimum(0);
        slider.setThumb(1);
        
        if (getSize() > 0)
            recordObject.setInput(iterator.at(0));
        
        slider.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                int selection = slider.getSelection();
                try {
                    recordObject.setInput(iterator.at(selection));
                } catch (UnableToSaveException e1) {
                    Logger.log().error(e1, "Unable to save current object");
                }
            }
            public void widgetDefaultSelected(SelectionEvent e) {
                widgetSelected(e);
            }
        });
        
        recordObject.addKeyListener(keyListener);
        recordObject.addRecordValidListener(recordValidListener);
    }
    
    protected KeyListener keyListener = new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
            int max = slider.getMaximum();
            int selection = slider.getSelection();

            switch (e.keyCode) {
                case SWT.PAGE_DOWN:
                    if (selection < max-1) {
                        ++selection;
                        try {
                            recordObject.setInput(iterator.at(selection));
                            slider.setSelection(selection);
                        } catch (UnableToSaveException e1) {
                        }
                    }
                    break;
                case SWT.PAGE_UP:
                    if (selection > 0) {
                        --selection;
                        try {
                            recordObject.setInput(iterator.at(selection));
                            slider.setSelection(selection);
                        } catch (UnableToSaveException e1) {
                        }
                    }
                    break;
                case SWT.HOME:
                    if ((e.stateMask & SWT.CONTROL) != 0 && selection > 0) {
                        selection = 0;
                        try {
                            recordObject.setInput(iterator.at(selection));
                            slider.setSelection(selection);
                        } catch (UnableToSaveException e1) {
                        }
                    }
                    break;
                case SWT.END:
                    if ((e.stateMask & SWT.CONTROL) != 0 && selection < max-1) {
                        selection = max-1;
                        try {
                            recordObject.setInput(iterator.at(selection));
                            slider.setSelection(selection);
                        } catch (UnableToSaveException e1) {
                        }
                    }
                    break;
                case SWT.INSERT:
                    try {
                        if (isInsertEnabled()) {
                            recordObject.saveDirtyFields();
                            recordObject.validateAllFields();
                            iCreated = true;
                            Model.getDefault().create(container);
                        }
                    } catch (UnableToSaveException e1) {
                        // This isn't an error.  In this case; we just won't create the new
                        // object because the user has to fix the validation problem first...
                    }
                    break;
                case SWT.DEL:
                    int size=0;
                    try {
                        size = getSize();
                    } catch (Exception e1) {
                        Logger.log().error(e1, "Unable to get collection size");
                    }
                    
                    if ((e.stateMask & SWT.CONTROL) != 0 && size > 0 && max > 0 && isDeleteEnabled()) {
                        Object toDelete = iterator.at(selection);
                        
                        boolean canDelete = Model.getDefault().fireVetoableObjectDeletionEvent(
                            container,
                            selection,
                            toDelete);
                            
                        if (canDelete) {
                            iterator.remove();
                            iDeleted = true;

                            Model.getDefault().fireObjectDeletedEvent(
                                container,
                                selection,
                                toDelete);
                        }
                    }
                    break;
            }
        }
    };
    
    protected boolean iDeleted = false;
    protected Object toResort = null;
    protected boolean iCreated = false;
    
    protected IModelChangeListener modelChangeListener = new ModelChangeAdapter() {
        public void objectCreated(Object container, NewObject newObject) {
            try {
                // Avoid CoModificationException--get a new iterator
                iterator = IteratorAdapterFactory.iterator(container);
            } catch (Exception e) {
                Logger.log().error(e, "Unable to get a new iterator");
            }

            int max=0;
            try {
                max = getSize();
            } catch (Exception e) {
                Logger.log().error(e, "Unable to get a new maxsize");
            }
            
            if (iCreated || newObject.newObject == toResort) {
                // If I created it or it's the first element in the list, or
                // the object was re-sorted from a different position, I 
                // need to scroll to it so it can be edited
                int selection = newObject.position;

                slider.setMaximum(max);
                slider.setSelection(selection);

                try {
                    recordObject.setInput(iterator.at(selection));
                } catch (UnableToSaveException e1) {
                    // Because we just validated all fields, this should never happen
                    Logger.log().error(e1, "We should never get here...");
                }
                iCreated = false;
            } else {
                // Otherwise, just update the scroll bar appropriately
                int selection = slider.getSelection();
                if (newObject.position <= selection) {
                    ++selection;
                }
                slider.setMaximum(max);
                slider.setSelection(selection);
            }
        }
        
        public void objectDeleted(
            Object container,
            int deletedItemPosition,
            Object removed) 
        {
            // If the current row's object is being removed and it appears again
            // in another place, we know that the row was re-sorted and we'd
            // better move the focus with it.
            if (removed == recordObject.getInput());
                toResort = removed;
            
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
            
            int selection = slider.getSelection();
            int max=0;
            try {
                max = getSize();
            } catch (Exception e) {
                Logger.log().error(e, "Unable to get a new maxsize");
            }
            slider.setMaximum(max);
            
            // We're now done unless we happen to be viewing the item that
            // was deleted.
            if (selection == deletedItemPosition) {
                if (selection >= max-1) {
                    selection = max-1;
                    if (selection < 0) selection = 0;
                    slider.setSelection(selection);
                }
                recordObject.undoChanges();
                try {
                    if (max > 0) {
                        recordObject.setInput(null);
                        recordObject.setInput(iterator.at(selection));
                    } else {
                        recordObject.clear();
                        recordObject.setDirty(false);
                        recordObject.setInput(null);
                        recordObject.setDirty(false);
                    }
                } catch (UnableToSaveException e1) {
                    // Because we just did an undo, we know this will succeed
                    Logger.log().error(e1, "We should never get here...");
                }
            }
        }
    };
    
    protected IRecordValidListener recordValidListener = new IRecordValidListener() {
        public void isValid(boolean isValid) {
            slider.setEnabled(isValid);
        }
    };

    /**
     * Method getControl.  Returns the underlying Slider object.
     * 
     * @return Slider the underlying Slider object.
     */
    public Control getControl() {
        return slider;
    }
    
    /**
     * Method isDeleteEnabled.  Returns if deletion is enabled for the underlying
     * collection.
     * 
     * @return true if delete is enabled; false otherwise.
     */
    public boolean isDeleteEnabled() {
        return deleteEnabled;
    }

    /**
     * Method setDeleteEnabled.  Sets if deletion is enabled for the underlying
     * collection.
     * 
     * @param b true if delete is enabled; false otherwise.
     */
    public void setDeleteEnabled(boolean b) {
        deleteEnabled = b;
    }

    /**
     * Method isInsertEnabled.  Returns if object insertions are enabled (provided an
     * appropriate object factory has been registered).  True by default.
     * 
     * @return true if insert is enabled.  False otherwise.
     */
    public boolean isInsertEnabled() {
        return insertEnabled;
    }

    /**
     * Method setInsertEnabled.  Sets if object insertions are enabled.
     * 
     * @param b true if insert is enabled.  False otherwise.
     */
    public void setInsertEnabled(boolean b) {
        insertEnabled = b;
    }

}



