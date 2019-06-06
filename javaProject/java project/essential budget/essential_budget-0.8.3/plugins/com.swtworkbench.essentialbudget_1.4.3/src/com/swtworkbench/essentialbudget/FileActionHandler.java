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
package com.swtworkbench.essentialbudget;

import com.swtworkbench.bus.IActor;
import com.swtworkbench.bus.Request;
import com.swtworkbench.essentialbudget.model.BudgetAppData;
import com.swtworkbench.rcplite.Application;
import com.swtworkbench.rcplite.actions.Action;

/**
 * Class FileActionHandler.  Handles file menu / file toolbar action requests.
 * 
 * @author daveo
 */
public class FileActionHandler implements IActor {
    public Object perform(Request r) throws Exception {
        Action action = (Action) r.action;
        if (action.getId().equals("FileExit")) {
            BudgetAppData.save();
            Application.getDefault().getMainWindow().getShell().close();
            r.fulfilled = true;
        } else if (action.getId().equals("FileSave")) {
            BudgetAppData.save();
            r.fulfilled = true;
        }
        return null;
    }
}
