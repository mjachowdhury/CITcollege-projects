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

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.swtworkbench.ed.aware.messages.IMessageListener;
import com.swtworkbench.ed.aware.messages.MessageSource;
import com.swtworkbench.essentialbudget.about.AboutHandler;
import com.swtworkbench.essentialbudget.model.BudgetAppData;
import com.swtworkbench.essentialbudget.views.AccountsView;
import com.swtworkbench.essentialbudget.views.CategoryView;
import com.swtworkbench.essentialbudget.views.TransactionView;
import com.swtworkbench.essentialbudget.views.budgeteditor.BudgetsView;
import com.swtworkbench.rcplite.Application;
import com.swtworkbench.rcplite.Perspective;
import com.swtworkbench.rcplite.PerspectiveManager;
import com.swtworkbench.rcplite.actions.Action;
import com.swtworkbench.rcplite.actions.ActionManager;
import com.swtworkbench.rcplite.actions.MenuPos;
import com.swtworkbench.swtutils.logger.FileLogger;
import com.swtworkbench.swtutils.logger.ILogger;
import com.swtworkbench.swtutils.logger.Logger;
import com.swtworkbench.swtutils.logger.StdLogger;
import com.swtworkbench.swtutils.logger.TeeLogger;

/**
 * Class EssentialBudget.  This is the main application class for EssentialBudget.
 * It mainly implements the RCPLite application window and hooks up the 
 * Choreographer bus to the action framework.
 * 
 * @author daveo
 */
public class EssentialBudget extends Application {
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.demo.framework.Application#initialize()
     */
    protected void initialize() {
        // Initialize the main application shell.  Note that the application's
        // general layout--tool bar, menu bar, perspective area, status bar, etc.
        // are automatically created by the framework.
        Shell shell = getMainWindow().getShell();
        Display display = Display.getDefault();
        Display.setAppName("Essential Budget");
        shell.setText("Essential Budget");
        shell.setImage(new Image(display, getClass().getResourceAsStream("dollar_e.gif")));
        
        // Create and initialize all perspectives/views
        PerspectiveManager pm = getPerspectiveManager();
        
        // Create and hook all global actions--that will appear in the menu bar
        // and on the tool bar.
        ActionManager actionManager = getActionManager();
        
        actionManager.add(new Action("File", null, null, null, 
            new MenuPos("File", "&File")));
        actionManager.add(new Action("FileSave", 
            new Image(display, getClass().getResourceAsStream("icons/save_edit_e.gif")), 
            new Image(display, getClass().getResourceAsStream("icons/save_edit_g.gif")), 
            "File",
            new MenuPos("File",
            new MenuPos("Save", "&Save"))));
        actionManager.add(new Action("FileSep1", null, null, null,
            new MenuPos("File",
            new MenuPos("Sep1", "-"))));
        actionManager.add(new Action("FileExit", null, null, null,
            new MenuPos("File",
            new MenuPos("Exit", "E&xit"))));
        
        actionManager.add(new Action("View", null, null, null, 
            new MenuPos("View", "&View")));
            
        actionManager.add(new Action("Help", null, null, null, 
            new MenuPos("Help", "&Help")));
        actionManager.add(new Action("HelpAbout", null, null, null,
            new MenuPos("Help",
            new MenuPos("About", "&About"))));

        // The ActionManager automatically puts action objects on the
        // Choreographer bus whenever the corresponding UI action is invoked.
        // 
        // Register our action handlers with the Choreographer so we'll know
        // when these actions occur.  Notice that action handlers can choose
        // to handle multiple actions if desired.
        Application.choreographer().register(new FileActionHandler(), Action.class);
        Application.choreographer().register(new AboutHandler(), Action.class);

        
        // Create and initialize the application's perspectives
        
        // Accounts and categories perspective
        Perspective accountsCategories = pm.create("accountsCategories", "&Accounts && Categories", new Image(display, getClass().getResourceAsStream("icons/overview_e.gif")) );
        SashForm s = new SashForm(accountsCategories, SWT.HORIZONTAL);
        AccountsView accountsView = new AccountsView(s);
        new CategoryView(s);
        s.setWeights(new int[]{50, 50});
        accountsCategories.setFocusControl(accountsView.getFocusControl());

        // Budgeting perspective
        Perspective budgeting = pm.create("budget", "&Budgets", new Image(display, getClass().getResourceAsStream("icons/budget_e.gif")) );
        new BudgetsView(budgeting);
        
        // Transactions perspective
        Perspective transactions = pm.create("transactions", "T&ransactions", new Image(display, getClass().getResourceAsStream("icons/transx_e.gif")) );
        new TransactionView(transactions);
        
        // Hook up the status bar
        MessageSource.getDefault().addMessageListener(new IMessageListener() {
            public void message(String message) {
                Application.getDefault().getMainWindow().getStatusBar().setMessage(message);
            }
        });
        
        // Make sure data gets saved if the user closes the shell directly.
        getMainWindow().getShell().addShellListener(new ShellAdapter() {
            public void shellClosed(ShellEvent e) {
                BudgetAppData.save();
            }
        });
    }
    
    private static final String logFile = System.getProperty("user.dir") + "/.log.txt";
    private static final String logControlFile = System.getProperty("user.dir") + "/verboselog.txt";
    
    // This gets everything started to begin with...
    public static void main(String[] args) {
        FileLogger fileLogger = null;
        try {
            // Logging policy for the FileLogger is defined by the log
            // control file.  Logging policy for the StdLogger is just
            // to print actual errors and warnings.
            fileLogger = FileLogger.configure(logFile, logControlFile);
            ILogger realLogger = new TeeLogger(new StdLogger(), fileLogger);
            Logger.setLogger(realLogger);
        } catch (IOException e) {
            System.err.println("WARNING: Could not initialize error log file!");
            e.printStackTrace();
        }

        new EssentialBudget();
        
        try {
            if (fileLogger != null)
                fileLogger.close();
        } catch (IOException e1) {
            System.err.println("WARNING: Could not close error log file!");
            e1.printStackTrace();
        }
    }
}
