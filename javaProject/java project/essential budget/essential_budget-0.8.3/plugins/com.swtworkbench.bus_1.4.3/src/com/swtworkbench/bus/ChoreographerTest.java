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

import junit.framework.TestCase;

/**
 * Class ChoreographerTest.  
 * 
 * @author daveo
 */
public class ChoreographerTest extends TestCase {

    /**
     * Constructor for ChoreographerTest.
     * @param name
     */
    public ChoreographerTest(String name) {
        super(name);
    }
    
    private class o1 {
        public int a1 = 0;
        public int a2 = 0;
    }
    
    private class Actor1 implements IActor {
        public boolean itRan = false;
        public Object perform(Request action) {
            itRan = true;
            o1 element = (o1) action.action;
            ++element.a1;
            return null;
        }
    };

    private class Actor2 implements IActor {
        public boolean itRan = false;
        public Object perform(Request action) {
            itRan = true;
            o1 element = (o1) action.action;
            ++element.a2;
            action.fulfilled = true;
            return null;
        }
    };
    
    public void testPriorityExecution() throws Exception {
        Choreographer c = new Choreographer();
        c.register(new Actor1());
        c.registerActor(new Actor2(), IPriority.high);
        o1 o = new o1();
        c.request(o);
        assertEquals("Actor1 should have been eclipsed", 0, o.a1);
        assertEquals("Actor2 should have run once", 1, o.a2);
    }
}
