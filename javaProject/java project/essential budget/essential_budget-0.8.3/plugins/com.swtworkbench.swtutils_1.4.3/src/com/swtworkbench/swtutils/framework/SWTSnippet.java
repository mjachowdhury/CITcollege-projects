/*
 * Copyright (c) 2003 Advanced Systems Concepts, Inc.  All rights reserved.
 * This file is made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package com.swtworkbench.swtutils.framework;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Class SWTSnippet. A generic SWT snippet class. Use this class by
 * subclassing it and overriding the setupUI() and optionally the afterClose()
 * methods. In your public static void main(), just create an instance of your
 * subclass. That will automatically start the application, call setupUI() and
 * afterClose() at the appropriate times, and automatically run the SWT event
 * loop.
 * <p>
 * For example, the SWT "Hello World" application would be written as:
 * </p>
 * 
 * <pre>
 * public class Hello extends SWTSnippet {
 *     public void setupUI(Shell parent) {
 *         parent.setLayout(new FillLayout());
 *         new Label(parent, SWT.CENTER).setText(&quot;Hello, world&quot;);
 *     }
 * 
 *     public static void main(String[] args) {
 *         new Hello();
 *     }
 * }
 * </pre>
 * 
 * <p>
 * SWTSnippet is intended for simple one-shot programs where it is important
 * not to add any additional dependencies beyond the SWTSnippet class itself.
 * SWTApplication is useful for real SWT applications that require a logging
 * framework.  SWTApplication uses the SWTUtils meta-logging framework, so 
 * that it can easily be hooked into the logging framework of your choice. 
 * </p>
 * 
 * @author daveo@asc-iseries.com
 */
public abstract class SWTSnippet {
    /**
     * Construct and run an SWT Snippet.
     * <p>
     * This constructor does everything needed to actually construct and run
     * a SWT Snippet application.
     */
    public SWTSnippet() {
        display = new Display();
        shell = new Shell(display);
        
        setupUI(shell);

        // Set the Shell's initial size
        Point defaultSize = getDefaultSize();
        Rectangle displaySize = display.getBounds();
        if (displaySize.width <= defaultSize.x || displaySize.height <= defaultSize.y)
            shell.setMaximized(true);
        else
            shell.setSize(defaultSize);

        shell.open();
        
        runEventLoop();
        
        display.dispose();
        
        afterClose();
    }
    
    protected Display display;
    
    /**
     * @return Returns the display.
     */
    public Display getDisplay() {
        return display;
    }
    
    /**
     * Runs the SWT event loop.  Override this method if you need to add
     * special processing in the SWT event loop.
     */
    protected void runEventLoop() {
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }

    protected Shell shell;
    
    /**
     * @return the application shell
     */
    protected Shell getShell() {
        return shell;
    }

    /**
     * Override/implement this method to set up your UI.
     * 
     * @param parent The SWT parent shell
     */
    protected abstract void setupUI(Shell parent);
     
    /**
     * Override this method to do something after the Shell closes
     * (print results, debug info, etc.).
     */
    protected void afterClose() {
    }

    /**
     * Override this method to set a new default Shell size. The new size can be
     * a constant (the default is Point(800, 600)) or it can be computed or
     * loaded from a preferences area, etc.
     * 
     * @return Point the Shell's default size.
     */
    protected Point getDefaultSize() {
        return new Point(1024, 768);
    }
    
}
