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
 * Class Person.
 * 
 * @author djo
 */
public class Person {
    private String firstName="";
    private String lastName="";
    private LinkedList acquaintances = new LinkedList();
    
    /**
     * @return Returns the acquaintances.
     */
    public LinkedList getAcquaintances() {
        return acquaintances;
    }
    /**
     * @param acquaintances The acquaintances to set.
     */
    public void setAcquaintances(LinkedList acquaintances) {
        this.acquaintances = acquaintances;
    }
    /**
     * Get an object factory for acquaintances...
     * @return
     */
    public IObjectFactory getAcquaintancesObjectFactory() {
        return new IObjectFactory() {
            public NewObject getNewObject(Object collection) {
                int position = (int)(Math.random() * acquaintances.size());
                Person newPerson = new Person();
                acquaintances.add(position, newPerson);
                return new NewObject(newPerson, position);
            }
        };
    }
    /**
     * @return Returns the firstName.
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * @param firstName The firstName to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * @return Returns the lastName.
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * @param lastName The lastName to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
