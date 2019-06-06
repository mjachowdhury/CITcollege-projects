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
package com.swtworkbench.bus;

import java.io.Serializable;

import com.swtworkbench.bus.internal.WorkQueueManager;
import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class Choreographer.  A new take on messaging busses:<p>
 * <ul>
 *   <li>Separates the application bus from the transport(s) used
 *   <li>Any old Java object can be a message (action) (uses POJOs)
 *   <li>The Java inheritence/interface hierarchies define topics/subtopics, etc.
 *   <li>Efficient message (action) dispatching
 *   <li>Includes separate priority levels for separating event/message types
 * </ul>
 * 
 * Although this class synchronizes accesses to its data structures, it does not
 * in itself implement a separate background message-handling thread because
 * different applications may have different threading requirements.  For example,
 * some applications may want to run everything in the same thread.  Other
 * applications will have an actor sitting on a Choreographer bus acting as
 * a bridge between different Choreographer busses in different applications.
 * In this case, the actor will probably have a background thread for servicing
 * network connections.  Other applications may want to run a Choreographer
 * bus in a background thread altogether.  The implementation goal is to 
 * enable all of these potential threading models without requiring the 
 * single-threaded application to pay for a threading model it doesn't use
 * above and beyond what it would pay anyway to use a thread-safe library.
 * 
 * @author djo
 */
public class Choreographer implements Serializable {
    
    protected WorkQueueManager workQueueManager = new WorkQueueManager();
    
    /**
     * Method register.  Same as:<p>
     * 
     * register(actor, Object.class, 0);
     * 
     * @param actor The actor to register
     */
    public void register(IActor actor) {
        register(actor, Object.class, 0);
    }
    
    /**
     * Method register.  Same as:<p>
     * 
     * register(actor, Object.class, priority);
     * @param actor The actor to register
     * @param priority The priority level at which to register the actor
     */
    public void registerActor(IActor actor, int priority) {
        register(actor, Object.class, priority);
    }

    /**
     * Method register.  Equivalent to:<p>
     * 
     * register(actor, interestingActions, 0);
     * 
     * @param actor The actor 
     * @param interestingActions a Class describing action objects of interest
     */
    public void register(IActor actor, Class interestingActions) {
        register(actor, interestingActions, 0);
    }
    
    /**
     * Method register.  Register an actor that is interested in performing 
     * actions that are an instanceof interestingActions
     * 
     * @param actor The IActor
     * @param interestingActions a Class describing action objects of interest
     * @param priority The priority this actor gets when handling actions of 
     *                  the specified type
     */
    public synchronized void register(IActor actor, Class interestingActions, int priority) {
        workQueueManager.add(interestingActions, actor, priority);
    }
    
    /**
     * Method unregisterActor.  Express disinterest in some actor continuing 
     * to receive a certain class of events 
     * 
     * @param actor The actor to unregister (must be the exact actor object)
     * @param interestingAction The interestingAction class that is no longer interesting
     */
    public synchronized void unregisterActor(IActor actor, Class interestingAction) {
        workQueueManager.remove(interestingAction, actor);
    }

    /**
     * Method request.  Execute a request.
     * 
     * @param action the action object to request
     * @return LinkedList any result object(s).
     * @throws Exception if Something Bad Happened.
     */
    public synchronized Result request(Object action) throws Exception {
        return workQueueManager.execute(action);
    }
    
    /**
     * Method requestl.  Execute a request, logging any exception that occurs.
     * 
     * @param action The action to request
     * @return LinkedList any result object(s).
     */
    public Result requestl(Object action) {
        try {
            return request(action);
        } catch (Exception e) {
            Logger.log().error(e, "Exception occured while processing " + action.getClass().getName());
        }
        return null;
    }
}


