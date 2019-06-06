/*
 * This file is part of com.swtworkbench.essentialbudget.
 *
 * com.swtworkbench.essentialbudget is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License
 * as published by the Free Software Foundation.
 *
 * com.swtworkbench.essentialbudget is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with com.swtworkbench.essentialbudget; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.swtworkbench.essentialbudget.views;

import com.swtworkbench.bus.IActor;
import com.swtworkbench.bus.Request;
import com.swtworkbench.rcplite.actions.Action;
import com.swtworkbench.rcplite.views.Part;

/**
 * This is a handler for actions that just focus a certain Part
 * @author daveo
 */
public class FocusPartHandler implements IActor {
    private String actionID;
    private Part part;
    
    public FocusPartHandler(Part part, String actionID) {
        this.part = part;
        this.actionID = actionID;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.bus.IActor#perform(com.swtworkbench.bus.Request)
     */
    public Object perform(Request r) throws Exception {
        Action action = (Action) r.action;
        if (action.getId().equals(actionID)) {
            part.setFocus();
            r.fulfilled = true;
        }
        return null;
    }
}
