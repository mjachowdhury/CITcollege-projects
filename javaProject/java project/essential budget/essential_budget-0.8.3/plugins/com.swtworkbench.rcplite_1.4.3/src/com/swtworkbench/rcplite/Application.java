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

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.swtworkbench.bus.Choreographer;
import com.swtworkbench.rcplite.actions.ActionManager;
import com.swtworkbench.swtutils.framework.SWTApplication;
import com.swtworkbench.swtutils.fromeclipse.CompositeFocusEventDispatcher;

/**
 * Class Application.  The main application class.  This is the root object
 * in an RCPLite application's object graph.<p>
 * 
 * An RCPLite application is constructed by extending Application, and providing
 * two additional ingredients:<p>
 * 
 * <ol>
 * <li>Implement the abstract #initialize() method, which is where the 
 * application UI should be constructed, database connections established, etc.
 * 
 * <li>Create a "public static void main" that does nothing but create an 
 * instance of the descendant class.  This will automatically call
 * Application's 0-arg constructor which will create the main window, call
 * the #initialize() method mentioned above, and run the SWT event loop.
 * </ol>
 * 
 * The Application object provides a singleton reference (Application.getDefault())
 * to the most recently created Application object in the entire program
 * (which is normally the only Application object).  This singleton can
 * then be used from anywhere to get a handle to the ActionManager,
 * MainWindow, or PerspectiveManager objects.<p>
 * 
 * @author daveo
 */
public abstract class Application extends SWTApplication {
    /**
     * The Application singleton
     */
    private static Application application;
    
    /**
     * Method getDefault.  Provides a reference to the current application object.
     * @return The Application object
     */
    public static Application getDefault() {
        if (application == null)
            throw new IllegalArgumentException("The application object doesn't exist yet!");
        return application;
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.swtutils.framework.SWTSnippet#setupUI(org.eclipse.swt.widgets.Shell)
     */
    protected void setupUI(Shell parent) {
        application = this;
        choreographer = new Choreographer();
        new CompositeFocusEventDispatcher(getDisplay()); 
        mainWindow = new MainWindow(getShell());
        perspectiveManager = new PerspectiveManager(mainWindow);
        actionManager = new ActionManager();
        initialize();
        actionManager.buildUI();
        Display.getDefault().asyncExec(new Runnable() {
            public void run() {
                perspectiveManager.select(perspectiveManager.getCurrentId());
            }
        });
    }
    
    private MainWindow mainWindow;
    
    /**
     * Method initialize.  Clients must override this method in order to provide
     * their custom functionality.  Typically this would mean adding action sets
     * and perspectives, opening database connections, etc.
     */
    protected abstract void initialize();
    

    private PerspectiveManager perspectiveManager;
    
    /**
     * Method getPerspectiveManager.  Returns the PerspectiveManger
     * @return The PerspectiveManager
     */
    public PerspectiveManager getPerspectiveManager() {
        return perspectiveManager;
    }
    
    public static PerspectiveManager perspectiveManager() {
        return getDefault().getPerspectiveManager();
    }
    

    private ActionManager actionManager;
    
    /**
     * Method getActionManager.  Returns the ActionManager
     * @return The ActionManager
     */
    public ActionManager getActionManager() {
        return actionManager;
    }
    
    public static ActionManager actionManager() {
        return getDefault().getActionManager();
    }

    /**
     * Method getMainWindow.  
     * @return the application's MainWindow
     */
    public MainWindow getMainWindow() {
        return mainWindow;
    }
    
    public static MainWindow mainWindow() {
        return getDefault().getMainWindow();
    }
    
    private Choreographer choreographer;
    
    /**
     * Returns the application UI's Choreographer bus.
     * <p>
     * <b>NOTE!</b>
     * <p>
     * It is a *big mistake* to use this Choreographer bus for your application's
     * model to communicate!  This bus should be used by controller elements
     * of your application's UI only!
     * <p>
     * Your application's model object should create its own Choreographer
     * (or other callback mechanism, such as EMF objects) and persist that.
     * Otherwise, data that belongs to your application's controllers will
     * be persisted along with your application's data, causing your application's
     * data store to break whenever the application's code changes.
     */
    public Choreographer getChoreographer() {
        return choreographer;
    }
    
    public static Choreographer choreographer() {
        return getDefault().getChoreographer();
    }

}


