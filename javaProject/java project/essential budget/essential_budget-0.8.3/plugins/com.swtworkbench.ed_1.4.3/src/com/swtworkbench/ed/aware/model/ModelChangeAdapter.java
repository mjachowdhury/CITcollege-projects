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

/**
 * Class ModelChangeAdapter.  An adapter class to assist in implementing 
 * IModelChangeListener.  The adapter implements empty objectCreated
 * and empty objectDeleted methods.  checkObjectDeletion always returns true.
 * 
 * @author daveo
 */
public class ModelChangeAdapter implements IModelChangeListener {

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.model.IModelChangeListener#objectCreated(java.lang.Object, com.swtworkbench.ed.aware.model.NewObject)
     */
    public void objectCreated(Object container, NewObject newObject) {
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.model.IModelChangeListener#objectDeleted(java.lang.Object, int, java.lang.Object)
     */
    public void objectDeleted(Object container, int position, Object removed) {
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.model.IModelChangeListener#checkObjectDeletion(java.lang.Object, int, java.lang.Object)
     */
    public boolean checkObjectDeletion(
        Object container,
        int selection,
        Object toDelete) 
    {
        return true;
    }

}
