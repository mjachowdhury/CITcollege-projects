/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License as 
 * published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * 
 * If you would like to license this software for closed-source use,
 * please see http://www.swtworkbench.com/home/license_form.shtml
 * for details.
 */
package com.swtworkbench.rcplite.example;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.swtworkbench.bus.IActor;
import com.swtworkbench.bus.Request;
import com.swtworkbench.rcplite.Application;
import com.swtworkbench.rcplite.Perspective;
import com.swtworkbench.rcplite.PerspectiveManager;
import com.swtworkbench.rcplite.actions.Action;
import com.swtworkbench.rcplite.actions.ActionManager;
import com.swtworkbench.rcplite.actions.MenuPos;
import com.swtworkbench.rcplite.views.View;
import com.swtworkbench.rcplite.views.ViewFolder;
import com.swtworkbench.rcplite.views.ViewFolderPage;

/**
 * Class HelloRCPLite.  A Hello, RCPLite application that illustrates how
 * to use all of RCPLite's basic features.<p>
 * 
 * Normally you wouldn't put an entire RCPLite application into a single
 * class like this, but we've done it this way to show all the elements
 * of an RCPLite application in one place.
 * 
 * @author daveo
 */
public class HelloRCPLite extends Application {
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.demo.framework.Application#initialize()
     */
    protected void initialize() {
        // Initialize the main application shell.  Note that the application's
        // general layout--tool bar, menu bar, perspective area, status bar, etc.
        // are automatically created by the framework.
        Shell shell = getMainWindow().getShell();
        Display display = Display.getDefault();
        Display.setAppName("Hello, RCPLite");
        shell.setText("Hello, RCPLite");
        shell.setImage(new Image(display, getClass().getResourceAsStream("x.gif")));
        
        // Create and initialize all perspectives/views
        PerspectiveManager pm = getPerspectiveManager();
        
        // In RCPLite, a perspective is just a Composite in a stack.  You
        // add your views, SashForms, etc., to it manually just like you 
        // would if you were manually building a UI.
        Perspective perspective1 = pm.create("perspective1id", "&Perspective1", 
            new Image(display, getClass().getResourceAsStream("x.gif")) );
        SashForm s = new SashForm(perspective1, SWT.HORIZONTAL);

        // Normally you'll extend View and make your own View class rather
        // than use anonymous inner classes like this...
        new View(s, new Image(display, 
                getClass().getResourceAsStream("x.gif")), "View 1") {
            protected Composite createPartControl(Composite parent) {
                return new Composite(parent, SWT.NULL);
            }
        };
        new View(s, new Image(display, 
                getClass().getResourceAsStream("x.gif")), "View 2") {
            protected Composite createPartControl(Composite parent) {
                return new Composite(parent, SWT.NULL);
            }
        };
        s.setWeights(new int[]{50, 50});

        // Let's make a perspective that uses stacked views...
        // Again, one would normally extend ViewFolderPage to do this, but
        // for simplicity of illustration we'll use anonymouse inner classes.
        Perspective perspective2 = pm.create("perspective2id", "P&erspective2", 
            new Image(display, getClass().getResourceAsStream("x.gif")) );
        ViewFolder folder = new ViewFolder(perspective2);
        new ViewFolderPage(folder, new Image(display, 
                getClass().getResourceAsStream("x.gif")), "View 1") {
            protected Composite createPartControl(Composite parent) {
                return new Composite(parent, SWT.NULL);
            }
        };
        new ViewFolderPage(folder, new Image(display, 
                getClass().getResourceAsStream("x.gif")), "View 2") {
            protected Composite createPartControl(Composite parent) {
                return new Composite(parent, SWT.NULL);
            }
        };
        new ViewFolderPage(folder, new Image(display, 
                getClass().getResourceAsStream("x.gif")), "View 3") {
            protected Composite createPartControl(Composite parent) {
                return new Composite(parent, SWT.NULL);
            }
        };
        

        // Create and hook all global actions--that will appear in the menu bar
        // and on the tool bar.
        ActionManager actions = getActionManager();
        
        actions.add(new Action("File", null, null, null, 
            new MenuPos("File", "&File")));
        actions.add(new Action("FileSave", 
            new Image(display, getClass().getResourceAsStream("save_edit_e.gif")), 
            new Image(display, getClass().getResourceAsStream("save_edit_g.gif")), 
            "File",
            new MenuPos("File",
            new MenuPos("Save", "&Save"))));
        actions.add(new Action("FileSep1", null, null, null,
            new MenuPos("File",
            new MenuPos("Sep1", "-"))));
        actions.add(new Action("FileExit", null, null, null,
            new MenuPos("File",
            new MenuPos("Exit", "E&xit"))));
            
        actions.add(new Action("Help", null, null, null, 
            new MenuPos("Help", "&Help")));
        actions.add(new Action("HelpAbout", null, null, null,
            new MenuPos("Help",
            new MenuPos("About", "&About"))));
            
        // We can enable/disable all instances of an action in the UI in one call...
        actions.setEnabled("FileSave", false);
        
        
        // Listen to menu bar and tool bar action events
        Application.choreographer().register(new IActor() {
            public Object perform(Request r) throws Exception {
                // Get the menu bar or tool bar action that was invoked
                Action action = (Action) r.action;
        
                // Do something based on which action object was invoked
                if (action.getId().equals("FileExit")) {
                    Application.getDefault().getMainWindow().getShell().close();
                    r.fulfilled = true;
                } else if (action.getId().equals("FileSave")) {
                    // Invoke save operation here...
                } else
                    System.out.println(action.getId());
                return null;
            }
        }, Action.class);
        
        
        // Hook up the status bar to Essential Data's message bar event source
//        MessageSource.getDefault().addMessageListener(new IMessageListener() {
//            public void message(String message) {
//                Application.getDefault().getMainWindow().getStatusBar().setMessage(message);
//            }
//        });
    }
    
    public static void main(String[] args) {
        new HelloRCPLite();
    }
}
