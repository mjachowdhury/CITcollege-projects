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
import org.eclipse.swt.widgets.Display;

import com.swtworkbench.ed.aware.interfaces.IAwareWidget;
import com.swtworkbench.ed.aware.interfaces.IFieldAdapter;
import com.swtworkbench.ed.aware.marshaller.MarshallException;
import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class AwareBooleanField.
 * 
 * @author daveo
 */
public class AwareBooleanField extends AbstractAwareField implements IAwareWidget {

    // Methods for getting and setting the value of the referenced control 
    private Method setSelection = null;
    private Method getSelection = null;
    
    // Selection listener
    private Method addSelectionListener = null;
    private Method removeSelectionListener = null;
    
    /**
     * Constructor AwareBooleanField.  
     * 
     * @param dataPersister
     * @param control
     */
    public AwareBooleanField(IFieldAdapter dataPersister, Control control) {
        super(dataPersister, control);
        getMethods();
        addListeners(control);
   }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IAwareWidget#getType()
     */
    public Class getType() {
        return Boolean.TYPE;
    }
    
    
    /**
     * Method getMethods.  Get Method objects for each of the event hooks.  
     */
    protected void getMethods() {
        super.getMethods();
        setSelection = getMethod("setSelection", new Class[] {Boolean.TYPE});
        getSelection = getMethod("getSelection", new Class[] {});
        addSelectionListener = getMethod("addSelectionListener", new Class[] {SelectionListener.class});
        removeSelectionListener = getMethod("removeSelectionListener", new Class[] {SelectionListener.class});
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IAwareTextWidget#getValue()
     */
    public Object getValue() {
        ensureMarshallers();
        
        Boolean bool = getBooleanValue();
    
        // Convert it to the desired type
        Object result = null;
        try {
            result = marshall2Property.convert(bool);
        } catch (MarshallException e) {
            Logger.log().error(e, "Unable to convert " + getType().getName() + " to " + dataPersister.getType().getName() + " using source data: " + bool);
        }
        return result;
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IAwareWidget#getStringValue()
     */
    public String getStringValue() {
        return getBooleanValue().toString();
    }
    
    /**
     * Method getBooleanValue.  Gets the current value as a Boolean
     * @return Boolean the current value
     */
    protected Boolean getBooleanValue() {
        return (Boolean) invoke(getSelection, new Object[] {});
    }
    
    /**
     * Method setBooleanValue.  Sets the selection from a boolean value
     * @param value
     */
    protected void setBooleanValue(boolean value) {
        invoke(setSelection, new Object[] {new Boolean(value)});
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IAwareTextWidget#setValue(java.lang.Object)
     */
    public void setValue(Object newValue) {
        ensureMarshallers();

        Boolean updatedValue = Boolean.FALSE;
        try {
            updatedValue = (Boolean)marshall2Field.convert(newValue);
        } catch (MarshallException e) {
            Logger.log().error(e, "Unable to convert " + dataPersister.getType().getName() + " to " + getType().getName() + " using source data: " + newValue);
        }
        invoke(setSelection, new Object[]{updatedValue});
        this.original = newValue;
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IAwareWidget#clear()
     */
    public void clear() {
        setBooleanValue(false);
    }
    
    /**
     * Method addSelectionListener.  Adds a solection listener to the underlying
     * control.
     * 
     * @param selectionListener
     */
    private void addSelectionListener(SelectionListener selectionListener) {
        invoke(addSelectionListener, new Object[] {selectionListener});
    }

    /**
     * Method addSelectionListener.  Adds a solection listener to the underlying
     * control.
     * 
     * @param selectionListener
     */
    private void removeSelectionListener(SelectionListener selectionListener) {
        invoke(removeSelectionListener, new Object[] {selectionListener});
    }

    /**
     * Method hookEvents.  Hook up listeners to the control
     * @param control
     */
    protected void addListeners(Control control) {
        super.addListeners(control);
        addSelectionListener(selectionListener);
    }

    /**
     * Method removeListeners.  Unlisten to any events we were listening to.
     */
    public void removeListeners() {
        super.removeListeners();
        removeSelectionListener(selectionListener);
    }

    /*
     * SWT Event Handlers here.  These can be overridden in a descendent class.
     */
    
    protected SelectionListener selectionListener = new SelectionListener() {
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
         */
        public void widgetSelected(SelectionEvent e) {
            if (!dataPersister.isReadOnly())
                dataPersister.save(awareField, getValue());
            else
                Display.getDefault().asyncExec(new Runnable() {
                    public void run() {
                        Boolean newValue = getBooleanValue();
                        if (newValue != null)
                            setBooleanValue(!newValue.booleanValue());
                    }
                });
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



