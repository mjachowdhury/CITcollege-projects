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
package com.swtworkbench.ed.aware.maskededit;

/**
 * Class MaskMatchException.  An exception that is thrown when an existing input 
 * string that is being set into a masked edit field cannot match the current
 * input mask.
 * 
 * @author daveo
 */
public class MaskMatchException extends Exception {

    /**
     * Constructor MaskMatchException.  
     * 
     * @param position
     * @param mask
     * @param newValue
     */
    public MaskMatchException(int position, String mask, String newValue) {
        super(position + ": Unable to set \"" + newValue + "\" into field with edit mask: " + mask);
        this.position = position;
        this.mask = mask;
        this.newValue = newValue;
    }
    
    public final int position;
    
    public final String mask;
    
    public final String newValue;
}
