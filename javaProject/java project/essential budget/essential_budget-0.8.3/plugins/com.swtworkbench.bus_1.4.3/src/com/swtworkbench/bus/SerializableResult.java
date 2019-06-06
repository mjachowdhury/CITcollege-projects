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
package com.swtworkbench.bus;

import java.io.Serializable;

/**
 * Class SerializableResult.  
 * 
 * @author daveo
 */
public class SerializableResult extends Result implements Serializable {

    public Exception exception = null;


    /**
     * Constructor SerializableResult.  
     * 
     * @param e
     */
    public SerializableResult(Exception e) {
        super(null, false);
        exception = e;
    }

    /**
     * Constructor SerializableResult.  
     * 
     * @param result
     * @param handled
     */
    public SerializableResult(Result r) {
        super(r.result, r.handled);
    }
}
