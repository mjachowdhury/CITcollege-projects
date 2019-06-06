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
package com.swtworkbench.rcplite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.swtworkbench.swtutils.fromeclipse.PageBook;

/**
 * Class MainWindow.  The RCPLite main window class.<p>
 * 
 * This class's constructor calls the protected #createMainWindowLayout() 
 * method.
 * 
 * @author daveo
 */
public class MainWindow {
    
    /**(non-API)
     * Constructor MainWindow.  
     * 
     * Construct a MainWindow object and call createMainWindowLayout().  This
     * is called automatically by the Application object.
     */
    public MainWindow(Shell shell) {
        this.shell = shell;
        createMainWindowLayout(shell);
    }
    
    /**(non-API)
     * Method createMainWindowLayout.  This method creates the GUI for the
     * main window.
     */
    protected void createMainWindowLayout(final Shell shell) {
        GridLayout l = new GridLayout();
        l.marginHeight=0;
        l.marginWidth=0;
        l.horizontalSpacing=0;
        l.verticalSpacing=0;
        shell.setLayout(l);
        
        // Separator
        new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL)
            .setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        // CoolBar/ToolBar
        coolBar = new CoolBar(shell, SWT.FLAT);
        coolBar.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        coolBar.addControlListener(new ControlAdapter() {
            public void controlResized(ControlEvent e) {
                shell.layout(true);
            }
        });

        // Separator
        new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL)
            .setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        // PageBook for perspectives
        Composite contentPaneHolder = new Composite(shell, SWT.NULL);
        l = new GridLayout(1, false);
        l.marginHeight = 3;
        l.marginWidth = 3;
        contentPaneHolder.setLayout(l);
        contentPaneHolder.setLayoutData(new GridData(GridData.FILL_BOTH));
        contentPane = new PageBook(contentPaneHolder, SWT.NULL);
        contentPane.setLayoutData(new GridData(GridData.FILL_BOTH));

        // Status bar
        statusBar = new StatusBar(shell, SWT.NULL);
        statusBar.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    }
    
    private PageBook contentPane;
    
    /**(non-API)
     * Method getContentPane.  Returns the application's main content pane.  In
     * this implementation, the content pane is a PageBook, which is the 
     * container for all perspectives.
     * 
     * @return PageBook the content pane PageBook
     */
    public PageBook getContentPane() {
        return contentPane;
    }

    protected Shell shell;
    
    /**
     * Method getShell.  Returns the main window's shell object.
     * @return The Shell
     */
    public Shell getShell() {
        return shell;
    }
    
    protected CoolBar coolBar;

    /**(non-API)
     * Method getCoolBar.  Returns the application's CoolBar.
     * @return The CoolBar
     */
    public CoolBar getCoolBar() {
        return coolBar;
    }
    
    protected StatusBar statusBar;
    
    /**
     * Method getStatusBar.  Returns the applications StatusBar.
     * @return The StatusBar
     */
    public StatusBar getStatusBar() {
        return statusBar;
    }

}
