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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.swtworkbench.rcplite.Application;
import com.swtworkbench.rcplite.actions.Action;
import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class CoolBarManager.  
 * 
 * @author daveo
 */
public class CoolBarManager extends SelectionAdapter implements IActionManager {

    private LinkedList coolBarGroups = new LinkedList();
    
    private class CoolBarGroup {
        public CoolBarGroup(String id) {
            this.id = id;
        }
        public final String id;
        
        public LinkedList items = new LinkedList(); 
    }
    
    private CoolBarGroup find(String id) {
        for (Iterator i = coolBarGroups.iterator(); i.hasNext();) {
            CoolBarGroup group = (CoolBarGroup) i.next();
            if (group.id.equals(id))
                return group;
        }
        return null;
    }
    
    private class CoolBarItem {
        public ToolItem toolItem = null;

        public final Action action;

        public CoolBarItem(Action action) {
            this.action = action;
        }
    }

    /**
     * Method add.  
     * @param action
     */
    public void add(Action action) {
        CoolBarGroup group = find(action.getToolbarGroup());
        if (group == null) {
            group = new CoolBarGroup(action.getToolbarGroup());
            coolBarGroups.addLast(group);
        }
        group.items.addLast(new CoolBarItem(action));
    }

    /**
     * Method buildUI.  
     */
    public void buildUI() {
        final CoolBar coolBar = Application.getDefault().getMainWindow().getCoolBar();
        for (Iterator groups = coolBarGroups.iterator(); groups.hasNext();) {
            CoolItem coolItem = new CoolItem(coolBar, SWT.NONE);
            CoolBarGroup group = (CoolBarGroup) groups.next();
            ToolBar toolBar = new ToolBar(coolBar, SWT.FLAT);
            
            for (Iterator items = group.items.iterator(); items.hasNext();) {
                CoolBarItem item = (CoolBarItem) items.next();
                ToolItem toolItem = new ToolItem(toolBar, SWT.PUSH);
                toolItem.addSelectionListener(this);
                item.toolItem = toolItem;
                Action action = item.action;
                toolItem.setText(action.getName());
                Image icon = action.getImage();
                if (icon != null)
                    toolItem.setImage(icon);
                icon = action.getDisabledImage();
                if (icon != null)
                    toolItem.setDisabledImage(icon);
                toolItem.setData(action);
                toolItem.setEnabled(action.isEnabled());
            }

            Point size = toolBar.computeSize(SWT.DEFAULT, SWT.DEFAULT);
            coolItem.setPreferredSize(coolItem.computeSize(size.x, size.y));
            coolItem.setControl(toolBar);
        }
        coolBar.addListener(SWT.Resize, new Listener() {
            public void handleEvent(Event event) {
                coolBar.getParent().layout(true);
            }
        });
        coolBar.getParent().layout(true);
    }

    /**
     * Method setEnabled.  
     * @param action
     * @param enabled
     */
    public void setEnabled(Action action, boolean enabled) {
        for (Iterator groups = coolBarGroups.iterator(); groups.hasNext();) {
            CoolBarGroup group = (CoolBarGroup) groups.next();
            for (Iterator items = group.items.iterator(); items.hasNext();) {
                CoolBarItem item = (CoolBarItem) items.next();
                if (item.action == action && item.toolItem != null)
                    item.toolItem.setEnabled(enabled);
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
