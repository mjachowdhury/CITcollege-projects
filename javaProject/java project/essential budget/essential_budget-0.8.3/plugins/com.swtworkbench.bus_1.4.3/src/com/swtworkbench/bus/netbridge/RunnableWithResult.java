/*
 * This file is part of com.swtworkbench.bus.
 *
 * com.swtworkbench.bus is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License
 * as published by the Free Software Foundation.
 *
 * com.swtworkbench.bus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with com.swtworkbench.bus; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.swtworkbench.bus.netbridge;

import java.io.Serializable;

/**
 * Class RunnableWithResult.  Represents a Runnable that can return a
 * result and will capture any exception that is thrown.  Works around the
 * fact that Runnable provides neither of these capabilities by default.
 * 
 * @author daveo
 */
public abstract class RunnableWithResult implements Runnable, Serializable {

    /**
     * Method runIt.  Clients should override this method to provide their
     * own behavior.
     *   
     * @return Object The return value
     * @throws Exception When Something Bad Happens
     */
    protected abstract Object runIt() throws Exception;
    
    protected Object result = null;
    
    protected Exception exception = null;

    /**
     * Method getException.  
     * @return
     */
    public Exception getException() {
        return exception;
    }

    /**
     * Method getResult.  
     * @return
     */
    public Object getResult() {
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run() {
        try {
            result = runIt();
        } catch (Exception e) {
            exception = e;
        }
    }
}
