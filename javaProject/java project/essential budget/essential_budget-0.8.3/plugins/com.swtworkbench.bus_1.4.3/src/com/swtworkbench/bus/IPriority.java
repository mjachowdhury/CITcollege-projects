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

/**
 * Class Priority.  A bunch of constants to help make priorities more readable
 * in Choreographer code.  These define the priority conventions used by 
 * Choreographer actions.<p>
 * 
 * Note that the priority mechanism is used along with the action topic 
 * (class/interface) mechanism to separate different types of actions from 
 * each other.  For example, you don't normally want UI validation events
 * leaking over your Choreographer bridge out over the network to other 
 * Choreographer busses in other applications.  Thus, by convention, these
 * are given the highest priority so that these actions will be handled before
 * any network actor gets a chance to look at them.<p>
 * 
 * The Choreographer priority mechanism is designed so that clients can subclass
 * this interface and add their own priority constants in between the numbers
 * chosen here.<p> 
 * 
 * @author djo
 */
public interface IPriority {
    // UI action message priority
    public static final int uiVeryHigh = 50200;
    public static final int uiHigh = 50100;
    public static final int ui = 50000;
    public static final int uiLow = 40900;
    public static final int uiVeryLow = 40800;
    
    // "Normal" action message priority
    public static final int veryHigh = 200;
    public static final int high = 100;
    public static final int average = 0;
    public static final int low = -100;
    public static final int veryLow = -200;
    
    // Network action message priorities
    public static final int netVeryHigh = -40800;
    public static final int netHigh = -40900;
    public static final int net = -50000;
    public static final int netLow = -50100;
    public static final int netVeryLow = -50200;
}


