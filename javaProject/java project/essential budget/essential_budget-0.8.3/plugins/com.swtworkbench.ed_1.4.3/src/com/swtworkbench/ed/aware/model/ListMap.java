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
import java.util.Map;
import java.util.Set;

/**
 * Class ListMap.  
 * 
 * @author daveo
 */
public class ListMap implements Map, Serializable {
    
    private class Pair implements Map.Entry, Serializable {
        public Object key;
        public Object value;
        
        public Pair(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
        
        /* (non-Javadoc)
         * @see java.util.Map.Entry#getKey()
         */
        public Object getKey() {
            return key;
        }
        
        /* (non-Javadoc)
         * @see java.util.Map.Entry#getValue()
         */
        public Object getValue() {
            return value;
        }
        
        /* (non-Javadoc)
         * @see java.util.Map.Entry#hashCode()
         */
        public int hashCode() {
            return key.hashCode();
        }

        /* (non-Javadoc)
         * @see java.util.Map.Entry#setValue(java.lang.Object)
         */
        public Object setValue(Object arg0) {
            Object oldValue = value;
            value = arg0;
            return oldValue;
        }

    }
    
    private LinkedList storage = new LinkedList();

    /* (non-Javadoc)
     * @see java.util.Map#size()
     */
    public int size() {
        return storage.size();
    }

    /* (non-Javadoc)
     * @see java.util.Map#clear()
     */
    public void clear() {
        storage = new LinkedList();
    }

    /* (non-Javadoc)
     * @see java.util.Map#isEmpty()
     */
    public boolean isEmpty() {
        return storage.isEmpty();
    }

    /* (non-Javadoc)
     * @see java.util.Map#containsKey(java.lang.Object)
     */
    public boolean containsKey(Object arg0) {
        for (Iterator i = storage.iterator(); i.hasNext();) {
            Pair element = (Pair) i.next();
            if (element.key == arg0) return true;
        }
        return false;
    }

    /* (non-Javadoc)
     * @see java.util.Map#containsValue(java.lang.Object)
     */
    public boolean containsValue(Object arg0) {
        for (Iterator i = storage.iterator(); i.hasNext();) {
            Pair element = (Pair) i.next();
            if (element.value == arg0) return true;
        }
        return false;
    }

    /* (non-Javadoc)
     * @see java.util.Map#values()
     */
    public Collection values() {
        LinkedList result = new LinkedList();
        for (Iterator i = storage.iterator(); i.hasNext();) {
            Pair element = (Pair) i.next();
            result.add(element.value);
        }
        return result;
    }

    /* (non-Javadoc)
     * @see java.util.Map#putAll(java.util.Map)
     */
    public void putAll(Map arg0) {
        for (Iterator i = arg0.keySet().iterator(); i.hasNext();) {
            Object key = (Object) i.next();
            put(key, arg0.get(key));
        }
    }

    /* (non-Javadoc)
     * @see java.util.Map#entrySet()
     */
    public Set entrySet() {
        Set result = new ListSet();
        for (Iterator i = storage.iterator(); i.hasNext();) {
            Pair element = (Pair) i.next();
            result.add(element);
        }
        return result;
    }

    /* (non-Javadoc)
     * @see java.util.Map#keySet()
     */
    public Set keySet() {
        Set result = new ListSet();
        for (Iterator i = storage.iterator(); i.hasNext();) {
            Pair element = (Pair) i.next();
            result.add(element.key);
        }
        return result;
    }
    
    private Pair findPair(Object key) {
        for (Iterator i = storage.iterator(); i.hasNext();) {
            Pair element = (Pair) i.next();
            if (element.key == key)
                return element;
        }
        return null;
    }

    /* (non-Javadoc)
     * @see java.util.Map#get(java.lang.Object)
     */
    public Object get(Object arg0) {
        Pair element = findPair(arg0);
        if (element != null)
            return element.value;
        return null;
    }

    /* (non-Javadoc)
     * @see java.util.Map#remove(java.lang.Object)
     */
    public Object remove(Object arg0) {
        for (Iterator i = storage.iterator(); i.hasNext();) {
            Pair element = (Pair) i.next();
            if (element.key == arg0) {
                i.remove();
                return element.value;
            }
        }
        return null;
    }

    /* (non-Javadoc)
     * @see java.util.Map#put(java.lang.Object, java.lang.Object)
     */
    public Object put(Object arg0, Object arg1) {
        Pair element = findPair(arg0);
        if (element != null) {
            Object oldvalue = element.value;
            element.value = arg1;
            return oldvalue;
        } else {
            element = new Pair(arg0, arg1);
            storage.addLast(element);
            return null;
        }
    }

}
