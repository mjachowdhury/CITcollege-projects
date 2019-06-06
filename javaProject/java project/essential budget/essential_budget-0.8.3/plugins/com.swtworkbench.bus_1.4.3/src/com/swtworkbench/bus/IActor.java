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
 * Interface IActor.  An interface for a Choreographer's actor (event handler) object
 * 
 * @author djo
 */
public interface IActor extends Serializable {
    /**
     * Method perform. Tells the actor to fulfill some request.
     *   
     * @param r The Request object describing what the actor should do
     * @return An object as a return value or null.
     * @throws Exception if Something Bad happened.
     */
    public Object perform(Request r) throws Exception;
}
