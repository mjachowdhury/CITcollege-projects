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
package com.swtworkbench.ed.aware.messages;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class MessageSource.  All validation error messages are routed through an instance
 * of this class.  By default, the singleton is used, but this may be overridden
 * as appropriate by calling setMessageSender() on the appropriate objects.
 * 
 * @author daveo
 */
public class MessageSource {

    private static MessageSource singleton = null;

    /**
     * Method getDefault.  Returns the default message source object.  This
     * is the MessageSource that will be used if no MessageSource is explicitly
     * registered with an AwareObjectList or a POJOFieldDataAdapter.
     *   
     * @return The default message source object.
     */
    public static MessageSource getDefault() {
        if (singleton == null)
            singleton = new MessageSource();
        return singleton;
    }
    
    //-------------------------------------------------------------------------

    private LinkedList listeners = new LinkedList();

    /**
     * Method addMessageListener.  Adds an IMessageListener event handler to
     * this MessageSource.
     * 
     * @param listener The IMessageListener to add.
     */
    public void addMessageListener(IMessageListener listener) {
        listeners.add(listener);
    }
    
    /**
     * Method removeMessageListener.  
     * 
     * @param listener The IMessageListener to remove.
     * @return true if the IMessageListener was removed; false otherwise.
     */
    public boolean removeMessageListener(IMessageListener listener) {
        return listeners.remove(listener);
    }
    
    /**
     * Method sendMessage.  Broadcasts a message string to all listeners.
     * @param message The message string.
     */
    public void sendMessage(String message) {
        Iterator i = listeners.iterator();
        while (i.hasNext()) {
            IMessageListener listener = (IMessageListener) i.next();
            listener.message(message);
        }
    }
}
