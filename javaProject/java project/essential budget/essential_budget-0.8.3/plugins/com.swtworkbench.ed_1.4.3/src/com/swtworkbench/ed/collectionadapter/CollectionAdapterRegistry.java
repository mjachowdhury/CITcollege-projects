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

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import com.swtworkbench.ed.collectionadapters.LinkedListAdapter;
import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class CollectionAdapterRegistry.  
 * 
 * @author daveo
 */
public class CollectionAdapterRegistry {
    private static HashMap registry = new HashMap();
    static {
        registry.put(LinkedList.class, LinkedListAdapter.class);
    }
    
    /**
     * Method lookup.  Retrieve a collection adapter for the specified object.
     * 
     * @param input The input object
     * @return The relevent ICollectionAdapter
     */
    public static ICollectionAdapter lookup(Object input) {
        // Build a list of the IGenericIterator types that could possibly support 'input'
        LinkedList possibleTypes = new LinkedList();
        Iterator i = registry.keySet().iterator();
        while (i.hasNext()) {
            Class supportedClass = (Class)i.next();
            if (supportedClass.isAssignableFrom(input.getClass())) {
                possibleTypes.add(supportedClass);
            }
        }
        
        // Now, figure out which one is most specifically applicable to 'input'
        Class specificClass = (Class) possibleTypes.getFirst();
        if (specificClass != null) {
            i = possibleTypes.iterator();
            while (i.hasNext()) {
                Class maybeMoreSpecificClass = (Class)i.next();
                if (specificClass.isAssignableFrom(maybeMoreSpecificClass)) {
                    specificClass = maybeMoreSpecificClass;
                }
            }
        }
        
        // If we found something, construct an IGenericIterator and return it
        if (specificClass != null) {
            Class classToConstruct = (Class) registry.get(specificClass);
            try {
                ICollectionAdapter result = (ICollectionAdapter) classToConstruct.newInstance();
                result.setInput(input);
                return result;
            } catch (Exception e) {
                Logger.log().error(e, "Unable to construct a " + classToConstruct.getName());
                return null;
            }
        }
        
        // If we didn't find anything, try to construct an IGenericIterator using
        // one of our reflection-based implementations
        return null;
    }
}
