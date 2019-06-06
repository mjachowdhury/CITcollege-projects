/*
 * This file is part of com.swtworkbench.ed.
 *
 * com.swtworkbench.ed is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License
 * as published by the Free Software Foundation.
 *
 * com.swtworkbench.ed is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with com.swtworkbench.ed; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.swtworkbench.ed.controls.mrotable.test.masterdetail;

import java.util.LinkedList;

import com.swtworkbench.ed.aware.model.IObjectFactory;
import com.swtworkbench.ed.aware.model.NewObject;

/**
 * Class People.
 * 
 * @author djo
 */
public class People {
    private LinkedList people = new LinkedList();
    
    /**
     * @return Returns the people.
     */
    public LinkedList getPeople() {
        return people;
    }
    /**
     * @param people The people to set.
     */
    public void setPeople(LinkedList people) {
        this.people = people;
    }
    /**
     * Get an object factory for people...
     * @return
     */
    public IObjectFactory getPeopleObjectFactory() {
        return new IObjectFactory() {
            public NewObject getNewObject(Object collection) {
                int position = (int)(Math.random() * people.size());
                Person newPerson = new Person();
                people.add(position, newPerson);
                return new NewObject(newPerson, position);
            }
        };
    }
}
