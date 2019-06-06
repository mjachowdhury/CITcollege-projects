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
package com.swtworkbench.ed.aware.iterator;

import java.lang.reflect.InvocationTargetException;
import java.util.ListIterator;

/**
 * Class IAwareIterator.  A ListIterator that supports jumping "directly" to
 * a positional offset within the collection and returning that element. 
 * 
 * @author daveo
 */
public interface IAwareIterator extends ListIterator {
    /**
     * Method at.  Jump to and return the element at position.  nextIndex()
     * is left at position+1 after execution.
     * 
     * @param position The position to jump to
     * @return The object at that position.
     */
    public Object at(int position);
    
    /**
     * Method reset.  Resets the iterator to the 0th position by creating a
     * new iterator and discarding the old one.
     */
    public void reset() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException ;
    
    /**
     * Method settable.  Returns if the set() method is implemented.
     * 
     * @return true if this.set() is implemented; false otherwise.
     */
    public boolean settable();
    
    /**
     * Method addable.
     *   
     * @return true if this.add() is implemented; false otherwise.
     */
    public boolean addable();
}
