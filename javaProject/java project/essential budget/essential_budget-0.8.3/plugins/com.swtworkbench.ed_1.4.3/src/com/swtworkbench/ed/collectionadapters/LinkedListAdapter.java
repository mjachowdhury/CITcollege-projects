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
package com.swtworkbench.ed.collectionadapters;

import java.util.LinkedList;
import java.util.ListIterator;

import com.swtworkbench.ed.collectionadapter.ICollectionAdapter;

/**
 * Class LinkedListAdapter.  
 * 
 * @author daveo
 */
public class LinkedListAdapter implements ICollectionAdapter {
    
    private LinkedList input;

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.collectionadapter.ICollectionAdapter#setInput(java.lang.Object)
     */
    public void setInput(Object input) {
        this.input = (LinkedList) input;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.collectionadapter.ICollectionAdapter#size()
     */
    public int size() {
        return input.size();
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.collectionadapter.ICollectionAdapter#listIterator()
     */
    public ListIterator listIterator() {
        return input.listIterator();
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.collectionadapter.ICollectionAdapter#isDeletable()
     */
    public boolean isDeletable() {
        return true;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.collectionadapter.ICollectionAdapter#isInsertable()
     */
    public boolean isInsertable() {
        return true;
    }

}
