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

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Control;

import com.swtworkbench.ed.aware.interfaces.IFieldAdapter;
import com.swtworkbench.swtutils.logger.Logger;


/**
 * Class AwareComboField.  An AwareField for Combo-like SWT controls<p>
 * 
 * In this implementation, we treat a Combo box like a small list box.  There
 * is <b>no</b> support for using Combo as a recently used values list where
 * the user is allowed to type something other than is in the Combo's valid
 * values list.<p>
 * 
 * This class generally works with SWT objects supporting the following methods
 * and where these methods implement the same semantics as the corresponding
 * methods in SWT's Combo class:<p>
 * 
 * <ul>
 * <li>String[] getSelection()
 * <li>int getSelectionIndex()
 * <li>select(int)
 * <li>setText(String)
 * <li>String getText()
 * <li>removeAll()
 * <li>add(String)
 * <li>void addSelectionListener(SelectionListener)
 * <li>void removeSelectionListener(SelectionListener)
 * </ul>
 * 
 * @author daveo
 */
public class AwareComboField extends AbstractAwareField {
    
    private Method getSelection;
    private Method getSelectionIndex;
    private Method select;
    private Method removeAll;
    private Method add;
    private Method getText;
    private Method setText;
    
    private Method addSelectionListener;
    private Method removeSelectionListener;
    
    //private static final String CHOICE_ERR = "Please choose one of the valid values in the combo";

    /**
     * Constructor AwareIntSelectorField.  
     * 
     * @param dataPersister
     * @param control
     */
    public AwareComboField(IFieldAdapter dataPersister, Control control) {
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
        select = getMethod("select", new Class[] {Integer.TYPE});
        getText = getMethod("getText", new Class[] {});
        setText = getMethod("setText", new Class[] {String.class});
        removeAll = getMethod("removeAll", new Class[] {});
        add = getMethod("add", new Class[]{String.class});
        addSelectionListener = getMethod("addSelectionListener", new Class[] {SelectionListener.class});
        removeSelectionListener = getMethod("removeSelectionListener", new Class[] {SelectionListener.class});
        if (getSelection == null
            || getSelectionIndex == null
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
     * Method select.  Select the specified choice.
     * @param choice  An int representing the position of the desired choice
     * in the combo box list.  0-based. 
     */
    protected void select(int choice) {
        invokel(select, new Object[] {new Integer(choice)});
    }
    
    /**
     * Method getText.  Returns the text inside the combo's edit field.
     * @return String the text inside the combo's edit field.
     */
    protected String getText() {
        return (String) invokel(getText, new Object[] {});
    }
    
    /**
     * Method setText.  Sets the text inside the combo's edit field.
     * @param newValue The new text string.
     */
    protected void setText(String newValue) {
        invokel(setText, new Object[] {newValue});
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
    String[] choices;
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IAwareWidget#getValue()
     */
    public Object getValue() {
        int selection = getSelectionIndex();
        if (selection >= 0)
            return validValues[selection];
        else
            return null;
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IAwareWidget#getStringValue()
     */
    public String getStringValue() {
        return getText();
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
    
    /**
     * Method selectObject.  Selects the string corresponding to the passed
     * Object.
     * 
     * @param toSelect The Object whose corresponding String should be selected
     */
    protected int findSelectedObject(String toSelect) {
        int selection;
        boolean foundIt = false;
        for (selection = 0; selection < choices.length; selection++) {
            if (toSelect.equals(choices[selection])) {
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
        
        choices = dataPersister.getChoices();
        removeAll();
        for (int i = 0; i < choices.length; i++) {
            add(choices[i]);
        }

        int selection = findSelectedObject(newValue);
        if (selection >= 0)
            select(selection);
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IAwareWidget#clear()
     */
    public void clear() {
        removeAll();
        setText("");
    }

    
    /*
     * SWT event handlers here...
     */

    protected SelectionListener selectionListener = new SelectionListener() {
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
         */
        public void widgetSelected(SelectionEvent e) {
            printEvent(e);
            dataPersister.save(awareField, getValue());
            setDirty(false);
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
        // Nothing to do here; this type of field is always valid
    }

    
}
