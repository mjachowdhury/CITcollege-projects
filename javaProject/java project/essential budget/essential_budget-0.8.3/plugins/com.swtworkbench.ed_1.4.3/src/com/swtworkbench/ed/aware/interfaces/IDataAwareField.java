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

import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.swtworkbench.ed.aware.masterdetail.*;
import com.swtworkbench.ed.aware.messages.MessageSource;

/**
 * Class IDataAwareField.  The public API implemented by data aware fields.<p>  
 * 
 * Implementers could use Reflection to store values to POJOs or store values in 
 * a database via JDBC.<p>
 * 
 * Typically, implementers will implement this interface and IFieldAdapter.
 * 
 * @author djo
 */
public interface IDataAwareField extends IMaster, IDetail {
    /**
     * Method getPropertyName.  Returns the name of the property/field that this field is
     * bound to in the database table or Java object.  Typically this property or field name
     * is passed to and set in the object's constructor.  This method makes the property
     * or field name a read-only property of the IDataAwareField client.
     * 
     * @return The property name string
     */
    public abstract String getPropertyName();
    /**(non-API)
     * Method forceInput.  Sets the input object for this control/field, discarding any
     * changes.  This method is not public API and is subject to change.
     * 
     * @param input
     */
    public abstract void forceInput(Object input);
    /**
     * Method attach.  Attach some SWT widget to the object property described by this
     * object.
     * 
     * @param widget  The SWT Control to attach to this field adapter.
     */
    public abstract void attach(Control control);
    /**
     * Method detatch.  Remove some SWT widget from being managed by this field
     * adapter.
     * 
     * @param widget The SWT Control to detatch from this field adapter.
     */
    public abstract void detatch(Control control);
    /**
     * Method setMessageSender.  Sets the object used to send validation error
     * messages.
     *   
     * @param source
     */
    public abstract void setMessageSender(MessageSource source);
    /**
     * Method addValidateListener.  Add an IValidateListener to this field.  
     * @param listener The IValidateListener to add.
     */
    public abstract void addValidateListener(IValidateListener listener);
    /**
     * Method removeValidateListener.  Remove an IValidateListener to this
     * field.
     * @param listener The IValidateListener to remove.
     */
    public abstract void removeValidateListener(IValidateListener listener);
    /**
     * Method validateField.  Validate the contents of the current field
     * if any SWT controls referenced by it are dirty.  If no SWT controls
     * are dirty, the value is assumed to be valid and no validation is 
     * performed.<p>
     * 
     * This method is inteded to be called by something managing the entire
     * object in preparation for replacing the inputs of all the objects's fields.
     * 
     * @return true if the field is valid; false otherwise
     */
    public abstract boolean validateField();
    /**
     * Method isDirty.  Returns true if any attached field is dirty.
     *   
     * @return true if any attached field is dirty.
     */
    public abstract boolean isDirty();
    /**
     * Method setDirty.  Sets or clears the underlying field's dirty flag.  
     * ie: if the field needs saving.  This might be done if the programmer
     * knows that the underlying field is a new (blank) value that is not
     * valid.
     * 
     * @param dirty True if the underlying field needs saving.
     */
    public abstract void setDirty(boolean dirty);
    /**
     * Method getHelpMessage.  Returns the current validator's help message  
     * @return String The current validator's help message.
     */
    public abstract String getHelpMessage();
    /**
     * Method addKeyListener.  Adds the specified key listener to all attached 
     * fields/controls.  If a field is removed from this POJOObjectAdapter, all
     * registered key listeners will be removed from that field.
     * 
     * @param l The key listener to add.
     */
    public abstract void addKeyListener(KeyListener l);
    /**
     * Method removeKeyListner.  Removed the specified key listener from all
     * attached fields/controls.
     * 
     * @param l The key listener to remove.
     */
    public abstract void removeKeyListener(KeyListener l);
    /**
     * Method setEnabled.  Sets the enabled property of all controls bound
     * to this field.
     * 
     * @param enabled the value to which to set the enabled property
     */
    public abstract void setEnabled(boolean enabled);
    /**
     * Method isEnabled.  Returns true if at least one control bound to this
     * field is enabled.
     * 
     * @return true if at least one control is enabled, false otherwise
     */
    public abstract boolean isEnabled();
    /**
     * Method clear.  Clears all controls bound to the current field.
     */
    public abstract void clear();
    /**
     * Method getShell.  Returns a shell containing one of the widgets
     * @return Shell a shell containing one of the managed widgets
     */
    public abstract Shell getShell();
    /**
     * Method save.  Tells the field to save itself.
     */
    public abstract void save();
}

