/*
 * This file is part of com.swtworkbench.rcplite.
 *
 * com.swtworkbench.rcplite is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License
 * as published by the Free Software Foundation.
 *
 * com.swtworkbench.rcplite is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with com.swtworkbench.rcplite; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.swtworkbench.rcplite;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.swt.graphics.Image;

/**
 * Class PerspectiveManager.  The object responsible for creating and managing
 * the UI for perspectives.<p>
 * 
 * @author daveo
 */
public class PerspectiveManager {
    private MainWindow mainWindow;

    /**(non-API)
     * Constructor PerspectiveManager.  
     * 
     * @param mainWindow
     */
    public PerspectiveManager(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        mainWindow.getStatusBar().addPerspectiveChangeListener(perspectiveChangeListener);
    }
    
    private HashMap perspectives = new HashMap();
    
    /**
     * Method create.  Create, register, and return a new Perspective object.
     * 
     * @param id The String ID of the perspective
     * @param name The perspective's name that will appear on its button
     * @param icon The perspective's icon or null if there is none
     * @return The new Perspective object
     */
    public Perspective create(String id, String name, Image icon) {
        mainWindow.getStatusBar().addPerspective(id, name, icon);
        Perspective result = new Perspective(id, name, mainWindow);
        if (perspectives.size() == 0) {
            mainWindow.getContentPane().showPage(result);
            current = id;
        }
        perspectives.put(id, result); 
        return result;
    }

    private LinkedList perspectiveChangeListeners = new LinkedList();

    private IPerspectiveChangeListener perspectiveChangeListener = new IPerspectiveChangeListener() {
        public boolean perspectiveChange(String current, String previous) {
            boolean canDoIt = firePerspectiveChangeEvent(current, previous);
            if (canDoIt) 
                select(current);
            return canDoIt;
        }
    };
    
    /**
     * Method addPerspectiveChangeListener.  Add a perspective change listener
     * to the PerspectiveManager.
     * 
     * @param l The listener.
     */
    public void addPerspectiveChangeListener(IPerspectiveChangeListener l) {
        perspectiveChangeListeners.add(l);
    }
    
    /**
     * Method removePerspectiveChangeListener. Remove a perspective change 
     * listener from the PerspectiveManager.
     *   
     * @param l The listener.
     */
    public void removePerspectiveChangeListener(IPerspectiveChangeListener l) {
        perspectiveChangeListeners.remove(l);
    }
    
    /**
     * Method firePerspectiveChangeEvent.  Indicate that a perspective change is about
     * to occur and allow any listener to veto the perspective change event.
     * 
     * @param current
     * @param previous
     */
    protected boolean firePerspectiveChangeEvent(String current, String previous) {
        for (Iterator i = perspectiveChangeListeners.iterator(); i.hasNext();) {
            IPerspectiveChangeListener l = (IPerspectiveChangeListener)i.next();
            if (!l.perspectiveChange(current, previous))
                return false;
        }
        return true;
    }
    
    private String current = "";
    
    /**
     * Method getCurrent.  Returns the current perspective's ID.
     * 
     * @return The current perspective's ID
     */
    public String getCurrentId() {
        return current;
    }
    
    /**
     * Method getCurrentPerspective.  Returns the current Perspective.
     * 
     * @return The current perspective object
     */
    public Perspective getCurrentPerspective() {
        return (Perspective)perspectives.get(current);
    }
    
    /**
     * Method getPerspective.  Get a Perspective object based on its ID.
     * 
     * @param id The perspective ID
     * @return The Perspective associated with the given ID or null if the given perspective
     *               does not exist.
     */
    public Perspective getPerspective(String id) {
        return (Perspective)perspectives.get(id);
    }

    /**
     * Method select.  Changes the current perspective to a different 
     * perspective.
     * 
     * @param id the ID of the perspective to switch to
     * @return true if the change was successful; false otherwise.
     */
    public boolean select(String id) {
        boolean canChange = firePerspectiveChangeEvent(id, current);
        if (canChange) {
            Perspective perspective = (Perspective)perspectives.get(id);
            if (perspective == null)
                return false;
            mainWindow.getStatusBar().selectPerspective(id);
            mainWindow.getContentPane().showPage(perspective);
            current = id;
            perspective.setFocus();
        }
        return canChange;
    }
}


