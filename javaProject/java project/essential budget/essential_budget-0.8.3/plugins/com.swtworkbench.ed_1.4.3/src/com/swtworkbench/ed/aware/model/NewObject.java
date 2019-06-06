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

/**
 * Class NewObject.  
 * 
 * @author daveo
 */
public class NewObject implements Serializable {
    /**
     * The new object that was created
     */
    public final Object newObject;
    
    /**
     * The 0-based position of the new object in the collection
     */
    public final int position;
    
    public NewObject(Object newObject, int position) {
        this.newObject = newObject;
        this.position = position;
    }
}
