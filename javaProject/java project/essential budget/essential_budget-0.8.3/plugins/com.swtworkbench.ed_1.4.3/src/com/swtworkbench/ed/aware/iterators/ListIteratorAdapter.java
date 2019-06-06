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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ListIterator;

import com.swtworkbench.ed.aware.iterator.IAwareIterator;

/**
 * Class ListIteratorAdapter.  
 * 
 * @author daveo
 */
public class ListIteratorAdapter implements IAwareIterator {

    // The current iterator that we're using
    private ListIterator i;
    
    // What we need to get a new ListIterator
    private Method iteratorMethod;
    private Object container;

    /**
     * Constructor ListIteratorAdapter.  Construct a ListIteratorAdapter object  
     * 
     * @param container The object to iterate over
     * @throws NoSuchMethodException If we can't find an iterator() method
     * @throws InvocationTargetException If the target was invalid
     * @throws IllegalAccessException If the method call is not allowed
     */
    public ListIteratorAdapter(Object container) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        iteratorMethod = container.getClass().getMethod("listIterator", new Class[] {});
        this.container = container;
        reset();
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.iterators.AwareIterator#addable()
     */
    public boolean addable() {
        return true;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.iterators.AwareIterator#settable()
     */
    public boolean settable() {
        return true;
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.iterator.IAwareIterator#reset()
     */
    public void reset() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        i = (ListIterator) iteratorMethod.invoke(container, new Object[] {});
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.iterators.AwareIterator#at(int)
     */
    public Object at(int position) {
        Object result = null;
        while (nextIndex() < position) {
            next();
        }
        while (nextIndex() > position) {
            previous();
        }
        result = next();
        return result;
    }

    /* (non-Javadoc)
     * @see java.util.ListIterator#add(java.lang.Object)
     */
    public void add(Object o) {
        i.add(o);
    }

    /* (non-Javadoc)
     * @see java.util.Iterator#hasNext()
     */
    public boolean hasNext() {
        return i.hasNext();
    }

    /* (non-Javadoc)
     * @see java.util.ListIterator#hasPrevious()
     */
    public boolean hasPrevious() {
        return i.hasPrevious();
    }

    /* (non-Javadoc)
     * @see java.util.Iterator#next()
     */
    public Object next() {
        return i.next();
    }

    /* (non-Javadoc)
     * @see java.util.ListIterator#nextIndex()
     */
    public int nextIndex() {
        return i.nextIndex();
    }

    /* (non-Javadoc)
     * @see java.util.ListIterator#previous()
     */
    public Object previous() {
        return i.previous();
    }

    /* (non-Javadoc)
     * @see java.util.ListIterator#previousIndex()
     */
    public int previousIndex() {
        return i.previousIndex();
    }

    /* (non-Javadoc)
     * @see java.util.Iterator#remove()
     */
    public void remove() {
        i.remove();
    }

    /* (non-Javadoc)
     * @see java.util.ListIterator#set(java.lang.Object)
     */
    public void set(Object o) {
        i.set(o);
    }

}
