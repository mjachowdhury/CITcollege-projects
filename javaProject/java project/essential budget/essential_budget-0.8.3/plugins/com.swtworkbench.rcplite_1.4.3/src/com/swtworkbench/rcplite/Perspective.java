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
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;



/**
 * Class Perspective.  RCPLite's Perspective implementation.<p>
 * 
 * A Perspective is just a Composite that also keeps track of its perspective 
 * ID and name.  UI elements are added to the Perspective just like you would 
 * add any SWT component to any other SWT object.  The PerspectiveManager will 
 * automatically show and hide the Perspective (and all of its contained UI 
 * elements) at the right times.<p>
 * 
 * Perspectives are never constructed directly.  Instead, create a new
 * perspective using the #create() factory method on PerspectiveManager.
 * 
 * @author daveo
 */
public class Perspective extends Composite {

    /**(non-API)
     * Constructor Perspective.  Perspectives are constructed automatically
     * by the #create() factory method on the PerspectiveManager.
     * 
     * @param id The perspective ID
     * @param name The perspective name
     * @param mainWindow The application's main window
     */
    public Perspective(String id, String name, MainWindow mainWindow) {
        super(mainWindow.getContentPane(), SWT.NULL);
        this.id = id;
        this.perspectiveName = name;
        setLayout(new FillLayout());
    }

    private final String id;
    
    /**
     * Method getId.  Returns the perspective's ID.
     * 
     * @return the perspective's ID string
     */
    public String getId() {
        return id;
    }

    private final String perspectiveName;
    
    /**
     * Method getPerspectiveName.  Returns the perspective's name.
     * @return the perspective's name
     */
    public String getPerspectiveName() {
        return perspectiveName;
    }
    
    protected Control focusControl;
    
    /**(non-API)
     * Method getFocusControl.  
     * @return
     */
    public Control getFocusControl() {
        return focusControl;
    }

    /**(non-API)
     * Method setFocusControl.  
     * @param control
     */
    public void setFocusControl(Control control) {
        focusControl = control;
    }

    /* (non-Javadoc)
     * @see org.eclipse.swt.widgets.Composite#setFocus()
     */
    public boolean setFocus() {
        final Control realFocusControl = focusControl;
        boolean result = super.setFocus();
        if (realFocusControl != null && !realFocusControl.isDisposed())
            Display.getDefault().asyncExec(new Runnable() {
                public void run() {
                    realFocusControl.setFocus();
                }
            });
        return result;
    }
    
}
