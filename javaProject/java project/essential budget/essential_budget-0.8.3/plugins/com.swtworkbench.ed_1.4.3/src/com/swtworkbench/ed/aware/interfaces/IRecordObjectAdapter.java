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

import com.swtworkbench.ed.aware.adapters.pojo.UnableToSaveException;
import com.swtworkbench.ed.aware.masterdetail.*;
import com.swtworkbench.ed.aware.messages.MessageSource;

/**
 * An IRecordObjectAdapter manages all the data-aware fields attached to a particular
 * object or record in a result set.
 *  
 * @author djo
 */
public interface IRecordObjectAdapter extends IMaster, IDetail {
	/**
	 * Method getInput.  
	 * @return the input object
	 */
	public abstract Object getInput();
    /**
     * Method validateAllFields.  Make sure that every modified field in the
     * object can be safely saved.
     * 
     * @throws UnableToSaveException if they cannot.  The message is the error 
     * message of the field that cannot be saved.
     */
    public void validateAllFields() throws UnableToSaveException;
	/**
	 * Method setMessageSender.  Sets the MessageSource that should dispatch
	 * error messages to the UI for this POJO.
	 * 
	 * @param messageSource The MessageSource object to set on each field
	 */
	public abstract void setMessageSender(final MessageSource messageSource);
	/**
	 * Method add.  Adds a field to this POJOObjectAdapter.
	 * @param adapter
	 */
	public abstract void add(IDataAwareField field);
	/**
	 * Method addRecordValidListener Adds an IRecordValidListener to the
	 * set of listeners that are interested in record valid/invalid events.
	 * 
	 * @param listener The IRecordValidListener
	 */
	public abstract void addRecordValidListener(IRecordValidListener listener);
	/**
	 * Method removeRecordValidListener Removes an IRecordValidListener
	 * from the list of listeners that are interested in receiving 
	 * record valid/invalid events.
	 * 
	 * @param listener The IRecordValidListener that was previously registered
	 */
	public abstract void removeRecordValidListener(IRecordValidListener listener);
	/**
	 * Method remove.  Removes a field from this POJOObjectAdapter.
	 * @param field
	 */
	public abstract void remove(IDataAwareField field);
    /**
     * Method addKeyListener.  Adds the specified key listener to all attached 
     * fields/controls.  If a field is removed from this POJOObjectAdapter, all
     * registered key listeners will be removed from that field.
     * 
     * @param l The key listener to add.
     */
    public abstract void addKeyListener(KeyListener l);
    /**
     * Method removeKeyListener.  Removed the specified key listener from all
     * attached fields/controls.
     * 
     * @param l The key listener to remove.
     */
    public abstract void removeKeyListener(KeyListener l);
    /**
     * Method undoChanges.  Performs an undo on any pending changes
     */
    public abstract void undoChanges();
    /**
     * Method setEnabled.  Sets all controls bound to this record object
     * to be either enabled or disabled.
     * 
     * @param enabled true if controls should be enabled; false otherwise.
     */
    public abstract void setEnabled(boolean enabled);
    /**
     * Method isEnabled.  Returns if at least one control bound to this
     * record object is enabled.
     * 
     * @return true if at least one control is enabled; false otherwise
     */
    public abstract boolean isEnabled();
    /**
     * Method clear.  Clear the value of all fields attached to this
     * RecordObject
     */
    public abstract void clear();
    /**
     * Method setDirty.  Sets or clears all fields' dirty flags.
     * 
     * @param b true if all fields should be considered dirty (needing saving); false otherwise
     */
    public abstract void setDirty(boolean b);
    /**
     * Method isDirty.  Returns true if any field is dirty (needing saving) 
     * 
     * @return true if any field is dirty (needing saving); false otherwise
     */
    public abstract boolean isDirty();
    /**
     * Method saveDirtyFields.  Attempts to save any fields that have changed.
     * @throws UnableToSaveException if a field is still invalid
     */
    public abstract void saveDirtyFields() throws UnableToSaveException;
}

