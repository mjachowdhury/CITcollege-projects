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
package com.swtworkbench.ed.aware.fields;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Control;

import com.swtworkbench.ed.aware.interfaces.IFieldAdapter;
import com.swtworkbench.swtutils.logger.Logger;


/**
 * Class AwareSelectorField.  An AwareField for SWT controls (such as list
 * boxes) that select among a set of possible objects representing a set
 * of possible values.<p>
 * 
 * This class can operate in either a single or a multiple selection mode.
 * It automatically detects the mode of the underlying control and operates
 * in that mode as long as multi-select mode is initiated by passing SWT.MULTI
 * as one of the the control's constructor style bits.<p>
 * 
 * In single selection mode, an actual value returned by the property's
 * valid values method will be stored.<p>
 * 
 * In multi-select mode, a java.util.Set containing values returned by 
 * the property's valid values method will be stored.<p>
 * 
 * This class generally works with SWT objects supporting the following methods
 * and where these methods implement the same semantics as the corresponding
 * methods in SWT's List class:<p>
 * 
 * <ul>
 * <li>String[] getSelection()
 * <li>int getSelectionIndex()
 * <li>int[] getSelectionIndices()
 * <li>void setSelection(int)
 * <li>void setSelection(int[])
 * <li>removeAll()
 * <li>add(String)
 * <li>void addSelectionListener(SelectionListener)
 * <li>void removeSelectionListener(SelectionListener)
 * </ul>
 * 
 * @author daveo
 */
public class AwareSelectorField extends AbstractAwareField {
    
    private Method getSelection;
    private Method getSelectionIndex;
    private Method getSelectionIndices;
    private Method setSelection;
    private Method setSelectionMulti;
    private Method removeAll;
    private Method add;
    private Method addSelectionListener;
    private Method removeSelectionListener;

    /**
     * Constructor AwareIntSelectorField.  
     * 
     * @param dataPersister
     * @param control
     */
    public AwareSelectorField(IFieldAdapter dataPersister, Control control) {
        super(dataPersister, control);
        getMethods();
        addListeners(control);
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IAwareWidget#getType()
     */
    public Class getType() {
        return Integer.TYPE;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.fields.AbstractAwareField#getMethods()
     */
    protected void getMethods() {
        super.getMethods();
        getSelection = getMethod("getSelection", new Class[] {});
        getSelectionIndex = getMethod("getSelectionIndex", new Class[] {});
        getSelectionIndices = getMethod("getSelectionIndices", new Class[] {});
        setSelection = getMethod("setSelection", new Class[] {Integer.TYPE});
        setSelectionMulti = getMethod("setSelection", new Class[] {int[].class});
        removeAll = getMethod("removeAll", new Class[] {});
        add = getMethod("add", new Class[]{String.class});
        addSelectionListener = getMethod("addSelectionListener", new Class[] {SelectionListener.class});
        removeSelectionListener = getMethod("removeSelectionListener", new Class[] {SelectionListener.class});
        if (getSelection == null
            || getSelectionIndex == null
            || getSelectionIndices == null
            || setSelection == null
            || setSelectionMulti == null
            || removeAll == null
            || add == null
            || addSelectionListener == null
            || removeSelectionListener == null)
            Logger.log().error(
                new Exception(),
                "Unable to retrieve all necessary methods on "
                    + getControl().getClass().getName());
    }
    
    /**
     * Method isMultiSelect.  Returns if the underlying control supports
     * multiple selection.
     * 
     * @return boolean true if the underlying control supports multiple selection, false otherwise
     */
    protected boolean isMultiSelect() {
        return (control.getStyle() & SWT.MULTI) != 0;
    }
    
    /**
     * Method getSelection.  Returns the set of currently selected strings
     * @return The set of currently selected strings as a String[]
     */
    protected String[] getSelection() {
        return (String[])invokel(getSelection, new Object[] {});
    }
    
    /**
     * Method getSelectionIndex.  Calls getSelectionIndex() on the underlying
     * Control and returns the result.
     * 
     * @return int the current selection index.
     */
    protected int getSelectionIndex() {
        Integer result = (Integer)invokel(getSelectionIndex, new Object[] {});
        return result.intValue();
    }
    
    /**
     * Method getSelectionIndices.  Calls getSelectionIndices on the underlying
     * Control and returns the result.
     * 
     * @return int[] the current set of selected values.
     */
    protected int[] getSelectionIndices() {
        return (int[])invokel(getSelectionIndices, new Object[] {});
    }
    
    /**
     * Method setSelection.  calls setSelection(newSelection) on the underlying
     * control.
     * 
     * @param newSelection int The new selection index.
     */
    protected void setSelection(int newSelection) {
        invokel(setSelection, new Object[] {new Integer(newSelection)});
    }
    
    /**
     * Method setSelection.  calls setSelection(newSelection) on the underlying
     * control.
     * 
     * @param newSelection int The new selection index array.
     */
    protected void setSelection(int[] newSelection) {
        invokel(setSelectionMulti, new Object[] {newSelection});
    }
    
    /**
     * Method removeAll.  Remove all choices from the underlying control.
     */
    protected void removeAll() {
        invokel(removeAll, new Object[] {});
    }
    
    /**
     * Method add.  Add a new choice to the underlying control.
     * @param choice The choice string.
     */
    protected void add(String choice) {
        invokel(add, new Object[] {choice});
    }
    
    /**
     * Method addSelectionListener.
     *   
     * @param l the SelectionListener to add
     */
    protected void addSelectionListener(SelectionListener l) {
        invokel(addSelectionListener, new Object[] {l});
    }
    
    /**
     * Method removeSelectionListener.  
     * 
     * @param l the SelectionListener to remove
     */
    protected void removeSelectionListener(SelectionListener l) {
        invokel(removeSelectionListener, new Object[] {l});
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.fields.AbstractAwareField#addListeners(org.eclipse.swt.widgets.Control)
     */
    protected void addListeners(Control control) {
        super.addListeners(control);
        addSelectionListener(selectionListener);
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.fields.AbstractAwareField#removeListeners()
     */
    public void removeListeners() {
        super.removeListeners();
        removeSelectionListener(selectionListener);
    }

    // Keep track of the valid values corresponding to the list box strings
    Object[] validValues;
    
    /**
     * Method getValueSet.  Returns all selected values inside a java.util.Set.
     * 
     * @return Set the set of selected values.
     */
    protected Set getValueSet() {
        int[] selection = getSelectionIndices();
        HashSet result = new HashSet();
        for (int i = 0; i < selection.length; i++) {
            result.add(validValues[selection[i]]);
        }
        return result;
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IAwareWidget#getValue()
     */
    public Object getValue() {
        if (isMultiSelect())
            return getValueSet();
        else {
            int selection = getSelectionIndex();
            return validValues[selection];
        }
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IAwareWidget#getStringValue()
     */
    public String getStringValue() {
        String[] selection = getSelection();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < selection.length; i++) {
            if (result.length() == 0)
                result.append(selection[i]);
            else {
                result.append("\t");
                result.append(selection[i]);
            }
        }
        return result.toString();
    }
    
    /**
     * Method selectObject.  Selects the string corresponding to the passed
     * Object.
     * 
     * @param toSelect The Object whose corresponding String should be selected
     */
    protected int findSelectedObject(Object toSelect) {
        int selection;
        boolean foundIt = false;
        for (selection = 0; selection < validValues.length; selection++) {
            if (toSelect == validValues[selection]) { // Note use of ==, not .equals()
                foundIt = true;
                break;
            }
        }
        if (foundIt)
            return selection;
        else
            return -1;
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IAwareWidget#setValue(java.lang.Object)
     */
    public void setValue(Object newValue) {
        validValues = dataPersister.getValidValues();
        if (validValues == null) return;
        
        String[] choices = dataPersister.getChoices();
        removeAll();
        for (int i = 0; i < choices.length; i++) {
            add(choices[i]);
        }

        if (isMultiSelect()) {
            Set selected = (Set) newValue;
            int[] selection = new int[selected.size()];
            int i=0;
            for (Iterator iter = selected.iterator(); iter.hasNext();) {
                Object element = iter.next();
                selection[i] = findSelectedObject(element);
                ++i;
            }
            setSelection(selection);
        } else {
            int selection = findSelectedObject(newValue);
            if (selection >= 0)
                setSelection(selection);
        }
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IAwareWidget#clear()
     */
    public void clear() {
        removeAll();
    }

    
    /*
     * SWT event handlers here...
     */

    protected SelectionListener selectionListener = new SelectionListener() {
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
         */
        public void widgetSelected(SelectionEvent e) {
            dataPersister.save(awareField, getValue());
        }
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
         */
        public void widgetDefaultSelected(SelectionEvent e) {
            widgetSelected(e);
        }
    };
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.fields.AbstractAwareField#validateField()
     */
    public void validateField() {
        // Nothing to do here; this type of field is always valid.
    }

}
