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
import java.lang.reflect.Method;

import com.swtworkbench.ed.aware.iterators.IteratorAdapter;
import com.swtworkbench.ed.aware.iterators.ListIteratorAdapter;

/**
 * Class IteratorFactory.  
 * 
 * @author daveo
 */
public class IteratorAdapterFactory {
    /**
     * Method getIterator.  Returns an IAwareIterator for container
     * @param container The container over which to iterate
     * @return The IAwareIterator that was created
     * 
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static IAwareIterator iterator(Object container) throws SecurityException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class containerClass = container.getClass();
        try {
            Method listIterator = containerClass.getMethod("listIterator", new Class[] {});
            if (listIterator != null)
                return new ListIteratorAdapter(container);
            else
                return new IteratorAdapter(container);
        } catch (NoSuchMethodException e) {
            return new IteratorAdapter(container);
        }
    }

}
