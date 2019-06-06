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
package com.swtworkbench.ed.aware.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;


/**
 * Class Model.  Creates new objects; allows objects to observe the
 * creation of new objects.
 * 
 * @author daveo
 */
public class Model {
    
    //----------------------------------------------------------------------
    
    private static Model singleton = null;
    
    /**
     * Method getDefault.  Returns the default Model instance.  If 
     * a ModelRegistry does not exist, one is created.
     * 
     * @return The default Model instance
     */
    public static Model getDefault() {
        if (singleton == null) {
            singleton = new Model();
        }
        return singleton;
    }
    
    //----------------------------------------------------------------------
    
    private Map registry = new ListMap();
    
    class RegistryNode {
        int referenceCount = 1;
        IObjectFactory factory;
        
        public RegistryNode(IObjectFactory factory) {
            this.factory = factory;
        }
    }
    
    private IObjectFactory get(Object collection) {
        RegistryNode node = (RegistryNode) registry.get(collection);
        if (node == null) 
            return null;
        return node.factory;
    }
    
    private RegistryNode getNode(Object collection) {
        return (RegistryNode) registry.get(collection);
    }
    
    /**
     * Method registerObjectFactory.  Register an object that can add new
     * objects to a collection on demand.
     * 
     * @param collection the collection to which the factory will add
     * @param factory The IObjectFactory
     */
    public void registerObjectFactory(Object collection, IObjectFactory factory) {
        RegistryNode node = getNode(collection);
        if (node == null) {
            node = new RegistryNode(factory);
            registry.put(collection, node);
        } else {
            ++node.referenceCount;
        }
    }
    
    /**
     * Method unregisterObjectFactory.  Unregister any object factory registered
     * for the specified collection.
     * 
     * @param collection The collection to unregister.
     */
    public void unregisterObjectFactory(Object collection) {
        RegistryNode node = getNode(collection);
        if (node != null) {
            --node.referenceCount;
            if (node.referenceCount < 1) {
                registry.remove(collection);
            }
        }
    }
    
    private Map modelListeners = new ListMap();
    
    private void readState(ObjectInputStream is) throws IOException, ClassNotFoundException {
        modelListeners = (Map) is.readObject();
    }
    
    private void writeState(ObjectOutputStream os) throws IOException {
        os.writeObject(modelListeners);
    }
    
    /**
     * Method addModelChangeListener.  Make an object listen to changes on
     * the specified container.
     * 
     * @param container the container on which to listen for changes
     * @param l The listener
     */
    public void addModelChangeListener(Object container, IModelChangeListener l) {
        LinkedList containerListeners = (LinkedList) modelListeners.get(container);
        if (containerListeners == null) {
            containerListeners = new LinkedList();
            modelListeners.put(container, containerListeners);
        }
        containerListeners.add(l);
    }
    
    /**
     * Method removeModelChangeListener.  Removes the specified IModelChangeListener
     * from the specified container.
     * 
     * @param container The specified container
     * @param l The IModelChangeListener to remove
     */
    public void removeModelChangeListener(Object container, IModelChangeListener l) {
        LinkedList containerListeners = (LinkedList) modelListeners.get(container);
        if (containerListeners == null) return;
        containerListeners.remove(l);
        if (containerListeners.isEmpty()) {
            modelListeners.remove(container);
        }
    }
    
    /**
     * Method fireObjectCreatedEvent.  Tell the world that a new object was born.
     * 
     * @param container The container into which the new object was placed
     * @param newObject The new object
     */
    public void fireObjectCreatedEvent(Object container, NewObject newObject) {
        LinkedList containerListeners = (LinkedList) modelListeners.get(container);
        if (containerListeners == null) return;
        for (Iterator i = containerListeners.iterator(); i.hasNext();) {
            IModelChangeListener listener = (IModelChangeListener) i.next();
            listener.objectCreated(container, newObject);
        }
    }
    
    /**
     * Method fireVetoableObjectDeletionEvent.  Fires an event requesting
     * permission to remove an object from a container.  If any listener
     * returns false, the entire object deletion is cancelled.
     * 
     * @param container The container from which the object should be removed
     * @param selection The position of the object in the container
     * @param toDelete The object that is to be removed
     * @return true if the object can be deleted; false otherwise
     */
    public boolean fireVetoableObjectDeletionEvent(Object container, int selection, Object toDelete) {
        LinkedList containerListeners = (LinkedList) modelListeners.get(container);
        if (containerListeners == null) return true;
        for (Iterator i = containerListeners.iterator(); i.hasNext();) {
            IModelChangeListener listener = (IModelChangeListener) i.next();
            if (!listener.checkObjectDeletion(container, selection, toDelete))
                return false;
        }
        return true;
    }
    
    /**
     * Method fireObjectDeletedEvent.  Fires an event indicating that 
     * an object has already been removed from a container.
     * 
     * @param container The container from which the object was removed
     * @param position The old position of the object in the container
     * @param removed The removed object
     */
    public void fireObjectDeletedEvent(Object container, int position, Object removed) {
        LinkedList containerListeners = (LinkedList) modelListeners.get(container);
        if (containerListeners == null) return;
        for (Iterator i = containerListeners.iterator(); i.hasNext();) {
            IModelChangeListener listener = (IModelChangeListener) i.next();
            listener.objectDeleted(container, position, removed);
        }
    }

    /**
     * Method create.  Create a NewObject in the specified collection.  Returns
     * null if a factory is not registered for that collection.
     * 
     * @param collection the specified collection
     * @return The NewObject or null if a NewObject cannot be created
     */
    public NewObject create(Object collection) {
        IObjectFactory factory = get(collection);
        if (factory == null) return null;
        NewObject newObject = factory.getNewObject(collection);
        fireObjectCreatedEvent(collection, newObject);
        return newObject;
    }

    /**
     * Method read.  Read the model's state from the specified input stream.<p>
     * 
     * This only saves/restores the model change listener list.  But be sure this 
     * is what you want to do!
     * 
     * @param is
     */
    public static void read(ObjectInputStream is) throws IOException, ClassNotFoundException {
        getDefault().readState(is);
    }

    /**
     * Method write.  Write the model's state to the specified output stream.<p>
     * 
     * This only saves/restores the model change listener list.  But be sure this 
     * is what you want to do!
     * 
     * @param os
     */
    public static void write(ObjectOutputStream os) throws IOException {
        getDefault().writeState(os);
    }
    

}


