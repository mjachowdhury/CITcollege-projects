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
package com.swtworkbench.rcplite.actions;

import java.util.HashMap;

import com.swtworkbench.rcplite.internal.CoolBarManager;
import com.swtworkbench.rcplite.internal.IActionManager;
import com.swtworkbench.rcplite.internal.MenuBarManager;

/**
 * Class ActionManager.  Provides a mechanism for Actions to be added
 * to the user interface of an RCPLite application, to be globally 
 * enabled, or to be globally disabled.  Also provides the infrastructure 
 * that is used by RCPLite itself to build the menu bar and tool bar 
 * from the actions.
 * 
 * @author daveo
 */
public class ActionManager {

    private IActionManager menuBarManager = new MenuBarManager();
    private IActionManager coolBarManager = new CoolBarManager();
    private HashMap actions = new HashMap();

    /**(non-API)
     * Method setEnabled.  Enable or disable a particular action and its
     * UI elements.
     * 
     * @param action the action to enable or disable
     * @param b true if the action should be enabled; false otherwise
     */
    public void setEnabled(Action action, boolean b) {
        menuBarManager.setEnabled(action, b);
        coolBarManager.setEnabled(action, b);
    }
    
    /**
     * Method setEnabled.  Enable or disable an action based on its ID
     * @param actionID
     * @param enabled
     * @throws IllegalArgumentException if an invalid ID is passed.
     */
    public void setEnabled(String actionID, boolean enabled) {
        Action action = (Action) actions.get(actionID);
        if (action != null) {
            action.setEnabled(enabled);
        } else
            throw new IllegalArgumentException("Undefined action: " + actionID);
    }
    
    /**
     * Method getEnabled.  Return if the action corresponding to the specified
     * ID is enabled
     * 
     * @param actionID The action ID string
     * @return true if the corresponding action is enabled.  fsalse otherwise.
     * @throws IllegalArgumentException if an invalid ID is passed.
     */
    public boolean getEnabled(String actionID) {
        Action action = (Action) actions.get(actionID);
        if (action == null) throw new IllegalArgumentException("Undefined action: " + actionID);
        return action.isEnabled();
    }
    
    /**
     * Method add.  Add an action to the UI.  This method may only be called
     * during application startup (ie: from Application#initialize()).
     * 
     * @param action The Action to add to the UI.
     */
    public void add(Action action) {
        actions.put(action.getId(), action);
        if (action.getMenuPosition() != null) {
            menuBarManager.add(action);
        }
        if (action.getToolbarGroup() != null) {
            coolBarManager.add(action);
        }
    }
    
    /**
     * Method get.  Get an Action object based on its ID.
     * 
     * @param id The action's ID
     * @return The Action object or null if it cannot be found.
     */
    public Action get(String id) {
        Action action = (Action) actions.get(id);
        return action;
    }

    /**(non-API)
     * Method buildUI.  This method is called by the Application object after
     * all Parts have been initialized.  It is responsible for building the
     * menu bar and cool bar according to the Action objects that have been
     * registered during system startup.<p>
     * 
     * RCPLite does not support dynamicly adding actions to menu bars or 
     * tool bars after the UI is built.  Calling ActionManager#add() after the
     * UI is built has undefined behavior.
     */
    public void buildUI() {
        menuBarManager.buildUI();
        coolBarManager.buildUI();
    }
}

