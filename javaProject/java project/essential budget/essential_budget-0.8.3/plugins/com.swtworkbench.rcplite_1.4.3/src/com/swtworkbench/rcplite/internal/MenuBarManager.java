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

import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import com.swtworkbench.rcplite.Application;
import com.swtworkbench.rcplite.actions.Action;
import com.swtworkbench.rcplite.actions.MenuPos;
import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class MenuBarManager.  Creates the menu bar from the Action objects.  Responds
 * to menu selection events as follows:<p>
 * 
 * <ul>
 * <li>If the action instanceof Runnable, then execute the action's run() method
 * <li>Otherwise, pass the action object to the Choreographer bus
 * </ul>
 * 
 * @author daveo
 */
public class MenuBarManager extends SelectionAdapter implements IActionManager {
    
    class MenuNode {
        public MenuNode(Action action) {
            this.action = action;
        }
        
        public MenuNode find(String id) {
            for (Iterator i = children.iterator(); i.hasNext();) {
                MenuNode node = (MenuNode) i.next();
                if (node.action.getId().equals(id)) {
                    return node;
                }
            }
            throw new IllegalArgumentException("Unable to locate menu id " + id + " as a child of " + action.getId());
        }
        
        public final Action action;
        public MenuItem menuItem;
        public LinkedList children = new LinkedList();
    }
    
    private MenuNode root = new MenuNode(null); 

    /**
     * Method add.  
     * @param action
     */
    public void add(Action action) {
        MenuPos menu = action.getMenuPosition();
        addMenuItemTo(action, menu, root);
    }
    
    /*
     * A MenuPos object is basically an OO version of an insert query.  A
     * MenuPos object plus its "getNext()" nodes represents a linked list of
     * menu nodes to traverse to find the correct place to add the new node.
     * The final MenuPos object in the chain represents the actual node to
     * add to the tree.<p>
     * 
     * The strategy here is to recursively traverse the MenuNode tree until
     * we find the place corresponding to the last MenuPos object in the chain.
     * The new MenuNode is added there.
     */
    private void addMenuItemTo(Action action, MenuPos menuItem, MenuNode menu) {
        // If this node is the child node (leaf node in the menu tree), add it
        MenuPos childMenuItem = menuItem.getNext();
        if (childMenuItem == null) {
            menu.children.add(new MenuNode(action));
        } else {
            // Otherwise, find the next child node and recurse...
            MenuNode childMenu = menu.find(menuItem.getMenuID());
            addMenuItemTo(action, childMenuItem, childMenu);
        }
    }

    /**
     * Method buildUI.  
     */
    public void buildUI() {
        if (root.children.size() > 0) {
            Shell shell = Application.getDefault().getMainWindow().getShell();
            Menu bar = new Menu(shell, SWT.BAR);
            buildMenuBar(shell, bar, root);
            shell.setMenuBar(bar);
        }
    }
    
    private void buildMenuBar(Shell shell, Menu bar, MenuNode root) {
        for (Iterator i = root.children.iterator(); i.hasNext();) {
            MenuNode childNode = (MenuNode) i.next();
            MenuItem menuItem;
            int position = childNode.action.getPosition();
            if (position >= 0)
                menuItem = new MenuItem(bar, SWT.CASCADE, position);
            else
                menuItem = new MenuItem(bar, SWT.CASCADE);
            childNode.menuItem = menuItem;
            menuItem.setText(childNode.action.getName());
            menuItem.setEnabled(childNode.action.isEnabled());
            Image icon = childNode.action.getImage();
            if (icon != null)
                menuItem.setImage(icon);
            menuItem.addSelectionListener(this);
            menuItem.setData(childNode.action);
            if (childNode.children.size() > 0) {
                Menu childMenu = new Menu(shell, SWT.DROP_DOWN);
                menuItem.setMenu(childMenu);
                buildCascadedMenu(childMenu, childNode);
            }
        }
    }
    
    private void buildCascadedMenu(Menu menu, MenuNode menuNode) {
        for (Iterator i = menuNode.children.iterator(); i.hasNext();) {
            MenuNode childNode = (MenuNode) i.next();
            String name = childNode.action.getName();
            
            MenuItem menuItem;
            int position = childNode.action.getPosition();
            if (name.equals("-")) {
                if (position >= 0)
                    menuItem = new MenuItem(menu, SWT.SEPARATOR, position);
                else
                    menuItem = new MenuItem(menu, SWT.SEPARATOR);
            } else if (childNode.children.size() > 0) {
                if (position >= 0)
                    menuItem = new MenuItem(menu, SWT.CASCADE, position);
                else
                    menuItem = new MenuItem(menu, SWT.CASCADE);
                Menu childMenu = new Menu(menuItem);
                menuItem.setMenu(childMenu);
                buildCascadedMenu(childMenu, childNode);
            } else {
                if (position >= 0)
                    menuItem = new MenuItem(menu, SWT.NULL, position);
                else
                    menuItem = new MenuItem(menu, SWT.NULL);
            }
            
            menuItem.setEnabled(childNode.action.isEnabled());
            childNode.menuItem = menuItem;
            menuItem.setText(childNode.action.getName());
            Image icon = childNode.action.getImage();
            if (icon != null)
                menuItem.setImage(icon);
            int accelerator = childNode.action.getAccelerator();
            if (accelerator > -1)
                menuItem.setAccelerator(accelerator);
            menuItem.setData(childNode.action);
            menuItem.addSelectionListener(this);
        }
    }

    /**(non-API)
     * Method setEnabled.  Enables/disables all menu choices associated with
     * the specified Action.
     * 
     * @param action The Action to enable/disable
     * @param enabled true if the Action should be enabled; false otherwise.
     */
    public void setEnabled(Action action, boolean enabled) {
        for (Iterator i = root.children.iterator(); i.hasNext();) {
            MenuNode item = (MenuNode) i.next();
            setEnabled(item, action, enabled);
        }
        setEnabled(root, action, enabled);
    }

    /**
     * Method setEnabled.  
     * 
     * @param root
     * @param action
     * @param enabled
     */
    private void setEnabled(MenuNode item, Action action, boolean enabled) {
        if (item.action == action && item.menuItem != null)
            item.menuItem.setEnabled(enabled);
        if (item.children.size() > 0) {
            for (Iterator i = item.children.iterator(); i.hasNext();) {
                MenuNode childItem = (MenuNode) i.next();
                setEnabled(childItem, action, enabled);
            }
        }
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetSelected(SelectionEvent e) {
        Object action = e.widget.getData();
        if (action instanceof Runnable) {
            Runnable reaction = (Runnable) action;
            reaction.run();
        } else {
            try {
                Application.choreographer().request(e.widget.getData());
            } catch (Exception e1) {
                Logger.log().error(e1, "Unhandled exception when handling menu bar request");
            }
        }
    }


}



