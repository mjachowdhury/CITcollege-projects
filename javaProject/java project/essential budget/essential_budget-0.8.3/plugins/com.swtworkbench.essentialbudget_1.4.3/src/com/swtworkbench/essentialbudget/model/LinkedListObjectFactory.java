/*
 * This file is part of com.swtworkbench.essentialbudget.
 *
 * com.swtworkbench.essentialbudget is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License
 * as published by the Free Software Foundation.
 *
 * com.swtworkbench.essentialbudget is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with com.swtworkbench.essentialbudget; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.swtworkbench.essentialbudget.model;

import java.io.Serializable;
import java.util.LinkedList;

import com.swtworkbench.ed.aware.model.IObjectFactory;
import com.swtworkbench.ed.aware.model.NewObject;
import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class LinkedListObjectFactory.  A generic object factory that appends new
 * objects to a LinkedList.
 * 
 * @author daveo
 */
public class LinkedListObjectFactory implements IObjectFactory, Serializable {
    private Class toCreate;
        
    public LinkedListObjectFactory(Class toCreate) {
        this.toCreate = toCreate;
    }

    public NewObject getNewObject(Object collection) {
        LinkedList list = (LinkedList) collection;
        Object newObject=null;
        try {
            newObject = toCreate.newInstance();
        } catch (InstantiationException e) {
            Logger.log().error(e, "Unable to instantiate " + toCreate.getName());
        } catch (IllegalAccessException e) {
            Logger.log().error(e, "Unable to access " + toCreate.getName());
        }
        if (newObject != null) {
            list.addLast(newObject);
            return new NewObject(newObject, list.size()-1);
        }
        return null;
    }
}
