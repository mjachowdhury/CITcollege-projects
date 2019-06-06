package com.swtworkbench.swtutils.logger;

/*
 * Copyright (c) 2003 Advanced Systems Concepts, Inc.  All rights reserved.
 * This file is made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */

/**
 * Interface ILogger. An interface to the platform's logging mechanism.
 * 
 * @author daveo
 */
public interface ILogger {
	/**
	 * Logger an application error
	 * 
	 * @param t
	 *            The exception object
	 * @param message
	 *            The message to log
	 */
	public abstract void error(Throwable t, String message);

	/**
	 * Method message. Logger a message
	 * 
	 * @param message
	 *            The message to log
	 */
	public abstract void message(String message);

	/**
	 * Method message. Logger a debug message. Only logged if debug mode is on.
	 * 
	 * @param message
	 *            The message to log
	 */
	public abstract void debug(Class subject, String message);

	/**
	 * Method isDebug. Returns true if global debug mode is on. Default = false.
	 * If global debug mode is on, all debug messages are logged, regardless
	 * of the Class subject.
	 * 
	 * @return boolean true if global debug mode is on, false otherwise.
	 */
	public abstract boolean isDebug();

	/**
	 * Method setDebug. Turns global debug mode on or off. If global debug mode
	 * is on, all debug messages are displayed, regardless of the Class subject.
	 * 
	 * @param debugMode
	 */
	public abstract void setDebug(boolean debugMode);
    
    /**
     * Sets debug mode for messages of Class subject.
     * 
     * @param subject The Class for which debug messages should be enabled/disabled
     * @param enabled true if debug messages should be displayed; false otherwise
     */
    public abstract void setDebug(Class subject, boolean enabled);

    /**
     * Returns if debug mode is turned on for messages of Class subject
     * 
     * @param subject The Class of debug messages under consideration 
     * @return true if debug mode is on for the specified Class; false otherwise
     */
    public abstract boolean isDebug(Class subject);
}
