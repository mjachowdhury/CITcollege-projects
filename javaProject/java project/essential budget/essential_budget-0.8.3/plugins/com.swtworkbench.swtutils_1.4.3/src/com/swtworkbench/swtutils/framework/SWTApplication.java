/*
 * Copyright (c) 2003 Advanced Systems Concepts, Inc.  All rights reserved.
 * This file is made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package com.swtworkbench.swtutils.framework;

import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class SWTApplication. A generic SWT application class. Use this class by
 * subclassing and overriding the setupUI() and optionally the afterClose()
 * methods. In your public static void main(), just create an instance of your
 * subclass. That will automatically start the application, call setupUI() and
 * afterClose() at the appropriate times, and automatically run the SWT event
 * loop.
 * <p>
 * The difference between SWTSnippet and SWTApplication is that SWTApplication
 * logs any uncaught exceptions using the SWTUtils meta-logging framework and 
 * provides the application developer the option to have the application 
 * continue or halt.  By default, the application will continue after detecting 
 * an error.
 * </p><p>
 * For example, the SWT "Hello World" application would be written as:
 * </p>
 * 
 * <pre>
 * public class Hello extends SWTApplication {
 *     public void setupUI(Shell parent) {
 *         setExitOnUncaughtException(true);  // Only needed if you need to exit
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
public abstract class SWTApplication extends SWTSnippet {
    
    /*
     * (non-Javadoc)
     * Adds error logging and allows the application to specify if it should
     * exit if an uncaught exception occurs.
     * 
     * @see com.swtworkbench.swtutils.framework.SWTSnippet#runEventLoop()
     */
    protected void runEventLoop() {
        while (!shell.isDisposed()) {
            try {
                if (!display.readAndDispatch())
                    display.sleep();
            } catch (Throwable t) {
                Logger.log().error(t, "Uncaught exception detected in main SWT event loop");
                if (exitOnUncaughtException)
                    break;
            }
        }
    }

    protected boolean exitOnUncaughtException = false;
    
    /**
     * Sets if the application will exit when an uncaught exception occurs
     * (including RuntimeExceptions).  The default is false.
     * 
     * @param exitOnUncaughtException true if the application should exit
     * upon detecting an uncaught exception.  false if the exception should
     * just be logged and the application should continue.
     */
    public void setExitOnUncaughtException(boolean exitOnUncaughtException) {
        this.exitOnUncaughtException = exitOnUncaughtException;
    }
    
    /**
     * Indicates if the application will exit when an uncaught exception
     * occurs (including RuntimeExceptions).
     * 
     * @return Returns the exitOnUncaughtException flag.
     */
    public boolean getExitOnUncaughtException() {
        return exitOnUncaughtException;
    }
}
