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
package com.swtworkbench.ed.aware.interfaces;

import org.eclipse.swt.events.VerifyEvent;

import com.swtworkbench.ed.aware.maskededit.internal.MaskMachine;

/**
 * Class IFieldAdapter.  An interface for an object that can validate
 * and store a field's value that has been changed inside the user interface.
 * This is the private API that the IAwareWidget sees.<p>
 * 
 * Clients could use Reflection to store values to POJOs or store values in 
 * a database via JDBC.<p>
 * 
 * Typically, a client will implement this interface and IDataAwareField.
 * 
 * @author daveo
 */
public interface IFieldAdapter {
    /**
     * Method dispose.  Called when the underlying SWT control is disposed.
     */
    public void dispose();
    
    /**
     * Method getType.  Returns the type of the underlying field or property.
     * Data sent to the save() method will be expected to be already converted
     * to this type.
     * 
     * @return Class the type of the underlying field or property.
     */
    public Class getType();
    
    /**
     * Method save.  Tell the listener to save a modified value.  
     * @param sender The AwareField sending the event
     * @param data The new data string to save
     */
    public void save(IAwareWidget sender, Object data);
    
    /**
     * Method validate.  Tell the listener to validate a modified value. 
     * Typically this is called after every keystroke for Text fields.
     * 
     * @param data The new data string to validate
     * @param verifyEvent The underlying SWT VerifyEvent
     */
    public void validate(String currentValue, VerifyEvent verifyEvent);
    
    /**
     * Method validateField.  Validate the contents of the current field before
     * moving off of it.
     *   
     * @param currentValue The field's current value as a string
     * @return true if the field is valid; false otherwise
     */
    public boolean validateField(String newValue);
    
    /**
     * Method getEditMask.  Returns a state machine implementing the current
     * field's edit mask or null if there is no edit mask.
     * 
     * @return a MaskMachine (edit mask state machine) implementing the 
     * current field's edit mask or null if there is none.
     */
    public MaskMachine getEditMask();
    
    /**
     * Method getValidValues.  Returns the list of valid values allowed by the property.
     * 
     * @return Object[] The list of valid values storable into the property.
     */
    public Object[] getValidValues();

    /**
     * Method getChoices.  Returns an array of Strings corresponding to the Strings
     * that should be put into the list box or radio group.  This method first tries
     * to read an explicit choices array from the property itself.  If this array is not
     * defined, then it constructs one by iterating over the valid values array and
     * applying toString() to each element.
     * 
     * @return String[] An array of the valid strings.
     */
    public String[] getChoices();

    /**
     * Method sendMessage.  Use sendMessage() to send messages to the UI rather
     * than accessing the messageSender object directly.  When the message is 
     * no longer valid, call clearMessage() to clear it.
     *   
     * @param message The error message to send to the UI layer.
     */
    public void sendMessage(String message);
    
    /**
     * Method clearMessage.  Clears any error message that has been sent
     * by a validator.  Normally this would be called when moving off of
     * a field that has not been changed but where an error message may
     * have been displayed.
     */
    public void clearMessage();

    /**
     * Method getPropertyName.  Returns the name of the underlying field.
     * 
     * @return The name of the underlying field
     */
    public String getPropertyName();

    /**
     * Method isReadOnly.  Returns if the underlying property value can be changed
     * @return true if the underlying property can be changed; false otherwise
     */
    public boolean isReadOnly();
}
