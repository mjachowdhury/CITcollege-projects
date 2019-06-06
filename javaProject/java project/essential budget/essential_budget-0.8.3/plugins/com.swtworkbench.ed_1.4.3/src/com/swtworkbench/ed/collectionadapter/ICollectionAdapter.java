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
package com.swtworkbench.ed.collectionadapter;

import java.util.ListIterator;

/**
 * Class ICollectionAdapter.  An interface for a collection of things that
 * can be edited using an POJOObjectAdapter.
 * 
 * @author daveo
 */
public interface ICollectionAdapter {
    
    /**
     * Method setInput.  Sets the adapter's input.
     * @param input The input object.
     */
    public void setInput(Object input);
    
    /**
     * Method size.  Return the number of items in the collection
     * 
     * @return int the number of items in the collection
     */
    public int size();
    
    /**
     * Method listIterator.  Returns a ListIterator for the collection.
     * The ListIterator may be implemented by decorating some other iterator,
     * in which case, backward navigation may be extremely inefficient.
     * Alternatively, it may be implemented by copying the results into
     * a LinkedList and then returning a ListIterator onto the LinkedList.
     *   
     * @return ListIterator A ListIterator on the collection's contents
     */
    public ListIterator listIterator();
    
    /**
     * Method isInsertable.  Returns if the underlying collection allows
     * insertions.
     * 
     * @return true if the underlying collection allows insertions; false otherwise.
     */
    public boolean isInsertable();
    
    /**
     * Method isDeletable.  Returns if the underlying collection allows
     * deletions.
     * 
     * @return true if the underlying collection allows deletions; false otherwise.
     */
    public boolean isDeletable();
}
