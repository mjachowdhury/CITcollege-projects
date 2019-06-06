package com.swtworkbench.swtutils.fromeclipse;

/*
 * Copyright (c) 2003 tlinsay, David Orme.  All rights reserved.
 * This file is made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * Class CompositeFocusEventDispatcher.  Allows listeners to receive any focus
 * events that happen to any child of an arbitrary Composite.  The API is 
 * based on work originally done by tlindsay and contributed to the Eclipse
 * newsgroups.  It has been reworked and largely rewritten for Eclipse 3.0 by 
 * David Orme.
 * 
 * @author tlindsay
 * @author daveo@asc-iseries.com - Rewrote nearly everything for Eclipse 3.0 APIs.
 *  
 * This class is under the terms of the CPL according to the Eclipse web site
 * terms of use (I (daveo@asc-iseries.com) found it on the Eclipse newsgroup).
 */
public class CompositeFocusEventDispatcher implements Listener {
    private static CompositeFocusEventDispatcher singleton=null;

    /**
     * A singleton for the common case where there is exactly one Display
     * in the application.
     * 
     * @return The default CompositeFocusEventDispatcher
     */
    public static CompositeFocusEventDispatcher getDefault() {
        if (singleton==null) {
            new CompositeFocusEventDispatcher();
        }
        return singleton;
    }

    private Display display;
    private Map parentFocusListeners = new HashMap();

    /**
     * Construct a CompositeFocusEventDispatcher that dispatches events for a specific
     * shell.
     * 
     * @param shell The shell for which this CompositeFocusEventDispatcher will dispatch events
     */
    public CompositeFocusEventDispatcher(Display display) {
        singleton = this;
        this.display = display;
        display.addFilter(SWT.FocusIn, this);
        display.addFilter(SWT.FocusOut, this);
    }
    
    /**
     * Construct a CompositeFocusEventDispatcher on the default display.
     */
    public CompositeFocusEventDispatcher() {
        singleton = this;
        this.display = Display.getDefault();
        display.addFilter(SWT.FocusIn, this);
        display.addFilter(SWT.FocusOut, this);
    }

    /**
     * Method addParentFocusListener.  Add a focus listener to some arbitrary
     * Composite.  The focus listener will receive all focus events for the
     * Composite and any children it has in the inheritence hierarchy.
     * 
     * @param parent The parent composite
     * @param focusListener The focus listener
     */
    public void addParentFocusListener(Composite parent, FocusListener focusListener) {
        parentFocusListeners.put(parent, focusListener);
    }

    /*
     * (non-api)
     * Returns if the specified Control is a child control of the specified
     * Composite.
     * 
     * @param parent The parent Composite
     * @param child The child Control
     * @return true if child is a child control of parent; false otherwise. 
     */
    private boolean isChild(Composite parent, Control child) {
        Control children[] = parent.getChildren();

        for (int i = 0; i < children.length; i++) {
            Control c = children[i];

            if (child == c)
                return true;

            if (c instanceof Composite && isChild((Composite) c, child))
                return true;
        }
        return false;
    }

    /* (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	public void handleEvent(Event event) {
		FocusEvent focusEvent = new FocusEvent(event);
        switch (event.type) {
            case SWT.FocusIn:
                for (Iterator i = parentFocusListeners.keySet().iterator(); i.hasNext();) {
                    Composite parent = (Composite) i.next();
    
                    if (isChild(parent, (Control)event.widget)) {
                        FocusListener fl =
                            (FocusListener) parentFocusListeners.get(parent);
                        fl.focusGained(focusEvent);
                    }
                }
                break;
            case SWT.FocusOut:
                for (Iterator i = parentFocusListeners.keySet().iterator(); i.hasNext();) {
                    Composite parent = (Composite) i.next();
    
                    if (isChild(parent, (Control)event.widget)) {
                        FocusListener fl =
                            (FocusListener) parentFocusListeners.get(parent);
                        fl.focusLost(focusEvent);
                    }
                }
                break;
        }
	}
    
}
