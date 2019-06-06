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
package com.swtworkbench.ed.aware.adapters.pojo;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.swtworkbench.ed.aware.fields.AwareFieldFactory;
import com.swtworkbench.ed.aware.interfaces.IAwareWidget;
import com.swtworkbench.ed.aware.interfaces.IDataAwareField;
import com.swtworkbench.ed.aware.interfaces.IFieldAdapter;
import com.swtworkbench.ed.aware.interfaces.IValidateListener;
import com.swtworkbench.ed.aware.maskededit.internal.MaskMachine;
import com.swtworkbench.ed.aware.masterdetail.IMaster;
import com.swtworkbench.ed.aware.masterdetail.IMasterObjectChangeListener;
import com.swtworkbench.ed.aware.masterdetail.MasterObjectChangeEvent;
import com.swtworkbench.ed.aware.messages.MessageSource;
import com.swtworkbench.ed.aware.validator.IEditMaskValidator;
import com.swtworkbench.ed.aware.validator.IValidator;
import com.swtworkbench.ed.aware.validators.generic.ReadOnlyValidator;
import com.swtworkbench.ed.reflect.Property;

/**
 * Class POJOFieldAdapter.  An IDataAwareField/IFieldAdapter implementation 
 * for POJOs.
 * 
 * @author daveo
 */
public class POJOFieldAdapter implements IFieldAdapter, IDataAwareField {

    private String propertyName;
    private Object input=null;
    private LinkedList awareFields = new LinkedList();
    private IValidator validator = null;
    private MaskMachine editMaskMachine = null;
    private MessageSource messageSender = MessageSource.getDefault();
    
    /**
     * Constructor POJOFieldAdapter.  Create a POJOFieldAdapter on a property.
     * 
     * @param propertyName The name of the JavaBeans property to read/write
     */
    public POJOFieldAdapter(String propertyName) {
        this.propertyName = propertyName;
    }
    
    /**
     * Constructor POJOFieldAdapter.  Create a POJOFieldAdapter on a field and an
     * initial input object.
     * 
     * @param propertyName The name of the JavaBeans property to read/write
     * @param input The initial input object
     */
    public POJOFieldAdapter(String propertyName, Object input) {
        this.propertyName = propertyName;
        forceInput(input);
    }
    
    /**
     * Constructor POJOFieldAdapter.  Create a POJOFieldAdapter on a field, an
     * initial input object, and attach it to an initial control.
     * 
     * @param toAttach The control that will be attached to this property
     * @param propertyName The name of the JavaBeans property to read/write
     * @param input The initial input object
     */
    public POJOFieldAdapter(Control toAttach, String propertyName, Object input) {
        this.propertyName = propertyName;
        forceInput(input);
        attach(toAttach);
    }

    /**
	 * Constructor POJOFieldAdapter.  Create a POJOFieldAdapter on a field
	 * and a control.
	 * 
     * @param toAttach The control that will be attached to this property
	 * @param propertyName The name of the JavaBeans property to read/write
	 */
	public POJOFieldAdapter(Control toAttach, String propertyName) {
	    this.propertyName = propertyName;
        attach(toAttach);
	}
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IDataAwareField#getPropertyName()
     */
    public String getPropertyName() {
        return propertyName;
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IFieldAdapter#getType()
     */
    public Class getType() {
        if (property != null)
            return property.type();
        else
            return null;
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IFieldAdapter#dispose()
     */
    public void dispose() {
        if (property != null)
            property.removeChangeListener(propertyChangeListener);
    }

    protected Property property = null;

    //---- Manage any master-detail relationships in which this table is participating
    
    protected IMaster master = null;
    
    private IMasterObjectChangeListener masterChangeListener = new IMasterObjectChangeListener() {
        public void requestChange(MasterObjectChangeEvent e) {
            if (getInput() != null)
                e.doit = validateField();
        }
        public void masterObjectChanged(MasterObjectChangeEvent e) {
            try {
                Object newInput = master.getMasterObject(e.newSelection.y);
                setInput(newInput);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Unable to set the input even though all fields validated correctly.");
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

    // Row change event handling...
    
    private LinkedList rowChangeListeners = new LinkedList();
    
    /**
     * Method addRowChangeListener.  Since a POJOObjectAdapter does not have 
     * a notion of a "row" per se, this instead is just an "object change listener".
     * Consequently, the current row/column fields in the MasterObjectChangeEvent are 
     * ignored (-1 is passed), but the event still may be used to veto a row change
     * before it occurs.  If you want a true "row change" event, use either a
     * MROTable or a SliderNavigator object, both of which are designed
     * to natively edit collections of objects.
     * 
     * @see com.swtworkbench.ed.aware.interfaces.IMaster#addRowChangeListener(com.swtworkbench.ed.controls.table.IMasterObjectChangeListener)
     */
    public void addMasterObjectChangeListener(IMasterObjectChangeListener l) {
        rowChangeListeners.addLast(l);
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IMaster#removeRowChangeListener(com.swtworkbench.ed.controls.table.RowChangeListener)
     */
    public void removeMasterObjectChangeListener(IMasterObjectChangeListener l) {
        rowChangeListeners.remove(l);
    }

    protected boolean fireRowChangeRequest(int oldCol, int oldRow, int newCol, int newRow) {
        MasterObjectChangeEvent e = new MasterObjectChangeEvent(this, oldCol, oldRow, newCol, newRow);
        for (Iterator i = rowChangeListeners.iterator(); i.hasNext();) {
            IMasterObjectChangeListener element = (IMasterObjectChangeListener) i.next();
            element.requestChange(e);
        }
        return e.doit;
    }

    protected void fireRowChangeEvent(int oldCol, int oldRow, int newCol, int newRow) {
        MasterObjectChangeEvent e = new MasterObjectChangeEvent(this, oldCol, oldRow, newCol, newRow);
        for (Iterator i = rowChangeListeners.iterator(); i.hasNext();) {
            IMasterObjectChangeListener element = (IMasterObjectChangeListener) i.next();
            element.masterObjectChanged(e);
        }
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IMaster#getRowObject(int)
     */
    public Object getMasterObject(int row) {
        return input;
    }

    /**
     * Method getInput.  Returns the input object.
     * 
     * @return The input object
     */
    public Object getInput() {
        return input;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IDetail#setInput(java.lang.Object)
     */
    public void setInput(Object bean) throws UnableToSaveException {
        if (!validateField()) {
            throw new UnableToSaveException(getHelpMessage());
        }
        if (!fireRowChangeRequest(-1, -1, -1, -1)) {
            throw new UnableToSaveException("A detail object is unable to save");
        }
        forceInput(bean);
        fireRowChangeEvent(-1, -1, -1, -1);
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IDataAwareField#forceInput(java.lang.Object)
     */
    public void forceInput(Object input) {
        save();
        this.input = input;
        if (property != null) 
            property.removeChangeListener(propertyChangeListener);
        if (input != null) {
            property = new Property(input, propertyName);
            property.addChangeListener(propertyChangeListener);
        } else {
            property = null;
        }
        refreshControl();
    }
    
    private PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {
        /* (non-Javadoc)
         * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
         */
        public void propertyChange(PropertyChangeEvent evt) {
            refreshControl();
        }
    };

    /**
     * Method save.  Save any fields that haven't already been saved
     * @see com.swtworkbench.ed.aware.interfaces.IDataAwareField#save()
     */
    public void save() {
        Iterator i = awareFields.iterator();
        while (i.hasNext()) {
            IAwareWidget field = (IAwareWidget)i.next();
            if (field.isDirty()) {
                save(field, field.getValue());
                field.setDirty(false);
            }
        }        
    }
    
    /**
     * Method getDataMarshaller.  Get a data marshaller for the property's type.
     */
    private void getDataMarshallerAndValidator() {
        if (property != null && property.isValid()) {
            //Class fieldType = property.type();
            validator = property.validator();
            if (validator instanceof IEditMaskValidator) {
                IEditMaskValidator editMaskValidator = (IEditMaskValidator)validator;
                editMaskMachine = editMaskValidator.getEditMask().getMaskStateMachine();
            } else
                editMaskMachine = null;
        } else {
            validator = ReadOnlyValidator.getDefault();
        } 
    }

    /**
     * Method setFieldInputs.  Push the data from the property into the control.  
     */
    private void setFieldInputs() {
        if (property != null && property.isValid()) {
            Object data = property.get();
            Iterator i = awareFields.iterator();
        	while (i.hasNext()) {
    			IAwareWidget awareField = (IAwareWidget) i.next();
    			awareField.setValue(data);
    		}
        }
    }
    
    /**
     * Method attachControl.  Initialize a control from the field value.
     * 
     * @param control
     */
    private void attachControl(Control control) {
        IAwareWidget awareField = AwareFieldFactory.getFieldAdapter(this, control);
        awareFields.addLast(awareField);
        for (Iterator k = keyListeners.iterator(); k.hasNext();) {
            KeyListener l = (KeyListener) k.next();
            awareField.addKeyListener(l);
        }
    }
    
    /**
     * Method refreshControl.  Tries to push the data from the JavaBean into the
     * control for editing.
     */
    protected void refreshControl() {
        getDataMarshallerAndValidator();
        setFieldInputs();
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IDataAwareField#attach(org.eclipse.swt.widgets.Control)
     */
    public void attach(Control control) {
        attachControl(control);
        refreshControl();
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IDataAwareField#detatch(org.eclipse.swt.widgets.Control)
     */
    public void detatch(Control control) {
        Iterator i = awareFields.iterator();
        while (i.hasNext()) {
            IAwareWidget element = (IAwareWidget)i.next();
            if (element.getControl() == control) {
                element.removeListeners();
                i.remove();
                for (Iterator k = keyListeners.iterator(); k.hasNext();) {
                    KeyListener l = (KeyListener) k.next();
                    element.removeKeyListener(l);
                }
            }
        }
    }
    
    LinkedList keyListeners = new LinkedList();
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IDataAwareField#addKeyListener(org.eclipse.swt.events.KeyListener)
     */
    public void addKeyListener(KeyListener l) {
        for (Iterator i = awareFields.iterator(); i.hasNext();) {
            IAwareWidget awareWidget = (IAwareWidget) i.next();
            awareWidget.addKeyListener(l);
        }
        keyListeners.addLast(l);
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IDataAwareField#removeKeyListener(org.eclipse.swt.events.KeyListener)
     */
    public void removeKeyListener(KeyListener l) {
        for (Iterator i = awareFields.iterator(); i.hasNext();) {
            IAwareWidget awareWidget = (IAwareWidget) i.next();
            awareWidget.removeKeyListener(l);
        }
        keyListeners.addLast(l);
    }
    
    private IValidator oldValidator = null;
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IDataAwareField#setEnabled(boolean)
     */
    public void setEnabled(boolean enabled) {
        // If fields are being disabled, disable field validation, otherwise
        // restore field validation
        if (validator != null)
            oldValidator = validator;
        if (enabled) {
            validator = oldValidator;
        } else {
            validator = null;
        }
        
        for (Iterator i = awareFields.iterator(); i.hasNext();) {
            IAwareWidget awareWidget = (IAwareWidget) i.next();
            awareWidget.setEnabled(enabled);
        }
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IDataAwareField#isEnabled()
     */
    public boolean isEnabled() {
        for (Iterator i = awareFields.iterator(); i.hasNext();) {
            IAwareWidget awareWidget = (IAwareWidget) i.next();
            if (awareWidget.isEnabled())
                return true;
        }
        return false;
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IFieldAdapter#isReadOnly()
     */
    public boolean isReadOnly() {
        if (property == null)
            return true;
        return property.isReadOnly();
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IDataAwareField#clear()
     */
    public void clear() {
        for (Iterator i = awareFields.iterator(); i.hasNext();) {
            IAwareWidget awareWidget = (IAwareWidget) i.next();
            awareWidget.clear();
        }
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IDataAwareField#setMessageSender(com.swtworkbench.choreographer.aware.messages.MessageSource)
     */
    public void setMessageSender(MessageSource source) {
        messageSender = source;
    }
    
    protected boolean messageCleared = true;
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IFieldAdapter#sendMessage(java.lang.String)
     */
    public void sendMessage(String message) {
        messageSender.sendMessage(message);
        messageCleared = false;
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IFieldAdapter#clearMessage()
     */
    public void clearMessage() {
        if (!messageCleared) {
            messageSender.sendMessage("");
            messageCleared = true;
        }
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IFieldDataPersister#save(com.swtworkbench.choreographer.aware.AwareField, java.lang.String)
     */
    public void save(IAwareWidget sender, Object data) {
        property.set(data);
        clearMessage();

        // Make sure all other UI objects bound to the same field receive the
        // new value.
        Iterator i = awareFields.iterator();
        while (i.hasNext()) {
            IAwareWidget awareField = (IAwareWidget)i.next();
            if (awareField == sender) continue;
            awareField.setValue(data);
        }
    }
    
    /**
     * Method replaceRegion.  Utility method to replace some substring of a string
     * with another string.
     * 
     * @param source The string in which substring replacement surgery is required.
     * @param begin The beginning offset of the substring to replace.
     * @param end The ending offset of the substring to replace.
     * @param replacement The replacement string.
     * @return The doctored string.
     */
    private String replaceRegion(String source, int begin, int end, String replacement) {
        StringBuffer result = new StringBuffer(source.substring(0, begin));
        result.append(replacement);
        result.append(source.substring(end, source.length()));
        return result.toString();
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IFieldDataPersister#validate(java.lang.String, org.eclipse.swt.events.VerifyEvent)
     */
    public void validate(String currentValue, VerifyEvent verifyEvent) {
        if (validator != null) {
            String newValue = replaceRegion(currentValue, verifyEvent.start, verifyEvent.end, verifyEvent.text);
            verifyEvent.doit = validator.validatePartial(newValue);
            
            if (!verifyEvent.doit) {
                sendMessage(validator.getHelp());
            } else {
            	validateField(newValue);
            }
        }
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IFieldAdapter#validateField(java.lang.String)
     */
    public boolean validateField(String newValue) {
        if (validator != null) {
            boolean valid = validator.validateComplete(newValue);
            if (valid && validator instanceof IEditMaskValidator) {
                IEditMaskValidator maskValidator = (IEditMaskValidator) validator;
                valid = maskValidator.getEditMask().getMaskStateMachine().isValid();
            }
            if (valid) clearMessage();
            else sendMessage(validator.getHelp()); 
            fireValidateListeners(valid);
            return valid;
        }
        clearMessage();
        fireValidateListeners(true);
        return true;
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IFieldAdapter#getEditMask()
     */
    public MaskMachine getEditMask() {
        return editMaskMachine;
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IDataAwareField#validateField()
     */
    public boolean validateField() {
        if (validator != null) {
            Iterator i = awareFields.iterator();
            while (i.hasNext()) {
                IAwareWidget field = (IAwareWidget) i.next();
                boolean doit = false;
                if (editMaskMachine != null) {
                    doit = editMaskMachine.isValid();
                    if (doit)
                        doit = validator.validateComplete(field.getStringValue());
                } else {
                    doit = validator.validateComplete(field.getStringValue());
                }
                fireValidateListeners(doit);
                if (doit) 
                    clearMessage();
                else {
                    if (editMaskMachine != null) {
                        sendMessage(editMaskMachine.getHelpMessage(getPropertyName()));
                    } else {
                        sendMessage(validator.getHelp());
                    }
                    return false;
                }
            }
        }
        clearMessage();
        fireValidateListeners(true);
        return true;
    }
    
    private LinkedList validateListeners = new LinkedList();
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IDataAwareField#addValidateListener(com.swtworkbench.choreographer.aware.IValidateListener)
     */
    public void addValidateListener(IValidateListener listener) {
        validateListeners.add(listener);
    }
    
    private void fireValidateListeners(boolean isValid) {
        for (Iterator i = validateListeners.iterator(); i.hasNext();) {
            IValidateListener listener = (IValidateListener) i.next();
            listener.isValid(this, isValid);
        }
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IDataAwareField#removeValidateListener(com.swtworkbench.choreographer.aware.IValidateListener)
     */
    public void removeValidateListener(IValidateListener listener) {
        validateListeners.remove(listener);
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IFieldAdapter#getValidValues()
     */
    public Object[] getValidValues() {
        return property.validValues();
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IFieldAdapter#getChoices()
     */
    public String[] getChoices() {
        String[] choices = property.choices();
        if (choices == null) {
            Object[] validValues = property.validValues();
            choices = new String[validValues.length];
            for (int i = 0; i < validValues.length; i++) {
                if (validValues[i] == null)
                    choices[i] = "";
                else
                    choices[i] = validValues[i].toString();
            }
        }
        return choices;
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IDataAwareField#isDirty()
     */
    public boolean isDirty() {
       Iterator i = awareFields.iterator();
       while (i.hasNext()) {
           IAwareWidget field = (IAwareWidget) i.next();
           if (field.isDirty())
               return true;
       }
       return false;
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IDataAwareField#setDirty(boolean)
     */
    public void setDirty(boolean dirty) {
        Iterator i = awareFields.iterator();
        while (i.hasNext()) {
            IAwareWidget field = (IAwareWidget) i.next();
            field.setDirty(dirty);
        }
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IDataAwareField#getShell()
     */
    public Shell getShell() {
        Iterator i = awareFields.iterator();
        if (!i.hasNext()) return null;
        IAwareWidget field = (IAwareWidget)i.next();
        return field.getControl().getShell();
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IDataAwareField#getHelpMessage()
     */
    public String getHelpMessage() {
        if (validator != null) return validator.getHelp();
        return "";
    }
    

}
