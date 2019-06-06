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

import com.swtworkbench.bus.IActor;

/**
 * Class ActorActivation.  
 * 
 * @author daveo
 */
public class ActorActivation implements Serializable {
    protected Class interestingType;
    protected IActor actor;
    protected int priority;
    
    public ActorActivation(Class interestingType, IActor actor, int priority) {
        this.interestingType = interestingType;
        this.actor = actor;
        this.priority = priority;
    }
        
    public boolean equals(Object arg0) {
        if (!(arg0 instanceof ActorActivation))
            return false;
        ActorActivation otherNode = (ActorActivation) arg0;
        return (otherNode.interestingType.equals(interestingType) &&
                 otherNode.actor == actor &&
                 otherNode.priority == priority);
    }

}
