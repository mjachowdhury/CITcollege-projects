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
package com.swtworkbench.bus.internal;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import com.swtworkbench.bus.IActor;
import com.swtworkbench.bus.Request;
import com.swtworkbench.bus.Result;

/**
 * Class WorkQueueTree.  The Choreographer work queue tree indexes 
 * actors by the Class objects that they're interested in.  The assumption
 * here is that actors are added occasionally, removed infrequently, and
 * called often.  Therefore, add and remove operations are moderately
 * expensive and execute operations are very cheap.<p>
 * 
 * Moreover, Choreographer has the ability for actors to register interest
 * in specific classes.  But if this ability is not used (all actors are
 * interested in all object events), the search time for the interested
 * actors is reduced from n log n to constant time.
 * 
 * @author djo
 */
public class WorkQueueManager implements Serializable {

    // The root actor activation queue
    protected ActivationQueue rootQueue = new ActivationQueue(Object.class);
    
    /**
     * Method initNewQueue.  We just created a new ActivationQueue.  Initialize
     * it with all relevent ActorActivation objects.
     *   
     * @param current The current position in the recursion
     * @param newQ The new Q we're trying to initialize
     */
    private void initNewQueue(ActivationQueue current, ActivationQueue newQ) {
        if (current == newQ) return;
        
        if (current.type.isAssignableFrom(newQ.type)) {
            Iterator i = current.interestedActors.iterator();
            while (i.hasNext()) {
                ActorActivation actor = (ActorActivation) i.next();
                newQ.addActor(actor);
            }
        }
        
        Iterator i = current.subtypes.iterator();
        while (i.hasNext()) {
            ActivationQueue element = (ActivationQueue) i.next();
            initNewQueue(element, newQ);
        }
    }
    
    /**
     * Method createActivationQueue.  Create an activation queue for klass
     * starting searching at `current` in the hierarchy.  If the queue already
     * exists, it is returned.
     *   
     * @param current The current position in the tree
     * @param klass The class to add
     * @return The new/found ActivationQueue
     */
    private ActivationQueue createActivationQueue(ActivationQueue current, Class klass) {
        if (current.type.equals(klass)) return current;
        
        ListIterator i = current.subtypes.listIterator();
        while (i.hasNext()) {
            ActivationQueue element = (ActivationQueue) i.next();
            
            // If we're inserting an interface, make sure we only compare
            // against interfaces, etc.
            if (klass.isInterface() != element.type.isInterface())
                continue;
                
            // If we found an exact match, just return it
            if (klass.equals(element.type)) {
                return element;
            }

            // If klass is a subtype of element, traverse
            if (element.type.isAssignableFrom(klass)) {
                if (!klass.isAssignableFrom(element.type)) {
                    return createActivationQueue(element, klass);
                }
            }

            // If element is a subtype of klass, insert here
            if (klass.isAssignableFrom(element.type)) {
                if (!element.type.isAssignableFrom(klass)) {
                    ActivationQueue newQ = new ActivationQueue(klass);
                    newQ.subtypes.addLast(element);
                    newQ.parent = current;
                    current.subtypes.addLast(newQ);

                    current.subtypes.remove(element);  //**
                    element.parent = newQ;
                    
                    initNewQueue(rootQueue, newQ);
                    return newQ;
                }
            }
        }
        
        // If we got this far, we found a subtype of `current`
        ActivationQueue newQ = new ActivationQueue(klass);
        current.subtypes.addLast(newQ);
        newQ.parent = current;
        
        initNewQueue(rootQueue, newQ);
        return newQ;
    }

    /**
     * Method addToQueues.  Add an actor to all relevent queues.
     * @param current The current queue we're processing
     * @param actor The actor to potentially add
     */
    private void addToQueues(ActivationQueue current, ActorActivation actor) {
        if (actor.interestingType.isAssignableFrom(current.type)) {
            current.addActor(actor);
        }
        
        Iterator i = current.subtypes.iterator();
        while (i.hasNext()) {
            ActivationQueue element = (ActivationQueue) i.next();
            addToQueues(element, actor);
        }
    }

	/**
	 * Method add.  Add an actor to the work queue tree.
     * 
	 * @param interestingType
	 * @param actor
	 * @param priority
	 */
    public void add(Class interestingType, IActor actor, int priority) {
        ActorActivation node = new ActorActivation(interestingType, actor, priority);
        
        // If it's just interested in Object.class, add it to the root queue
        if (interestingType.equals(Object.class)) {
            rootQueue.addActor(node);
            return;
        }
        
        // Make sure the applicable type exists in the work queue tree
        createActivationQueue(rootQueue, interestingType);
        
        // Add the new ActorActivation to the newQ
        addToQueues(rootQueue, node);
    }
    
    /**
     * Method initNewQueue.  We just created a new ActivationQueue.  Initialize
     * it with all relevent ActorActivation objects.<p>
     * 
     * @param current The current position in the recursion
     * @param actor The ActorActivation we're removing
     */
    private void removeActor(ActivationQueue current, Class interestingType, IActor actor) {
        current.removeActor(actor, interestingType);
        
        Iterator i = current.subtypes.iterator();
        while (i.hasNext()) {
            ActivationQueue element = (ActivationQueue) i.next();
            removeActor(element, interestingType, actor);
        }
    }

	/**
	 * Method remove.  Remove an actor from all areas of the work queue tree.<p>
     * 
     * Running time is proportional to (a * q) where a is the number of actors 
     * and q is the number of work queues.
     * 
	 * @param interestingType
	 * @param actor
	 */
    public void remove(Class interestingType, IActor actor) {
        removeActor(rootQueue, interestingType, actor);
    }
    
    /**
     * Method findActivationQueue.  Returns the actor queue that is applicable
     * for event's type.
     * 
     * @param current The current activation queue we're testing.
     * @param actionType The Class of the action we want to occur.
     * @return The ActivationQueue applicable to this action's type
     */
    private ActivationQueue findActivationQueue(ActivationQueue current, Class actionType) {
        Iterator i = current.subtypes.iterator();
        while (i.hasNext()) {
            ActivationQueue subtype = (ActivationQueue) i.next();
            if (subtype.type.isInterface()) 
                continue;
                
            if (subtype.type.isAssignableFrom(actionType)) {
                return findActivationQueue(subtype, actionType);
            }
        }
        /*
         * If we didn't find an activation queue for this specific actionType,
         * we need to create one.  We only do this once per actionType (results
         * are cached).
         */
        if (current.type.equals(actionType))
            return current;
        else
            return createActivationQueue(rootQueue, actionType);
    }
    
    /**
     * Method execute.  Execute some action.<p>  
     * 
     * Searching for the appropriate work queue is worst case O(2(n log n)), best 
     * case O(1), where n is the number of discrete work queues.
     * 
     * @param action
     */
    public Result execute(Object action) throws Exception{
        Request request = new Request(action);
        LinkedList results = new LinkedList();
        
        ActivationQueue runQ = findActivationQueue(rootQueue, action.getClass());
        Iterator i = runQ.interestedActors.iterator();
        while (i.hasNext()) {
            ActorActivation actorActivation = (ActorActivation) i.next();
            Object result = actorActivation.actor.perform(request);
            if (result != null) {
                results.add(result);
            }
            if (request.fulfilled == true)
                break;
        }
        return new Result(results, request.fulfilled);
    }
}



