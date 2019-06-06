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
package com.swtworkbench.ed.aware.navigators;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.swt.widgets.Control;

import com.swtworkbench.ed.aware.adapters.pojo.UnableToSaveException;
import com.swtworkbench.ed.aware.iterator.IAwareIterator;
import com.swtworkbench.ed.aware.iterator.IteratorAdapterFactory;
import com.swtworkbench.ed.aware.model.IModelChangeListener;
import com.swtworkbench.ed.aware.model.IObjectFactory;
import com.swtworkbench.ed.aware.model.Model;
import com.swtworkbench.ed.reflect.Property;

/**
 * Class AbstractNavigator.  A superclass for Navigator objects.  Navigator objects
 * allow editing of collections of objects in a record-at-a-time or multi-record
 * object way.
 * 
 * @author daveo
 */
public abstract class AbstractNavigator {
    protected Object oldContainer=null;
    protected Object container=null;
    
    protected IAwareIterator iterator;
    
    protected Method getSize;
    
    protected IObjectFactory objectFactory = null;
    private IModelChangeListener propertyModelChangeListener = null;
    protected Property property = null;
    

    public void setInput(Object bean) throws UnableToSaveException 
    {
        if (property == null)
            throw new IllegalArgumentException("Must provide a property name the first time edit() is called");
            
        if (objectFactory != null) {
            Model.getDefault().unregisterObjectFactory(property.getInput());
        }
        if (propertyModelChangeListener != null) {
            Model.getDefault().removeModelChangeListener(property.getInput(), propertyModelChangeListener);
        }
        property = new Property(bean, property.getPropertyName());
        objectFactory = property.getObjectFactory();
        if (objectFactory != null) {
            Model.getDefault().registerObjectFactory(property.get(), objectFactory);
        }
        propertyModelChangeListener = property.getChangeListener();
        if (propertyModelChangeListener != null) {
            Model.getDefault().addModelChangeListener(property.get(), propertyModelChangeListener);
        }
        try {
            setCollection(property.get());
        } catch (SecurityException e) {
            throw new UnableToSaveException(e);
        } catch (NoSuchMethodException e) {
            throw new UnableToSaveException(e);
        } catch (IllegalAccessException e) {
            throw new UnableToSaveException(e);
        } catch (InvocationTargetException e) {
            throw new UnableToSaveException(e);
        }
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.navigators.ICollectionEditor#edit(java.lang.Object, java.lang.String)
     */
    public void edit(Object bean, String propertyName)
        throws
            SecurityException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            UnableToSaveException 
    {
        if (objectFactory != null) {
            Model.getDefault().unregisterObjectFactory(property.getInput());
        }
        if (propertyModelChangeListener != null) {
            Model.getDefault().removeModelChangeListener(property.getInput(), propertyModelChangeListener);
        }
        property = new Property(bean, propertyName);
        objectFactory = property.getObjectFactory();
        if (objectFactory != null) {
            Model.getDefault().registerObjectFactory(property.get(), objectFactory);
        }
        propertyModelChangeListener = property.getChangeListener();
        if (propertyModelChangeListener != null) {
            Model.getDefault().addModelChangeListener(property.get(), propertyModelChangeListener);
        }
        setCollection(property.get());
    }
    
    /**
     * Method setCollection.  Attach a container to a record object.<p>
     * 
     * Clients should override this method to provide their own widget 
     * initialization; they should be sure to call 
     * super.attach(container)<p>
     * 
     * @param container The container to attach
     * 
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    protected void setCollection(final Object container) throws SecurityException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, UnableToSaveException {
        this.oldContainer = this.container;
        this.container = container;
        this.iterator = IteratorAdapterFactory.iterator(container);
        getSize = container.getClass().getMethod("size", new Class[] {});
    }
    
    /**
     * Method getSize.  Returns the size of the encapsulated collection.
     * @return int The size of the encapsulated collection.
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    protected int getSize() throws SecurityException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return ((Integer)getSize.invoke(container, new Object[] {})).intValue();
    }

    /**
     * Method getControl.  Return the underlying SWT control.
     * 
     * @return Control the underlying SWT control
     */
    public abstract Control getControl();
}

