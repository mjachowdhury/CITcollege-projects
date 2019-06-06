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
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 * Class ListSet.  
 * 
 * @author daveo
 */
public class ListSet implements Set, Serializable {
    
    private LinkedList contents = new LinkedList();

    /* (non-Javadoc)
     * @see java.util.Collection#size()
     */
    public int size() {
        return contents.size();
    }

    /* (non-Javadoc)
     * @see java.util.Collection#clear()
     */
    public void clear() {
        contents = new LinkedList();
    }

    /* (non-Javadoc)
     * @see java.util.Collection#isEmpty()
     */
    public boolean isEmpty() {
        return contents.isEmpty();
    }

    /* (non-Javadoc)
     * @see java.util.Collection#toArray()
     */
    public Object[] toArray() {
        return contents.toArray();
    }

    /* (non-Javadoc)
     * @see java.util.Collection#add(java.lang.Object)
     */
    public boolean add(Object arg0) {
        if (!contains(arg0)) {
            contents.addLast(arg0);
            return true;
        }
        return false;
    }

    /* (non-Javadoc)
     * @see java.util.Collection#contains(java.lang.Object)
     */
    public boolean contains(Object arg0) {
        return isInCollection(contents, arg0);
    }

    /* (non-Javadoc)
     * @see java.util.Collection#remove(java.lang.Object)
     */
    public boolean remove(Object arg0) {
        return contents.remove(arg0);
    }

    /* (non-Javadoc)
     * @see java.util.Collection#addAll(java.util.Collection)
     */
    public boolean addAll(Collection arg0) {
        boolean result = true;
        for (Iterator i = arg0.iterator(); i.hasNext();) {
            Object element = (Object) i.next();
            if (!add(element))
                result = false;
        }
        return result;
    }

    /* (non-Javadoc)
     * @see java.util.Collection#containsAll(java.util.Collection)
     */
    public boolean containsAll(Collection arg0) {
        for (Iterator i = arg0.iterator(); i.hasNext();) {
            Object element = (Object) i.next();
            if (!contains(element))
                return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.util.Collection#removeAll(java.util.Collection)
     */
    public boolean removeAll(Collection arg0) {
        boolean result = true;
        for (Iterator i = arg0.iterator(); i.hasNext();) {
            Object element = (Object) i.next();
            if (!remove(element))
                result = false;
        }
        return result;
    }
    
    private boolean isInCollection(Collection c, Object o) {
        for (Iterator i = c.iterator(); i.hasNext();) {
            Object element = (Object) i.next();
            if (element == o) return true;
        }
        return false;
    }

    /* (non-Javadoc)
     * @see java.util.Collection#retainAll(java.util.Collection)
     */
    public boolean retainAll(Collection arg0) {
        for (Iterator i = contents.iterator(); i.hasNext();) {
            Object element = (Object) i.next();
            if (!isInCollection(arg0, element))
                i.remove();
        }
        for (Iterator i = arg0.iterator(); i.hasNext();) {
            Object element = (Object) i.next();
            if (!isInCollection(contents, element))
                return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.util.Collection#iterator()
     */
    public Iterator iterator() {
        return contents.listIterator();
    }

    /* (non-Javadoc)
     * @see java.util.Collection#toArray(java.lang.Object[])
     */
    public Object[] toArray(Object[] arg0) {
        LinkedList result = new LinkedList();
        for (int i = 0; i < arg0.length; i++) {
            if (isInCollection(contents, arg0[i]))
                result.add(arg0[i]);
        }
        return result.toArray();
    }

}
