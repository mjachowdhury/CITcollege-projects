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

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Shell;

import com.swtworkbench.ed.aware.interfaces.IDataAwareField;
import com.swtworkbench.ed.aware.interfaces.IRecordObjectAdapter;
import com.swtworkbench.ed.aware.interfaces.IRecordValidListener;
import com.swtworkbench.ed.aware.interfaces.IValidateListener;
import com.swtworkbench.ed.aware.masterdetail.IMaster;
import com.swtworkbench.ed.aware.masterdetail.IMasterObjectChangeListener;
import com.swtworkbench.ed.aware.masterdetail.MasterObjectChangeEvent;
import com.swtworkbench.ed.aware.messages.MessageSource;

/**
 * Class POJOObjectAdapter.  Manages all the data-aware fields attached to a particular
 * object. 
 * 
 * @author daveo
 */
public class POJOObjectAdapter implements IRecordObjectAdapter {

    protected LinkedList fields = new LinkedList();
    
    protected Object input = null;
    
    /**
     * Constructor POJOObjectAdapter.  
     * 
     * Default constructor.
     */
    public POJOObjectAdapter() {
    }

    /**
     * Constructor POJOObjectAdapter.  Construct an POJOObjectAdapter.
     * 
     * @param input The object this POJOObjectAdapter will manage.
     */
    public POJOObjectAdapter(Object input) throws UnableToSaveException {
        setInput(input);
    }
    
    /**
     * Method getInput.  
     * @return
     */
    public Object getInput() {
        return input;
    }


    //---- Manage any master-detail relationships in which this table is participating
    
    protected IMaster master = null;
    
    private IMasterObjectChangeListener masterChangeListener = new IMasterObjectChangeListener() {
        public void requestChange(MasterObjectChangeEvent e) {
            try {
                if (getInput() != null)
                    validateAllFields();
            } catch (UnableToSaveException e1) {
                e.doit = false;
            }
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
        return getInput();
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IRecordObjectAdapter#setInput(java.lang.Object)
     */
    public void setInput(Object object) throws UnableToSaveException {
        /* In theory, if a robot were manipulating the UI, there could
         * be a race condition between these lines if a field were changed
         * after validation and before the refresh were complete.
         * 
         * But since we're dealing with slow humans typing here, we'll
         * not worry about that... :-)
         */
        if (object != null && input != null) {
            saveDirtyFields();      // Save all pending changes if possible
            validateAllFields();    // Make sure everything else is a-okay
            if (!fireRowChangeRequest(-1, -1, -1, -1)) {
                throw new UnableToSaveException("Row change event was vetoed by a detail object");
            }
        }
        input = object;
        refreshFieldInputs();
        fireRowChangeEvent(-1, -1, -1, -1);
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IRecordObjectAdapter#validateAllFields()
     */
    public void validateAllFields() throws UnableToSaveException {
        Iterator i = fields.iterator();
        while (i.hasNext()) {
            IDataAwareField field = (IDataAwareField) i.next();
            if (!field.validateField())
                throw new UnableToSaveException(field.getHelpMessage());
        }
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IRecordObjectAdapter#saveDirtyFields()
     */
    public void saveDirtyFields() throws UnableToSaveException {
        for (Iterator i = fields.iterator(); i.hasNext();) {
            IDataAwareField field = (IDataAwareField)i.next();
            if (field.isDirty()) {
                if (!field.validateField())
                    throw new UnableToSaveException(field.getHelpMessage());
                field.save();
            }
        }
    }

    /**
     * Method refreshFieldInputs. 
     */
    private void refreshFieldInputs() {
        forEachField(new IFieldMethod() {
            public void run(IDataAwareField field) {
                field.forceInput(input);
            }
        });
    }
    
    /**
     * Method setMessageSender.  Sets the MessageSource that should dispatch
     * error messages to the UI for this POJO.
     * 
     * @param messageSource The MessageSource object to set on each field
     */
    public void setMessageSender(final MessageSource messageSource) {
        forEachField(new IFieldMethod() {
            public void run(IDataAwareField field) {
                field.setMessageSender(messageSource);
            }
        });
    }

    /**
     * Method add.  Adds a field to this POJOObjectAdapter.
     * @param adapter
     */
    public void add(IDataAwareField field) {
        fields.add(field);
        field.addValidateListener(validationListener);
        for (Iterator i = keyListeners.iterator(); i.hasNext();) {
            KeyListener l = (KeyListener)i.next();
            field.addKeyListener(l);
        }
    }

    /*
     * Track valid/invalid changes of dependant fields.  Be able to 
     * answer if all fields are valid.
     */
    private class ValidationListener implements IValidateListener {
		private HashSet invalidFields = new HashSet();

		/* (non-Javadoc)
		 * @see com.swtworkbench.choreographer.aware.pojo.IValidateListener#isValid(com.swtworkbench.choreographer.aware.pojo.IDataAwareField, boolean)
		 */
		public void isValid(IDataAwareField sender, boolean isValid) {
			boolean allAreValid = allAreValid();
			if (isValid) {
				invalidFields.remove(sender);
				if (invalidFields.size() > 0) {
                    IDataAwareField field = (IDataAwareField) invalidFields.iterator().next();
					field.validateField();
				}
			} else {
				invalidFields.add(sender);
			}
			if (allAreValid != allAreValid()) {
				fireRecordValidEvent();
			}
		}
		
		public boolean allAreValid() {
			return invalidFields.size() == 0;
		}
    }
    private ValidationListener validationListener = new ValidationListener();
    
    private LinkedList recordValidListeners = new LinkedList();
    
    /**
     * Method addRecordValidListener Adds an IRecordValidListener to the
     * set of listeners that are interested in record valid/invalid events.
     * 
     * @param listener The IRecordValidListener
     */
    public void addRecordValidListener(IRecordValidListener listener) {
    	recordValidListeners.add(listener);
    }
    
    /**
     * Method removeRecordValidListener Removes an IRecordValidListener
     * from the list of listeners that are interested in receiving 
     * record valid/invalid events.
     * 
     * @param listener The IRecordValidListener that was previously registered
     */
    public void removeRecordValidListener(IRecordValidListener listener) {
    	recordValidListeners.remove(listener);
    }
    
    protected void fireRecordValidEvent() {
    	boolean allAreValid = validationListener.allAreValid();
    	for (Iterator i = recordValidListeners.iterator(); i.hasNext();) {
			IRecordValidListener listener = (IRecordValidListener) i.next();
			listener.isValid(allAreValid);
		}
    }
    
    /**
     * Method remove.  Removes a field from this POJOObjectAdapter.
     * @param field
     */
    public void remove(IDataAwareField field) {
        fields.remove(field);
        for (Iterator i = keyListeners.iterator(); i.hasNext();) {
            KeyListener listener = (KeyListener) i.next();
            field.removeKeyListener(listener);
        }
    }


    // Iteration utilities //
    
    interface IFieldMethod {
        public void run(IDataAwareField field);
    }
    
    private void forEachField(IFieldMethod func) {
        Iterator i = fields.iterator();
        while (i.hasNext()) {
            IDataAwareField field = (IDataAwareField) i.next();
            func.run(field);
        }
    }
    
    protected LinkedList keyListeners = new LinkedList();
    
    protected Shell getShell() {
        Iterator i = fields.iterator();
        if (!i.hasNext()) return null;
        IDataAwareField field = (IDataAwareField)i.next();
        return field.getShell();
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IRecordObjectAdapter#addKeyListener(org.eclipse.swt.events.KeyListener)
     */
    public void addKeyListener(final KeyListener l) {
        forEachField(new IFieldMethod() {
            public void run(IDataAwareField field) {
                field.addKeyListener(l);
            }
        });
        Shell shell = getShell();
        if (shell != null) shell.addKeyListener(l);
        keyListeners.add(l);
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IRecordObjectAdapter#removeKeyListener(org.eclipse.swt.events.KeyListener)
     */
    public void removeKeyListener(final KeyListener l) {
        forEachField(new IFieldMethod() {
            public void run(IDataAwareField field) {
                field.removeKeyListener(l);
            }
        });
        Shell shell = getShell();
        if (shell != null) shell.addKeyListener(l);
        keyListeners.remove(l);
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IRecordObjectAdapter#setDirty(boolean)
     */
    public void setDirty(final boolean b) {
        forEachField(new IFieldMethod() {
            public void run(IDataAwareField field) {
                field.setDirty(b);
            }
        });
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IRecordObjectAdapter#isDirty()
     */
    public boolean isDirty() {
        for (Iterator i = fields.iterator(); i.hasNext();) {
            IDataAwareField field = (IDataAwareField)i.next();
            if (field.isDirty())
                return true;
        }
        return false;
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IRecordObjectAdapter#setEnabled(boolean)
     */
    public void setEnabled(final boolean enabled) {
        forEachField(new IFieldMethod() {
            public void run(IDataAwareField field) {
                field.setEnabled(enabled);
            }
        });
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IRecordObjectAdapter#isEnabled()
     */
    public boolean isEnabled() {
        for (Iterator i = fields.iterator(); i.hasNext();) {
            IDataAwareField element = (IDataAwareField) i.next();
            if (element.isEnabled())
                return true;
        }
        return false;
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IRecordObjectAdapter#clear()
     */
    public void clear() {
        for (Iterator i = fields.iterator(); i.hasNext();) {
            IDataAwareField field = (IDataAwareField) i.next();
            field.clear();
        }
    }


    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IRecordObjectAdapter#undoChanges()
     */
    public void undoChanges() {
        forEachField(new IFieldMethod() {
            public void run(IDataAwareField field) {
                field.setDirty(false);
            }
        });
    }

}


