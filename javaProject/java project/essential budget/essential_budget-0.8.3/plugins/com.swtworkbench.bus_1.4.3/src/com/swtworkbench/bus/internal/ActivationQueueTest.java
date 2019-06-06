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

import java.util.Iterator;
import java.util.StringTokenizer;

import junit.framework.TestCase;

import com.swtworkbench.bus.IActor;
import com.swtworkbench.bus.Request;

/**
 * Class ActivationQueueTest.  
 * 
 * @author daveo
 */
public class ActivationQueueTest extends TestCase {

    /**
     * Constructor for ActivationQueueTest.
     * @param name
     */
    public ActivationQueueTest(String name) {
        super(name);
    }
    
    private class Actor implements IActor {
        public Object perform(Request action) {
            return null;
        }
    };

    public void testNewActivationQueue() {
        ActivationQueue q = new ActivationQueue(Object.class);
        assertEquals("interestedActors.isEmpty() != true", q.interestedActors.isEmpty(), true);
        assertEquals("Root activation queue thinks it has a parent", q.parent == null, true);
        assertTrue("The root object type should be Object.class", q.type.equals(Object.class));
    }
    
    private void checkOrder(ActivationQueue q, String order) {
        StringTokenizer t = new StringTokenizer(order, " ,");
        Iterator i = q.interestedActors.iterator();
        String got = "";
        while (i.hasNext() && t.hasMoreTokens()) {
            ActorActivation element = (ActorActivation) i.next();
            int expected = Integer.parseInt(t.nextToken());
            got += Integer.toString(element.priority) + " ";
            assertEquals("Expected: (" + order + "); got: (" + got + "...", expected, element.priority);
        }

        assertEquals("Too many elements in Q", i.hasNext(), false);
        assertEquals("Not enough elements in Q", t.hasMoreTokens(), false);
    }

    public void testAddActor() {
        ActivationQueue q = new ActivationQueue(Object.class);
        Actor a = new Actor();
        q.addActor(new ActorActivation(Object.class, a, 5));
        assertEquals("The first object didn't make it into the Q", q.interestedActors.size(), 1);
        checkOrder(q, "5");
        q.addActor(new ActorActivation(Object.class, a, 6));
        checkOrder(q, "6");
        q.addActor(new ActorActivation(Object.class, a, 4));
        checkOrder(q, "6");
        q.addActor(new ActorActivation(Object.class, new Actor(), 9));
        checkOrder(q, "9, 6");
        q.addActor(new ActorActivation(Object.class, new Actor(), 4));
        checkOrder(q, "9, 6, 4");
        q.addActor(new ActorActivation(Object.class, a, 2));
        checkOrder(q, "9, 6, 4");
        q.addActor(new ActorActivation(Object.class, a, 1));
        checkOrder(q, "9, 6, 4");
        q.addActor(new ActorActivation(Object.class, new Actor(), 1));
        checkOrder(q, "9, 6, 4, 1");
        q.addActor(new ActorActivation(Object.class, a, 8));
        checkOrder(q, "9, 8, 4, 1");
        q.addActor(new ActorActivation(Object.class, new Actor(), 2));
        checkOrder(q, "9, 8, 4, 2, 1");
        q.addActor(new ActorActivation(Object.class, a, 13));
        checkOrder(q, "13, 9, 4, 2, 1");
    }

}
