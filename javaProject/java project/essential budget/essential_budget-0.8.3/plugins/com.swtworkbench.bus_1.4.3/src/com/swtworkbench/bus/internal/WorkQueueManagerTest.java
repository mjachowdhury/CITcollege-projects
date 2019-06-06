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

import junit.framework.TestCase;

import com.swtworkbench.bus.IActor;
import com.swtworkbench.bus.IPriority;
import com.swtworkbench.bus.Request;

/**
 * Class WorkQueueManagerTest.  
 * 
 * @author daveo
 */
public class WorkQueueManagerTest extends TestCase {

    public WorkQueueManagerTest(String name) {
        super(name);
    }
    
    
    private class Actor implements IActor {
        public int runCount = 0;
        public boolean itRan = false;
        public Object perform(Request action) {
            itRan = true;
            ++runCount;
            return null;
        }
        public void reset() {
            itRan = false;
            runCount = 0;
        }
    };
    
    public void testNew() {
        WorkQueueManager m = new WorkQueueManager();
        assertEquals("interested actors should be empty", m.rootQueue.interestedActors.isEmpty(), true);
        assertEquals("The root queue's parent should be null", m.rootQueue.parent, null);
        assertEquals("Subtypes list should be empty", m.rootQueue.subtypes.isEmpty(), true);
        assertEquals("root object type should be Object.class", m.rootQueue.type, Object.class);
    }
    
    
    private interface i1 {}
    
    private interface i2 extends i1 {}
    
    private interface i3 extends i2 {}
    
    private class o1 {}
    
    private class o2 extends o1 {}
    
    private class o3 extends o2 implements i2 {}
    
    private class o4 extends o3 {}
    
    /**
     * Method checkSubclasses.  Make sure that all subclasses are in fact subclasses.
     * This should always be an invariant for this class.
     * 
     * @param q
     */
    private void checkSubclasses(ActivationQueue q) {
        Iterator i = q.subtypes.iterator();
        while (i.hasNext()) {
            ActivationQueue subtype = (ActivationQueue) i.next();
            // Make sure the subtype is assignable to the supertype
            assertEquals(subtype.type.getName() + " is not assignable to " + q.type.getName(), q.type.isAssignableFrom(subtype.type), true);
            
            // Make sure that Object is the only object-based supertype of an interface
            if (subtype.type.isInterface()) {
                if (!q.type.isInterface()) {
                    assertEquals(q.type, Object.class);
                    checkSubclasses(subtype);
                    continue;
                }
            }
            
            // Otherwises, objects are subtypes of objects and interfaces are subtypes of interfaces
            assertEquals(q.type.getName() + " and " + subtype.type.getName() + " must both be objects or interfaces", q.type.isInterface(), subtype.type.isInterface());
            
            // Recurse....
            checkSubclasses(subtype);
        }
    }

    public void testAddHierarchyCreation() {
        WorkQueueManager m = new WorkQueueManager();
        checkSubclasses(m.rootQueue);

        // Make sure that the class hierarchy invariant is always true
        Actor a1 = new Actor();
        m.add(Object.class, a1, 0);
        checkSubclasses(m.rootQueue);
        m.add(o3.class, a1, 0);
        checkSubclasses(m.rootQueue);
        m.add(o2.class, a1, 0);
        checkSubclasses(m.rootQueue);
        m.add(i2.class, a1, 0);
        checkSubclasses(m.rootQueue);
        m.add(o1.class, a1, 0);
        checkSubclasses(m.rootQueue);
        m.add(o4.class, a1, 0);
        checkSubclasses(m.rootQueue);
        m.add(i1.class, a1, 0);
        checkSubclasses(m.rootQueue);
        m.add(i3.class, a1, 0);
        checkSubclasses(m.rootQueue);
    }

    public void testQueueCreationAndExecute() throws Exception {
        WorkQueueManager m = new WorkQueueManager();
        
        Actor a1_i2 = new Actor();

        m.add(i2.class, a1_i2, 0);
        m.execute(new o2());
        assertEquals(a1_i2.itRan, false);

        m.execute(new o3());
        assertEquals(a1_i2.itRan, true);

        a1_i2.reset();
        m.execute(new o4());
        assertEquals(a1_i2.itRan, true);
        
        Actor a2_o4 = new Actor();

        m.add(o4.class, a2_o4, 0);
        a1_i2.reset();
        m.execute(new o4());
        assertEquals(a2_o4.itRan, true);
        assertEquals(a1_i2.itRan, true);

        a1_i2.reset();
        a2_o4.reset();
        m.execute(new o2());
        assertEquals(a1_i2.itRan, false);
        assertEquals(a2_o4.itRan, false);

        a1_i2.reset();
        a2_o4.reset();
        m.execute(new o3());
        assertEquals(a1_i2.itRan, true);
        assertEquals(a2_o4.itRan, false);
        
        Actor a4_i2 = new Actor();
        m.add(i2.class, a4_i2, 0);
        m.execute(new o3());
        assertEquals(a1_i2.itRan, true);
        assertEquals(a4_i2.itRan, true);

        Actor a3_i3 = new Actor();
        m.add(i3.class, a3_i3, 0);
        m.execute(new o3());
        assertEquals(a3_i3.itRan, false);
    }
    
    public void testRemove() throws Exception {
        WorkQueueManager m = new WorkQueueManager();
        
        Actor a1_i2 = new Actor();

        m.add(i2.class, a1_i2, 0);
        m.execute(new o3());
        assertEquals(a1_i2.itRan, true);
        a1_i2.reset();
        m.remove(i2.class, a1_i2);
        m.execute(new o3());
        assertEquals(a1_i2.itRan, false);

        Actor a2_o3 = new Actor();
        
        m.add(o3.class, a2_o3, 0);
        m.execute(new o4());
        assertEquals(a2_o3.itRan, true);
        a2_o3.reset();
        m.remove(o3.class, a2_o3);
        m.execute(new o4());
        assertEquals(a2_o3.itRan, false);
        
        Actor a3_i2o3 = new Actor();
        
        // Make sure that remove behaves correctly when multiple copies of the
        // same actor have been registered for an object (ie: the actor was
        // registered on the object itself and on an interface implemented by
        // the object).
        m.add(o3.class, a3_i2o3, 0);
        m.add(i2.class, a3_i2o3, IPriority.high);
        m.execute(new o3());
        assertEquals("Action should have run once", 1, a3_i2o3.runCount);
        a3_i2o3.reset();
        m.remove(i2.class, a3_i2o3);
        m.execute(new o3());
        assertEquals("Action should have run once", 1, a3_i2o3.runCount);
        a3_i2o3.reset();
        m.remove(o3.class, a3_i2o3);
        m.execute(new o3());
        assertEquals("Action should not have run", false, a3_i2o3.itRan);
    }
    
}


