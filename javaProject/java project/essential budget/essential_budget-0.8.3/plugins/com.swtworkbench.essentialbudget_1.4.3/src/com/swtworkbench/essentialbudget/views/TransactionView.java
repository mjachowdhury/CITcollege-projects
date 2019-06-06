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

import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import com.swtworkbench.ed.aware.model.IModelChangeListener;
import com.swtworkbench.ed.aware.model.Model;
import com.swtworkbench.ed.aware.model.ModelChangeAdapter;
import com.swtworkbench.ed.aware.model.NewObject;
import com.swtworkbench.essentialbudget.EssentialBudget;
import com.swtworkbench.essentialbudget.model.Account;
import com.swtworkbench.essentialbudget.model.BudgetAppData;
import com.swtworkbench.rcplite.Application;
import com.swtworkbench.rcplite.actions.Action;
import com.swtworkbench.rcplite.actions.ActionManager;
import com.swtworkbench.rcplite.actions.MenuPos;
import com.swtworkbench.rcplite.views.ViewFolder;
import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class TransactionView.  The Transactions view is a ViewFolder, which is
 * to say that it's a view that is a folder for ViewFolderPages.  This is
 * RCPLite's analog to Eclipse editors, except that since a ViewFolder is
 * just another kind of View, you can have as many ViewFolders as you like,
 * anywhere in your user interface.
 * 
 * @author daveo
 */
public class TransactionView extends ViewFolder {

    /**
     * Constructor TransactionView.  Construct the TransactionView.
     * 
     * @param parent
     */
    public TransactionView(Composite parent) {
        super(parent);
        
        // Add some actions and action handlers to the UI
        ActionManager actionManager = Application.getDefault().getActionManager();
        Display display = Display.getDefault();
        actionManager.add(new Action("ViewTransactions", 
                new Image(display, EssentialBudget.class.getResourceAsStream("icons/transx_e.gif")), 
                new Image(display, EssentialBudget.class.getResourceAsStream("icons/transx_g.gif")), 
                "View",
                new MenuPos("View",
                new MenuPos("ViewTransactions", "T&ransactions"))));
        
        Application.choreographer().register(new FocusPartHandler(this, "ViewTransactions"), Action.class);

        // Hook the data model, create transaction registers...
        theThis=this;
        budgetData = BudgetAppData.getDefault();
        createTransactionRegisters();
        hookAccountEvents();
    }

    private BudgetAppData budgetData;
    protected TransactionView theThis;
    
    protected LinkedList registers = new LinkedList();

    /**
     * Method createTransactionRegisters.  
     * 
     */
    private void createTransactionRegisters() {
        createRegisters(budgetData.getAssets());
        createRegisters(budgetData.getLiabilities());
    }

    private void createRegisters(LinkedList accountList) {
        for (Iterator i = accountList.iterator(); i.hasNext();) {
            Account account = (Account) i.next();
            registers.addLast(new TransactionRegister(this, account));
        }
    }

    private void hookAccountEvents() {
        Model.getDefault().addModelChangeListener(budgetData.getAssets(), listener);
        Model.getDefault().addModelChangeListener(budgetData.getLiabilities(), listener);
    }

    private IModelChangeListener listener = new ModelChangeAdapter() {
        public void objectCreated(Object container, NewObject newObject) {
            registers.addLast(new TransactionRegister(theThis, (Account)newObject.newObject));
        }
        public void objectDeleted(
            Object container,
            int position,
            Object removed) 
        {
            for (Iterator i = registers.iterator(); i.hasNext();) {
                TransactionRegister register = (TransactionRegister) i.next();
                if (register.getAccount() == removed) {
                    i.remove();
                    register.dispose();
                    return;
                }
            }
            Logger.log().error(new Exception(), "Unable to find register to remove");
        }
    };

}
