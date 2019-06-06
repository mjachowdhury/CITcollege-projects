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
package com.swtworkbench.ed.aware.adapters.pojo;

/**
 * An exception that is thrown when code programmatically attempts to
 * remove focus from an object or a property that cannot be saved because
 * it is not valid.
 * 
 * @author daveo
 */
public class UnableToSaveException extends Exception {
    public UnableToSaveException() {}
    
    public UnableToSaveException(String message, Exception e) {
        super(message, e);
    }
    
    public UnableToSaveException(String message) {
        super(message);
    }
    
    public UnableToSaveException(Exception e) {
        super(e);
    }
}
