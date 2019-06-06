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

/**
 * Class IAwareWidget.  
 * 
 * @author daveo
 */
public interface IAwareWidget {
    /**
     * Method getControl.  Returns the underlying SWT control.  
     * @return Control the underlying SWT control
     */
    public abstract Control getControl();
    /**
     * Method getType. Returns the required type for getValue()/setValue().  
     * @return Class The required type.
     */
    public abstract Class getType();
    /**
     * Method getStringValue. Returns the value of the underlying control in 
     * the native format.  
     * @return Object the value in the underlying control.
     */
    public abstract Object getValue();
    /**
     * Method getStringValue.  Returns the value of the underlying control 
     * as a String
     * @return
     */
    public abstract String getStringValue();
    /**
     * Method setValue.  Sets the value of the underlying control.
     * @param newValue The new value String.
     */
    public abstract void setValue(Object newValue);
    /**
     * Method getOriginal.  Returns the original input (that is reset every
     * time setValue() is called).
     * 
     * @return The original input.
     */
    public abstract Object getOriginal();
    /**
     * Method isDirty.  
     * @return If the data in the control has changed
     */
    public abstract boolean isDirty();
    /**
     * Method setDirty.  Sets the underlying dirty flag.
     * @param dirty True if the underlying field needs saving, false otherwise.
     */
    public abstract void setDirty(boolean dirty);
    /**
     * Method removeListeners.  Unlisten to any events we were listening to.
     */
    public abstract void removeListeners();
    /**
     * Method addKeyListener.  Adds a key listener to the bound widget
     * @param l The listener to add
     */
    public abstract void addKeyListener(KeyListener l);
    /**
     * Method removeKeyListener.  Removes a key listener from the bound widget
     * @param l The listener to remove
     */
    public abstract void removeKeyListener(KeyListener l);
    /**
     * Method setEnabled.  Sets the "enabled" property of the bound widget
     * @param enabled Sets the 'enabled' property value
     */
    public abstract void setEnabled(boolean enabled);
    /**
     * Method isEnabled.  Returns the value of the "enabled" property
     * @return if the widget is enabled
     */
    public abstract boolean isEnabled();
    /**
     * Method clear.  Clears the value in the specified widget
     */
    public abstract void clear();
}

