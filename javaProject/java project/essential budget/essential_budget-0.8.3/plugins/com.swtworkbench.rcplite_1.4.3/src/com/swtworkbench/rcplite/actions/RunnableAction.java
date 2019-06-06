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

/**
 * Class RunnableAction.  A convenience class for creating actions with
 * a run() method.  These actions' run() method will automatically be 
 * called whenever the action needs to be run.
 * 
 * @author daveo
 */
public abstract class RunnableAction extends Action implements Runnable {

   /**
    * Constructor RunnableAction.  Construct a RunnableAction.
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
   public RunnableAction(
       String id,
       Image icon,
       Image disabledIcon,
       String toolbarGroup,
       MenuPos menuPosition) 
   {
       super(id, icon, disabledIcon, toolbarGroup, menuPosition);
   }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public abstract void run();
}
