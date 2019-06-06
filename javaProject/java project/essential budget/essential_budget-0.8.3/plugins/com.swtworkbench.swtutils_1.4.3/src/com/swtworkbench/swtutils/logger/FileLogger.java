/*******************************************************************************
 * Copyright (c) 2000, 2003 Advanced Systems Concepts, Inc.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     David Orme (ASC) - Initial implementation
 ******************************************************************************/
package com.swtworkbench.swtutils.logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

/**
 * This class logs messages to the OutputStream that is passed to its constructor.
 * 
 * @author daveo
 */
public class FileLogger extends AbstractLogger {
    private OutputStream logFileStream;
    private PrintWriter logFile;
    
    /**
     * Construct a LogFile logger, passing it an OutputStream on which to
     * log.
     * 
     * @param logFile The OutputStream on which to log.
     */
    public FileLogger(OutputStream logFile) {
        this.logFileStream = logFile;
        this.logFile = new PrintWriter(logFile);
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.swtutils.logger.ILogger#error(java.lang.Throwable, java.lang.String)
     */
    public synchronized void error(Throwable t, String message) {
        logFile.println("ERROR: " + message);
        logFile.println(t.toString());
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.swtutils.logger.ILogger#message(java.lang.String)
     */
    public synchronized void message(String message) {
        logFile.println("WARNING: " + message);
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.swtutils.logger.ILogger#debug(java.lang.Class, java.lang.String)
     */
    public synchronized void debug(Class subject, String message) {
        if (isDebug(subject)) {
            logFile.println(subject.getName() + ": " + message);
        }
    }
    
    /**
     * Close the underlying OutputStream object.
     * 
     * @throws IOException if closing the file failed.
     */
    public void close() throws IOException {
        debug(FileLogger.class, "=== SHUTDOWN === : " + new Date());
        logFile.flush();
        logFileStream.close();
    }
    
    /**
     * Set up the Logger to use a FileLogger using the specified logFile.
     * 
     * @param logFile The (String) name of the log file
     * @throws IOException if the log file could not be opened
     */
    public static FileLogger configure(String logFile) throws IOException {
        OutputStream logFileStream = new FileOutputStream(logFile, true);
        FileLogger result = new FileLogger(logFileStream);
        result.setDebug(FileLogger.class, true);
        result.debug(FileLogger.class, "=== STARTUP ===  : " + new Date());
        Logger.setLogger(result);
        return result;
    }

    /**
     * Set up the Logger to use a FileLogger using the specified logFile.
     * If controlFile is present, it will be read and logging verbosity will
     * be specified accordingly.  The verbosity options in the control file are:
     * <ul>
     * <li>"*" on any line turns on global logging--maximum verbosity
     * <li>A fully-qualified class name on a line turns on logging just for that class
     * </ul>
     * 
     * @param logFile The (String) name of the log file
     * @param controlFile The (String) name of the optional control file
     * @throws IOException if the log file could not be opened
     */
    public static FileLogger configure(String logFile, String controlFile) throws IOException {
        FileLogger result = configure(logFile);
        BufferedReader controlFileReader = null;
        try {
            controlFileReader = new BufferedReader(new FileReader(controlFile));
        } catch (FileNotFoundException e) {}
        if (controlFileReader != null) {
            String toDebug;
            while ((toDebug = controlFileReader.readLine()) != null) {
                if (toDebug.equals("*")) {
                    result.setDebug(true);
                    continue;
                }
                try {
                    Class debugClass = Class.forName(toDebug);
                    result.setDebug(debugClass, true);
                } catch (ClassNotFoundException e1) {
                    result.error(e1, "Unable to turn on debugging for class: " + toDebug);
                }
            }
            controlFileReader.close();
        }
        return result;
    }
}
