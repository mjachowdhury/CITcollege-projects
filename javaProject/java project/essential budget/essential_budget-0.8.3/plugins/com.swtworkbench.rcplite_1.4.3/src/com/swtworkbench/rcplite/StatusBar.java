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
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.swtworkbench.rcplite.internal.PerspectiveChanger;

/**
 * Class StatusBar.  The RCPLite status bar implementation.
 * 
 * @author daveo
 */
public class StatusBar extends Composite {
    public StatusBar(Composite parent, int style) {
        super(parent, style);
        GridLayout l = new GridLayout(2, false);
        l.marginHeight = 0;
        l.marginWidth = 0;
        setLayout(l);
        
        perspectiveChanger = new PerspectiveChanger(this, SWT.NULL);
        perspectiveChanger.setLayoutData(new GridData(GridData.BEGINNING));
        
        messageArea = new CLabel(this, SWT.SHADOW_IN);
        messageArea.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    }
    
    private CLabel messageArea;
    
    /**
     * Method setMessage.  Sets the message in the message area.
     * 
     * @param message
     */
    public void setMessage(String message) {
        messageArea.setText(message);
    }
    
    /**
     * Method clearMessage.  Clears any message in the message area.
     */
    public void clearMessage() {
        messageArea.setText("");
    }

    PerspectiveChanger perspectiveChanger;
    
    /** (non-API) */
    public void addPerspective(String id, String name, Image icon) {
        perspectiveChanger.add(id, name, icon);
    }
    
    /** (non-API) */
    public void removePerspective(String id) {
        perspectiveChanger.remove(id);
    }
    
    /** (non-API) */
    public void selectPerspective(String id) {
        perspectiveChanger.select(id);
    }
    
    /** (non-API) */
    public void addPerspectiveChangeListener(IPerspectiveChangeListener l) {
        perspectiveChanger.addChangeListener(l);
    }
    
    /** (non-API) */
    public void removePerspectiveChangeListener(IPerspectiveChangeListener l) {
        perspectiveChanger.removeChangeListener(l);
    }
}

