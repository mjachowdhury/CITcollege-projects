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

/**
 * Class ActivationQueue.  
 * 
 * @author daveo
 */
public class ActivationQueue implements Serializable {
    public Class type;
    public ActivationQueue parent = null;
    public LinkedList subtypes = new LinkedList();
        
    // A sorted linked list ordered by actor priority
    public LinkedList interestedActors = new LinkedList();
    
    // This LinkedList contains additional instances of actors listed in
    // interestedActors, but at a lower priority.
    private LinkedList shadowedActors = new LinkedList();
        
    /**
     * Constructor ActivationQueue.  Create an ActivationQueue for a 
     * particular class  
     * 
     * @param klass The Class representing objects managed by this 
     * activation queue
     */
    public ActivationQueue(Class klass) {
        this.type = klass;
    }
        
    /**
     * Method addActor.  Add an ActorActivation to this activation queue.
     * 
     * @param actor the ActorActivation to add
     */
    public void addActor(ActorActivation actor) {
        boolean added = false;
        
        if (interestedActors.isEmpty()) {
            interestedActors.add(actor);
        } else {
            ListIterator i = interestedActors.listIterator();
            while (i.hasNext()) {
                ActorActivation existing = (ActorActivation) i.next();
                
                if (actor.priority > existing.priority && !added) {
                    i.previous();
                    i.add(actor);
                    i.next();
                    added = true;
                }

                if (existing.actor == actor.actor) {
                    if (added) {
                        // We found a duplicate with a lower priority; 
                        // remove it.
                        shadowedActors.add(existing);
                        i.remove();
                    } else {
                        // We found another instance with higher priority;
                        // don't add it a second time into the same queue,
                        // but add it to the shadowedActors list
                        shadowedActors.add(actor);
                        return;
                    }
                }
            }
            if (!added)
                interestedActors.addLast(actor);
        }
    }

    /**
     * Method removeActor.  
     * @param actor
     */
    public void removeActor(IActor actor, Class interestingType) {
        // Remove the requested actor
        Iterator i = interestedActors.iterator();
        while (i.hasNext()) {
            ActorActivation element = (ActorActivation) i.next();
            if (element.actor == actor && element.interestingType.equals(interestingType))
                i.remove();
        }
        
        // See if that actor activation shadowed any others
        i = shadowedActors.iterator();
        ActorActivation bestShadow = null;
        while (i.hasNext()) {
            ActorActivation element = (ActorActivation) i.next();
            if (element.actor == actor) {
                if (bestShadow == null) {
                    bestShadow = element;
                    i.remove();
                } else if (bestShadow.priority < element.priority) {
                    shadowedActors.addFirst(shadowedActors);
                    bestShadow = element;
                    i.remove();
                }
            }
        }
        
        // If there was a shadowed activation, add it back
        if (bestShadow != null)
            addActor(bestShadow);
    }
}


