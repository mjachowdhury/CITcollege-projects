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

import org.eclipse.swt.graphics.Point;

/**
 * The event that is sent when an IMaster is about to move focus from one
 * row/object to another.  
 * 
 * @author daveo
 */
public class MasterObjectChangeEvent {
    public MasterObjectChangeEvent(IMaster sender, int oldCol, int oldRow, int newCol, int newRow) {
        this.sender = sender;
        this.oldSelection = new Point(oldCol, oldRow);
        this.newSelection = new Point(newCol, newRow);
    }
    
    /**
     * The IMaster object sending the event
     */
    public final IMaster sender;
    
    /**
     * The (col, row) of the cell that is losing focus.  If the master is not
     * tabular in nature, col will be -1.
     */
    public final Point oldSelection;
    
    /**
     * The (col, row) of the cell that is gaining focus.  If the master is not
     * tabular in nature, col will be -1.
     */
    public final Point newSelection;
    
    /**
     * A flag indicating if the row change should occur.  Defaults
     * to true.  Setting doit to false will prohibit the change from occurring.
     */
    public boolean doit=true;
    
}


