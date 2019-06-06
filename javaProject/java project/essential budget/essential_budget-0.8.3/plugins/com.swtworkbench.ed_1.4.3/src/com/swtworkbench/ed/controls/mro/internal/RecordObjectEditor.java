/*
 * This file is part of com.swtworkbench.ed.
 *
 * com.swtworkbench.ed is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License
 * as published by the Free Software Foundation.
 *
 * com.swtworkbench.ed is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with com.swtworkbench.ed; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.swtworkbench.ed.controls.mro.internal;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import com.swtworkbench.ed.aware.adapters.pojo.POJOFieldAdapter;
import com.swtworkbench.ed.aware.adapters.pojo.POJOObjectAdapter;
import com.swtworkbench.ed.aware.interfaces.IRecordObjectAdapter;
import com.swtworkbench.ed.aware.interfaces.IRecordValidListener;

/**
 * Class RecordObjectEditor. Manages the SWT editor objects corresponding to a
 * single IRecordObjectAdapter in a MRO. Expects each SWT control that is to be
 * bound to a data element to have a Data property called "boundTo" containing
 * the name of the property to which the control should be bound.
 * <p>
 * Forwards events from the individual SWT controls to the containing MRO.
 * </p>
 * 
 * @author daveo
 */
public class RecordObjectEditor {
    
    private final RecordObjectEditor sender;
    
    public RecordObjectEditor(MRO parent) {
        this.sender = this;
        this.parent = parent;
        this.childControl = parent.getPartControlFactory().createPartControl(parent.getControl());
        objectAdapter = new POJOObjectAdapter();
        objectAdapter.addRecordValidListener(recordValidListener);
        Control[] children = childControl.getChildren();
        for (int i = 0; i < children.length; i++) {
            Control childControl = children[i];
            childControl.addFocusListener(focusListener);
            childControl.addKeyListener(keyListener);
            childControl.addTraverseListener(traverseListener);
            childControl.addDisposeListener(disposeListener);
            
            String propertyName = (String) childControl.getData("boundTo");
            if (propertyName != null) {
                objectAdapter.add(new POJOFieldAdapter(childControl, propertyName));
            }
        }
    }
    
    private final MRO parent;
    private final Composite childControl;
    private final IRecordObjectAdapter objectAdapter;

    /**
     * Returns the element number of the specified control.
     * 
     * @param control The control to find.
     * @return It's element offset or -1 if not found.
     */
    protected int getChildElementNum(Control control) {
        if (childControl.isDisposed())
            return -1;
        
        Control[] children = childControl.getChildren();
        for (int i = 0; i < children.length; i++) {
            if (children[i] == control)
                return i;
        }
        
        return -1;
    }
    
    /**
     * Returns the control for a specified column.
     * @param childColNum The column number of the desired control.
     * @return The corresponding control.
     */
    public Control getCellElement(int childColNum) {
        Control[] children = childControl.getChildren();
        return children[childColNum];
    }
    
    /**
     * Returns the part control
     * @return the part control
     */
    public Control getControl() {
        return childControl;
    }
    
    /**
     * @return Returns the objectAdapter.
     */
    public IRecordObjectAdapter getObjectAdapter() {
        return objectAdapter;
    }

    private IRecordValidListener recordValidListener = new IRecordValidListener() {
        public void isValid(boolean isValid) {
            parent.elementValid(sender, isValid);
        }
    };
    private FocusListener focusListener = new FocusListener() {
        public void focusGained(final FocusEvent e) {
            Display.getDefault().asyncExec(new Runnable() {
                public void run() {
                    parent.elementFocusGained(sender, e);
                }
            });
        }
        public void focusLost(final FocusEvent e) {
            Display.getDefault().asyncExec(new Runnable() {
                public void run() {
                    parent.elementFocusLost(sender, e);
                }
            });
        }
    };
    private KeyListener keyListener = new KeyListener() {
        public void keyPressed(final KeyEvent e) {
            Display.getDefault().asyncExec(new Runnable() {
                public void run() {
                    parent.elementKeyPressed(sender, e);
                }
            });
        }
        public void keyReleased(final KeyEvent e) {
            Display.getDefault().asyncExec(new Runnable() {
                public void run() {
                    parent.elementKeyReleased(sender, e);
                }
            });
        }
    };
    private TraverseListener traverseListener = new TraverseListener() {
        public void keyTraversed(final TraverseEvent e) {
            Display.getDefault().asyncExec(new Runnable() {
                public void run() {
                    parent.elementKeyTraversed(sender, e);
                }
            });
        }
    };
    private DisposeListener disposeListener = new DisposeListener() {
        public void widgetDisposed(DisposeEvent e) {
            Control control = (Control) e.widget;
            control.removeFocusListener(focusListener);
            control.removeKeyListener(keyListener);
            control.removeTraverseListener(traverseListener);
        }
    };
}

