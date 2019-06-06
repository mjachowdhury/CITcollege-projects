/*
 * This file is part of com.swtworkbench.bus.
 *
 * com.swtworkbench.bus is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License
 * as published by the Free Software Foundation.
 *
 * com.swtworkbench.bus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with com.swtworkbench.bus; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.swtworkbench.bus.netbridge;


/**
 * Class ISynchronizer.  Represents a class that can run an arbitrary 
 * RunnableWithResult while satisfying multithreading constraints.<p>
 * 
 * For example, Runnables that execute SWT code must be executed on the 
 * SWT thread.  A SWT implementation would permit the execution to be delegated 
 * to the SWT thread.<p>
 * 
 * In the context of Choreographer, the ISynchronizer is what implements
 * the synchronization policy of each individual application as follows:<p>
 *   
 * Each application using the Choreographer netbridge must provide
 * an ISynchronizer in order to answer the policy question: "on what 
 * thread should remote events be executed?"  As was described above, 
 * in a SWT application, this is likely to be the SWT thread.  In that case, 
 * the application would provide an ISynchronizer that uses SWT's 
 * Display.getDefault().asyncExec() method to run the RunnableWithResult 
 * on the SWT thread.<p>
 * 
 * It is recommended to implement the inter-thread communication required
 * by this interface using Doug Lea's util.concurrent library.
 * 
 * @author daveo
 */
public interface ISynchronizer {

    /**
     * Method execute.  Execute the specified RunnableWithResult on the desired
     * thread.<p>
     * 
     * If the RunnableWithResult threw an exception, re-throw it.  Otherwise 
     * return the result.
     * 
     * @param r The RunnableWithResult to run
     * @return The object returned by the RunnableWithResult
     * @throws Exception if Something Bad Happened
     */
    Object execute(RunnableWithResult r) throws Exception;

}
