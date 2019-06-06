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
package com.swtworkbench.ed.aware.iterators;

import java.lang.reflect.Method;
import java.util.Iterator;

import com.swtworkbench.ed.aware.iterator.IAwareIterator;
import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class IteratorAdapter.  Adapts a java.util.Iterator to a ListIterator interface, albeit
 * much less efficiently than if the object could provide a ListIterator directly.<p>
 * 
 * Also, does not implement set() or add() in the ListIterator interface.
 * 
 * @author djo
 */
public class IteratorAdapter implements IAwareIterator {
    
    // The container we're iterating over
    private final Object container;
    
    // The iterator() method we can use to get a new Iterator
    private final Method iteratorMethod;
    
    // The current iterator that we're wrapping
    private Iterator i;
    
    // Our position within the collection
    private int position = 0;
    
    // The last direction we moved
    private boolean lastMovementWasNext = true;
    
    /**
     * Constructor IteratorAdapter.  Construct an IteratorAdapter object  
     * 
     * @param container The object to iterate over
     * @throws NoSuchMethodException If we can't find an iterator() method
     */
    public IteratorAdapter(Object container) throws NoSuchMethodException {
        this.iteratorMethod = container.getClass().getMethod("iterator", new Class[] {});
        this.container = container;
        reset();
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.iterator.IAwareIterator#reset()
     */
    public void reset() {
        try {
            i = (Iterator) iteratorMethod.invoke(container, new Object[] {});
        } catch (Exception e) {
            Logger.log().error(e, "Unable to get a new iterator");
        }
        position = 0;
    }

    /* (non-Javadoc)
     * @see java.util.Iterator#hasNext()
     */
    public boolean hasNext() {
        return i.hasNext();
    }

    /* (non-Javadoc)
     * @see java.util.Iterator#next()
     */
    public Object next() {
        lastMovementWasNext = true;
        ++position;
        return i.next();
    }

    /* (non-Javadoc)
     * @see java.util.ListIterator#hasPrevious()
     */
    public boolean hasPrevious() {
        return position > 0;
    }

    /* (non-Javadoc)
     * @see java.util.ListIterator#previous()
     */
    public Object previous() {
        lastMovementWasNext = false;
        int currentPos = position;
        reset();
        Object result = null;
        for (int i=0; i < currentPos; ++i) {
            result = next();
        }
        reset();
        for (int i=0; i < currentPos - 1; ++i) {
            next();
        }
        return result;
    }

    /* (non-Javadoc)
     * @see java.util.ListIterator#nextIndex()
     */
    public int nextIndex() {
        return position;
    }

    /* (non-Javadoc)
     * @see java.util.ListIterator#previousIndex()
     */
    public int previousIndex() {
        return position - 1;
    }

    /* (non-Javadoc)
     * @see java.util.Iterator#remove()
     */
    public void remove() {
        if (lastMovementWasNext) {
            i.remove();
        } else {
            i.next();
            i.remove();
        }
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.iterators.AwareIterator#at(int)
     */
    public Object at(int position) {
        Object result = null;
        if (nextIndex() < position) {
            while (nextIndex() <= position) result = next();
        } else {
            reset();
            for (int i = 0; i <= position; ++i) result = next();
        }
        return result;
    }

    /* (non-Javadoc)
     * @see java.util.ListIterator#set(java.lang.Object)
     */
    public void set(Object arg0) {
        // Not implemented
    }

    /* (non-Javadoc)
     * @see java.util.ListIterator#add(java.lang.Object)
     */
    public void add(Object arg0) {
        // Not implemented
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.iterators.AwareIterator#settable()
     */
    public boolean settable() {
        return false;
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.iterators.AwareIterator#addable()
     */
    public boolean addable() {
        return false;
    }
}


