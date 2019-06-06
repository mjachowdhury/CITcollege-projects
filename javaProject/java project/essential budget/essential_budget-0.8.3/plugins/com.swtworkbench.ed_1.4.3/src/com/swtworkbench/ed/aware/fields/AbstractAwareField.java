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

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.internal.SWTEventObject;
import org.eclipse.swt.widgets.Control;

import com.swtworkbench.ed.aware.interfaces.IAwareWidget;
import com.swtworkbench.ed.aware.interfaces.IFieldAdapter;
import com.swtworkbench.ed.aware.marshaller.DataMarshallerRegistry;
import com.swtworkbench.ed.aware.marshaller.IDataMarshaller;
import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class AbstractAwareField.  
 * 
 * @author daveo
 */
public abstract class AbstractAwareField implements IAwareWidget {

    protected Object original = null;
    protected final Control control;
    private boolean dirty = false;
    protected final IFieldAdapter dataPersister;
    protected IDataMarshaller marshall2Field = null;
    protected IDataMarshaller marshall2Property = null;
    protected final AbstractAwareField awareField = this;
    private Method addDisposeListener = null;
    private Method removeDisposeListener = null;

    /**
     * Constructor AbstractAwareField.  
     * 
     * @param dataPersister
     * @param control
     */
    public AbstractAwareField(IFieldAdapter dataPersister, Control control) {
        this.dataPersister = dataPersister;
        this.control = control;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IAwareWidget#getType()
     */
    public abstract Class getType();

    /**
     * Method getMethod.  Returns the Method object for a specific method on the
     * underlying control or null if it cannot be found for any reason.  
     * 
     * @param name The method's name (as a String)
     * @param args The method's argument list types (as a Class[])
     * @return Method the found Method or null if not found.
     */
    protected Method getMethod(String name, Class[] args) {
        Class controlClass = getControl().getClass();
        Method result = null;
        try {
            result = controlClass.getMethod(name, args);
        } catch (Exception e) {}
        return result;
    }

    /**
     * Method invoke. Invoke some method on the underlying control with the
     * specified arguments.  Returns null if any type of failure occurred.
     *   
     * @param method The Method to invoke
     * @param args The arguments to pass to the method (Object[])
     * @return The result Object
     */
    protected Object invoke(Method method, Object[] args) {
        Object result = null;
        try {
            result = method.invoke(getControl(), args);
        } catch (Exception e) {}
        return result;
    }

    /**
     * Method invoke. Invoke some method on the underlying control with the
     * specified arguments.  Logs exception and throws RuntimeException if 
     * any type of failure occurred.
     *   
     * @param method The Method to invoke
     * @param args The arguments to pass to the method (Object[])
     * @return The result Object
     */
    protected Object invokel(Method method, Object[] args) {
        Object result = null;
        try {
            result = method.invoke(getControl(), args);
        } catch (Exception e) {
            Logger.log().error(e, "Unable to invoke " + method.getName());
            throw new RuntimeException();
        }
        return result;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IAwareWidget#getControl()
     */
    public Control getControl() {
        return control;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IAwareWidget#getOriginal()
     */
    public Object getOriginal() {
        return original;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IAwareWidget#isDirty()
     */
    public boolean isDirty() {
        return dirty;
    }

    /**
     * Method setDirty.  
     * @param b
     */
    public void setDirty(boolean b) {
        dirty = b;
    }
    
    /**
     * Method validateField.  
     * Make sure the field contains a valid value and issue the appropriate events
     * if it does not.
     */
    public abstract void validateField();

    /**
     * Method getMethods.  Get Method objects for each of the event hooks.  
     */
    protected void getMethods() {
        addDisposeListener = getMethod("addDisposeListener", new Class[] {DisposeListener.class});
        removeDisposeListener = getMethod("removeDisposeListener", new Class[] {DisposeListener.class});
    }

    /**
    * Method addListeners.  Hook up listeners to the control
    * @param control
    */
   protected void addListeners(Control control) {
       addDisposeListener(disposeListener);
   }

   /**
     * Method addDisposeListener.  Add a dispose listener to the underlying control.
     * @param disposeListener
     */
    private void addDisposeListener(DisposeListener disposeListener) {
        invoke(addDisposeListener, new Object[] {disposeListener});
    }

    /**
     * Method removeDisposeListener.  Remove a dispose listener from the underlying
     * control.
     * @param disposeListener
     */
    private void removeDisposeListener(DisposeListener disposeListener) {
        invoke(removeDisposeListener, new Object[] {disposeListener});
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IAwareWidget#removeListeners()
     */
    public void removeListeners() {
        removeDisposeListener(disposeListener);
    }

    /**
     * Method ensureMarshallers.  Make sure we have marshaller objects to convert
     * data for us.
     */
    protected void ensureMarshallers() {
        if (marshall2Field == null)
            marshall2Field = DataMarshallerRegistry.getMarshaller(getType(), dataPersister.getType());
        if (marshall2Property == null)
            marshall2Property = DataMarshallerRegistry.getMarshaller(dataPersister.getType(), getType());
        if (marshall2Field == null || marshall2Property == null)
            Logger.log().error(new Exception(), "Unable to retrieve marshaller; my type == " + getType().getName() + "; other type == " + dataPersister.getType().getName());
    }
    
    /**
     * Method printEvent. Event printing code here can be uncommented to debug
     * event handling problems.  Or it can be commented out so that the 
     * optimizer can remove it from all events.
     *
     * @param e
     */
    protected void printEvent(SWTEventObject e) {
        Logger.log().debug(getClass(), e.toString());
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IAwareWidget#addKeyListener(org.eclipse.swt.events.KeyListener)
     */
    public void addKeyListener(KeyListener l) {
        control.addKeyListener(l);
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IAwareWidget#removeKeyListener(org.eclipse.swt.events.KeyListener)
     */
    public void removeKeyListener(KeyListener l) {
        control.removeKeyListener(l);
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IAwareWidget#setEnabled(boolean)
     */
    public void setEnabled(boolean enabled) {
        control.setEnabled(enabled);
        if (!enabled)
            dirty=false;
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IAwareWidget#isEnabled()
     */
    public boolean isEnabled() {
        return control.isEnabled();
    }

    protected DisposeListener disposeListener = new DisposeListener() {
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
         */
        public void widgetDisposed(DisposeEvent e) {
            dataPersister.dispose();
            removeListeners();
        }
    };

}
