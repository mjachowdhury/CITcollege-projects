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

import java.io.Serializable;


/**
 * IModelChangeListener.  An interface for listening to changes in the
 * items stored in a collection.  The events in this interface are fired
 * by com.swtworkbench.ed.aware.model.Model.
 * 
 * @author daveo
 */
public interface IModelChangeListener extends Serializable {
    /**
     * Method objectCreated.  An event that is fired when a new object is
     * created in a managed container.
     * 
     * @param container The container in which the new object lives
     * @param newObject The NewObject
     */
    public void objectCreated(Object container, NewObject newObject);
    
    /**
     * Method objectDeleted.  An event that is fired when an object is removed
     * from a managed container.
     * 
     * @param container The managed container
     * @param position The 0-based position of the removed element in the collection
     * @param removed The removed object or null if this cannot be determined.
     * Clients must be prepared to handle either case.
     */
    public void objectDeleted(Object container, int position, Object removed);

    /**
     * Method checkObjectDeletion.  Asks the client if a proposed deletion is 
     * permitted.  This is typically used to show a confiramation dialog in 
     * the user interface, but may be used for any purpose.  If any client
     * vetos the deletion, the deletion will not occur.
     * 
     * @param container The managed container
     * @param selection The 0-based position of the element to be removed in the collection
     * @param toDelete The element to be removed
     * @return true if it is okay to remove the element, false otherwise
     */
    public boolean checkObjectDeletion(Object container, int selection, Object toDelete);
}
