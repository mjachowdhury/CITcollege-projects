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

import org.eclipse.swt.graphics.Image;

import com.swtworkbench.rcplite.Application;

/**
 * Class Action.  An abstract representation of a UI action and where it should
 * be represented in the UI.  Basic actions do not have a run method.  Instead,
 * the action object itself is sent as a request on the Choreographer bus.
 * 
 * @author daveo
 */
public class Action {
    /**
     * Constructor Action.  Construct an Action object.
     * 
     * @param id The action ID
     * 
     * @param icon The normal icon
     * 
     * @param disabledIcon the icon to show if the action is disabled
     * 
     * @param toolbarGroup A String indication the tool bar group that should 
     * display this action or null if the action should not appear on the tool bar.
     * 
     * @param menuPosition A MenuPos graph indicating the location of this 
     * Action on the menu bar or null if this action should not appear on the menu.
     */
    public Action(
        String id,
        Image icon,
        Image disabledIcon,
        String toolbarGroup,
        MenuPos menuPosition) 
    {
        this.id = id;
        this.image = icon;
        this.disabledImage = disabledIcon;
        this.toolbarGroup = toolbarGroup;
        this.menuPosition = menuPosition;
    }

    private final String id;

    /**
     * Method getId.  Returns the action ID
     * @return The action ID string
     */
    public String getId() {
        return id;
    }

    /**
     * Method getName.  Returns the menu item's name for the UI.
     * @return The menu item's name
     */
    public String getName() {
        MenuPos menuItem;
        for (menuItem = menuPosition;
            menuItem.getNext() != null;
            menuItem = menuItem.getNext()) {}
        return menuItem.getName();
    }
    
    /**
     * Method getPosition.
     * 
     * @see MenuPos#getPosition
     */
    public int getPosition() {
        MenuPos menuItem;
        for (menuItem = menuPosition;
            menuItem.getNext() != null;
            menuItem = menuItem.getNext()) {}
        return menuItem.getPosition();
    }

    /**
     * Method getAccelerator.  
     * @return
     */
    public int getAccelerator() {
        MenuPos menuItem;
        for (menuItem = menuPosition;
            menuItem.getNext() != null;
            menuItem = menuItem.getNext()) {}
        return menuItem.getAccelerator();
    }

    private final Image image;

    /**
     * Method getImage.  Returns the action's icon.
     * @return
     */
    public Image getImage() {
        return image;
    }

    private final Image disabledImage;

    /**
     * Method getDisabledImage.  
     * @return
     */
    public Image getDisabledImage() {
        return disabledImage;
    }

    private final String toolbarGroup;

    /**
     * Method getToolbarGroup.  Returns the name of the toolbar group to which
     * this action belongs.
     * 
     * @return the tool bar group name
     */
    public String getToolbarGroup() {
        return toolbarGroup;
    }

    private final MenuPos menuPosition;

    /**
     * Method getMenuPosition.  Returns the position of this action on the
     * menu bar or null if this action is not on the menu bar.
     * 
     * @return The MenuPos or null if this action is not on the menu
     */
    public MenuPos getMenuPosition() {
        return menuPosition;
    }

    private boolean enabled = true;

    /**
     * Method isEnabled.  Returns if this action is enabled in the UI.
     * @return
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Method setEnabled.  Enables or disables this action in the UI.
     * @param b
     */
    public void setEnabled(boolean b) {
        Application.getDefault().getActionManager().setEnabled(this, b);
        enabled = b;
    }

}
