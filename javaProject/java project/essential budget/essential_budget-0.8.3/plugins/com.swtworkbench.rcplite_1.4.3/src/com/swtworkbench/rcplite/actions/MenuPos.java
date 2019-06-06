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

/**
 * Class MenuPos.  Represents the position of a menu item on the menu by chaining
 * objects of class MenuPos together.
 * 
 * @author daveo
 */
public class MenuPos {
    
    /**
     * Constructor MenuPos.  Adds a leaf node to the menu tree.
     * 
     * @param menuID The new menu item's ID.
     * @param name The new menu item's human-readable name.
     * @param accelerator The accelerator key or -1 if none
     */
    public MenuPos(String menuID, String name, int accelerator, int position) {
        this.menuID = menuID;
        this.name=name;
        this.accelerator = accelerator;
        this.position = position;
        next = null;
    }
    
    /**
     * Constructor MenuPos.  Adds a leaf node to the menu tree.
     * 
     * @param menuID The new menu item's ID.
     * @param name The new menu item's human-readable name.
     */
    public MenuPos(String menuID, String name) {
        this.menuID = menuID;
        this.name=name;
        this.accelerator = -1;
        next = null;
        position = -1;
    }
    
    /**
     * Constructor MenuPos.  Adds a leaf node to the menu tree.
     * 
     * @param menuID The new menu item's ID.
     * @param name The new menu item's human-readable name.
     */
    public MenuPos(String menuID, String name, int position) {
        this.menuID = menuID;
        this.name=name;
        this.accelerator = -1;
        this.position = position;
        next = null;
    }
    
    /**
     * Constructor MenuPos.  Traverses a pre-existing intermediate node
     * in the menu tree.  Note that no nodes are added implicitly.  Instead, 
     * every node starts out as a leaf node, and then child nodes are added
     * to it as needed.
     * 
     * @param menuID The ID of the existing node.
     * @param next The next node in the chain.
     */
    public MenuPos(String menuID, MenuPos next) {
        this.menuID = menuID;
        this.next = next;
        name=null;
        position = -1;
        accelerator = -1;
    }
    
    private final String menuID;

    /**
     * Method getMenuID.  Returns the programmatic ID for the menu choice.
     * @return The menu ID string
     */
    public String getMenuID() {
        return menuID;
    }
    
    private final MenuPos next;
    
    /**
     * Method getNext.  Returns the next MenuPos in the chain or null if there
     * is none.
     * 
     * @return The next MenuPos or null if there is none
     */
    public MenuPos getNext() {
        return next;
    }
    
    private final String name;

    /**
     * Method getName.  Returns the menu's human-readable name or null
     * if this is not the leaf node in the chain.
     * 
     * @return The human-readable name.
     */
    public String getName() {
        return name;
    }
    
    private final int position;
    
    /**
     * Method getPosition.  Specifies the position in the parent menu where 
     * the child where be inserted.
     * 
     * @return the 0-based offset where the item will be inserted in the parent
     * or -1 if the item should be appended to the end.
     */
    public int getPosition() {
        return position;
    }

    private final int accelerator;

    /**
     * Method getAccelerator.  Gets the accelerator key sequence.
     * @return The SWT accelerator key sequence
     */
    public int getAccelerator() {
        return accelerator;
    }

}
