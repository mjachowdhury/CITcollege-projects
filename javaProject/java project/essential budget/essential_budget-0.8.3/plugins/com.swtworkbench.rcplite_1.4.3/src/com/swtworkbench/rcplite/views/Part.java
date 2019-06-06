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
package com.swtworkbench.rcplite.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import com.swtworkbench.rcplite.Application;
import com.swtworkbench.rcplite.Perspective;
import com.swtworkbench.swtutils.fromeclipse.CompositeFocusEventDispatcher;
import com.swtworkbench.swtutils.fromeclipse.ViewForm;

/**
 * Class Part.  An abstract class implementing common behavior for all UI 
 * elements that can receive focus.
 * 
 * @author daveo
 */
public abstract class Part implements Listener {
    
    /*
     * Define a bunch of standard colors
     */
    protected final Color COLOR_TITLE_BACKGROUND;
    protected final Color COLOR_TITLE_BACKGROUND_GRADIENT;
    protected final Color COLOR_TITLE_FOREGROUND;
    protected final Color COLOR_TITLE_INACTIVE_BACKGROUND;
    protected final Color COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT;
    protected final Color COLOR_TITLE_INACTIVE_FOREGROUND;
    protected final Color COLOR_WIDGET_BACKGROUND;
    protected final Color COLOR_LIST_BACKGROUND;
    protected final Color COLOR_LIST_FOREGROUND;
    
    protected final Color[] activeViewGradient;
    protected final int[] activeViewGradientPercentages = new int[] {50, 100};
    
    protected final Color[] activeEditorGradient;
    protected final int[] activeEditorGradientPercentages = new int[] {50, 100};
    
    protected final Color[] activeNoFocusEditorGradient;
    protected final int[] activeNoFocusEditorGradientPercentages = new int[0];

    protected final Color[] deactivatedViewGradient;
    protected final int[] deactivatedViewGradientPercentages = new int[] {70, 100};
    
    protected final Color[] deactivatedEditorGradient;
    protected final int[] deactivatedEditorGradientPercentages = new int[] {70, 100};

    // The view's actual UI
    protected final ViewForm viewForm;
    
    /**
     * Constructor Part.  Construct a part.
     * 
     * @param parent
     * @param style
     */
    public Part(Composite parent, int style) {
        this.parent = parent;
        
        // Initialize the colors
        Display display = Display.getDefault();
        COLOR_TITLE_BACKGROUND = display.getSystemColor(SWT.COLOR_TITLE_BACKGROUND);
        COLOR_TITLE_BACKGROUND_GRADIENT = display.getSystemColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT);
        COLOR_TITLE_FOREGROUND = display.getSystemColor(SWT.COLOR_TITLE_FOREGROUND);
        COLOR_TITLE_INACTIVE_BACKGROUND = display.getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND);
        COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT = display.getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT);
        COLOR_TITLE_INACTIVE_FOREGROUND = display.getSystemColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND);
        COLOR_WIDGET_BACKGROUND = display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND);
        COLOR_LIST_BACKGROUND = display.getSystemColor(SWT.COLOR_LIST_BACKGROUND);
        COLOR_LIST_FOREGROUND = display.getSystemColor(SWT.COLOR_LIST_FOREGROUND);

        // Define active view gradient using same OS title gradient colors.
        activeViewGradient = new Color[] {COLOR_TITLE_BACKGROUND, COLOR_TITLE_BACKGROUND_GRADIENT, COLOR_WIDGET_BACKGROUND};

        // Define active editor gradient using same OS title gradient colors.
        activeEditorGradient = new Color[] {COLOR_TITLE_BACKGROUND, COLOR_TITLE_BACKGROUND_GRADIENT, COLOR_WIDGET_BACKGROUND};
    
        // Define active no focus editor gradient
        activeNoFocusEditorGradient = new Color[] {COLOR_LIST_BACKGROUND};
    
        // Define view gradient for deactivated window using same OS title gradient colors.
        deactivatedViewGradient = new Color[] {COLOR_TITLE_INACTIVE_BACKGROUND, COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT, COLOR_WIDGET_BACKGROUND};

        // Define editor gradient for deactivated window using same OS title gradient colors.
        deactivatedEditorGradient = new Color[] {COLOR_TITLE_INACTIVE_BACKGROUND, COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT, COLOR_WIDGET_BACKGROUND};
        
        /*
         * Initialize the UI
         */
        viewForm = new ViewForm(parent, style);
        
        Control topLeft = createTopLeft(viewForm);
        viewForm.setTopLeft(topLeft);

        Control topCenter = createTopCenter(viewForm);
        viewForm.setTopCenter(topCenter);
        viewForm.setTopCenterSeparate(false);

        Control topRight = createTopRight(viewForm);
        viewForm.setTopRight(topRight);

        Display.getDefault().asyncExec(new Runnable() {
            public void run() {
                contents = createPartControl(viewForm);
                viewForm.setContent(contents);
            }
        });

        // Find the Perspective from the parent...
        Composite current = parent;
        while (!(current instanceof Perspective) && !(current instanceof Shell)) {
            current = current.getParent();
        }
        if (current instanceof Perspective) {
            perspective = (Perspective) current;
        } else {
            perspective = null;
        }

        /*
         * Add listeners to the appropriate places in the UI
         */
        viewForm.addListener(SWT.Activate, this);
        viewForm.getShell().addListener(SWT.Deactivate, shellListener);
        CompositeFocusEventDispatcher.getDefault().addParentFocusListener(viewForm, focusListener);
    }
    
    protected final Composite parent;
    protected Composite contents;
    protected final Perspective perspective;
    
    /**
     * Set the current perspective to the perspective containing this Part.
     */
    public void focusPartPerspective() {
        Application.getDefault().getPerspectiveManager().select(perspective.getId());
    }
    
    /**
     * Set the current perspective to the perspective containing this Part and
     * set the focus to the control within this Part that most recently had
     * focus.
     */
    public void setFocus() {
        focusPartPerspective();
        Display.getDefault().asyncExec(new Runnable() {
            public void run() {
                focusControl.setFocus();
            }
        });
    }
    
    /**
     * Method getParent.  
     * @return
     */
    public Composite getParent() {
        return parent;
    }

    /**
     * @see Listener
     */
    public void handleEvent(Event event) {
        if (event.type == SWT.Activate)
            onActivate();
    }
    
    /**
     * Method createTopLeft.  Create the top left title bar control.
     * @param parent The SWT parent
     * @return The created control or null if none
     */
    protected abstract Control createTopLeft(Composite parent);
    
    /**
     * Method createTopCenter.  Create the top center title bar control.
     * @param parent The SWT parent control
     * @return The created control or null if none
     */
    protected abstract Control createTopCenter(Composite parent);
    
    /**
     * Method createTopRight.  Create the top right title bar control.
     * @param parent The SWT parent control
     * @return The created control or null if none
     */
    protected abstract Control createTopRight(Composite parent);
    
    /**
     * Method createPartControl.  Create the UI for the part.
     * @param parent The SWT parent control
     * @return The created control or null if none
     */
    protected abstract Composite createPartControl(Composite parent);

    /*
     * Manage the part's focus
     */
    protected static Part focusedPart = null;
    
    /**
     * Method getFocusedPart.  Returns the part that has the focus.
     * @return the part that has the focus.
     */
    public Part getFocusedPart() {
        return focusedPart;
    }
    
    // A part may have the focus, not have the focus or have the focus but 
    // its parent not have the focus
    
    /** The part no longer has the focus */
    public static final int FOCUS_FALSE = 0;
    
    /** The part now has the focus */
    public static final int FOCUS_TRUE = 1;
    
    /** The part has the focus but its parent shell lost the focus */
    public static final int FOCUS_SHELL_LOST = 2; 
    
    protected Control focusControl = null;
    
    // Called when the part itself is activated
    private void onActivate() {
        if (focusedPart != null)
            focusedPart.setActivated(FOCUS_FALSE);
        focusedPart = this;
        setActivated(FOCUS_TRUE);

        if (focusControl != null && !focusControl.isDisposed()) {
            focusControl.setFocus();
        } else if (contents != null){
            setChildFocus(contents);
        }
    }
    
    /**
     * Method setChildFocus.  Give the focus to the first child of control
     * that is willing to accept it.
     * 
     * @param control
     */
    private boolean setChildFocus(Composite control) {
        Control[] children = control.getChildren();
        for (int i = 0; i < children.length; i++) {
            if (children[i].setFocus()) {
                return true;
            } else {
                if (children[i] instanceof Composite && setChildFocus((Composite)children[i]))
                    return true;
            }
        }
        return false;
    }

    // Called when the shell loses focus
    private Listener shellListener = new Listener() {
        public void handleEvent(Event event) {
            if (event.type == SWT.Deactivate) {
                if (focusedPart != null)
                    focusedPart.setActivated(FOCUS_SHELL_LOST);
            }
        }
    };
    
    // Called when anything inside the Part gets focus
    private FocusListener focusListener = new FocusListener() {
        public void focusLost(FocusEvent e) {
        }
        public void focusGained(FocusEvent e) {
            if (e != null && e.widget instanceof Control) {
                focusControl = (Control)e.widget;
                if (perspective != null)
                    perspective.setFocusControl((Control)e.widget);
            }
        }
    };
    
    /**
     * Method setActivated.  Clients must override this method to process
     * focus in/out events.  Normally processing the event will involve 
     * redrawing a title bar, tab folder tab, etc.
     * 
     * @param focused One  of the three constants Part.FOCUS_FALSE,
     * Part.FOCUS_TRUE, or Part.FOCUS_SHELL_LOST
     */
    protected abstract void setActivated(int focused);
    
    /**
     * Method isActivated.  Returns if this Part has the focus.
     * @return
     */
    public boolean isActivated() {
        return this == focusedPart;
    }

    /**
     * Method getFocusControl.  
     * @return
     */
    public Control getFocusControl() {
        return focusControl;
    }

    /**
     * Method setFocusControl.  
     * @param control
     */
    public void setFocusControl(Control control) {
        focusControl = control;
    }

}


