/*
 * This file is part of com.swtworkbench.swtutils.
 *
 * com.swtworkbench.swtutils is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License
 * as published by the Free Software Foundation.
 *
 * com.swtworkbench.swtutils is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with com.swtworkbench.swtutils; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.swtworkbench.swtutils.logger;

/**
 * A TeeLogger allows the logger to log to two separate loggers at once as
 * specified in the constructor.  This makes it possible to log to, for example,
 * a FileLogger and a StdLogger simultaneously.
 * 
 * @author daveo
 */
public class TeeLogger extends AbstractLogger {
    
    private ILogger l1, l2;
    
    /**
     * Construct a TeeLogger, specifying two other loggers that will actually
     * be used to log events.
     * 
     * @param logger1 The first logger to use
     * @param logger2 The second logger to use
     */
    public TeeLogger(ILogger logger1, ILogger logger2) {
        l1=logger1;
        l2=logger2;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.swtutils.logger.ILogger#error(java.lang.Throwable, java.lang.String)
     */
    public void error(Throwable t, String message) {
        l1.error(t, message);
        l2.error(t, message);
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.swtutils.logger.ILogger#message(java.lang.String)
     */
    public void message(String message) {
        l1.message(message);
        l2.message(message);
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.swtutils.logger.ILogger#debug(java.lang.Class, java.lang.String)
     */
    public void debug(Class subject, String message) {
        // Allow each individual logger's debug policy to rule here...
        l1.debug(subject, message);
        l2.debug(subject, message);
    }
    
    // This allows clients to control debug policies of the tee'd loggers...
    
    /**
     * @return Returns the l1.
     */
    public ILogger getL1() {
        return l1;
    }

    /**
     * @return Returns the l2.
     */
    public ILogger getL2() {
        return l2;
    }
}
