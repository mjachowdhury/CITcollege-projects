/*
 * This file is part of com.swtworkbench.rcplite.
 *
 * com.swtworkbench.rcplite is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License
 * as published by the Free Software Foundation.
 *
 * com.swtworkbench.rcplite is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with com.swtworkbench.rcplite; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.swtworkbench.rcplite.internal;

import com.swtworkbench.rcplite.actions.Action;


/**
 * Class IActionManager.  
 * 
 * @author daveo
 */
public interface IActionManager {
    /**
     * Method add.  
     * @param action
     */
    public abstract void add(Action action);
    /**
     * Method buildUI.  
     */
    public abstract void buildUI();
    /**
     * Method setEnabled.  
     * @param action
     * @param b
     */
    public abstract void setEnabled(Action action, boolean b);
}