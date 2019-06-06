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
package com.swtworkbench.ed.aware.masterdetail;


/**
 * Class IMaster.  An interface for an object that can act as a master in a
 * master-detail relationship.
 * 
 * @see com.swtworkbench.ed.aware.interfaces.IDetail
 * 
 * @author daveo
 */
public interface IMaster {
    /**
     * Adds the listener to the collection of listeners who will
     * be notified when the current row is changing, by sending
     * it one of the messages defined in the <code>IMasterObjectChangeListener</code>
     * interface.<p>
     * 
     * This interface allows clients to block the focus change if 
     * desired.<p>
     * 
     * <b>NOTE</b> When the event is sent, the focus change has not
     * necessarily occured yet.  Be sure to use the MasterObjectChangeEvent 
     * members to discover the old/new row and/or (if applicable)
     * column.<p>
     *
     * @param listener the listener which should be notified
     *
     * @exception IllegalArgumentException <ul>
     *    <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     * </ul>
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     *
     * @see IMasterObjectChangeListener
     * @see #removeMasterObjectChangeListener
     */
    public void addMasterObjectChangeListener(IMasterObjectChangeListener listener);

    /**
     * Removes the listener from the collection of listeners who will
     * be notified when the current row is changing.
     *
     * @param listener the listener which should be notified
     *
     * @exception IllegalArgumentException <ul>
     *    <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     * </ul>
     * @exception SWTException <ul>
     *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
     *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
     * </ul>
     *
     * @see IMasterObjectChangeListener
     * @see #addMasterObjectChangeListener
     */
    public void removeMasterObjectChangeListener(IMasterObjectChangeListener listener);
    
    
    /**
     * Returns the master object corresponding to the specified offset in the 
     * master's collection.  If the master does not have a collection, it is
     * free to ignore the "offset" parameter.
     * 
     * @param offset the offset of the master object within its collection
     * @return the current master object 
     */
    public Object getMasterObject(int offset);
    
}


