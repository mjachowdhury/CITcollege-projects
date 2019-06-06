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

import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.swtworkbench.bus.IActor;
import com.swtworkbench.bus.Request;
import com.swtworkbench.ed.controls.mrotable.IPartControlFactory;
import com.swtworkbench.ed.controls.mrotable.MROTable;
import com.swtworkbench.ed.controls.mrotable.TableLayout;
import com.swtworkbench.essentialbudget.EssentialBudget;
import com.swtworkbench.essentialbudget.model.BudgetAppData;
import com.swtworkbench.rcplite.Application;
import com.swtworkbench.rcplite.actions.Action;
import com.swtworkbench.rcplite.actions.ActionManager;
import com.swtworkbench.rcplite.actions.MenuPos;
import com.swtworkbench.rcplite.views.View;
import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class AccountsView.  Allows editing of the user's accounts.
 * 
 * @author daveo
 */
public class AccountsView extends View {
    
    protected AccountsView theThis;

    /**
     * Constructor AccountsView.  
     * 
     * @param parent
     * @param icon
     * @param title
     */
    public AccountsView(Composite parent) {
        super(parent, 
            new Image(Display.getDefault(), EssentialBudget.class.getResourceAsStream("icons/accounts_e.gif")), 
            "Accounts");
        
        theThis = this;

        // Add some actions and action handlers to the UI
        // 
        // This must be done here because createPartControl() is asyncExec()d
        // and thus executes after the main window's UI has already been built.
        ActionManager actionManager = Application.getDefault().getActionManager();
        Display display = Display.getDefault();
        actionManager.add(new Action("ViewAccounts", 
                new Image(display, EssentialBudget.class.getResourceAsStream("icons/accounts_e.gif")), 
                new Image(display, EssentialBudget.class.getResourceAsStream("icons/accounts_g.gif")), 
                "View",
                new MenuPos("View",
                new MenuPos("Accounts", "&Accounts"))));
        actionManager.add(new Action("ViewAccountsAssets", null, null, null,
                new MenuPos("View",
                new MenuPos("ViewAccounts",
                new MenuPos("ViewAccountsAssets", "&Assets")))));
        actionManager.add(new Action("ViewAccountsLiabilities", null, null, null,
                new MenuPos("View",
                new MenuPos("ViewAccounts",
                new MenuPos("ViewAccountsLiabilities", "&Liabilities")))));
        
        Application.choreographer().register(new FocusPartHandler(this, "ViewAccounts"), Action.class);
        Application.choreographer().register(new ActionHandler(), Action.class);
    }
    
    private class ActionHandler implements IActor {
        public Object perform(Request r) throws Exception {
            Action action = (Action) r.action;
            if (action.getId().equals("ViewAccountsAssets")) {
                if (!isActivated())
                    theThis.setFocus();
                assetsTable.setFocus();
                r.fulfilled = true;
            } else if (action.getId().equals("ViewAccountsLiabilities")) {
                if (!isActivated())
                    theThis.setFocus();
                Display.getDefault().asyncExec(new Runnable() {
                    public void run() {
                        liabilitiesTable.setFocus();
                    }
                });
                r.fulfilled = true;
            }
            
            return null;
        }
    }
    
    private LinkedList assets;
    private LinkedList liabilities;
    
    private Color white = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
    private Color darkgray = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY);

    private int[] getColumnWeights() {
        return new int[] {80, 20};
    }
    
    private class AccountHeaderFactory implements IPartControlFactory {
        public AccountHeaderFactory(String title) {
            this.title = title;
        }
        private String title = "";
		public Composite createPartControl(Composite parent) {
            Composite part = new Composite(parent, SWT.NULL);
            part.setLayout(new TableLayout(getColumnWeights(), SWT.CENTER, darkgray));

            new Label(part, SWT.NULL).setText(title);
            new Label(part, SWT.RIGHT).setText("Balance");
            
            return part;
		}
    }
    
    private class AccountRowFactory implements IPartControlFactory {
		public Composite createPartControl(Composite parent) {
            // The row Composite
            Composite part = new Composite(parent, SWT.NULL);
            part.setBackground(white);
            part.setLayout(new TableLayout(getColumnWeights(), SWT.CENTER | SWT.BORDER));

            // The actual SWT editor objects
            new Text(part, SWT.NULL).setData("boundTo", "Name");
            Label balance = new Label(part, SWT.RIGHT);   // FIXME: Can't use SWT Label!
            balance.setData("boundTo", "Balance");
            balance.setBackground(white);
            
            return part;
		}
    }
    
    private MROTable createMROTable(Composite parent, Object input, String property, String title) {
        // Make the actual table
        MROTable table = new MROTable(parent, SWT.BORDER);
        table.setHelpText("Click to highlight; [INS] to insert a row; [Ctrl-DEL] to delete a row");
        table.setHeaderFactory(new AccountHeaderFactory(title));
        table.getControl().setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
        try {
        	table.edit(input, property, new AccountRowFactory());
        } catch (Exception e) {
            Logger.log().error(e, "Error creating MROTable");
        }
        
        return table;
    }
    
    private MROTable assetsTable;
    private MROTable liabilitiesTable;
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.demo.rcplite.views.Part#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    protected Composite createPartControl(Composite parent) {
        // Get the data that we'll edit
        this.assets = BudgetAppData.getDefault().getAssets();
        this.liabilities = BudgetAppData.getDefault().getLiabilities();
        BudgetAppData budgetData = BudgetAppData.getDefault();

        // Create the UI
        SashForm s = new SashForm(parent, SWT.VERTICAL);
        assetsTable = createMROTable(s, budgetData, "Assets", "Asset Account Name");
        liabilitiesTable = createMROTable(s, budgetData, "Liabilities", "Liability Account Name");
        s.setWeights(new int[]{50, 50});
        
        return s;
    }

}


